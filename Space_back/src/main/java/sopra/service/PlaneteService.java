package sopra.service;

import sopra.dao.jpa.DAOPlanete;
import sopra.model.Planete;

import java.util.List;

public class PlaneteService implements IService<Planete,Integer>{
    final DAOPlanete daoPlanete = new DAOPlanete();

    public Planete getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une planete sans id ?!");
        }
        return daoPlanete.findById(id);
    }

    public List<Planete> getAll() {
        return daoPlanete.findAll();
    }

    public Planete create(Planete planete) {
        planete = daoPlanete.save(planete);
        return planete;
    }

    public Planete update(Planete planete) {
        planete = daoPlanete.save(planete);
        return planete;
    }

    public boolean deleteById(Integer id) {
        daoPlanete.delete(id);
        return true;
    }

    public boolean delete(Planete planete) throws Exception {
        if (planete.getId() == null) {
            throw new Exception("Impossible de supprimer une planete qui n'a pas d'id");
        }
        deleteById(planete.getId());
        return true;
    }
}
