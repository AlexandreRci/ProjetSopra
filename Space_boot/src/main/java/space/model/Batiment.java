package space.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "batiment")
public class Batiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    //à coder , pas sûr de moi!!!!!
    @Enumerated(EnumType.STRING)
    @Column(name = "taille", columnDefinition = "ENUM('Petit','Moyen','Grand')", nullable = false)
    private Taille taille;
    @Enumerated(EnumType.STRING)
    @Column(name = "ressource", columnDefinition = "ENUM('Arme','Nourriture','Energie','Argent')", nullable = false)
    private Ressource ressource;


    public Batiment() {
    }


    public Batiment(Integer id, String nom, Taille taille, Ressource ressource) {
        this.id = id;
        this.nom = nom;
        this.taille = taille;
        this.ressource = ressource;
    }


    public Batiment(String nom, Taille taille, Ressource ressource) {
        this.nom = nom;
        this.taille = taille;
        this.ressource = ressource;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public Taille getTaille() {
        return taille;
    }


    public void setTaille(Taille taille) {
        this.taille = taille;
    }


    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    @Override
    public String toString() {
        return "Batiment [id=" + id + ", nom=" + nom + ", taille=" + taille + ", ressource=" + ressource + "]";
    }


}
