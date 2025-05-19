package space.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import space.model.*;
import space.rest.request.ActionRequest;
import space.rest.request.PartieRequest;
import space.rest.request.StartRequest;
import space.rest.response.PartieResponse;
import space.rest.response.StartResponse;
import space.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/partie")
public class PartieRestController {
    private final PartieService partieService;
    private final JoueurService joueurService;
    private final PlanetSeedService planetSeedService;
    private final EspeceService especeService;
    private final PlaneteService planeteService;
    private  final CompteService compteService;

    public PartieRestController(
            PartieService partieService,
            JoueurService joueurService,
            PlanetSeedService planetSeedService,
            EspeceService especeService,
            PlaneteService planeteService,
            CompteService compteService) {
        this.partieService = partieService;
        this.joueurService = joueurService;
        this.planetSeedService = planetSeedService;
        this.especeService = especeService;
        this.planeteService = planeteService;
        this.compteService = compteService;
    }

    @GetMapping("")
    public List<PartieResponse> getAll() {
        List<Partie> parties = this.partieService.getAll();

        return parties.stream().map(PartieResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public PartieResponse getById(@PathVariable Integer id) {
        try {
            return PartieResponse.convert(partieService.getById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public PartieResponse create(@RequestBody PartieRequest partieRequest) {
        Partie partie = PartieRequest.convert(partieRequest);
        partie = partieService.create(partie);
        for (Joueur joueur : partie.getJoueurs()) {
            try {
                joueur = joueurService.getById(joueur.getId());
                joueur.setPartie(partie);
                joueurService.create(joueur);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur non trouvé " + joueur.getId());
            }
        }

        return PartieResponse.convert(partie);
    }

    /**
     * Start a new game
     * <p>
     * Create species if they don't already exist
     * Create planets if they don't already exist
     * Create and add 20 planetSeed to the partie
     *
     * @param startRequest front request information to start a game
     * @return HttpStatus
     */
    @PostMapping("/start")
    public StartResponse start(@RequestBody StartRequest startRequest) {
        Random random = new Random();

        Partie partie = new Partie(0, 1, 4, Statut.DEBUT);
        //To be modified as maybe unnecessary (check if adding a joueur to a partie also adds it into the partie list
        // of player
        Utilisateur utilisateur = null;
        try {
            utilisateur = (Utilisateur) compteService.getById(startRequest.getUserId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utilisateur inconnu");
        }
        partie.addJoueur(new Joueur(0, partie, null, utilisateur));
        partie = partieService.create(partie);
        //Create base species
        if (especeService.getAll().isEmpty()) {
            especeService.create(new Espece("humain", 1.0, 0.75, 0.25, 0.5));
            especeService.create(new Espece("insecte", 0.25, 1.0, 0.5, 0.75));
            especeService.create(new Espece("robot", 0.5, 0.25, 0.75, 1.0));
            especeService.create(new Espece("triton", 0.75, 0.5, 1.0, 0.25));
        }
        //Attribute a species to the player
        Joueur joueur;
        try {
            joueur = new Joueur(0, partie, especeService.getById(startRequest.getIdEspece()), utilisateur);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Espece inconnue");
        }
        joueur = joueurService.create(joueur);
        partie.addJoueur(joueur);
        //Create base planets
        if (planeteService.getAll().isEmpty()) {
            for (PlanetNames planetName : PlanetNames.values()) {
                List<Biome> biomes = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    biomes.add(Biome.values()[random.nextInt(Biome.values().length)]);
                }
                Planete planete = new Planete(planetName.toString(), random.nextInt(20, 50), biomes);
                planeteService.create(planete);
            }
        }
        //Initialize the 20 planetSeeds in the partie
        List<Planete> planetes = planeteService.getAll();
        for (int i = 0; i < 20; i++) {
            int selectedPlanet = random.nextInt(planetes.size());
            Planete planete = planetes.get(selectedPlanet);
            planetes.remove(selectedPlanet);
            PlanetSeed planetSeed = new PlanetSeed(
                    random.nextInt(200, 400),
                    0,
                    random.nextInt(20, 50),
                    null,
                    planete
            );
            planetSeedService.create(planetSeed);
        }
        // Attribute a randow planet to the player
        List<PlanetSeed> planetSeeds = planetSeedService.getAll();
        PlanetSeed planetSeed = planetSeeds.get(random.nextInt(planetSeeds.size()));
        planetSeed.setJoueur(joueur);
        planetSeedService.update(planetSeed);

        StartResponse startResponse = new StartResponse();
        startResponse.setIdGame(partie.getId());
        startResponse.setIdPlayer(joueur.getId());
        return startResponse;
    }

    /**
     * Apply the actions asked by the player
     *
     * @param actionRequest Actions asked by a player
     * @return HttpStatus
     * @throws Exception unable to obtain the player or planet
     */
    @PostMapping("/action")
    public HttpStatus action(@RequestBody ActionRequest actionRequest) throws Exception {
        Joueur player = joueurService.getById(actionRequest.getIdPlayer());
        Action[] actions = actionRequest.getActions();
        Integer[] targetPlanets = actionRequest.getTargetPlanetsId();
        Ressource[] buildingTypes = actionRequest.getBuildingTypes();
        Taille[] buildingSizes = actionRequest.getBuildingSizes();
        Possession possession;

        for (int i = 0; i < actions.length; i++) {
            PlanetSeed planetSeed = planetSeedService.getById(targetPlanets[i]);
            switch (actions[i]) {
                case ATTACK:
                    possession = player.searchByRessource(Ressource.ARME);
                    possession.setQuantite(possession.getQuantite() - actionRequest.getNbAttackers()[i]);
                    if (planetSeed.getPopulation() <= actionRequest.getNbAttackers()[i]) {
                        planetSeed.setJoueur(player);
                    }
                    int populationLeft = Math.abs(actionRequest.getNbAttackers()[i] - planetSeed.getPopulation());
                    planetSeed.setPopulation(populationLeft);

                case BUILD:
                    possession = player.searchByRessource(Ressource.ARGENT);
                    possession.setQuantite(possession.getQuantite() - buildingSizes[i].getPrix());
                    Batiment batiment = new Batiment(buildingTypes[i].name(), buildingSizes[i], buildingTypes[i]);
                    planetSeed.getBatiments().add(batiment);
            }
            planetSeedService.update(planetSeed);
        }
        joueurService.update(player);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public PartieResponse update(@RequestBody PartieRequest partieRequest, @PathVariable Integer id) {
        if (!id.equals(partieRequest.getId()) || !this.partieService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
        }

        Partie partie = PartieRequest.convert(partieRequest);

        return PartieResponse.convert(partieService.create(partie));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!this.partieService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
        }

        this.partieService.deleteById(id);
    }
}
