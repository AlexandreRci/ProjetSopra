package sopra.dao.jpa;

import sopra.context.Singleton;
import sopra.dao.IDAOCompte;
import sopra.model.Compte;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOCompte implements IDAOCompte {

    @Override
    public Compte findByLoginAndPassword(String username, String password) {
        Compte compte = null;
        EntityManager em = null;
        try {
            em = Singleton.getInstance().getEmf().createEntityManager();
            TypedQuery<Compte> query = em.createQuery("SELECT c from Compte c where c.username=:username and c.password=:password", Compte.class);
            compte = query.setParameter("username", username).setParameter("password", password).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return compte;
    }

    @Override
    public List<Compte> findAll() {
        List<Compte> comptes = new ArrayList<>();
        EntityManager em = null;
        try {
            em = Singleton.getInstance().getEmf().createEntityManager();
            comptes = em.createQuery("FROM Compte").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return comptes;
    }

    @Override
    public Compte findById(Integer id) {
        Compte compte = null;
        EntityManager em = null;
        try {
            em = Singleton.getInstance().getEmf().createEntityManager();
            compte = em.find(Compte.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return compte;
    }

    @Override
    public Compte save(Compte compte) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = Singleton.getInstance().getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            compte = em.merge(compte);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return compte;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = Singleton.getInstance().getEmf().createEntityManager();
            Compte compte = em.find(Compte.class, id);
            tx = em.getTransaction();
            tx.begin();

            em.remove(compte);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
