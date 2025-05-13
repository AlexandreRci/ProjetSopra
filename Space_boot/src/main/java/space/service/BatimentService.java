package space.service;

import org.springframework.stereotype.Service;
import space.dao.IDAOBatiment;
import space.model.Batiment;

import java.util.List;

@Service
public class BatimentService implements IService<Batiment, Integer> {

    IDAOBatiment daoBatiment;

    public BatimentService(IDAOBatiment daoBatiment) {
        this.daoBatiment = daoBatiment;
    }

    public Batiment getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une batiment sans id ?!");
        }
        return daoBatiment.findById(id).orElse(null);
    }

    public List<Batiment> getAll() {
        return daoBatiment.findAll();
    }

    public Batiment create(Batiment batiment) {
        return daoBatiment.save(batiment);
    }

    public Batiment update(Batiment batiment) {
        return daoBatiment.save(batiment);
    }

    public void deleteById(Integer id) {
        daoBatiment.deleteById(id);
    }

    public void delete(Batiment batiment) {
        daoBatiment.delete(batiment);
    }

    public boolean existsById(Integer id) {
        return daoBatiment.existsById(id);
    }
}
