package space.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import space.model.Compte;
import space.model.Utilisateur;
import space.rest.request.ConnexionRequest;
import space.service.CompteService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CommonRestControllerTest {

    @Autowired
    CompteService compteService;
    @Autowired
    PasswordEncoder passwordEncoder;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext applicationContext;


    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    void createSucess() throws Exception {
        Compte compte = new Utilisateur("test", passwordEncoder.encode("password"), "test");
        compteService.create(compte);


        ConnexionRequest connexionRequest = new ConnexionRequest();
        connexionRequest.setUsername("test");
        connexionRequest.setPassword("password");

        String jsonRequest = objectMapper.writeValueAsString(connexionRequest);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").isNotEmpty());
    }

    @Test
    void createFailure() throws Exception {
        ConnexionRequest connexionRequest = new ConnexionRequest();
        connexionRequest.setUsername("test");
        connexionRequest.setPassword("password");

        String jsonRequest = objectMapper.writeValueAsString(connexionRequest);

        assertThrows(Exception.class, () -> {
            this.mockMvc.perform(MockMvcRequestBuilders.post("/connexion")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest));
        });
    }
}