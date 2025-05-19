package co.edu.unbosque.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
// Importa TODAS tus clases JFrame de la vista aquí
// Asegúrate de que todas tus clases de vista estén en el paquete co.edu.unbosque.view
// o ajusta los imports si alguna está en otro paquete (como User_View en prueba_usuarios).
// Idealmente, todas deberían estar en co.edu.unbosque.view.

public class Facada_Vista_View {

    // Vistas Principales / Menús de Módulos
    private Facada_Vista_login loginView;
    private Admin_View adminView;
    private User_View userView; // Si la mueves a co.edu.unbosque.view, este import es directo.
                                // Si sigue en prueba_usuarios, necesitarás: import prueba_usuarios.User_View;
    private MVentas_View mVentasView;
    private MClientes_View mClientesView;
    private MProductos_Frame mProductosFrame;
    private MProveedores_Frame mProveedoresFrame; // O MProveedores_View, decide cuál usar como principal para el módulo
    private MReportes_Frame mReportesFrame;

    // Vistas de Operaciones/CRUD para Clientes
    private NuevoCliente_Frame nuevoClienteFrame;
    private EditarCliente_Frame editarClienteFrame;
    private EliminarCliente_Frame eliminarClienteFrame;
    private VerCliente_Frame verClienteFrame;

    // Vistas de Operaciones/CRUD para Productos
    private NuevoProducto_Frame nuevoProductoFrame;
    private EditarProducto_Frame editarProductoFrame;
    private EliminarProducto_Frame eliminarProductoFrame;
    private VerProducto_Frame verProductoFrame;

    // Vistas de Operaciones/CRUD para Proveedores
    private RegistroProve_Frame registroProveFrame;
    private EditarProve_Frame editarProveFrame;
    private VerProve_Frame verProveFrame;
    private RegistroPedido_Frame registroPedidoFrame; // Para registrar pedidos a proveedores
    private VerPedidos_Frame verPedidosFrame;         // Para ver pedidos a proveedores
    // Nota: Devoluciones_Frame se usa tanto para ventas como para proveedores, podrías necesitar dos instancias
    // o pasarle un contexto si su comportamiento cambia. Aquí la manejaremos como una genérica.
    private Devoluciones_Frame devolucionProveedoresFrame; // Específica para devoluciones a proveedor

    // Vistas de Operaciones/CRUD para Ventas
    private NuevaVenta_View nuevaVentaView;
    private EliminarVenta_Frame eliminarVentaFrame;
    private HistorialVentasUI historialVentasUI;
    private Devoluciones_Frame devolucionVentasFrame; // Específica para devoluciones de clientes/ventas


    // Para gestionar la vista "principal" activa y cerrarla al cambiar a otra principal.
    private JFrame vistaPrincipalActual;

    public Facada_Vista_View() {
        // Las vistas se crearán cuando se llamen por primera vez (lazy instantiation)
    }

    // --- Métodos para Mostrar Vistas Principales (Login, Admin, User) ---

