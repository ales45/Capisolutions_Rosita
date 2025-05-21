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

public class ClientesDao { // Nombre de clase DAO en plural

    /**
     * Crea un nuevo cliente en la base de datos.
     * El ID generado se actualiza en el objeto ClientesDto.
     * @param cliente El DTO del cliente a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearCliente(ClientesDto cliente) throws SQLException {
        // DDL clientes: idCliente (PK, AI), nombre, rol, correo, cedula, telefono
        String sql = "INSERT INTO clientes (nombre, rol, correo, cedula, telefono) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getRol());
            pstmt.setString(3, cliente.getCorreo());
            pstmt.setLong(4, cliente.getCedula());
            pstmt.setLong(5, cliente.getTelefono());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setIdCliente(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene un cliente por su ID.
     * @param idCliente El ID del cliente a buscar.
     * @return Un Optional conteniendo el ClientesDto si se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<ClientesDto> obtenerClientePorId(int idCliente) throws SQLException {
        String sql = "SELECT idCliente, nombre, rol, correo, cedula, telefono FROM clientes WHERE idCliente = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idCliente);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ClientesDto cliente = new ClientesDto();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setRol(rs.getString("rol"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setCedula(rs.getLong("cedula"));
                    cliente.setTelefono(rs.getLong("telefono"));
                    return Optional.of(cliente);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todos los clientes de la base de datos.
     * @return Una lista de ClientesDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<ClientesDto> obtenerTodosLosClientes() throws SQLException {
        List<ClientesDto> clientes = new ArrayList<>();
        String sql = "SELECT idCliente, nombre, rol, correo, cedula, telefono FROM clientes";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                ClientesDto cliente = new ClientesDto();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setRol(rs.getString("rol"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCedula(rs.getLong("cedula"));
                cliente.setTelefono(rs.getLong("telefono"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    /**
     * Actualiza un cliente existente en la base de datos.
     * @param cliente El DTO del cliente con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarCliente(ClientesDto cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, rol = ?, correo = ?, cedula = ?, telefono = ? WHERE idCliente = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getRol());
            pstmt.setString(3, cliente.getCorreo());
            pstmt.setLong(4, cliente.getCedula());
            pstmt.setLong(5, cliente.getTelefono());
            pstmt.setInt(6, cliente.getIdCliente());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un cliente de la base de datos por su ID.
     * @param idCliente El ID del cliente a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE idCliente = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idCliente);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    public Optional<ClientesDto> obtenerClientePorCedula(long cedula) {
        String sql = "SELECT * FROM clientes WHERE cedula = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, cedula);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ClientesDto cliente = new ClientesDto();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setRol(rs.getString("rol"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setCedula(rs.getLong("cedula"));
                    cliente.setTelefono(rs.getLong("telefono"));
                    return Optional.of(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}