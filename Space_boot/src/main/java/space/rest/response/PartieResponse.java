package space.rest.response;

import org.springframework.beans.BeanUtils;
import space.model.Joueur;
import space.model.Partie;
import space.model.PlanetSeed;
import space.model.Statut;

import java.util.List;

public class PartieResponse {
    private Integer id;
    private int currentPosition;
    private int nbTour;
    private int nbJoueur;
    private List<Integer> joueurs;
    private List<Integer> planetSeeds;
    private Statut statut;

    public PartieResponse() {
    }

    public static PartieResponse convert(Partie partie) {
        PartieResponse partieResponse = new PartieResponse();
        partieResponse.setJoueurs(partie.getJoueurs().stream().map(Joueur::getId).toList());
        partieResponse.setPlanetSeeds(partie.getPlanetSeeds().stream().map(PlanetSeed::getId).toList());
        BeanUtils.copyProperties(partie, partieResponse);
        return partieResponse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getNbTour() {
        return nbTour;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }

    public List<Integer> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Integer> joueurs) {
        this.joueurs = joueurs;
    }

    public List<Integer> getPlanetSeeds() {
        return planetSeeds;
    }

    public void setPlanetSeeds(List<Integer> planetSeeds) {
        this.planetSeeds = planetSeeds;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
