package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.DFacturaDao; // Actualizado
import co.edu.unbosque.model.daosYdtos.DFacturaDto; // Actualizado

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Dfactura { // Nombre de clase de servicio como en diagrama

    private DFacturaDao dFacturaDao;

    public Dfactura() {
        this.dFacturaDao = new DFacturaDao();
    }

    /**
     * Crea un nuevo detalle de factura.
     * @param idFactura ID de la factura a la que pertenece.
     * @param idProducto ID del producto.
     * @param idProveedor ID del proveedor (puede ser null).
     * @param idPromocion ID de la promoción aplicada (puede ser null).
     * @param tipo Tipo de ítem (ej. 'venta', 'servicio').
     * @param cantidad Cantidad del producto.
     * @param precioUnitario Precio unitario del producto.
     * @param total Total para esta línea de detalle (cantidad * precioUnitario - descuentos).
     * @return El DFacturaDto creado, o null si hubo un error.
     */
    public DFacturaDto crear_dfactura(int idFactura, int idProducto, Integer idProveedor, Integer idPromocion, String tipo, int cantidad, double precioUnitario, double total) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (idFactura <= 0 || idProducto <= 0) {
            System.err.println("Error de validación: IDs de factura o producto no válidos.");
            return null;
        }
        if (cantidad <= 0) {
            System.err.println("Error de validación: La cantidad debe ser positiva.");
            return null;
        }
        // Validar que el total sea consistente (cantidad * precioUnitario), 
        // o si el total ya incluye cálculos de descuentos por promoción.
        // double totalCalculado = cantidad * precioUnitario;
        // if (Math.abs(total - totalCalculado) > 0.001 && idPromocion == null) { // Permitir diferencia si hay promoción
        //     System.err.println("Advertencia: El total proporcionado no coincide con cantidad * precioUnitario.");
        // }
        // --- FIN LÓGICA DE NEGOCIO ---

        DFacturaDto nuevoDetalle = new DFacturaDto();
        nuevoDetalle.setIdFactura(idFactura);
        nuevoDetalle.setIdProducto(idProducto);
        nuevoDetalle.setIdProveedor(idProveedor);
        nuevoDetalle.setIdPromocion(idPromocion);
        nuevoDetalle.setTipo(tipo);
        nuevoDetalle.setCantidad(cantidad);
        nuevoDetalle.setPrecioUnitario(precioUnitario);
        nuevoDetalle.setTotal(total);

        try {
            dFacturaDao.crearDetalleFactura(nuevoDetalle);
            System.out.println("Servicio: Detalle de Factura ID " + nuevoDetalle.getIdDetalle() + " para Factura ID " + idFactura + " creado.");
            return nuevoDetalle;
        } catch (SQLException e) {
            System.err.println("Error en DFacturaService al crear detalle de factura: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un detalle de factura existente.
     * @param idDfactura ID del detalle de factura a actualizar (parámetro del diagrama).
     * @param idFactura Nuevo ID de la factura.
     * @param idProducto Nuevo ID del producto.
     * @param idProveedor Nuevo ID del proveedor (puede ser null).
     * @param idPromocion Nuevo ID de la promoción (puede ser null).
     * @param tipo Nuevo tipo.
     * @param cantidad Nueva cantidad.
     * @param precioUnitario Nuevo precio unitario.
     * @param total Nuevo total.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_dfactura(int idDfactura, int idFactura, int idProducto, Integer idProveedor, Integer idPromocion, String tipo, int cantidad, double precioUnitario, double total) {
        if (idDfactura <= 0) {
            System.err.println("Error de validación: Se requiere un ID de detalle de factura válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        DFacturaDto detalleActualizado = new DFacturaDto();
        detalleActualizado.setIdDetalle(idDfactura);
        detalleActualizado.setIdFactura(idFactura);
        detalleActualizado.setIdProducto(idProducto);
        detalleActualizado.setIdProveedor(idProveedor);
        detalleActualizado.setIdPromocion(idPromocion);
        detalleActualizado.setTipo(tipo);
        detalleActualizado.setCantidad(cantidad);
        detalleActualizado.setPrecioUnitario(precioUnitario);
        detalleActualizado.setTotal(total);
        
        try {
            return dFacturaDao.actualizarDetalleFactura(detalleActualizado);
        } catch (SQLException e) {
            System.err.println("Error en DFacturaService al actualizar detalle de factura: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un detalle de factura por su ID de detalle.
     * (Nombre de método 'ver_dfactura' y param 'id_Dfactura' del diagrama, adaptado)
     * @param id_Dfactura El ID del detalle de factura.
     * @return Un Optional que puede contener el DFacturaDto si se encuentra.
     */
    public Optional<DFacturaDto> ver_dfactura_por_id_detalle(int id_Dfactura) {
        try {
            return dFacturaDao.obtenerDetalleFacturaPorId(id_Dfactura);
        } catch (SQLException e) {
            System.err.println("Error en DFacturaService al obtener detalle de factura por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene todos los detalles asociados a un ID de factura específico.
     * @param id_Factura El ID de la factura.
     * @return Una lista de DFacturaDto.
     */
    public List<DFacturaDto> obtener_detalles_por_id_factura(int id_Factura) {
        if (id_Factura <= 0) {
             System.err.println("ID de factura no válido.");
            return Collections.emptyList();
        }
        try {
            return dFacturaDao.obtenerDetallesPorIdFactura(id_Factura);
        } catch (SQLException e) {
            System.err.println("Error en DFacturaService al obtener detalles por ID de factura: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un detalle de factura por su ID de detalle.
     * @param id_Dfactura El ID del detalle de factura a eliminar (parámetro del diagrama).
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_dfactura(int id_Dfactura) {
        try {
            return dFacturaDao.eliminarDetalleFactura(id_Dfactura);
        } catch (SQLException e) {
            System.err.println("Error en DFacturaService al eliminar detalle de factura: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina todos los detalles de factura asociados a un ID de factura específico.
     * @param id_Factura El ID de la factura.
     * @return true si se eliminaron detalles, false en caso contrario.
     */
    public boolean eliminar_dfacturas_por_id_factura(int id_Factura) {
         if (id_Factura <= 0) {
             System.err.println("ID de factura no válido para eliminar detalles.");
            return false;
        }
        try {
            return dFacturaDao.eliminarDetallesPorIdFactura(id_Factura);
        } catch (SQLException e) {
            System.err.println("Error en DFacturaService al eliminar detalles por ID de factura: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
