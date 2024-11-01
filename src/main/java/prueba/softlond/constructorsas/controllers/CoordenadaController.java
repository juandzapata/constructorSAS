package prueba.softlond.constructorsas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.softlond.constructorsas.models.services.CoordenadaService;

@RestController
@RequestMapping("/api/coordenadas")
public class CoordenadaController {

    @Autowired
    private CoordenadaService coordenadaService;

}
