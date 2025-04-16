package space.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import space.dao.IDAOBatiment;
import space.model.Batiment;

@Service
public class BatimentService implements IService<Batiment, Integer> {
    @Autowired
    IDAOBatiment daoBatiment;

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

    public void delete(Batiment batiment){
        daoBatiment.delete(batiment);
    }
    
    public boolean existsById(Integer id) {
    	return daoBatiment.existsById(id);
    }
}
