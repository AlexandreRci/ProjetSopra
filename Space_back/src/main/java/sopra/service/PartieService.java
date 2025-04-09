package sopra.service;

import sopra.dao.jpa.DAOPartie;
import sopra.model.Partie;

import java.util.List;

public class PartieService implements IService<Partie, Integer>{
    final DAOPartie daoPartie = new DAOPartie();

    public Partie getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une partie sans id ?!");
        }
        return daoPartie.findById(id);
    }

    public List<Partie> getAll() {
        return daoPartie.findAll();
    }

    public Partie create(Partie partie) {
        partie = daoPartie.save(partie);
        return partie;
    }

    public Partie update(Partie partie) {
        partie = daoPartie.save(partie);
        return partie;
    }

    public boolean deleteById(Integer id) {
        daoPartie.delete(id);
        return true;
    }

    public boolean delete(Partie partie) throws Exception {
        if (partie.getId() == null) {
            throw new Exception("Impossible de supprimer une partie qui n'a pas d'id");
        }
        deleteById(partie.getId());
        return true;
    }
}
