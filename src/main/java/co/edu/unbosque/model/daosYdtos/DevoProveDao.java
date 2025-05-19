package co.edu.unbosque.model.daosYdtos;

import co.edu.unbosque.model.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevoProveDao {

    /**
     * Crea una nueva devolución a proveedor en la base de datos.
     * @param devolucion El DTO de la devolución a proveedor a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearDevolucionProveedor(DevoProveDto devolucion) throws SQLException {
        // DDL devolucionesProveedor: idDevolucionPK (PK, AI), idProveedor, idProducto, cantidad, motivo, fecha
        String sql = "INSERT INTO devolucionesProveedor (idProveedor, idProducto, cantidad, motivo, fecha) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, devolucion.getIdProveedor());
            pstmt.setInt(2, devolucion.getIdProducto());
            pstmt.setInt(3, devolucion.getCantidad());
            pstmt.setString(4, devolucion.getMotivo());
            pstmt.setDate(5, devolucion.getFecha());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // El DTO.idDevoProve se mapea a idDevolucionPK de la BD
                        devolucion.setIdDevoProve(generatedKeys.getInt(1)); 
                    }
                }
            }
        }
    }

    /**
     * Obtiene una devolución a proveedor por su ID.
     * @param idDevoProve El ID de la devolución (mapeado a idDevolucionPK en BD).
     * @return Un Optional conteniendo el DevoProveDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<DevoProveDto> obtenerDevolucionProveedorPorId(int idDevoProve) throws SQLException {
        String sql = "SELECT idDevolucionPK, idProveedor, idProducto, cantidad, motivo, fecha FROM devolucionesProveedor WHERE idDevolucionPK = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idDevoProve);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    DevoProveDto devolucion = new DevoProveDto();
                    devolucion.setIdDevoProve(rs.getInt("idDevolucionPK"));
                    devolucion.setIdProveedor(rs.getInt("idProveedor"));
                    devolucion.setIdProducto(rs.getInt("idProducto"));
                    devolucion.setCantidad(rs.getInt("cantidad"));
                    devolucion.setMotivo(rs.getString("motivo"));
                    devolucion.setFecha(rs.getDate("fecha"));
                    return Optional.of(devolucion);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todas las devoluciones a proveedor de la base de datos.
     * @return Una lista de DevoProveDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<DevoProveDto> obtenerTodasLasDevolucionesProveedor() throws SQLException {
        List<DevoProveDto> devoluciones = new ArrayList<>();
        String sql = "SELECT idDevolucionPK, idProveedor, idProducto, cantidad, motivo, fecha FROM devolucionesProveedor";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                DevoProveDto devolucion = new DevoProveDto();
                devolucion.setIdDevoProve(rs.getInt("idDevolucionPK"));
                devolucion.setIdProveedor(rs.getInt("idProveedor"));
                devolucion.setIdProducto(rs.getInt("idProducto"));
                devolucion.setCantidad(rs.getInt("cantidad"));
                devolucion.setMotivo(rs.getString("motivo"));
                devolucion.setFecha(rs.getDate("fecha"));
                devoluciones.add(devolucion);
            }
        }
        return devoluciones;
    }

    /**
     * Actualiza una devolución a proveedor existente.
     * @param devolucion El DTO de la devolución con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarDevolucionProveedor(DevoProveDto devolucion) throws SQLException {
        String sql = "UPDATE devolucionesProveedor SET idProveedor = ?, idProducto = ?, cantidad = ?, motivo = ?, fecha = ? WHERE idDevolucionPK = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, devolucion.getIdProveedor());
            pstmt.setInt(2, devolucion.getIdProducto());
            pstmt.setInt(3, devolucion.getCantidad());
            pstmt.setString(4, devolucion.getMotivo());
            pstmt.setDate(5, devolucion.getFecha());
            pstmt.setInt(6, devolucion.getIdDevoProve()); // Mapea a idDevolucionPK
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina una devolución a proveedor por su ID.
     * @param idDevoProve El ID de la devolución a eliminar (mapeado a idDevolucionPK).
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarDevolucionProveedor(int idDevoProve) throws SQLException {
        String sql = "DELETE FROM devolucionesProveedor WHERE idDevolucionPK = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idDevoProve);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}