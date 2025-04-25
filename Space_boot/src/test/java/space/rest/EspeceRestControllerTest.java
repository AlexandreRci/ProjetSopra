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
import space.model.Espece;
import space.rest.request.EspeceRequest;
import space.service.EspeceService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class EspeceRestControllerTest {

    @Autowired
    EspeceService especeService;
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    private Map<Biome, Double> biomesMap;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
        Map<Biome, Double> biomesMap = new HashMap<>();
        biomesMap.put(Biome.Plaine, 1.0);
        biomesMap.put(Biome.Foret, 0.75);
        biomesMap.put(Biome.Desertique, 0.5);
        biomesMap.put(Biome.Ocean, 0.25);
        this.biomesMap = biomesMap;
    }


    @Test
    void getAll() throws Exception {
        Espece espece1 = new Espece("Espece A", biomesMap);
        Espece espece2 = new Espece("Espece B", biomesMap);
        especeService.create(espece1);
        especeService.create(espece2);

        // ACT et ASSERT
        this.mockMvc.perform(MockMvcRequestBuilders.get("/espece").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Espece A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Espece B"));

    }

    @Test
    void getById() throws Exception {
        Espece espece1 = new Espece("Espece A", biomesMap);
        int id = especeService.create(espece1).getId();

        // ACT et ASSERT
        this.mockMvc.perform(MockMvcRequestBuilders.get("/espece/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Espece A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biomes.Plaine").value(1.0));
    }

    @Test
    void create() throws Exception {
        EspeceRequest especeRequest = new EspeceRequest();
        especeRequest.setNom("Espece A");
        especeRequest.setBiomes(biomesMap);

        // Convert the EspeceRequest object to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(especeRequest);


        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/espece")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Espece A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biomes.Plaine").value(1.0))
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        int id = jsonNode.get("id").asInt();
        assertTrue(especeService.existsById(id));
        Espece espece = especeService.getById(id);
        assertEquals("Espece A", espece.getNom());
        assertEquals(biomesMap, espece.getBiomes());

    }

    @Test
    void update() throws Exception {
        Espece espece1 = new Espece("Espece A", biomesMap);
        int id = especeService.create(espece1).getId();
        Map<Biome, Double> biomesMap2 = new HashMap<>();
        biomesMap2.put(Biome.Plaine, 0.5);
        biomesMap2.put(Biome.Foret, 0.75);
        biomesMap2.put(Biome.Desertique, 0.5);
        biomesMap2.put(Biome.Ocean, 0.25);

        EspeceRequest especeRequest = new EspeceRequest();
        especeRequest.setId(id);
        especeRequest.setNom("Espece B");
        especeRequest.setBiomes(biomesMap2);

        // Convert the EspeceRequest object to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(especeRequest);


        this.mockMvc.perform(MockMvcRequestBuilders.put("/espece/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Espece B"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biomes.Plaine").value(0.5));


        Espece espece = especeService.getById(id);
        assertEquals("Espece B", espece.getNom());
        assertEquals(biomesMap2, espece.getBiomes());
    }

    @Test
    void delete() throws Exception {
        Espece espece1 = new Espece("Espece A", biomesMap);
        int id = especeService.create(espece1).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/espece/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertFalse(especeService.existsById(id));
    }
}