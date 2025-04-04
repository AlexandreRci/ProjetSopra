package sopra.context;

import sopra.service.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Singleton {
    private static Singleton instance = null;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");

    private final CompteService compteSrv = new CompteService();
    private final JoueurService joueurSrv = new JoueurService();
    private final PartieService partieSrv = new PartieService();
    private final EspeceService especeSrv = new EspeceService();
    private final PlaneteService planeteSrv = new PlaneteService();
    private final PlanetSeedService planetSeedSrv = new PlanetSeedService();
    private final PossessionService possessionSrv = new PossessionService();
    private final BatimentService batimentSrv = new BatimentService();


    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    /**
     * Should only be used when exiting the application.
     */
    public void closeEmf() {
        this.emf.close();
    }

    public CompteService getCompteSrv() {
        return compteSrv;
    }

    public JoueurService getJoueurSrv() {
        return joueurSrv;
    }

    public PartieService getPartieSrv() {
        return partieSrv;
    }

    public EspeceService getEspeceSrv() {
        return especeSrv;
    }

    public PlaneteService getPlaneteSrv() {
        return planeteSrv;
    }

    public PlanetSeedService getPlanetSeedSrv() {
        return planetSeedSrv;
    }

    public PossessionService getPossessionSrv() {
        return possessionSrv;
    }

    public BatimentService getBatimentSrv() {
        return batimentSrv;
    }
}
