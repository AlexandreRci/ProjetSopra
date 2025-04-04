package sopra.service;

import sopra.dao.jpa.DAOJoueur;
import sopra.model.Joueur;

import java.util.List;

public class JoueurService implements IService<Joueur, Integer>{
    final DAOJoueur daoJoueur = new DAOJoueur();

    public Joueur getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher un joueur sans id ?!");
        }
        return daoJoueur.findById(id);
    }

    public List<Joueur> getAll() {
        return daoJoueur.findAll();
    }

    public Joueur create(Joueur joueur) {
        joueur = daoJoueur.save(joueur);
        return joueur;
    }

    public Joueur update(Joueur joueur) {
        joueur = daoJoueur.save(joueur);
        return joueur;
    }

    public boolean deleteById(Integer id) {
        daoJoueur.delete(id);
        return true;
    }

    public boolean delete(Joueur joueur) throws Exception {
        if (joueur.getId() == null) {
            throw new Exception("Impossible de supprimer un joueur qui n'a pas d'id");
        }
        deleteById(joueur.getId());
        return true;
    }
}
