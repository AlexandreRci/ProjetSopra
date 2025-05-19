package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import space.model.Partie;

import java.util.List;

public interface IDAOPartie extends JpaRepository<Partie, Integer> {


    @Query("SELECT DISTINCT p FROM Partie p JOIN p.joueurs j JOIN j.utilisateur u WHERE u.id = :utilisateur_id")
    List<Partie> findByUtilisateur(@Param("utilisateur_id") Integer id);
}

