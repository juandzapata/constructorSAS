package prueba.softlond.constructorsas.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import prueba.softlond.constructorsas.models.documents.Construccion;
import prueba.softlond.constructorsas.models.documents.TipoConstruccion;
import reactor.core.publisher.Mono;

public interface ConstruccionRepository extends ReactiveMongoRepository<Construccion, String> {
    Mono<Construccion> findByTipoConstruccion(TipoConstruccion tipoConstruccion);
}
