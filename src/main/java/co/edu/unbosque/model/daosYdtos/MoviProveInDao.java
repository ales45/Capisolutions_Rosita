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

public class MoviProveInDao {

    /**
     * Crea un nuevo movimiento de proveedor (incremento) en la base de datos.
     * El ID generado se actualiza en el objeto MoviProveInDto.
     * @param movimiento El DTO del movimiento a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearMoviProveIn(MoviProveInDto movimiento) throws SQLException {
        // DDL movimientosProveedoresINC: idMovimientoProveedorINC (PK, AI), idProducto, idInventario, tipoMovimiento, cantidad, fecha, motivo
        String sql = "INSERT INTO movimientosProveedoresINC (idInventario, tipoMovimiento, cantidad, fecha, motivo,idProducto) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, movimiento.getIdInventario());
            pstmt.setString(2, movimiento.getTipoMovimiento());
            pstmt.setInt(3, movimiento.getCantidad());
            pstmt.setDate(4, movimiento.getFecha());
            pstmt.setString(5, movimiento.getMotivo());
            pstmt.setInt(6, movimiento.getIdProducto());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        movimiento.setIdMovimientoProveedorINC(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene un movimiento de proveedor (incremento) por su ID.
     * @param idMovimiento El ID del movimiento a buscar.
     * @return Un Optional conteniendo el MoviProveInDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<MoviProveInDto> obtenerMoviProveInPorId(int idMovimiento) throws SQLException {
        String sql = "SELECT idMovimientoProveedorINC, idProducto, idInventario, tipoMovimiento, cantidad, fecha, motivo FROM movimientosProveedoresINC WHERE idMovimientoProveedorINC = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idMovimiento);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    MoviProveInDto movimiento = new MoviProveInDto();
                    movimiento.setIdMovimientoProveedorINC(rs.getInt("idMovimientoProveedorINC"));
                    movimiento.setIdProducto(rs.getInt("idProducto"));
                    movimiento.setIdInventario(rs.getInt("idInventario"));
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
     * Obtiene todos los movimientos de proveedor (incremento) de la base de datos.
     * @return Una lista de MoviProveInDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<MoviProveInDto> obtenerTodosLosMoviProveIn() throws SQLException {
        List<MoviProveInDto> movimientos = new ArrayList<>();
        String sql = "SELECT idMovimientoProveedorINC, idProducto, idInventario, tipoMovimiento, cantidad, fecha, motivo FROM movimientosProveedoresINC";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                MoviProveInDto movimiento = new MoviProveInDto();
                movimiento.setIdMovimientoProveedorINC(rs.getInt("idMovimientoProveedorINC"));
                movimiento.setIdProducto(rs.getInt("idProducto"));
                movimiento.setIdInventario(rs.getInt("idInventario"));
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
     * Actualiza un movimiento de proveedor (incremento) existente.
     * @param movimiento El DTO del movimiento con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarMoviProveIn(MoviProveInDto movimiento) throws SQLException {
        String sql = "UPDATE movimientosProveedoresINC SET idProducto = ?, idInventario = ?, tipoMovimiento = ?, cantidad = ?, fecha = ?, motivo = ? WHERE idMovimientoProveedorINC = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, movimiento.getIdProducto());
            pstmt.setInt(2, movimiento.getIdInventario());
            pstmt.setString(3, movimiento.getTipoMovimiento());
            pstmt.setInt(4, movimiento.getCantidad());
            pstmt.setDate(5, movimiento.getFecha());
            pstmt.setString(6, movimiento.getMotivo());
            pstmt.setInt(7, movimiento.getIdMovimientoProveedorINC());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un movimiento de proveedor (incremento) por su ID.
     * @param idMovimiento El ID del movimiento a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarMoviProveIn(int idMovimiento) throws SQLException {
        String sql = "DELETE FROM movimientosProveedoresINC WHERE idMovimientoProveedorINC = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idMovimiento);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}