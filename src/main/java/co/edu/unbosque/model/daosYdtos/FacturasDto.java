package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import java.sql.Date; // Para el campo fecha

public class FacturasDto { // Nombre de clase DTO en plural
    private int idFactura;
    private int idCliente;
    private Date fecha;
    private String estadoPago; // 'estadopago' en el diagrama DTO
    private double total;      // 'total:float' en el diagrama DTO. Ver nota sobre este campo.

    public FacturasDto() {
    }

    public FacturasDto(int idFactura, int idCliente, Date fecha, String estadoPago, double total) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estadoPago = estadoPago;
        this.total = total;
    }

    // Getters y Setters
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    /**
     * Obtiene el total de la factura.
     * Nota: Este campo no existe directamente en la tabla 'facturas' del DDL.
     * Probablemente sea un valor calculado a partir de los detalles de la factura.
     * Las operaciones CRUD básicas del DAO no poblarán este campo automáticamente.
     * @return el total calculado de la factura.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el total de la factura.
     * @param total el nuevo total.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "FacturasDto{" +
               "idFactura=" + idFactura +
               ", idCliente=" + idCliente +
               ", fecha=" + fecha +
               ", estadoPago='" + estadoPago + '\'' +
               ", total=" + total +
               '}';
    }
}