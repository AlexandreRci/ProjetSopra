package sopra.service;

import sopra.dao.jpa.DAOBatiment;
import sopra.model.Batiment;

import java.util.List;

public class BatimentService implements IService<Batiment,Integer>{
    final DAOBatiment daoBatiment = new DAOBatiment();

    public Batiment getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une batiment sans id ?!");
        }
        return daoBatiment.findById(id);
    }

    public List<Batiment> getAll() {
        return daoBatiment.findAll();
    }

    public Batiment create(Batiment batiment) {
        batiment = daoBatiment.save(batiment);
        return batiment;
    }

    public Batiment update(Batiment batiment) {
        batiment = daoBatiment.save(batiment);
        return batiment;
    }

    public boolean deleteById(Integer id) {
        daoBatiment.delete(id);
        return true;
    }

    public boolean delete(Batiment batiment) throws Exception {
        if (batiment.getId() == null) {
            throw new Exception("Impossible de supprimer une batiment qui n'a pas d'id");
        }
        deleteById(batiment.getId());
        return true;
    }
}
