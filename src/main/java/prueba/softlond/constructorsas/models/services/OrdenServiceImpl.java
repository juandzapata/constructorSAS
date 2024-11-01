package prueba.softlond.constructorsas.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.softlond.constructorsas.models.documents.Orden;
import prueba.softlond.constructorsas.models.repository.OrdenRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public Flux<Orden> findAll() {
        return ordenRepository.findAll();
    }

    @Override
    public Mono<Orden> findById(String id) {
        return ordenRepository.findById(id);
    }

    @Override
    public Mono<Orden> save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public Mono<Orden> update(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return ordenRepository.deleteById(id);
    }
}
