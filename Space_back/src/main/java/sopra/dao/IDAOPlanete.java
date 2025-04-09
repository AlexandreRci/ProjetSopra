package sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sopra.model.Planete;

public interface IDAOPlanete extends JpaRepository<Planete, Integer> {
}
