package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import co.edu.unbosque.model.DatabaseManager; // Asumiendo la clase de conexión

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VentasAnuDao {

    /**
     * Crea un nuevo registro de venta anulada en la base de datos.
     * @param ventaAnulada El DTO de la venta anulada a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearVentaAnulada(VentasAnuDto ventaAnulada) throws SQLException {
        // DDL ventasAnuladas: idAnulacion (PK, AI), idFactura, motivo, fechaAnulacion, idCliente
        String sql = "INSERT INTO ventasAnuladas (idFactura, motivo, fechaAnulacion, idCliente) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, ventaAnulada.getIdFactura());
            pstmt.setString(2, ventaAnulada.getMotivo());
            pstmt.setDate(3, ventaAnulada.getFechaAnulacion());
            pstmt.setInt(4, ventaAnulada.getIdCliente());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ventaAnulada.setIdAnulacion(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene un registro de venta anulada por su ID de anulación.
     * @param idAnulacion El ID de la anulación a buscar.
     * @return Un Optional conteniendo el VentasAnuDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<VentasAnuDto> obtenerVentaAnuladaPorIdAnulacion(int idAnulacion) throws SQLException {
        String sql = "SELECT idAnulacion, idFactura, motivo, fechaAnulacion, idCliente FROM ventasAnuladas WHERE idAnulacion = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idAnulacion);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    VentasAnuDto ventaAnulada = new VentasAnuDto();
                    ventaAnulada.setIdAnulacion(rs.getInt("idAnulacion"));
                    ventaAnulada.setIdFactura(rs.getInt("idFactura"));
                    ventaAnulada.setMotivo(rs.getString("motivo"));
                    ventaAnulada.setFechaAnulacion(rs.getDate("fechaAnulacion"));
                    ventaAnulada.setIdCliente(rs.getInt("idCliente"));
                    return Optional.of(ventaAnulada);
                }
            }
        }
        return Optional.empty();
    }
    
    /**
     * Obtiene un registro de venta anulada por el ID de la factura asociada.
     * Asume que solo puede haber una anulación por factura.
     * @param idFactura El ID de la factura.
     * @return Un Optional conteniendo el VentasAnuDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<VentasAnuDto> obtenerVentaAnuladaPorIdFactura(int idFactura) throws SQLException {
        String sql = "SELECT idAnulacion, idFactura, motivo, fechaAnulacion, idCliente FROM ventasAnuladas WHERE idFactura = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFactura);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Asume una o ninguna anulación por factura
                    VentasAnuDto ventaAnulada = new VentasAnuDto();
                    ventaAnulada.setIdAnulacion(rs.getInt("idAnulacion"));
                    ventaAnulada.setIdFactura(rs.getInt("idFactura"));
                    ventaAnulada.setMotivo(rs.getString("motivo"));
                    ventaAnulada.setFechaAnulacion(rs.getDate("fechaAnulacion"));
                    ventaAnulada.setIdCliente(rs.getInt("idCliente"));
                    return Optional.of(ventaAnulada);
                }
            }
        }
        return Optional.empty();
    }


    /**
     * Obtiene todos los registros de ventas anuladas de la base de datos.
     * @return Una lista de VentasAnuDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<VentasAnuDto> obtenerTodasLasVentasAnuladas() throws SQLException {
        List<VentasAnuDto> ventasAnuladas = new ArrayList<>();
        String sql = "SELECT idAnulacion, idFactura, motivo, fechaAnulacion, idCliente FROM ventasAnuladas";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                VentasAnuDto ventaAnulada = new VentasAnuDto();
                ventaAnulada.setIdAnulacion(rs.getInt("idAnulacion"));
                ventaAnulada.setIdFactura(rs.getInt("idFactura"));
                ventaAnulada.setMotivo(rs.getString("motivo"));
                ventaAnulada.setFechaAnulacion(rs.getDate("fechaAnulacion"));
                ventaAnulada.setIdCliente(rs.getInt("idCliente"));
                ventasAnuladas.add(ventaAnulada);
            }
        }
        return ventasAnuladas;
    }

    /**
     * Actualiza un registro de venta anulada existente.
     * @param ventaAnulada El DTO de la venta anulada con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarVentaAnulada(VentasAnuDto ventaAnulada) throws SQLException {
        String sql = "UPDATE ventasAnuladas SET idFactura = ?, motivo = ?, fechaAnulacion = ?, idCliente = ? WHERE idAnulacion = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, ventaAnulada.getIdFactura());
            pstmt.setString(2, ventaAnulada.getMotivo());
            pstmt.setDate(3, ventaAnulada.getFechaAnulacion());
            pstmt.setInt(4, ventaAnulada.getIdCliente());
            pstmt.setInt(5, ventaAnulada.getIdAnulacion());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un registro de venta anulada por su ID de anulación.
     * @param idAnulacion El ID de la anulación a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarVentaAnulada(int idAnulacion) throws SQLException {
        String sql = "DELETE FROM ventasAnuladas WHERE idAnulacion = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idAnulacion);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Elimina registros de ventas anuladas por ID de factura.
     * Puede ser útil si una factura se "des-anula" o se modifica.
     * @param idFactura El ID de la factura.
     * @return true si se eliminaron registros, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarVentasAnuladasPorIdFactura(int idFactura) throws SQLException {
        String sql = "DELETE FROM ventasAnuladas WHERE idFactura = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFactura);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}