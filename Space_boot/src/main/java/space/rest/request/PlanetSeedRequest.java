package space.rest.request;

import org.springframework.beans.BeanUtils;
import space.model.Batiment;
import space.model.Joueur;
import space.model.PlanetSeed;
import space.model.Planete;

import java.util.ArrayList;
import java.util.List;

public class PlanetSeedRequest {
    private Integer id;
    private int population;
    private int arme;
    private int mineraiRestant;
    private Integer idJoueur;
    private Integer idPlanete;
    private List<Integer> idBatiments;


    public PlanetSeedRequest() {
        super();
    }

    public static PlanetSeed convert(PlanetSeedRequest planetSeedRequest) {
        PlanetSeed planetSeed = new PlanetSeed();
        BeanUtils.copyProperties(planetSeedRequest, planetSeed);

        if (planetSeedRequest.getIdJoueur() != null) {
            Joueur joueur = new Joueur();
            joueur.setId(planetSeedRequest.getIdJoueur());
            planetSeed.setJoueur(joueur);
        }

        if (planetSeedRequest.getIdPlanete() != null) {
            Planete planete = new Planete();
            planete.setId(planetSeedRequest.getIdPlanete());
            planetSeed.setPlanete(planete);
        }

        if (planetSeedRequest.getIdBatiments() != null && !planetSeedRequest.getIdBatiments().isEmpty()) {
            List<Batiment> batiments = new ArrayList<>();
            for (Integer idBatiment : planetSeedRequest.getIdBatiments()) {
                Batiment batiment = new Batiment();
                batiment.setId(idBatiment);
                batiments.add(batiment);
            }
            planetSeed.setBatiments(batiments);
        }

        return planetSeed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getArme() {
        return arme;
    }

    public void setArme(int arme) {
        this.arme = arme;
    }

    public int getMineraiRestant() {
        return mineraiRestant;
    }

    public void setMineraiRestant(int mineraiRestant) {
        this.mineraiRestant = mineraiRestant;
    }

    public Integer getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Integer idJoueur) {
        this.idJoueur = idJoueur;
    }

    public Integer getIdPlanete() {
        return idPlanete;
    }

    public void setIdPlanete(Integer idPlanete) {
        this.idPlanete = idPlanete;
    }

    public List<Integer> getIdBatiments() {
        return idBatiments;
    }

    public void setIdBatiments(List<Integer> idBatiments) {
        this.idBatiments = idBatiments;
    }
}
