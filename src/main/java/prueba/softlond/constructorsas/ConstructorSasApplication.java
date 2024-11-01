package prueba.softlond.constructorsas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import prueba.softlond.constructorsas.models.documents.*;
import prueba.softlond.constructorsas.models.repository.ConstruccionRepository;
import prueba.softlond.constructorsas.models.repository.ProyectoRepository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
@EnableScheduling
@SpringBootApplication
public class ConstructorSasApplication implements CommandLineRunner {
    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ConstruccionRepository construccionRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    private static final Logger log = LoggerFactory.getLogger(ConstructorSasApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConstructorSasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mongoTemplate.dropCollection("proyectos").subscribe();
        mongoTemplate.dropCollection("solicitudes").subscribe();
        mongoTemplate.dropCollection("coordenadas").subscribe();
        mongoTemplate.dropCollection("construcciones").subscribe();
        mongoTemplate.dropCollection("ordenes").subscribe();

        Flux.just(new Proyecto("Ciudadela del futuro",
                new ArrayList<>(
                        Arrays.asList(
                                new Material(TipoMaterial.CE, 10000),
                                new Material(TipoMaterial.GR, 10000),
                                new Material(TipoMaterial.AR, 10000),
                                new Material(TipoMaterial.MA, 10000),
                                new Material(TipoMaterial.AD, 10000)
                        )
                )
        ))
                .flatMap(proyecto -> proyectoRepository.save(proyecto))
                .subscribe(proyecto -> log.info("Insert: " + proyecto.getId() + " " + proyecto.getNombre()));

        Flux.just(new Construccion(TipoConstruccion.CASA),
                new Construccion(TipoConstruccion.LAGO),
                new Construccion(TipoConstruccion.CANCHA),
                new Construccion(TipoConstruccion.EDIFICIO),
                new Construccion(TipoConstruccion.GIMNASIO))
                .flatMap(construccion -> construccionRepository.save(construccion))
                .collectList()
                .subscribe(construcciones -> System.out.println("Insert: " + construcciones));
    }
}
