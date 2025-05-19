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

public class MovimientoDevoDao {

    /**
     * Crea un nuevo movimiento de devolución en la base de datos.
     * @param movimiento El DTO del movimiento de devolución a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearMovimientoDevolucion(MovimientoDevoDto movimiento) throws SQLException {
        // DDL movimientosDevolucion: idMovimientoProveedor (PK, AI), idProducto, tipoMovimiento, cantidad, fecha, motivo
        String sql = "INSERT INTO movimientosDevolucion (idProducto, tipoMovimiento, cantidad, fecha, motivo) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, movimiento.getIdProducto());
            pstmt.setString(2, movimiento.getTipoMovimiento());
            pstmt.setInt(3, movimiento.getCantidad());
            pstmt.setDate(4, movimiento.getFecha());
            pstmt.setString(5, movimiento.getMotivo());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // El DTO.idMovimientoDevo se mapea a idMovimientoProveedor de la BD
                        movimiento.setIdMovimientoDevo(generatedKeys.getInt(1)); 
                    }
                }
            }
        }
    }

    /**
     * Obtiene un movimiento de devolución por su ID.
     * @param idMovimientoDevo El ID del movimiento (mapeado a idMovimientoProveedor en BD).
     * @return Un Optional conteniendo el MovimientoDevoDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<MovimientoDevoDto> obtenerMovimientoDevolucionPorId(int idMovimientoDevo) throws SQLException {
        String sql = "SELECT idMovimientoProveedor, idProducto, tipoMovimiento, cantidad, fecha, motivo FROM movimientosDevolucion WHERE idMovimientoProveedor = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idMovimientoDevo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    MovimientoDevoDto movimiento = new MovimientoDevoDto();
                    movimiento.setIdMovimientoDevo(rs.getInt("idMovimientoProveedor"));
                    movimiento.setIdProducto(rs.getInt("idProducto"));
                    movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));
                    movimiento.setCantidad(rs.getInt("cantidad"));
                    movimiento.setFecha(rs.getDate("fecha"));
                    movimiento.setMotivo(rs.getString("motivo"));
                    return Optional.of(movimiento);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todos los movimientos de devolución de la base de datos.
     * @return Una lista de MovimientoDevoDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<MovimientoDevoDto> obtenerTodosLosMovimientosDevolucion() throws SQLException {
        List<MovimientoDevoDto> movimientos = new ArrayList<>();
        String sql = "SELECT idMovimientoProveedor, idProducto, tipoMovimiento, cantidad, fecha, motivo FROM movimientosDevolucion";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                MovimientoDevoDto movimiento = new MovimientoDevoDto();
                movimiento.setIdMovimientoDevo(rs.getInt("idMovimientoProveedor"));
                movimiento.setIdProducto(rs.getInt("idProducto"));
                movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setFecha(rs.getDate("fecha"));
                movimiento.setMotivo(rs.getString("motivo"));
                movimientos.add(movimiento);
            }
        }
        return movimientos;
    }

    /**
     * Actualiza un movimiento de devolución existente.
     * @param movimiento El DTO del movimiento con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarMovimientoDevolucion(MovimientoDevoDto movimiento) throws SQLException {
        String sql = "UPDATE movimientosDevolucion SET idProducto = ?, tipoMovimiento = ?, cantidad = ?, fecha = ?, motivo = ? WHERE idMovimientoProveedor = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, movimiento.getIdProducto());
            pstmt.setString(2, movimiento.getTipoMovimiento());
            pstmt.setInt(3, movimiento.getCantidad());
            pstmt.setDate(4, movimiento.getFecha());
            pstmt.setString(5, movimiento.getMotivo());
            pstmt.setInt(6, movimiento.getIdMovimientoDevo()); // Mapea a idMovimientoProveedor
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un movimiento de devolución por su ID.
     * @param idMovimientoDevo El ID del movimiento a eliminar (mapeado a idMovimientoProveedor).
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarMovimientoDevolucion(int idMovimientoDevo) throws SQLException {
        String sql = "DELETE FROM movimientosDevolucion WHERE idMovimientoProveedor = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idMovimientoDevo);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}