package space.rest.request;

import org.springframework.beans.BeanUtils;
import space.model.Biome;
import space.model.Espece;

import java.util.EnumMap;
import java.util.Map;

public class EspeceRequest {

    private Integer id;
    private String nom;
    private Map<Biome, Double> biomes = new EnumMap<>(Biome.class);

    public EspeceRequest() {
        super();
    }

    public static Espece convert(EspeceRequest especeRequest) {
        Espece espece = new Espece();
        BeanUtils.copyProperties(especeRequest, espece);
        return espece;
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

}
