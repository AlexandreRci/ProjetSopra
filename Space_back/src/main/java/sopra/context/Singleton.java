package sopra.context;

import sopra.service.CompteService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Singleton {
    private static Singleton instance = null;
    private CompteService compteSrv = new CompteService();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");

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
}
