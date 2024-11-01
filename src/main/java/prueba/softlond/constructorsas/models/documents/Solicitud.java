package prueba.softlond.constructorsas.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.transform.SourceLocator;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "solicitudes")
public class Solicitud {
    @Id
    private String id;

    private Construccion construccion;
    private LocalDateTime fechaSolicitud;
    private Coordenada coordenada;

    public Solicitud(){}
    public Solicitud(Construccion construccion, LocalDateTime fechaSolicitud, Coordenada coordenada) {
        this.construccion = construccion;
        this.fechaSolicitud = fechaSolicitud;
        this.coordenada = coordenada;
    }

    public String getId() {
        return id;
    }

    public Construccion getConstruccion() {
        return construccion;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setConstruccion(Construccion construccion) {
        this.construccion = construccion;
    }
}
