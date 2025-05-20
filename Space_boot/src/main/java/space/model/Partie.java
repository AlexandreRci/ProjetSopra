package space.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partie")
public class Partie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "current_position")
    private int currentPosition;
    @Column(name = "nb_tour")
    private int nbTour;
    @Column(name = "nb_joueur")
    private int nbJoueur;
    @OneToMany(mappedBy = "partie")
    private List<Joueur> joueurs;
    @OneToMany(mappedBy = "partie")
    private List<PlanetSeed> planetSeeds;
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", columnDefinition = "ENUM('DEBUT','EN_COURS','FINI')", nullable = false)
    private Statut statut;


    public Partie() {
        this.planetSeeds = new ArrayList<>();
        this.joueurs = new ArrayList<>();
    }

    public Partie(Integer id, int currentPosition, int nbTour, int nbJoueur,
                  Statut statut) {
        this.id = id;
        this.currentPosition = currentPosition;
        this.nbTour = nbTour;
        this.nbJoueur = nbJoueur;
        this.statut = statut;
        this.planetSeeds = new ArrayList<>();
        this.joueurs = new ArrayList<>();
    }

    public Partie(int currentPosition, int nbTour, int nbJoueur, Statut statut) {
        this.currentPosition = currentPosition;
        this.nbTour = nbTour;
        this.nbJoueur = nbJoueur;
        this.statut = statut;
        this.planetSeeds = new ArrayList<>();
        this.joueurs = new ArrayList<>();
    }

    public void addJoueur(Joueur joueur) {
        if (this.joueurs == null) {
            this.joueurs = new java.util.ArrayList<>();
        }
        this.joueurs.add(joueur);
        joueur.setPartie(this);
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


    public List<Joueur> getJoueurs() {
        return joueurs;
    }


    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }


    public List<PlanetSeed> getPlanetSeeds() {
        return planetSeeds;
    }


    public void setPlanetSeeds(List<PlanetSeed> planetSeeds) {
        this.planetSeeds = planetSeeds;
    }


    public Statut getStatut() {
        return statut;
    }


    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Partie [id=" + id + ", currentPosition=" + currentPosition + ", nbTour=" + nbTour + ", nbJoueur="
                + nbJoueur + ", statut=" + statut + "]";
    }


}
