package sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sopra.model.Possession;

public interface IDAOPossession extends JpaRepository<Possession, Integer> {
}
