package space.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "joueur")
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private int position;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "joueur_possession",
            joinColumns = @JoinColumn(name = "joueur_id"),
            inverseJoinColumns = @JoinColumn(name = "possession_id"))
    private List<Possession> possessions;
    @ManyToOne
    @JoinColumn(name = "partie_id", nullable = false)
    private Partie partie;
    @ManyToOne
    @JoinColumn(name = "espece_id", nullable = false)
    private Espece espece;
    @OneToMany(mappedBy = "joueur")
    private List<PlanetSeed> planetSeeds;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;


    public Joueur() {
    }

    public Joueur(Integer id, int position, Partie partie, Espece espece, Utilisateur utilisateur) {
        this.id = id;
        this.position = position;
        this.partie = partie;
        this.espece = espece;
        this.utilisateur = utilisateur;
    }

    public Joueur(int position, Partie partie, Espece espece, Utilisateur utilisateur) {
        this.position = position;
        this.partie = partie;
        this.espece = espece;
        this.utilisateur = utilisateur;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public int getPosition() {
        return position;
    }


    public void setPosition(int position) {
        this.position = position;
    }


    public List<Possession> getPossessions() {
        return possessions;
    }


    public void setPossessions(List<Possession> possessions) {
        this.possessions = possessions;
    }


    public Partie getPartie() {
        return partie;
    }


    public void setPartie(Partie partie) {
        this.partie = partie;
    }


    public Espece getEspece() {
        return espece;
    }


    public void setEspece(Espece espece) {
        this.espece = espece;
    }


    public List<PlanetSeed> getPlanetSeeds() {
        return planetSeeds;
    }


    public void setPlanetSeeds(List<PlanetSeed> planetSeeds) {
        this.planetSeeds = planetSeeds;
    }


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id=" + id +
                ", position=" + position +
                ", partie=" + partie.getId() +
                ", espece=" + espece.getNom() +
                ", utilisateur=" + utilisateur.getUsername() +
                '}';
    }

    public Possession searchByRessource(Ressource ressource) {
        for (Possession possession : possessions) {
            if (possession.getRessource().equals(ressource)) {
                return possession;
            }
        }
        return null;
    }
}
