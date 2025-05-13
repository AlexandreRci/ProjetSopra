package space.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import space.model.Batiment;
import space.rest.request.BatimentRequest;
import space.rest.response.BatimentResponse;
import space.service.BatimentService;

import java.util.List;

@RestController
@RequestMapping("/batiment")
public class BatimentRestController {

    private final BatimentService batimentService;

    public BatimentRestController(BatimentService batimentService) {
        super();
        this.batimentService = batimentService;

    }

    @GetMapping("")
    public List<BatimentResponse> getAll() {
        List<Batiment> batiments = this.batimentService.getAll();

        return batiments.stream().map(BatimentResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public BatimentResponse getById(@PathVariable Integer id) {

        try {
            return BatimentResponse.convert(batimentService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PostMapping("")
    public Batiment create(@RequestBody BatimentRequest batimentRequest) {
        Batiment batiment = BatimentRequest.convert(batimentRequest);

        return batimentService.create(batiment);
    }


    @PutMapping("/{id}")
    public Batiment update(@RequestBody BatimentRequest batimentRequest, @PathVariable Integer id) {
        if (!id.equals(batimentRequest.getId()) || !this.batimentService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
        }

        Batiment batiment = BatimentRequest.convert(batimentRequest);

        return batimentService.create(batiment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!this.batimentService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
        }

        this.batimentService.deleteById(id);
    }
}
