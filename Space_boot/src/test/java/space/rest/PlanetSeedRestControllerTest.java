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
import space.rest.request.PlanetSeedRequest;
import space.service.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PlanetSeedRestControllerTest {

    @Autowired
    EspeceService especeService;
    @Autowired
    PartieService partieService;
    @Autowired
    JoueurService joueurService;
    @Autowired
    PlaneteService planeteService;
    @Autowired
    PlanetSeedService planetSeedService;
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private Partie partie1;
    private Joueur joueur1;
    private Planete planete1;
    private PlanetSeed planeteSeed1;


    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();

        partie1 = new Partie(1, 5, 2, Statut.DEBUT);

        partie1 = partieService.create(partie1);

        Map<Biome, Double> biomesMap = new EnumMap<>(Biome.class);
        biomesMap.put(Biome.PLAINE, 1.0);
        biomesMap.put(Biome.FORET, 0.75);
        biomesMap.put(Biome.DESERTIQUE, 0.5);
        biomesMap.put(Biome.OCEAN, 0.25);

        Espece espece1 = new Espece("Espece A", biomesMap);
        espece1 = especeService.create(espece1);
        joueur1 = new Joueur(1, partie1, espece1);
        joueur1 = joueurService.create(joueur1);
        planete1 = new Planete("Planete A", 125, new ArrayList<>());
        planete1 = planeteService.create(planete1);
        planeteSeed1 = new PlanetSeed(100, 12, 10, joueur1, planete1);
    }

    @Test
    void getAll() throws Exception {
        Map<Biome, Double> biomesMap = new EnumMap<>(Biome.class);
        biomesMap.put(Biome.PLAINE, 1.0);
        biomesMap.put(Biome.FORET, 0.75);
        biomesMap.put(Biome.DESERTIQUE, 0.5);
        biomesMap.put(Biome.OCEAN, 0.25);

        Espece espece2 = new Espece("Espece N", biomesMap);
        espece2 = especeService.create(espece2);
        Joueur joueur2 = new Joueur(2, partie1, espece2);
        joueur2 = joueurService.create(joueur2);
        Planete planete2 = new Planete("Planete B", 125, new ArrayList<>());
        planete2 = planeteService.create(planete2);
        PlanetSeed planeteSeed2 = new PlanetSeed(150, 10, 5, joueur2, planete2);

        planetSeedService.create(planeteSeed1);
        planetSeedService.create(planeteSeed2);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/planetSeed").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].population").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].population").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].arme").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].arme").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mineraiRestant").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].mineraiRestant").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idJoueur").value(joueur1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].idJoueur").value(joueur2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idPlanete").value(planete1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].idPlanete").value(planete2.getId()));
    }

    @Test
    void getById() throws Exception {
        int id = planetSeedService.create(planeteSeed1).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/planetSeed/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.population").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arme").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mineraiRestant").value(10));
    }

    @Test
    void create() throws Exception {
        PlanetSeedRequest planetSeedRequest = new PlanetSeedRequest();
        planetSeedRequest.setPopulation(100);
        planetSeedRequest.setArme(12);
        planetSeedRequest.setMineraiRestant(10);
        planetSeedRequest.setIdPlanete(planete1.getId());
        planetSeedRequest.setIdJoueur(joueur1.getId());

        String jsonRequest = objectMapper.writeValueAsString(planetSeedRequest);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/planetSeed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.population").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arme").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mineraiRestant").value(10))
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        int id = jsonNode.get("id").asInt();
        assertTrue(planetSeedService.existsById(id));
        PlanetSeed planetSeed = planetSeedService.getById(id);
        assertEquals(100, planetSeed.getPopulation());
        assertEquals(12, planetSeed.getArme());
        assertEquals(10, planetSeed.getMineraiRestant());
    }

    @Test
    void update() throws Exception {
        int id = planetSeedService.create(planeteSeed1).getId();

        Map<Biome, Double> biomesMap = new EnumMap<>(Biome.class);
        Espece espece2 = new Espece("Espece N", biomesMap);
        espece2 = especeService.create(espece2);
        Joueur joueur2 = new Joueur(2, partie1, espece2);
        int idJoueur = joueurService.create(joueur2).getId();
        Planete planete2 = new Planete("Planete B", 125, new ArrayList<>());
        int idPlanete = planeteService.create(planete2).getId();

        PlanetSeedRequest planetSeedRequest = new PlanetSeedRequest();
        planetSeedRequest.setId(id);
        planetSeedRequest.setPopulation(150);
        planetSeedRequest.setArme(15);
        planetSeedRequest.setMineraiRestant(16);
        planetSeedRequest.setIdPlanete(idPlanete);
        planetSeedRequest.setIdJoueur(idJoueur);

        String jsonRequest = objectMapper.writeValueAsString(planetSeedRequest);

        //Ensure that the response contains the correct elements
        this.mockMvc.perform(MockMvcRequestBuilders.put("/planetSeed/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.population").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arme").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mineraiRestant").value(16))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idJoueur").value(idJoueur))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPlanete").value(idPlanete));

        //Ensure that the element in database has been updated
        PlanetSeed planetSeed = planetSeedService.getById(id);
        assertEquals(150, planetSeed.getPopulation());
        assertEquals(15, planetSeed.getArme());
        assertEquals(16, planetSeed.getMineraiRestant());
        assertEquals(joueur2, planetSeed.getJoueur());
        assertEquals(planete2, planetSeed.getPlanete());
    }

    @Test
    void delete() throws Exception {
        int id = planetSeedService.create(planeteSeed1).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/planetSeed/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertFalse(planetSeedService.existsById(id));
    }
}