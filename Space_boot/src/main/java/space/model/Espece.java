package space.model;

import jakarta.persistence.*;

import java.util.EnumMap;
import java.util.Map;

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
    private Map<Biome, Double> biomes = new EnumMap<>(Biome.class);


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

    public Espece(String nom, double ratePlain, double rateForest, double rateOcean, double rateDesert) {
        this.nom = nom;
        this.biomes = new EnumMap<>(Biome.class);
        this.biomes.put(Biome.PLAINE, ratePlain);
        this.biomes.put(Biome.FORET, rateForest);
        this.biomes.put(Biome.OCEAN, rateOcean);
        this.biomes.put(Biome.DESERTIQUE, rateDesert);
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
