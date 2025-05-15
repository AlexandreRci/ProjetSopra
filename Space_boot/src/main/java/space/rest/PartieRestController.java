package space.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import space.model.Joueur;
import space.model.Partie;
import space.rest.request.PartieRequest;
import space.rest.response.PartieResponse;
import space.service.JoueurService;
import space.service.PartieService;

import java.util.List;

@RestController
@RequestMapping("/partie")
public class PartieRestController {
    private final PartieService partieService;
    private final JoueurService joueurService;

    public PartieRestController(PartieService partieService, JoueurService joueurService) {
        this.partieService = partieService;
        this.joueurService = joueurService;
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
