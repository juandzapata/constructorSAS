package prueba.softlond.constructorsas.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.softlond.constructorsas.models.documents.Proyecto;
import prueba.softlond.constructorsas.models.repository.ProyectoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public Flux<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    @Override
    public Mono<Proyecto> findById(String id) {
        return proyectoRepository.findById(id);
    }

    @Override
    public Mono<Proyecto> save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Mono<Proyecto> update(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return proyectoRepository.deleteById(id);
    }
}
