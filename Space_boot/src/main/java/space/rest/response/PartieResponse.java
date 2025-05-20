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
    private Statut statut;
    private List<Integer> joueurs;
    private List<Integer> planetSeeds; // ← AJOUT

    public static PartieResponse convert(Partie partie) {
        PartieResponse partieResponse = new PartieResponse();

        // Copie des propriétés simples
        partieResponse.setId(partie.getId());
        partieResponse.setCurrentPosition(partie.getCurrentPosition());
        partieResponse.setNbTour(partie.getNbTour());
        partieResponse.setNbJoueur(partie.getNbJoueur());
        partieResponse.setStatut(partie.getStatut());

        // Conversion des IDs joueurs
        if (partie.getJoueurs() != null) {
            partieResponse.setJoueurs(
                    partie.getJoueurs().stream()
                            .filter(j -> j != null && j.getId() != null)
                            .map(Joueur::getId)
                            .toList());
        } else {
            partieResponse.setJoueurs(List.of());
        }

        // Conversion des IDs planetSeeds
        if (partie.getPlanetSeeds() != null) {
            partieResponse.setPlanetSeeds(
                    partie.getPlanetSeeds().stream()
                            .filter(p -> p != null && p.getId() != null)
                            .map(PlanetSeed::getId)
                            .toList());
        } else {
            partieResponse.setPlanetSeeds(List.of());
        }

        return partieResponse;
    }

    // Getters et setters...

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

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
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
}
