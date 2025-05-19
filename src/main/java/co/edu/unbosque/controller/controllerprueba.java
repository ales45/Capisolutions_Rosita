package co.edu.unbosque.controller;

import co.edu.unbosque.model.Facada_Model;
import co.edu.unbosque.view.*; // Importa todas tus clases de vista del paquete view

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controllerprueba implements ActionListener {
    private Facada_Model modelo;
    private Facada_Vista_View vistaFacade;

    private String tipoUsuarioLogueado = ""; // Para simular roles: "admin", "user"

    public controllerprueba() {
        this.modelo = new Facada_Model(); // Se instancia, pero no se usará para lógica de BD en este paso
        this.vistaFacade = new Facada_Vista_View();

        SwingUtilities.invokeLater(() -> {
            vistaFacade.mostrarLoginView(); // Empezamos con la vista de login
            asignarTodosLosActionListeners();
        });
    }

    private void asignarTodosLosActionListeners() {
        // LOGIN
        Facada_Vista_login loginView = vistaFacade.getLoginView();
        if (loginView != null && loginView.getBtnInicio() != null) {
            loginView.getBtnInicio().setActionCommand("LOGIN_INICIAR_SESION"); // Coincide con tu lógica anterior
            loginView.getBtnInicio().addActionListener(this);
        }

        // ADMIN VIEW
        Admin_View adminView = vistaFacade.getAdminView();
        if (adminView != null) {
            adminView.getBtnventas().setActionCommand("adminVentas"); adminView.getBtnventas().addActionListener(this);
            adminView.getBtnclientes().setActionCommand("adminClientes"); adminView.getBtnclientes().addActionListener(this);
            adminView.getBtnproducto().setActionCommand("adminProductos"); adminView.getBtnproducto().addActionListener(this);
            adminView.getBtnproveedor().setActionCommand("adminProveedores"); adminView.getBtnproveedor().addActionListener(this);
            adminView.getBtnReportes().setActionCommand("adminReportes"); adminView.getBtnReportes().addActionListener(this);
            adminView.getBtnCerrarSesion().setActionCommand("cerrarSesion"); adminView.getBtnCerrarSesion().addActionListener(this);
        }

        // USER VIEW (Asegúrate que User_View tenga los getters y esté en co.edu.unbosque.view)
        User_View userView = vistaFacade.getUserView();
        if (userView != null) {
            userView.getBtnventas().setActionCommand("abrirVentas"); userView.getBtnventas().addActionListener(this);
            userView.getBtnclientes().setActionCommand("abrirClientes"); userView.getBtnclientes().addActionListener(this);
            userView.getBtnReportes().setActionCommand("abrirReportes"); userView.getBtnReportes().addActionListener(this);
            userView.getBtnCerrarSesion().setActionCommand("cerrarSesion"); userView.getBtnCerrarSesion().addActionListener(this);
        }
        
        // MÓDULOS PRINCIPALES Y SUS BOTONES "REGRESAR" Y BOTONES INTERNOS
        setupListenersModuloVentas(vistaFacade.getMVentasView());
        setupListenersModuloClientes(vistaFacade.getMClientesView());
        setupListenersModuloProductos(vistaFacade.getMProductosFrame());
        setupListenersModuloProveedores(vistaFacade.getMProveedoresFrame()); // o MProveedores_View
        setupListenersModuloReportes(vistaFacade.getMReportesFrame());
        
        // SUB-VISTAS (principalmente botones "Regresar" y cualquier otro que no sea de guardado de datos)
        setupListenersSubVistasClientes();
        setupListenersSubVistasProductos();
        setupListenersSubVistasVentas();
        setupListenersSubVistasProveedores();
    }
    
    private void setupListenersModuloVentas(MVentas_View view) {
        if (view != null) {
            view.getBtnRegresar().setActionCommand("regresarVentas"); view.getBtnRegresar().addActionListener(this);
            view.getBtnNuevaVenta().setActionCommand("abrirNuevaVenta"); view.getBtnNuevaVenta().addActionListener(this);
            view.getBtnHistorial().setActionCommand("abrirHistorialVenta"); view.getBtnHistorial().addActionListener(this);
            view.getBtnCorregir().setActionCommand("devolucion"); view.getBtnCorregir().addActionListener(this); // Devoluciones de Venta
            view.getBtnEliminar().setActionCommand("abrirEliminarVenta"); view.getBtnEliminar().addActionListener(this);
        }
    }

    private void setupListenersModuloClientes(MClientes_View view) {
        if (view != null) {
            view.getBtnRegresar().setActionCommand("regresarClientes"); view.getBtnRegresar().addActionListener(this);
            view.getBtnNuevoCliente_View().setActionCommand("abrirNuevoCliente"); view.getBtnNuevoCliente_View().addActionListener(this);
            view.getBtnEditarCliente_View().setActionCommand("abrirEditarCliente"); view.getBtnEditarCliente_View().addActionListener(this);
            view.getBtnVerCliente_View().setActionCommand("abrirVerClientes"); view.getBtnVerCliente_View().addActionListener(this);
            view.getBtnEliminarCliente_View().setActionCommand("abrirEliminarCliente"); view.getBtnEliminarCliente_View().addActionListener(this);
        }
    }
    
    private void setupListenersModuloProductos(MProductos_Frame view) {
        if (view != null) {
            view.getBtnRegresar().setActionCommand("regresarDeProductos"); view.getBtnRegresar().addActionListener(this);
            view.getBtnNuevoProducto().setActionCommand("abrirNuevoProducto"); view.getBtnNuevoProducto().addActionListener(this);
            view.getBtnEditarProducto().setActionCommand("abrirEditarProducto"); view.getBtnEditarProducto().addActionListener(this);
            view.getBtnVerProductos().setActionCommand("abrirVerProducto"); view.getBtnVerProductos().addActionListener(this);
            view.getBtnEliminarProducto().setActionCommand("abrirEliminarProducto"); view.getBtnEliminarProducto().addActionListener(this);
        }
    }

    private void setupListenersModuloProveedores(MProveedores_Frame view) { // o MProveedores_View
        if (view != null) {
            view.getBtnRegresar().setActionCommand("regresarDeProveedores"); view.getBtnRegresar().addActionListener(this);
            view.getBtnNuevaProve().setActionCommand("abrirRegistroProve"); view.getBtnNuevaProve().addActionListener(this);
            view.getBtnEditarProve().setActionCommand("abrirEditarProve"); view.getBtnEditarProve().addActionListener(this);
            view.getBtnVerProve().setActionCommand("abrirVerProve"); view.getBtnVerProve().addActionListener(this);
            view.getBtnRegistrarPedido().setActionCommand("abrirRegistroPedido"); view.getBtnRegistrarPedido().addActionListener(this);
            view.getBtnDevolucionProve().setActionCommand("abrirDevolucionProve"); view.getBtnDevolucionProve().addActionListener(this); // Devolución a Proveedor
            view.getBtnVerPedidos_View().setActionCommand("abrirVerPedidos"); view.getBtnVerPedidos_View().addActionListener(this);
        }
    }
    
    private void setupListenersModuloReportes(MReportes_Frame view) {
        if (view != null) {
            view.getBtnRegresar().setActionCommand("regresarReportes"); view.getBtnRegresar().addActionListener(this);
            // Los botones de "Generar PDF" se conectarán cuando implementemos esa lógica
        }
    }

    private void setupListenersSubVistasClientes() {
        NuevoCliente_Frame ncf = vistaFacade.getNuevoClienteFrame();
        if (ncf != null) { ncf.getBtnRegresar().setActionCommand("regresarNuevoCliente"); ncf.getBtnRegresar().addActionListener(this); }
        EditarCliente_Frame ecf = vistaFacade.getEditarClienteFrame();
        if (ecf != null) { ecf.getBtnRegresar().setActionCommand("regresarEditarCliente"); ecf.getBtnRegresar().addActionListener(this); }
        EliminarCliente_Frame elcf = vistaFacade.getEliminarClienteFrame();
        if (elcf != null) { elcf.getBtnRegresar().setActionCommand("regresarEliminarCliente"); elcf.getBtnRegresar().addActionListener(this); }
        VerCliente_Frame vcf = vistaFacade.getVerClienteFrame();
        if (vcf != null) { vcf.getBtnRegresar().setActionCommand("regresarVerCliente"); vcf.getBtnRegresar().addActionListener(this); }
    }
    
    private void setupListenersSubVistasProductos() {
        NuevoProducto_Frame npf = vistaFacade.getNuevoProductoFrame();
        if(npf != null) { npf.getBtnRegresar().setActionCommand("NuevoProducto"); npf.getBtnRegresar().addActionListener(this); } // Tu ActionCommand es "NuevoProducto"
        EditarProducto_Frame epf = vistaFacade.getEditarProductoFrame();
        if(epf != null) { epf.getBtnRegresar().setActionCommand("regresarEditarProducto"); epf.getBtnRegresar().addActionListener(this); }
        EliminarProducto_Frame elpf = vistaFacade.getEliminarProductoFrame();
        if(elpf != null) { elpf.getBtnRegresar().setActionCommand("regresarEliminarProducto"); elpf.getBtnRegresar().addActionListener(this); }
        VerProducto_Frame vpf = vistaFacade.getVerProductoFrame();
        if(vpf != null) { vpf.getBtnRegresar().setActionCommand("regresarVerProducto"); vpf.getBtnRegresar().addActionListener(this); }
    }

    private void setupListenersSubVistasVentas() {
        NuevaVenta_View nvv = vistaFacade.getNuevaVentaView();
        if(nvv != null) { nvv.getBtnRegresar().setActionCommand("regresarNuevaVenta"); nvv.getBtnRegresar().addActionListener(this); }
        EliminarVenta_Frame evf = vistaFacade.getEliminarVentaFrame();
        if(evf != null) { evf.getBtnRegresar().setActionCommand("regresarEliminarV"); evf.getBtnRegresar().addActionListener(this); }
        HistorialVentasUI hvui = vistaFacade.getHistorialVentasUI();
        if(hvui != null) { hvui.getBtnRegresar().setActionCommand("regresarHistorial"); hvui.getBtnRegresar().addActionListener(this); }
        Devoluciones_Frame dfVentas = vistaFacade.getDevolucionVentasFrame();
        if(dfVentas != null) { dfVentas.getBtnRegresar().setActionCommand("regresarDevolucion"); dfVentas.getBtnRegresar().addActionListener(this); }
    }

    private void setupListenersSubVistasProveedores() {
        RegistroProve_Frame rpf = vistaFacade.getRegistroProveFrame();
        if (rpf != null) { rpf.getBtnRegresar().setActionCommand("regresarRegistroProve"); rpf.getBtnRegresar().addActionListener(this); }
        EditarProve_Frame epf = vistaFacade.getEditarProveFrame();
        if (epf != null) { epf.getBtnRegresar().setActionCommand("regresarEditarProve"); epf.getBtnRegresar().addActionListener(this); }
        VerProve_Frame vpf = vistaFacade.getVerProveFrame();
        if (vpf != null) { vpf.getBtnRegresar().setActionCommand("regresarVerProve"); vpf.getBtnRegresar().addActionListener(this); }
        RegistroPedido_Frame rpedf = vistaFacade.getRegistroPedidoFrame();
        if (rpedf != null) { rpedf.getBtnRegresar().setActionCommand("regresarRegistroPedido"); rpedf.getBtnRegresar().addActionListener(this); }
        VerPedidos_Frame vpedf = vistaFacade.getVerPedidosFrame();
        if (vpedf != null) { vpedf.getBtnRegresar().setActionCommand("regresarVerPedidos"); vpedf.getBtnRegresar().addActionListener(this); }
        Devoluciones_Frame dfProve = vistaFacade.getDevolucionProveedoresFrame();
        if (dfProve != null) { dfProve.getBtnRegresar().setActionCommand("regresarDevolucionProve"); dfProve.getBtnRegresar().addActionListener(this); }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        // System.out.println("Controller: Comando Recibido -> " + command); // Descomenta para depurar

        // Lógica de navegación basada en tu ControladorPrueba
        // Simularemos login y luego mostraremos AdminView
        switch (command) {
            case "LOGIN_INICIAR_SESION":
                String user = vistaFacade.getLoginView().getTxtUsuario().getText();
                // Aquí iría la validación real con el modelo. Por ahora, simulamos:
                if ("admin".equalsIgnoreCase(user) || "Admin".equalsIgnoreCase(user)) {
                    tipoUsuarioLogueado = "admin";
                    vistaFacade.cerrarVentanaEspecifica(vistaFacade.getLoginView());
                    vistaFacade.mostrarAdminView();
                } else if ("user".equalsIgnoreCase(user) || "User".equalsIgnoreCase(user)) {
                     tipoUsuarioLogueado = "user";
                     vistaFacade.cerrarVentanaEspecifica(vistaFacade.getLoginView());
                     vistaFacade.mostrarUserView(); // Asumiendo que tienes este método en Facada_Vista_View
                } else {
                    vistaFacade.getLoginView().mostrarMensajeError("Usuario de prueba no reconocido (use 'admin' o 'user')");
                }
                break;

            case "cerrarSesion":
                tipoUsuarioLogueado = "";
                vistaFacade.cerrarVistaPrincipalActual(); // Cierra Admin o User View
                vistaFacade.mostrarLoginView();
                break;

            // --- NAVEGACIÓN DESDE Admin_View ---
            case "adminVentas": vistaFacade.mostrarModuloVentas(); break;
            case "adminClientes": vistaFacade.mostrarModuloClientes(); break;
            case "adminProductos": vistaFacade.mostrarModuloProductos(); break;
            case "adminProveedores": vistaFacade.mostrarModuloProveedores(); break;
            case "adminReportes": vistaFacade.mostrarModuloReportes(); break;

            // --- NAVEGACIÓN DESDE User_View ---
            case "abrirVentas": vistaFacade.mostrarModuloVentas(); break; // User_View -> Módulo Ventas
            case "abrirClientes": vistaFacade.mostrarModuloClientes(); break; // User_View -> Módulo Clientes
            case "abrirReportes": vistaFacade.mostrarModuloReportes(); break; // User_View -> Módulo Reportes
            
            // --- BOTONES "REGRESAR" DE LOS MÓDULOS PRINCIPALES ---
            case "regresarVentas":
            case "regresarClientes":
            case "regresarReportes":
            case "regresarDeProveedores":
            case "regresarDeProductos":
                vistaFacade.cerrarVistaPrincipalActual(); // Cierra el módulo actual
                if ("admin".equals(tipoUsuarioLogueado)) {
                    vistaFacade.mostrarAdminView();
                } else if ("user".equals(tipoUsuarioLogueado)) {
                    vistaFacade.mostrarUserView();
                } else {
                    vistaFacade.mostrarLoginView(); // Fallback si no hay rol
                }
                break;

            // --- NAVEGACIÓN DENTRO DEL MÓDULO DE VENTAS ---
            case "abrirNuevaVenta": vistaFacade.mostrarNuevaVentaView(); break;
            case "regresarNuevaVenta": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getNuevaVentaView()); break;
            case "abrirEliminarVenta": vistaFacade.mostrarEliminarVentaFrame(); break;
            case "regresarEliminarV": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getEliminarVentaFrame()); break;
            case "abrirHistorialVenta": vistaFacade.mostrarHistorialVentasUI(); break;
            case "regresarHistorial": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getHistorialVentasUI()); break;
            case "devolucion": vistaFacade.mostrarDevolucionVentasFrame(); break; // Devolución de Venta
            case "regresarDevolucion": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getDevolucionVentasFrame()); break;

            // --- NAVEGACIÓN DENTRO DEL MÓDULO DE CLIENTES ---
            case "abrirNuevoCliente": vistaFacade.mostrarNuevoClienteFrame(); break;
            case "regresarNuevoCliente": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getNuevoClienteFrame()); break;
            case "abrirEditarCliente": vistaFacade.mostrarEditarClienteFrame(); break;
            case "regresarEditarCliente": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getEditarClienteFrame()); break;
            case "abrirEliminarCliente": vistaFacade.mostrarEliminarClienteFrame(); break;
            case "regresarEliminarCliente": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getEliminarClienteFrame()); break;
            case "abrirVerClientes": vistaFacade.mostrarVerClienteFrame(); break;
            case "regresarVerCliente": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getVerClienteFrame()); break;

            // --- NAVEGACIÓN DENTRO DEL MÓDULO DE PRODUCTOS ---
            case "abrirNuevoProducto": vistaFacade.mostrarNuevoProductoFrame(); break;
            case "NuevoProducto": //Regresar desde NuevoProducto_Frame
                vistaFacade.cerrarVentanaEspecifica(vistaFacade.getNuevoProductoFrame()); break;
            case "abrirEditarProducto": vistaFacade.mostrarEditarProductoFrame(); break;
            case "regresarEditarProducto": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getEditarProductoFrame()); break;
            case "abrirEliminarProducto": vistaFacade.mostrarEliminarProductoFrame(); break;
            case "regresarEliminarProducto": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getEliminarProductoFrame()); break;
            case "abrirVerProducto": vistaFacade.mostrarVerProductoFrame(); break;
            case "regresarVerProducto": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getVerProductoFrame()); break;

            // --- NAVEGACIÓN DENTRO DEL MÓDULO DE PROVEEDORES ---
            case "abrirRegistroProve": vistaFacade.mostrarRegistroProveFrame(); break;
            case "regresarRegistroProve": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getRegistroProveFrame()); break;
            case "abrirEditarProve": vistaFacade.mostrarEditarProveFrame(); break;
            case "regresarEditarProve": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getEditarProveFrame()); break;
            case "abrirVerProve": vistaFacade.mostrarVerProveFrame(); break;
            case "regresarVerProve": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getVerProveFrame()); break;
            case "abrirDevolucionProve": vistaFacade.mostrarDevolucionProveedoresFrame(); break;
            case "regresarDevolucionProve": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getDevolucionProveedoresFrame()); break;
            case "abrirVerPedidos": vistaFacade.mostrarVerPedidosFrame(); break;
            case "regresarVerPedidos": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getVerPedidosFrame()); break;
            case "abrirRegistroPedido": vistaFacade.mostrarRegistroPedidoFrame(); break;
            case "regresarRegistroPedido": vistaFacade.cerrarVentanaEspecifica(vistaFacade.getRegistroPedidoFrame()); break;

            default:
                System.out.println("Controller: Comando no reconocido o sin acción de navegación definida -> " + command);
        }
    }
}