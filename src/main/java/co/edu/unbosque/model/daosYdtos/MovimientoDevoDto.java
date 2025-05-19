package co.edu.unbosque.model.daosYdtos;

import java.sql.Date;

public class MovimientoDevoDto {
    private int idMovimientoDevo; // Maps to idMovimientoProveedor in DDL
    private int idProducto;
    private String tipoMovimiento;
    private int cantidad;
    private Date fecha;
    private String motivo;

    public MovimientoDevoDto() {
    }

    public MovimientoDevoDto(int idMovimientoDevo, int idProducto, String tipoMovimiento, int cantidad, Date fecha, String motivo) {
        this.idMovimientoDevo = idMovimientoDevo;
        this.idProducto = idProducto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    // Getters and Setters
    public int getIdMovimientoDevo() {
        return idMovimientoDevo;
    }

    public void setIdMovimientoDevo(int idMovimientoDevo) {
        this.idMovimientoDevo = idMovimientoDevo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "MovimientoDevoDto{" +
               "idMovimientoDevo=" + idMovimientoDevo +
               ", idProducto=" + idProducto +
               ", tipoMovimiento='" + tipoMovimiento + '\'' +
               ", cantidad=" + cantidad +
               ", fecha=" + fecha +
               ", motivo='" + motivo + '\'' +
               '}';
    }
}