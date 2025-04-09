package sopra.dao.jpa;

import sopra.dao.IDAO;
import sopra.model.Batiment;

import java.util.List;

public class DAOBatiment implements IDAO<Batiment, Integer> {
    @Override
    public List<Batiment> findAll() {
        return findAll(Batiment.class);
    }

    @Override
    public Batiment findById(Integer id) {
        return findById(id, Batiment.class);
    }

    @Override
    public void delete(Integer id) {
        delete(id, Batiment.class);
    }
}
