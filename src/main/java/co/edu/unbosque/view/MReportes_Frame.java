package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class MReportes_Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lbTitulo;
	private JLabel lbLogoPapel;
	private JLabel lbHistoriaV;
	private JLabel lbClientes;
	private JLabel lbHistorialPedi;
	private JLabel lbProveedores;
	private JLabel lbDevolucionProve;
	private JLabel lbProductos;

	private JButton btnHistoriaV;
	private JButton btnClientes;
	private JButton btnHistoriaPedi;
	private JButton btnProveedores;
	private JButton btnDevolucionProve;
	private JButton btnProductos;
	private JButton btnRegresar;
	private JButton btnDian;

	private final String HISTORIAV = "Historial de Ventas";
	private final String CLIENTES = "Clientes";
	private final String HISTORIAPEDI = "Historial de Pedidos";
	private final String PROVEEDORES = "Proveedores";
	private final String DEVOLUCIONPROVE = "Devoluciones del Proveedor";
	private final String PRODUCTOS = "Productos";
	private final String FACTURAELECTRONICA = "Emitir Factura electronica";

	public MReportes_Frame() {
		setTitle("Papelería de Rosita - Módulo de Reportes");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JPanel fondo = new JPanel(new BorderLayout());
		fondo.setBackground(Color.decode("#e8f0fe"));
		getContentPane().add(fondo);

		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		ImageIcon icon = new ImageIcon("src/logo.png");
		Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		lbLogoPapel = new JLabel(new ImageIcon(img));
		panelSuperior.add(lbLogoPapel, BorderLayout.WEST);

		lbTitulo = new JLabel("Módulo de Reportes", SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		panelSuperior.add(lbTitulo, BorderLayout.CENTER);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setPreferredSize(new Dimension(160, 40));
		btnRegresar.setBackground(Color.decode("#e8f0fe"));
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setFocusPainted(false);
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegresar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panelSuperior.add(btnRegresar, BorderLayout.EAST);

		fondo.add(panelSuperior, BorderLayout.NORTH);

		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		contenedor.setBackground(Color.WHITE);
		contenedor.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		contenedor.add(crearPanelOpcion(HISTORIAV));
		contenedor.add(crearPanelOpcion(CLIENTES));
		contenedor.add(crearPanelOpcion(HISTORIAPEDI));
		contenedor.add(crearPanelOpcion(PROVEEDORES));
		contenedor.add(crearPanelOpcion(DEVOLUCIONPROVE));
		contenedor.add(crearPanelOpcion(PRODUCTOS));
		contenedor.add(crearPanelOpcion(FACTURAELECTRONICA));

		fondo.add(contenedor, BorderLayout.CENTER);
	}

	private JPanel crearPanelOpcion(String texto) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setMaximumSize(new Dimension(600, 60));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel icono = new JLabel(new ImageIcon("src/archivo.png"));
		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(new Font("Arial", Font.PLAIN, 18));

		JPanel izq = new JPanel(new FlowLayout(FlowLayout.LEFT));
		izq.setBackground(Color.WHITE);
		izq.add(icono);
		izq.add(etiqueta);

		JButton boton = new JButton("Generar PDF");
		boton.setPreferredSize(new Dimension(150, 35));
		boton.setBackground(new Color(66, 133, 244));
		boton.setForeground(Color.WHITE);
		boton.setFont(new Font("Arial", Font.BOLD, 14));
		boton.setFocusPainted(false);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		switch (texto) {
		case HISTORIAV -> {
			lbHistoriaV = etiqueta;
			btnHistoriaV = boton;
		}
		case CLIENTES -> {
			lbClientes = etiqueta;
			btnClientes = boton;
		}
		case HISTORIAPEDI -> {
			lbHistorialPedi = etiqueta;
			btnHistoriaPedi = boton;
		}
		case PROVEEDORES -> {
			lbProveedores = etiqueta;
			btnProveedores = boton;
		}
		case DEVOLUCIONPROVE -> {
			lbDevolucionProve = etiqueta;
			btnDevolucionProve = boton;
		}
		case PRODUCTOS -> {
			lbProductos = etiqueta;
			btnProductos = boton;
		}
		case FACTURAELECTRONICA -> {
			lbProveedores = etiqueta;
			btnDian = boton;
		}

		}

		panel.add(izq, BorderLayout.WEST);
		panel.add(boton, BorderLayout.EAST);
		return panel;
	}

	// Getters
	public JButton getBtnHistoriaV() {
		return btnHistoriaV;
	}

	public JButton getBtnClientes() {
		return btnClientes;
	}

	public JButton getBtnHistoriaPedi() {
		return btnHistoriaPedi;
	}

	public JButton getBtnProveedores() {
		return btnProveedores;
	}

	public JButton getBtnDevolucionProve() {
		return btnDevolucionProve;
	}

	public JButton getBtnProductos() {
		return btnProductos;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JButton getBtnDian() {
		return btnDian;
	}

}
