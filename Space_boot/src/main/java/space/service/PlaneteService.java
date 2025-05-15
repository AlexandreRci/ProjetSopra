package space.service;

import org.springframework.stereotype.Service;
import space.dao.IDAOPlanete;
import space.model.Planete;

import java.util.List;

@Service
public class PlaneteService implements IService<Planete, Integer> {
    private final IDAOPlanete daoPlanete;

    public PlaneteService(IDAOPlanete daoPlanete) {
        this.daoPlanete = daoPlanete;
    }

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

    public void delete(Planete planete) {
        daoPlanete.delete(planete);
    }

    public boolean existsById(Integer id) {
        return daoPlanete.existsById(id);
    }
}
