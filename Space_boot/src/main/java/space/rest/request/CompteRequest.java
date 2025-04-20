package space.rest.request;

import org.springframework.beans.BeanUtils;
import space.model.Admin;
import space.model.Compte;
import space.model.Utilisateur;

public class CompteRequest {
    private Integer id;
    private String username;
    private String password;
    private CompteType compteType;
    private String name;

    public CompteRequest() {
        super();
    }

    public static Compte convert(CompteRequest compteRequest) {
        Compte compte = null;
        if (compteRequest.getCompteType() == CompteType.ADMIN) {
            compte = new Admin();
        } else if (compteRequest.getCompteType() == CompteType.UTILISATEUR) {
            compte = new Utilisateur();
        }
        BeanUtils.copyProperties(compteRequest, compte);

        return compte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CompteType getCompteType() {
        return compteType;
    }

    public void setCompteType(CompteType compteType) {
        this.compteType = compteType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum CompteType {
        ADMIN, UTILISATEUR
    }
}
