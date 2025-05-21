package co.edu.unbosque.controller;

import co.edu.unbosque.view.*;

public class AplMain {

	public static void main(String[] args) {
		Facada_Vista_login loginView = new Facada_Vista_login();
		Admin_View adminView = new Admin_View();
		User_View userView = new User_View();
		MVentas_View mventasView = new MVentas_View();
		MClientes_View mclientesView = new MClientes_View();
		MProductos_Frame mproductosFrame = new MProductos_Frame();
		MProveedores_Frame mproveedoresFrame = new MProveedores_Frame();
		MReportes_Frame mreportesFrame = new MReportes_Frame();
		NuevaVenta_View nuevaVentaView = new NuevaVenta_View();
		EliminarVenta_Frame eliminarVentaFrame = new EliminarVenta_Frame();
		HistorialVentasUI historialVentasUI = new HistorialVentasUI();
		Devoluciones_Frame devolucionVentasFrame = new Devoluciones_Frame();
		NuevoCliente_Frame nuevoClienteFrame = new NuevoCliente_Frame();
		EditarCliente_Frame editarClienteFrame = new EditarCliente_Frame();
		EliminarCliente_Frame eliminarClienteFrame = new EliminarCliente_Frame();
		VerCliente_Frame verClienteFrame = new VerCliente_Frame();
		NuevoProducto_Frame nuevoProductoFrame = new NuevoProducto_Frame();
		EditarProducto_Frame editarProductoFrame = new EditarProducto_Frame();
		EliminarProducto_Frame eliminarProductoFrame = new EliminarProducto_Frame();
		VerProducto_Frame verProductoFrame = new VerProducto_Frame();
		RegistroProve_Frame registroProveFrame = new RegistroProve_Frame();
		EditarProve_Frame editarProveFrame = new EditarProve_Frame();
		VerProve_Frame verProveFrame = new VerProve_Frame();
		RegistroPedido_Frame registroPedidoFrame = new RegistroPedido_Frame();
		VerPedidos_Frame verPedidosFrame = new VerPedidos_Frame();
		Devoluciones_Frame devolucionProveedoresFrame = new Devoluciones_Frame();
		NuevoInventario_Frame nuevoInv = new NuevoInventario_Frame();
		Facada_Vista_crearUsuario crearU = new Facada_Vista_crearUsuario();
		// Crear el controlador y pasar todas las vistas
		controllerprueba controller = new controllerprueba(loginView, adminView, userView, mventasView, mclientesView,
				mproductosFrame, mproveedoresFrame, mreportesFrame, nuevaVentaView, eliminarVentaFrame,
				historialVentasUI, devolucionVentasFrame, nuevoClienteFrame, editarClienteFrame, eliminarClienteFrame,
				verClienteFrame, nuevoProductoFrame, editarProductoFrame, eliminarProductoFrame, verProductoFrame,
				registroProveFrame, editarProveFrame, verProveFrame, registroPedidoFrame, verPedidosFrame,
				devolucionProveedoresFrame, nuevoInv, crearU);
	}
}
