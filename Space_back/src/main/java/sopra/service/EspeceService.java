package sopra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sopra.dao.IDAOEspece;
import sopra.model.Espece;

import java.util.List;

@Service
public class EspeceService implements IService<Espece, Integer> {
    @Autowired
    IDAOEspece daoEspece;

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

    public void delete(Espece espece){
        daoEspece.delete(espece);
    }
}
