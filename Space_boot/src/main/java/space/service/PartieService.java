package space.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import space.dao.IDAOPartie;
import space.model.Joueur;
import space.model.Partie;
import space.model.PlanetSeed;

import java.util.List;

@Service
@Transactional
public class PartieService implements IService<Partie, Integer> {
    private final IDAOPartie daoPartie;
    private final JoueurService joueurService;
    private final PlanetSeedService planetSeedService;

    public PartieService(IDAOPartie daoPartie, JoueurService joueurService, PlanetSeedService planetSeedService) {
        this.daoPartie = daoPartie;
        this.joueurService = joueurService;
        this.planetSeedService = planetSeedService;
    }

    public Partie getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une partie sans id ?!");
        }
        return daoPartie.findById(id).orElse(null);
    }

    public List<Partie> getAll() {
        return daoPartie.findAll();
    }

    public Partie create(Partie partie) {
        return daoPartie.save(partie);
    }

    public Partie update(Partie partie) {
        return daoPartie.save(partie);
    }

    public void deleteById(Integer id) {
        try {
            delete(getById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Partie partie) {
        for (Joueur joueur : partie.getJoueurs()) {
            joueurService.delete(joueur);
        }
        for (PlanetSeed planetSeed : partie.getPlanetSeeds()) {
            planetSeedService.delete(planetSeed);
        }
        daoPartie.delete(partie);
    }

    public boolean existsById(Integer id) {
        return daoPartie.existsById(id);
    }
}
