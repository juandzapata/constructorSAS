package prueba.softlond.constructorsas.models.documents;

public class SolicitudDTO {
    private int x;
    private int y;
    private TipoConstruccion tipoConstruccion;
    private String proyectoId;

    public SolicitudDTO() {}
    public SolicitudDTO(int x, int y, TipoConstruccion tipo, String proyectoId) {
        this.x = x;
        this.y = y;
        this.tipoConstruccion = tipo;
        this.proyectoId = proyectoId;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TipoConstruccion getTipoConstruccion() {
        return tipoConstruccion;
    }

    public void setTipoConstruccion(TipoConstruccion tipo) {
        this.tipoConstruccion = tipo;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }
}
