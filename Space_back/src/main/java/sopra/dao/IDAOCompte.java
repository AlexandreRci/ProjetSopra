package sopra.dao;

import sopra.model.Compte;

public interface IDAOCompte extends IDAO<Compte,Integer> {
    /**
     * Should be modified to hash password and use the hash to compare in the bdd.
     * @param username username
     * @param password  password in clear
     * @return Compte class if credentials present  in bdd esle null
     */
    Compte findByLoginAndPassword(String username, String password);
}
