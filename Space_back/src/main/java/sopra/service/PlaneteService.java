package sopra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sopra.dao.IDAOPlanete;
import sopra.model.Planete;

import java.util.List;

@Service
public class PlaneteService implements IService<Planete, Integer> {
    @Autowired
    IDAOPlanete daoPlanete;

    public Planete getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une planete sans id ?!");
        }
        return daoPlanete.findById(id).orElse(null);
    }

    public List<Planete> getAll() {
        return daoPlanete.findAll();
    }

    public Planete create(Planete planete) {
        return daoPlanete.save(planete);
    }

    public Planete update(Planete planete) {
        return daoPlanete.save(planete);
    }

    public void deleteById(Integer id) {
        daoPlanete.deleteById(id);
    }

    public void delete(Planete planete){
        daoPlanete.delete(planete);
    }
}
