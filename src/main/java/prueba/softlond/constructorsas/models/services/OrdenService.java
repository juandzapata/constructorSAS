package prueba.softlond.constructorsas.models.services;

import prueba.softlond.constructorsas.models.documents.Orden;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdenService {
    public Flux<Orden> findAll();
    public Mono<Orden> findById(String id);
    public Mono<Orden> save(Orden orden);
    public Mono<Orden> update(Orden orden);
    public Mono<Void> deleteById(String id);
}
