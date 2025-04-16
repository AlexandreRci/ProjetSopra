package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import space.model.Compte;

public interface IDAOCompte extends JpaRepository<Compte, Integer> {
    /**
     * Should be modified to hash password and use the hash to compare in the bdd.
     *
     * @param username username
     * @param password password in clear
     * @return Compte class if credentials present in bdd else null
     */
    @Query("SELECT c from Compte c where c.username=:username and c.password=:password")
    Compte findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
