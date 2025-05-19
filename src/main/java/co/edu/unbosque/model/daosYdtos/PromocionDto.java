package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import java.sql.Date; // Para las fechas

public class PromocionDto {
    private int idPromocion;
    private String nombre;
    private String descripcion;
    private double descuento; // Usamos double para DECIMAL(5,2)
    private Date fechaInicio;
    private Date fechaFin;
    private int idProducto;

    public PromocionDto() {
    }

    public PromocionDto(int idPromocion, String nombre, String descripcion, double descuento, Date fechaInicio, Date fechaFin, int idProducto) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idProducto = idProducto;
    }

    // Getters y Setters
    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "PromocionDto{" +
               "idPromocion=" + idPromocion +
               ", nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", descuento=" + descuento +
               ", fechaInicio=" + fechaInicio +
               ", fechaFin=" + fechaFin +
               ", idProducto=" + idProducto +
               '}';
    }
}
