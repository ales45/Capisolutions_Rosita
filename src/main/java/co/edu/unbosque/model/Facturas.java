package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.FacturasDao; // Actualizado
import co.edu.unbosque.model.daosYdtos.FacturasDto; // Actualizado
// Para calcular el total, podrías necesitar DetalleFacturaDao y DetalleFacturaDto
// import co.edu.unbosque.model.daosYdtos.DetalleFacturaDao;
// import co.edu.unbosque.model.daosYdtos.DetalleFacturaDto;


import java.sql.SQLException;
import java.sql.Date; // Para el tipo de fecha
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Facturas { // Nombre de clase de servicio en plural

    private FacturasDao facturasDao;
    // private DetalleFacturaDao detalleFacturaDao; // Para calcular el total

    public Facturas() {
        this.facturasDao = new FacturasDao();
        // this.detalleFacturaDao = new DetalleFacturaDao(); // Instanciar si se usa
    }

    /**
     * Crea una nueva factura.
     * El total no se calcula/asigna aquí directamente.
     * @param idCliente El ID del cliente.
     * @param fecha La fecha de la factura.
     * @param estadoPago El estado de pago de la factura.
     * @return El FacturasDto creado (sin el total calculado), o null si hubo un error.
     */
    public FacturasDto crear_factura(int idCliente, java.util.Date fecha, String estadoPago) { // Param fecha como java.util.Date
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (idCliente <= 0) {
            System.err.println("Error de validación: El ID de cliente no es válido.");
            return null;
        }
        if (fecha == null) {
            System.err.println("Error de validación: La fecha no puede ser nula.");
            return null;
        }
        // Validar estadoPago (ej. contra una lista de estados válidos)
        // --- FIN LÓGICA DE NEGOCIO ---

        FacturasDto nuevaFactura = new FacturasDto();
        nuevaFactura.setIdCliente(idCliente);
        nuevaFactura.setFecha(new Date(fecha.getTime())); // Convertir java.util.Date a java.sql.Date
        nuevaFactura.setEstadoPago(estadoPago);
        // El total se establecería después de agregar los detalles de la factura

        try {
            facturasDao.crearFactura(nuevaFactura);
            System.out.println("Servicio: Factura ID " + nuevaFactura.getIdFactura() + " para cliente ID " + nuevaFactura.getIdCliente() + " creada.");
            return nuevaFactura;
        } catch (SQLException e) {
            System.err.println("Error en FacturasService al crear factura: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza una factura existente.
     * No actualiza el 'total' a través de este método directamente.
     * @param idfactura El ID de la factura a actualizar.
     * @param idcliente El nuevo ID del cliente.
     * @param fecha La nueva fecha de la factura.
     * @param estadopago El nuevo estado de pago.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_factura(int idfactura, int idcliente, java.util.Date fecha, String estadopago) {
        if (idfactura <= 0) {
            System.err.println("Error de validación: Se requiere un ID de factura válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        FacturasDto facturaActualizada = new FacturasDto();
        facturaActualizada.setIdFactura(idfactura);
        facturaActualizada.setIdCliente(idcliente);
        facturaActualizada.setFecha(new Date(fecha.getTime()));
        facturaActualizada.setEstadoPago(estadopago);
        
        try {
            return facturasDao.actualizarFactura(facturaActualizada);
        } catch (SQLException e) {
            System.err.println("Error en FacturasService al actualizar factura: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene una factura por su ID.
     * El DTO devuelto no tendrá el campo 'total' poblado por este método.
     * @param id_Factura El ID de la factura (parámetro como en diagrama).
     * @return Un Optional que puede contener el FacturasDto si se encuentra.
     */
    public Optional<FacturasDto> ver_factura(int id_Factura) { // Nombre de método y param como en diagrama
        try {
            Optional<FacturasDto> optFactura = facturasDao.obtenerFacturaPorId(id_Factura);
            // if (optFactura.isPresent()) {
            //     // Aquí se podría calcular y establecer el total si fuera necesario
            //     // FacturasDto factura = optFactura.get();
            //     // double totalCalculado = calcularTotalParaFactura(factura.getIdFactura());
            //     // factura.setTotal(totalCalculado);
            // }
            return optFactura;
        } catch (SQLException e) {
            System.err.println("Error en FacturasService al obtener factura por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todas las facturas.
     * Los DTOs devueltos no tendrán el campo 'total' poblado.
     * @return Una lista de FacturasDto.
     */
    public List<FacturasDto> obtener_todas_las_facturas() { // Método nuevo, nombre con guion bajo
        try {
            return facturasDao.obtenerTodasLasFacturas();
        } catch (SQLException e) {
            System.err.println("Error en FacturasService al obtener todas las facturas: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene todas las facturas de un cliente específico.
     * Los DTOs devueltos no tendrán el campo 'total' poblado.
     * @param idCliente El ID del cliente.
     * @return Una lista de FacturasDto.
     */
    public List<FacturasDto> obtener_facturas_por_cliente(int idCliente) { // Método nuevo
        if (idCliente <= 0) {
            System.err.println("ID de cliente no válido.");
            return Collections.emptyList();
        }
        try {
            return facturasDao.obtenerFacturasPorCliente(idCliente);
        } catch (SQLException e) {
            System.err.println("Error en FacturasService al obtener facturas por cliente: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina una factura por su ID.
     * @param id_Factura El ID de la factura a eliminar (parámetro como en diagrama).
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_factura(int id_Factura) { // Nombre de método y param como en diagrama
        // Lógica de negocio:
        // Antes de eliminar una factura, podrías necesitar eliminar sus detalles,
        // registros en 'Fiado', 'ventasAnuladas', etc., si no hay ON DELETE CASCADE.
        // ej. detalleFacturaDao.eliminarDetallesPorIdFactura(id_Factura);
        //     fiadoDao.eliminarFiadoPorIdFactura(id_Factura);
        //     ventasAnuladasDao.eliminarAnulacionPorIdFactura(id_Factura);
        try {
            return facturasDao.eliminarFactura(id_Factura);
        } catch (SQLException e) {
            System.err.println("Error en FacturasService al eliminar factura: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Ejemplo de cómo podrías calcular el total (necesitaría DetalleFacturaDao)
    /*
    private double calcularTotalParaFactura(int idFactura) {
        double totalGeneral = 0;
        try {
            List<DetalleFacturaDto> detalles = detalleFacturaDao.obtenerDetallesPorIdFactura(idFactura);
            for (DetalleFacturaDto detalle : detalles) {
                totalGeneral += detalle.getTotal(); // Asumiendo que DetalleFacturaDto tiene getTotal()
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular total para factura ID " + idFactura + ": " + e.getMessage());
            // Manejar el error apropiadamente
        }
        return totalGeneral;
    }
    */
}