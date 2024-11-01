package prueba.softlond.constructorsas.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import prueba.softlond.constructorsas.models.documents.Estado;
import prueba.softlond.constructorsas.models.documents.Orden;
import reactor.core.publisher.Mono;

public interface OrdenRepository extends ReactiveMongoRepository<Orden, String> {
    Mono<Orden> findByEstado(Estado estado);
    Mono<Orden> findFirstByEstadoOrderBySolicitud_FechaSolicitudAsc(Estado estado);
}
