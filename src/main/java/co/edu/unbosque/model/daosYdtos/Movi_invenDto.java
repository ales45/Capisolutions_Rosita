package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import java.sql.Date;

public class Movi_invenDto { // Nombre como en el diagrama
    private int idMovimiento;    // PK de la tabla movimientosInventarios
    private int idProducto;      // 'idproducto' en el diagrama DTO
    private String tipoMovimiento; // 'tipomovimiento' en el diagrama DTO
    private int cantidad;
    private Date fecha;
    private String motivo;

    public Movi_invenDto() {
    }

    public Movi_invenDto(int idMovimiento, int idProducto, String tipoMovimiento, int cantidad, Date fecha, String motivo) {
        this.idMovimiento = idMovimiento;
        this.idProducto = idProducto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    // Getters y Setters
    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
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
        return "Movi_invenDto{" +
               "idMovimiento=" + idMovimiento +
               ", idProducto=" + idProducto +
               ", tipoMovimiento='" + tipoMovimiento + '\'' +
               ", cantidad=" + cantidad +
               ", fecha=" + fecha +
               ", motivo='" + motivo + '\'' +
               '}';
    }
}