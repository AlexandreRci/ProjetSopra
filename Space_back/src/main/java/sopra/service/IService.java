package sopra.service;

import java.util.List;

public interface IService<T, K> {
    T getById(K id) throws Exception;

    List<T> getAll();

    T create(T object);

    T update(T object);

    boolean deleteById(K id);

    boolean delete(T object) throws Exception;
}
