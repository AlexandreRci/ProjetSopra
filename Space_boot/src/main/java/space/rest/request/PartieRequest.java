package space.rest.request;


import org.springframework.beans.BeanUtils;
import space.model.Joueur;
import space.model.Partie;
import space.model.PlanetSeed;
import space.model.Statut;

import java.util.List;

public class PartieRequest {
    private Integer id;
    private int currentPosition;
    private int nbTour;
    private int nbJoueur;
    private List<Integer> joueurs;
    private List<Integer> planetSeeds;
    private Statut statut;

    public PartieRequest() {
    }

    public static Partie convert(PartieRequest partieRequest) {
        Partie partie = new Partie();
        if (partieRequest.getJoueurs()!= null){
            partie.setJoueurs(partieRequest.getJoueurs().stream().map(id -> {
                Joueur joueur = new Joueur();
                joueur.setId(id);
                return joueur;
            }).toList());
        }
        if (partieRequest.getPlanetSeeds() != null){
            partie.setPlanetSeeds(partieRequest.getPlanetSeeds().stream().map(id -> {
                PlanetSeed planetSeed = new PlanetSeed();
                planetSeed.setId(id);
                return planetSeed;
            }).toList());
        }
        BeanUtils.copyProperties(partieRequest, partie);
        return partie;
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
