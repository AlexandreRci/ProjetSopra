package sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sopra.model.Partie;

public interface IDAOPartie extends JpaRepository<Partie, Integer> {
}
