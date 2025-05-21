package co.edu.unbosque.model; // Asumiendo el mismo paquete

import co.edu.unbosque.model.daosYdtos.ClientesDao; // Actualizado
import co.edu.unbosque.model.daosYdtos.ClientesDto; // Actualizado

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Clientes { // Nombre de clase de servicio en plural

    private ClientesDao clienteDao;

    public Clientes() {
        this.clienteDao = new ClientesDao();
    }

    /**
     * Crea un nuevo cliente.
     * @param nombre El nombre del cliente.
     * @param rol El rol del cliente.
     * @param correo El correo electrónico del cliente.
     * @param cedula La cédula del cliente.
     * @param telefono El teléfono del cliente.
     * @return El ClientesDto creado, o null si hubo un error.
     */
    public ClientesDto crear_cliente(String nombre, String rol, String correo, long cedula, long telefono) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre del cliente no puede estar vacío.");
            return null;
        }
        if (correo == null || !correo.contains("@")) { // Validación simple de correo
            System.err.println("Error de validación: El correo no es válido.");
            return null;
        }
        // Validar cédula y teléfono si es necesario (ej. longitud, solo números)
        // --- FIN LÓGICA DE NEGOCIO ---

        ClientesDto nuevoCliente = new ClientesDto();
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setRol(rol);
        nuevoCliente.setCorreo(correo);
        nuevoCliente.setCedula(cedula);
        nuevoCliente.setTelefono(telefono);

        try {
            clienteDao.crearCliente(nuevoCliente);
            System.out.println("Servicio: Cliente '" + nuevoCliente.getNombre() + "' creado con ID: " + nuevoCliente.getIdCliente());
            return nuevoCliente;
        } catch (SQLException e) {
            System.err.println("Error en ClientesService al crear cliente: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un cliente existente.
     * @param id_cliente El ID del cliente a actualizar (nombre de parámetro como en diagrama).
     * @param nombre El nuevo nombre del cliente.
     * @param rol El nuevo rol del cliente.
     * @param correo El nuevo correo electrónico.
     * @param cedula La nueva cédula.
     * @param telefono El nuevo teléfono.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar_cliente( String nombre, String rol, String correo, long cedula, long telefono) {
        Optional<ClientesDto> clienteOpt = clienteDao.obtenerClientePorCedula(cedula);
        if (!clienteOpt.isPresent()) {
            System.err.println("Error: No se encontró el cliente con cédula " + cedula);
            return false;
        }
        if (clienteOpt.get().getIdCliente() <= 0) {
            System.err.println("Error de validación: Se requiere un ID de cliente válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (nombre == null || nombre.trim().isEmpty()) { /* ... */ }
        // ... más validaciones ...



        // --- FIN LÓGICA DE NEGOCIO ---
        ClientesDto clienteActualizado = new ClientesDto();
        clienteActualizado.setIdCliente(clienteOpt.get().getIdCliente());
        clienteActualizado.setNombre(nombre);
        clienteActualizado.setRol(rol);
        clienteActualizado.setCorreo(correo);
        clienteActualizado.setCedula(cedula);
        clienteActualizado.setTelefono(telefono);
        
        try {
            // Opcional: verificar si el cliente existe.
            // Optional<ClientesDto> existente = clienteDao.obtenerClientePorId(id_cliente);
            // if (!existente.isPresent()) {
            //     System.err.println("Cliente con ID " + id_cliente + " no encontrado para actualizar.");
            //     return false;
            // }
            return clienteDao.actualizarCliente(clienteActualizado);
        } catch (SQLException e) {
            System.err.println("Error en ClientesService al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un cliente por su ID.
     * El nombre del parámetro 'id_cliente' coincide con el diagrama.
     * @param id_cliente El ID del cliente.
     * @return Un Optional que puede contener el ClientesDto si se encuentra.
     */
    public Optional<ClientesDto> ver_cliente(int id_cliente) { // Nombre de método y param como en diagrama
        try {
            return clienteDao.obtenerClientePorId(id_cliente);
        } catch (SQLException e) {
            System.err.println("Error en ClientesService al obtener cliente por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los clientes.
     * @return Una lista de ClientesDto.
     */
    public List<ClientesDto> obtener_todos_los_clientes() { // Nuevo método, siguiendo patrón de nombres
        try {
            return clienteDao.obtenerTodosLosClientes();
        } catch (SQLException e) {
            System.err.println("Error en ClientesService al obtener todos los clientes: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un cliente por su ID.
     * El nombre del parámetro 'id_cliente' coincide con el diagrama.
     * @param id_cliente El ID del cliente a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar_cliente(long cedula) { // Nombre de método y param como en diagrama
        // Lógica de negocio: verificar si el cliente tiene facturas asociadas antes de eliminar.
        // if (facturaDao.existenFacturasParaCliente(id_cliente)) {
        //    System.err.println("No se puede eliminar el cliente ID " + id_cliente + " porque tiene facturas asociadas.");
        //    return false;
        // }
        Optional<ClientesDto> clienteOpt = clienteDao.obtenerClientePorCedula(cedula);
        if (!clienteOpt.isPresent()) {
            System.err.println("Error: No se encontró el cliente con cédula " + cedula);
            return false;
        }
        if (clienteOpt.get().getIdCliente() <= 0) {
            System.err.println("Error de validación: Se requiere un ID de cliente válido para actualizar.");
            return false;
        }

        try {
            return clienteDao.eliminarCliente(clienteOpt.get().getIdCliente());
        } catch (SQLException e) {
            System.err.println("Error en ClientesService al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}