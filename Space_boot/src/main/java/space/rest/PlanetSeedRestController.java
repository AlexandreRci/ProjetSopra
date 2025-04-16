package space.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import space.model.PlanetSeed;
import space.rest.request.PlanetSeedRequest;
import space.rest.response.PlanetSeedResponse;
import space.service.PlanetSeedService;

@RestController
@RequestMapping("/planetSeed")
public class PlanetSeedRestController {

	private PlanetSeedService planetSeedService;

	public PlanetSeedRestController(PlanetSeedService planetSeedService) {
		super();
		this.planetSeedService = planetSeedService;
	}

	@GetMapping("")
	public List<PlanetSeedResponse> getAll() {
		List<PlanetSeed> planetSeeds = this.planetSeedService.getAll();

//		return planetSeeds.stream().map(u -> PlanetSeedResponse.convert(u)).toList();
		return planetSeeds.stream().map(PlanetSeedResponse::convert).toList();
	}

	@GetMapping("/{id}")
	public PlanetSeedResponse getById(@PathVariable Integer id) {
		
		try {
			return PlanetSeedResponse.convert(planetSeedService.getById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);

	}

	@PostMapping("")
	public PlanetSeedResponse create(@RequestBody PlanetSeedRequest planetSeedRequest) {
		PlanetSeed planetSeed = PlanetSeedRequest.convert(planetSeedRequest);
		return PlanetSeedResponse.convert(planetSeedService.create(planetSeed));
	}

	@PutMapping("/{id}")
	public PlanetSeedResponse update(@RequestBody PlanetSeedRequest planetSeedRequest, @PathVariable Integer id) {
		if (id != planetSeedRequest.getId() || !this.planetSeedService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		PlanetSeed planetSeed = PlanetSeedRequest.convert(planetSeedRequest);

		return PlanetSeedResponse.convert(planetSeedService.create(planetSeed));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.planetSeedService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.planetSeedService.deleteById(id);
	}
	
}
