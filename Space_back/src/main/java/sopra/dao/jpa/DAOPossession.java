package sopra.dao.jpa;

import sopra.dao.IDAO;
import sopra.model.Possession;

import java.util.List;

public class DAOPossession implements IDAO<Possession, Integer> {
    @Override
    public List<Possession> findAll() {
        return findAll(Possession.class);
    }

    @Override
    public Possession findById(Integer id) {
        return findById(id, Possession.class);
    }

    @Override
    public void delete(Integer id) {
        delete(id, Possession.class);
    }
}
