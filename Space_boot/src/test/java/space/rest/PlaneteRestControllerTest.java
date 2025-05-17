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
import space.model.Biome;
import space.model.Planete;
import space.rest.request.PlaneteRequest;
import space.service.PlaneteService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PlaneteRestControllerTest {

    @Autowired
    PlaneteService planeteService;
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private List<Biome> listeBiome1;
    private Planete planete1;


    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
        listeBiome1 = new ArrayList<>();
        listeBiome1.add(Biome.DESERTIQUE);
        listeBiome1.add(Biome.PLAINE);
        listeBiome1.add(Biome.OCEAN);
        planete1 = new Planete("Planete A", 125, listeBiome1);


    }

    @Test
    void getAll() throws Exception {
        List<Biome> listeBiome2 = new ArrayList<>();
        listeBiome2.add(Biome.FORET);
        listeBiome2.add(Biome.PLAINE);
        listeBiome2.add(Biome.DESERTIQUE);
        Planete planete2 = new Planete("Planete B", 247, listeBiome2);

        planeteService.create(planete1);
        planeteService.create(planete2);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/planete").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Planete A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Planete B"));

    }

    @Test
    void getById() throws Exception {
        int id = planeteService.create(planete1).getId();
        this.mockMvc.perform(MockMvcRequestBuilders.get("/planete/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Planete A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biomes[0]").value("DESERTIQUE"));
    }

    @Test
    void create() throws Exception {

        PlaneteRequest planeteRequest = new PlaneteRequest();
        planeteRequest.setBiomes(listeBiome1);
        planeteRequest.setNom("Planete A");
        planeteRequest.setMinerai(125);

        String jsonRequest = objectMapper.writeValueAsString(planeteRequest);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/planete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Planete A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.minerai").value(125))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biomes").exists())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        int id = jsonNode.get("id").asInt();
        assertTrue(planeteService.existsById(id));
        Planete planete = planeteService.getById(id);
        assertEquals("Planete A", planete.getNom());
        assertEquals(125, planete.getMinerai());
        assertEquals(listeBiome1, planete.getBiomes());
    }

    @Test
    void update() throws Exception {
        int id = planeteService.create(planete1).getId();

        List<Biome> listeBiome2 = new ArrayList<>();
        listeBiome2.add(Biome.FORET);
        listeBiome2.add(Biome.PLAINE);
        listeBiome2.add(Biome.DESERTIQUE);

        PlaneteRequest planeteRequest = new PlaneteRequest();
        planeteRequest.setId(id);
        planeteRequest.setBiomes(listeBiome2);
        planeteRequest.setNom("Planete B");
        planeteRequest.setMinerai(250);

        String jsonRequest = objectMapper.writeValueAsString(planeteRequest);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/planete/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Planete B"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.minerai").value(250))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biomes").exists())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());

        assertEquals(id, jsonNode.get("id").asInt());
        assertTrue(planeteService.existsById(id));
        Planete planete = planeteService.getById(id);
        assertEquals("Planete B", planete.getNom());
        assertEquals(250, planete.getMinerai());
        assertEquals(listeBiome2, planete.getBiomes());
    }

    @Test
    void delete() throws Exception {
        int id = planeteService.create(planete1).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/planete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertFalse(planeteService.existsById(id));
    }
}