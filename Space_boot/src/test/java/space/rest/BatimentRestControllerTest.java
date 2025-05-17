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
import space.model.Batiment;
import space.model.Ressource;
import space.model.Taille;
import space.rest.request.BatimentRequest;
import space.service.BatimentService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class BatimentRestControllerTest {

    @Autowired
    BatimentService batimentService;
    @Autowired
    private WebApplicationContext applicationContext;
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
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        Batiment batiment2 = new Batiment("Ferme", Taille.MOYEN, Ressource.NOURRITURE);
        batimentService.create(batiment1);
        batimentService.create(batiment2);

        // ACT et ASSERT
        this.mockMvc.perform(MockMvcRequestBuilders.get("/batiment").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Caserne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Ferme"));

    }

    @Test
    void getById() throws Exception {
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        int id = batimentService.create(batiment1).getId();

        // ACT et ASSERT
        this.mockMvc.perform(MockMvcRequestBuilders.get("/batiment/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Caserne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.taille").value(Taille.PETIT.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ressource").value(Ressource.ARME.toString()));
    }

    @Test
    void create() throws Exception {
        BatimentRequest batimentRequest = new BatimentRequest();
        batimentRequest.setNom("Caserne");
        batimentRequest.setTaille(Taille.MOYEN);
        batimentRequest.setRessource(Ressource.ARME);

        // Convert the BatimentRequest object to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(batimentRequest);


        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/batiment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Caserne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.taille").value(Taille.MOYEN.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ressource").value(Ressource.ARME.toString()))
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        int id = jsonNode.get("id").asInt();
        assertTrue(batimentService.existsById(id));
        Batiment batiment = batimentService.getById(id);
        assertEquals("Caserne", batiment.getNom());
        assertEquals(Taille.MOYEN, batiment.getTaille());
        assertEquals(Ressource.ARME, batiment.getRessource());

    }

    @Test
    void update() throws Exception {
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        int id = batimentService.create(batiment1).getId();

        BatimentRequest batimentRequest = new BatimentRequest();
        batimentRequest.setId(id);
        batimentRequest.setNom("Ferme");
        batimentRequest.setTaille(Taille.MOYEN);
        batimentRequest.setRessource(Ressource.NOURRITURE);

        // Convert the BatimentRequest object to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(batimentRequest);

        System.out.println(jsonRequest);
        System.out.println(id);


        this.mockMvc.perform(MockMvcRequestBuilders.put("/batiment/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Ferme"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.taille").value(Taille.MOYEN.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ressource").value(Ressource.NOURRITURE.toString()));

        Batiment batiment = batimentService.getById(id);
        assertEquals("Ferme", batiment.getNom());
        assertEquals(Taille.MOYEN, batiment.getTaille());
        assertEquals(Ressource.NOURRITURE, batiment.getRessource());
    }

    @Test
    void delete() throws Exception {
        Batiment batiment1 = new Batiment("Caserne", Taille.PETIT, Ressource.ARME);
        int id = batimentService.create(batiment1).getId();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/batiment/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertFalse(batimentService.existsById(id));
    }
}