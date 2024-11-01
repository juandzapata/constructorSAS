package prueba.softlond.constructorsas.models.services;

import prueba.softlond.constructorsas.models.documents.Proyecto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProyectoService {
    public Flux<Proyecto> findAll();
    public Mono<Proyecto> findById(String id);
    public Mono<Proyecto> save(Proyecto proyecto);
    public Mono<Proyecto> update(Proyecto proyecto);
    public Mono<Void> deleteById(String id);
}
