package sopra.service;

import sopra.dao.jpa.DAOPlanetSeed;
import sopra.model.PlanetSeed;

import java.util.List;

public class PlanetSeedService implements IService<PlanetSeed,Integer>{
    final DAOPlanetSeed daoPlanetSeed = new DAOPlanetSeed();

    public PlanetSeed getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une planetSeed sans id ?!");
        }
        return daoPlanetSeed.findById(id);
    }

    public List<PlanetSeed> getAll() {
        return daoPlanetSeed.findAll();
    }

    public PlanetSeed create(PlanetSeed planeteSeed) {
        planeteSeed = daoPlanetSeed.save(planeteSeed);
        return planeteSeed;
    }

    public PlanetSeed update(PlanetSeed planeteSeed) {
        planeteSeed = daoPlanetSeed.save(planeteSeed);
        return planeteSeed;
    }

    public boolean deleteById(Integer id) {
        daoPlanetSeed.delete(id);
        return true;
    }

    public boolean delete(PlanetSeed planeteSeed) throws Exception {
        if (planeteSeed.getId() == null) {
            throw new Exception("Impossible de supprimer une planetSeed qui n'a pas d'id");
        }
        deleteById(planeteSeed.getId());
        return true;
    }
}
