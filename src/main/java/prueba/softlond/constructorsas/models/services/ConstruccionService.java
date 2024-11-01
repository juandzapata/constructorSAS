package prueba.softlond.constructorsas.models.services;

import prueba.softlond.constructorsas.models.documents.Construccion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConstruccionService {
    public Flux<Construccion> findAll();
    public Mono<Construccion> findById(String id);
    public Mono<Construccion> save(Construccion construccion);
    public Mono<Construccion> update(Construccion construccion);
    public Mono<Void> deleteById(String id);

}
