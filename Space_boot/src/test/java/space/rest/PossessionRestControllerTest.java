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
import space.model.Possession;
import space.model.Ressource;
import space.rest.request.PossessionRequest;
import space.service.PossessionService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PossessionRestControllerTest {

    @Autowired
    PossessionService possessionService;
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    void getAll() throws Exception {
        Possession possession1 = new Possession(202, Ressource.ARME);
        Possession possession2 = new Possession(404, Ressource.NOURRITURE);
        possessionService.create(possession1);
        possessionService.create(possession2);

        // ACT et ASSERT
        this.mockMvc.perform(MockMvcRequestBuilders.get("/possession").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantite").value(202))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantite").value(404));

    }

    @Test
    void getById() throws Exception {
        Possession possession1 = new Possession(202, Ressource.ARME);
        int id = possessionService.create(possession1).getId();

        // ACT et ASSERT
        this.mockMvc.perform(MockMvcRequestBuilders.get("/possession/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantite").value(202))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ressource").value(Ressource.ARME.toString()));
    }

    @Test
    void create() throws Exception {
        PossessionRequest possessionRequest = new PossessionRequest();
        possessionRequest.setQuantite(202);
        possessionRequest.setRessource(Ressource.ARME);

        // Convert the PossessionRequest object to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(possessionRequest);


        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/possession")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantite").value(202))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ressource").value(Ressource.ARME.toString()))
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        int id = jsonNode.get("id").asInt();
        assertTrue(possessionService.existsById(id));
        Possession possession = possessionService.getById(id);
        assertEquals(202, possession.getQuantite());
        assertEquals(Ressource.ARME, possession.getRessource());

    }

    @Test
    void update() throws Exception {
        Possession possession1 = new Possession(202, Ressource.ARME);
        int id = possessionService.create(possession1).getId();

        PossessionRequest possessionRequest = new PossessionRequest();
        possessionRequest.setId(id);
        possessionRequest.setQuantite(404);
        possessionRequest.setRessource(Ressource.NOURRITURE);

        // Convert the PossessionRequest object to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(possessionRequest);


        this.mockMvc.perform(MockMvcRequestBuilders.put("/possession/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantite").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ressource").value(Ressource.NOURRITURE.toString()));

        Possession possession = possessionService.getById(id);
        assertEquals(404, possession.getQuantite());
        assertEquals(Ressource.NOURRITURE, possession.getRessource());
    }

    @Test
    void delete() throws Exception {
        Possession possession1 = new Possession(202, Ressource.ARME);
        int id = possessionService.create(possession1).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/possession/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertFalse(possessionService.existsById(id));
    }
}