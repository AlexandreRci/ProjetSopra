package space.service;

import org.springframework.stereotype.Service;
import space.dao.IDAOEspece;
import space.model.Espece;

import java.util.List;

@Service
public class EspeceService implements IService<Espece, Integer> {
    private final IDAOEspece daoEspece;

    public EspeceService(IDAOEspece daoEspece) {
        this.daoEspece = daoEspece;
    }

    public Espece getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une espece sans id ?!");
        }
        return daoEspece.findById(id).orElse(null);
    }

    public List<Espece> getAll() {
        return daoEspece.findAll();
    }

    public Espece create(Espece espece) {
        return daoEspece.save(espece);
    }

    public Espece update(Espece espece) {
        return daoEspece.save(espece);
    }

    public void deleteById(Integer id) {
        daoEspece.deleteById(id);
    }

    public void delete(Espece espece) {
        daoEspece.delete(espece);
    }

    public boolean existsById(Integer id) {
        return daoEspece.existsById(id);
    }
}
