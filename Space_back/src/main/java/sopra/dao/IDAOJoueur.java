package sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sopra.model.Joueur;

public interface IDAOJoueur extends JpaRepository<Joueur, Integer> {
}
