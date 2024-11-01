package prueba.softlond.constructorsas.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import prueba.softlond.constructorsas.ConstructorSasApplication;
import prueba.softlond.constructorsas.models.documents.Estado;
import prueba.softlond.constructorsas.models.documents.Orden;
import prueba.softlond.constructorsas.models.repository.OrdenRepository;
import prueba.softlond.constructorsas.models.repository.ProyectoRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdenManagmentService  {
    private static final Logger log = LoggerFactory.getLogger(OrdenManagmentService.class);

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Scheduled(cron = "0 42 0 * * ?")
    public void actualizarOrdenesPorLaMorning() {
        log.info("Actualizando ordenes por la Morning");
        ordenRepository.findByEstado(Estado.PROGRESO)
                .switchIfEmpty(
                        ordenRepository.findFirstByEstadoOrderBySolicitud_FechaSolicitudAsc(Estado.PENDIENTE)
                                .flatMap(this::iniciarOrdenEnProgreso)
                )
                .subscribe();
    }

    @Scheduled(cron = "30 42 0 * * ?")
    public void actualizarOrdenesPorLaNoche() {
        log.info("Actualizando ordenes por noche");
        ordenRepository.findByEstado(Estado.PROGRESO)
                .flatMap(this::finalizarOrdenSiCorresponde)
                .subscribe();
    }

    private Mono<Orden> iniciarOrdenEnProgreso(Orden orden) {
        int duracionDias = orden.getSolicitud().getConstruccion().getDias();
        LocalDate fechaFinalizacion = LocalDate.now().plusDays(duracionDias);

        orden.setEstado(Estado.PROGRESO);
        orden.setFechaFinalizacion(fechaFinalizacion);

        return ordenRepository.save(orden)
                .flatMap(this::actualizarProyectoOrden);
    }

    private Mono<Orden> finalizarOrdenSiCorresponde(Orden orden) {
        if (orden.getFechaFinalizacion().equals(LocalDate.now())) {
            orden.setEstado(Estado.TERMINADO);
            return ordenRepository.save(orden)
                    .flatMap(this::actualizarProyectoOrden);
        }
        return Mono.empty();
    }

    private Mono<Orden> actualizarProyectoOrden(Orden orden) {
        return proyectoRepository.findAll()
                .filter(proyecto -> proyecto.getOrdenes().stream().anyMatch(o -> o.getId().equals(orden.getId())))
                .next()
                .flatMap(proyecto -> {
                    List<Orden> ordenes = proyecto.getOrdenes();
                    boolean ordenActualizada = false;

                    for (int i = 0; i < ordenes.size(); i++) {
                        if (ordenes.get(i).getId().equals(orden.getId())) {
                            ordenes.set(i, orden);
                            ordenActualizada = true;
                            break;
                        }
                    }

                    if (!ordenActualizada) {
                        proyecto.addOrden(orden);
                    }

                    if (orden.getEstado() == Estado.PROGRESO) {
                        proyecto.setFechaTerminacion(orden.getFechaFinalizacion());
                    }

                    return proyectoRepository.save(proyecto).thenReturn(orden);
                });
    }
}
