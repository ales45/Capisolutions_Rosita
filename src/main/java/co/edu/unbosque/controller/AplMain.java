package co.edu.unbosque.controller;

import javax.swing.SwingUtilities;

import co.edu.unbosque.view.ControladorPrueba;
import co.edu.unbosque.view.Devoluciones_Frame;
import co.edu.unbosque.view.EliminarVenta_Frame;
import co.edu.unbosque.view.HistorialVentasUI;
import co.edu.unbosque.view.MClientes_View;
import co.edu.unbosque.view.MReportes_Frame;
import co.edu.unbosque.view.MVentas_View;
import co.edu.unbosque.view.NuevaVenta_View;
import co.edu.unbosque.view.User_View;

public class AplMain {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			User_View userView = new User_View();
			MVentas_View mventasView = new MVentas_View();
			MClientes_View mclientesView = new MClientes_View();
			MReportes_Frame mreportesFrame = new MReportes_Frame();
			NuevaVenta_View nuevaVentaView = new NuevaVenta_View();
			EliminarVenta_Frame eliminar = new EliminarVenta_Frame();
			HistorialVentasUI historialv = new HistorialVentasUI();
			Devoluciones_Frame dev = new Devoluciones_Frame();
			ControladorPrueba controlador = new ControladorPrueba(userView, mventasView, mclientesView, mreportesFrame,
					nuevaVentaView, eliminar, historialv, dev);
			controlador.iniciar();
		});
	}
}