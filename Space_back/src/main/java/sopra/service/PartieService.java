package sopra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sopra.dao.IDAOPartie;
import sopra.model.Partie;

import java.util.List;

@Service
public class PartieService implements IService<Partie, Integer> {
    @Autowired
    IDAOPartie daoPartie;

    public Partie getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher une partie sans id ?!");
        }
        return daoPartie.findById(id).orElse(null);
    }

    public List<Partie> getAll() {
        return daoPartie.findAll();
    }

    public Partie create(Partie partie) {
        return daoPartie.save(partie);
    }

    public Partie update(Partie partie) {
        return daoPartie.save(partie);
    }

    public void deleteById(Integer id) {
        daoPartie.deleteById(id);
    }

    public void delete(Partie partie){
        daoPartie.delete(partie);
    }
}
