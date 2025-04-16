package space.rest.request;


import org.springframework.beans.BeanUtils;
import space.model.*;

public class PossessionRequest {
    private Integer id;
    private int quantite;
    private Ressource ressource;

    public PossessionRequest() {
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

    public static Possession convert(PossessionRequest possessionRequest) {
        Possession possession = new Possession();
        BeanUtils.copyProperties(possessionRequest, possession);
        return possession;
    }

}
