package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.unbosque.view.Admin_View;
import co.edu.unbosque.view.Devoluciones_Frame;
import co.edu.unbosque.view.EditarCliente_Frame;
import co.edu.unbosque.view.EditarProducto_Frame;
import co.edu.unbosque.view.EditarProve_Frame;
import co.edu.unbosque.view.EliminarCliente_Frame;
import co.edu.unbosque.view.EliminarProducto_Frame;
import co.edu.unbosque.view.EliminarVenta_Frame;
import co.edu.unbosque.view.Facada_Vista_login;
import co.edu.unbosque.view.HistorialVentasUI;
import co.edu.unbosque.view.MClientes_View;
import co.edu.unbosque.view.MProductos_Frame;
import co.edu.unbosque.view.MProveedores_Frame;
import co.edu.unbosque.view.MReportes_Frame;
import co.edu.unbosque.view.MVentas_View;
import co.edu.unbosque.view.NuevaVenta_View;
import co.edu.unbosque.view.NuevoCliente_Frame;
import co.edu.unbosque.view.NuevoProducto_Frame;
import co.edu.unbosque.view.RegistroPedido_Frame;
import co.edu.unbosque.view.RegistroProve_Frame;
import co.edu.unbosque.view.User_View;
import co.edu.unbosque.view.VerCliente_Frame;
import co.edu.unbosque.view.VerPedidos_Frame;
import co.edu.unbosque.view.VerProducto_Frame;
import co.edu.unbosque.view.VerProve_Frame;
import co.edu.unbosque.model.Facada_Model;

public class controllerprueba implements ActionListener {
	private Facada_Model model;
	private Facada_Vista_login loginView;
	private Admin_View adminView;
	private User_View userView;
	private MVentas_View mventasView;
	private MClientes_View mclientesView;
	private MProductos_Frame mproductosFrame;
	private MProveedores_Frame mproveedoresFrame;
	private MReportes_Frame mreportesFrame;
	private NuevaVenta_View nuevaVentaView;
	private EliminarVenta_Frame eliminarVentaFrame;
	private HistorialVentasUI historialVentasUI;
	private Devoluciones_Frame devolucionVentasFrame;
	private NuevoCliente_Frame nuevoClienteFrame;
	private EditarCliente_Frame editarClienteFrame;
	private EliminarCliente_Frame eliminarClienteFrame;
	private VerCliente_Frame verClienteFrame;
	private NuevoProducto_Frame nuevoProductoFrame;
	private EditarProducto_Frame editarProductoFrame;
	private EliminarProducto_Frame eliminarProductoFrame;
	private VerProducto_Frame verProductoFrame;
	private RegistroProve_Frame registroProveFrame;
	private EditarProve_Frame editarProveFrame;
	private VerProve_Frame verProveFrame;
	private RegistroPedido_Frame registroPedidoFrame;
	private VerPedidos_Frame verPedidosFrame;
	private Devoluciones_Frame devolucionProveedoresFrame;

	private String tipoUsuarioLogueado = "";

