package prueba.softlond.constructorsas.models.services;

import prueba.softlond.constructorsas.models.documents.Solicitud;
import prueba.softlond.constructorsas.models.documents.SolicitudDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudService {
    public Flux<Solicitud> findAll();
    public Mono<Solicitud> findById(String id);
    public Mono<Solicitud> save(Solicitud solicitud);
    public Mono<Solicitud> update(Solicitud solicitud);
    public Mono<Void> deleteById(String id);
    public Mono<Solicitud> crearSolicitud(SolicitudDTO solicitudDTO);

}
