package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

public class FiadoDto {
    private int idFiado;      // Corresponde a idFiadoPK en DDL
    private int idFactura;    // Corresponde a idFacturas en diagrama DTO
    private int cuotas;
    private boolean estaPagado; // Corresponde a estaPagado BOOLEAN en DDL (diagrama dice estadoPago:String)

    public FiadoDto() {
    }

    public FiadoDto(int idFiado, int idFactura, int cuotas, boolean estaPagado) {
        this.idFiado = idFiado;
        this.idFactura = idFactura;
        this.cuotas = cuotas;
        this.estaPagado = estaPagado;
    }

    // Getters y Setters
    public int getIdFiado() {
        return idFiado;
    }

    public void setIdFiado(int idFiado) {
        this.idFiado = idFiado;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public boolean isEstaPagado() { // Getter para boolean es 'is'
        return estaPagado;
    }

    public void setEstaPagado(boolean estaPagado) {
        this.estaPagado = estaPagado;
    }

    @Override
    public String toString() {
        return "FiadoDto{" +
               "idFiado=" + idFiado +
               ", idFactura=" + idFactura +
               ", cuotas=" + cuotas +
               ", estaPagado=" + estaPagado +
               '}';
    }
}