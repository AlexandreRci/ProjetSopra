package sopra.dao.jpa;

import sopra.dao.IDAO;
import sopra.model.Partie;

import java.util.List;

public class DAOPartie implements IDAO<Partie, Integer> {
    @Override
    public List<Partie> findAll() {
        return findAll(Partie.class);
    }

    @Override
    public Partie findById(Integer id) {
        return findById(id, Partie.class);
    }

    @Override
    public void delete(Integer id) {
        delete(id, Partie.class);
    }
}
