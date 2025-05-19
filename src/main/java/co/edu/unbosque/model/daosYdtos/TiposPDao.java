package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import co.edu.unbosque.model.DatabaseManager; // Asumiendo la clase de conexión

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TiposPDao { // Nombre de clase DAO en plural

    /**
     * Crea un nuevo tipo de producto (Categoría) en la base de datos.
     * El ID generado (de la columna idProducto) se actualiza en el objeto TiposPDto.
     * @param tipoP El DTO del tipo de producto a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearTipoP(TiposPDto tipoP) throws SQLException {
        // DDL CategoriaP: idProducto (PK, AI), Categoria
        String sql = "INSERT INTO CategoriaP (Categoria) VALUES (?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, tipoP.getNombre());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // El ID generado es de la columna 'idProducto' de CategoriaP
                        tipoP.setIdTipoP(generatedKeys.getInt(1)); 
                    }
                }
            }
        }
    }

    /**
     * Obtiene un tipo de producto (Categoría) por su ID.
     * @param idTipoP El ID del tipo de producto a buscar (mapeado a idProducto en CategoriaP).
     * @return Un Optional conteniendo el TiposPDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<TiposPDto> obtenerTipoPPorId(int idTipoP) throws SQLException {
        String sql = "SELECT idProducto, Categoria FROM CategoriaP WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idTipoP);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    TiposPDto tipoP = new TiposPDto();
                    tipoP.setIdTipoP(rs.getInt("idProducto")); // Mapea idProducto de BD a idTipoP en DTO
                    tipoP.setNombre(rs.getString("Categoria"));   // Mapea Categoria de BD a nombre en DTO
                    return Optional.of(tipoP);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todos los tipos de producto (Categorías) de la base de datos.
     * @return Una lista de TiposPDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<TiposPDto> obtenerTodosLosTiposP() throws SQLException {
        List<TiposPDto> tiposP = new ArrayList<>();
        String sql = "SELECT idProducto, Categoria FROM CategoriaP";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                TiposPDto tipoP = new TiposPDto();
                tipoP.setIdTipoP(rs.getInt("idProducto"));
                tipoP.setNombre(rs.getString("Categoria"));
                tiposP.add(tipoP);
            }
        }
        return tiposP;
    }

    /**
     * Actualiza un tipo de producto (Categoría) existente en la base de datos.
     * @param tipoP El DTO del tipo de producto con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarTipoP(TiposPDto tipoP) throws SQLException {
        String sql = "UPDATE CategoriaP SET Categoria = ? WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tipoP.getNombre());
            pstmt.setInt(2, tipoP.getIdTipoP()); // idTipoP del DTO se usa para la condición WHERE idProducto
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un tipo de producto (Categoría) de la base de datos por su ID.
     * @param idTipoP El ID del tipo de producto a eliminar (mapeado a idProducto en CategoriaP).
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarTipoP(int idTipoP) throws SQLException {
        String sql = "DELETE FROM CategoriaP WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idTipoP);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}