    public void mostrarLoginView() {
        cerrarVistaPrincipalActual();
        if (loginView == null) {
            loginView = new Facada_Vista_login();
            // Para la ventana de login, usualmente se sale de la app si se cierra.
            loginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        loginView.setVisible(true);
        vistaPrincipalActual = loginView;
    }

    public void mostrarAdminView() {
        cerrarVistaPrincipalActual();
        if (adminView == null) {
            adminView = new Admin_View();
            adminView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        adminView.setVisible(true);
        vistaPrincipalActual = adminView;
    }

    public void mostrarUserView() {
        cerrarVistaPrincipalActual();
        if (userView == null) {
            // Asegúrate de que la clase User_View esté en co.edu.unbosque.view
            // o ajusta el import si la dejas en `prueba_usuarios`
            userView = new User_View(); // Si está en otro paquete: new prueba_usuarios.User_View();
            userView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        userView.setVisible(true);
        vistaPrincipalActual = userView;
    }

    // --- Métodos para Mostrar Menús de Módulos (desde Admin o User View) ---
    // Estos reemplazan la vista principal actual (Admin/User) con el módulo.

    public void mostrarModuloVentas() {
        cerrarVistaPrincipalActual();
        if (mVentasView == null) {
            mVentasView = new MVentas_View();
            mVentasView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // O EXIT_ON_CLOSE si es el único camino
        }
        mVentasView.setVisible(true);
        vistaPrincipalActual = mVentasView;
    }

    public void mostrarModuloClientes() {
        cerrarVistaPrincipalActual();
        if (mClientesView == null) {
            mClientesView = new MClientes_View();
            mClientesView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        mClientesView.setVisible(true);
        vistaPrincipalActual = mClientesView;
    }

    public void mostrarModuloProductos() {
        cerrarVistaPrincipalActual();
        if (mProductosFrame == null) {
            mProductosFrame = new MProductos_Frame();
            mProductosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        mProductosFrame.setVisible(true);
        vistaPrincipalActual = mProductosFrame;
    }

    public void mostrarModuloProveedores() {
        cerrarVistaPrincipalActual();
        if (mProveedoresFrame == null) { // Usando MProveedores_Frame como la principal del módulo
            mProveedoresFrame = new MProveedores_Frame();
            mProveedoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        mProveedoresFrame.setVisible(true);
        vistaPrincipalActual = mProveedoresFrame;
    }

    public void mostrarModuloReportes() {
        cerrarVistaPrincipalActual();
        if (mReportesFrame == null) {
            mReportesFrame = new MReportes_Frame();
            mReportesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        mReportesFrame.setVisible(true);
        vistaPrincipalActual = mReportesFrame;
    }

    // --- Métodos para Mostrar Vistas Secundarias (CRUD, operaciones específicas) ---
    // Estas vistas usualmente se abren "encima" o como diálogos y se cierran con DISPOSE_ON_CLOSE.
    // No necesariamente reemplazan la vistaPrincipalActual.

    // Clientes
    public void mostrarNuevoClienteFrame() {
        if (nuevoClienteFrame == null || !nuevoClienteFrame.isDisplayable()) { // Crear si es null o si fue disposed
            nuevoClienteFrame = new NuevoCliente_Frame();
            nuevoClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        nuevoClienteFrame.setVisible(true);
        nuevoClienteFrame.toFront(); // Traer al frente
    }
    public void mostrarEditarClienteFrame() {
        if (editarClienteFrame == null || !editarClienteFrame.isDisplayable()) {
            editarClienteFrame = new EditarCliente_Frame();
            editarClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        editarClienteFrame.setVisible(true);
        editarClienteFrame.toFront();
    }
    public void mostrarEliminarClienteFrame() {
        if (eliminarClienteFrame == null || !eliminarClienteFrame.isDisplayable()) {
            eliminarClienteFrame = new EliminarCliente_Frame();
            eliminarClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        eliminarClienteFrame.setVisible(true);
        eliminarClienteFrame.toFront();
    }
    public void mostrarVerClienteFrame() {
        if (verClienteFrame == null || !verClienteFrame.isDisplayable()) {
            verClienteFrame = new VerCliente_Frame();
            verClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             // Recordatorio: VerCliente_Frame tiene lógica de BD directa que hay que refactorizar.
        }
        verClienteFrame.setVisible(true);
        verClienteFrame.toFront();
    }

    // Productos
    public void mostrarNuevoProductoFrame() {
        if (nuevoProductoFrame == null || !nuevoProductoFrame.isDisplayable()) {
            nuevoProductoFrame = new NuevoProducto_Frame();
            nuevoProductoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        nuevoProductoFrame.setVisible(true);
        nuevoProductoFrame.toFront();
    }
    public void mostrarEditarProductoFrame() {
        if (editarProductoFrame == null || !editarProductoFrame.isDisplayable()) {
            editarProductoFrame = new EditarProducto_Frame();
            editarProductoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        editarProductoFrame.setVisible(true);
        editarProductoFrame.toFront();
    }
    public void mostrarEliminarProductoFrame() {
        if (eliminarProductoFrame == null || !eliminarProductoFrame.isDisplayable()) {
            eliminarProductoFrame = new EliminarProducto_Frame();
            eliminarProductoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        eliminarProductoFrame.setVisible(true);
        eliminarProductoFrame.toFront();
    }
    public void mostrarVerProductoFrame() {
        if (verProductoFrame == null || !verProductoFrame.isDisplayable()) {
            verProductoFrame = new VerProducto_Frame();
            verProductoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        verProductoFrame.setVisible(true);
        verProductoFrame.toFront();
    }

    // Proveedores
    public void mostrarRegistroProveFrame() {
        if (registroProveFrame == null || !registroProveFrame.isDisplayable()) {
            registroProveFrame = new RegistroProve_Frame();
            registroProveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        registroProveFrame.setVisible(true);
        registroProveFrame.toFront();
    }
    public void mostrarEditarProveFrame() {
        if (editarProveFrame == null || !editarProveFrame.isDisplayable()) {
            editarProveFrame = new EditarProve_Frame();
            editarProveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        editarProveFrame.setVisible(true);
        editarProveFrame.toFront();
    }
    public void mostrarVerProveFrame() {
        if (verProveFrame == null || !verProveFrame.isDisplayable()) {
            verProveFrame = new VerProve_Frame();
            verProveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        verProveFrame.setVisible(true);
        verProveFrame.toFront();
    }
    public void mostrarRegistroPedidoFrame() {
        if (registroPedidoFrame == null || !registroPedidoFrame.isDisplayable()) {
            registroPedidoFrame = new RegistroPedido_Frame();
            registroPedidoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        registroPedidoFrame.setVisible(true);
        registroPedidoFrame.toFront();
    }
    public void mostrarVerPedidosFrame() {
        if (verPedidosFrame == null || !verPedidosFrame.isDisplayable()) {
            verPedidosFrame = new VerPedidos_Frame();
            verPedidosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        verPedidosFrame.setVisible(true);
        verPedidosFrame.toFront();
    }
    public void mostrarDevolucionProveedoresFrame() {
        if (devolucionProveedoresFrame == null || !devolucionProveedoresFrame.isDisplayable()) {
            devolucionProveedoresFrame = new Devoluciones_Frame(); // Asumiendo que es la misma clase
            devolucionProveedoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            devolucionProveedoresFrame.setTitle("Devolución a Proveedor"); // Podrías cambiar el título si es necesario
        }
        devolucionProveedoresFrame.setVisible(true);
        devolucionProveedoresFrame.toFront();
    }


    // Ventas
    public void mostrarNuevaVentaView() {
        if (nuevaVentaView == null || !nuevaVentaView.isDisplayable()) {
            nuevaVentaView = new NuevaVenta_View();
            nuevaVentaView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        nuevaVentaView.setVisible(true);
        nuevaVentaView.toFront();
    }
    public void mostrarEliminarVentaFrame() {
        if (eliminarVentaFrame == null || !eliminarVentaFrame.isDisplayable()) {
            eliminarVentaFrame = new EliminarVenta_Frame();
            eliminarVentaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        eliminarVentaFrame.setVisible(true);
        eliminarVentaFrame.toFront();
    }
    public void mostrarHistorialVentasUI() {
        if (historialVentasUI == null || !historialVentasUI.isDisplayable()) {
            historialVentasUI = new HistorialVentasUI();
            historialVentasUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        historialVentasUI.setVisible(true);
        historialVentasUI.toFront();
    }
    public void mostrarDevolucionVentasFrame() {
        if (devolucionVentasFrame == null || !devolucionVentasFrame.isDisplayable()) {
            devolucionVentasFrame = new Devoluciones_Frame();
            devolucionVentasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            devolucionVentasFrame.setTitle("Devolución de Venta");
        }
        devolucionVentasFrame.setVisible(true);
        devolucionVentasFrame.toFront();
    }


    // --- Métodos de Utilidad ---

    public void cerrarVistaPrincipalActual() {
        if (vistaPrincipalActual != null && vistaPrincipalActual.isDisplayable()) {
            vistaPrincipalActual.dispose(); // Cierra la ventana y libera recursos
        }
        vistaPrincipalActual = null;
    }

    public void cerrarVentanaEspecifica(JFrame frame) {
        if (frame != null && frame.isDisplayable()) {
            frame.dispose();
        }
    }

    public void mostrarError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    // --- Getters para que el Controller acceda a las instancias de las vistas ---
    // (Necesarios para añadir ActionListeners a sus componentes)

    public Facada_Vista_login getLoginView() {
        if (loginView == null) loginView = new Facada_Vista_login();
        return loginView;
    }
    public Admin_View getAdminView() {
        if (adminView == null) adminView = new Admin_View();
        return adminView;
    }
    public User_View getUserView() {
        if (userView == null) userView = new User_View();
        return userView;
    }
    public MVentas_View getMVentasView() {
        if (mVentasView == null) mVentasView = new MVentas_View();
        return mVentasView;
    }
    public MClientes_View getMClientesView() {
        if (mClientesView == null) mClientesView = new MClientes_View();
        return mClientesView;
    }
    public MProductos_Frame getMProductosFrame() {
        if (mProductosFrame == null) mProductosFrame = new MProductos_Frame();
        return mProductosFrame;
    }
    public MProveedores_Frame getMProveedoresFrame() {
        if (mProveedoresFrame == null) mProveedoresFrame = new MProveedores_Frame();
        return mProveedoresFrame;
    }
    public MReportes_Frame getMReportesFrame() {
        if (mReportesFrame == null) mReportesFrame = new MReportes_Frame();
        return mReportesFrame;
    }
    public NuevoCliente_Frame getNuevoClienteFrame() {
        if (nuevoClienteFrame == null) nuevoClienteFrame = new NuevoCliente_Frame();
        return nuevoClienteFrame;
    }
    public EditarCliente_Frame getEditarClienteFrame() {
        if (editarClienteFrame == null) editarClienteFrame = new EditarCliente_Frame();
        return editarClienteFrame;
    }
    public EliminarCliente_Frame getEliminarClienteFrame() {
        if (eliminarClienteFrame == null) eliminarClienteFrame = new EliminarCliente_Frame();
        return eliminarClienteFrame;
    }
    public VerCliente_Frame getVerClienteFrame() {
        if (verClienteFrame == null) verClienteFrame = new VerCliente_Frame();
        return verClienteFrame;
    }
    public NuevoProducto_Frame getNuevoProductoFrame() {
        if (nuevoProductoFrame == null) nuevoProductoFrame = new NuevoProducto_Frame();
        return nuevoProductoFrame;
    }
    public EditarProducto_Frame getEditarProductoFrame() {
        if (editarProductoFrame == null) editarProductoFrame = new EditarProducto_Frame();
        return editarProductoFrame;
    }
    public EliminarProducto_Frame getEliminarProductoFrame() {
        if (eliminarProductoFrame == null) eliminarProductoFrame = new EliminarProducto_Frame();
        return eliminarProductoFrame;
    }
    public VerProducto_Frame getVerProductoFrame() {
        if (verProductoFrame == null) verProductoFrame = new VerProducto_Frame();
        return verProductoFrame;
    }
    public RegistroProve_Frame getRegistroProveFrame() {
        if (registroProveFrame == null) registroProveFrame = new RegistroProve_Frame();
        return registroProveFrame;
    }
    public EditarProve_Frame getEditarProveFrame() {
        if (editarProveFrame == null) editarProveFrame = new EditarProve_Frame();
        return editarProveFrame;
    }
    public VerProve_Frame getVerProveFrame() {
        if (verProveFrame == null) verProveFrame = new VerProve_Frame();
        return verProveFrame;
    }
    public RegistroPedido_Frame getRegistroPedidoFrame() {
        if (registroPedidoFrame == null) registroPedidoFrame = new RegistroPedido_Frame();
        return registroPedidoFrame;
    }
    public VerPedidos_Frame getVerPedidosFrame() {
        if (verPedidosFrame == null) verPedidosFrame = new VerPedidos_Frame();
        return verPedidosFrame;
    }
    public Devoluciones_Frame getDevolucionProveedoresFrame() {
        if (devolucionProveedoresFrame == null) {
            devolucionProveedoresFrame = new Devoluciones_Frame();
            devolucionProveedoresFrame.setTitle("Devolución a Proveedor");
        }
        return devolucionProveedoresFrame;
    }
    public NuevaVenta_View getNuevaVentaView() {
        if (nuevaVentaView == null) nuevaVentaView = new NuevaVenta_View();
        return nuevaVentaView;
    }
    public EliminarVenta_Frame getEliminarVentaFrame() {
        if (eliminarVentaFrame == null) eliminarVentaFrame = new EliminarVenta_Frame();
        return eliminarVentaFrame;
    }
    public HistorialVentasUI getHistorialVentasUI() {
        if (historialVentasUI == null) historialVentasUI = new HistorialVentasUI();
        return historialVentasUI;
    }
    public Devoluciones_Frame getDevolucionVentasFrame() {
        if (devolucionVentasFrame == null) {
            devolucionVentasFrame = new Devoluciones_Frame();
            devolucionVentasFrame.setTitle("Devolución de Venta");
        }
        return devolucionVentasFrame;
    }
}