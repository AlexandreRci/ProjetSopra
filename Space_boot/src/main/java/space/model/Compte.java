package space.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seqC", sequenceName = "seq_compte")
public abstract class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqC")
    @Column(name = "id_compte")
    protected Integer id;
    @Column(nullable = false, unique = true)
    protected String username;
    @Column(nullable = false)
    protected String password;


    protected Compte() {
    }

    protected Compte(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    protected Compte(String username, String password) {
        this.username = username;
        this.password = password;
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


}
