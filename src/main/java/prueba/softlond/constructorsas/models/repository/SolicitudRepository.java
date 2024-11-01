package prueba.softlond.constructorsas.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import prueba.softlond.constructorsas.models.documents.Solicitud;

public interface SolicitudRepository extends ReactiveMongoRepository<Solicitud, String> {
}
