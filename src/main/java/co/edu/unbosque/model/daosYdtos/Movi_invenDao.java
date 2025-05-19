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

public class Movi_invenDao { // Nombre como en el diagrama

    /**
     * Crea un nuevo movimiento de inventario en la base de datos.
     * @param movimiento El DTO del movimiento a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearMoviInven(Movi_invenDto movimiento) throws SQLException {
        // DDL movimientosInventarios: idMovimiento (PK, AI), idProducto, tipoMovimiento, cantidad, fecha, motivo
        String sql = "INSERT INTO movimientosInventarios (idProducto, tipoMovimiento, cantidad, fecha, motivo) VALUES (?, ?, ?, ?, ?)";
        
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
                        movimiento.setIdMovimiento(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene un movimiento de inventario por su ID.
     * @param idMovimiento El ID del movimiento a buscar.
     * @return Un Optional conteniendo el Movi_invenDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<Movi_invenDto> obtenerMoviInvenPorId(int idMovimiento) throws SQLException {
        String sql = "SELECT idMovimiento, idProducto, tipoMovimiento, cantidad, fecha, motivo FROM movimientosInventarios WHERE idMovimiento = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idMovimiento);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Movi_invenDto movimiento = new Movi_invenDto();
                    movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
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
     * Obtiene todos los movimientos de inventario de la base de datos.
     * @return Una lista de Movi_invenDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<Movi_invenDto> obtenerTodosLosMoviInven() throws SQLException {
        List<Movi_invenDto> movimientos = new ArrayList<>();
        String sql = "SELECT idMovimiento, idProducto, tipoMovimiento, cantidad, fecha, motivo FROM movimientosInventarios";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Movi_invenDto movimiento = new Movi_invenDto();
                movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
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
     * Obtiene todos los movimientos de inventario para un producto específico.
     * @param idProducto El ID del producto.
     * @return Una lista de Movi_invenDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<Movi_invenDto> obtenerMoviInvenPorProducto(int idProducto) throws SQLException {
        List<Movi_invenDto> movimientos = new ArrayList<>();
        String sql = "SELECT idMovimiento, idProducto, tipoMovimiento, cantidad, fecha, motivo FROM movimientosInventarios WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Movi_invenDto movimiento = new Movi_invenDto();
                    movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
                    movimiento.setIdProducto(rs.getInt("idProducto"));
                    movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));
                    movimiento.setCantidad(rs.getInt("cantidad"));
                    movimiento.setFecha(rs.getDate("fecha"));
                    movimiento.setMotivo(rs.getString("motivo"));
                    movimientos.add(movimiento);
                }
            }
        }
        return movimientos;
    }


    /**
     * Actualiza un movimiento de inventario existente.
     * @param movimiento El DTO del movimiento con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarMoviInven(Movi_invenDto movimiento) throws SQLException {
        String sql = "UPDATE movimientosInventarios SET idProducto = ?, tipoMovimiento = ?, cantidad = ?, fecha = ?, motivo = ? WHERE idMovimiento = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, movimiento.getIdProducto());
            pstmt.setString(2, movimiento.getTipoMovimiento());
            pstmt.setInt(3, movimiento.getCantidad());
            pstmt.setDate(4, movimiento.getFecha());
            pstmt.setString(5, movimiento.getMotivo());
            pstmt.setInt(6, movimiento.getIdMovimiento());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un movimiento de inventario por su ID.
     * @param idMovimiento El ID del movimiento a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarMoviInven(int idMovimiento) throws SQLException {
        String sql = "DELETE FROM movimientosInventarios WHERE idMovimiento = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idMovimiento);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}