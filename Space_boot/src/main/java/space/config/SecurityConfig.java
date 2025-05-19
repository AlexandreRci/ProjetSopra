package space.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import space.config.jwt.JwtHeaderAuthorizationFilter;

import java.util.List;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http, JwtHeaderAuthorizationFilter jwtFilter) throws Exception {
        // Méthode d'authentification par HTTP Basic
        http.httpBasic(Customizer.withDefaults());

        // Autorisations sur URLs
        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers("/connexion", "/compte", "/compte/**").permitAll() // Autorisé sans token
                    .requestMatchers("/api/admin/**").hasRole("ADMIN") // 👈 REST API des admins
                    .anyRequest().authenticated(); // Tout le reste nécessite un token JWT valide
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

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // Grace à ce Bean, on pourra injecter un AuthenticationManager directement
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
