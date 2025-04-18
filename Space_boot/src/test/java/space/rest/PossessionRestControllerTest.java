package space.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import space.model.Possession;
import space.model.Ressource;
import space.service.PossessionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PossessionRestControllerTest {

    @Autowired
    private PossessionService possessionService;
    @Autowired
    private TestRestTemplate template;


    @Test

    void getAll() {
        Possession possessionArme1 = new Possession(105, Ressource.Arme);
        Possession possessionArme2 = new Possession(293, Ressource.Arme);

        Possession possessionNourriture1 = new Possession(108, Ressource.Nourriture);
        Possession possessionNourriture2 = new Possession(210, Ressource.Nourriture);

        Possession possessionEnergie1 = new Possession(100, Ressource.Energie);
        Possession possessionEnergie2 = new Possession(230, Ressource.Energie);

        Possession possessionArgent1 = new Possession(125, Ressource.Argent);
        Possession possessionArgent2 = new Possession(240, Ressource.Argent);


        possessionService.create(possessionArme1);
        possessionService.create(possessionArme2);

        possessionService.create(possessionNourriture1);
        possessionService.create(possessionNourriture2);

        possessionService.create(possessionEnergie1);
        possessionService.create(possessionEnergie2);

        possessionService.create(possessionArgent1);
        possessionService.create(possessionArgent2);

        ResponseEntity<List> possessionResponse = template.getForEntity("/possession", List.class);

        // ASSERT
        assertEquals(HttpStatus.OK, possessionResponse.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, possessionResponse.getHeaders().getContentType());
        assertNotNull(possessionResponse.getBody());
        assertEquals(16, possessionResponse.getBody().size());
    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}