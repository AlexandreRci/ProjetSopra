package sopra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur extends Compte {

    @Column(nullable = false)
    protected String name;


    public Utilisateur() {
    }

    public Utilisateur(Integer id, String username, String password, String name) {
        super(id, username, password);
        this.name = name;

    }

    public Utilisateur(String username, String password, String name) {
        super(username, password);
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + "]";
    }


}
