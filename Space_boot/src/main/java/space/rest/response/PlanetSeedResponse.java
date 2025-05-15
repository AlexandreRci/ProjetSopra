package space.rest.response;

import org.springframework.beans.BeanUtils;
import space.model.Batiment;
import space.model.PlanetSeed;

import java.util.List;

public class PlanetSeedResponse {
    private Integer id;
    private int population;
    private int arme;
    private int mineraiRestant;
    private Integer idJoueur;
    private Integer idPlanete;
    private List<Integer> idBatiments;


    public PlanetSeedResponse() {
        super();
    }

    public static PlanetSeedResponse convert(PlanetSeed planetSeed) {
        PlanetSeedResponse planetSeedResponse = new PlanetSeedResponse();
        BeanUtils.copyProperties(planetSeed, planetSeedResponse);

        if (planetSeed.getJoueur() != null) {
            Integer idJoueur = planetSeed.getJoueur().getId();
            planetSeedResponse.setIdJoueur(idJoueur);
        }

        if (planetSeed.getPlanete() != null) {
            Integer idPlanete = planetSeed.getPlanete().getId();
            planetSeedResponse.setIdPlanete(idPlanete);
        }

        if (planetSeed.getBatiments() != null && !planetSeed.getBatiments().isEmpty()) {
            planetSeedResponse.setIdBatiments(planetSeed.getBatiments().stream().map(Batiment::getId).toList());

        }

        return planetSeedResponse;
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
