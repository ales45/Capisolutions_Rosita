package co.edu.unbosque.model;

import co.edu.unbosque.model.daosYdtos.MovimientoDevoDao;
import co.edu.unbosque.model.daosYdtos.MovimientoDevoDto;

import java.sql.SQLException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MovimientoDevo {

    private MovimientoDevoDao movimientoDevoDao;

    public MovimientoDevo() {
        this.movimientoDevoDao = new MovimientoDevoDao();
    }

    /**
     * Crea un nuevo movimiento de devolución.
     * @param idProducto ID del producto devuelto.
     * @param tipomovimiento Tipo de movimiento (ej. 'DEVOLUCION_CLIENTE', 'DEVOLUCION_PROVEEDOR_SALIDA').
     * @param cantidad Cantidad devuelta.
     * @param fecha Fecha de la devolución.
     * @param motivo Motivo de la devolución.
     * @return El MovimientoDevoDto creado, o null si hubo un error.
     */
    public MovimientoDevoDto crear_movimiento_devo(int idProducto, String tipomovimiento, int cantidad, java.util.Date fecha, String motivo) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (idProducto <= 0) {
            System.err.println("Error de validación: ID de producto no válido.");
            return null;
        }
        if (cantidad <= 0) {
            System.err.println("Error de validación: La cantidad devuelta debe ser positiva.");
            return null;
        }
        if (tipomovimiento == null || tipomovimiento.trim().isEmpty()) {
            System.err.println("Error de validación: El tipo de movimiento no puede estar vacío.");
            return null;
        }
        if (fecha == null) {
            System.err.println("Error de validación: La fecha no puede ser nula.");
            return null;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        MovimientoDevoDto nuevoMovimiento = new MovimientoDevoDto();
        nuevoMovimiento.setIdProducto(idProducto);
        nuevoMovimiento.setTipoMovimiento(tipomovimiento);
        nuevoMovimiento.setCantidad(cantidad);
        nuevoMovimiento.setFecha(new Date(fecha.getTime()));
        nuevoMovimiento.setMotivo(motivo);

        try {
            movimientoDevoDao.crearMovimientoDevolucion(nuevoMovimiento);
            System.out.println("Servicio: Movimiento de Devolución ID " + nuevoMovimiento.getIdMovimientoDevo() + " creado.");
            // Considerar actualizar el stock en la tabla 'Inventario' si la devolución afecta el stock disponible.
            // Por ejemplo, si es una devolución de cliente que reingresa a stock.
            // Esto requeriría lógica adicional y posiblemente el InventarioDao.
            return nuevoMovimiento;
        } catch (SQLException e) {
            System.err.println("Error en MovimientoDevoService al crear movimiento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un movimiento de devolución existente.
     * @param idmovimientoDevo ID del movimiento de devolución a actualizar.
     * @param idProducto Nuevo ID del producto.
     * @param tipomovimiento Nuevo tipo de movimiento.
     * @param cantidad Nueva cantidad.
     * @param fecha Nueva fecha.
     * @param motivo Nuevo motivo.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_movimiento_devo(int idmovimientoDevo, int idProducto, String tipomovimiento, int cantidad, java.util.Date fecha, String motivo) {
        if (idmovimientoDevo <= 0) {
            System.err.println("Error de validación: Se requiere un ID de movimiento de devolución válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        MovimientoDevoDto movimientoActualizado = new MovimientoDevoDto();
        movimientoActualizado.setIdMovimientoDevo(idmovimientoDevo);
        movimientoActualizado.setIdProducto(idProducto);
        movimientoActualizado.setTipoMovimiento(tipomovimiento);
        movimientoActualizado.setCantidad(cantidad);
        movimientoActualizado.setFecha(new Date(fecha.getTime()));
        movimientoActualizado.setMotivo(motivo);
        
        try {
            return movimientoDevoDao.actualizarMovimientoDevolucion(movimientoActualizado);
        } catch (SQLException e) {
            System.err.println("Error en MovimientoDevoService al actualizar movimiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un movimiento de devolución por su ID.
     * @param id_movimientoDevo El ID del movimiento de devolución.
     * @return Un Optional que puede contener el MovimientoDevoDto si se encuentra.
     */
    public Optional<MovimientoDevoDto> ver_movimiento_devo(int id_movimientoDevo) {
        try {
            return movimientoDevoDao.obtenerMovimientoDevolucionPorId(id_movimientoDevo);
        } catch (SQLException e) {
            System.err.println("Error en MovimientoDevoService al obtener movimiento por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los movimientos de devolución.
     * @return Una lista de MovimientoDevoDto.
     */
    public List<MovimientoDevoDto> obtener_todos_los_movimientos_devo() {
        try {
            return movimientoDevoDao.obtenerTodosLosMovimientosDevolucion();
        } catch (SQLException e) {
            System.err.println("Error en MovimientoDevoService al obtener todos los movimientos: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un movimiento de devolución por su ID.
     * @param id_movimientoDevo El ID del movimiento a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_movimiento_devo(int id_movimientoDevo) {
        // Lógica de negocio: Al eliminar un movimiento de devolución, ¿se debe revertir el impacto en el stock?
        // Similar a la creación, esto es una consideración importante.
        try {
            return movimientoDevoDao.eliminarMovimientoDevolucion(id_movimientoDevo);
        } catch (SQLException e) {
            System.err.println("Error en MovimientoDevoService al eliminar movimiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
