package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import java.sql.Date;

public class VentasAnuDto {
    private int idAnulacion; // PK de la tabla ventasAnuladas
    private int idFactura;   // Corresponde a id_Factura en el diagrama DTO
    private String motivo;
    private Date fechaAnulacion;
    private int idCliente;

    public VentasAnuDto() {
    }

    public VentasAnuDto(int idAnulacion, int idFactura, String motivo, Date fechaAnulacion, int idCliente) {
        this.idAnulacion = idAnulacion;
        this.idFactura = idFactura;
        this.motivo = motivo;
        this.fechaAnulacion = fechaAnulacion;
        this.idCliente = idCliente;
    }

    // Getters y Setters
    public int getIdAnulacion() {
        return idAnulacion;
    }

    public void setIdAnulacion(int idAnulacion) {
        this.idAnulacion = idAnulacion;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "VentasAnuDto{" +
               "idAnulacion=" + idAnulacion +
               ", idFactura=" + idFactura +
               ", motivo='" + motivo + '\'' +
               ", fechaAnulacion=" + fechaAnulacion +
               ", idCliente=" + idCliente +
               '}';
    }
}