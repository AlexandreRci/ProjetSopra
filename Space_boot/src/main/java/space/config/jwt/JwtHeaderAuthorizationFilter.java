package space.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import space.model.Compte;
import space.service.CompteService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JwtHeaderAuthorizationFilter extends OncePerRequestFilter {

    private final CompteService compteService;

    public JwtHeaderAuthorizationFilter(CompteService compteService) {
        this.compteService = compteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // On retire "Bearer " qui fait 7 caractères
            token = authHeader.substring(7);
        }

        Optional<String> optUsername = JwtUtil.getUsername(token);

        // Si on a le nom d'utilisateur, le jeton est valide
        if (optUsername.isPresent()) {
            String username = optUsername.get();
            Optional<Compte> optionalCompte = this.compteService.findByUsername(username);

            // Si on a l'utilisateur, on peut l'authentifier
            if (optionalCompte.isPresent()) {
                Compte compte = optionalCompte.get();

                // Simuler la connexion grâce au jeton

                // Créer la liste des rôles
                List<GrantedAuthority> authorities = new ArrayList<>();

                // Le préfix "ROLE_" permet d'indiquer à SPRING SECURITY qu'il s'agit d'un rôle
                if (compte instanceof space.model.Admin) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                } else if (compte instanceof space.model.Utilisateur) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_UTILISATEUR"));
                }

                // Recréer un nouvel Authentication
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

                // Injecter cet Authentication dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Important, pour passer à la suite
        filterChain.doFilter(request, response);
    }
}
