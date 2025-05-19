package co.edu.unbosque.model.daosYdtos; // Asumiendo el mismo paquete

import co.edu.unbosque.model.DatabaseManager; // Asumiendo que tienes esta clase para la conexión

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDao {

    /**
     * Crea un nuevo producto en la base de datos.
     * El ID generado se actualiza en el objeto ProductoDto.
     * @param producto El DTO del producto a crear.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void crearProducto(ProductoDto producto) throws SQLException {
        // DDL: idProducto (PK, AI), nombre, idCategoriaP, descripcion, precio, IVA
        String sql = "INSERT INTO productos (nombre, idCategoriaP, descripcion, precio, IVA) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, producto.getNombre());
            pstmt.setInt(2, producto.getIdCategoriaP());
            pstmt.setString(3, producto.getDescripcion());
            pstmt.setDouble(4, producto.getPrecio());
            pstmt.setDouble(5, producto.getIva());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        producto.setIdProducto(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Obtiene un producto por su ID.
     * @param idProducto El ID del producto a buscar.
     * @return Un Optional conteniendo el ProductoDto si se encuentra, o un Optional vacío.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Optional<ProductoDto> obtenerProductoPorId(int idProducto) throws SQLException {
        String sql = "SELECT idProducto, nombre, idCategoriaP, descripcion, precio, IVA FROM productos WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProducto);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ProductoDto producto = new ProductoDto();
                    producto.setIdProducto(rs.getInt("idProducto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setIdCategoriaP(rs.getInt("idCategoriaP"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setIva(rs.getDouble("IVA"));
                    return Optional.of(producto);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtiene todos los productos de la base de datos.
     * @return Una lista de ProductoDto.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<ProductoDto> obtenerTodosLosProductos() throws SQLException {
        List<ProductoDto> productos = new ArrayList<>();
        String sql = "SELECT idProducto, nombre, idCategoriaP, descripcion, precio, IVA FROM productos";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                ProductoDto producto = new ProductoDto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setIdCategoriaP(rs.getInt("idCategoriaP"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setIva(rs.getDouble("IVA"));
                productos.add(producto);
            }
        }
        return productos;
    }

    /**
     * Actualiza un producto existente en la base de datos.
     * @param producto El DTO del producto con los datos actualizados (debe incluir el ID).
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean actualizarProducto(ProductoDto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, idCategoriaP = ?, descripcion = ?, precio = ?, IVA = ? WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, producto.getNombre());
            pstmt.setInt(2, producto.getIdCategoriaP());
            pstmt.setString(3, producto.getDescripcion());
            pstmt.setDouble(4, producto.getPrecio());
            pstmt.setDouble(5, producto.getIva());
            pstmt.setInt(6, producto.getIdProducto());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     * @param idProducto El ID del producto a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean eliminarProducto(int idProducto) throws SQLException {
        String sql = "DELETE FROM productos WHERE idProducto = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProducto);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}