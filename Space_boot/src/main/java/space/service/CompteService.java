package space.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import space.dao.IDAOCompte;
import space.model.Compte;

@Service
public class CompteService implements IService<Compte, Integer> {
    @Autowired
    IDAOCompte daoCompte;

    public Compte getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher un compte sans id ?!");
        }
        return daoCompte.findById(id).orElse(null);
    }

    public Compte getByUsernameAndPassword(String username, String password) {
        return daoCompte.findByUsernameAndPassword(username, password);
    }

    public List<Compte> getAll() {
        return daoCompte.findAll();
    }

    public Compte create(Compte compte) {
        return daoCompte.save(compte);
    }

    public Compte update(Compte compte) {
        return daoCompte.save(compte);
    }

    public void deleteById(Integer id) {
        daoCompte.deleteById(id);
    }

    public void delete(Compte compte) {
        daoCompte.delete(compte);
    }
    
    public boolean existsById(Integer id) {
    	return daoCompte.existsById(id);
    }
}
