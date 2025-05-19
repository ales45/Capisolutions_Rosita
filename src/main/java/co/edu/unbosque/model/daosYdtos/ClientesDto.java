package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

public class ClientesDto { // Nombre de clase DTO en plural
    private int idCliente;
    private String nombre;
    private String rol;
    private String correo;
    private long cedula;    // BIGINT en DDL se mapea a long en Java
    private long telefono;  // BIGINT en DDL se mapea a long en Java

    public ClientesDto() {
    }

    public ClientesDto(int idCliente, String nombre, String rol, String correo, long cedula, long telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "ClientesDto{" +
               "idCliente=" + idCliente +
               ", nombre='" + nombre + '\'' +
               ", rol='" + rol + '\'' +
               ", correo='" + correo + '\'' +
               ", cedula=" + cedula +
               ", telefono=" + telefono +
               '}';
    }
}
