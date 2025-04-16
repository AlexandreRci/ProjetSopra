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
@Table(name = "possession")
public class Possession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int quantite;
    @Enumerated(EnumType.STRING)
    @Column(name = "ressource", columnDefinition = "ENUM('Arme','Nourriture','Energie','Argent')", nullable = false)
    private Ressource ressource;


    public Possession() {
    }

    public Possession(Integer id, int quantite, Ressource ressource) {
        this.id = id;
        this.quantite = quantite;
        this.ressource = ressource;
    }

    public Possession(int quantite, Ressource ressource) {
        this.quantite = quantite;
        this.ressource = ressource;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    @Override
    public String toString() {
        return "Possession [id=" + id + ", quantite=" + quantite + ", ressource=" + ressource + "]";
    }


}
