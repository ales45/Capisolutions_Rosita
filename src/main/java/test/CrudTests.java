package test; // Colócala en tu paquete de pruebas

import co.edu.unbosque.model.Facada_Model;
import co.edu.unbosque.model.daosYdtos.ProductoDto;
import co.edu.unbosque.model.daosYdtos.TiposPDto;
import co.edu.unbosque.model.daosYdtos.UsuariosDto;

import java.util.List;
import java.util.Optional;

public class CrudTests {

    public static void main(String[] args) {
        System.out.println("INICIANDO PRUEBAS CRUD DEL PROYECTO");
        System.out.println("===================================");
        System.out.println("ADVERTENCIA: Estas pruebas modificarán la base de datos.");
        System.out.println("ADVERTENCIA DE SEGURIDAD: Las contraseñas de usuario se manejan en texto plano en estas pruebas,\n" +
                           "reflejando la implementación actual de los servicios. Implementar hashing seguro es crucial.\n");

        Facada_Model facadaModel = new Facada_Model();

        // Llamar a los métodos de prueba
        testUsuariosCrud(facadaModel);
        testTipoPCrud(facadaModel);
        testProductosCrud(facadaModel);
        // Puedes añadir llamadas a más métodos de prueba para otras entidades aquí

        System.out.println("===================================");
        System.out.println("PRUEBAS CRUD FINALIZADAS");
    }

    public static void testUsuariosCrud(Facada_Model facadaModel) {
        System.out.println("\n--- Iniciando Pruebas CRUD para USUARIOS ---");
        int idUsuarioCreado = -1;

        // 1. CREAR Usuario
        System.out.println("\n1. Probando CREAR Usuario...");
        UsuariosDto nuevoUsuario = facadaModel.getUsuarios().crearUsuario("usuarioTestCrud", "claveSegura123", "tester");
        if (nuevoUsuario != null && nuevoUsuario.getIdUsuario() > 0) {
            idUsuarioCreado = nuevoUsuario.getIdUsuario();
            System.out.println("Usuario CREADO con éxito: " + nuevoUsuario.getUsuario() + " (ID: " + idUsuarioCreado + ")");
        } else {
            System.err.println("FALLO al crear usuario o no se retornó ID.");
            // No continuar con las pruebas de este CRUD si la creación falla
            System.out.println("--- Pruebas CRUD para USUARIOS abortadas ---");
            return;
        }

        // 2. LEER Usuario por ID
        System.out.println("\n2. Probando LEER Usuario por ID (" + idUsuarioCreado + ")...");
        Optional<UsuariosDto> usuarioLeidoOpt = facadaModel.getUsuarios().obtenerUsuarioPorId(idUsuarioCreado);
        if (usuarioLeidoOpt.isPresent()) {
            System.out.println("Usuario LEÍDO con éxito: " + usuarioLeidoOpt.get().getUsuario() + ", Acceso: " + usuarioLeidoOpt.get().getAcceso());
        } else {
            System.err.println("FALLO al leer usuario por ID " + idUsuarioCreado);
        }

        // 3. LEER Todos los Usuarios
        System.out.println("\n3. Probando LEER Todos los Usuarios...");
        List<UsuariosDto> todosLosUsuarios = facadaModel.getUsuarios().obtenerTodosLosUsuarios();
        if (todosLosUsuarios != null && !todosLosUsuarios.isEmpty()) {
            System.out.println("Total de usuarios leídos: " + todosLosUsuarios.size());
            // Opcional: Imprimir detalles de todos o solo del que creamos
            // todosLosUsuarios.forEach(u -> System.out.println("  - Usuario: " + u.getUsuario() + " (ID: " + u.getIdUsuario() + ")"));
        } else {
            System.err.println("FALLO al leer todos los usuarios o lista vacía.");
        }

        // 4. ACTUALIZAR Usuario
        System.out.println("\n4. Probando ACTUALIZAR Usuario ID (" + idUsuarioCreado + ")...");
        boolean actualizado = facadaModel.getUsuarios().actualizarUsuario(idUsuarioCreado, "usuarioTestActualizado", "nuevaClave456", "testerActualizado");
        if (actualizado) {
            System.out.println("Usuario ACTUALIZADO con éxito.");
            Optional<UsuariosDto> usuarioActualizadoOpt = facadaModel.getUsuarios().obtenerUsuarioPorId(idUsuarioCreado);
            if (usuarioActualizadoOpt.isPresent()) {
                System.out.println("Verificación de actualización: " + usuarioActualizadoOpt.get().getUsuario() + ", Acceso: " + usuarioActualizadoOpt.get().getAcceso());
            } else {
                System.err.println("FALLO al verificar la actualización del usuario.");
            }
        } else {
            System.err.println("FALLO al actualizar usuario.");
        }

        // 5. ELIMINAR Usuario
        System.out.println("\n5. Probando ELIMINAR Usuario ID (" + idUsuarioCreado + ")...");
        boolean eliminado = facadaModel.getUsuarios().eliminarUsuario(idUsuarioCreado);
        if (eliminado) {
            System.out.println("Usuario ELIMINADO con éxito.");
            Optional<UsuariosDto> usuarioEliminadoOpt = facadaModel.getUsuarios().obtenerUsuarioPorId(idUsuarioCreado);
            if (!usuarioEliminadoOpt.isPresent()) {
                System.out.println("Verificación de eliminación: Usuario ID " + idUsuarioCreado + " no encontrado (correcto).");
            } else {
                System.err.println("FALLO al verificar la eliminación, el usuario todavía existe.");
            }
        } else {
            System.err.println("FALLO al eliminar usuario.");
        }
        System.out.println("--- Pruebas CRUD para USUARIOS finalizadas ---");
    }

