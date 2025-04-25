package space.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import space.model.Biome;
import space.model.Planete;
import space.service.PlaneteService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlaneteRestControllerTest {


    @Autowired
    private PlaneteService planeteService;
    @Autowired
    private TestRestTemplate template;


    @Test
    @Transactional
    @Rollback(true)
    void testGetAllPlanete() {
    	
    	// ARRANGE 	
        List<Biome> listeBiome1 = new ArrayList<>();
        List<Biome> listeBiome2 = new ArrayList<>();
        List<Biome> listeBiome3 = new ArrayList<>();
        List<Biome> listeBiome4 = new ArrayList<>();
    	
        Collections.addAll(listeBiome1, Biome.Plaine, Biome.Foret, Biome.Desertique);
        Collections.addAll(listeBiome2, Biome.Plaine, Biome.Foret, Biome.Desertique);
        Collections.addAll(listeBiome3, Biome.Plaine, Biome.Foret, Biome.Desertique);
        Collections.addAll(listeBiome4, Biome.Plaine, Biome.Foret, Biome.Desertique);
      
        
        Planete planete1 = new Planete("Planete A", 125, listeBiome1);
        Planete planete2 = new Planete("Planete B", 247, listeBiome2);
        Planete planete3 = new Planete("Planete C", 371, listeBiome3);
        Planete planete4 = new Planete("Planete D", 404, listeBiome4);
        
        planeteService.create(planete1);
        planeteService.create(planete2);
        planeteService.create(planete3);
        planeteService.create(planete4);

        // ACT 	
        ResponseEntity<List> planeteResponse = template.getForEntity("/planete", List.class);

        // ASSERT
        assertEquals(HttpStatus.OK, planeteResponse.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, planeteResponse.getHeaders().getContentType());
        assertNotNull(planeteResponse.getBody());
        assertEquals(4, planeteResponse.getBody().size());
    }

    @Test
    @Transactional
    @Rollback(true)
    void testGetOnePlaneteById() throws Exception {
    	
    	// ARRANGE 	
        List<Biome> listeBiome1 = new ArrayList<>();
        List<Biome> listeBiome2 = new ArrayList<>();
        List<Biome> listeBiome3 = new ArrayList<>();
        List<Biome> listeBiome4 = new ArrayList<>();
    	
        Collections.addAll(listeBiome1, Biome.Plaine, Biome.Foret, Biome.Desertique);
        Collections.addAll(listeBiome2, Biome.Foret, Biome.Foret, Biome.Desertique);
        Collections.addAll(listeBiome3, Biome.Plaine, Biome.Desertique, Biome.Desertique);
        Collections.addAll(listeBiome4, Biome.Ocean, Biome.Ocean, Biome.Ocean);
      
        
        Planete planete1 = new Planete("Planete A", 125, listeBiome1);
        Planete planete2 = new Planete("Planete B", 247, listeBiome2);
        Planete planete3 = new Planete("Planete C", 371, listeBiome3);
        Planete planete4 = new Planete("Planete D", 404, listeBiome4);
        
        planeteService.create(planete1);
        planeteService.create(planete2);
        planeteService.create(planete3);
        planeteService.create(planete4);
        
        // ACT 	
        ResponseEntity<Planete> planeteResponse = template.getForEntity("/planete/1", Planete.class);
        
        // ASSERT
        assertEquals(HttpStatus.OK, planeteResponse.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, planeteResponse.getHeaders().getContentType());
        assertNotNull(planeteResponse.getBody());
        
        Planete checkPlanete = planeteResponse.getBody();
        assertNotNull(checkPlanete);
        assertEquals("Planete A", checkPlanete.getNom());		// Check le nom
        assertEquals(125, checkPlanete.getMinerai());			// Check le nombre de minerai
        assertEquals(listeBiome1, checkPlanete.getBiomes());	// check les biomes
        
        
    }

    //@Test(expected = Exception.class)
    @Test
    @Transactional
    @Rollback(true)
    void testGetOnePlaneteByIdError() throws Exception {
    	
    	// ARRANGE 	
        List<Biome> listeBiome1 = new ArrayList<>();
        Collections.addAll(listeBiome1, Biome.Plaine, Biome.Foret, Biome.Desertique);
        Planete planete1 = new Planete("Planete A", 125, listeBiome1);
        planeteService.create(planete1);
        planete1.setId(null);
        
        // ACT 	
        ResponseEntity<Planete> planeteResponse = template.getForEntity("/planete/1", Planete.class);
        planeteService.getById(1);
        
        // ASSERT
        assertEquals(HttpStatus.OK, planeteResponse.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, planeteResponse.getHeaders().getContentType());
        assertNotNull(planeteResponse.getBody());
        
        Planete checkPlanete = planeteResponse.getBody();
        
        assertEquals(" ", checkPlanete.getId());
        
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
	
}
