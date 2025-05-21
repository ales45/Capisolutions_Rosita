package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.MoviProveInDao;
import co.edu.unbosque.model.daosYdtos.MoviProveInDto;

import java.sql.SQLException;
import java.sql.Date; // Para el tipo de fecha
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MoviProveIn {

    private MoviProveInDao moviProveInDao;

    public MoviProveIn() {
        this.moviProveInDao = new MoviProveInDao();
    }

    /**
     * Crea un nuevo movimiento de incremento de proveedor.
     * (Nombre de método corregido de 'crear_factura' del diagrama)
     * @param idProducto ID del producto.
     * @param idInventario ID del inventario afectado.
     * @param tipoMovimiento Descripción del tipo de movimiento.
     * @param cantidad Cantidad del producto en el movimiento.
     * @param fecha Fecha del movimiento.
     * @param motivo Motivo del movimiento.
     * @return El MoviProveInDto creado, o null si hubo un error.
     */
    public MoviProveInDto crear_movi_prove_in( int idInventario, String tipoMovimiento, int cantidad, java.util.Date fecha, String motivo, int idProducto) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
 
        if (cantidad <= 0) {
            System.err.println("Error de validación: La cantidad debe ser positiva.");
            return null;
        }
        if (fecha == null) {
            System.err.println("Error de validación: La fecha no puede ser nula.");
            return null;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        MoviProveInDto nuevoMovimiento = new MoviProveInDto();

        nuevoMovimiento.setIdInventario(idInventario);
        nuevoMovimiento.setTipoMovimiento(tipoMovimiento);
        nuevoMovimiento.setCantidad(cantidad);
        nuevoMovimiento.setFecha(new Date(fecha.getTime())); // Convertir java.util.Date a java.sql.Date
        nuevoMovimiento.setMotivo(motivo);
        nuevoMovimiento.setIdProducto(idProducto);

        try {
            moviProveInDao.crearMoviProveIn(nuevoMovimiento);
            System.out.println("Servicio: Movimiento Proveedor INC ID " + nuevoMovimiento.getIdMovimientoProveedorINC() + " creado.");
            return nuevoMovimiento;
        } catch (SQLException e) {
            System.err.println("Error en MoviProveInService al crear movimiento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un movimiento de incremento de proveedor existente.
     * (Nombre de método corregido de 'actualizar_factura' del diagrama)
     * @param idMoviProveIn ID del movimiento a actualizar (parámetro 'idmoviprovein' del diagrama).
     * @param idProducto Nuevo ID del producto.
     * @param idInventario Nuevo ID del inventario.
     * @param tipoMovimiento Nuevo tipo de movimiento.
     * @param cantidad Nueva cantidad.
     * @param fecha Nueva fecha.
     * @param motivo Nuevo motivo.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_movi_prove_in(int idMoviProveIn, int idProducto, int idInventario, String tipoMovimiento, int cantidad, java.util.Date fecha, String motivo) {
        if (idMoviProveIn <= 0) {
            System.err.println("Error de validación: Se requiere un ID de movimiento válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        MoviProveInDto movimientoActualizado = new MoviProveInDto();
        movimientoActualizado.setIdMovimientoProveedorINC(idMoviProveIn);
        movimientoActualizado.setIdProducto(idProducto);
        movimientoActualizado.setIdInventario(idInventario);
        movimientoActualizado.setTipoMovimiento(tipoMovimiento);
        movimientoActualizado.setCantidad(cantidad);
        movimientoActualizado.setFecha(new Date(fecha.getTime()));
        movimientoActualizado.setMotivo(motivo);
        
        try {
            return moviProveInDao.actualizarMoviProveIn(movimientoActualizado);
        } catch (SQLException e) {
            System.err.println("Error en MoviProveInService al actualizar movimiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un movimiento de incremento de proveedor por su ID.
     * (Nombre de método corregido de 'ver_factura' del diagrama)
     * @param id_moviprovein El ID del movimiento (parámetro 'id_moviprovein' del diagrama).
     * @return Un Optional que puede contener el MoviProveInDto si se encuentra.
     */
    public Optional<MoviProveInDto> ver_movi_prove_in(int id_moviprovein) {
        try {
            return moviProveInDao.obtenerMoviProveInPorId(id_moviprovein);
        } catch (SQLException e) {
            System.err.println("Error en MoviProveInService al obtener movimiento por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los movimientos de incremento de proveedor.
     * @return Una lista de MoviProveInDto.
     */
    public List<MoviProveInDto> obtener_todos_los_movi_prove_in() {
        try {
            return moviProveInDao.obtenerTodosLosMoviProveIn();
        } catch (SQLException e) {
            System.err.println("Error en MoviProveInService al obtener todos los movimientos: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un movimiento de incremento de proveedor por su ID.
     * (Nombre de método corregido de 'eliminar_factura' del diagrama)
     * @param id_moviprovein El ID del movimiento a eliminar (parámetro 'id_moviprovein' del diagrama).
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_movi_prove_in(int id_moviprovein) {
        try {
            return moviProveInDao.eliminarMoviProveIn(id_moviprovein);
        } catch (SQLException e) {
            System.err.println("Error en MoviProveInService al eliminar movimiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para 'pedidos_Pendientes' según el diagrama.
     * La lógica específica para este método debe ser definida según los requerimientos de negocio.
     * Podría implicar consultar otra tabla o un estado específico de estos movimientos.
     * @return Un String representando los pedidos pendientes o un mensaje.
     */
    public String pedidos_pendientes() {
        // TODO: Implementar la lógica de negocio para determinar "pedidos pendientes".
        // Esto podría implicar:
        // 1. Consultar una tabla diferente (ej. una tabla de 'ordenes_de_compra').
        // 2. Filtrar movimientos con un 'tipoMovimiento' o 'motivo' específico que indique "pendiente".
        // Por ahora, es un placeholder.
        System.out.println("Servicio: La lógica para 'pedidos_pendientes' necesita ser implementada.");
        return "Lógica de pedidos pendientes no implementada.";
    }
}