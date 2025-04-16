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

import space.model.Espece;
import space.rest.request.EspeceRequest;
import space.rest.response.EspeceResponse;
import space.service.EspeceService;

@RestController
@RequestMapping("/espece")
public class EspeceRestController {
	private EspeceService especeService;

	public EspeceRestController(EspeceService especeService) {
		super();
		this.especeService = especeService;
	}

	@GetMapping("")
	public List<EspeceResponse> getAll() {
		List<Espece> espece = this.especeService.getAll();
		return espece.stream().map(EspeceResponse::convert).toList();
	}

	@GetMapping("/{id}")
	public EspeceResponse getById(@PathVariable Integer id) {
		
		try {
			return EspeceResponse.convert(especeService.getById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);

	}

	@PostMapping("")
	public Espece create(@RequestBody EspeceRequest especeRequest) {
		Espece espece = EspeceRequest.convert(especeRequest);
		return especeService.create(espece);
	}

	@PutMapping("/{id}")
	public Espece update(@RequestBody EspeceRequest especeRequest, @PathVariable Integer id) {
		if (id != especeRequest.getId() || !this.especeService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		Espece espece = EspeceRequest.convert(especeRequest);

		return especeService.create(espece);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.especeService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.especeService.deleteById(id);
	}
	
}
