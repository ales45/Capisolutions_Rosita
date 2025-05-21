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

public class ProveedorDao {

    /**
     * Crea un nuevo proveedor en la base de datos.
     * El ID generado se actualiza en el objeto ProveedorDto.
     * @param proveedor El DTO del proveedor a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearProveedor(ProveedorDto proveedor) throws SQLException {
        // DDL: idProveedor (PK, AI), nombre, contacto, direccion, idProducto
        String sql = "INSERT INTO proveedores (nombre, contacto, direccion, idProducto,cedula) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setString(3, proveedor.getDireccion());
            pstmt.setInt(4, proveedor.getIdProducto());
            pstmt.setLong(5, proveedor.getCedula());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        proveedor.setIdProveedor(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene un proveedor por su ID.
     * @param idProveedor El ID del proveedor a buscar.
     * @return Un Optional conteniendo el ProveedorDto si se encuentra, o un Optional vacío.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<ProveedorDto> obtenerProveedorPorId(int idProveedor) throws SQLException {
        String sql = "SELECT idProveedor, nombre, contacto, direccion, idProducto FROM proveedores WHERE idProveedor = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProveedor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ProveedorDto proveedor = new ProveedorDto();
                    proveedor.setIdProveedor(rs.getInt("idProveedor"));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setContacto(rs.getString("contacto"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    proveedor.setIdProducto(rs.getInt("idProducto"));
                    return Optional.of(proveedor);
                }
            }
        }
        return Optional.empty();
    }
    public Optional<ProveedorDto> obtenerProveedorPorCedula(long cedula) throws SQLException {
        String sql = "SELECT idProveedor, nombre, contacto, direccion, idProducto, cedula FROM proveedores WHERE cedula = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, cedula);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ProveedorDto proveedor = new ProveedorDto();
                    proveedor.setIdProveedor(rs.getInt("idProveedor"));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setContacto(rs.getString("contacto"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    proveedor.setIdProducto(rs.getInt("idProducto"));
                    proveedor.setCedula(rs.getLong("cedula"));
                    return Optional.of(proveedor);
                }
            }
        }
        return Optional.empty();
    }
    /**
     * Obtiene todos los proveedores de la base de datos.
     * @return Una lista de ProveedorDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<ProveedorDto> obtenerTodosLosProveedores() throws SQLException {
        List<ProveedorDto> proveedores = new ArrayList<>();
        String sql = "SELECT idProveedor, nombre, contacto, direccion, idProducto,cedula FROM proveedores";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                ProveedorDto proveedor = new ProveedorDto();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setIdProducto(rs.getInt("idProducto"));
                proveedor.setCedula(rs.getLong("cedula"));
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }

    /**
     * Actualiza un proveedor existente en la base de datos.
     * @param proveedor El DTO del proveedor con los datos actualizados (debe incluir el ID).
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarProveedor(ProveedorDto proveedor) throws SQLException {
        String sql = "UPDATE proveedores SET nombre = ?, contacto = ?, direccion = ?, idProducto = ?, cedula = ? WHERE idProveedor = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setString(3, proveedor.getDireccion());
            pstmt.setInt(4, proveedor.getIdProducto());
            pstmt.setLong(5, proveedor.getCedula());
            pstmt.setInt(6, proveedor.getIdProveedor());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un proveedor de la base de datos por su ID.
     * @param idProveedor El ID del proveedor a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarProveedor(int idProveedor) throws SQLException {
        String sql = "DELETE FROM proveedores WHERE idProveedor = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProveedor);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Obtiene todos los proveedores asociados a un producto específico.
     * @param idProducto El ID del producto.
     * @return Una lista de ProveedorDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<ProveedorDto> obtenerProveedoresPorIdProducto(int idProducto) throws SQLException {
        List<ProveedorDto> proveedores = new ArrayList<>();
        String sql = "SELECT idProveedor, nombre, contacto, direccion, idProducto,cedula FROM proveedores WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ProveedorDto proveedor = new ProveedorDto();
                    proveedor.setIdProveedor(rs.getInt("idProveedor"));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setContacto(rs.getString("contacto"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    proveedor.setIdProducto(rs.getInt("idProducto"));
                    proveedor.setCedula(rs.getLong("cedula"));
                    proveedores.add(proveedor);
                }
            }
        }
        return proveedores;
    }
}