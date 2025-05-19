package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

public class DFacturaDto { // Singular para el DTO de la entidad
    private int idDetalle;      // PK de la tabla detalleFactura
    private int idFactura;
    private int idProducto;
    private Integer idProveedor;  // Puede ser nulo
    private Integer idPromocion;  // Puede ser nulo
    private String tipo;          // Presente en DDL, ausente en diagrama DTO
    private int cantidad;
    private double precioUnitario; // float en diagrama, DECIMAL en DDL
    private double total;          // float en diagrama, DECIMAL en DDL

    public DFacturaDto() {
    }

    public DFacturaDto(int idDetalle, int idFactura, int idProducto, Integer idProveedor, Integer idPromocion, String tipo, int cantidad, double precioUnitario, double total) {
        this.idDetalle = idDetalle;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.idPromocion = idPromocion;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    // Getters y Setters
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DFacturaDto{" +
               "idDetalle=" + idDetalle +
               ", idFactura=" + idFactura +
               ", idProducto=" + idProducto +
               ", idProveedor=" + idProveedor +
               ", idPromocion=" + idPromocion +
               ", tipo='" + tipo + '\'' +
               ", cantidad=" + cantidad +
               ", precioUnitario=" + precioUnitario +
               ", total=" + total +
               '}';
    }
}