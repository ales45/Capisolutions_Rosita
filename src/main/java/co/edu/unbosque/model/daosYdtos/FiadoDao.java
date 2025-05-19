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

public class FiadoDao {

    /**
     * Crea un nuevo registro de fiado en la base de datos.
     * @param fiado El DTO del fiado a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearFiado(FiadoDto fiado) throws SQLException {
        // DDL Fiado: idFiadoPK (PK, AI), idFactura, cuotas, estaPagado
        String sql = "INSERT INTO Fiado (idFactura, cuotas, estaPagado) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, fiado.getIdFactura());
            pstmt.setInt(2, fiado.getCuotas());
            pstmt.setBoolean(3, fiado.isEstaPagado());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        fiado.setIdFiado(generatedKeys.getInt(1)); // Asigna el idFiadoPK
                    }
                }
            }
        }
    }

    /**
     * Obtiene un registro de fiado por su ID (idFiadoPK).
     * @param idFiado El ID del fiado a buscar.
     * @return Un Optional conteniendo el FiadoDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<FiadoDto> obtenerFiadoPorId(int idFiado) throws SQLException {
        String sql = "SELECT idFiadoPK, idFactura, cuotas, estaPagado FROM Fiado WHERE idFiadoPK = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFiado);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    FiadoDto fiado = new FiadoDto();
                    fiado.setIdFiado(rs.getInt("idFiadoPK"));
                    fiado.setIdFactura(rs.getInt("idFactura"));
                    fiado.setCuotas(rs.getInt("cuotas"));
                    fiado.setEstaPagado(rs.getBoolean("estaPagado"));
                    return Optional.of(fiado);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene un registro de fiado por el ID de la factura asociada.
     * (Asume que una factura puede tener a lo más un registro de fiado)
     * @param idFactura El ID de la factura.
     * @return Un Optional conteniendo el FiadoDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<FiadoDto> obtenerFiadoPorIdFactura(int idFactura) throws SQLException {
        String sql = "SELECT idFiadoPK, idFactura, cuotas, estaPagado FROM Fiado WHERE idFactura = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFactura);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Debería ser único si una factura solo tiene un 'fiado'
                    FiadoDto fiado = new FiadoDto();
                    fiado.setIdFiado(rs.getInt("idFiadoPK"));
                    fiado.setIdFactura(rs.getInt("idFactura"));
                    fiado.setCuotas(rs.getInt("cuotas"));
                    fiado.setEstaPagado(rs.getBoolean("estaPagado"));
                    return Optional.of(fiado);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todos los registros de fiado de la base de datos.
     * @return Una lista de FiadoDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<FiadoDto> obtenerTodosLosFiados() throws SQLException {
        List<FiadoDto> fiados = new ArrayList<>();
        String sql = "SELECT idFiadoPK, idFactura, cuotas, estaPagado FROM Fiado";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                FiadoDto fiado = new FiadoDto();
                fiado.setIdFiado(rs.getInt("idFiadoPK"));
                fiado.setIdFactura(rs.getInt("idFactura"));
                fiado.setCuotas(rs.getInt("cuotas"));
                fiado.setEstaPagado(rs.getBoolean("estaPagado"));
                fiados.add(fiado);
            }
        }
        return fiados;
    }

    /**
     * Actualiza un registro de fiado existente.
     * @param fiado El DTO del fiado con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarFiado(FiadoDto fiado) throws SQLException {
        String sql = "UPDATE Fiado SET idFactura = ?, cuotas = ?, estaPagado = ? WHERE idFiadoPK = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, fiado.getIdFactura());
            pstmt.setInt(2, fiado.getCuotas());
            pstmt.setBoolean(3, fiado.isEstaPagado());
            pstmt.setInt(4, fiado.getIdFiado());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un registro de fiado por su ID (idFiadoPK).
     * @param idFiado El ID del fiado a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarFiado(int idFiado) throws SQLException {
        String sql = "DELETE FROM Fiado WHERE idFiadoPK = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFiado);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Elimina un registro de fiado por el ID de la factura asociada.
     * @param idFactura El ID de la factura cuyo fiado se eliminará.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarFiadoPorIdFactura(int idFactura) throws SQLException {
        String sql = "DELETE FROM Fiado WHERE idFactura = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFactura);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}