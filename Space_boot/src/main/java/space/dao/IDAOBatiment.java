package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import space.model.Batiment;

public interface IDAOBatiment extends JpaRepository<Batiment, Integer> {
}
