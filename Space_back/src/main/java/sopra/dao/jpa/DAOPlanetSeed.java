package sopra.dao.jpa;

import sopra.dao.IDAO;
import sopra.model.PlanetSeed;

import java.util.List;

public class DAOPlanetSeed implements IDAO<PlanetSeed, Integer> {
    @Override
    public List<PlanetSeed> findAll() {
        return findAll(PlanetSeed.class);
    }

    @Override
    public PlanetSeed findById(Integer id) {
        return findById(id, PlanetSeed.class);
    }

    @Override
    public void delete(Integer id) {
        delete(id, PlanetSeed.class);
    }
}
