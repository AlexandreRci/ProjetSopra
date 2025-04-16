package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import space.model.PlanetSeed;

public interface IDAOPlanetSeed extends JpaRepository<PlanetSeed, Integer> {
}
