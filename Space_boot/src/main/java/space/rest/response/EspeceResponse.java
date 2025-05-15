package space.rest.response;

import org.springframework.beans.BeanUtils;
import space.model.Biome;
import space.model.Espece;

import java.util.EnumMap;
import java.util.Map;

public class EspeceResponse {

    private Integer id;
    private String nom;
    private Map<Biome, Double> biomes = new EnumMap<>(Biome.class);


    public EspeceResponse() {
        super();
    }

    public static EspeceResponse convert(Espece espece) {
        EspeceResponse especeResponse = new EspeceResponse();
        BeanUtils.copyProperties(espece, especeResponse); //copie des info d'espece vers especeResponse
        return especeResponse;
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
