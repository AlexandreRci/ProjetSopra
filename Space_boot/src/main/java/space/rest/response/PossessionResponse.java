package space.rest.response;

import org.springframework.beans.BeanUtils;
import space.model.Possession;
import space.model.Ressource;

public class PossessionResponse {
    private Integer id;
    private int quantite;
    private Ressource ressource;

    public PossessionResponse() {
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

    public static PossessionResponse convert(Possession possession) {
        PossessionResponse possessionResponse = new PossessionResponse();
        BeanUtils.copyProperties(possession, possessionResponse);
        return possessionResponse;
    }
}
