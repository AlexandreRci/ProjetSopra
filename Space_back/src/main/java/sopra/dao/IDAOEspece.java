package sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sopra.model.Espece;

public interface IDAOEspece extends JpaRepository<Espece, Integer> {
}
