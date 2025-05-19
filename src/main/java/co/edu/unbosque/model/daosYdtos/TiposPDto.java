package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

public class TiposPDto { // Nombre de clase DTO en plural
    private int idTipoP;    // Representa la PK (idProducto de la tabla CategoriaP)
    private String nombre;  // Representa la columna 'Categoria' de la tabla CategoriaP

    public TiposPDto() {
    }

    public TiposPDto(int idTipoP, String nombre) {
        this.idTipoP = idTipoP;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdTipoP() {
        return idTipoP;
    }

    public void setIdTipoP(int idTipoP) {
        this.idTipoP = idTipoP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TiposPDto{" +
               "idTipoP=" + idTipoP +
               ", nombre='" + nombre + '\'' +
               '}';
    }
}