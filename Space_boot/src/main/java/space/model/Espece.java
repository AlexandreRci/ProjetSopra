package space.model;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "espece")
public class Espece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nom;
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "espece_biome", joinColumns = @JoinColumn(name = "espece_id"))
    @Column(name = "biome_value", nullable = false)
    private Map<Biome, Double> biomes = new HashMap<>();


    public Espece() {
    }

    public Espece(Integer id, String nom, Map<Biome, Double> biomes) {
        this.id = id;
        this.nom = nom;
        this.biomes = biomes;
    }

    public Espece(String nom, Map<Biome, Double> biomes) {
        this.nom = nom;
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


    public Map<Biome, Double> getBiomes() {
        return biomes;
    }


    public void setBiomes(Map<Biome, Double> biomes) {
        this.biomes = biomes;
    }

    @Override
    public String toString() {
        return "Espece [id=" + id + ", nom=" + nom + ", biomes=" + biomes + "]";
    }

}