    public static void testTipoPCrud(Facada_Model facadaModel) {
        System.out.println("\n--- Iniciando Pruebas CRUD para TipoP (Categorías) ---");
        int idTipoPCreado = -1;

        // 1. CREAR TipoP
        System.out.println("\n1. Probando CREAR TipoP (Categoría)...");
        TiposPDto nuevoTipoP = facadaModel.getTipoP().crear_tipo_p("Categoría Test CRUD");
        if (nuevoTipoP != null && nuevoTipoP.getIdTipoP() > 0) {
            idTipoPCreado = nuevoTipoP.getIdTipoP();
            System.out.println("TipoP CREADO con éxito: " + nuevoTipoP.getNombre() + " (ID: " + idTipoPCreado + ")");
        } else {
            System.err.println("FALLO al crear TipoP o no se retornó ID.");
            System.out.println("--- Pruebas CRUD para TipoP abortadas ---");
            return;
        }

        // 2. LEER TipoP por ID
        System.out.println("\n2. Probando LEER TipoP por ID (" + idTipoPCreado + ")...");
        Optional<TiposPDto> tipoPLeidoOpt = facadaModel.getTipoP().obtener_tipo_p_por_id(idTipoPCreado);
        if (tipoPLeidoOpt.isPresent()) {
            System.out.println("TipoP LEÍDO con éxito: " + tipoPLeidoOpt.get().getNombre());
        } else {
            System.err.println("FALLO al leer TipoP por ID " + idTipoPCreado);
        }

        // 3. LEER Todos los TiposP
        System.out.println("\n3. Probando LEER Todos los TiposP...");
        List<TiposPDto> todosLosTiposP = facadaModel.getTipoP().obtener_todos_los_tipos_p();
        if (todosLosTiposP != null && !todosLosTiposP.isEmpty()) {
            System.out.println("Total de TiposP leídos: " + todosLosTiposP.size());
        } else {
            System.err.println("FALLO al leer todos los TiposP o lista vacía.");
        }

        // 4. ACTUALIZAR TipoP
        System.out.println("\n4. Probando ACTUALIZAR TipoP ID (" + idTipoPCreado + ")...");
        boolean actualizado = facadaModel.getTipoP().actualizar_tipo_p(idTipoPCreado, "Categoría Test CRUD Actualizada");
        if (actualizado) {
            System.out.println("TipoP ACTUALIZADO con éxito.");
            Optional<TiposPDto> tipoPActualizadoOpt = facadaModel.getTipoP().obtener_tipo_p_por_id(idTipoPCreado);
            if (tipoPActualizadoOpt.isPresent()) {
                System.out.println("Verificación de actualización: " + tipoPActualizadoOpt.get().getNombre());
            } else {
                System.err.println("FALLO al verificar la actualización del TipoP.");
            }
        } else {
            System.err.println("FALLO al actualizar TipoP.");
        }

        // 5. ELIMINAR TipoP
        System.out.println("\n5. Probando ELIMINAR TipoP ID (" + idTipoPCreado + ")...");
        // Antes de eliminar, asegurarse que no hay productos usándolo si hay FK constraints.
        // Para esta prueba aislada, asumimos que se puede eliminar.
        boolean eliminado = facadaModel.getTipoP().eliminar_tipo_p(idTipoPCreado);
        if (eliminado) {
            System.out.println("TipoP ELIMINADO con éxito.");
            Optional<TiposPDto> tipoPEliminadoOpt = facadaModel.getTipoP().obtener_tipo_p_por_id(idTipoPCreado);
            if (!tipoPEliminadoOpt.isPresent()) {
                System.out.println("Verificación de eliminación: TipoP ID " + idTipoPCreado + " no encontrado (correcto).");
            } else {
                System.err.println("FALLO al verificar la eliminación, el TipoP todavía existe.");
            }
        } else {
            System.err.println("FALLO al eliminar TipoP (podría estar en uso por algún producto si hay FK).");
        }
        System.out.println("--- Pruebas CRUD para TipoP finalizadas ---");
    }

