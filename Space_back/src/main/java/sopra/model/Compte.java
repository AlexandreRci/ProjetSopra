package sopra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seqC", sequenceName = "seq_compte")
public abstract class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqC")
    @Column(name = "id_compte")
    protected Integer id;
    @Column(nullable = false)
    protected String login;
    @Column(nullable = false)
    protected String password;


    public Compte() {
    }

    public Compte(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Compte(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


}
