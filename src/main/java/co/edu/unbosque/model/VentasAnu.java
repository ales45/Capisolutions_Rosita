package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.VentasAnuDao;
import co.edu.unbosque.model.daosYdtos.VentasAnuDto;
// Podrías necesitar FacturasDao para verificar si la factura existe o para cambiar su estado
// import co.edu.unbosque.model.daosYdtos.FacturasDao;


import java.sql.SQLException;
import java.sql.Date; // Para el tipo de fecha
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class VentasAnu {

    private VentasAnuDao ventasAnuDao;
    // private FacturasDao facturasDao; // Opcional, para lógica de negocio adicional

    public VentasAnu() {
        this.ventasAnuDao = new VentasAnuDao();
        // this.facturasDao = new FacturasDao(); // Opcional
    }

    /**
     * Crea un nuevo registro de venta anulada.
     * @param id_Factura El ID de la factura que se anula.
     * @param motivo El motivo de la anulación.
     * @param fechaAnulacion La fecha en que se realiza la anulación.
     * @param idCliente El ID del cliente asociado a la factura anulada.
     * @return El VentasAnuDto creado, o null si hubo un error.
     */
    public VentasAnuDto crear_ventas_anu(int id_Factura, String motivo, java.util.Date fechaAnulacion, int idCliente) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (id_Factura <= 0) {
            System.err.println("Error de validación: El ID de factura no es válido.");
            return null;
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            System.err.println("Error de validación: El motivo no puede estar vacío.");
            return null;
        }
        if (fechaAnulacion == null) {
            System.err.println("Error de validación: La fecha de anulación no puede ser nula.");
            return null;
        }
        // Opcional: Verificar si la factura existe (usando facturasDao)
        // Opcional: Verificar si ya existe una anulación para esta factura
        // Optional<VentasAnuDto> existente = ver_ventas_anu_por_id_factura(id_Factura);
        // if (existente.isPresent()) {
        //     System.err.println("Error: Ya existe una anulación para la factura ID " + id_Factura);
        //     return existente.get(); // O null, o manejar como error
        // }
        // Opcional: Cambiar el estado de la factura original a "Anulada" (usando facturasDao)
        // --- FIN LÓGICA DE NEGOCIO ---

        VentasAnuDto nuevaVentaAnulada = new VentasAnuDto();
        nuevaVentaAnulada.setIdFactura(id_Factura);
        nuevaVentaAnulada.setMotivo(motivo);
        nuevaVentaAnulada.setFechaAnulacion(new Date(fechaAnulacion.getTime()));
        nuevaVentaAnulada.setIdCliente(idCliente); // El idCliente también está en la factura original

        try {
            ventasAnuDao.crearVentaAnulada(nuevaVentaAnulada);
            System.out.println("Servicio: Venta Anulada registrada para Factura ID " + nuevaVentaAnulada.getIdFactura() + " con ID Anulación: " + nuevaVentaAnulada.getIdAnulacion());
            return nuevaVentaAnulada;
        } catch (SQLException e) {
            System.err.println("Error en VentasAnuService al crear venta anulada: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un registro de venta anulada existente.
     * @param idVentasAnu El ID del registro de anulación a actualizar.
     * @param id_Factura El nuevo ID de factura (generalmente no debería cambiar).
     * @param motivo El nuevo motivo.
     * @param fechaAnulacion La nueva fecha de anulación.
     * @param idCliente El nuevo ID de cliente (generalmente no debería cambiar).
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_ventas_anu(int idVentasAnu, int id_Factura, String motivo, java.util.Date fechaAnulacion, int idCliente) {
        if (idVentasAnu <= 0) {
            System.err.println("Error de validación: Se requiere un ID de anulación válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        VentasAnuDto ventaAnuladaActualizada = new VentasAnuDto();
        ventaAnuladaActualizada.setIdAnulacion(idVentasAnu);
        ventaAnuladaActualizada.setIdFactura(id_Factura);
        ventaAnuladaActualizada.setMotivo(motivo);
        ventaAnuladaActualizada.setFechaAnulacion(new Date(fechaAnulacion.getTime()));
        ventaAnuladaActualizada.setIdCliente(idCliente);
        
        try {
            return ventasAnuDao.actualizarVentaAnulada(ventaAnuladaActualizada);
        } catch (SQLException e) {
            System.err.println("Error en VentasAnuService al actualizar venta anulada: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un registro de venta anulada por su ID de anulación.
     * (Nombre de método 'ver_VentasAnu' del diagrama, adaptado para ID de anulación)
     * @param id_ventasAnu El ID del registro de anulación.
     * @return Un Optional que puede contener el VentasAnuDto si se encuentra.
     */
    public Optional<VentasAnuDto> ver_ventas_anu_por_id_anulacion(int id_ventasAnu) {
        try {
            return ventasAnuDao.obtenerVentaAnuladaPorIdAnulacion(id_ventasAnu);
        } catch (SQLException e) {
            System.err.println("Error en VentasAnuService al obtener venta anulada por ID de anulación: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Obtiene un registro de venta anulada por el ID de la factura asociada.
     * @param id_Factura El ID de la factura.
     * @return Un Optional que puede contener el VentasAnuDto si se encuentra.
     */
    public Optional<VentasAnuDto> ver_ventas_anu_por_id_factura(int id_Factura) {
        try {
            return ventasAnuDao.obtenerVentaAnuladaPorIdFactura(id_Factura);
        } catch (SQLException e) {
            System.err.println("Error en VentasAnuService al obtener venta anulada por ID de factura: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los registros de ventas anuladas.
     * @return Una lista de VentasAnuDto.
     */
    public List<VentasAnuDto> obtener_todas_las_ventas_anu() {
        try {
            return ventasAnuDao.obtenerTodasLasVentasAnuladas();
        } catch (SQLException e) {
            System.err.println("Error en VentasAnuService al obtener todas las ventas anuladas: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un registro de venta anulada por su ID de anulación.
     * @param id_ventasAnu El ID del registro de anulación a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_ventas_anu(int id_ventasAnu) {
        // Lógica de negocio:
        // Al eliminar un registro de anulación, ¿debería cambiar el estado de la factura original?
        // ej. facturasDao.actualizarEstadoFactura(ventaAnulada.getIdFactura(), "Activa");
        try {
            return ventasAnuDao.eliminarVentaAnulada(id_ventasAnu);
        } catch (SQLException e) {
            System.err.println("Error en VentasAnuService al eliminar venta anulada: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}