package sopra.service;

import sopra.dao.jpa.DAOPossession;
import sopra.model.Possession;

import java.util.List;

public class PossessionService {
    DAOPossession daoPossession = new DAOPossession();

    public Possession getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une possession sans id ?!");
        }
        return daoPossession.findById(id);
    }

    public List<Possession> getAll() {
        return daoPossession.findAll();
    }

    public Possession create(Possession possession) {
        possession = daoPossession.save(possession);
        return possession;
    }

    public Possession update(Possession possession) {
        possession = daoPossession.save(possession);
        return possession;
    }

    public boolean deleteById(Integer id) {
        daoPossession.delete(id);
        return true;
    }

    public boolean delete(Possession possession) throws Exception {
        if (possession.getId() == null) {
            throw new Exception("Impossible de supprimer une possession qui n'a pas d'id");
        }
        deleteById(possession.getId());
        return true;
    }
}
