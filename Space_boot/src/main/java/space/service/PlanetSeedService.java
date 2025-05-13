package space.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.dao.IDAOPlanetSeed;
import space.model.Batiment;
import space.model.PlanetSeed;

import java.util.List;

@Service
@Transactional
public class PlanetSeedService implements IService<PlanetSeed, Integer> {
    private final IDAOPlanetSeed daoPlanetSeed;
    private final BatimentService batimentService;

    public PlanetSeedService(IDAOPlanetSeed daoPlanetSeed, BatimentService batimentService) {
        this.daoPlanetSeed = daoPlanetSeed;
        this.batimentService = batimentService;
    }

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
        try {
            delete(getById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(PlanetSeed planeteSeed) {
        for (Batiment batiment : planeteSeed.getBatiments()) {
            batimentService.delete(batiment);
        }
        daoPlanetSeed.delete(planeteSeed);
    }

    public boolean existsById(Integer id) {
        return daoPlanetSeed.existsById(id);
    }
}
