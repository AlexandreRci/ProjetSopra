package space.model;


import jakarta.persistence.*;

@Entity
@Table(name = "possession")
public class Possession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int quantite;
    @Enumerated(EnumType.STRING)
    @Column(name = "ressource", columnDefinition = "ENUM('ARME','NOURRITURE','ENERGIE','ARGENT')", nullable = false)
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
