package space.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import space.model.Admin;
import space.model.Compte;
import space.model.Utilisateur;
import space.rest.request.CompteRequest;
import space.service.CompteService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CompteRestControllerTest {

    @Autowired
    private CompteService compteService;

    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    void getAll() throws Exception {
        Utilisateur utilisateur1 = new Utilisateur("login_judy", passwordEncoder.encode("password_judy"), "Judy");
        Utilisateur utilisateur2 = new Utilisateur("login_AlexL", passwordEncoder.encode("password_AlexL"), "AlexL");
        Admin admin1 = new Admin("login_AlexR", passwordEncoder.encode("password_AlexR"));
        Admin admin2 = new Admin("login_Yvan", passwordEncoder.encode("password_Yvan"));

        compteService.create(utilisateur1);
        compteService.create(utilisateur2);
        compteService.create(admin1);
        compteService.create(admin2);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/compte").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").exists());
    }

    @Test
    void getById() throws Exception {
        Utilisateur utilisateur1 = new Utilisateur("login_judy", passwordEncoder.encode("password_judy"), "Judy");
        Admin admin1 = new Admin("login_AlexR", passwordEncoder.encode("password_AlexR"));

        int id1 = compteService.create(utilisateur1).getId();
        int id2 = compteService.create(admin1).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/compte/" + id1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("login_judy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Judy"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/compte/" + id2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("login_AlexR"));
    }

    @Test
    void create() throws Exception {
        CompteRequest compteRequest = new CompteRequest();
        compteRequest.setUsername("login_judy");
        compteRequest.setPassword("password_judy");
        compteRequest.setName("Judy");
        compteRequest.setCompteType(CompteRequest.CompteType.UTILISATEUR);

        String jsonRequest = objectMapper.writeValueAsString(compteRequest);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/compte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Judy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("login_judy"))
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        int id = jsonNode.get("id").asInt();
        assertTrue(compteService.existsById(id));
        Compte compte = compteService.getById(id);
        assertEquals("login_judy", compte.getUsername());
        assertInstanceOf(Utilisateur.class, compte);

    }

    @Test
    void updateUser() throws Exception {
        Utilisateur utilisateur1 = new Utilisateur("login_judy", passwordEncoder.encode("password_judy"), "Judy");
        int id = compteService.create(utilisateur1).getId();

        CompteRequest compteRequest = new CompteRequest();
        compteRequest.setId(id);
        compteRequest.setUsername("username");
        compteRequest.setPassword("password");
        compteRequest.setName("Name");
        compteRequest.setCompteType(CompteRequest.CompteType.UTILISATEUR);

        String jsonRequest = objectMapper.writeValueAsString(compteRequest);


        this.mockMvc.perform(MockMvcRequestBuilders.put("/compte/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username"));

        Utilisateur utilisateur = (Utilisateur) compteService.getById(id);
        assertEquals("Name", utilisateur.getName());
        assertEquals("username", utilisateur.getUsername());

    }

    @Test
    void delete() throws Exception {
        Utilisateur utilisateur1 = new Utilisateur("login_judy", passwordEncoder.encode("password_judy"), "Judy");
        int id = compteService.create(utilisateur1).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/compte/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertFalse(compteService.existsById(id));
    }
}