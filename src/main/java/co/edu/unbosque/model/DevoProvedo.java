package co.edu.unbosque.model;

import co.edu.unbosque.model.daosYdtos.DevoProveDao;
import co.edu.unbosque.model.daosYdtos.DevoProveDto;

import java.sql.SQLException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DevoProvedo {

    private DevoProveDao devoProveDao;

    public DevoProvedo() {
        this.devoProveDao = new DevoProveDao();
    }

    /**
     * Crea una nueva devolución a proveedor.
     * (Nombre de método corregido de 'crear_clientes' del diagrama)
     * @param idProveedor ID del proveedor al que se devuelve.
     * @param idProducto ID del producto devuelto.
     * @param cantidad Cantidad devuelta.
     * @param motivo Motivo de la devolución.
     * @param fecha Fecha de la devolución (parámetro 'Fecha' del diagrama).
     * @return El DevoProveDto creado, o null si hubo un error.
     */
    public DevoProveDto crear_devo_prove(int idProveedor, int idProducto, int cantidad, String motivo, java.util.Date fecha) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (idProveedor <= 0 || idProducto <= 0) {
            System.err.println("Error de validación: IDs de proveedor o producto no válidos.");
            return null;
        }
        if (cantidad <= 0) {
            System.err.println("Error de validación: La cantidad devuelta debe ser positiva.");
            return null;
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            System.err.println("Error de validación: El motivo no puede estar vacío.");
            return null;
        }
        if (fecha == null) {
            System.err.println("Error de validación: La fecha no puede ser nula.");
            return null;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        DevoProveDto nuevaDevolucion = new DevoProveDto();
        nuevaDevolucion.setIdProveedor(idProveedor);
        nuevaDevolucion.setIdProducto(idProducto);
        nuevaDevolucion.setCantidad(cantidad);
        nuevaDevolucion.setMotivo(motivo);
        nuevaDevolucion.setFecha(new Date(fecha.getTime()));

        try {
            devoProveDao.crearDevolucionProveedor(nuevaDevolucion);
            System.out.println("Servicio: Devolución a Proveedor ID " + nuevaDevolucion.getIdDevoProve() + " creada.");
            // Considerar si este movimiento debe afectar el stock en la tabla 'Inventario' (disminuyéndolo).
            // Esto requeriría lógica adicional y posiblemente el InventarioDao.
            return nuevaDevolucion;
        } catch (SQLException e) {
            System.err.println("Error en DevoProveService al crear devolución: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza una devolución a proveedor existente.
     * (Nombre de método corregido de 'actualizar_clientes' del diagrama)
     * @param id_devo_prove ID de la devolución a actualizar (parámetro 'idDevoProvedo' del diagrama, corregido).
     * @param idProveedor Nuevo ID del proveedor.
     * @param idProducto Nuevo ID del producto.
     * @param cantidad Nueva cantidad.
     * @param motivo Nuevo motivo.
     * @param fecha Nueva fecha (parámetro 'Fecha' del diagrama).
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_devo_prove(int id_devo_prove, int idProveedor, int idProducto, int cantidad, String motivo, java.util.Date fecha) {
        if (id_devo_prove <= 0) {
            System.err.println("Error de validación: Se requiere un ID de devolución válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        DevoProveDto devolucionActualizada = new DevoProveDto();
        devolucionActualizada.setIdDevoProve(id_devo_prove); // Corregido de idDevoProvedo
        devolucionActualizada.setIdProveedor(idProveedor);
        devolucionActualizada.setIdProducto(idProducto);
        devolucionActualizada.setCantidad(cantidad);
        devolucionActualizada.setMotivo(motivo);
        devolucionActualizada.setFecha(new Date(fecha.getTime()));
        
        try {
            return devoProveDao.actualizarDevolucionProveedor(devolucionActualizada);
        } catch (SQLException e) {
            System.err.println("Error en DevoProveService al actualizar devolución: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene una devolución a proveedor por su ID.
     * (Nombre de método corregido de 'ver_clientes' del diagrama)
     * @param id_devo_prove El ID de la devolución (parámetro 'id_DevoProvedo' del diagrama, corregido).
     * @return Un Optional que puede contener el DevoProveDto si se encuentra.
     */
    public Optional<DevoProveDto> ver_devo_prove_por_id(int id_devo_prove) { // Nombre de parámetro corregido
        try {
            return devoProveDao.obtenerDevolucionProveedorPorId(id_devo_prove);
        } catch (SQLException e) {
            System.err.println("Error en DevoProveService al obtener devolución por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todas las devoluciones a proveedor.
     * @return Una lista de DevoProveDto.
     */
    public List<DevoProveDto> obtener_todas_las_devo_prove() {
        try {
            return devoProveDao.obtenerTodasLasDevolucionesProveedor();
        } catch (SQLException e) {
            System.err.println("Error en DevoProveService al obtener todas las devoluciones: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina una devolución a proveedor por su ID.
     * (Nombre de método corregido de 'eliminar_clientes' y tipo de parámetro corregido)
     * @param id_devo_prove El ID de la devolución a eliminar (parámetro 'id_DevoProvedo' del diagrama, corregido a int).
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_devo_prove(int id_devo_prove) { // Tipo de parámetro corregido
        // Lógica de negocio: Al eliminar una devolución a proveedor, ¿se debe revertir el impacto en el stock?
        try {
            return devoProveDao.eliminarDevolucionProveedor(id_devo_prove);
        } catch (SQLException e) {
            System.err.println("Error en DevoProveService al eliminar devolución: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}