    public static void testProductosCrud(Facada_Model facadaModel) {
        System.out.println("\n--- Iniciando Pruebas CRUD para PRODUCTOS ---");
        int idProductoCreado = -1;
        int idCategoriaParaProducto = -1;

        // Prerrequisito: Crear una Categoría (TipoP) para el producto
        System.out.println("\n* Creando categoría de prerrequisito para el producto...");
        TiposPDto categoriaProducto = facadaModel.getTipoP().crear_tipo_p("Categoría para Productos CRUD");
        if (categoriaProducto != null && categoriaProducto.getIdTipoP() > 0) {
            idCategoriaParaProducto = categoriaProducto.getIdTipoP();
            System.out.println("Categoría de prerrequisito CREADA: " + categoriaProducto.getNombre() + " (ID: " + idCategoriaParaProducto + ")");
        } else {
            System.err.println("FALLO al crear categoría de prerrequisito. Abortando pruebas de Productos.");
            System.out.println("--- Pruebas CRUD para PRODUCTOS abortadas ---");
            return;
        }

        // 1. CREAR Producto
        System.out.println("\n1. Probando CREAR Producto...");
        ProductoDto nuevoProducto = facadaModel.getProductos().crearProducto(
                "Producto CRUD Test", "Descripción del producto de prueba CRUD",
                199.99, idCategoriaParaProducto, 0.19);

        if (nuevoProducto != null && nuevoProducto.getIdProducto() > 0) {
            idProductoCreado = nuevoProducto.getIdProducto();
            System.out.println("Producto CREADO con éxito: " + nuevoProducto.getNombre() + " (ID: " + idProductoCreado + ")");
        } else {
            System.err.println("FALLO al crear producto o no se retornó ID.");
            // Limpiar la categoría creada si la creación del producto falla
            if (idCategoriaParaProducto > 0) {
                facadaModel.getTipoP().eliminar_tipo_p(idCategoriaParaProducto);
                System.out.println("* Categoría de prerrequisito (ID: " + idCategoriaParaProducto + ") eliminada tras fallo.");
            }
            System.out.println("--- Pruebas CRUD para PRODUCTOS abortadas ---");
            return;
        }

        // 2. LEER Producto por ID
        System.out.println("\n2. Probando LEER Producto por ID (" + idProductoCreado + ")...");
        Optional<ProductoDto> productoLeidoOpt = facadaModel.getProductos().obtenerProductoPorId(idProductoCreado);
        if (productoLeidoOpt.isPresent()) {
            System.out.println("Producto LEÍDO con éxito: " + productoLeidoOpt.get().getNombre() + ", Precio: " + productoLeidoOpt.get().getPrecio());
        } else {
            System.err.println("FALLO al leer producto por ID " + idProductoCreado);
        }

        // 3. LEER Todos los Productos
        System.out.println("\n3. Probando LEER Todos los Productos...");
        List<ProductoDto> todosLosProductos = facadaModel.getProductos().obtenerTodosLosProductos();
        if (todosLosProductos != null && !todosLosProductos.isEmpty()) {
            System.out.println("Total de productos leídos: " + todosLosProductos.size());
        } else {
            System.err.println("FALLO al leer todos los productos o lista vacía.");
        }

        // 4. ACTUALIZAR Producto
        System.out.println("\n4. Probando ACTUALIZAR Producto ID (" + idProductoCreado + ")...");
        ProductoDto productoParaActualizar = new ProductoDto();
        productoParaActualizar.setIdProducto(idProductoCreado);
        productoParaActualizar.setNombre("Producto CRUD Actualizado");
        productoParaActualizar.setDescripcion("Descripción actualizada del producto CRUD");
        productoParaActualizar.setPrecio(249.50);
        productoParaActualizar.setIdCategoriaP(idCategoriaParaProducto); // Mantener la misma categoría
        productoParaActualizar.setIva(0.16); // Cambiar IVA

        boolean actualizado = facadaModel.getProductos().actualizarProducto(productoParaActualizar);
        if (actualizado) {
            System.out.println("Producto ACTUALIZADO con éxito.");
            Optional<ProductoDto> productoActualizadoOpt = facadaModel.getProductos().obtenerProductoPorId(idProductoCreado);
            if (productoActualizadoOpt.isPresent()) {
                System.out.println("Verificación de actualización: " + productoActualizadoOpt.get().getNombre() + ", Precio: " + productoActualizadoOpt.get().getPrecio() + ", IVA: " + productoActualizadoOpt.get().getIva());
            } else {
                System.err.println("FALLO al verificar la actualización del producto.");
            }
        } else {
            System.err.println("FALLO al actualizar producto.");
        }

        // 5. ELIMINAR Producto
        System.out.println("\n5. Probando ELIMINAR Producto ID (" + idProductoCreado + ")...");
        boolean eliminado = facadaModel.getProductos().eliminarProducto(idProductoCreado);
        if (eliminado) {
            System.out.println("Producto ELIMINADO con éxito.");
            Optional<ProductoDto> productoEliminadoOpt = facadaModel.getProductos().obtenerProductoPorId(idProductoCreado);
            if (!productoEliminadoOpt.isPresent()) {
                System.out.println("Verificación de eliminación: Producto ID " + idProductoCreado + " no encontrado (correcto).");
            } else {
                System.err.println("FALLO al verificar la eliminación, el producto todavía existe.");
            }
        } else {
            System.err.println("FALLO al eliminar producto.");
        }

        // Limpieza: Eliminar la categoría de prerrequisito
        System.out.println("\n* Eliminando categoría de prerrequisito (ID: " + idCategoriaParaProducto + ")...");
        boolean categoriaEliminada = facadaModel.getTipoP().eliminar_tipo_p(idCategoriaParaProducto);
        if (categoriaEliminada) {
            System.out.println("Categoría de prerrequisito ELIMINADA con éxito.");
        } else {
            System.err.println("FALLO al eliminar la categoría de prerrequisito. Puede que necesites eliminarla manualmente si aún existen productos asociados que esta prueba no limpió debido a un fallo anterior.");
        }

        System.out.println("--- Pruebas CRUD para PRODUCTOS finalizadas ---");
    }
}