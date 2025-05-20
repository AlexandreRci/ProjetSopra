package space.rest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space.config.jwt.JwtUtil;
import space.model.Compte;
import space.rest.request.ConnexionRequest;
import space.rest.response.ConnexionResponse;
import space.service.CompteService;

@RestController
public class CommonRestController {
    private final AuthenticationManager authenticationManager;
    private final CompteService compteService;

    public CommonRestController(AuthenticationManager authenticationManager, CompteService compteService) {
        this.authenticationManager = authenticationManager;
        this.compteService = compteService;
    }

    @PostMapping("/connexion")
    public ConnexionResponse create(@RequestBody ConnexionRequest connexionRequest) {
        // Authentification correcte avec récupération du vrai Authentication enrichi
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        connexionRequest.getUsername(),
                        connexionRequest.getPassword()));

        // ✅ Utilise l'objet Authentication retourné (avec les rôles)
        String token = JwtUtil.generate(authentication);

        Compte compte = compteService.findByUsername(connexionRequest.getUsername()).orElseThrow(
                () -> new RuntimeException("Utilisateur non trouvé")
        );

        ConnexionResponse connexionResponse = new ConnexionResponse();
        connexionResponse.setSuccess(true);
        connexionResponse.setToken(token);
        connexionResponse.setId(compte.getId());
        return connexionResponse;
    }
}
