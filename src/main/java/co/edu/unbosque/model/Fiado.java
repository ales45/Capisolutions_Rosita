package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.FiadoDao;
import co.edu.unbosque.model.daosYdtos.FiadoDto;
// Podrías necesitar FacturasDao para verificar existencia o estado de factura
// import co.edu.unbosque.model.daosYdtos.FacturasDao;


import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Fiado {

    private FiadoDao fiadoDao;
    // private FacturasDao facturasDao; // Opcional

    public Fiado() {
        this.fiadoDao = new FiadoDao();
        // this.facturasDao = new FacturasDao(); // Opcional
    }

    /**
     * Crea un nuevo registro de fiado.
     * @param idFacturas El ID de la factura asociada (parámetro del diagrama).
     * @param cuotas El número de cuotas.
     * @param estaPagado El estado de pago (parámetro 'estadoPago:String' del diagrama adaptado a boolean).
     * @return El FiadoDto creado, o null si hubo un error.
     */
    public FiadoDto crear_fiado(int idFacturas, int cuotas, boolean estaPagado) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (idFacturas <= 0) {
            System.err.println("Error de validación: El ID de factura no es válido.");
            return null;
        }
        if (cuotas < 0) { // 0 cuotas podría ser pago inmediato, >0 para fiado
            System.err.println("Error de validación: El número de cuotas no puede ser negativo.");
            return null;
        }
        // Opcional: Verificar si la factura existe y está en un estado que permita fiado.
        // Optional<FacturasDto> facturaOpt = facturasDao.obtenerFacturaPorId(idFacturas);
        // if (!facturaOpt.isPresent() || !"Pendiente".equalsIgnoreCase(facturaOpt.get().getEstadoPago())) {
        //     System.err.println("La factura no existe o no está en estado para ser fiada.");
        //     return null;
        // }
        // Opcional: Verificar que no exista ya un fiado para esta factura
        // if(fiadoDao.obtenerFiadoPorIdFactura(idFacturas).isPresent()){
        //      System.err.println("Ya existe un registro de fiado para la factura ID " + idFacturas);
        //      return null;
        // }
        // --- FIN LÓGICA DE NEGOCIO ---

        FiadoDto nuevoFiado = new FiadoDto();
        nuevoFiado.setIdFactura(idFacturas);
        nuevoFiado.setCuotas(cuotas);
        nuevoFiado.setEstaPagado(estaPagado);

        try {
            fiadoDao.crearFiado(nuevoFiado);
            System.out.println("Servicio: Fiado registrado para Factura ID " + nuevoFiado.getIdFactura() + " con ID Fiado: " + nuevoFiado.getIdFiado());
            return nuevoFiado;
        } catch (SQLException e) {
            System.err.println("Error en FiadoService al crear fiado: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un registro de fiado existente.
     * @param idFiado El ID del registro de fiado a actualizar.
     * @param idFacturas El nuevo ID de la factura (generalmente no debería cambiar).
     * @param cuotas El nuevo número de cuotas.
     * @param estaPagado El nuevo estado de pago.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_fiado(int idFiado, int idFacturas, int cuotas, boolean estaPagado) {
        if (idFiado <= 0) {
            System.err.println("Error de validación: Se requiere un ID de fiado válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        FiadoDto fiadoActualizado = new FiadoDto();
        fiadoActualizado.setIdFiado(idFiado);
        fiadoActualizado.setIdFactura(idFacturas);
        fiadoActualizado.setCuotas(cuotas);
        fiadoActualizado.setEstaPagado(estaPagado);
        
        try {
            // Opcional: verificar si el registro de fiado existe
            // if(!fiadoDao.obtenerFiadoPorId(idFiado).isPresent()){
            //    System.err.println("Registro de fiado con ID " + idFiado + " no encontrado.");
            //    return false;
            // }
            return fiadoDao.actualizarFiado(fiadoActualizado);
        } catch (SQLException e) {
            System.err.println("Error en FiadoService al actualizar fiado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un registro de fiado por su ID.
     * (Nombre de método 'ver_fiado' y param 'id_fiado' del diagrama, adaptado)
     * @param id_fiado El ID del registro de fiado.
     * @return Un Optional que puede contener el FiadoDto si se encuentra.
     */
    public Optional<FiadoDto> ver_fiado_por_id(int id_fiado) {
        try {
            return fiadoDao.obtenerFiadoPorId(id_fiado);
        } catch (SQLException e) {
            System.err.println("Error en FiadoService al obtener fiado por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Obtiene un registro de fiado por el ID de la factura asociada.
     * @param id_factura El ID de la factura.
     * @return Un Optional que puede contener el FiadoDto si se encuentra.
     */
    public Optional<FiadoDto> ver_fiado_por_id_factura(int id_factura) {
        try {
            return fiadoDao.obtenerFiadoPorIdFactura(id_factura);
        } catch (SQLException e) {
            System.err.println("Error en FiadoService al obtener fiado por ID de factura: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los registros de fiado.
     * @return Una lista de FiadoDto.
     */
    public List<FiadoDto> obtener_todos_los_fiados() {
        try {
            return fiadoDao.obtenerTodosLosFiados();
        } catch (SQLException e) {
            System.err.println("Error en FiadoService al obtener todos los fiados: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un registro de fiado por su ID.
     * @param id_fiado El ID del registro de fiado a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_fiado(int id_fiado) {
        try {
            return fiadoDao.eliminarFiado(id_fiado);
        } catch (SQLException e) {
            System.err.println("Error en FiadoService al eliminar fiado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para 'Creditos_Vencidos' según el diagrama.
     * La lógica específica para este método debe ser definida.
     * Requeriría información de fechas de pago o plazos, que no está en la tabla Fiado actual.
     * @return Un String representando los créditos vencidos o un mensaje.
     */
    public String creditos_vencidos() { // Nombre de método como en el diagrama
        // TODO: Implementar la lógica de negocio para determinar "Créditos Vencidos".
        // Esto es complejo y depende de cómo se gestionen los plazos y pagos de las cuotas.
        // La tabla 'Fiado' actual solo tiene 'cuotas' y 'estaPagado'.
        // Se necesitaría información adicional como:
        // 1. Fecha de inicio del crédito (podría ser la fecha de la factura).
        // 2. Frecuencia de pago de cuotas (ej. mensual).
        // 3. Una tabla de pagos realizados por cuota.
        //
        // Ejemplo conceptual (no funcional con el DDL actual):
        // List<FiadoDto> todosLosFiados = obtener_todos_los_fiados();
        // StringBuilder vencidos = new StringBuilder("Créditos Vencidos:\n");
        // Date hoy = new Date(System.currentTimeMillis());
        // for (FiadoDto fiado : todosLosFiados) {
        //     if (!fiado.isEstaPagado()) {
        //         // Lógica para determinar si está vencido basado en fiado.getCuotas(),
        //         // la fecha de la factura (necesitaría FacturasDao), y la fecha actual.
        //         // if (estaVencido(fiado, facturasDao.obtenerFacturaPorId(fiado.getIdFactura()).get().getFecha(), hoy)) {
        //         //     vencidos.append("Fiado ID: ").append(fiado.getIdFiado()).append(" para Factura ID: ").append(fiado.getIdFactura()).append("\n");
        //         // }
        //     }
        // }
        System.out.println("Servicio: La lógica para 'creditos_vencidos' necesita ser implementada detalladamente.");
        return "Lógica de créditos vencidos no implementada. Requiere definición de plazos y fechas de pago.";
    }
}