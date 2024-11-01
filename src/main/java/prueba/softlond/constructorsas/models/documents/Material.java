package prueba.softlond.constructorsas.models.documents;

public class Material {

    private TipoMaterial tipo;
    private double cantidad;

    public Material(TipoMaterial tipo, double cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public TipoMaterial getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }
}
