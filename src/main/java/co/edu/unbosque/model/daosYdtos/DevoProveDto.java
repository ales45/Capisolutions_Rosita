package co.edu.unbosque.model.daosYdtos;

import java.sql.Date;

public class DevoProveDto {
    private int idDevoProve; // Mapea a idDevolucionPK en DDL
    private int idProveedor;
    private int idProducto;
    private int cantidad;
    private String motivo;
    private Date fecha;

    public DevoProveDto() {
    }

    public DevoProveDto(int idDevoProve, int idProveedor, int idProducto, int cantidad, String motivo, Date fecha) {
        this.idDevoProve = idDevoProve;
        this.idProveedor = idProveedor;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getIdDevoProve() {
        return idDevoProve;
    }

    public void setIdDevoProve(int idDevoProve) {
        this.idDevoProve = idDevoProve;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "DevoProveDto{" +
               "idDevoProve=" + idDevoProve +
               ", idProveedor=" + idProveedor +
               ", idProducto=" + idProducto +
               ", cantidad=" + cantidad +
               ", motivo='" + motivo + '\'' +
               ", fecha=" + fecha +
               '}';
    }
}