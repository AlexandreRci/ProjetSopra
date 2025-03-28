package sopra.dao;

import java.util.List;

public interface IDAO<T, K> {
    List<T> findAll();

    T findById(K id);

    T save(T obj);

    void delete(K id);
}
