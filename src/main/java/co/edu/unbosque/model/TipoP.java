package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.TiposPDao; // Actualizado
import co.edu.unbosque.model.daosYdtos.TiposPDto; // Actualizado

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TipoP { // Nombre de clase de servicio en plural

    private TiposPDao tipoPDao;

    public TipoP() {
        this.tipoPDao = new TiposPDao();
    }

    /**
     * Crea un nuevo tipo de producto (categoría).
     * @param nombre El nombre del tipo/categoría.
     * @return El TiposPDto creado, o null si hubo un error.
     */
    public TiposPDto crear_tipo_p(String nombre) { // Nombre de método con guion bajo
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre del tipo de producto no puede estar vacío.");
            return null;
        }
        // Podrías verificar si ya existe una categoría con ese nombre
        // --- FIN LÓGICA DE NEGOCIO ---

        TiposPDto nuevoTipoP = new TiposPDto();
        nuevoTipoP.setNombre(nombre);

        try {
            tipoPDao.crearTipoP(nuevoTipoP);
            System.out.println("Servicio: TipoP '" + nuevoTipoP.getNombre() + "' creado con ID: " + nuevoTipoP.getIdTipoP());
            return nuevoTipoP;
        } catch (SQLException e) {
            System.err.println("Error en TiposPService al crear tipo de producto: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un tipo de producto (categoría) existente.
     * @param id_tipo_p El ID del tipo/categoría a actualizar.
     * @param nombre El nuevo nombre del tipo/categoría.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_tipo_p(int id_tipo_p, String nombre) { // Nombre de método y params con guion bajo
        if (id_tipo_p <= 0) {
            System.err.println("Error de validación: Se requiere un ID de tipo de producto válido para actualizar.");
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre del tipo de producto no puede estar vacío al actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // --- FIN LÓGICA DE NEGOCIO ---

        TiposPDto tipoPActualizado = new TiposPDto();
        tipoPActualizado.setIdTipoP(id_tipo_p);
        tipoPActualizado.setNombre(nombre);
        
        try {
            return tipoPDao.actualizarTipoP(tipoPActualizado);
        } catch (SQLException e) {
            System.err.println("Error en TiposPService al actualizar tipo de producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un tipo de producto (categoría) por su ID.
     * @param id_tipo_p El ID del tipo/categoría.
     * @return Un Optional que puede contener el TiposPDto si se encuentra.
     */
    public Optional<TiposPDto> obtener_tipo_p_por_id(int id_tipo_p) { // Nombre de método con guion bajo
        try {
            return tipoPDao.obtenerTipoPPorId(id_tipo_p);
        } catch (SQLException e) {
            System.err.println("Error en TiposPService al obtener tipo de producto por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los tipos de producto (categorías).
     * @return Una lista de TiposPDto.
     */
    public List<TiposPDto> obtener_todos_los_tipos_p() { // Nombre de método con guion bajo
        try {
            return tipoPDao.obtenerTodosLosTiposP();
        } catch (SQLException e) {
            System.err.println("Error en TiposPService al obtener todos los tipos de producto: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un tipo de producto (categoría) por su ID.
     * @param id_tipo_p El ID del tipo/categoría a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_tipo_p(int id_tipo_p) { // Nombre de método con guion bajo
        // Lógica de negocio: verificar si alguna entidad (ej. producto) usa esta categoría antes de eliminar.
        // Esto requeriría un método en ProductoDao para contar productos por idCategoriaP.
        // ej. if (productoDao.contarProductosPorCategoria(id_tipo_p) > 0) {
        //        System.err.println("No se puede eliminar el tipoP ID " + id_tipo_p + " porque está en uso.");
        //        return false;
        //    }
        try {
            return tipoPDao.eliminarTipoP(id_tipo_p);
        } catch (SQLException e) {
            // Considerar errores de FK si la BD impide eliminar categorías en uso.
            System.err.println("Error en TiposPService al eliminar tipo de producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}