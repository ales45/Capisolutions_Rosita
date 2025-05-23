package co.edu.unbosque.model; // Asumiendo el mismo paquete que Usuarios (service)

import co.edu.unbosque.model.daosYdtos.ProductoDao;
import co.edu.unbosque.model.daosYdtos.ProductoDto;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Clase de servicio (o fachada) para gestionar la lógica de negocio de los Productos.
 * Esta clase utiliza ProductoDao para el acceso a datos.
 * (Nombre de la clase 'Productos' tomado del diagrama)
 */
public class Productos {

    private ProductoDao productoDao;

    public Productos() {
        this.productoDao = new ProductoDao();
    }

    /**
     * Crea un nuevo producto.
     * (Parámetros basados en el diagrama de clases)
     * @param nombre El nombre del producto.
     * @param descripcion La descripción del producto.
     * @param precio El precio del producto.
     * @param idCategoria La ID de la categoría del producto (referencia a CategoriaP.idProducto).
     * @param iva El IVA aplicable al producto.
     * @return El ProductoDto del producto creado (con su ID generado), o null si hubo un error.
     */
    public ProductoDto crearProducto(String nombre, String descripcion, double precio, int idCategoria, double iva) {
        // --- LÓGICA DE NEGOCIO (VALIDACIONES, ETC.) ---
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre del producto no puede estar vacío.");
            return null;
        }
        if (precio < 0) {
            System.err.println("Error de validación: El precio no puede ser negativo.");
            return null;
        }
        // Más validaciones aquí (ej. idCategoria existe, iva en rango)
        // --- FIN LÓGICA DE NEGOCIO ---

        ProductoDto nuevoProducto = new ProductoDto();
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setDescripcion(descripcion);
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setIdCategoriaP(idCategoria); // Mapeo del parámetro idCategoria a idCategoriaP del DTO
        nuevoProducto.setIva(iva);

        try {
            productoDao.crearProducto(nuevoProducto); // El DAO actualiza el ID en el DTO
            System.out.println("Servicio: Producto '" + nuevoProducto.getNombre() + "' creado con ID: " + nuevoProducto.getIdProducto());
            return nuevoProducto;
        } catch (SQLException e) {
            System.err.println("Error en ProductosService al crear producto: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene un producto por su ID.
     * (Interpretado desde ver_producto(id_producto:int) del diagrama)
     * @param idProducto El ID del producto.
     * @return Un Optional que puede contener el ProductoDto si se encuentra.
     */
    public Optional<ProductoDto> obtenerProductoPorId(String nombre) { // Mantiene el nombre 'obtenerProductoPorId' pero busca por 'nombre'
        // 1. Validacion basica de la entrada
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("Error de validación: El nombre del producto para buscar no puede estar vacío.");
            return Optional.empty();
        }
        // 2. Llama al metodo del DAO que busca por nombre.
        //    El DAO ya maneja su propia SQLException y devuelve Optional.empty() en caso de error o no encontrado.
        try {
            Optional<ProductoDto> productopt = productoDao.obtenerProductoPorNombre(nombre);
            // 3. Opcional: Puedes añadir un log si el producto no se encuentra (el DAO ya lo haria en caso de error de BD)
            // if (!productopt.isPresent()) {
            //     System.out.println("Servicio: No se encontró producto con nombre '" + nombre + "'.");
            // }
            // 4. Devuelve el resultado del DAO
            return productopt;
        } catch (SQLException e) {
            System.err.println("Error en ProductosService al buscar producto por nombre: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty(); // Retorna vacío si ocurre un error SQL
        }
    }
    public Optional<ProductoDto> obtenerProductoPorId1(int id) { // Mantiene el nombre 'obtenerProductoPorId' pero busca por 'nombre'
    // 1. Validacion basica de la entrada

    // 2. Llama al metodo del DAO que busca por nombre.
    //    El DAO ya maneja su propia SQLException y devuelve Optional.empty() en caso de error o no encontrado.
    try {
        Optional<ProductoDto> productopt = productoDao.obtenerProductoPorId(id);
        // 3. Opcional: Puedes añadir un log si el producto no se encuentra (el DAO ya lo haria en caso de error de BD)
        // if (!productopt.isPresent()) {
        //     System.out.println("Servicio: No se encontró producto con nombre '" + nombre + "'.");
        // }
        // 4. Devuelve el resultado del DAO
        return productopt;
    } catch (SQLException e) {
        System.err.println("Error en ProductosService al buscar producto por nombre: " + e.getMessage());
        e.printStackTrace();
        return Optional.empty(); // Retorna vacío si ocurre un error SQL
    }
}
    
    /**
     * Obtiene una lista de todos los productos.
     * @return Una lista de ProductoDto. Estará vacía si no hay productos o si ocurre un error.
     */
    public List<ProductoDto> obtenerTodosLosProductos() {
        try {
            return productoDao.obtenerTodosLosProductos();
        } catch (SQLException e) {
            System.err.println("Error en ProductosService al obtener todos los productos: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Actualiza un producto existente.
     * (Modificado para tomar ProductoDto para mayor flexibilidad, en lugar de los params limitados del diagrama)
     * @param productoAActualizar El DTO del producto con los datos actualizados (debe incluir el ID).
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarProducto(String nombre,int idCategoriaP,String descripcion,double precio,double iva) {
        Optional<ProductoDto> productopt;
        try {
            productopt = productoDao.obtenerProductoPorNombre(nombre);
        } catch (SQLException e) {
            System.err.println("Error en ProductosService al buscar producto por nombre para actualizar: " + e.getMessage());
            e.printStackTrace();
            return false; // Retorna false si ocurre un error SQL al buscar
        }

        if (!productopt.isPresent()) {
            System.err.println("Error: No se encontró el producto con nombre " + nombre + " para actualizar.");
            return false;
        }
        
        ProductoDto productoAActualizar = productopt.get(); // Usar el DTO encontrado
        // Mantener el ID del producto encontrado, solo actualizar los otros campos
        // productoAActualizar.setIdProducto(productopt.get().getIdProducto()); // Esto ya está en productopt
        productoAActualizar.setNombre(nombre); // Asegurar que el nombre (clave de búsqueda) este en el DTO
        productoAActualizar.setIdCategoriaP(idCategoriaP);
        productoAActualizar.setDescripcion(descripcion);
        productoAActualizar.setPrecio(precio);
        productoAActualizar.setIva(iva);

        if (productoAActualizar == null || productoAActualizar.getIdProducto() <= 0) {
            System.err.println("Error de validación: Se requiere un producto válido con ID para actualizar.");
            return false;
        }
        // --- LÓGICA DE NEGOCIO (VALIDACIONES SIMILARES A crearProducto) ---
         if (productoAActualizar.getNombre() == null || productoAActualizar.getNombre().trim().isEmpty()) {
            System.err.println("Error de validación: El nombre del producto no puede estar vacío al actualizar.");
            return false;
        }
        if (productoAActualizar.getPrecio() < 0) {
            System.err.println("Error de validación: El precio no puede ser negativo al actualizar.");
            return false;
        }
        // --- FIN LÓGICA DE NEGOCIO ---

        try {
            return productoDao.actualizarProducto(productoAActualizar);
        } catch (SQLException e) {
            System.err.println("Error en ProductosService al actualizar producto en la base de datos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un producto por su ID.
     * @param idProducto El ID del producto a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarProducto(int idProducto) {
        // Lógica de negocio (ej. verificar dependencias antes de eliminar)
        try {
            return productoDao.eliminarProducto(idProducto);
        } catch (SQLException e) {
            System.err.println("Error en ProductosService al eliminar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}