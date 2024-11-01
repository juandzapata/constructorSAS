package prueba.softlond.constructorsas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.softlond.constructorsas.exceptions.ConstruccionDesconocidaException;
import prueba.softlond.constructorsas.exceptions.CoordenadaEnUsoException;
import prueba.softlond.constructorsas.models.documents.Solicitud;
import prueba.softlond.constructorsas.models.documents.SolicitudDTO;
import prueba.softlond.constructorsas.models.services.SolicitudService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping
    public Mono<ResponseEntity<Flux<Solicitud>>> findAll() {
        return Mono.just(ResponseEntity.ok(solicitudService.findAll()));
    }

    @PostMapping
    public Mono<ResponseEntity<Object>> crearSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        return solicitudService.crearSolicitud(solicitudDTO)
                .map(solicitud -> ResponseEntity.status(HttpStatus.CREATED).body((Object) solicitud))  // Cast a Object para mantener compatibilidad
                .onErrorResume(e -> {
                    if (e instanceof CoordenadaEnUsoException) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Error: Las coordenadas ya están en uso."));
                    } else if (e instanceof ConstruccionDesconocidaException) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Error: Tipo de construcción desconocido."));
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error: Ocurrió un error inesperado."));
                });
    }
}
