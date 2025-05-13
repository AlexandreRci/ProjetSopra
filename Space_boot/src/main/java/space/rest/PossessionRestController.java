package space.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import space.model.Possession;
import space.rest.request.PossessionRequest;
import space.rest.response.PossessionResponse;
import space.service.PossessionService;

import java.util.List;

@RestController
@RequestMapping("/possession")
public class PossessionRestController {
    private final PossessionService possessionService;

    public PossessionRestController(PossessionService possessionService) {
        this.possessionService = possessionService;
    }

    @GetMapping("")
    public List<PossessionResponse> getAll() {
        List<Possession> possessions = this.possessionService.getAll();

        return possessions.stream().map(PossessionResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public PossessionResponse getById(@PathVariable Integer id) {
        try {
            return PossessionResponse.convert(possessionService.getById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public Possession create(@RequestBody PossessionRequest possessionRequest) {
        Possession possession = PossessionRequest.convert(possessionRequest);

        return possessionService.create(possession);
    }

    @PutMapping("/{id}")
    public Possession update(@RequestBody PossessionRequest possessionRequest, @PathVariable Integer id) {
        if (!id.equals(possessionRequest.getId()) || !this.possessionService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
        }

        Possession possession = PossessionRequest.convert(possessionRequest);

        return possessionService.create(possession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!this.possessionService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
        }

        this.possessionService.deleteById(id);
    }
}
