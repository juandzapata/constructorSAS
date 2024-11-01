package prueba.softlond.constructorsas.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document (collection = "proyectos")
public class Proyecto {
    @Id
    private String id;

    private String nombre;
    private List<Material> recursos;
    private List<Orden> ordenes;
    private LocalDate fechaTerminacion;

    public Proyecto() {
        this.ordenes = new ArrayList<>();
    }
    public Proyecto(String nombre, ArrayList<Material> recursos) {
        this.nombre = nombre;
        this.recursos = recursos;
        this.ordenes = new ArrayList<>();
        this.fechaTerminacion = LocalDate.now();
    }

    public LocalDate getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(LocalDate fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public void iniciarMateriales(int ce, int gr, int ar, int ma, int ad){
        this.recursos = new ArrayList<>(
                Arrays.asList(
                        new Material(TipoMaterial.CE, ce),
                        new Material(TipoMaterial.GR, gr),
                        new Material(TipoMaterial.AR, ar),
                        new Material(TipoMaterial.MA, ma),
                        new Material(TipoMaterial.AD, ad)
                )
        );
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Material> getRecursos() {
        return recursos;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void addOrden(Orden orden) {
        this.ordenes.add(orden);
    }
}
