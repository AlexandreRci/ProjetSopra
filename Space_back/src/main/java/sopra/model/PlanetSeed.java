package sopra.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "planet_seed")
public class PlanetSeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int population;
    private int arme;
    @Column(name = "minerai_restant")
    private int mineraiRestant;
    @ManyToOne
    @JoinColumn(name = "joueur_id", nullable = false)
    private Joueur joueur;
    @ManyToOne
    @JoinColumn(name = "planete_id", nullable = false)
    private Planete planete;
    @OneToMany
    @JoinTable(name = "batiment_sur_planete",
            joinColumns = @JoinColumn(name = "planet_seed_id"),
            inverseJoinColumns = @JoinColumn(name = "batiment_id"))
    private List<Batiment> batiments;


    public PlanetSeed() {
    }

    public PlanetSeed(Integer id, int population, int arme, int mineraiRestant, Joueur joueur, Planete planete) {
        this.id = id;
        this.population = population;
        this.arme = arme;
        this.mineraiRestant = mineraiRestant;
        this.joueur = joueur;
        this.planete = planete;
    }

    public PlanetSeed(int population, int arme, int mineraiRestant, Joueur joueur, Planete planete) {
        this.population = population;
        this.arme = arme;
        this.mineraiRestant = mineraiRestant;
        this.joueur = joueur;
        this.planete = planete;
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


    public Joueur getJoueur() {
        return joueur;
    }


    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }


    public Planete getPlanete() {
        return planete;
    }


    public void setPlanete(Planete planete) {
        this.planete = planete;
    }


    public List<Batiment> getBatiments() {
        return batiments;
    }


    public void setBatiments(List<Batiment> batiments) {
        this.batiments = batiments;
    }

    @Override
    public String toString() {
        return "PlanetSeed [id=" + id + ", population=" + population + ", arme=" + arme + ", mineraiRestant="
                + mineraiRestant + ", joueur=" + joueur.getId() + ", planete=" + planete.getNom() +
                "]";
    }

}
