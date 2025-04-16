package space.service;

import java.util.List;

public interface IService<T, K> {
    T getById(K id) throws Exception;

    List<T> getAll();

    T create(T object);

    T update(T object);

    void deleteById(K id);

    void delete(T object);
}
