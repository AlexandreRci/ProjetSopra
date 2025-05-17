package space.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import space.dao.IDAOBatiment;
import space.model.Batiment;
import space.model.Ressource;
import space.model.Taille;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BatimentServiceTest {

    @Autowired
    IDAOBatiment idaoBatiment;

    @Autowired
    BatimentService batimentService;


    @Test
    void getById() throws Exception {
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        Integer id = idaoBatiment.save(batiment1).getId();

        Batiment batimentDB = batimentService.getById(id);

        assertNotNull(batimentDB.getId());
        assertNull(batimentService.getById(9999));
        assertThrows(Exception.class, () -> batimentService.getById(null));
        assertEquals("Caserne", batimentDB.getNom());
        assertEquals(Taille.PETIT, batimentDB.getTaille());
        assertEquals(Ressource.ARME, batimentDB.getRessource());
    }

    @Test
    void getAll() {
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        Batiment batiment2 = new Batiment("Ferme", Taille.MOYEN, Ressource.NOURRITURE);
        idaoBatiment.save(batiment1);
        idaoBatiment.save(batiment2);

        List<Batiment> batiments = batimentService.getAll();

        assertEquals(2, batiments.size());
    }

    @Test
    void create() {
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);

        Integer id = batimentService.create(batiment1).getId();
        Batiment batimentDB = idaoBatiment.findById(id).orElse(null);

        assertNotNull(batimentDB);
        assertEquals(batiment1, batimentDB);
    }

    @Test
    void update() {
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        batiment1 = idaoBatiment.save(batiment1);
        batiment1.setNom("Ferme");
        batiment1.setTaille(Taille.MOYEN);
        batiment1.setRessource(Ressource.NOURRITURE);

        Batiment batimentDB = batimentService.update(batiment1);

        assertEquals(batiment1, batimentDB);
    }

    @Test
    void deleteById() {
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        Integer id = batimentService.create(batiment1).getId();

        batimentService.deleteById(id);

        assertNull(idaoBatiment.findById(id).orElse(null));
    }

    @Test
    void delete() {
        Batiment batiment = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        batiment = batimentService.create(batiment);

        batimentService.delete(batiment);

        assertNull(idaoBatiment.findById(batiment.getId()).orElse(null));
    }

    @Test
    void existsById() {
        Batiment batiment = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        int id = idaoBatiment.save(batiment).getId();

        boolean exist = batimentService.existsById(id);
        boolean dontExist = batimentService.existsById(9999);

        assertTrue(exist);
        assertFalse(dontExist);
    }
}