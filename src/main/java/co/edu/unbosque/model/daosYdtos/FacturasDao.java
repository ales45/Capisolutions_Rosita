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

public class FacturasDao { // Nombre de clase DAO en plural

    /**
     * Crea una nueva factura en la base de datos.
     * El campo 'total' del DTO no se guarda directamente aquí ya que no está en la tabla 'facturas'.
     * @param factura El DTO de la factura a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearFactura(FacturasDto factura) throws SQLException {
        // DDL facturas: idFactura (PK, AI), idCliente, fecha, estadoPago
        String sql = "INSERT INTO facturas (idCliente, fecha, estadoPago) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, factura.getIdCliente());
            pstmt.setDate(2, factura.getFecha());
            pstmt.setString(3, factura.getEstadoPago());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        factura.setIdFactura(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene una factura por su ID.
     * El campo 'total' del DTO no se carga desde la tabla 'facturas'.
     * @param idFactura El ID de la factura a buscar.
     * @return Un Optional conteniendo el FacturasDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<FacturasDto> obtenerFacturaPorId(int idFactura) throws SQLException {
        String sql = "SELECT idFactura, idCliente, fecha, estadoPago FROM facturas WHERE idFactura = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFactura);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    FacturasDto factura = new FacturasDto();
                    factura.setIdFactura(rs.getInt("idFactura"));
                    factura.setIdCliente(rs.getInt("idCliente"));
                    factura.setFecha(rs.getDate("fecha"));
                    factura.setEstadoPago(rs.getString("estadoPago"));
                    // factura.setTotal(...); // El total se calcularía por separado
                    return Optional.of(factura);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todas las facturas de la base de datos.
     * El campo 'total' de los DTOs no se carga desde la tabla 'facturas'.
     * @return Una lista de FacturasDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<FacturasDto> obtenerTodasLasFacturas() throws SQLException {
        List<FacturasDto> facturas = new ArrayList<>();
        String sql = "SELECT idFactura, idCliente, fecha, estadoPago FROM facturas";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                FacturasDto factura = new FacturasDto();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdCliente(rs.getInt("idCliente"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setEstadoPago(rs.getString("estadoPago"));
                // factura.setTotal(...); // El total se calcularía por separado
                facturas.add(factura);
            }
        }
        return facturas;
    }

    /**
     * Actualiza una factura existente en la base de datos.
     * Solo actualiza campos presentes en la tabla 'facturas'.
     * @param factura El DTO de la factura con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarFactura(FacturasDto factura) throws SQLException {
        String sql = "UPDATE facturas SET idCliente = ?, fecha = ?, estadoPago = ? WHERE idFactura = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, factura.getIdCliente());
            pstmt.setDate(2, factura.getFecha());
            pstmt.setString(3, factura.getEstadoPago());
            pstmt.setInt(4, factura.getIdFactura());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina una factura de la base de datos por su ID.
     * Considerar eliminar detalles de factura asociados (ON DELETE CASCADE o manualmente).
     * @param idFactura El ID de la factura a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarFactura(int idFactura) throws SQLException {
        String sql = "DELETE FROM facturas WHERE idFactura = ?";
        // Considerar también eliminar de detalleFactura, ventasAnuladas, Fiado si es necesario
        // y no hay ON DELETE CASCADE configurado en la BD.
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFactura);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Obtiene todas las facturas de un cliente específico.
     * El campo 'total' de los DTOs no se carga desde la tabla 'facturas'.
     * @param idCliente El ID del cliente.
     * @return Una lista de FacturasDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<FacturasDto> obtenerFacturasPorCliente(int idCliente) throws SQLException {
        List<FacturasDto> facturas = new ArrayList<>();
        String sql = "SELECT idFactura, idCliente, fecha, estadoPago FROM facturas WHERE idCliente = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FacturasDto factura = new FacturasDto();
                    factura.setIdFactura(rs.getInt("idFactura"));
                    factura.setIdCliente(rs.getInt("idCliente"));
                    factura.setFecha(rs.getDate("fecha"));
                    factura.setEstadoPago(rs.getString("estadoPago"));
                    // factura.setTotal(...); // El total se calcularía por separado
                    facturas.add(factura);
                }
            }
        }
        return facturas;
    }
}
