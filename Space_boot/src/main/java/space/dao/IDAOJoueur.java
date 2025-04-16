package space.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import space.model.Joueur;

public interface IDAOJoueur extends JpaRepository<Joueur, Integer> {
}
