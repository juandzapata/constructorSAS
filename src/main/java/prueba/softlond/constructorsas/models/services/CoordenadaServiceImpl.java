package prueba.softlond.constructorsas.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.softlond.constructorsas.models.documents.Coordenada;
import prueba.softlond.constructorsas.models.repository.CoordenadaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoordenadaServiceImpl implements CoordenadaService {
    @Autowired
    private CoordenadaRepository coordenadaRepository;

    @Override
    public Flux<Coordenada> findAll() {
        return coordenadaRepository.findAll();
    }

    @Override
    public Mono<Coordenada> findById(String id) {
        return coordenadaRepository.findById(id);
    }

    @Override
    public Mono<Coordenada> save(Coordenada coordenada) {
        return coordenadaRepository.save(coordenada);
    }

    @Override
    public Mono<Coordenada> update(Coordenada coordenada) {
        return coordenadaRepository.save(coordenada);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return coordenadaRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> coordenadasEnUso(int x, int y){
        return coordenadaRepository.existsByXAndY(x, y);
    }
}
