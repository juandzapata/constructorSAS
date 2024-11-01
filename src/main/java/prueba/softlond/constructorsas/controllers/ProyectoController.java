package prueba.softlond.constructorsas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.softlond.constructorsas.models.documents.Proyecto;
import prueba.softlond.constructorsas.models.services.ProyectoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public Mono<ResponseEntity<Flux<Proyecto>>> getProyectos() {
        return Mono.just(ResponseEntity.ok(proyectoService.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Proyecto>> getProyectoById(@PathVariable String id) {
        return proyectoService.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Flux<Proyecto>>> createProyecto(@RequestBody Proyecto proyecto) {
        if(proyecto.getRecursos() == null) {
            proyecto.iniciarMateriales(1000, 1000, 1000, 1000, 1000);
        }

        return proyectoService.save(proyecto).map(p -> ResponseEntity.created(URI.create("/api/proyectos/" + p.getId())).build());
    }
}
