package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.Movi_invenDao; // Actualizado
import co.edu.unbosque.model.daosYdtos.Movi_invenDto; // Actualizado
// Para Producto_MasVendido, podrías necesitar ProductoDao/Dto si quieres devolver más que el ID.
// import co.edu.unbosque.model.daosYdtos.ProductoDao;
// import co.edu.unbosque.model.daosYdtos.ProductoDto;


import java.sql.SQLException;
import java.sql.Date; // Para el tipo de fecha
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Movi_Invento { // Nombre de clase con guion bajo como en el diagrama

    private Movi_invenDao movi_invenDao;
    // private ProductoDao productoDao; // Opcional para Producto_MasVendido

    public Movi_Invento() {
        this.movi_invenDao = new Movi_invenDao();
        // this.productoDao = new ProductoDao(); // Opcional
    }

    /**
     * Crea un nuevo movimiento de inventario.
     * @param idproducto ID del producto.
     * @param tipoMovimiento Descripción del tipo de movimiento.
     * @param cantidad Cantidad del producto en el movimiento.
     * @param fecha Fecha del movimiento.
     * @param motivo Motivo del movimiento.
     * @return El Movi_invenDto creado, o null si hubo un error.
     */
    public Movi_invenDto crear_movi_inven(int idproducto, String tipoMovimiento, int cantidad, java.util.Date fecha, String motivo) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (idproducto <= 0) {
            System.err.println("Error de validación: ID de producto no válido.");
            return null;
        }
        // La cantidad puede ser negativa para salidas de inventario, o positiva para entradas.
        // if (cantidad == 0) {
        //     System.err.println("Error de validación: La cantidad no puede ser cero.");
        //     return null;
        // }
        if (fecha == null) {
            System.err.println("Error de validación: La fecha no puede ser nula.");
            return null;
        }
        if (tipoMovimiento == null || tipoMovimiento.trim().isEmpty()) {
            System.err.println("Error de validación: El tipo de movimiento no puede estar vacío.");
            return null;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        Movi_invenDto nuevoMovimiento = new Movi_invenDto();
        nuevoMovimiento.setIdProducto(idproducto);
        nuevoMovimiento.setTipoMovimiento(tipoMovimiento);
        nuevoMovimiento.setCantidad(cantidad);
        nuevoMovimiento.setFecha(new Date(fecha.getTime())); // Convertir java.util.Date a java.sql.Date
        nuevoMovimiento.setMotivo(motivo);

        try {
            movi_invenDao.crearMoviInven(nuevoMovimiento);
            System.out.println("Servicio: Movimiento de inventario ID " + nuevoMovimiento.getIdMovimiento() + " creado.");
            // Aquí podrías actualizar el stock en la tabla Inventario si esta tabla movimientosInventarios
            // no lo hace automáticamente mediante triggers o si esta es la fuente principal.
            // Esto requeriría InventarioDao.
            return nuevoMovimiento;
        } catch (SQLException e) {
            System.err.println("Error en Movi_inventoService al crear movimiento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un movimiento de inventario existente.
     * @param idmovi_invento ID del movimiento a actualizar (parámetro del diagrama).
     * @param idproducto Nuevo ID del producto.
     * @param tipoMovimiento Nuevo tipo de movimiento.
     * @param cantidad Nueva cantidad.
     * @param fecha Nueva fecha.
     * @param motivo Nuevo motivo.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_movi_inven(int idmovi_invento, int idproducto, String tipoMovimiento, int cantidad, java.util.Date fecha, String motivo) {
        if (idmovi_invento <= 0) {
            System.err.println("Error de validación: Se requiere un ID de movimiento válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        Movi_invenDto movimientoActualizado = new Movi_invenDto();
        movimientoActualizado.setIdMovimiento(idmovi_invento);
        movimientoActualizado.setIdProducto(idproducto);
        movimientoActualizado.setTipoMovimiento(tipoMovimiento);
        movimientoActualizado.setCantidad(cantidad);
        movimientoActualizado.setFecha(new Date(fecha.getTime()));
        movimientoActualizado.setMotivo(motivo);
        
        try {
            return movi_invenDao.actualizarMoviInven(movimientoActualizado);
        } catch (SQLException e) {
            System.err.println("Error en Movi_inventoService al actualizar movimiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un movimiento de inventario por su ID.
     * (Nombre de método 'ver_Movi_inven' y param 'idmovi_invento' del diagrama, adaptado)
     * @param idmovi_invento El ID del movimiento.
     * @return Un Optional que puede contener el Movi_invenDto si se encuentra.
     */
    public Optional<Movi_invenDto> ver_movi_inven_por_id(int idmovi_invento) {
        try {
            return movi_invenDao.obtenerMoviInvenPorId(idmovi_invento);
        } catch (SQLException e) {
            System.err.println("Error en Movi_inventoService al obtener movimiento por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los movimientos de inventario.
     * @return Una lista de Movi_invenDto.
     */
    public List<Movi_invenDto> obtener_todos_los_movi_inven() {
        try {
            return movi_invenDao.obtenerTodosLosMoviInven();
        } catch (SQLException e) {
            System.err.println("Error en Movi_inventoService al obtener todos los movimientos: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    /**
     * Obtiene todos los movimientos de inventario para un producto específico.
     * @param id_producto El ID del producto.
     * @return Una lista de Movi_invenDto.
     */
    public List<Movi_invenDto> obtener_movi_inven_por_producto(int id_producto) {
        if (id_producto <= 0) {
            System.err.println("ID de producto no válido.");
            return Collections.emptyList();
        }
        try {
            return movi_invenDao.obtenerMoviInvenPorProducto(id_producto);
        } catch (SQLException e) {
            System.err.println("Error en Movi_inventoService al obtener movimientos por producto: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un movimiento de inventario por su ID.
     * @param id_movi_invento El ID del movimiento a eliminar (parámetro del diagrama).
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_movi_inven(int id_movi_invento) {
        // Lógica de negocio: Al eliminar un movimiento, ¿se debe revertir el impacto en el stock de la tabla Inventario?
        // Esto requeriría obtener el movimiento antes de eliminarlo para saber la cantidad y el producto,
        // y luego actualizar la tabla Inventario.
        try {
            return movi_invenDao.eliminarMoviInven(id_movi_invento);
        } catch (SQLException e) {
            System.err.println("Error en Movi_inventoService al eliminar movimiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para 'Producto_MasVendido' según el diagrama.
     * La lógica específica para este método debe ser definida.
     * Podría implicar analizar esta tabla 'movimientosInventarios' (si 'tipoMovimiento' indica ventas)
     * o, más comúnmente, la tabla 'detalleFactura'.
     * @return Un String representando el producto más vendido o un mensaje.
     */
    public String producto_mas_vendido() { // Nombre de método como en el diagrama
        // TODO: Implementar la lógica de negocio para determinar el "Producto Más Vendido".
        // Esto es complejo y depende de cómo se registran las ventas:
        // 1. Si las ventas se deducen de 'movimientosInventarios' (ej. tipoMovimiento='VENTA'):
        //    - SELECT idProducto, SUM(cantidad) AS totalVendido FROM movimientosInventarios
        //      WHERE tipoMovimiento = 'VENTA' (o el que corresponda a una salida por venta)
        //      AND cantidad < 0  -- O si cantidad es siempre positiva y tipoMovimiento lo define
        //      GROUP BY idProducto ORDER BY totalVendido DESC LIMIT 1;
        // 2. O, más probablemente, se debería consultar la tabla 'detalleFactura':
        //    - SELECT idProducto, SUM(cantidad) AS totalVendido FROM detalleFactura
        //      GROUP BY idProducto ORDER BY totalVendido DESC LIMIT 1;
        //
        // Una vez obtenido el idProducto, se podría usar ProductoDao para obtener el nombre.
        System.out.println("Servicio: La lógica para 'producto_mas_vendido' necesita ser implementada detalladamente.");
        return "Lógica de producto más vendido no implementada. Requiere análisis de ventas.";
    }
}