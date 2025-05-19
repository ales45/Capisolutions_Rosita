/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unbosque.model;
import co.edu.unbosque.model.daosYdtos.UsuariosDao;
import co.edu.unbosque.model.daosYdtos.UsuariosDto;

import java.sql.SQLException;
import java.util.Collections; // Para devolver una lista vacía en caso de error
import java.util.List;
import java.util.Optional;

/**
 * Clase de servicio (o fachada) para gestionar la lógica de negocio de los Usuarios.
 * Esta clase utiliza UsuariosDao para el acceso a datos.
 */
public class Usuarios { // Renombré "Usuarios" a "UsuariosService" para mayor claridad de su rol

    private UsuariosDao usuariosDao;

    // Constructor
    public Usuarios() {
        this.usuariosDao = new UsuariosDao(); // Instanciamos el DAO aquí
    }

    /**
     * Crea un nuevo usuario después de aplicar lógica de negocio (ej. validaciones, hasheo de contraseña).
     *
     * @param nombreUsuario El nombre de usuario.
     * @param contrasena La contraseña en texto plano (debe ser hasheada aquí).
     * @param tipoAcceso El tipo de acceso o rol del usuario.
     * @return El UsuariosDto del usuario creado (con su ID generado), o null si hubo un error.
     */
    public UsuariosDto crearUsuario(String nombreUsuario, String contrasena, String tipoAcceso) {
        // --- INICIO LÓGICA DE NEGOCIO ---
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre de usuario no puede estar vacío.");
            return null; // O lanzar una IllegalArgumentException
        }
        if (contrasena == null || contrasena.length() < 6) { // Ejemplo de validación simple
            System.err.println("Error de validación: La contraseña debe tener al menos 6 caracteres.");
            return null;
        }

        // ¡MUY IMPORTANTE! Hashear la contraseña antes de guardarla.
        // Esto es solo un placeholder. Deberías usar una librería como BCrypt o SCrypt.
        //String contrasenaHasheada = "hash_de(" + contrasena + ")"; // ¡REEMPLAZAR CON HASHING REAL!
        //System.out.println("ADVERTENCIA DE SEGURIDAD: La contraseña debería ser hasheada con un algoritmo robusto.");
        // --- FIN LÓGICA DE NEGOCIO ---

        UsuariosDto nuevoUsuario = new UsuariosDto();
        nuevoUsuario.setUsuario(nombreUsuario);
        nuevoUsuario.setContraseña(contrasena); // Guardar la contraseña hasheada
        nuevoUsuario.setAcceso(tipoAcceso);

        try {
            usuariosDao.crearUsuario(nuevoUsuario); // El DAO debería actualizar el ID en el DTO
            System.out.println("Servicio: Usuario '" + nuevoUsuario.getUsuario() + "' creado con ID: " + nuevoUsuario.getIdUsuario());
            return nuevoUsuario;
        } catch (SQLException e) {
            // Aquí puedes loggear el error de forma más específica o incluso
            // lanzar una excepción de servicio personalizada.
            System.err.println("Error en UsuariosService al crear usuario: " + e.getMessage());
            e.printStackTrace();
            return null; // Opcional: podrías lanzar una nueva RuntimeException("Error al crear usuario", e);
        }
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param idUsuario El ID del usuario.
     * @return Un Optional que puede contener el UsuariosDto si se encuentra.
     */
    public Optional<UsuariosDto> obtenerUsuarioPorId(int idUsuario) {
        try {
            return usuariosDao.obtenerUsuarioPorId(idUsuario);
        } catch (SQLException e) {
            System.err.println("Error en UsuariosService al obtener usuario por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty(); // Devuelve un Optional vacío en caso de error
        }
    }

    /**
     * Obtiene una lista de todos los usuarios.
     * (En tu diagrama, ver_Usuario(id) devolvía List<UsuariosDto>, lo cual es inusual para un ID.
     * Este método es más apropiado para devolver una lista).
     * @return Una lista de UsuariosDto. Estará vacía si no hay usuarios o si ocurre un error.
     */
    public List<UsuariosDto> obtenerTodosLosUsuarios() {
        try {
            return usuariosDao.obtenerTodosLosUsuarios();
        } catch (SQLException e) {
            System.err.println("Error en UsuariosService al obtener todos los usuarios: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList(); // Devuelve una lista vacía en caso de error
        }
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param idUsuario El ID del usuario a actualizar.
     * @param nombreUsuario El nuevo nombre de usuario.
     * @param contrasena La nueva contraseña (en texto plano, será hasheada si no está vacía).
     * @param tipoAcceso El nuevo tipo de acceso.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarUsuario(int idUsuario, String nombreUsuario, String contrasena, String tipoAcceso) {
        // --- INICIO LÓGICA DE NEGOCIO ---
        // Validaciones similares a crearUsuario
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
             System.err.println("Error de validación: El nombre de usuario para actualizar no puede estar vacío.");
            return false;
        }

        // Solo hashear y actualizar la contraseña si se proporciona una nueva.
        String contrasenaParaActualizar = null;
        if (contrasena != null && !contrasena.trim().isEmpty()) {
            if (contrasena.length() < 6) {
                System.err.println("Error de validación: La nueva contraseña debe tener al menos 6 caracteres.");
                return false;
            }
            contrasenaParaActualizar = "hash_de(" + contrasena + ")"; // ¡REEMPLAZAR CON HASHING REAL!
            System.out.println("ADVERTENCIA DE SEGURIDAD: La contraseña debería ser hasheada con un algoritmo robusto.");
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        // Obtener el usuario actual para no perder otros datos si la contraseña no se actualiza
        // O, mejor aún, el DAO podría manejar la actualización selectiva de campos.
        // Por simplicidad aquí, creamos un DTO. Si la contraseña es null en el DTO,
        // el DAO NO debería actualizarla. (Esto requeriría lógica adicional en el DAO).
        // Aquí asumimos que el DTO enviado al DAO siempre reemplaza los valores.
        Optional<UsuariosDto> optUsuarioExistente = obtenerUsuarioPorId(idUsuario);
        if (!optUsuarioExistente.isPresent()) {
            System.err.println("Usuario con ID " + idUsuario + " no encontrado para actualizar.");
            return false;
        }
        
        UsuariosDto usuarioActualizado = optUsuarioExistente.get();
        usuarioActualizado.setUsuario(nombreUsuario);
        usuarioActualizado.setAcceso(tipoAcceso);
        if (contrasenaParaActualizar != null) {
            usuarioActualizado.setContraseña(contrasenaParaActualizar);
        }
        // Si no se proporciona nueva contraseña, se mantiene la antigua (que ya está hasheada en la BD)
        // Nota: El DTO que pasamos a usuariosDao.actualizarUsuario() debe tener la contraseña
        // (ya sea la nueva hasheada o la que se leyó de la BD si no se cambia).
        // El DAO de ejemplo que te di asume que la contraseña en el DTO es la que se va a guardar.
        // Para un mejor manejo, el DAO podría tener un método que actualice la contraseña solo si se provee una.

        try {
            return usuariosDao.actualizarUsuario(usuarioActualizado);
        } catch (SQLException e) {
            System.err.println("Error en UsuariosService al actualizar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param idUsuario El ID del usuario a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarUsuario(int idUsuario) {
        // Podrías añadir lógica de negocio aquí, por ejemplo:
        // - Verificar si el usuario tiene dependencias que impidan su eliminación.
        // - Verificar permisos del usuario que realiza la acción.
        try {
            return usuariosDao.eliminarUsuario(idUsuario);
        } catch (SQLException e) {
            System.err.println("Error en UsuariosService al eliminar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
