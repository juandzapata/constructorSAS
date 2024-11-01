package prueba.softlond.constructorsas.models.documents;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "construcciones")
public class Construccion {

    @Id
    private String id;

    private TipoConstruccion tipoConstruccion;
    private List<Material> materiales;
    private int dias;

    public Construccion(TipoConstruccion tipoConstruccion) {
        this.tipoConstruccion = tipoConstruccion;
        inicializarConstruccion();
    }

    private void inicializarConstruccion() {
        switch (this.tipoConstruccion) {
            case CASA -> asignarValores(100, 50, 90, 20, 100, 3);
            case LAGO -> asignarValores(50, 60, 80, 10, 20, 2);
            case CANCHA -> asignarValores(20, 20, 20, 20, 20, 1);
            case EDIFICIO -> asignarValores(200, 100, 180, 40, 200, 6);
            case GIMNASIO -> asignarValores(50, 25, 45, 10, 50, 2);
            case null -> {
                return;
            }
        }
    }

    private void asignarValores(int ce, int gr, int ar, int ma, int ad, int dias){
        this.materiales = new ArrayList<>();
        this.materiales.add(new Material(TipoMaterial.CE, ce));
        this.materiales.add(new Material(TipoMaterial.GR, gr));
        this.materiales.add(new Material(TipoMaterial.AR, ar));
        this.materiales.add(new Material(TipoMaterial.MA, ma));
        this.materiales.add(new Material(TipoMaterial.AD, ad));

        this.dias = dias;
    }

    public String getId() {
        return id;
    }

    public TipoConstruccion getTipoConstruccion() {
        return tipoConstruccion;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public int getDias() {
        return dias;
    }
}
