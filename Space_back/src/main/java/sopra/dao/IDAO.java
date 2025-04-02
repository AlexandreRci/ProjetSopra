package sopra.dao;

import sopra.context.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public interface IDAO<T, K> {
    List<T> findAll();

    T findById(K id);

    void delete(Integer id);

    default List<T> findAll(Class<T> entityClass) {
        List<T> objects = new ArrayList<>();
        EntityManager em = null;
        try {
            em = Singleton.getInstance().getEmf().createEntityManager();

            Query query = em.createQuery("FROM :tableName").setParameter("tableName", entityClass.getSimpleName());
            objects = (List<T>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return objects;
    }

    default T findById(K id, Class<T> entityClass) {
        T object = null;
        EntityManager em = null;
        try {
            em = Singleton.getInstance().getEmf().createEntityManager();
            object = em.find(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return object;
    }

    default T save(T object) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = Singleton.getInstance().getEmf().createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            object = em.merge(object);

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

        return object;
    }

    default void delete(Integer id, Class<T> entityClass) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = Singleton.getInstance().getEmf().createEntityManager();
            T object = em.find(entityClass, id);
            tx = em.getTransaction();
            tx.begin();

            em.remove(object);

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
