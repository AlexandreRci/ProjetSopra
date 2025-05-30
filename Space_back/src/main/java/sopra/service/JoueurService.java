package sopra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sopra.dao.IDAOJoueur;
import sopra.model.Joueur;

import java.util.List;

@Service
public class JoueurService implements IService<Joueur, Integer> {
    @Autowired
    IDAOJoueur daoJoueur;

    public Joueur getById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Impossible de chercher un joueur sans id ?!");
        }
        return daoJoueur.findById(id).orElse(null);
    }

    public List<Joueur> getAll() {
        return daoJoueur.findAll();
    }

    public Joueur create(Joueur joueur) {
        return daoJoueur.save(joueur);
    }

    public Joueur update(Joueur joueur) {
        return daoJoueur.save(joueur);

    }

    public void deleteById(Integer id) {
        daoJoueur.deleteById(id);
    }

    public void delete(Joueur joueur){
        daoJoueur.delete(joueur);
    }
}