	public controllerprueba(Facada_Vista_login loginView, Admin_View adminView, User_View userView,
			MVentas_View mventasView, MClientes_View mclientesView, MProductos_Frame mproductosFrame,
			MProveedores_Frame mproveedoresFrame, MReportes_Frame mreportesFrame, NuevaVenta_View nuevaVentaView,
			EliminarVenta_Frame eliminarVentaFrame, HistorialVentasUI historialVentasUI,
			Devoluciones_Frame devolucionVentasFrame, NuevoCliente_Frame nuevoClienteFrame,
			EditarCliente_Frame editarClienteFrame, EliminarCliente_Frame eliminarClienteFrame,
			VerCliente_Frame verClienteFrame, NuevoProducto_Frame nuevoProductoFrame,
			EditarProducto_Frame editarProductoFrame, EliminarProducto_Frame eliminarProductoFrame,
			VerProducto_Frame verProductoFrame, RegistroProve_Frame registroProveFrame,
			EditarProve_Frame editarProveFrame, VerProve_Frame verProveFrame, RegistroPedido_Frame registroPedidoFrame,
			VerPedidos_Frame verPedidosFrame, Devoluciones_Frame devolucionProveedoresFrame) {
		this.loginView = loginView;
		this.adminView = adminView;
		this.userView = userView;
		this.mventasView = mventasView;
		this.mclientesView = mclientesView;
		this.mproductosFrame = mproductosFrame;
		this.mproveedoresFrame = mproveedoresFrame;
		this.mreportesFrame = mreportesFrame;
		this.nuevaVentaView = nuevaVentaView;
		this.eliminarVentaFrame = eliminarVentaFrame;
		this.historialVentasUI = historialVentasUI;
		this.devolucionVentasFrame = devolucionVentasFrame;
		this.nuevoClienteFrame = nuevoClienteFrame;
		this.editarClienteFrame = editarClienteFrame;
		this.eliminarClienteFrame = eliminarClienteFrame;
		this.verClienteFrame = verClienteFrame;
		this.nuevoProductoFrame = nuevoProductoFrame;
		this.editarProductoFrame = editarProductoFrame;
		this.eliminarProductoFrame = eliminarProductoFrame;
		this.verProductoFrame = verProductoFrame;
		this.registroProveFrame = registroProveFrame;
		this.editarProveFrame = editarProveFrame;
		this.verProveFrame = verProveFrame;
		this.registroPedidoFrame = registroPedidoFrame;
		this.verPedidosFrame = verPedidosFrame;
		this.devolucionProveedoresFrame = devolucionProveedoresFrame;
		
		

		addListeners();

		iniciar();
	}

	public void iniciar() {
		if (model == null) {
			model = new Facada_Model();
		}
		loginView.setVisible(true);

	}

