package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import space.model.Planete;

public interface IDAOPlanete extends JpaRepository<Planete, Integer> {
}
