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

import space.model.Joueur;
import space.rest.request.JoueurRequest;
import space.rest.response.JoueurResponse;
import space.service.JoueurService;

@RestController
@RequestMapping("/joueur")
public class JoueurRestController {
	
	private JoueurService joueurService;

	public JoueurRestController(JoueurService joueurService) {
		super();
		this.joueurService = joueurService;
	}

	@GetMapping("")
	public List<JoueurResponse> getAll() {
		List<Joueur> joueur = this.joueurService.getAll();
		//JoueurResponse joueurResponse
		return joueur.stream().map(JoueurResponse::convert).toList();
		
	}

	@GetMapping("/{id}")
	public JoueurResponse getById(@PathVariable Integer id) {
		
		try {
			return JoueurResponse.convert(joueurService.getById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);

	}

	@PostMapping("")
	public JoueurResponse create(@RequestBody JoueurRequest joueurRequest) {
		Joueur joueur = JoueurRequest.convert(joueurRequest);
		return JoueurResponse.convert(joueurService.create(joueur));
	}

	@PutMapping("/{id}")
	public JoueurResponse update(@RequestBody JoueurRequest joueurRequest, @PathVariable Integer id) {
		if (id != joueurRequest.getId() || !this.joueurService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		Joueur joueur = JoueurRequest.convert(joueurRequest);
		
		return JoueurResponse.convert(joueurService.create(joueur));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.joueurService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.joueurService.deleteById(id);
	}
	
}
