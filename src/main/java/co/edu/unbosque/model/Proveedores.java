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
    public ProveedorDto crearProveedor(String nombre, String contacto, String direccion, int idProducto,Long cedula) {
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
        nuevoProveedor.setCedula(cedula);

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
     * @param nombre El nuevo nombre del proveedor.
     * @param contacto La nueva información de contacto.
     * @param direccion La nueva dirección.
     * @param idProducto El nuevo ID de producto asociado.
     * @param cedula La cédula del proveedor.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarProveedor(String nombre, String contacto, String direccion, int idProducto, Long cedula) {
        Optional<ProveedorDto> provedoresopt;
        try {
            provedoresopt = proveedorDao.obtenerProveedorPorCedula(cedula);
        } catch (SQLException e) {
            System.err.println("Error al buscar proveedor por cédula para actualizar: " + e.getMessage());
            e.printStackTrace();
            return false; // Retorna false si hay un error SQL
        }

        if (!provedoresopt.isPresent()) {
            System.err.println("Error: No se encontró el proveedor con cédula " + cedula);
            return false;
        }

        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre del proveedor no puede estar vacío.");
            return false;
        }
        if (contacto == null || contacto.trim().isEmpty()) {
            System.err.println("Error de validación: El contacto del proveedor no puede estar vacío.");
            return false;
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            System.err.println("Error de validación: La dirección del proveedor no puede estar vacía.");
            return false;
        }
        if (idProducto <= 0) {
            System.err.println("Error de validación: El ID de producto asociado no es válido.");
            return false;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        ProveedorDto proveedorActualizado = new ProveedorDto();
        proveedorActualizado.setIdProveedor(provedoresopt.get().getIdProveedor());
        proveedorActualizado.setNombre(nombre);
        proveedorActualizado.setContacto(contacto);
        proveedorActualizado.setDireccion(direccion);
        proveedorActualizado.setIdProducto(idProducto);
        proveedorActualizado.setCedula(cedula);
        
        try {
            return proveedorDao.actualizarProveedor(proveedorActualizado);
        } catch (SQLException e) {
            System.err.println("Error en ProveedoresService al actualizar proveedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un proveedor por su ID.
     * @param cedula La cédula del proveedor.
     * @return Un Optional que puede contener el ProveedorDto si se encuentra.
     */
    public Optional<ProveedorDto> obtenerProveedorPorCedula(long cedula) {
        Optional<ProveedorDto> provedoresopt = Optional.empty();
        try {
            provedoresopt = proveedorDao.obtenerProveedorPorCedula(cedula);
        } catch (SQLException e) {
            System.err.println("Error al buscar proveedor por cédula: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            if (provedoresopt.isPresent()) {
                return proveedorDao.obtenerProveedorPorId(provedoresopt.get().getIdProveedor());
            } else {
                return Optional.empty();
            }
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
     * @param cedula La cédula del proveedor a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarProveedor(Long cedula) {
        Optional<ProveedorDto> provedoresopt = Optional.empty();
        try {
            // 1. Buscar el proveedor por cedula
            provedoresopt = proveedorDao.obtenerProveedorPorCedula(cedula);
        } catch (SQLException e) {
            System.err.println("Error al buscar proveedor por cédula para eliminar: " + e.getMessage());
            e.printStackTrace();
            return false; // Retorna false si hay un error SQL al buscar
        }

        // 2. Verificar si se encontro el proveedor
        if (!provedoresopt.isPresent()) {
            System.err.println("Error: No se encontró el proveedor con cédula " + cedula + " para eliminar.");
            return false; // Retorna false si no se encontro el proveedor
        }
        
        // 3. Obtener el ID del proveedor encontrado
        int idProveedorAEliminar = provedoresopt.get().getIdProveedor();

        // 4. Eliminar el proveedor usando el ID
        try {
            return proveedorDao.eliminarProveedor(idProveedorAEliminar); // Llama al DAO con el ID y retorna el resultado boolean
        } catch (SQLException e) {
            System.err.println("Error en ProveedoresService al eliminar proveedor en la base de datos: " + e.getMessage());
            e.printStackTrace();
            return false; // Retorna false si hay un error SQL al eliminar
        }
    }
}