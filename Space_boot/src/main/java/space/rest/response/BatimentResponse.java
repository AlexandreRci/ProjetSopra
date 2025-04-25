package space.rest.response;

import org.springframework.beans.BeanUtils;
import space.model.Batiment;
import space.model.Ressource;
import space.model.Taille;

public class BatimentResponse {

    private Integer id;
    private String nom;
    private Taille taille;
    private Ressource ressource;


    public BatimentResponse() {
        super();
    }

    public static BatimentResponse convert(Batiment batiment) {
        BatimentResponse batimentResponse = new BatimentResponse();

        BeanUtils.copyProperties(batiment, batimentResponse);

        return batimentResponse;
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
}