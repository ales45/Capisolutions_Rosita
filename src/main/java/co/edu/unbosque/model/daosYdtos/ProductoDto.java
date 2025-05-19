package co.edu.unbosque.model.daosYdtos;


public class ProductoDto {
    private int idProducto;
    private String nombre;
    private int idCategoriaP; // Corresponde a la FK en la tabla productos
    private String descripcion;
    private double precio;
    private double iva;

    public ProductoDto() {
    }

    public ProductoDto(int idProducto, String nombre, int idCategoriaP, String descripcion, double precio, double iva) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.idCategoriaP = idCategoriaP;
        this.descripcion = descripcion;
        this.precio = precio;
        this.iva = iva;
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCategoriaP() {
        return idCategoriaP;
    }

    public void setIdCategoriaP(int idCategoriaP) {
        this.idCategoriaP = idCategoriaP;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    @Override
    public String toString() {
        return "ProductoDto{" +
               "idProducto=" + idProducto +
               ", nombre='" + nombre + '\'' +
               ", idCategoriaP=" + idCategoriaP +
               ", descripcion='" + descripcion + '\'' +
               ", precio=" + precio +
               ", iva=" + iva +
               '}';
    }
}
