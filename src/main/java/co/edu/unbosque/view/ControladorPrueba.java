package prueba_usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrueba implements ActionListener {

	private User_View userView;
	private MVentas_View mventasView;
	private MClientes_View mclientesView;
	private MReportes_Frame mreportesFrame;
	private NuevaVenta_View nuevaVentaView;
	private EliminarVenta_Frame eliminarFrame;
	private HistorialVentasUI historialV;
	private Devoluciones_Frame devolucion;
	private Admin_View adminView;
	private EditarCliente_Frame editarCliente;
	private NuevoCliente_Frame nuevoCliente;
	private EliminarCliente_Frame eliminarCliente;
	private VerCliente_Frame verCliente;
	private MProveedores_View mproveedoresView;

	public ControladorPrueba(User_View userView, MVentas_View mventasView, MClientes_View mclientesView,
			MReportes_Frame mreportesFrame, NuevaVenta_View nuevaVentaView, EliminarVenta_Frame eliminarFrame,
			HistorialVentasUI historialV, Devoluciones_Frame devolucion) {
		this.userView = userView;
		this.mventasView = mventasView;
		this.mclientesView = mclientesView;
		this.mreportesFrame = mreportesFrame;
		this.nuevaVentaView = nuevaVentaView;
		this.eliminarFrame = eliminarFrame;
		this.historialV = historialV;
		this.devolucion = devolucion;

		// Instanciar las vistas de clientes
		this.editarCliente = new EditarCliente_Frame();
		this.nuevoCliente = new NuevoCliente_Frame();
		this.eliminarCliente = new EliminarCliente_Frame();
		this.verCliente = new VerCliente_Frame();
		this.adminView = new Admin_View();
		this.mproveedoresView = new MProveedores_View();
		addListeners();
	}

	public void iniciar() {
		adminView.setVisible(true);
	}

	private void addListeners() {
		// Botones principales
		userView.getBtnventas().addActionListener(this);
		userView.getBtnventas().setActionCommand("abrirVentas");

		userView.getBtnclientes().addActionListener(this);
		userView.getBtnclientes().setActionCommand("abrirClientes");

		userView.getBtnReportes().addActionListener(this);
		userView.getBtnReportes().setActionCommand("abrirReportes");

		// botones de admin

		adminView.getBtnventas().addActionListener(this);
		adminView.getBtnventas().setActionCommand("adminVentas");

		adminView.getBtnclientes().addActionListener(this);
		adminView.getBtnclientes().setActionCommand("adminClientes");

		adminView.getBtnproducto().addActionListener(this);
		adminView.getBtnproducto().setActionCommand("adminProductos");

		adminView.getBtnproveedor().addActionListener(this);
		adminView.getBtnproveedor().setActionCommand("adminProveedores");

		adminView.getBtnReportes().addActionListener(this);
		adminView.getBtnReportes().setActionCommand("adminReportes");

		adminView.getBtnCerrarSesion().addActionListener(this);
		adminView.getBtnCerrarSesion().setActionCommand("cerrarSesion");

		// Regresar en vistas principales
		mventasView.getBtnRegresar().addActionListener(this);
		mventasView.getBtnRegresar().setActionCommand("regresarVentas");

		mclientesView.getBtnRegresar().addActionListener(this);
		mclientesView.getBtnRegresar().setActionCommand("regresarClientes");

		mreportesFrame.getBtnRegresar().addActionListener(this);
		mreportesFrame.getBtnRegresar().setActionCommand("regresarReportes");

		mproveedoresView.getBtnRegresar().addActionListener(this);
		mproveedoresView.getBtnRegresar().setActionCommand("regresarDeProveedores");

		// Botones dentro de ventas
		mventasView.getBtnNuevaVenta().addActionListener(this);
		mventasView.getBtnNuevaVenta().setActionCommand("abrirNuevaVenta");

		mventasView.getBtnEliminar().addActionListener(this);
		mventasView.getBtnEliminar().setActionCommand("abrirEliminarVenta");

		mventasView.getBtnHistorial().addActionListener(this);
		mventasView.getBtnHistorial().setActionCommand("abrirHistorialVenta");

		mventasView.getBtnCorregir().addActionListener(this);
		mventasView.getBtnCorregir().setActionCommand("devolucion");

		nuevaVentaView.getBtnRegresar().addActionListener(this);
		nuevaVentaView.getBtnRegresar().setActionCommand("regresarNuevaVenta");

		eliminarFrame.getBtnRegresar().addActionListener(this);
		eliminarFrame.getBtnRegresar().setActionCommand("regresarEliminarV");

		historialV.getBtnRegresar().addActionListener(this);
		historialV.getBtnRegresar().setActionCommand("regresarHistorial");

		devolucion.getBtnRegresar().addActionListener(this);
		devolucion.getBtnRegresar().setActionCommand("regresarDevolucion");

		// Botones de menú de clientes
		mclientesView.getBtnNuevoCliente_View().addActionListener(this);
		mclientesView.getBtnNuevoCliente_View().setActionCommand("abrirNuevoCliente");

		mclientesView.getBtnEditarCliente_View().addActionListener(this);
		mclientesView.getBtnEditarCliente_View().setActionCommand("abrirEditarCliente");

		mclientesView.getBtnVerCliente_View().addActionListener(this);
		mclientesView.getBtnVerCliente_View().setActionCommand("abrirVerClientes");

		mclientesView.getBtnEliminarCliente_View().addActionListener(this);
		mclientesView.getBtnEliminarCliente_View().setActionCommand("abrirEliminarCliente");

		// Botones regresar de sub-vistas de clientes
		nuevoCliente.getBtnRegresar().addActionListener(this);
		nuevoCliente.getBtnRegresar().setActionCommand("regresarNuevoCliente");

		editarCliente.getBtnRegresar().addActionListener(this);
		editarCliente.getBtnRegresar().setActionCommand("regresarEditarCliente");

		eliminarCliente.getBtnRegresar().addActionListener(this);
		eliminarCliente.getBtnRegresar().setActionCommand("regresarEliminarCliente");

		verCliente.getBtnRegresar().addActionListener(this);
		verCliente.getBtnRegresar().setActionCommand("regresarVerCliente");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
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

		case "regresarVentas":
			mventasView.setVisible(false);
			adminView.setVisible(true);
			break;

		case "regresarClientes":
			mclientesView.setVisible(false);
			adminView.setVisible(true);
			break;

		case "regresarReportes":
			mreportesFrame.setVisible(false);
			adminView.setVisible(true);
			break;

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
			eliminarFrame.setVisible(true);
			break;

		case "regresarEliminarV":
			mventasView.setVisible(true);
			eliminarFrame.setVisible(false);
			break;

		case "abrirHistorialVenta":
			mventasView.setVisible(false);
			historialV.setVisible(true);
			break;

		case "regresarHistorial":
			mventasView.setVisible(true);
			historialV.setVisible(false);
			break;

		case "devolucion":
			mventasView.setVisible(false);
			devolucion.setVisible(true);
			break;

		case "regresarDevolucion":
			mventasView.setVisible(true);
			devolucion.setVisible(false);
			break;

		case "abrirNuevoCliente":
			mclientesView.setVisible(false);
			nuevoCliente.setVisible(true);
			break;

		case "regresarNuevoCliente":
			nuevoCliente.setVisible(false);
			mclientesView.setVisible(true);
			break;

		case "abrirEditarCliente":
			mclientesView.setVisible(false);
			editarCliente.setVisible(true);
			break;

		case "regresarEditarCliente":
			editarCliente.setVisible(false);
			mclientesView.setVisible(true);
			break;

		case "abrirEliminarCliente":
			mclientesView.setVisible(false);
			eliminarCliente.setVisible(true);
			break;

		case "regresarEliminarCliente":
			eliminarCliente.setVisible(false);
			mclientesView.setVisible(true);
			break;

		case "abrirVerClientes":
			mclientesView.setVisible(false);
			verCliente.setVisible(true);
			break;

		case "regresarVerCliente":
			verCliente.setVisible(false);
			mclientesView.setVisible(true);
			break;
		case "adminVentas":
			adminView.setVisible(false);
			mventasView.setVisible(true);
			break;

		case "adminClientes":
			adminView.setVisible(false);
			mclientesView.setVisible(true);
			break;

		case "adminReportes":
			adminView.setVisible(false);
			mreportesFrame.setVisible(true);
			break;

		case "regresarDeVentas":
			mventasView.setVisible(false);
			adminView.setVisible(true);
			break;

		case "regresarDeClientes":
			mclientesView.setVisible(false);
			adminView.setVisible(true);
			break;

		case "regresarDeReportes":
			mreportesFrame.setVisible(false);
			adminView.setVisible(true);
			break;
//		case "adminProductos":
//			adminView.setVisible(false);
//			JOptionPane.showMessageDialog(null, "Módulo de productos aún no implementado");
//			break;

		case "adminProveedores":
			adminView.setVisible(false);
			mproveedoresView.setVisible(true);
			break;

		case "regresarDeProveedores":
			mproveedoresView.setVisible(false);
			adminView.setVisible(true);
			break;

		default:
			throw new IllegalArgumentException("Comando no reconocido: " + e.getActionCommand());
		}
	}
}
