package co.edu.unbosque.model; // Asumiendo el mismo paquete que las otras clases de servicio

import co.edu.unbosque.model.daosYdtos.InventarioDao;
import co.edu.unbosque.model.daosYdtos.InventarioDto;
// Importar ProductoDao y ProductoDto si se necesita obtener nombres de productos para el reporte.
// import co.edu.unbosque.model.daosYdtos.ProductoDao;
// import co.edu.unbosque.model.daosYdtos.ProductoDto;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Inventario { // Nombre de la clase 'Inventario' tomado del diagrama

    private InventarioDao inventarioDao;
    // private ProductoDao productoDao; // Descomentar si necesitas nombres de productos en productosAgotar

    public Inventario() {
        this.inventarioDao = new InventarioDao();
        // this.productoDao = new ProductoDao(); // Descomentar
    }

    /**
     * Crea un nuevo registro de inventario.
     * Parámetros basados en el diagrama de clases.
     * @param stock Stock actual.
     * @param stockMinimo Stock mínimo permitido.
     * @param ubicacion Ubicación del inventario.
     * @param idProducto ID del producto asociado.
     * @param ultimaActualizacion Fecha de la última actualización (se convertirá a Timestamp).
     * @return El InventarioDto creado (con su ID generado), o null si hubo un error.
     */
    public InventarioDto crearInventario(int stock, int stockMinimo, String ubicacion, int idProducto, java.util.Date ultimaActualizacion) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        if (idProducto <= 0) {
            System.err.println("Error de validación: El ID de producto no es válido.");
            return null;
        }
        if (stock < 0 || stockMinimo < 0) {
            System.err.println("Error de validación: El stock y stock mínimo no pueden ser negativos.");
            return null;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        InventarioDto nuevoInventario = new InventarioDto();
        nuevoInventario.setIdProducto(idProducto);
        nuevoInventario.setStock(stock);
        nuevoInventario.setStockMinimo(stockMinimo);
        nuevoInventario.setUbicacion(ubicacion);
        if (ultimaActualizacion != null) {
            nuevoInventario.setUltimaActualizacion(new Timestamp(ultimaActualizacion.getTime()));
        } else {
            // Establecer a ahora si es null, o manejar como error según la lógica de negocio
             nuevoInventario.setUltimaActualizacion(new Timestamp(System.currentTimeMillis()));
        }


        try {
            inventarioDao.crearInventario(nuevoInventario); // El DAO actualiza el ID en el DTO
            System.out.println("Servicio: Inventario para producto ID " + nuevoInventario.getIdProducto() + " creado con ID: " + nuevoInventario.getIdInventario());
            return nuevoInventario;
        } catch (SQLException e) {
            System.err.println("Error en InventarioService al crear inventario: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Actualiza un registro de inventario existente.
     * Parámetros basados en el diagrama de clases.
     * @param idInventario ID del inventario a actualizar.
     * @param stock Nuevo stock.
     * @param stockMinimo Nuevo stock mínimo.
     * @param ubicacion Nueva ubicación.
     * @param idProducto Nuevo ID de producto.
     * @param ultimaActualizacion Nueva fecha de última actualización.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarInventario(int idInventario, int idProducto, int stock, int stockMinimo, String ubicacion, java.util.Date ultimaActualizacion) {
         if (idInventario <= 0) {
            System.err.println("Error de validación: Se requiere un ID de inventario válido para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES) ---
        // (similar a crearInventario y otras validaciones)
        // --- FIN LÓGICA DE NEGOCIO ---

        InventarioDto inventarioActualizado = new InventarioDto();
        inventarioActualizado.setIdInventario(idInventario);
        inventarioActualizado.setIdProducto(idProducto);
        inventarioActualizado.setStock(stock);
        inventarioActualizado.setStockMinimo(stockMinimo);
        inventarioActualizado.setUbicacion(ubicacion);
        if (ultimaActualizacion != null) {
            inventarioActualizado.setUltimaActualizacion(new Timestamp(ultimaActualizacion.getTime()));
        } else {
             inventarioActualizado.setUltimaActualizacion(new Timestamp(System.currentTimeMillis()));
        }

        try {
            return inventarioDao.actualizarInventario(inventarioActualizado);
        } catch (SQLException e) {
            System.err.println("Error en InventarioService al actualizar inventario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Obtiene un registro de inventario por su ID.
     * @param idInventario El ID del inventario.
     * @return Un Optional que puede contener el InventarioDto si se encuentra.
     */
    public Optional<InventarioDto> obtenerInventarioPorId(int idInventario) {
        try {
            return inventarioDao.obtenerInventarioPorId(idInventario);
        } catch (SQLException e) {
            System.err.println("Error en InventarioService al obtener inventario por ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    /**
     * Obtiene una lista de todos los registros de inventario.
     * @return Una lista de InventarioDto.
     */
    public List<InventarioDto> obtenerTodosLosInventarios() {
        try {
            return inventarioDao.obtenerTodosLosInventarios();
        } catch (SQLException e) {
            System.err.println("Error en InventarioService al obtener todos los inventarios: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Elimina un registro de inventario por su ID.
     * @param idInventario El ID del inventario a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarInventario(int idInventario) {
        try {
            return inventarioDao.eliminarInventario(idInventario);
        } catch (SQLException e) {
            System.err.println("Error en InventarioService al eliminar inventario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Genera un reporte de productos que están por agotarse o agotados.
     * (Nombre del método 'productosAgotar' basado en 'Productos_Agotar' del diagrama)
     * @return Un String con la información de los productos con stock bajo.
     */
    public String productosAgotar() {
        try {
            List<InventarioDto> inventariosBajos = inventarioDao.obtenerInventariosConStockBajo();
            if (inventariosBajos.isEmpty()) {
                return "No hay productos por agotar o con stock bajo.";
            }

            // Para un reporte más completo, podrías obtener los nombres de los productos
            // usando el idProducto de cada InventarioDto y el ProductoDao.
            // Aquí solo se muestra la información del inventario.
            StringBuilder reporte = new StringBuilder("Productos con Stock Bajo o Agotados:\n");
            for (InventarioDto item : inventariosBajos) {
                reporte.append(String.format(
                    " - Inventario ID: %d, Producto ID: %d, Stock Actual: %d, Stock Mínimo: %d, Ubicación: %s\n",
                    item.getIdInventario(),
                    item.getIdProducto(),
                    item.getStock(),
                    item.getStockMinimo(),
                    item.getUbicacion()
                ));
                // Para agregar nombre del producto (necesitarías productoDao y manejar SQLException):
                // Optional<ProductoDto> productoOpt = productoDao.obtenerProductoPorId(item.getIdProducto());
                // if (productoOpt.isPresent()) {
                //     reporte.append(String.format("   Nombre Producto: %s\n", productoOpt.get().getNombre()));
                // }
            }
            return reporte.toString();
        } catch (SQLException e) {
            System.err.println("Error en InventarioService al generar reporte de productos por agotar: " + e.getMessage());
            e.printStackTrace();
            return "Error al generar el reporte de stock bajo.";
        }
    }
}