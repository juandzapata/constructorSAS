package prueba.softlond.constructorsas.models.services;

import prueba.softlond.constructorsas.models.documents.Coordenada;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoordenadaService {
    public Flux<Coordenada> findAll();
    public Mono<Coordenada> findById(String id);
    public Mono<Coordenada> save(Coordenada coordenada);
    public Mono<Coordenada> update(Coordenada coordenada);
    public Mono<Void> deleteById(String id);
    public Mono<Boolean> coordenadasEnUso(int x, int y);

}
