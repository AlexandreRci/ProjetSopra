package space.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import space.model.Planete;
import space.rest.request.PlaneteRequest;
import space.rest.response.PlaneteResponse;
import space.service.PlaneteService;

import java.util.List;

@RestController
@RequestMapping("/planete")
public class PlaneteRestController {

    private final PlaneteService planeteService;

    public PlaneteRestController(PlaneteService planeteService) {
        super();
        this.planeteService = planeteService;
    }

    @GetMapping("")
    public List<PlaneteResponse> getAll() {
        List<Planete> planetes = this.planeteService.getAll();
        return planetes.stream().map(PlaneteResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public PlaneteResponse getById(@PathVariable Integer id) {

        try {
            return PlaneteResponse.convert(planeteService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PostMapping("")
    public Planete create(@RequestBody PlaneteRequest planeteRequest) {
        Planete planete = PlaneteRequest.convert(planeteRequest);

        return planeteService.create(planete);
    }

    @PutMapping("/{id}")
    public Planete update(@RequestBody PlaneteRequest planeteRequest, @PathVariable Integer id) {
        if (!id.equals(planeteRequest.getId()) || !this.planeteService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
        }

        Planete planete = PlaneteRequest.convert(planeteRequest);

        return planeteService.create(planete);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!this.planeteService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
        }

        this.planeteService.deleteById(id);
    }

}
