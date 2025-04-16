package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import space.model.Partie;

public interface IDAOPartie extends JpaRepository<Partie, Integer> {
}
