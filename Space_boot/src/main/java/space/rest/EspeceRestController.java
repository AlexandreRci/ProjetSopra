package space.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import space.model.Espece;
import space.rest.request.EspeceRequest;
import space.rest.response.EspeceResponse;
import space.service.EspeceService;

import java.util.List;

@RestController
@RequestMapping("/espece")
public class EspeceRestController {
    private final EspeceService especeService;

    public EspeceRestController(EspeceService especeService) {
        super();
        this.especeService = especeService;
    }

    @GetMapping("")
    public List<EspeceResponse> getAll() {
        List<Espece> espece = this.especeService.getAll();
        if (espece.isEmpty()) {
            especeService.create(new Espece("humain", 1.0, 0.75, 0.25, 0.5));
            especeService.create(new Espece("insecte", 0.25, 1.0, 0.5, 0.75));
            especeService.create(new Espece("robot", 0.5, 0.25, 0.75, 1.0));
            especeService.create(new Espece("triton", 0.75, 0.5, 1.0, 0.25));
        }
        espece = this.especeService.getAll();
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
        if (!id.equals(especeRequest.getId()) || !this.especeService.existsById(id)) {
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
