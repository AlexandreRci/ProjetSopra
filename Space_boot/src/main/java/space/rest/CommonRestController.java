package space.rest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space.config.jwt.JwtUtil;
import space.dao.IDAOCompte;
import space.model.Compte;
import space.rest.request.ConnexionRequest;
import space.rest.response.ConnexionResponse;

@RestController
public class CommonRestController {
    private final AuthenticationManager authenticationManager;
    private final IDAOCompte daoCompte;

    public CommonRestController(AuthenticationManager authenticationManager, IDAOCompte daoCompte) {
        this.authenticationManager = authenticationManager;
        this.daoCompte = daoCompte;
    }

    @PostMapping("/connexion")
    public ConnexionResponse create(@RequestBody ConnexionRequest connexionRequest) {
        // Authentification correcte avec récupération du vrai Authentication enrichi
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        connexionRequest.getUsername(),
                        connexionRequest.getPassword()));

        Compte compte = daoCompte.findByUsername(connexionRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        // ✅ Utilise l'objet Authentication retourné (avec les rôles)
        String token = JwtUtil.generate(authentication, compte.getId());

        ConnexionResponse connexionResponse = new ConnexionResponse();
        connexionResponse.setSuccess(true);
        connexionResponse.setToken(token);
        connexionResponse.setId(compte.getId());
        return connexionResponse;
    }
}
