package space.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.dao.IDAOPossession;
import space.model.Possession;

import java.util.List;

@Service
public class PossessionService implements IService<Possession, Integer> {
    @Autowired
    IDAOPossession daoPossession;

    public Possession getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une possession sans id ?!");
        }
        return daoPossession.findById(id).orElse(null);
    }

    public List<Possession> getAll() {
        return daoPossession.findAll();
    }

    public Possession create(Possession possession) {
        return daoPossession.save(possession);
    }

    public Possession update(Possession possession) {
        return daoPossession.save(possession);
    }

    public void deleteById(Integer id) {
        daoPossession.deleteById(id);
    }

    public void delete(Possession possession) {
        daoPossession.delete(possession);
    }

    public boolean existsById(Integer id) {
        return daoPossession.existsById(id);
    }
}
