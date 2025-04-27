package space.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.List;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Méthode d'authentification par HTTP Basic
        http.httpBasic(Customizer.withDefaults());

        // Autorisations sur URLs
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/**").permitAll();
        });

        http.csrf(c -> c.ignoringRequestMatchers("/**"));

        // Configurer les CORS (Cross-Origine Resources Sharing)
        http.cors(c -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();

                // On autorise tout le monde
                config.setAllowedOrigins(List.of("*"));

                // On autorise toutes les commandes HTTP (GET, POST, PUT, ...)
                config.setAllowedMethods(List.of("*"));

                // On autorise toutes les en-têtes HTTP
                config.setAllowedHeaders(List.of("*"));

                return config;
            };

            c.configurationSource(source);
        });
        return http.build();
    }

    // Grace à ce Bean, on pourra injecter un AuthenticationManager directement
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
