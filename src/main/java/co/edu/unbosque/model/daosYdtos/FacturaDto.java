package co.edu.unbosque.model.daosYdtos;

import java.util.Date;
import java.util.List;

public class FacturaDto {
    private int id;
    private String cliente;
    private Date fecha;
    private double total;
    private double iva;
    private String metodoPago;
    private List<DetalleFacturaDto> detalles;

    public FacturaDto() {
    }

    public FacturaDto(String cliente, Date fecha, double total, double iva, String metodoPago) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.iva = iva;
        this.metodoPago = metodoPago;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<DetalleFacturaDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFacturaDto> detalles) {
        this.detalles = detalles;
    }
} 