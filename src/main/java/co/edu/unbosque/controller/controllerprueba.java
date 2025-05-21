package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import co.edu.unbosque.view.Admin_View;
import co.edu.unbosque.view.Devoluciones_Frame;
import co.edu.unbosque.view.EditarCliente_Frame;
import co.edu.unbosque.view.EditarProducto_Frame;
import co.edu.unbosque.view.EditarProve_Frame;
import co.edu.unbosque.view.EliminarCliente_Frame;
import co.edu.unbosque.view.EliminarProducto_Frame;
import co.edu.unbosque.view.EliminarVenta_Frame;
import co.edu.unbosque.view.Facada_Vista_crearUsuario;
import co.edu.unbosque.view.Facada_Vista_login;
import co.edu.unbosque.view.HistorialVentasUI;
import co.edu.unbosque.view.MClientes_View;
import co.edu.unbosque.view.MProductos_Frame;
import co.edu.unbosque.view.MProveedores_Frame;
import co.edu.unbosque.view.MReportes_Frame;
import co.edu.unbosque.view.MVentas_View;
import co.edu.unbosque.view.NuevaVenta_View;
import co.edu.unbosque.view.NuevoCliente_Frame;
import co.edu.unbosque.view.NuevoInventario_Frame;
import co.edu.unbosque.view.NuevoProducto_Frame;
import co.edu.unbosque.view.RegistroPedido_Frame;
import co.edu.unbosque.view.RegistroProve_Frame;
import co.edu.unbosque.view.User_View;
import co.edu.unbosque.view.VerCliente_Frame;
import co.edu.unbosque.view.VerPedidos_Frame;
import co.edu.unbosque.view.VerProducto_Frame;
import co.edu.unbosque.view.VerProve_Frame;
import co.edu.unbosque.model.Facada_Model;
import co.edu.unbosque.model.FacturaElectronicaPDF;
import co.edu.unbosque.model.ReporteClientesPDF;
import co.edu.unbosque.model.ReporteDevolucionesProveedorPDF;
import co.edu.unbosque.model.ReporteFacturasPDF;
import co.edu.unbosque.model.ReportePedidosPDF;
import co.edu.unbosque.model.ReporteProductosPDF;
import co.edu.unbosque.model.ReporteProveedoresPDF;
import co.edu.unbosque.model.daosYdtos.ClientesDto;
import co.edu.unbosque.model.daosYdtos.InventarioDto;
import co.edu.unbosque.model.daosYdtos.MoviProveInDto;
import co.edu.unbosque.model.daosYdtos.ProductoDto;
import co.edu.unbosque.model.daosYdtos.ProveedorDto;
import co.edu.unbosque.model.daosYdtos.UsuariosDao;
import co.edu.unbosque.model.daosYdtos.UsuariosDto;
import co.edu.unbosque.model.daosYdtos.DetalleFacturaDto;
import co.edu.unbosque.model.daosYdtos.FacturasDto;
import co.edu.unbosque.model.daosYdtos.DFacturaDto;

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
	private NuevoInventario_Frame nuevoInventario;
	private String tipoUsuarioLogueado = "";
	private String nombreUsuario;
	private Facada_Vista_crearUsuario crearU;

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
			VerPedidos_Frame verPedidosFrame, Devoluciones_Frame devolucionProveedoresFrame,
			NuevoInventario_Frame nuevoInventario, Facada_Vista_crearUsuario crearU) {
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
		this.nuevoInventario = nuevoInventario;
		this.crearU = crearU;
		addListeners();

		iniciar();
	}

	public void iniciar() {
		if (model == null) {
			model = new Facada_Model();
			model.getTipoP().crear_tipo_p("casa");
			model.getInventario().crearInventario(0, 12, "tipoUsuarioLogueado", 7, null);

		}
		loginView.setVisible(true);

	}

	private void addListeners() {
		// LOGIN
		if (loginView != null && loginView.getBtnInicio() != null) {
			loginView.getBtnInicio().setActionCommand("LOGIN_INICIAR_SESION");
			loginView.getBtnInicio().addActionListener(this);
			loginView.getBtnCrearUsuario().addActionListener(this);
			loginView.getBtnCrearUsuario().setActionCommand("crearU");
			crearU.getBtnRegresar().addActionListener(this);
			crearU.getBtnRegresar().setActionCommand("regresarLogin");
			crearU.getBtnCrearUsuario().addActionListener(this);
			crearU.getBtnCrearUsuario().setActionCommand("CrearUsuario");
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
			mproductosFrame.getBtnInventarioCrear().addActionListener(this);
			mproductosFrame.getBtnInventarioCrear().setActionCommand("abrirCrearInventario");
			nuevoInventario.getBtnRegresar().addActionListener(this);
			nuevoInventario.getBtnRegresar().setActionCommand("regresarCrearI");
			nuevoInventario.getBtnGuardar().addActionListener(this);
			nuevoInventario.getBtnGuardar().setActionCommand("confirmarCrearInventario");
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

			mreportesFrame.getBtnClientes().addActionListener(this);
			mreportesFrame.getBtnClientes().setActionCommand("pdfCliente");

			mreportesFrame.getBtnProductos().addActionListener(this);
			mreportesFrame.getBtnProductos().setActionCommand("pdfProducto");

			mreportesFrame.getBtnProveedores().addActionListener(this);
			mreportesFrame.getBtnProveedores().setActionCommand("pdfProveedor");

			mreportesFrame.getBtnDevolucionProve().addActionListener(this);
			mreportesFrame.getBtnDevolucionProve().setActionCommand("pdfDevolucion");

			mreportesFrame.getBtnHistoriaPedi().addActionListener(this);
			mreportesFrame.getBtnHistoriaPedi().setActionCommand("pdfHistorialPedido");

			mreportesFrame.getBtnHistoriaV().addActionListener(this);
			mreportesFrame.getBtnHistoriaV().setActionCommand("pdfHistorialVentas");

			mreportesFrame.getBtnDian().addActionListener(this);
			mreportesFrame.getBtnDian().setActionCommand("Dian");

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
			nuevoProductoFrame.getBtnGuardar().setActionCommand("GuardarProducto");
			nuevoProductoFrame.getBtnGuardar().addActionListener(this);
		}
		if (editarProductoFrame != null) {
			editarProductoFrame.getBtnRegresar().setActionCommand("regresarEditarProducto");
			editarProductoFrame.getBtnRegresar().addActionListener(this);
			editarProductoFrame.getBtnGuardar().setActionCommand("EditarProducto");
			editarProductoFrame.getBtnGuardar().addActionListener(this);
		}
		if (eliminarProductoFrame != null) {
			eliminarProductoFrame.getBtnRegresar().setActionCommand("regresarEliminarProducto");
			eliminarProductoFrame.getBtnRegresar().addActionListener(this);
			eliminarProductoFrame.getBtnEliminar().setActionCommand("EliminarProducto");
			eliminarProductoFrame.getBtnEliminar().addActionListener(this);

		}
		if (verProductoFrame != null) {
			verProductoFrame.getBtnRegresar().setActionCommand("regresarVerProducto");
			verProductoFrame.getBtnRegresar().addActionListener(this);
		}

		// SUB-VISTAS VENTAS
		if (nuevaVentaView != null) {
			nuevaVentaView.getBtnRegresar().setActionCommand("regresarNuevaVenta");
			nuevaVentaView.getBtnRegresar().addActionListener(this);
			nuevaVentaView.getBtnConfirmar().setActionCommand("confirmarVenta");
			nuevaVentaView.getBtnConfirmar().addActionListener(this);
			nuevaVentaView.getBtnAgregarProducto().setActionCommand("agregarProductoV");
			nuevaVentaView.getBtnAgregarProducto().addActionListener(this);
		}
		if (eliminarVentaFrame != null) {
			eliminarVentaFrame.getBtnRegresar().setActionCommand("regresarEliminarV");
			eliminarVentaFrame.getBtnRegresar().addActionListener(this);
			eliminarClienteFrame.getBtnEliminar().setActionCommand("confirmarEliminacion");
			eliminarClienteFrame.getBtnEliminar().addActionListener(this);
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
			registroProveFrame.getBtnRegistrar().setActionCommand("ConfirmarProvedor");
			registroProveFrame.getBtnRegistrar().addActionListener(this);
			registroProveFrame.getBtnLimpiar().setActionCommand("LimpiarRegistroProve");
			registroProveFrame.getBtnLimpiar().addActionListener(this);
		}
		if (editarProveFrame != null) {
			editarProveFrame.getBtnRegresar().setActionCommand("regresarEditarProve");
			editarProveFrame.getBtnRegresar().addActionListener(this);
			editarProveFrame.getBtnGuardar().setActionCommand("ConfirmarEditarProvedor");
			editarProveFrame.getBtnGuardar().addActionListener(this);

		}
		if (verProveFrame != null) {
			verProveFrame.getBtnRegresar().setActionCommand("regresarVerProve");
			verProveFrame.getBtnRegresar().addActionListener(this);

			// Agregar listeners para búsqueda y filtros
			verProveFrame.getTxtBuscar().getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					filtrarProveedores();
				}

				public void removeUpdate(DocumentEvent e) {
					filtrarProveedores();
				}

				public void insertUpdate(DocumentEvent e) {
					filtrarProveedores();
				}
			});

			verProveFrame.getCboxMetodoPago().addActionListener(e -> filtrarProveedores());
			verProveFrame.getCboxFecha().addActionListener(e -> filtrarProveedores());
			verProveFrame.getCboxEstado().addActionListener(e -> filtrarProveedores());
		}
		if (registroPedidoFrame != null) {
			registroPedidoFrame.getBtnRegresar().setActionCommand("regresarRegistroPedido");
			registroPedidoFrame.getBtnRegresar().addActionListener(this);
			registroPedidoFrame.getBtnGuardar().setActionCommand("ConfirmarPedido");
			registroPedidoFrame.getBtnGuardar().addActionListener(this);
		}
		if (verPedidosFrame != null) {
			verPedidosFrame.getBtnRegresar().setActionCommand("regresarVerPedidos");
			verPedidosFrame.getBtnRegresar().addActionListener(this);

			// Agregar listeners para búsqueda y filtros en VerPedidos_Frame
			verPedidosFrame.getTxtBuscar().getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					filtrarPedidos();
				}

				public void removeUpdate(DocumentEvent e) {
					filtrarPedidos();
				}

				public void insertUpdate(DocumentEvent e) {
					filtrarPedidos();
				}
			});

			verPedidosFrame.getCboxMetodoPago().addActionListener(e -> filtrarPedidos()); // cboxMetodoPago es para
																							// Producto
			verPedidosFrame.getCboxEstado().addActionListener(e -> filtrarPedidos()); // cboxEstado es para Proveedor
			verPedidosFrame.getCboxFecha().addActionListener(e -> filtrarPedidos()); // cboxFecha es para Fecha
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
				nombreUsuario = "Rosita González"; // asignamos el nombre real del emisor
				loginView.setVisible(false);
				adminView.setVisible(true);
			} else if ("user".equalsIgnoreCase(user)) {
				tipoUsuarioLogueado = "user";
				nombreUsuario = "Gladys Arango"; // nombre del usuario
				loginView.setVisible(false);
				userView.setVisible(true);
			} else {
				loginView.mostrarMensajeError("Usuario de prueba no reconocido (use 'admin' o 'user')");
			}
			break;

		case "crearU":
			crearU.setVisible(true);
			loginView.setVisible(false);
			break;
		case "CrearUsuario":
			String nombredUsuario = crearU.getTxtUsuario().getText().trim();
			String contraseña = String.valueOf(crearU.getJpContraseña().getPassword()).trim();
			String confirmar = String.valueOf(crearU.getJpConfirmarContraseña().getPassword()).trim();

			if (nombredUsuario.isEmpty() || contraseña.isEmpty() || confirmar.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
				break;
			}

			if (!contraseña.equals(confirmar)) {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
				break;
			}

			UsuariosDto nuevoUsuario = new UsuariosDto();
			nuevoUsuario.setUsuario(nombredUsuario);
			nuevoUsuario.setContraseña(contraseña);
			nuevoUsuario.setAcceso("user");

			try {
				UsuariosDao usuarioDAO = new UsuariosDao();
				usuarioDAO.crearUsuario(nuevoUsuario);
				JOptionPane.showMessageDialog(null, "Usuario creado con éxito.");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + ex.getMessage());
			}
			break;

		case "regresarLogin":
			loginView.setVisible(true);
			crearU.setVisible(false);

		case "cerrarSesion":
			if ("admin".equals(tipoUsuarioLogueado)) {
				adminView.setVisible(false);
			} else if ("user".equals(tipoUsuarioLogueado)) {
				userView.setVisible(false);
			}
			tipoUsuarioLogueado = "";
			loginView.setVisible(true);
			break;

		// NAVEGACIÓN DESDE Admin_View
		case "adminVentas":
			mventasView.setVisible(true);
			adminView.setVisible(false);
			break;
		case "adminClientes":
			mclientesView.setVisible(true);
			adminView.setVisible(false);
			break;
		case "adminProductos":
			mproductosFrame.setVisible(true);
			adminView.setVisible(false);
			break;
		case "adminProveedores":
			mproveedoresFrame.setVisible(true);
			adminView.setVisible(false);
			break;
		case "adminReportes":
			mreportesFrame.setVisible(true);
			adminView.setVisible(false);
			break;

		// NAVEGACIÓN DESDE User_View
		case "abrirVentas":
			mventasView.setVisible(true);
			userView.setVisible(false);
			break;
		case "abrirClientes":
			userView.setVisible(false);
			mclientesView.setVisible(true);
			break;
		case "abrirReportes":
			userView.setVisible(false);
			mreportesFrame.setVisible(true);
			break;

		// MANEJO DE LAS SUBVISTAS
		// CLIENTES
		// subvista Nuevo Clientes
		case "confirmarNuevoCliente":
			model.getClientes().crear_cliente(nuevoClienteFrame.getTxtNombre().getText().toString(),
					nuevoClienteFrame.getComboTipoCliente().getSelectedItem().toString(),
					nuevoClienteFrame.getTxtCorreo().getText().toString(),
					Long.parseLong(nuevoClienteFrame.getTxtCedula().getText().toString()),
					Long.parseLong(nuevoClienteFrame.getTxtTelefono().getText().toString()));
			break;
		case "LimpiarCliente":

			// subvistas Editar Clientes
		case "confirmarEditarCliente":
			model.getClientes().actualizar_cliente(editarClienteFrame.getTxtNombre().getText().toString(),
					editarClienteFrame.getComboTipoCliente().getSelectedItem().toString(),
					editarClienteFrame.getTxtCorreo().getText().toString(),
					Long.parseLong(editarClienteFrame.getTxtCedula().getText().toString()),
					Long.parseLong(editarClienteFrame.getTxtTelefono().getText().toString()));
			break;

		// eliminar cliente
		case "confirmacionEliminarcliente":
			eliminarClienteFrame.getTxtNombre()
					.setText(model.getClientes()
							.ver_cliente(Long.parseLong(eliminarClienteFrame.getTxtCedula().getText().toString())).get()
							.getNombre().toString());
			eliminarClienteFrame.getTxtCedula()
					.setText(String.valueOf(model.getClientes()
							.ver_cliente(Long.parseLong(eliminarClienteFrame.getTxtCedula().getText().toString())).get()
							.getCedula()));
			eliminarClienteFrame.getTxtTelefono()
					.setText(String.valueOf(model.getClientes()
							.ver_cliente(Long.parseLong(eliminarClienteFrame.getTxtCedula().getText().toString())).get()
							.getTelefono()));
			eliminarClienteFrame.getTxtCorreo()
					.setText(model.getClientes()
							.ver_cliente(Long.parseLong(eliminarClienteFrame.getTxtCedula().getText().toString())).get()
							.getCorreo().toString());

			model.getClientes()
					.eliminar_cliente(Long.parseLong(eliminarClienteFrame.getTxtCedula().getText().toString()));
			break;

		// PRODUCTOS
		// productos ya esta hehco solo es aplicarlo, modificar el combo box mas
		// adelante
		case "GuardarProducto":
			model.getProductos().crearProducto(nuevoProductoFrame.getTxtNombre().getText().toString(),
					nuevoProductoFrame.getTxtDescripcion().getText().toString(),
					Double.parseDouble(nuevoProductoFrame.getTxtPrecio().getText().toString()), 5,
					Double.parseDouble(nuevoProductoFrame.getTxtIVA().getText().toString()));
			// editar producto
		case "EditarProducto":
			model.getProductos().actualizarProducto(nuevoProductoFrame.getTxtNombre().getText().toString(), 5,
					nuevoProductoFrame.getTxtDescripcion().getText().toString(),
					Double.parseDouble(nuevoProductoFrame.getTxtPrecio().getText().toString()),
					Double.parseDouble(nuevoProductoFrame.getTxtIVA().getText().toString()));

			break;
		// eliminar Producto
		case "EliminarProducto":
			// Obtenemos el nombre del campo de texto
			String nombreProductoBusqueda = eliminarProductoFrame.getTxtNombre().getText().toString(); // Usamos el
																										// campo de
																										// nombre para
																										// buscar

			// Buscar el producto por nombre usando el metodo del modelo
			// Este metodo (obtenerProductoPorId(String nombre)) ahora busca por nombre y
			// devuelve Optional<ProductoDto>
			Optional<ProductoDto> productoOpt = model.getProductos().obtenerProductoPorId(nombreProductoBusqueda);

			// Verificar si el Optional contiene un valor (si se encontro el producto)
			if (productoOpt.isPresent()) {
				ProductoDto productoAEliminar = productoOpt.get(); // Obtener el DTO si esta presente

				// Llenar los campos de texto con los datos del producto encontrado
				// Asegúrate de convertir los tipos numéricos a String para setText()
				eliminarProductoFrame.getTxtId().setText(String.valueOf(productoAEliminar.getIdProducto())); // Mostrar
																												// el ID
																												// encontrado
				eliminarProductoFrame.getTxtNombre().setText(productoAEliminar.getNombre());
				eliminarProductoFrame.getTxtDescripcion().setText(productoAEliminar.getDescripcion());
				eliminarProductoFrame.getTxtPrecio().setText(String.valueOf(productoAEliminar.getPrecio())); // Convertir
																												// double
																												// a
																												// String
				eliminarProductoFrame.getTxtIVA().setText(String.valueOf(productoAEliminar.getIva())); // Convertir
																										// double a
																										// String

				// *** Lógica de Eliminación y Confirmación (ejemplo) ***
				model.getProductos().eliminarProducto(productoAEliminar.getIdProducto());

				// Mensaje de confirmacion visual
				JOptionPane.showMessageDialog(eliminarProductoFrame, "Producto eliminado exitosamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				// Limpiar los campos después de eliminar con éxito
				eliminarProductoFrame.getTxtId().setText("");
				eliminarProductoFrame.getTxtNombre().setText("");
				eliminarProductoFrame.getTxtDescripcion().setText("");
				eliminarProductoFrame.getTxtPrecio().setText("");

			} else {
				// Si el Optional esta vacio (producto no encontrado)

				// Opcional: limpiar los campos excepto el nombre ingresado
				eliminarProductoFrame.getTxtId().setText(""); // Si tienes un campo ID
				// eliminarProductoFrame.getTxtNombre().setText(""); // Puedes dejar el nombre
				// ingresado
				eliminarProductoFrame.getTxtDescripcion().setText("");
				eliminarProductoFrame.getTxtPrecio().setText("");
				eliminarProductoFrame.getTxtIVA().setText("");
				// Limpiar JComboBox si aplica
			}
			break;

		// PROVEDOR
		case "ConfirmarProvedor":
			String nombreProveedor = registroProveFrame.getTxtNombre().getText().trim();
			String telefonoProveedor = registroProveFrame.getTxtTelefono().getText().trim();
			String direccionProveedor = registroProveFrame.getTxtDireccion().getText().trim();
			String nombreProductoSeleccionado = (String) registroProveFrame.getComboProductos().getSelectedItem();

			int idProductoAsociado = 0; // Valor por defecto si no se selecciona un producto valido

			// Buscar el ID del producto seleccionado
			if (nombreProductoSeleccionado != null && !nombreProductoSeleccionado.equals("Seleccione un producto")) {
				List<ProductoDto> listaProductos = model.getProductos().obtenerTodosLosProductos();
				if (listaProductos != null) {
					for (ProductoDto producto : listaProductos) {
						if (producto.getNombre().equals(nombreProductoSeleccionado)) {
							idProductoAsociado = producto.getIdProducto();
							break; // Encontramos el producto, salimos del bucle
						}
					}
				}
			}

			// Validar campos obligatorios y si se selecciono un producto
			if (nombreProveedor.isEmpty() || telefonoProveedor.isEmpty() || direccionProveedor.isEmpty()) {
				JOptionPane.showMessageDialog(registroProveFrame, "Por favor, complete todos los campos obligatorios.",
						"Campos vacíos", JOptionPane.WARNING_MESSAGE);
			} else if (idProductoAsociado == 0) {
				JOptionPane.showMessageDialog(registroProveFrame, "Por favor, seleccione un producto válido.",
						"Producto no seleccionado", JOptionPane.WARNING_MESSAGE);
			} else {
				// Llamar al metodo crearProveedor con el ID del producto seleccionado
				model.getProveedores().crearProveedor(nombreProveedor, telefonoProveedor, // Usando telefono para el
																							// campo 'contacto'
						direccionProveedor, idProductoAsociado,
						Long.parseLong(registroProveFrame.getTxtCedula().getText().toString()) // Asumiendo que
																								// getTxtCedula es para
																								// cedula/NIT del
																								// proveedor
				);
				JOptionPane.showMessageDialog(registroProveFrame, "Proveedor registrado exitosamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				registroProveFrame.limpiarFormulario(); // Limpiar el formulario si el registro fue exitoso
			}
			break;
		case "ConfirmarEditarProvedor":
			String nombreProveedorEdit = editarProveFrame.getTxtNombre().getText().trim();
			String cedulaProveedorEdit = editarProveFrame.getTxtCedula().getText().trim(); // Asumiendo que la cedula se
																							// usa para identificar al
																							// proveedor
			String telefonoProveedorEdit = editarProveFrame.getTxtTelefono().getText().trim();
			String direccionProveedorEdit = editarProveFrame.getTxtDireccion().getText().trim();
			String nombreProductoSeleccionadoEdit = (String) editarProveFrame.getComboProductos().getSelectedItem();

			int idProductoAsociadoEdit = 0;

			// Buscar el ID del producto seleccionado para la actualizacion
			if (nombreProductoSeleccionadoEdit != null
					&& !nombreProductoSeleccionadoEdit.equals("Seleccione un producto")) {
				List<ProductoDto> listaProductosEdit = model.getProductos().obtenerTodosLosProductos();
				if (listaProductosEdit != null) {
					for (ProductoDto producto : listaProductosEdit) {
						if (producto.getNombre().equals(nombreProductoSeleccionadoEdit)) {
							idProductoAsociadoEdit = producto.getIdProducto();
							break;
						}
					}
				}
			}

			// Validar campos obligatorios y si se selecciono un producto
			if (nombreProveedorEdit.isEmpty() || cedulaProveedorEdit.isEmpty() || telefonoProveedorEdit.isEmpty()
					|| direccionProveedorEdit.isEmpty()) {
				JOptionPane.showMessageDialog(editarProveFrame, "Por favor, complete todos los campos obligatorios.",
						"Campos vacíos", JOptionPane.WARNING_MESSAGE);
			} else if (idProductoAsociadoEdit == 0) {
				JOptionPane.showMessageDialog(editarProveFrame, "Por favor, seleccione un producto válido.",
						"Producto no seleccionado", JOptionPane.WARNING_MESSAGE);
			} else {
				// Convertir cedula a long
				long cedulaLongEdit;
				try {
					cedulaLongEdit = Long.parseLong(cedulaProveedorEdit);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(editarProveFrame, "La cédula/NIT debe ser un número válido.",
							"Error de formato", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Llamar al metodo actualizarProveedor
				boolean exitoActualizacion = model.getProveedores().actualizarProveedor(

						// Aquí deberías pasar el ID del proveedor si actualizarProveedor lo requiere y
						// tienes acceso a el
						nombreProveedorEdit, telefonoProveedorEdit, // Usando telefono para el campo \'contacto\'
						direccionProveedorEdit, idProductoAsociadoEdit, cedulaLongEdit // Pasando la cedula/NIT
																						// convertida

				);

				if (exitoActualizacion) {
					JOptionPane.showMessageDialog(editarProveFrame, "Proveedor actualizado exitosamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					// Opcional: Limpiar campos o cerrar ventana después de actualizar
				} else {
					JOptionPane.showMessageDialog(editarProveFrame, "Error al actualizar el proveedor.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			break;

		// Pedidos
		case "ConfirmarPedido":
			try {
				// Validar que todos los campos estén llenos
				if (registroPedidoFrame.getTxtTipoMovimiento().getText().isEmpty()
						|| registroPedidoFrame.getTxtCantidad().getText().isEmpty()
						|| !registroPedidoFrame.hayFechaSeleccionada()
						|| registroPedidoFrame.getTxtMotivo().getText().isEmpty()
						|| registroPedidoFrame.getTxtProvedor().getSelectedIndex() == 0
						|| registroPedidoFrame.getTxtProducto().getSelectedIndex() == 0) {

					JOptionPane.showMessageDialog(registroPedidoFrame,
							"Por favor, complete todos los campos del formulario.", "Campos incompletos",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtener los valores del formulario
				String tipoMovimiento = registroPedidoFrame.getTxtTipoMovimiento().getText();
				int cantidad = Integer.parseInt(registroPedidoFrame.getTxtCantidad().getText());
				Date fecha = registroPedidoFrame.getFechaDate();
				String motivo = registroPedidoFrame.getTxtMotivo().getText();

				// Obtener el ID del producto seleccionado
				String productoSeleccionado = registroPedidoFrame.getTxtProducto().getSelectedItem().toString();
				int idProducto = 0;

				// Extraer el ID del producto del texto seleccionado (formato: "Nombre -
				// $Precio")
				List<ProductoDto> productos = model.getProductos().obtenerTodosLosProductos();
				for (ProductoDto producto : productos) {
					if (productoSeleccionado.startsWith(producto.getNombre())) {
						idProducto = producto.getIdProducto();
						break;
					}
				}

				if (idProducto == 0) {
					JOptionPane.showMessageDialog(registroPedidoFrame,
							"Error al obtener el ID del producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtener el ID del inventario asociado al producto
				int idInventario = 12; // Por ahora lo dejamos fijo, pero deberías obtenerlo según tu lógica

				// Crear el movimiento
				MoviProveInDto movimiento = model.getMoviProveIn().crear_movi_prove_in(idInventario, tipoMovimiento,
						cantidad, fecha, motivo, idProducto);

				if (movimiento != null) {
					JOptionPane.showMessageDialog(registroPedidoFrame, "Movimiento registrado exitosamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					// Limpiar el formulario
					registroPedidoFrame.getTxtTipoMovimiento().setText("");
					registroPedidoFrame.getTxtCantidad().setText("");
					registroPedidoFrame.getTxtMotivo().setText("");
					registroPedidoFrame.getTxtProvedor().setSelectedIndex(0);
					registroPedidoFrame.getTxtProducto().setSelectedIndex(0);
					registroPedidoFrame.setFechaActual();
				} else {
					JOptionPane.showMessageDialog(registroPedidoFrame, "Error al registrar el movimiento.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(registroPedidoFrame, "La cantidad debe ser un número válido.",
						"Error de formato", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(registroPedidoFrame,
						"Error al procesar el movimiento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		// Inventario
		case "abrirCrearInventario":
			nuevoInventario.setVisible(true);
			mproductosFrame.setVisible(false);

			// Limpiar y cargar el combobox de productos
			nuevoInventario.getComboProducto().removeAllItems();
			nuevoInventario.getComboProducto().addItem("Seleccione un producto");

			List<ProductoDto> listaProductosInventario = model.getProductos().obtenerTodosLosProductos();
			if (listaProductosInventario != null) {
				for (ProductoDto producto : listaProductosInventario) {
					nuevoInventario.getComboProducto().addItem(producto.getNombre());
				}
			}
			break;
		case "regresarCrearI":
			mproductosFrame.setVisible(true);
			nuevoInventario.setVisible(false);
			break;
		case "confirmarCrearInventario":
			try {
				// Validar que todos los campos estén llenos
				if (nuevoInventario.getComboProducto().getSelectedIndex() == 0
						|| nuevoInventario.getTxtStock().getText().isEmpty()
						|| nuevoInventario.getTxtStockMinimo().getText().isEmpty()
						|| nuevoInventario.getTxtUbicacion().getText().isEmpty()) {

					JOptionPane.showMessageDialog(nuevoInventario,
							"Por favor, complete todos los campos del formulario.", "Campos incompletos",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtener los valores del formulario
				String productoSeleccionado = (String) nuevoInventario.getComboProducto().getSelectedItem();
				int stock = Integer.parseInt(nuevoInventario.getTxtStock().getText());
				int stockMinimo = Integer.parseInt(nuevoInventario.getTxtStockMinimo().getText());
				String ubicacion = nuevoInventario.getTxtUbicacion().getText();

				// Obtener el ID del producto seleccionado
				int idProducto = 0;
				List<ProductoDto> productos = model.getProductos().obtenerTodosLosProductos();
				for (ProductoDto producto : productos) {
					if (productoSeleccionado.equals(producto.getNombre())) {
						idProducto = producto.getIdProducto();
						break;
					}
				}

				if (idProducto == 0) {
					JOptionPane.showMessageDialog(nuevoInventario, "Error al obtener el ID del producto seleccionado.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Crear el inventario
				InventarioDto inventarioCreado = model.getInventario().crearInventario(stock, stockMinimo, ubicacion,
						idProducto, new java.util.Date() // Fecha actual
				);

				if (inventarioCreado != null) {
					JOptionPane.showMessageDialog(nuevoInventario, "Inventario registrado exitosamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					// Limpiar el formulario
					nuevoInventario.getComboProducto().setSelectedIndex(0);
					nuevoInventario.getTxtStock().setText("");
					nuevoInventario.getTxtStockMinimo().setText("");
					nuevoInventario.getTxtUbicacion().setText("");
				} else {
					JOptionPane.showMessageDialog(nuevoInventario, "Error al registrar el inventario.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(nuevoInventario, "El stock y stock mínimo deben ser números válidos.",
						"Error de formato", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(nuevoInventario, "Error al procesar el inventario: " + ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "confirmarVenta":
			try {
				// Obtener datos de la venta
				String nombreCliente = nuevaVentaView.getTxtCliente().getText().trim();
				Date fecha = nuevaVentaView.getFechaVenta().getDate();
				String metodoPago = nuevaVentaView.getMetodoPago().getSelectedItem().toString();
				double total = Double.parseDouble(nuevaVentaView.getTxtTotal().getText().replace("$", "").trim());
				double iva = Double.parseDouble(nuevaVentaView.getTxtIva().getText().replace("$", "").trim());

				// Validar datos del cliente
				if (nombreCliente.isEmpty()) {
					JOptionPane.showMessageDialog(nuevaVentaView, "Por favor ingrese el nombre del cliente", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Buscar cliente por nombre
				List<ClientesDto> clientes = model.getClientes().obtener_todos_los_clientes();
				Optional<ClientesDto> clienteOpt = clientes.stream()
						.filter(c -> c.getNombre().equalsIgnoreCase(nombreCliente)).findFirst();

				int idCliente;
				if (clienteOpt.isPresent()) {
					idCliente = clienteOpt.get().getIdCliente();
				} else {
					// Crear nuevo cliente
					ClientesDto nuevoCliente = model.getClientes().crear_cliente(nombreCliente, "Cliente",
							"cliente@email.com", 0L, // Cedula temporal
							0L // Teléfono temporal
					);
					if (nuevoCliente == null) {
						JOptionPane.showMessageDialog(nuevaVentaView, "Error al crear el cliente", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					idCliente = nuevoCliente.getIdCliente();
				}

				// Crear factura
				FacturasDto factura = model.getFacturas().crear_factura(idCliente, fecha, metodoPago);

				if (factura != null) {
					// Obtener detalles de productos
					DefaultTableModel modelo = (DefaultTableModel) nuevaVentaView.getTablaProductos().getModel();

					for (int i = 0; i < modelo.getRowCount(); i++) {
						int idProducto = Integer.parseInt(modelo.getValueAt(i, 0).toString());
						int cantidad = Integer.parseInt(modelo.getValueAt(i, 2).toString());
						double precioUnitario = Double.parseDouble(modelo.getValueAt(i, 3).toString());
						double subtotal = Double.parseDouble(modelo.getValueAt(i, 4).toString());

						// Crear detalle de factura
						model.getDfactura().crear_dfactura(factura.getIdFactura(), idProducto, null, // idProveedor
								null, // idPromocion
								"venta", // tipo
								cantidad, precioUnitario, subtotal);

						// Actualizar inventario
						InventarioDto inventario = model.getInventario().obtenerInventarioPorProducto(idProducto);
						if (inventario != null) {
							int nuevoStock = inventario.getStock() - cantidad;
							model.getInventario().actualizarStock(idProducto, nuevoStock);
						}
					}

					JOptionPane.showMessageDialog(nuevaVentaView, "Venta registrada exitosamente", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					// Limpiar formulario
					nuevaVentaView.getTxtCliente().setText("");
					nuevaVentaView.getFechaVenta().setDate(new Date());
					nuevaVentaView.getMetodoPago().setSelectedIndex(0);
					nuevaVentaView.getTxtTotal().setText("$ 0.00");
					nuevaVentaView.getTxtIva().setText("$ 0.00");
					((DefaultTableModel) nuevaVentaView.getTablaProductos().getModel()).setRowCount(0);
				} else {
					JOptionPane.showMessageDialog(nuevaVentaView, "Error al registrar la venta", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(nuevaVentaView, "Error al procesar la venta: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "agregarProductoV":
			// Obtener todos los productos de la base de datos
			List<ProductoDto> productos = model.getProductos().obtenerTodosLosProductos();

			// Crear matriz de datos para la tabla
			Object[][] datosProductos = new Object[productos.size()][7];

			for (int i = 0; i < productos.size(); i++) {
				ProductoDto producto = productos.get(i);
				// Obtener el inventario del producto
				InventarioDto inventario = model.getInventario().obtenerInventarioPorProducto(producto.getIdProducto());
				int stock = (inventario != null) ? inventario.getStock() : 0;

				datosProductos[i][0] = producto.getIdProducto(); // ID
				datosProductos[i][1] = producto.getNombre(); // Nombre
				datosProductos[i][2] = producto.getPrecio(); // Precio
				datosProductos[i][3] = stock; // Stock
				datosProductos[i][4] = 1; // Cantidad inicial
				datosProductos[i][5] = producto.getPrecio(); // Precio Unitario
				datosProductos[i][6] = producto.getPrecio(); // Subtotal inicial
			}

			nuevaVentaView.mostrarVentanaProductos(datosProductos);
			break;
		case "confirmarEliminacion":
			try {
				// Obtener los datos de la venta a eliminar
				int idVenta = Integer.parseInt(eliminarVentaFrame.getTxtIdVenta().getText().trim());
				String fechaVenta = eliminarVentaFrame.getTxtFechaVenta().getText().trim();
				String cliente = eliminarVentaFrame.getTxtCliente().getText().trim();
				double total = Double.parseDouble(eliminarVentaFrame.getTxtTotal().getText().trim());
				String estado = (String) eliminarVentaFrame.getComboEstado().getSelectedItem();
				String motivo = eliminarVentaFrame.getTxtMotivo().getText().trim();

				// Validar que la venta exista
				Optional<FacturasDto> facturaOpt = model.getFacturas().ver_factura(idVenta);
				if (!facturaOpt.isPresent()) {
					JOptionPane.showMessageDialog(eliminarVentaFrame, "No se encontró la venta con el ID especificado",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtener los detalles de la factura para actualizar el inventario
				List<DFacturaDto> detalles = model.getDfactura().obtener_detalles_por_id_factura(idVenta);

				// Restaurar el inventario
				for (DFacturaDto detalle : detalles) {
					InventarioDto inventario = model.getInventario()
							.obtenerInventarioPorProducto(detalle.getIdProducto());
					if (inventario != null) {
						int nuevoStock = inventario.getStock() + detalle.getCantidad();
						model.getInventario().actualizarStock(detalle.getIdProducto(), nuevoStock);
					}
				}

				// Eliminar los detalles de la factura
				for (DFacturaDto detalle : detalles) {
					model.getDfactura().eliminar_dfactura(detalle.getIdDetalle());
				}

				// Eliminar la factura
				boolean eliminado = model.getFacturas().eliminar_factura(idVenta);

				if (eliminado) {
					JOptionPane.showMessageDialog(eliminarVentaFrame, "Venta eliminada exitosamente", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					// Limpiar campos
					eliminarVentaFrame.getTxtIdVenta().setText("");
					eliminarVentaFrame.getTxtFechaVenta().setText("");
					eliminarVentaFrame.getTxtCliente().setText("");
					eliminarVentaFrame.getTxtTotal().setText("");
					eliminarVentaFrame.getTxtMotivo().setText("");
					eliminarVentaFrame.getComboEstado().setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(eliminarVentaFrame, "Error al eliminar la venta", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(eliminarVentaFrame,
						"Por favor ingrese valores numéricos válidos para el ID y el total", "Error de formato",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(eliminarVentaFrame,
						"Error al procesar la eliminación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		// usuario
		case "CrearUsiario":
			model.getUsuarios().crearUsuario(crearU.getTxtUsuario().getText().toString(),
					new String(crearU.getJpContraseña().getPassword()), "usuario");
			break;
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

			// Cargar el historial de ventas
			cargarHistorialVentas();

			// Agregar listeners para los filtros
			historialVentasUI.getTxtBuscar().getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					filtrarHistorialVentas();
				}

				public void removeUpdate(DocumentEvent e) {
					filtrarHistorialVentas();
				}

				public void insertUpdate(DocumentEvent e) {
					filtrarHistorialVentas();
				}
			});

			historialVentasUI.getCbMetodoPago().addActionListener(event -> filtrarHistorialVentas());
			historialVentasUI.getCbEstado().addActionListener(event -> filtrarHistorialVentas());
			historialVentasUI.getTxtFecha().addPropertyChangeListener("value", event -> filtrarHistorialVentas());
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

			DefaultTableModel modeloTabla = (DefaultTableModel) verClienteFrame.getTbTablaClientes().getModel();
			modeloTabla.setRowCount(0);
			List<ClientesDto> listaClientes = model.getClientes().obtener_todos_los_clientes();
			if (listaClientes != null) {
				for (ClientesDto cliente : listaClientes) {
					// Crear una fila con los datos del cliente, en el orden de las columnas de la
					// tabla
					Object[] fila = new Object[5]; // Hay 5 columnas definidas en VerCliente_Frame
					fila[0] = cliente.getNombre(); // "Nombre"
					fila[1] = cliente.getCedula(); // "Cédula/NIT"
					fila[2] = cliente.getTelefono(); // "Teléfono"
					fila[3] = cliente.getCorreo(); // "Correo electrónico"
					fila[4] = "N/A"; // "Dirección" - tu DTO no tiene este campo, usamos N/A o ""

					modeloTabla.addRow(fila); // Añadir la fila al modelo
				}
			} else {
				System.out.println("No se encontraron clientes para mostrar.");
				// Opcional: mostrar un mensaje en la interfaz de usuario si no hay clientes
				// JOptionPane.showMessageDialog(verClienteFrame, "No hay clientes
				// registrados.");
			}

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
			System.out.println("Intentando obtener la tabla de productos...");
			DefaultTableModel modeloTablaProductos = (DefaultTableModel) verProductoFrame.getTablaProductos()
					.getModel();
			System.out.println("Modelo de tabla obtenido correctamente");

			modeloTablaProductos.setRowCount(0);
			System.out.println("Tabla limpiada");

			System.out.println("Intentando obtener la lista de productos...");
			List<ProductoDto> listaProductos = model.getProductos().obtenerTodosLosProductos();
			System.out.println("Lista de productos obtenida. Tamaño: "
					+ (listaProductos != null ? listaProductos.size() : "null"));

			if (listaProductos != null) {
				System.out.println("Procesando productos...");
				for (ProductoDto producto : listaProductos) {
					System.out.println("Procesando producto: " + producto.getNombre());

					// Obtener el inventario del producto
					String stock = "N/A";
					List<InventarioDto> inventarios = model.getInventario().obtenerTodosLosInventarios();
					if (inventarios != null) {
						for (InventarioDto inventario : inventarios) {
							if (inventario.getIdProducto() == producto.getIdProducto()) {
								stock = String.valueOf(inventario.getStock());
								break;
							}
						}
					}

					Object[] fila = new Object[7];
					fila[0] = producto.getIdProducto();
					fila[1] = producto.getNombre();
					fila[2] = producto.getIdCategoriaP();
					fila[3] = producto.getDescripcion();
					fila[4] = producto.getPrecio();
					fila[5] = producto.getIva();
					fila[6] = stock; // Ahora mostramos el stock real del inventario

					modeloTablaProductos.addRow(fila);
					System.out.println("Fila añadida para producto: " + producto.getNombre());
				}
				System.out.println("Procesamiento de productos completado");
			} else {
				System.out.println("No se encontraron productos para mostrar.");
				JOptionPane.showMessageDialog(verProductoFrame, "No hay productos registrados.", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		case "regresarVerProducto":
			verProductoFrame.setVisible(false);
			mproductosFrame.setVisible(true);
			break;

		// NAVEGACIÓN DENTRO DEL MÓDULO DE PROVEEDORES
		case "abrirRegistroProve":
			mproveedoresFrame.setVisible(false);
			registroProveFrame.setVisible(true);

			// Llenar el JComboBox de productos
			registroProveFrame.getComboProductos().removeAllItems(); // Limpiar items existentes
			registroProveFrame.getComboProductos().addItem("Seleccione un producto"); // Añadir opción por defecto

			List<ProductoDto> listaProductosProveedores = model.getProductos().obtenerTodosLosProductos();
			if (listaProductosProveedores != null) {
				for (ProductoDto producto : listaProductosProveedores) {
					registroProveFrame.getComboProductos().addItem(producto.getNombre()); // Añadir el nombre del
																							// producto
				}
			} else {
				System.out.println("No hay productos disponibles para cargar en el ComboBox.");
			}

			break;
		case "regresarRegistroProve":
			registroProveFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "abrirEditarProve":
			mproveedoresFrame.setVisible(false);
			editarProveFrame.setVisible(true);
			// --- Parte 1: Llenar el JComboBox de productos con TODOS los productos ---
			editarProveFrame.getComboProductos().removeAllItems(); // Limpiar items existentes
			editarProveFrame.getComboProductos().addItem("Seleccione un producto"); // Añadir opción por defecto

			List<ProductoDto> productosParaEditar = model.getProductos().obtenerTodosLosProductos();
			if (productosParaEditar != null) {
				for (ProductoDto producto : productosParaEditar) {
					// Aquí añadimos el nombre de cada producto al JComboBox
					editarProveFrame.getComboProductos().addItem(producto.getNombre());
				}
			} else {
				System.out.println("No hay productos disponibles para cargar en el ComboBox de edición.");
			}

			// --- Parte 2: Cargar los datos del proveedor existente y seleccionar su
			// producto asociado ---

			// **Paso A:** Obtener el identificador del proveedor a editar.
			// Esto es CRUCIAL. Necesitas saber qué proveedor editar.
			// En el ejemplo anterior, asumí que la cedula se ingresa en txtCedula antes.
			// Si tu lógica es diferente (ej. seleccionas de una tabla), adapta cómo
			// obtienes la cedula/ID aquí.
			String cedulaProveedorStr = editarProveFrame.getTxtCedula().getText().trim(); // O el método que obtenga la
																							// cedula

			if (cedulaProveedorStr.isEmpty()) {
				// Si no hay identificador, no podemos cargar datos. Muestra un mensaje y sales.
				JOptionPane.showMessageDialog(editarProveFrame,
						"Por favor, ingrese la cédula/NIT del proveedor a editar.", "Cédula/NIT Vacía",
						JOptionPane.WARNING_MESSAGE);
				// Además, limpia o resetea los campos si la cédula está vacía
				editarProveFrame.getTxtNombre().setText("");
				editarProveFrame.getTxtTelefono().setText("");
				editarProveFrame.getTxtDireccion().setText("");
				editarProveFrame.getComboProductos().setSelectedItem("Seleccione un producto");
				// TODO: Limpiar o seleccionar opción por defecto en comboTipoProveedor
				return;
			}

			long cedulaProveedor;
			try {
				cedulaProveedor = Long.parseLong(cedulaProveedorStr);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(editarProveFrame, "La cédula/NIT debe ser un número válido.",
						"Error de formato", JOptionPane.ERROR_MESSAGE);
				// Limpiar o resetear campos
				editarProveFrame.getTxtNombre().setText("");
				editarProveFrame.getTxtTelefono().setText("");
				editarProveFrame.getTxtDireccion().setText("");
				editarProveFrame.getComboProductos().setSelectedItem("Seleccione un producto");
				// TODO: Limpiar o seleccionar opción por defecto en comboTipoProveedor
				return;
			}

			// **Paso B:** Buscar el proveedor usando su identificador.
			Optional<ProveedorDto> proveedorOpt = model.getProveedores().obtenerProveedorPorCedula(cedulaProveedor); // Usar
																														// tu
																														// método
																														// de
																														// búsqueda
																														// por
																														// cedula

			if (proveedorOpt.isPresent()) {
				ProveedorDto proveedor = proveedorOpt.get();

				// **Paso C:** Llenar los campos de texto con los datos del proveedor
				// encontrado.
				editarProveFrame.getTxtNombre().setText(proveedor.getNombre());
				editarProveFrame.getTxtTelefono().setText(proveedor.getContacto()); // Asegurate que getContacto
																					// devuelve String o conviertelo
				editarProveFrame.getTxtDireccion().setText(proveedor.getDireccion());
				// El campo txtCedula ya debería tener la cédula que se usó para buscar.

				// **Paso D, E y F:** Identificar el producto asociado y seleccionarlo en el
				// JComboBox.

				// Necesitas el ID del producto asociado al proveedor.
				String nomrbeProductoAsociado = proveedor.getNombre(); // Obtienes el ID del producto del DTO del
																		// proveedor

				// Ahora, busca el nombre de ese producto en la lista completa de productos.
				// Revisa si tu model.getProductos().obtenerProductoPorId(int idProducto) existe
				// y busca por ID (int).
				// Si no, NECESITAS agregar ese método primero en ProductoDao y Productos.java.
				// Si obtenerProductoPorId en Productos.java SÍ busca por ID (int), la llamada
				// sería:
				Optional<ProductoDto> productoAsociadoOpt = model.getProductos()
						.obtenerProductoPorId(nomrbeProductoAsociado); // Llama con el ID (int)

				if (productoAsociadoOpt.isPresent()) {
					String nombreProductoAsociado = productoAsociadoOpt.get().getNombre();
					// **Finalmente, selecciona el nombre del producto asociado en el JComboBox.**
					editarProveFrame.getComboProductos().setSelectedItem(nombreProductoAsociado);
				} else {
					// Si por alguna razón el producto asociado no se encuentra, selecciona la
					// opción por defecto.
					editarProveFrame.getComboProductos().setSelectedItem("Seleccione un producto");
				}

				// TODO: Si tienes un JComboBox para el tipo de proveedor en EditarProve_Frame,
				// selecciona el tipo correcto aquí
				// editarProveFrame.getComboTipoProveedor().setSelectedItem(proveedor.getTipoProveedor());
				// // si existiera getTipoProveedor()

			} else {
				// Proveedor no encontrado con esa cedula
				JOptionPane.showMessageDialog(editarProveFrame,
						"No se encontró ningún proveedor con la cédula/NIT ingresada.", "Proveedor no encontrado",
						JOptionPane.INFORMATION_MESSAGE);
				// Limpia o resetea los campos si el proveedor no se encontró
				editarProveFrame.getTxtNombre().setText("");
				editarProveFrame.getTxtTelefono().setText("");
				editarProveFrame.getTxtDireccion().setText("");
				editarProveFrame.getComboProductos().setSelectedItem("Seleccione un producto");
				// TODO: Limpiar o seleccionar opción por defecto en comboTipoProveedor
				// Opcional: cerrar la ventana de edición
				// editarProveFrame.setVisible(false);
				// mproveedoresFrame.setVisible(true);
			}

			break;
		case "regresarEditarProve":
			editarProveFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "abrirVerProve":
			mproveedoresFrame.setVisible(false);
			verProveFrame.setVisible(true);

			// Limpiar la tabla antes de cargar nuevos datos
			DefaultTableModel modeloTablaProveedores = (DefaultTableModel) verProveFrame.getTbProve().getModel();
			modeloTablaProveedores.setRowCount(0);

			// Obtener la lista de todos los proveedores
			List<ProveedorDto> listaProveedores = model.getProveedores().obtenerTodosLosProveedores();

			if (listaProveedores != null && !listaProveedores.isEmpty()) {
				for (ProveedorDto proveedor : listaProveedores) {
					Object[] fila = new Object[5]; // 5 columnas: Nombre, NIT/Cédula, Teléfono, Correo, Dirección
					fila[0] = proveedor.getNombre();
					fila[1] = proveedor.getCedula();
					fila[2] = proveedor.getContacto();
					fila[3] = "N/A"; // No hay campo de correo en ProveedorDto
					fila[4] = proveedor.getDireccion();

					modeloTablaProveedores.addRow(fila);
				}
			} else {
				JOptionPane.showMessageDialog(verProveFrame, "No hay proveedores registrados en el sistema.",
						"Información", JOptionPane.INFORMATION_MESSAGE);
			}
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

			// Limpiar la tabla antes de cargar nuevos datos
			DefaultTableModel modeloTablaPedidos = (DefaultTableModel) verPedidosFrame.getTbPedidos().getModel();
			modeloTablaPedidos.setRowCount(0);

			// Obtener la lista de todos los movimientos de proveedores
			List<MoviProveInDto> listaMovimientos = model.getMoviProveIn().obtener_todos_los_movi_prove_in();

			// Listas para llenar los ComboBoxes de filtro
			Set<String> productosUnicos = new java.util.HashSet<>();
			Set<String> proveedoresUnicos = new java.util.HashSet<>();
			Set<String> fechasUnicas = new java.util.HashSet<>();

			if (listaMovimientos != null && !listaMovimientos.isEmpty()) {
				for (MoviProveInDto movimiento : listaMovimientos) {
					// Obtener el nombre del producto
					String nombreProducto = "N/A";
					Optional<ProductoDto> productoEnPedidoOpt = model.getProductos()
							.obtenerProductoPorId1(movimiento.getIdProducto()); // Usar el ID (int)
					if (productoEnPedidoOpt.isPresent()) {
						nombreProducto = productoEnPedidoOpt.get().getNombre();
						productosUnicos.add(nombreProducto); // Agregar producto al set de unicos
					} else {
						System.err
								.println("Advertencia: No se encontró el producto con ID " + movimiento.getIdProducto()
										+ " para el movimiento " + movimiento.getIdMovimientoProveedorINC());
						nombreProducto = "Producto Desconocido (ID: " + movimiento.getIdProducto() + ")";
					}

					// Obtener el nombre del proveedor (Necesitas un método para obtener Proveedor
					// por ID de Movimiento o Proveedor)
					// Asumiendo que puedes obtener el ProveedorDto usando el idInventario o algún
					// otro campo del movimiento
					// String nombreProveedor = "N/A"; // Variable duplicada, comentada o eliminada
					// TODO: Implementar lógica para obtener el proveedor y añadir su nombre a
					// proveedoresUnicos
					// Ejemplo (requiere método en model.getProveedores() que busque por
					// idInventario o similar):
					// Optional<ProveedorDto> proveedorOpt =
					// model.getProveedores().obtenerProveedorPorInventarioId(movimiento.getIdInventario());
					// if(proveedorOpt.isPresent()) {
					// nombreProveedor = proveedorOpt.get().getNombre();
					// proveedoresUnicos.add(nombreProveedor);
					// }

					// Formatear y agregar fecha al set de unicos
					if (movimiento.getFecha() != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						fechasUnicas.add(sdf.format(movimiento.getFecha()));
					}

					// Crear la fila con los datos del movimiento
					Object[] fila = new Object[7];
					fila[0] = movimiento.getIdMovimientoProveedorINC();
					fila[1] = nombreProducto;
					fila[2] = movimiento.getTipoMovimiento();
					fila[3] = movimiento.getCantidad();
					fila[4] = movimiento.getFecha(); // Puedes formatear la fecha aquí si lo prefieres
					fila[5] = movimiento.getMotivo();
					fila[6] = movimiento.getIdInventario(); // O nombreProveedor si lo obtienes

					modeloTablaPedidos.addRow(fila);
				}
			}

			// Llenar los ComboBoxes de filtro
			verPedidosFrame.getCboxMetodoPago().removeAllItems(); // ComboBox para Producto
			verPedidosFrame.getCboxMetodoPago().addItem("Producto:");
			List<String> productosOrdenados = new ArrayList<>(productosUnicos);
			Collections.sort(productosOrdenados);
			for (String producto : productosOrdenados) {
				verPedidosFrame.getCboxMetodoPago().addItem(producto);
			}

			verPedidosFrame.getCboxEstado().removeAllItems(); // ComboBox para Proveedor
			verPedidosFrame.getCboxEstado().addItem("Proveedor:");
			// TODO: Llenar ComboBox de Proveedor una vez que se pueda obtener el nombre del
			// proveedor por movimiento
			// List<String> proveedoresOrdenados = new ArrayList<>(proveedoresUnicos);
			// Collections.sort(proveedoresOrdenados);
			// for (String proveedor : proveedoresOrdenados) {
			// verPedidosFrame.getCboxEstado().addItem(proveedor);
			// }

			verPedidosFrame.getCboxFecha().removeAllItems(); // ComboBox para Fecha
			verPedidosFrame.getCboxFecha().addItem("Fecha:");
			List<String> fechasOrdenadas = new ArrayList<>(fechasUnicas);
			Collections.sort(fechasOrdenadas);
			for (String fecha : fechasOrdenadas) {
				verPedidosFrame.getCboxFecha().addItem(fecha);
			}

			if (listaMovimientos == null || listaMovimientos.isEmpty()) {
				JOptionPane.showMessageDialog(verPedidosFrame,
						"No hay movimientos de proveedores registrados en el sistema.", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		case "regresarVerPedidos":
			verPedidosFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "abrirRegistroPedido":
			mproveedoresFrame.setVisible(false);
			registroPedidoFrame.setVisible(true);

			// Limpiar y cargar el combobox de proveedores
			registroPedidoFrame.getTxtProvedor().removeAllItems();
			registroPedidoFrame.getTxtProvedor().addItem("Seleccione un proveedor");

			List<ProveedorDto> listaProveedoresPedido = model.getProveedores().obtenerTodosLosProveedores();
			if (listaProveedoresPedido != null) {
				for (ProveedorDto proveedor : listaProveedoresPedido) {
					registroPedidoFrame.getTxtProvedor().addItem(proveedor.getNombre() + " - " + proveedor.getCedula());
				}
			}

			// Limpiar y cargar el combobox de productos
			registroPedidoFrame.getTxtProducto().removeAllItems();
			registroPedidoFrame.getTxtProducto().addItem("Seleccione un producto");

			List<ProductoDto> listaProductosPedido = model.getProductos().obtenerTodosLosProductos();
			if (listaProductosPedido != null) {
				for (ProductoDto producto : listaProductosPedido) {
					registroPedidoFrame.getTxtProducto().addItem(producto.getNombre() + " - $" + producto.getPrecio());
				}
			}
			break;
		case "regresarRegistroPedido":
			registroPedidoFrame.setVisible(false);
			mproveedoresFrame.setVisible(true);
			break;
		case "pdfCliente":
			ReporteClientesPDF reporteC = new ReporteClientesPDF();
			reporteC.generarReportePDF();
			break;
		case "pdfProducto":
			ReporteProductosPDF reporteP = new ReporteProductosPDF();
			reporteP.generarReportePDF();
			break;
		case "pdfHistorialVentas":
			ReporteFacturasPDF reporteF = new ReporteFacturasPDF();
			reporteF.generarReportePDF();
		case "pdfDevolucion":
			ReporteDevolucionesProveedorPDF reporteDevo = new ReporteDevolucionesProveedorPDF();
			reporteDevo.generarReportePDF();
			break;
		case "pdfProveedor":
			ReporteProveedoresPDF reporteProve = new ReporteProveedoresPDF();
			reporteProve.generarReportePDF();
			break;
		case "pdfHistorialPedido":
			ReportePedidosPDF reportePedido = new ReportePedidosPDF();
			reportePedido.generarReportePDF();
			break;
		case "Dian":
			FacturaElectronicaPDF f = new FacturaElectronicaPDF();
			f.emitirFacturaElectronica(nombreUsuario, model);
		default:
			throw new IllegalArgumentException("Comando no reconocido: " + command);
		}
	}

	private void filtrarProveedores() {
		if (verProveFrame == null)
			return;

		String textoBusqueda = verProveFrame.getTxtBuscar().getText().toLowerCase();
		String metodoPago = (String) verProveFrame.getCboxMetodoPago().getSelectedItem();
		String fecha = (String) verProveFrame.getCboxFecha().getSelectedItem();
		String estado = (String) verProveFrame.getCboxEstado().getSelectedItem();

		DefaultTableModel modeloTabla = (DefaultTableModel) verProveFrame.getTbProve().getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
		verProveFrame.getTbProve().setRowSorter(sorter);

		// Crear filtros
		List<RowFilter<Object, Object>> filters = new ArrayList<>();

		// Filtro de texto de búsqueda
		if (!textoBusqueda.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + textoBusqueda));
		}

		// Aplicar filtros
		if (!filters.isEmpty()) {
			RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
			sorter.setRowFilter(combinedFilter);
		} else {
			sorter.setRowFilter(null);
		}
	}

	private void filtrarPedidos() {
		if (verPedidosFrame == null)
			return;

		DefaultTableModel modeloTabla = (DefaultTableModel) verPedidosFrame.getTbPedidos().getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
		verPedidosFrame.getTbPedidos().setRowSorter(sorter);

		List<RowFilter<Object, Object>> filters = new ArrayList<>();

		// Filtro de texto de búsqueda (en todas las columnas)
		String textoBusqueda = verPedidosFrame.getTxtBuscar().getText().trim().toLowerCase();
		if (!textoBusqueda.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + textoBusqueda));
		}

		// Filtro por Producto (usando cboxMetodoPago)
		String productoSeleccionado = (String) verPedidosFrame.getCboxMetodoPago().getSelectedItem();
		if (productoSeleccionado != null && !productoSeleccionado.equals("Producto:")) {
			// Asumiendo que la columna de Producto es la segunda (indice 1)
			filters.add(RowFilter.regexFilter("(?i)" + productoSeleccionado, 1));
		}

		// Filtro por Proveedor (usando cboxEstado)
		String proveedorSeleccionado = (String) verPedidosFrame.getCboxEstado().getSelectedItem();
		if (proveedorSeleccionado != null && !proveedorSeleccionado.equals("Proveedor:")) {
			// TODO: Implementar filtro por proveedor. Necesitas obtener el nombre del
			// proveedor en la tabla
			// y filtrar por el nombre seleccionado. Asumiendo columna de Proveedor es la
			// séptima (indice 6) si muestras ID Inventario o donde muestres el nombre del
			// proveedor
			// filters.add(RowFilter.regexFilter("(?i)" + proveedorSeleccionado, 6));
		}

		// Filtro por Fecha (usando cboxFecha)
		String fechaSeleccionada = (String) verPedidosFrame.getCboxFecha().getSelectedItem();
		if (fechaSeleccionada != null && !fechaSeleccionada.equals("Fecha:")) {
			// Asumiendo que la columna de Fecha es la quinta (indice 4)
			filters.add(RowFilter.regexFilter("(?i)" + fechaSeleccionada, 4));
		}

		// Aplicar filtros combinados
		if (!filters.isEmpty()) {
			RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
			sorter.setRowFilter(combinedFilter);
		} else {
			sorter.setRowFilter(null); // Remover todos los filtros si no hay ninguno seleccionado
		}
	}

	private void cargarHistorialVentas() {
		try {
			// Obtener todas las facturas
			List<FacturasDto> facturas = model.getFacturas().obtener_todas_las_facturas();
			Object[][] datos = new Object[facturas.size()][7];

			for (int i = 0; i < facturas.size(); i++) {
				FacturasDto factura = facturas.get(i);

				// Obtener el cliente
				String nombreCliente = "N/A";
				Optional<ClientesDto> clienteOpt = model.getClientes().ver_cliente(factura.getIdCliente());
				nombreCliente = clienteOpt.map(ClientesDto::getNombre).orElse("N/A");

				// Formatear la fecha
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String fechaFormateada = sdf.format(factura.getFecha());

				// Calcular el total
				double total = 0;
				List<DFacturaDto> detalles = model.getDfactura()
						.obtener_detalles_por_id_factura(factura.getIdFactura());
				for (DFacturaDto detalle : detalles) {
					total += detalle.getTotal();
				}

				datos[i][0] = factura.getIdFactura();
				datos[i][1] = fechaFormateada;
				datos[i][2] = nombreCliente;
				datos[i][3] = factura.getEstadoPago(); // Columna "Método de pago"
				datos[i][4] = factura.getEstadoPago(); // Columna "Estado"
				datos[i][5] = String.format("$%.2f", total); // Columna "Total"
				datos[i][6] = "Ver"; // Columna "Ver"
			}

			historialVentasUI.actualizarTabla(datos);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(historialVentasUI,
					"Error al cargar el historial de ventas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void filtrarHistorialVentas() {
		try {
			String textoBusqueda = historialVentasUI.getTxtBuscar().getText().toLowerCase();
			String metodoPago = (String) historialVentasUI.getCbMetodoPago().getSelectedItem();
			String estado = (String) historialVentasUI.getCbEstado().getSelectedItem();
			Date fechaSeleccionada = (Date) historialVentasUI.getTxtFecha().getValue();

			// Obtener todas las facturas
			List<FacturasDto> facturas = model.getFacturas().obtener_todas_las_facturas();
			List<Object[]> datosFiltrados = new ArrayList<>();

			for (FacturasDto factura : facturas) {
				// Obtener el cliente
				String nombreCliente = "N/A";
				Optional<ClientesDto> clienteOpt = model.getClientes().ver_cliente(factura.getIdCliente());
				nombreCliente = clienteOpt.map(ClientesDto::getNombre).orElse("N/A");

				// Formatear la fecha
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String fechaFormateada = sdf.format(factura.getFecha());

				// Calcular el total
				double total = 0;
				List<DFacturaDto> detalles = model.getDfactura()
						.obtener_detalles_por_id_factura(factura.getIdFactura());
				for (DFacturaDto detalle : detalles) {
					total += detalle.getTotal();
				}

				// Aplicar filtros
				boolean cumpleFiltros = true;

				// Filtro de texto
				if (!textoBusqueda.isEmpty()) {
					cumpleFiltros = cumpleFiltros
							&& (String.valueOf(factura.getIdFactura()).toLowerCase().contains(textoBusqueda)
									|| nombreCliente.toLowerCase().contains(textoBusqueda)
									|| fechaFormateada.toLowerCase().contains(textoBusqueda));
				}

				// Filtro de método de pago
				if (metodoPago != null && !metodoPago.isEmpty()) {
					cumpleFiltros = cumpleFiltros && factura.getEstadoPago().equals(metodoPago);
				}

				// Filtro de estado
				if (estado != null && !estado.isEmpty()) {
					cumpleFiltros = cumpleFiltros && factura.getEstadoPago().equals(estado);
				}

				// Filtro de fecha
				if (fechaSeleccionada != null) {
					SimpleDateFormat sdfDia = new SimpleDateFormat("dd/MM/yyyy");
					cumpleFiltros = cumpleFiltros
							&& sdfDia.format(factura.getFecha()).equals(sdfDia.format(fechaSeleccionada));
				}

				if (cumpleFiltros) {
					Object[] fila = new Object[7];
					fila[0] = factura.getIdFactura();
					fila[1] = fechaFormateada;
					fila[2] = nombreCliente;
					fila[3] = factura.getEstadoPago(); // Columna "Método de pago"
					fila[4] = factura.getEstadoPago(); // Columna "Estado"
					fila[5] = String.format("$%.2f", total); // Columna "Total"
					fila[6] = "Ver"; // Columna "Ver"
					datosFiltrados.add(fila);
				}
			}

			// Actualizar la tabla con los datos filtrados
			historialVentasUI.actualizarTabla(datosFiltrados.toArray(new Object[0][]));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(historialVentasUI,
					"Error al filtrar el historial de ventas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}