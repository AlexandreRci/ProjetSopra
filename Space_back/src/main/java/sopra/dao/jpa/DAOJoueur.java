package sopra.dao.jpa;


import sopra.dao.IDAO;
import sopra.model.Joueur;

import java.util.List;

public class DAOJoueur implements IDAO<Joueur, Integer> {

    @Override
    public List<Joueur> findAll() {
        return findAll(Joueur.class);
    }

    @Override
    public Joueur findById(Integer id) {
        return findById(id, Joueur.class);
    }

    @Override
    public void delete(Integer id) {
        delete(id, Joueur.class);
    }
}
