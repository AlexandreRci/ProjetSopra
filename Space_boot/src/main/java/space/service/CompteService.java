package space.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import space.dao.IDAOCompte;
import space.model.Admin;
import space.model.Compte;
import space.model.Utilisateur;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService implements IService<Compte, Integer>, UserDetailsService {
    private final IDAOCompte daoCompte;
    private PasswordEncoder passwordEncoder;

    public CompteService(IDAOCompte daoCompte, PasswordEncoder passwordEncoder) {
        this.daoCompte = daoCompte;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Compte compte = this.daoCompte.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas."));

        // Si l'utilisateur n'a pas été trouvé, l'exception sera jetée, et on s'arrêtera là
        // Si mot de passe hashés en base, utiliser ça :
        User.UserBuilder userBuilder = User.withUsername(username).password(compte.getPassword());

        switch (compte) {
            case Admin admin -> userBuilder.roles("ADMIN");
            case Utilisateur utilisateur -> userBuilder.roles("UTILISATEUR");
            default -> {
                throw new UsernameNotFoundException("L'utilisateur n'existe pas.");
            }
        }

        return userBuilder.build();
    }

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

    public Optional<Compte> findByUsername(String username) {
        return daoCompte.findByUsername(username);
    }
}
