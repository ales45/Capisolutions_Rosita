package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import co.edu.unbosque.model.DatabaseManager; // Asumiendo que tienes esta clase para la conexión

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventarioDao {

    /**
     * Crea un nuevo registro de inventario en la base de datos.
     * El ID generado se actualiza en el objeto InventarioDto.
     * @param inventario El DTO del inventario a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearInventario(InventarioDto inventario) throws SQLException {
        // DDL: idInventario (PK, AI), idProducto, stock, stockMinimo, ubicacion, ultimaActualizacion
        String sql = "INSERT INTO inventario (idProducto, stock, stockMinimo, ubicacion, ultimaActualizacion) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, inventario.getIdProducto());
            pstmt.setInt(2, inventario.getStock());
            pstmt.setInt(3, inventario.getStockMinimo());
            pstmt.setString(4, inventario.getUbicacion());
            pstmt.setTimestamp(5, inventario.getUltimaActualizacion());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        inventario.setIdInventario(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene un registro de inventario por su ID.
     * @param idInventario El ID del inventario a buscar.
     * @return Un Optional conteniendo el InventarioDto si se encuentra, o un Optional vacío.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<InventarioDto> obtenerInventarioPorId(int idInventario) throws SQLException {
        String sql = "SELECT idInventario, idProducto, stock, stockMinimo, ubicacion, ultimaActualizacion FROM inventario WHERE idInventario = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idInventario);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    InventarioDto inventario = new InventarioDto();
                    inventario.setIdInventario(rs.getInt("idInventario"));
                    inventario.setIdProducto(rs.getInt("idProducto"));
                    inventario.setStock(rs.getInt("stock"));
                    inventario.setStockMinimo(rs.getInt("stockMinimo"));
                    inventario.setUbicacion(rs.getString("ubicacion"));
                    inventario.setUltimaActualizacion(rs.getTimestamp("ultimaActualizacion"));
                    return Optional.of(inventario);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todos los registros de inventario de la base de datos.
     * @return Una lista de InventarioDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<InventarioDto> obtenerTodosLosInventarios() throws SQLException {
        List<InventarioDto> inventarios = new ArrayList<>();
        String sql = "SELECT idInventario, idProducto, stock, stockMinimo, ubicacion, ultimaActualizacion FROM inventario";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                InventarioDto inventario = new InventarioDto();
                inventario.setIdInventario(rs.getInt("idInventario"));
                inventario.setIdProducto(rs.getInt("idProducto"));
                inventario.setStock(rs.getInt("stock"));
                inventario.setStockMinimo(rs.getInt("stockMinimo"));
                inventario.setUbicacion(rs.getString("ubicacion"));
                inventario.setUltimaActualizacion(rs.getTimestamp("ultimaActualizacion"));
                inventarios.add(inventario);
            }
        }
        return inventarios;
    }

    /**
     * Actualiza un registro de inventario existente en la base de datos.
     * @param inventario El DTO del inventario con los datos actualizados (debe incluir el ID).
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarInventario(InventarioDto inventario) throws SQLException {
        String sql = "UPDATE inventario SET idProducto = ?, stock = ?, stockMinimo = ?, ubicacion = ?, ultimaActualizacion = ? WHERE idInventario = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, inventario.getIdProducto());
            pstmt.setInt(2, inventario.getStock());
            pstmt.setInt(3, inventario.getStockMinimo());
            pstmt.setString(4, inventario.getUbicacion());
            pstmt.setTimestamp(5, inventario.getUltimaActualizacion());
            pstmt.setInt(6, inventario.getIdInventario());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un registro de inventario de la base de datos por su ID.
     * @param idInventario El ID del inventario a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarInventario(int idInventario) throws SQLException {
        String sql = "DELETE FROM inventario WHERE idInventario = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idInventario);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Obtiene los registros de inventario donde el stock es menor o igual al stock mínimo.
     * @return Una lista de InventarioDto que representan productos con stock bajo.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<InventarioDto> obtenerInventariosConStockBajo() throws SQLException {
        List<InventarioDto> inventariosBajos = new ArrayList<>();
        String sql = "SELECT idInventario, idProducto, stock, stockMinimo, ubicacion, ultimaActualizacion FROM inventario WHERE stock <= stockMinimo";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                InventarioDto inventario = new InventarioDto();
                inventario.setIdInventario(rs.getInt("idInventario"));
                inventario.setIdProducto(rs.getInt("idProducto"));
                inventario.setStock(rs.getInt("stock"));
                inventario.setStockMinimo(rs.getInt("stockMinimo"));
                inventario.setUbicacion(rs.getString("ubicacion"));
                inventario.setUltimaActualizacion(rs.getTimestamp("ultimaActualizacion"));
                inventariosBajos.add(inventario);
            }
        }
        return inventariosBajos;
    }
}