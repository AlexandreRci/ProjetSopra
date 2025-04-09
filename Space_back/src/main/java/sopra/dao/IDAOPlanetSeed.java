package sopra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sopra.model.PlanetSeed;

public interface IDAOPlanetSeed extends JpaRepository<PlanetSeed, Integer> {
}
