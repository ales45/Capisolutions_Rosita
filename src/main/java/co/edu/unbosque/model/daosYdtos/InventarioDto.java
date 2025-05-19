package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import java.sql.Timestamp;

public class InventarioDto {
    private int idInventario;
    private int idProducto;
    private int stock;
    private int stockMinimo;
    private String ubicacion;
    private Timestamp ultimaActualizacion;

    public InventarioDto() {
    }

    public InventarioDto(int idInventario, int idProducto, int stock, int stockMinimo, String ubicacion, Timestamp ultimaActualizacion) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.ubicacion = ubicacion;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    // Getters y Setters
    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Timestamp getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Timestamp ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    @Override
    public String toString() {
        return "InventarioDto{" +
               "idInventario=" + idInventario +
               ", idProducto=" + idProducto +
               ", stock=" + stock +
               ", stockMinimo=" + stockMinimo +
               ", ubicacion='" + ubicacion + '\'' +
               ", ultimaActualizacion=" + ultimaActualizacion +
               '}';
    }
}