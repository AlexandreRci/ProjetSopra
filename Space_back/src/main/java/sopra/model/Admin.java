package sopra.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends Compte {

    public Admin() {
    }

    public Admin(Integer id, String username, String password) {
        super(id, username, password);

    }

    public Admin(String username, String password) {
        super(username, password);

    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
    }


}
