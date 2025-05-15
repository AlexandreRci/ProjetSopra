package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import space.model.Possession;

public interface IDAOPossession extends JpaRepository<Possession, Integer> {
}
