/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unbosque.model.daosYdtos;


import co.edu.unbosque.model.daosYdtos.UsuariosDto;
import co.edu.unbosque.model.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuariosDao {

    /**
     * Crea un nuevo usuario en la base de datos.
     * @param usuario El DTO del usuario a crear. El ID se actualizará en este objeto si se genera.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearUsuario(UsuariosDto usuario) throws SQLException {
        // Nombres de columnas en la BD: id_usuario, usuario, contrasena, acceso
        String sql = "INSERT INTO Usuario (usuario, contraseña, acceso) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getContraseña()); // ¡RECUERDA! Hashear la contraseña antes de guardarla.
            pstmt.setString(3, usuario.getAcceso());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setIdUsuario(generatedKeys.getInt(1)); // Actualiza el DTO con el ID generado
                    }
                }
            }
        }
        // La excepción SQLException se propaga si ocurre.
    }

    /**
     * Obtiene un usuario por su ID.
     * @param idUsuario El ID del usuario a buscar.
     * @return Un Optional conteniendo el UsuariosDto si se encuentra, o un Optional vacío.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<UsuariosDto> obtenerUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "SELECT id_usuario, usuario, contraseña, acceso FROM Usuario WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuario);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UsuariosDto usuario = new UsuariosDto();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setContraseña(rs.getString("contraseña")); // Considera no cargar la contraseña a menos que sea para validación
                    usuario.setAcceso(rs.getString("acceso"));
                    return Optional.of(usuario);
                }
            }
        }
        return Optional.empty(); // No se encontró el usuario
    }

    /**
     * Obtiene todos los usuarios de la base de datos.
     * @return Una lista de UsuariosDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<UsuariosDto> obtenerTodosLosUsuarios() throws SQLException {
        List<UsuariosDto> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, Usuario, acceso FROM Usuario"; // Generalmente no se trae la contraseña en listados masivos
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                UsuariosDto usuario = new UsuariosDto();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario"));
                // No se recupera la contraseña aquí por seguridad y eficiencia
                usuario.setAcceso(rs.getString("acceso"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     * @param usuario El DTO del usuario con los datos actualizados (debe incluir el ID).
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarUsuario(UsuariosDto usuario) throws SQLException {
        String sql = "UPDATE Usuario SET usuario = ?, contraseña = ?, acceso = ? WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getContraseña()); // ¡RECUERDA! Hashear si se está actualizando.
            pstmt.setString(3, usuario.getAcceso());
            pstmt.setInt(4, usuario.getIdUsuario());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     * @param idUsuario El ID del usuario a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuario);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Podrías añadir más métodos según tus necesidades, por ejemplo:
    // public Optional<UsuariosDto> obtenerUsuarioPorNombreUsuario(String nombreUsuario) throws SQLException { ... }
}
