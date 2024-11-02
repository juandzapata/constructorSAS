package prueba.softlond.constructorsas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import prueba.softlond.constructorsas.models.documents.Estado;
import prueba.softlond.constructorsas.models.documents.Orden;
import prueba.softlond.constructorsas.models.repository.OrdenRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class InformeController {

    @Autowired
    private OrdenRepository ordenRepository;

    @GetMapping("/informe")
    public String generarInforme(Model model) {
        // Obtener todas las órdenes
        Flux<Orden> todasLasOrdenes = ordenRepository.findAll();

        // Filtrar las órdenes según su estado y recolectar la cantidad por tipo
        Mono<Map<String, Long>> pendientes = todasLasOrdenes
                .filter(orden -> orden.getEstado() == Estado.PENDIENTE)
                .collect(Collectors.groupingBy(orden -> orden.getSolicitud().getConstruccion().getTipoConstruccion().name(), Collectors.counting()));

        Mono<Map<String, Long>> enProgreso = todasLasOrdenes
                .filter(orden -> orden.getEstado() == Estado.PROGRESO)
                .collect(Collectors.groupingBy(orden -> orden.getSolicitud().getConstruccion().getTipoConstruccion().name(), Collectors.counting()));

        Mono<Map<String, Long>> terminadas = todasLasOrdenes
                .filter(orden -> orden.getEstado() == Estado.TERMINADO)
                .collect(Collectors.groupingBy(orden -> orden.getSolicitud().getConstruccion().getTipoConstruccion().name(), Collectors.counting()));

        return Mono.zip(pendientes, enProgreso, terminadas)
                .doOnNext(tuple -> {
                    model.addAttribute("pendientes", tuple.getT1());
                    model.addAttribute("enProgreso", tuple.getT2());
                    model.addAttribute("terminadas", tuple.getT3());
                })
                .thenReturn("informe")
                .block();
    }
}
