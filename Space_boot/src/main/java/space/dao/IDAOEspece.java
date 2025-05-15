package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import space.model.Espece;

public interface IDAOEspece extends JpaRepository<Espece, Integer> {
}
