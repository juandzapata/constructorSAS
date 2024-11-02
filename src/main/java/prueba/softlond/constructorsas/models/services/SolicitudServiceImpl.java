package prueba.softlond.constructorsas.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.softlond.constructorsas.exceptions.ConstruccionDesconocidaException;
import prueba.softlond.constructorsas.exceptions.CoordenadaEnUsoException;
import prueba.softlond.constructorsas.models.documents.*;
import prueba.softlond.constructorsas.models.repository.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private CoordenadaRepository coordenadaRepository;

    @Autowired
    private ConstruccionRepository construccionRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    private static final Logger log = LoggerFactory.getLogger(SolicitudServiceImpl.class);

    public Mono<Solicitud> crearSolicitud(SolicitudDTO solicitudDTO) {
        return verificarCoordenadas(solicitudDTO)
                .flatMap(coordenada -> buscarConstruccionYCrearSolicitud(solicitudDTO, coordenada))
                .flatMap(solicitud -> {
                    log.info("Creando una orden para la solicitud con ID: {}", solicitud.getId());
                    return crearOrdenYAsociarAProyecto(solicitud, solicitudDTO.getProyectoId());
                });
    }

    private Mono<Coordenada> verificarCoordenadas(SolicitudDTO solicitudDTO) {
        return coordenadaRepository.existsByXAndY(solicitudDTO.getX(), solicitudDTO.getY())
                .flatMap(coordenadasEnUso -> {
                    if (coordenadasEnUso) {
                        return Mono.error(new CoordenadaEnUsoException("Las coordenadas ya están en uso."));
                    }
                    Coordenada nuevaCoordenada = new Coordenada();
                    nuevaCoordenada.setX(solicitudDTO.getX());
                    nuevaCoordenada.setY(solicitudDTO.getY());
                    return coordenadaRepository.save(nuevaCoordenada);
                });
    }

    private Mono<Solicitud> buscarConstruccionYCrearSolicitud(SolicitudDTO solicitudDTO, Coordenada coordenada) {
        return construccionRepository.findByTipoConstruccion(solicitudDTO.getTipoConstruccion())
                .switchIfEmpty(Mono.error(new ConstruccionDesconocidaException("Tipo de construcción desconocido.")))
                .flatMap(construccion -> {
                    Solicitud solicitud = new Solicitud();
                    solicitud.setCoordenada(coordenada);
                    solicitud.setConstruccion(construccion);
                    solicitud.setFechaSolicitud(LocalDateTime.now());
                    return solicitudRepository.save(solicitud);
                });
    }

    private Mono<Solicitud> crearOrdenYAsociarAProyecto(Solicitud solicitud, String proyectoId) {
        Orden nuevaOrden = new Orden(solicitud);
        return ordenRepository.save(nuevaOrden)
                .flatMap(ordenGuardada -> asociarOrdenAProyecto(proyectoId, ordenGuardada))
                .thenReturn(solicitud);
    }

    private Mono<Proyecto> asociarOrdenAProyecto(String proyectoId, Orden orden) {
        return proyectoRepository.findById(proyectoId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Proyecto no encontrado.")))
                .flatMap(proyecto -> {
                    proyecto.addOrden(orden);
                    return proyectoRepository.save(proyecto);
                });
    }


    @Override
    public Flux<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    @Override
    public Mono<Solicitud> findById(String id) {
        return solicitudRepository.findById(id);
    }

    @Override
    public Mono<Solicitud> save(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Mono<Solicitud> update(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return solicitudRepository.deleteById(id);
    }
}
