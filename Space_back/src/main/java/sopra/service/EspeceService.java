package sopra.service;

import sopra.dao.jpa.DAOEspece;
import sopra.model.Espece;

import java.util.List;

public class EspeceService {
    DAOEspece daoEspece = new DAOEspece();

    public Espece getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une espece sans id ?!");
        }
        return daoEspece.findById(id);
    }

    public List<Espece> getAll() {
        return daoEspece.findAll();
    }

    public Espece create(Espece espece) {
        espece = daoEspece.save(espece);
        return espece;
    }

    public Espece update(Espece espece) {
        espece = daoEspece.save(espece);
        return espece;
    }

    public boolean deleteById(Integer id) {
        daoEspece.delete(id);
        return true;
    }

    public boolean delete(Espece espece) throws Exception {
        if (espece.getId() == null) {
            throw new Exception("Impossible de supprimer une espece qui n'a pas d'id");
        }
        deleteById(espece.getId());
        return true;
    }
}
