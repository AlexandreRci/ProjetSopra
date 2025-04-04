package sopra.dao.jpa;

import sopra.dao.IDAO;
import sopra.model.Planete;

import java.util.List;

public class DAOPlanete implements IDAO<Planete, Integer> {
    @Override
    public List<Planete> findAll() {
        return findAll(Planete.class);
    }

    @Override
    public Planete findById(Integer id) {
        return findById(id, Planete.class);
    }

    @Override
    public void delete(Integer id) {
        delete(id, Planete.class);
    }
}
