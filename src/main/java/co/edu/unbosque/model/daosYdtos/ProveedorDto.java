package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

public class ProveedorDto {
    private int idProveedor;
    private String nombre;
    private String contacto; // VARCHAR en DDL
    private String direccion;
    private int idProducto; // FK a la tabla productos

    public ProveedorDto() {
    }

    public ProveedorDto(int idProveedor, String nombre, String contacto, String direccion, int idProducto) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = direccion;
        this.idProducto = idProducto;
    }

    // Getters y Setters
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "ProveedorDto{" +
               "idProveedor=" + idProveedor +
               ", nombre='" + nombre + '\'' +
               ", contacto='" + contacto + '\'' +
               ", direccion='" + direccion + '\'' +
               ", idProducto=" + idProducto +
               '}';
    }
}