	private void addListeners() {
		// LOGIN
		if (loginView != null && loginView.getBtnInicio() != null) {
			loginView.getBtnInicio().setActionCommand("LOGIN_INICIAR_SESION");
			loginView.getBtnInicio().addActionListener(this);
		}

		// ADMIN VIEW
		if (adminView != null) {
			adminView.getBtnventas().setActionCommand("adminVentas");
			adminView.getBtnventas().addActionListener(this);
			adminView.getBtnclientes().setActionCommand("adminClientes");
			adminView.getBtnclientes().addActionListener(this);
			adminView.getBtnproducto().setActionCommand("adminProductos");
			adminView.getBtnproducto().addActionListener(this);
			adminView.getBtnproveedor().setActionCommand("adminProveedores");
			adminView.getBtnproveedor().addActionListener(this);
			adminView.getBtnReportes().setActionCommand("adminReportes");
			adminView.getBtnReportes().addActionListener(this);
			adminView.getBtnCerrarSesion().setActionCommand("cerrarSesion");
			adminView.getBtnCerrarSesion().addActionListener(this);
		}

		// USER VIEW
		if (userView != null) {
			userView.getBtnventas().setActionCommand("abrirVentas");
			userView.getBtnventas().addActionListener(this);
			userView.getBtnclientes().setActionCommand("abrirClientes");
			userView.getBtnclientes().addActionListener(this);
			userView.getBtnReportes().setActionCommand("abrirReportes");
			userView.getBtnReportes().addActionListener(this);
			userView.getBtnCerrarSesion().setActionCommand("cerrarSesion");
			userView.getBtnCerrarSesion().addActionListener(this);
		}

		// MÓDULO VENTAS
		if (mventasView != null) {
			mventasView.getBtnRegresar().setActionCommand("regresarVentas");
			mventasView.getBtnRegresar().addActionListener(this);
			mventasView.getBtnNuevaVenta().setActionCommand("abrirNuevaVenta");
			mventasView.getBtnNuevaVenta().addActionListener(this);
			mventasView.getBtnHistorial().setActionCommand("abrirHistorialVenta");
			mventasView.getBtnHistorial().addActionListener(this);
			mventasView.getBtnCorregir().setActionCommand("devolucion");
			mventasView.getBtnCorregir().addActionListener(this);
			mventasView.getBtnEliminar().setActionCommand("abrirEliminarVenta");
			mventasView.getBtnEliminar().addActionListener(this);
		}

		// MÓDULO CLIENTES
		if (mclientesView != null) {
			mclientesView.getBtnRegresar().setActionCommand("regresarClientes");
			mclientesView.getBtnRegresar().addActionListener(this);
			mclientesView.getBtnNuevoCliente_View().setActionCommand("abrirNuevoCliente");
			mclientesView.getBtnNuevoCliente_View().addActionListener(this);
			mclientesView.getBtnEditarCliente_View().setActionCommand("abrirEditarCliente");
			mclientesView.getBtnEditarCliente_View().addActionListener(this);
			mclientesView.getBtnVerCliente_View().setActionCommand("abrirVerClientes");
			mclientesView.getBtnVerCliente_View().addActionListener(this);
			mclientesView.getBtnEliminarCliente_View().setActionCommand("abrirEliminarCliente");
			mclientesView.getBtnEliminarCliente_View().addActionListener(this);
		}

		// MÓDULO PRODUCTOS
		if (mproductosFrame != null) {
			mproductosFrame.getBtnRegresar().setActionCommand("regresarDeProductos");
			mproductosFrame.getBtnRegresar().addActionListener(this);
			mproductosFrame.getBtnNuevoProducto().setActionCommand("abrirNuevoProducto");
			mproductosFrame.getBtnNuevoProducto().addActionListener(this);
			mproductosFrame.getBtnEditarProducto().setActionCommand("abrirEditarProducto");
			mproductosFrame.getBtnEditarProducto().addActionListener(this);
			mproductosFrame.getBtnVerProductos().setActionCommand("abrirVerProducto");
			mproductosFrame.getBtnVerProductos().addActionListener(this);
			mproductosFrame.getBtnEliminarProducto().setActionCommand("abrirEliminarProducto");
			mproductosFrame.getBtnEliminarProducto().addActionListener(this);
		}

		// MÓDULO PROVEEDORES
		if (mproveedoresFrame != null) {
			mproveedoresFrame.getBtnRegresar().setActionCommand("regresarDeProveedores");
			mproveedoresFrame.getBtnRegresar().addActionListener(this);
			mproveedoresFrame.getBtnNuevaProve().setActionCommand("abrirRegistroProve");
			mproveedoresFrame.getBtnNuevaProve().addActionListener(this);
			mproveedoresFrame.getBtnEditarProve().setActionCommand("abrirEditarProve");
			mproveedoresFrame.getBtnEditarProve().addActionListener(this);
			mproveedoresFrame.getBtnVerProve().setActionCommand("abrirVerProve");
			mproveedoresFrame.getBtnVerProve().addActionListener(this);
			mproveedoresFrame.getBtnRegistrarPedido().setActionCommand("abrirRegistroPedido");
			mproveedoresFrame.getBtnRegistrarPedido().addActionListener(this);
			mproveedoresFrame.getBtnDevolucionProve().setActionCommand("abrirDevolucionProve");
			mproveedoresFrame.getBtnDevolucionProve().addActionListener(this);
			mproveedoresFrame.getBtnVerPedidos_View().setActionCommand("abrirVerPedidos");
			mproveedoresFrame.getBtnVerPedidos_View().addActionListener(this);
		}

		// MÓDULO REPORTES
		if (mreportesFrame != null) {
			mreportesFrame.getBtnRegresar().setActionCommand("regresarReportes");
			mreportesFrame.getBtnRegresar().addActionListener(this);
		}

		// SUB-VISTAS CLIENTES
		if (nuevoClienteFrame != null) {
			nuevoClienteFrame.getBtnRegresar().setActionCommand("regresarNuevoCliente");
			nuevoClienteFrame.getBtnRegresar().addActionListener(this);
			nuevoClienteFrame.getBtnRegistrar().setActionCommand("confirmarNuevoCliente");
			nuevoClienteFrame.getBtnRegistrar().addActionListener(this);
			nuevoClienteFrame.getBtnLimpiar().setActionCommand("LimpiarCliente");
			nuevoClienteFrame.getBtnLimpiar().addActionListener(this);
		}
		if (editarClienteFrame != null) {
			editarClienteFrame.getBtnRegresar().setActionCommand("regresarEditarCliente");
			editarClienteFrame.getBtnRegresar().addActionListener(this);
			editarClienteFrame.getBtnGuardar().setActionCommand("confirmarEditarCliente");
			editarClienteFrame.getBtnGuardar().addActionListener(this);
			editarClienteFrame.getBtnLimpiar().setActionCommand("LimpiarEditarCliente");
			editarClienteFrame.getBtnLimpiar().addActionListener(this);


		}
		if (eliminarClienteFrame != null) {
			eliminarClienteFrame.getBtnRegresar().setActionCommand("regresarEliminarCliente");
			eliminarClienteFrame.getBtnRegresar().addActionListener(this);
			eliminarClienteFrame.getBtnEliminar().setActionCommand("confirmacionEliminarcliente");
			eliminarClienteFrame.getBtnEliminar().addActionListener(this);
			
		}
		if (verClienteFrame != null) {
			verClienteFrame.getBtnRegresar().setActionCommand("regresarVerCliente");
			verClienteFrame.getBtnRegresar().addActionListener(this);
		}

		// SUB-VISTAS PRODUCTOS
		if (nuevoProductoFrame != null) {
			nuevoProductoFrame.getBtnRegresar().setActionCommand("NuevoProducto");
			nuevoProductoFrame.getBtnRegresar().addActionListener(this);
		}
		if (editarProductoFrame != null) {
			editarProductoFrame.getBtnRegresar().setActionCommand("regresarEditarProducto");
			editarProductoFrame.getBtnRegresar().addActionListener(this);
		}
		if (eliminarProductoFrame != null) {
			eliminarProductoFrame.getBtnRegresar().setActionCommand("regresarEliminarProducto");
			nuevoProductoFrame.getBtnRegresar().addActionListener(this);
		}
		if (verProductoFrame != null) {
			verProductoFrame.getBtnRegresar().setActionCommand("regresarVerProducto");
			verProductoFrame.getBtnRegresar().addActionListener(this);
		}

		// SUB-VISTAS VENTAS
		if (nuevaVentaView != null) {
			nuevaVentaView.getBtnRegresar().setActionCommand("regresarNuevaVenta");
			nuevaVentaView.getBtnRegresar().addActionListener(this);
		}
		if (eliminarVentaFrame != null) {
			eliminarVentaFrame.getBtnRegresar().setActionCommand("regresarEliminarV");
			eliminarVentaFrame.getBtnRegresar().addActionListener(this);
		}
		if (historialVentasUI != null) {
			historialVentasUI.getBtnRegresar().setActionCommand("regresarHistorial");
			historialVentasUI.getBtnRegresar().addActionListener(this);
		}
		if (devolucionVentasFrame != null) {
			devolucionVentasFrame.getBtnRegresar().setActionCommand("regresarDevolucion");
			devolucionVentasFrame.getBtnRegresar().addActionListener(this);
		}

		// SUB-VISTAS PROVEEDORES
		if (registroProveFrame != null) {
			registroProveFrame.getBtnRegresar().setActionCommand("regresarRegistroProve");
			registroProveFrame.getBtnRegresar().addActionListener(this);
		}
		if (editarProveFrame != null) {
			editarProveFrame.getBtnRegresar().setActionCommand("regresarEditarProve");
			editarProveFrame.getBtnRegresar().addActionListener(this);
		}
		if (verProveFrame != null) {
			verProveFrame.getBtnRegresar().setActionCommand("regresarVerProve");
			verProveFrame.getBtnRegresar().addActionListener(this);
		}
		if (registroPedidoFrame != null) {
			registroPedidoFrame.getBtnRegresar().setActionCommand("regresarRegistroPedido");
			registroPedidoFrame.getBtnRegresar().addActionListener(this);
		}
		if (verPedidosFrame != null) {
			verPedidosFrame.getBtnRegresar().setActionCommand("regresarVerPedidos");
			verPedidosFrame.getBtnRegresar().addActionListener(this);
		}
		if (devolucionProveedoresFrame != null) {
			devolucionProveedoresFrame.getBtnRegresar().setActionCommand("regresarDevolucionProve");
			devolucionProveedoresFrame.getBtnRegresar().addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
		case "LOGIN_INICIAR_SESION":
			String user = loginView.getTxtUsuario().getText();
			if ("admin".equalsIgnoreCase(user)) {
				tipoUsuarioLogueado = "admin";
				loginView.setVisible(false);
				adminView.setVisible(true);
			} else if ("user".equalsIgnoreCase(user)) {
				tipoUsuarioLogueado = "user";
				loginView.setVisible(false);
				userView.setVisible(true);
			} else {
				loginView.mostrarMensajeError("Usuario de prueba no reconocido (use 'admin' o 'user')");
			}
			break;

		case "cerrarSesion":
			tipoUsuarioLogueado = "";
			if ("admin".equals(tipoUsuarioLogueado)) {
				adminView.setVisible(false);
			} else if ("user".equals(tipoUsuarioLogueado)) {
				userView.setVisible(false);
			}
			loginView.setVisible(true);
			break;

		// NAVEGACIÓN DESDE Admin_View
		case "adminVentas":
			adminView.setVisible(false);
			mventasView.setVisible(true);
			break;
		case "adminClientes":
			adminView.setVisible(false);
			mclientesView.setVisible(true);
			break;
		case "adminProductos":
			adminView.setVisible(false);
			mproductosFrame.setVisible(true);
			break;
		case "adminProveedores":
			adminView.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "adminReportes":
			adminView.setVisible(false);
			mreportesFrame.setVisible(true);
			break;

		// NAVEGACIÓN DESDE User_View
		case "abrirVentas":
			userView.setVisible(false);
			mventasView.setVisible(true);
			break;
		case "abrirClientes":
			userView.setVisible(false);
			mclientesView.setVisible(true);
			break;
		case "abrirReportes":
			userView.setVisible(false);
			mreportesFrame.setVisible(true);
			break;
		
		//subvista Nuevo Clientes
		case "confirmarNuevoCliente":
			model.getClientes().crear_cliente(
			nuevoClienteFrame.getName(),
			nuevoClienteFrame.getComboTipoCliente().getSelectedItem().toString(),
			 nuevoClienteFrame.getTxtCorreo().getText().toString(),
			 Long.parseLong(nuevoClienteFrame.getTxtCedula().getText().toString()),
			Long.parseLong(nuevoClienteFrame.getTxtTelefono().getText().toString())
			);
			break;
		case "LimpiarCliente":

		//subvistas Editar Clientes
		case "confirmarEditarCliente":
		model.getClientes().actualizar_cliente( 
		editarClienteFrame.getName().toString(),
		editarClienteFrame.getComboTipoCliente().getSelectedItem().toString(), 
		editarClienteFrame.getTxtCorreo().getText().toString(), 
		Long.parseLong(editarClienteFrame.getTxtCedula().getText().toString()), 
		Long.parseLong(editarClienteFrame.getTxtTelefono().getText().toString()));
		break;

		case "LimpiarEditarCliente": 
			//añadir logica de limpiar

		//eliminar cliente 
		case "confirmacionEliminarcliente":
		model.getClientes().eliminar_cliente(Long.parseLong(eliminarClienteFrame.getTxtCedula().getText().toString()));


		// BOTONES "REGRESAR" DE MÓDULOS PRINCIPALES
		case "regresarVentas":
			mventasView.setVisible(false);
			if ("admin".equals(tipoUsuarioLogueado)) {
				adminView.setVisible(true);
			} else {
				userView.setVisible(true);
			}
			break;
		case "regresarClientes":
			mclientesView.setVisible(false);
			if ("admin".equals(tipoUsuarioLogueado)) {
				adminView.setVisible(true);
			} else {
				userView.setVisible(true);
			}
			break;
		case "regresarReportes":
			mreportesFrame.setVisible(false);
			if ("admin".equals(tipoUsuarioLogueado)) {
				adminView.setVisible(true);
			} else {
				userView.setVisible(true);
			}
			break;
		case "regresarDeProveedores":
			mproveedoresFrame.setVisible(false);
			adminView.setVisible(true);
			break;
		case "regresarDeProductos":
			mproductosFrame.setVisible(false);
			adminView.setVisible(true);
			break;

		// NAVEGACIÓN DENTRO DEL MÓDULO DE VENTAS
		case "abrirNuevaVenta":
			mventasView.setVisible(false);
			nuevaVentaView.setVisible(true);
			break;
		case "regresarNuevaVenta":
			nuevaVentaView.setVisible(false);
			mventasView.setVisible(true);
			break;
		case "abrirEliminarVenta":
			mventasView.setVisible(false);
			eliminarVentaFrame.setVisible(true);
			break;
		case "regresarEliminarV":
			eliminarVentaFrame.setVisible(false);
			mventasView.setVisible(true);
			break;
		case "abrirHistorialVenta":
			mventasView.setVisible(false);
			historialVentasUI.setVisible(true);
			break;
		case "regresarHistorial":
			historialVentasUI.setVisible(false);
			mventasView.setVisible(true);
			break;
		case "devolucion":
			mventasView.setVisible(false);
			devolucionVentasFrame.setVisible(true);
			break;
		case "regresarDevolucion":
			devolucionVentasFrame.setVisible(false);
			mventasView.setVisible(true);
			break;

		// NAVEGACIÓN DENTRO DEL MÓDULO DE CLIENTES
		case "abrirNuevoCliente":
			mclientesView.setVisible(false);
			nuevoClienteFrame.setVisible(true);
			break;
		case "regresarNuevoCliente":
			nuevoClienteFrame.setVisible(false);
			mclientesView.setVisible(true);
			break;
		case "abrirEditarCliente":
			mclientesView.setVisible(false);
			editarClienteFrame.setVisible(true);
			break;
		case "regresarEditarCliente":
			editarClienteFrame.setVisible(false);
			mclientesView.setVisible(true);
			break;
		case "abrirEliminarCliente":
			mclientesView.setVisible(false);
			eliminarClienteFrame.setVisible(true);
			break;
		case "regresarEliminarCliente":
			eliminarClienteFrame.setVisible(false);
			mclientesView.setVisible(true);
			break;
		case "abrirVerClientes":
			mclientesView.setVisible(false);
			verClienteFrame.setVisible(true);
			break;
		case "regresarVerCliente":
			verClienteFrame.setVisible(false);
			mclientesView.setVisible(true);
			break;

		// NAVEGACIÓN DENTRO DEL MÓDULO DE PRODUCTOS
		case "abrirNuevoProducto":
			mproductosFrame.setVisible(false);
			nuevoProductoFrame.setVisible(true);
			break;
		case "NuevoProducto":
			nuevoProductoFrame.setVisible(false);
			mproductosFrame.setVisible(true);
			break;
		case "abrirEditarProducto":
			mproductosFrame.setVisible(false);
			editarProductoFrame.setVisible(true);
			break;
		case "regresarEditarProducto":
			editarProductoFrame.setVisible(false);
			mproductosFrame.setVisible(true);
			break;
		case "abrirEliminarProducto":
			mproductosFrame.setVisible(false);
			eliminarProductoFrame.setVisible(true);
			break;
		case "regresarEliminarProducto":
			eliminarProductoFrame.setVisible(false);
			mproductosFrame.setVisible(true);
			break;
		case "abrirVerProducto":
			mproductosFrame.setVisible(false);
			verProductoFrame.setVisible(true);
			break;
		case "regresarVerProducto":
			verProductoFrame.setVisible(false);
			mproductosFrame.setVisible(true);
			break;

		// NAVEGACIÓN DENTRO DEL MÓDULO DE PROVEEDORES
		case "abrirRegistroProve":
			mproveedoresFrame.setVisible(false);
			registroProveFrame.setVisible(true);
			break;
		case "regresarRegistroProve":
			registroProveFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "abrirEditarProve":
			mproveedoresFrame.setVisible(false);
			editarProveFrame.setVisible(true);
			break;
		case "regresarEditarProve":
			editarProveFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "abrirVerProve":
			mproveedoresFrame.setVisible(false);
			verProveFrame.setVisible(true);
			break;
		case "regresarVerProve":
			verProveFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "abrirDevolucionProve":
			mproveedoresFrame.setVisible(false);
			devolucionProveedoresFrame.setVisible(true);
			break;
		case "regresarDevolucionProve":
			devolucionProveedoresFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "abrirVerPedidos":
			mproveedoresFrame.setVisible(false);
			verPedidosFrame.setVisible(true);
			break;
		case "regresarVerPedidos":
			verPedidosFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "abrirRegistroPedido":
			mproveedoresFrame.setVisible(false);
			registroPedidoFrame.setVisible(true);
			break;
		case "regresarRegistroPedido":
			registroPedidoFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;

		default:
			throw new IllegalArgumentException("Comando no reconocido: " + command);
		}
	}
}