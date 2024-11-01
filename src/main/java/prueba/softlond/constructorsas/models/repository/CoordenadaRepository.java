package prueba.softlond.constructorsas.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import prueba.softlond.constructorsas.models.documents.Coordenada;
import reactor.core.publisher.Mono;

public interface CoordenadaRepository extends ReactiveMongoRepository<Coordenada, String> {
    Mono<Boolean> existsByXAndY(int x, int y);
}
