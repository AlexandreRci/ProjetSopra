package space.rest;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import space.model.Compte;
import space.rest.request.CompteRequest;
import space.rest.response.CompteResponse;
import space.service.CompteService;

@RestController
@RequestMapping("/compte")
public class CompteRestController {

    private final CompteService compteService;
    private PasswordEncoder passwordEncoder;

    public CompteRestController(CompteService compteService, PasswordEncoder passwordEncoder) {
        this.compteService = compteService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public List<CompteResponse> getAll() {
        List<Compte> comptes = this.compteService.getAll();

        return comptes.stream().map(CompteResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public CompteResponse getById(@PathVariable Integer id) {

        try {
            return CompteResponse.convert(compteService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PostMapping("")
    public Compte create(@RequestBody CompteRequest compteRequest) {
        Compte compte = CompteRequest.convert(compteRequest);
        compte.setPassword(this.passwordEncoder.encode(compte.getPassword()));
        
        try {
            return compteService.create(compte);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Identifiant déjà utilisé", e);
        }
    }

    @PutMapping("/{id}")
    public Compte update(@RequestBody CompteRequest compteRequest, @PathVariable Integer id) {
        if (!id.equals(compteRequest.getId()) || !this.compteService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
        }

        Compte compte = CompteRequest.convert(compteRequest);

        return compteService.create(compte);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!this.compteService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
        }

        this.compteService.deleteById(id);
    }

}
