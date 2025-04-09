package sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sopra.model.Batiment;

public interface IDAOBatiment extends JpaRepository<Batiment, Integer> {
}
