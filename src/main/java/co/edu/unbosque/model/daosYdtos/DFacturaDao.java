package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import co.edu.unbosque.model.DatabaseManager; // Asumiendo la clase de conexión

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types; // Para manejar NULLables con setNull
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DFacturaDao { // Singular

    /**
     * Crea un nuevo detalle de factura en la base de datos.
     * @param detalle El DTO del detalle de factura a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearDetalleFactura(DFacturaDto detalle) throws SQLException {
        // DDL detalleFactura: idDetalle (PK,AI), idFactura, idProducto, idProveedor, idPromocion, tipo, cantidad, precioUnitario, Total
        String sql = "INSERT INTO detalleFactura (idFactura, idProducto, idProveedor, idPromocion, tipo, cantidad, precioUnitario, Total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, detalle.getIdFactura());
            pstmt.setInt(2, detalle.getIdProducto());

            if (detalle.getIdProveedor() != null) {
                pstmt.setInt(3, detalle.getIdProveedor());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            
            if (detalle.getIdPromocion() != null) {
                pstmt.setInt(4, detalle.getIdPromocion());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            
            pstmt.setString(5, detalle.getTipo());
            pstmt.setInt(6, detalle.getCantidad());
            pstmt.setDouble(7, detalle.getPrecioUnitario());
            pstmt.setDouble(8, detalle.getTotal());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        detalle.setIdDetalle(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene un detalle de factura por su ID.
     * @param idDetalle El ID del detalle a buscar.
     * @return Un Optional conteniendo el DFacturaDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<DFacturaDto> obtenerDetalleFacturaPorId(int idDetalle) throws SQLException {
        String sql = "SELECT idDetalle, idFactura, idProducto, idProveedor, idPromocion, tipo, cantidad, precioUnitario, Total FROM detalleFactura WHERE idDetalle = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idDetalle);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    DFacturaDto detalle = new DFacturaDto();
                    detalle.setIdDetalle(rs.getInt("idDetalle"));
                    detalle.setIdFactura(rs.getInt("idFactura"));
                    detalle.setIdProducto(rs.getInt("idProducto"));
                    detalle.setIdProveedor(rs.getObject("idProveedor", Integer.class)); // Maneja NULL
                    detalle.setIdPromocion(rs.getObject("idPromocion", Integer.class)); // Maneja NULL
                    detalle.setTipo(rs.getString("tipo"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    detalle.setTotal(rs.getDouble("Total"));
                    return Optional.of(detalle);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todos los detalles de una factura específica.
     * @param idFactura El ID de la factura.
     * @return Una lista de DFacturaDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<DFacturaDto> obtenerDetallesPorIdFactura(int idFactura) throws SQLException {
        List<DFacturaDto> detalles = new ArrayList<>();
        String sql = "SELECT idDetalle, idFactura, idProducto, idProveedor, idPromocion, tipo, cantidad, precioUnitario, Total FROM detalleFactura WHERE idFactura = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFactura);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DFacturaDto detalle = new DFacturaDto();
                    detalle.setIdDetalle(rs.getInt("idDetalle"));
                    detalle.setIdFactura(rs.getInt("idFactura"));
                    detalle.setIdProducto(rs.getInt("idProducto"));
                    detalle.setIdProveedor(rs.getObject("idProveedor", Integer.class));
                    detalle.setIdPromocion(rs.getObject("idPromocion", Integer.class));
                    detalle.setTipo(rs.getString("tipo"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    detalle.setTotal(rs.getDouble("Total"));
                    detalles.add(detalle);
                }
            }
        }
        return detalles;
    }

    /**
     * Actualiza un detalle de factura existente.
     * @param detalle El DTO del detalle con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarDetalleFactura(DFacturaDto detalle) throws SQLException {
        String sql = "UPDATE detalleFactura SET idFactura = ?, idProducto = ?, idProveedor = ?, idPromocion = ?, tipo = ?, cantidad = ?, precioUnitario = ?, Total = ? WHERE idDetalle = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, detalle.getIdFactura());
            pstmt.setInt(2, detalle.getIdProducto());
            if (detalle.getIdProveedor() != null) {
                pstmt.setInt(3, detalle.getIdProveedor());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            if (detalle.getIdPromocion() != null) {
                pstmt.setInt(4, detalle.getIdPromocion());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setString(5, detalle.getTipo());
            pstmt.setInt(6, detalle.getCantidad());
            pstmt.setDouble(7, detalle.getPrecioUnitario());
            pstmt.setDouble(8, detalle.getTotal());
            pstmt.setInt(9, detalle.getIdDetalle());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un detalle de factura por su ID.
     * @param idDetalle El ID del detalle a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarDetalleFactura(int idDetalle) throws SQLException {
        String sql = "DELETE FROM detalleFactura WHERE idDetalle = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idDetalle);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina todos los detalles de una factura específica.
     * Útil cuando se elimina una factura completa.
     * @param idFactura El ID de la factura cuyos detalles se eliminarán.
     * @return true si se eliminaron detalles, false si no había detalles o error.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarDetallesPorIdFactura(int idFactura) throws SQLException {
        String sql = "DELETE FROM detalleFactura WHERE idFactura = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFactura);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}