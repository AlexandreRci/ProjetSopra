package space.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import space.model.*;
import space.rest.request.JoueurRequest;
import space.service.EspeceService;
import space.service.JoueurService;
import space.service.PartieService;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class JoueurRestControllerTest {

    @Autowired
    JoueurService joueurService;
    @Autowired
    PartieService partieService;
    @Autowired
    EspeceService especeService;
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    private Map<Biome, Double> biomesMap;
    private Partie partie1;
    private Espece espece1;
    private Joueur joueur;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();

        partie1 = new Partie(1, 5, 2, Statut.DEBUT);

        partie1 = partieService.create(partie1);

        biomesMap = new EnumMap<>(Biome.class);
        biomesMap.put(Biome.PLAINE, 1.0);
        biomesMap.put(Biome.FORET, 0.75);
        biomesMap.put(Biome.DESERTIQUE, 0.5);
        biomesMap.put(Biome.OCEAN, 0.25);

        espece1 = new Espece("Espece A", biomesMap);
        espece1 = especeService.create(espece1);
        joueur = new Joueur(1, partie1, espece1);

    }

    @Test
    void getAll() throws Exception {

        Joueur joueur2 = new Joueur(2, partie1, espece1);

        joueurService.create(joueur);
        joueurService.create(joueur2);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/joueur").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].position").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].position").value(2));

    }

    @Test
    void getById() throws Exception {
        int id = joueurService.create(joueur).getId();
        this.mockMvc.perform(MockMvcRequestBuilders.get("/joueur/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idEspece").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPartie").exists());
    }

    @Test
    void create() throws Exception {
        JoueurRequest joueurRequest = new JoueurRequest();
        joueurRequest.setPosition(1);
        joueurRequest.setIdEspece(espece1.getId());
        joueurRequest.setIdPartie(partie1.getId());

        String jsonRequest = objectMapper.writeValueAsString(joueurRequest);


        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/joueur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPartie").value(partie1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idEspece").value(espece1.getId()))
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        int id = jsonNode.get("id").asInt();
        assertTrue(joueurService.existsById(id));
        Joueur joueur1 = joueurService.getById(id);
        assertEquals(1, joueur1.getPosition());
        assertEquals(partie1.getId(), joueur1.getPartie().getId());
        assertEquals(espece1.getId(), joueur1.getEspece().getId());
    }

    @Test
    void update() throws Exception {

        int id = joueurService.create(joueur).getId();

        JoueurRequest joueurRequest = new JoueurRequest();
        joueurRequest.setId(id);
        joueurRequest.setPosition(1);
        joueurRequest.setIdEspece(espece1.getId());
        joueurRequest.setIdPartie(partie1.getId());

        String jsonRequest = objectMapper.writeValueAsString(joueurRequest);


        this.mockMvc.perform(MockMvcRequestBuilders.put("/joueur/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPartie").value(partie1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idEspece").value(espece1.getId()));

        assertTrue(joueurService.existsById(id));
        Joueur joueur1 = joueurService.getById(id);
        assertEquals(1, joueur1.getPosition());
        assertEquals(partie1.getId(), joueur1.getPartie().getId());
        assertEquals(espece1.getId(), joueur1.getEspece().getId());
    }

    @Test
    void delete() throws Exception {
        int id = joueurService.create(joueur).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/joueur/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertFalse(especeService.existsById(id));
    }
}