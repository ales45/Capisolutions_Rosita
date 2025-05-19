package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import java.sql.Date;

public class MoviProveInDto {
    private int idMovimientoProveedorINC; // PK de la tabla movimientosProveedoresINC
    private int idProducto;
    private int idInventario;
    private String tipoMovimiento;
    private int cantidad;
    private Date fecha;
    private String motivo;

    public MoviProveInDto() {
    }

    public MoviProveInDto(int idMovimientoProveedorINC, int idProducto, int idInventario, String tipoMovimiento, int cantidad, Date fecha, String motivo) {
        this.idMovimientoProveedorINC = idMovimientoProveedorINC;
        this.idProducto = idProducto;
        this.idInventario = idInventario;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    // Getters y Setters
    public int getIdMovimientoProveedorINC() {
        return idMovimientoProveedorINC;
    }

    public void setIdMovimientoProveedorINC(int idMovimientoProveedorINC) {
        this.idMovimientoProveedorINC = idMovimientoProveedorINC;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
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
        return "MoviProveInDto{" +
               "idMovimientoProveedorINC=" + idMovimientoProveedorINC +
               ", idProducto=" + idProducto +
               ", idInventario=" + idInventario +
               ", tipoMovimiento='" + tipoMovimiento + '\'' +
               ", cantidad=" + cantidad +
               ", fecha=" + fecha +
               ", motivo='" + motivo + '\'' +
               '}';
    }
}
