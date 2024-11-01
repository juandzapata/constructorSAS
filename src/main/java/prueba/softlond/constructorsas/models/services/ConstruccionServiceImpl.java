package prueba.softlond.constructorsas.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.softlond.constructorsas.models.documents.Construccion;
import prueba.softlond.constructorsas.models.repository.ConstruccionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConstruccionServiceImpl implements ConstruccionService {

    @Autowired
    private ConstruccionRepository construccionRepository;

    @Override
    public Flux<Construccion> findAll() {
        return construccionRepository.findAll();
    }

    @Override
    public Mono<Construccion> findById(String id) {
        return construccionRepository.findById(id);
    }

    @Override
    public Mono<Construccion> save(Construccion construccion) {
        return construccionRepository.save(construccion);
    }

    @Override
    public Mono<Construccion> update(Construccion construccion) {
        return construccionRepository.save(construccion);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return construccionRepository.deleteById(id);
    }
}
