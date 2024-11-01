package prueba.softlond.constructorsas.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import prueba.softlond.constructorsas.models.documents.Proyecto;

public interface ProyectoRepository extends ReactiveMongoRepository<Proyecto, String> {
}
