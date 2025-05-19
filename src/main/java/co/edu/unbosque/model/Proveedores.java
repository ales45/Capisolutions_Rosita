package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.ProveedorDao;
import co.edu.unbosque.model.daosYdtos.ProveedorDto;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Proveedores { // Usando 'ProveedoresService' para seguir patrón, o 'Proveedores' si prefieres.

    private ProveedorDao proveedorDao;

    public Proveedores() {
        this.proveedorDao = new ProveedorDao();
    }

    /**
     * Crea un nuevo proveedor.
     * @param nombre El nombre del proveedor.
     * @param contacto La información de contacto del proveedor.
     * @param direccion La dirección del proveedor.
     * @param idProducto El ID del producto asociado (según DDL).
     * @return El ProveedorDto creado, o null si hubo un error.
     */
    public ProveedorDto crearProveedor(String nombre, String contacto, String direccion, int idProducto) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre del proveedor no puede estar vacío.");
            return null;
        }
        if (idProducto <= 0) { // Asumiendo que el producto debe ser válido
            System.err.println("Error de validación: El ID de producto asociado no es válido.");
            return null;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        ProveedorDto nuevoProveedor = new ProveedorDto();
        nuevoProveedor.setNombre(nombre);
        nuevoProveedor.setContacto(contacto);
        nuevoProveedor.setDireccion(direccion);
        nuevoProveedor.setIdProducto(idProducto);

        try {
            proveedorDao.crearProveedor(nuevoProveedor);
            System.out.println("Servicio: Proveedor '" + nuevoProveedor.getNombre() + "' creado con ID: " + nuevoProveedor.getIdProveedor());
            return nuevoProveedor;
        } catch (SQLException e) {
            System.err.println("Error en ProveedoresService al crear proveedor: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un proveedor existente.
     * @param idProveedor El ID del proveedor a actualizar.
     * @param nombre El nuevo nombre del proveedor.
     * @param contacto La nueva información de contacto.
     * @param direccion La nueva dirección.
     * @param idProducto El nuevo ID de producto asociado.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarProveedor(int idProveedor, String nombre, String contacto, String direccion, int idProducto) {
        if (idProveedor <= 0) {
            System.err.println("Error de validación: Se requiere un ID de proveedor válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (nombre == null || nombre.trim().isEmpty()) { /* ... */ }
        // ... más validaciones ...
        // --- FIN LÓGICA DE NEGOCIO ---

        ProveedorDto proveedorActualizado = new ProveedorDto();
        proveedorActualizado.setIdProveedor(idProveedor);
        proveedorActualizado.setNombre(nombre);
        proveedorActualizado.setContacto(contacto);
        proveedorActualizado.setDireccion(direccion);
        proveedorActualizado.setIdProducto(idProducto);
        
        try {
            // Opcional: verificar si el proveedor existe.
            // Optional<ProveedorDto> existente = proveedorDao.obtenerProveedorPorId(idProveedor);
            // if (!existente.isPresent()) {
            //     System.err.println("Proveedor con ID " + idProveedor + " no encontrado para actualizar.");
            //     return false;
            // }
            return proveedorDao.actualizarProveedor(proveedorActualizado);
        } catch (SQLException e) {
            System.err.println("Error en ProveedoresService al actualizar proveedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un proveedor por su ID.
     * @param idProveedor El ID del proveedor.
     * @return Un Optional que puede contener el ProveedorDto si se encuentra.
     */
    public Optional<ProveedorDto> obtenerProveedorPorId(int idProveedor) {
        try {
            return proveedorDao.obtenerProveedorPorId(idProveedor);
        } catch (SQLException e) {
            System.err.println("Error en ProveedoresService al obtener proveedor por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los proveedores.
     * @return Una lista de ProveedorDto.
     */
    public List<ProveedorDto> obtenerTodosLosProveedores() {
        try {
            return proveedorDao.obtenerTodosLosProveedores();
        } catch (SQLException e) {
            System.err.println("Error en ProveedoresService al obtener todos los proveedores: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene una lista de proveedores asociados a un ID de producto específico.
     * @param idProducto El ID del producto.
     * @return Una lista de ProveedorDto.
     */
    public List<ProveedorDto> obtenerProveedoresPorIdProducto(int idProducto) {
        if (idProducto <= 0) {
            System.err.println("ID de producto no válido.");
            return Collections.emptyList();
        }
        try {
            return proveedorDao.obtenerProveedoresPorIdProducto(idProducto);
        } catch (SQLException e) {
            System.err.println("Error en ProveedoresService al obtener proveedores por ID de producto: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un proveedor por su ID.
     * @param idProveedor El ID del proveedor a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarProveedor(int idProveedor) {
        try {
            return proveedorDao.eliminarProveedor(idProveedor);
        } catch (SQLException e) {
            System.err.println("Error en ProveedoresService al eliminar proveedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}