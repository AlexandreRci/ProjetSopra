package sopra.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name = "planete")
public class Planete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private int minerai;
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Biome.class)
    @JoinTable(name = "biomes_sur_planete", joinColumns = @JoinColumn(name = "planete"))
    @Column(name = "biome", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Biome> biomes;


    public Planete() {
    }


    public Planete(Integer id, String nom, int minerai, List<Biome> biomes) {
        this.id = id;
        this.nom = nom;
        this.minerai = minerai;
        this.biomes = biomes;
    }


    public Planete(String nom, int minerai, List<Biome> biomes) {
        this.nom = nom;
        this.minerai = minerai;
        this.biomes = biomes;
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


    public int getMinerai() {
        return minerai;
    }


    public void setMinerai(int minerai) {
        this.minerai = minerai;
    }


    public List<Biome> getBiomes() {
        return biomes;
    }


    public void setBiomes(List<Biome> biomes) {
        this.biomes = biomes;
    }


    @Override
    public String toString() {
        return "Planete{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", minerai=" + minerai +
                '}';
    }
}
