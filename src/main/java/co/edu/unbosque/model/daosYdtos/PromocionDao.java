package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import co.edu.unbosque.model.DatabaseManager; // Asumiendo que tienes esta clase para la conexión

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromocionDao {

    /**
     * Crea una nueva promoción en la base de datos.
     * El ID generado se actualiza en el objeto PromocionDto.
     * @param promocion El DTO de la promoción a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearPromocion(PromocionDto promocion) throws SQLException {
        // DDL: idPromocion (PK, AI), nombre, descripcion, descuento, fechaInicio, fechaFin, idProducto
        String sql = "INSERT INTO promociones (nombre, descripcion, descuento, fechaInicio, fechaFin, idProducto) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, promocion.getNombre());
            pstmt.setString(2, promocion.getDescripcion());
            pstmt.setDouble(3, promocion.getDescuento());
            pstmt.setDate(4, promocion.getFechaInicio());
            pstmt.setDate(5, promocion.getFechaFin());
            pstmt.setInt(6, promocion.getIdProducto());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        promocion.setIdPromocion(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene una promoción por su ID.
     * @param idPromocion El ID de la promoción a buscar.
     * @return Un Optional conteniendo el PromocionDto si se encuentra, o un Optional vacío.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<PromocionDto> obtenerPromocionPorId(int idPromocion) throws SQLException {
        String sql = "SELECT idPromocion, nombre, descripcion, descuento, fechaInicio, fechaFin, idProducto FROM promociones WHERE idPromocion = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPromocion);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    PromocionDto promocion = new PromocionDto();
                    promocion.setIdPromocion(rs.getInt("idPromocion"));
                    promocion.setNombre(rs.getString("nombre"));
                    promocion.setDescripcion(rs.getString("descripcion"));
                    promocion.setDescuento(rs.getDouble("descuento"));
                    promocion.setFechaInicio(rs.getDate("fechaInicio"));
                    promocion.setFechaFin(rs.getDate("fechaFin"));
                    promocion.setIdProducto(rs.getInt("idProducto"));
                    return Optional.of(promocion);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todas las promociones de la base de datos.
     * @return Una lista de PromocionDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<PromocionDto> obtenerTodasLasPromociones() throws SQLException {
        List<PromocionDto> promociones = new ArrayList<>();
        String sql = "SELECT idPromocion, nombre, descripcion, descuento, fechaInicio, fechaFin, idProducto FROM promociones";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                PromocionDto promocion = new PromocionDto();
                promocion.setIdPromocion(rs.getInt("idPromocion"));
                promocion.setNombre(rs.getString("nombre"));
                promocion.setDescripcion(rs.getString("descripcion"));
                promocion.setDescuento(rs.getDouble("descuento"));
                promocion.setFechaInicio(rs.getDate("fechaInicio"));
                promocion.setFechaFin(rs.getDate("fechaFin"));
                promocion.setIdProducto(rs.getInt("idProducto"));
                promociones.add(promocion);
            }
        }
        return promociones;
    }

    /**
     * Actualiza una promoción existente en la base de datos.
     * @param promocion El DTO de la promoción con los datos actualizados (debe incluir el ID).
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarPromocion(PromocionDto promocion) throws SQLException {
        String sql = "UPDATE promociones SET nombre = ?, descripcion = ?, descuento = ?, fechaInicio = ?, fechaFin = ?, idProducto = ? WHERE idPromocion = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, promocion.getNombre());
            pstmt.setString(2, promocion.getDescripcion());
            pstmt.setDouble(3, promocion.getDescuento());
            pstmt.setDate(4, promocion.getFechaInicio());
            pstmt.setDate(5, promocion.getFechaFin());
            pstmt.setInt(6, promocion.getIdProducto());
            pstmt.setInt(7, promocion.getIdPromocion());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina una promoción de la base de datos por su ID.
     * @param idPromocion El ID de la promoción a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarPromocion(int idPromocion) throws SQLException {
        String sql = "DELETE FROM promociones WHERE idPromocion = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPromocion);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
     /**
     * Obtiene todas las promociones asociadas a un producto específico.
     * @param idProducto El ID del producto.
     * @return Una lista de PromocionDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<PromocionDto> obtenerPromocionesPorProducto(int idProducto) throws SQLException {
        List<PromocionDto> promociones = new ArrayList<>();
        String sql = "SELECT idPromocion, nombre, descripcion, descuento, fechaInicio, fechaFin, idProducto FROM promociones WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PromocionDto promocion = new PromocionDto();
                    promocion.setIdPromocion(rs.getInt("idPromocion"));
                    promocion.setNombre(rs.getString("nombre"));
                    promocion.setDescripcion(rs.getString("descripcion"));
                    promocion.setDescuento(rs.getDouble("descuento"));
                    promocion.setFechaInicio(rs.getDate("fechaInicio"));
                    promocion.setFechaFin(rs.getDate("fechaFin"));
                    promocion.setIdProducto(rs.getInt("idProducto"));
                    promociones.add(promocion);
                }
            }
        }
        return promociones;
    }
}
