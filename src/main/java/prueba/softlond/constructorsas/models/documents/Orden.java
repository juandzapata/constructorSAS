package prueba.softlond.constructorsas.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document (collection = "ordenes")
public class Orden {
    @Id
    private String id;

    private Solicitud solicitud;
    private Estado estado;
    private LocalDate fechaFinalizacion;

    public Orden(){}
    public Orden(Solicitud solicitud) {
        this.solicitud = solicitud;
        this.estado = Estado.PENDIENTE;
        this.fechaFinalizacion = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public Estado getEstado() {
        return estado;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
