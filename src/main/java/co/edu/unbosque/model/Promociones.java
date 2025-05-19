package co.edu.unbosque.model; // Asumiendo el mismo paquete que las otras clases de servicio

import co.edu.unbosque.model.daosYdtos.PromocionDao;
import co.edu.unbosque.model.daosYdtos.PromocionDto;

import java.sql.SQLException;
import java.sql.Date; // Para las fechas
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Promociones { // Nombre de la clase 'Promociones' tomado del diagrama

    private PromocionDao promocionDao;

    public Promociones() {
        this.promocionDao = new PromocionDao();
    }

    /**
     * Crea una nueva promoción.
     * Parámetros basados en el diagrama de clases.
     * @param nombre El nombre de la promoción.
     * @param descripcion La descripción de la promoción.
     * @param descuento El porcentaje de descuento (ej. 0.1 para 10%).
     * @param fechaInicio La fecha de inicio de la promoción.
     * @param fechaFin La fecha de fin de la promoción.
     * @param idProducto El ID del producto asociado a la promoción.
     * @return El PromocionDto de la promoción creada (con su ID generado), o null si hubo un error.
     */
    public PromocionDto crearPromocion(String nombre, String descripcion, double descuento, java.util.Date fechaInicio, java.util.Date fechaFin, int idProducto) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre de la promoción no puede estar vacío.");
            return null;
        }
        if (descuento <= 0 || descuento >= 1) { // Asumiendo que descuento es un factor (ej. 0.1 para 10%)
             System.err.println("Error de validación: El descuento debe ser un valor entre 0 y 1 (ej. 0.1 para 10%).");
             // O si es un porcentaje directo (10 para 10%), ajustar la validación.
             // return null; // Descomentar si se aplica esta validación estricta.
        }
        if (fechaInicio == null || fechaFin == null || fechaInicio.after(fechaFin)) {
            System.err.println("Error de validación: Las fechas de inicio y fin no son válidas o la fecha de inicio es posterior a la fecha de fin.");
            return null;
        }
        if (idProducto <= 0) { // Asumiendo que idProducto debe ser positivo
            System.err.println("Error de validación: El ID de producto no es válido.");
            return null;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        PromocionDto nuevaPromocion = new PromocionDto();
        nuevaPromocion.setNombre(nombre);
        nuevaPromocion.setDescripcion(descripcion);
        nuevaPromocion.setDescuento(descuento);
        nuevaPromocion.setFechaInicio(new Date(fechaInicio.getTime()));
        nuevaPromocion.setFechaFin(new Date(fechaFin.getTime()));
        nuevaPromocion.setIdProducto(idProducto);

        try {
            promocionDao.crearPromocion(nuevaPromocion); // El DAO actualiza el ID en el DTO
            System.out.println("Servicio: Promoción '" + nuevaPromocion.getNombre() + "' creada con ID: " + nuevaPromocion.getIdPromocion());
            return nuevaPromocion;
        } catch (SQLException e) {
            System.err.println("Error en PromocionesService al crear promoción: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza una promoción existente.
     * Parámetros basados en el diagrama de clases.
     * @param idPromocion ID de la promoción a actualizar.
     * @param nombre Nuevo nombre.
     * @param descripcion Nueva descripción.
     * @param descuento Nuevo descuento.
     * @param fechaInicio Nueva fecha de inicio.
     * @param fechaFin Nueva fecha de fin.
     * @param idProducto Nuevo ID de producto.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarPromocion(int idPromocion, String nombre, String descripcion, double descuento, java.util.Date fechaInicio, java.util.Date fechaFin, int idProducto) {
         if (idPromocion <= 0) {
            System.err.println("Error de validación: Se requiere un ID de promoción válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES SIMILARES A crearPromocion) ---
         if (nombre == null || nombre.trim().isEmpty()) { /* ... */ }
         // ... más validaciones ...
        // --- FIN LÓGICA DE NEGOCIO ---

        PromocionDto promocionActualizada = new PromocionDto();
        promocionActualizada.setIdPromocion(idPromocion);
        promocionActualizada.setNombre(nombre);
        promocionActualizada.setDescripcion(descripcion);
        promocionActualizada.setDescuento(descuento);
        promocionActualizada.setFechaInicio(new Date(fechaInicio.getTime()));
        promocionActualizada.setFechaFin(new Date(fechaFin.getTime()));
        promocionActualizada.setIdProducto(idProducto);
        
        try {
            // Opcional: verificar si la promoción existe antes de intentar actualizar.
            // Optional<PromocionDto> existente = promocionDao.obtenerPromocionPorId(idPromocion);
            // if (!existente.isPresent()) {
            //     System.err.println("Promoción con ID " + idPromocion + " no encontrada para actualizar.");
            //     return false;
            // }
            return promocionDao.actualizarPromocion(promocionActualizada);
        } catch (SQLException e) {
            System.err.println("Error en PromocionesService al actualizar promoción: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene una promoción por su ID.
     * @param idPromocion El ID de la promoción.
     * @return Un Optional que puede contener el PromocionDto si se encuentra.
     */
    public Optional<PromocionDto> obtenerPromocionPorId(int idPromocion) {
        try {
            return promocionDao.obtenerPromocionPorId(idPromocion);
        } catch (SQLException e) {
            System.err.println("Error en PromocionesService al obtener promoción por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todas las promociones.
     * @return Una lista de PromocionDto.
     */
    public List<PromocionDto> obtenerTodasLasPromociones() {
        try {
            return promocionDao.obtenerTodasLasPromociones();
        } catch (SQLException e) {
            System.err.println("Error en PromocionesService al obtener todas las promociones: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    /**
     * Obtiene todas las promociones asociadas a un producto específico.
     * @param idProducto El ID del producto.
     * @return Una lista de PromocionDto.
     */
    public List<PromocionDto> obtenerPromocionesPorProducto(int idProducto) {
        if (idProducto <= 0) {
            System.err.println("ID de producto no válido.");
            return Collections.emptyList();
        }
        try {
            return promocionDao.obtenerPromocionesPorProducto(idProducto);
        } catch (SQLException e) {
            System.err.println("Error en PromocionesService al obtener promociones por producto: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    /**
     * Elimina una promoción por su ID.
     * @param idPromocion El ID de la promoción a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarPromocion(int idPromocion) {
        // Lógica de negocio (ej. verificar si la promoción está activa o tiene dependencias)
        try {
            return promocionDao.eliminarPromocion(idPromocion);
        } catch (SQLException e) {
            System.err.println("Error en PromocionesService al eliminar promoción: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}