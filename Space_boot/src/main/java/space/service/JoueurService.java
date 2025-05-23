package space.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import space.dao.IDAOJoueur;
import space.model.Joueur;
import space.model.PlanetSeed;
import space.model.Possession;

import java.util.List;

@Service
@Transactional
public class JoueurService implements IService<Joueur, Integer> {
    private final IDAOJoueur daoJoueur;
    private final PlanetSeedService planetSeedService;
    private final PossessionService possessionService;

    public JoueurService(IDAOJoueur daoJoueur,
                         PlanetSeedService planetSeedService,
                         PossessionService possessionService
    ) {
        this.daoJoueur = daoJoueur;
        this.planetSeedService = planetSeedService;
        this.possessionService = possessionService;
    }

    public Joueur getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher un joueur sans id ?!");
        }
        return daoJoueur.findById(id).orElse(null);
    }

    public List<Joueur> getAll() {
        return daoJoueur.findAll();
    }

    public Joueur create(Joueur joueur) {
        return daoJoueur.save(joueur);
    }

    public Joueur update(Joueur joueur) {
        return daoJoueur.save(joueur);
    }

    public void deleteById(Integer id) {
        try {
            delete(getById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Do not delete planetSeeds list
     *
     * @param joueur
     */
    public void delete(Joueur joueur) {
        if (joueur.getPlanetSeeds() != null) {
            for (PlanetSeed planetSeed : joueur.getPlanetSeeds()) {
                planetSeed.setJoueur(null);
                planetSeedService.update(planetSeed);
            }
        }
        if (joueur.getPossessions() != null) {
            for (Possession possession : joueur.getPossessions()) {
                possessionService.delete(possession);
            }
        }
        daoJoueur.delete(joueur);
    }

    public boolean existsById(Integer id) {
        return daoJoueur.existsById(id);
    }
}
