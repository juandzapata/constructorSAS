package prueba.softlond.constructorsas.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import prueba.softlond.constructorsas.models.documents.Orden;

public interface OrdenRepository extends ReactiveMongoRepository<Orden, String> {
}
