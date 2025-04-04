package sopra.dao.jpa;

import sopra.dao.IDAO;
import sopra.model.Espece;

import java.util.List;

public class DAOEspece implements IDAO<Espece, Integer> {
    @Override
    public List<Espece> findAll() {
        return findAll(Espece.class);
    }

    @Override
    public Espece findById(Integer id) {
        return findById(id, Espece.class);
    }

    @Override
    public void delete(Integer id) {
        delete(id, Espece.class);
    }
}
