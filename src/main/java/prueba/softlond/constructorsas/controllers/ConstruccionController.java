package prueba.softlond.constructorsas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.softlond.constructorsas.models.documents.Construccion;
import prueba.softlond.constructorsas.models.services.ConstruccionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/construcciones")
public class ConstruccionController {

    @Autowired
    private ConstruccionService construccionService;

    @GetMapping
    public Mono<ResponseEntity<Flux<Construccion>>> getConstrucciones() {
        return Mono.just(ResponseEntity.ok(construccionService.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Construccion>> getConstruccionById(@PathVariable String id) {
        return construccionService.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
