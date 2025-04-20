package space.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.dao.IDAOPlanetSeed;
import space.model.PlanetSeed;

import java.util.List;

@Service
public class PlanetSeedService implements IService<PlanetSeed, Integer> {
    @Autowired
    IDAOPlanetSeed daoPlanetSeed;

    public PlanetSeed getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une planetSeed sans id ?!");
        }
        return daoPlanetSeed.findById(id).orElse(null);
    }

    public List<PlanetSeed> getAll() {
        return daoPlanetSeed.findAll();
    }

    public PlanetSeed create(PlanetSeed planeteSeed) {
        return daoPlanetSeed.save(planeteSeed);
    }

    public PlanetSeed update(PlanetSeed planeteSeed) {
        return daoPlanetSeed.save(planeteSeed);
    }

    public void deleteById(Integer id) {
        daoPlanetSeed.deleteById(id);
    }

    public void delete(PlanetSeed planeteSeed) {
        daoPlanetSeed.delete(planeteSeed);
    }

    public boolean existsById(Integer id) {
        return daoPlanetSeed.existsById(id);
    }
}
