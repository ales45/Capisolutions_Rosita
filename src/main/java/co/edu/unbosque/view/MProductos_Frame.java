package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class MProductos_Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final String NUEVOPROD = "NUEVOPROD";
	public static final String EDITARPROD = "EDITARPROD";
	public static final String VERPROD = "VERPROD";
	public static final String ELIMINARPROD = "ELIMINARPROD";

	private JLabel lbTitulo;
	private JButton btnNuevoProducto;
	private JButton btnEditarProducto;
	private JButton btnVerProductos;
	private JButton btnEliminarProducto;
	private JButton btnRegresar;

	public MProductos_Frame() {
		setTitle("Papelería de Rosita - Gestión de Productos");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JPanel fondo = new JPanel(new BorderLayout());
		fondo.setBackground(Color.decode("#f7ec72"));
		getContentPane().add(fondo);

		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		ImageIcon icon = new ImageIcon("src/logo.png");
		Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		lbTitulo = new JLabel(new ImageIcon(img));
		panelSuperior.add(lbTitulo, BorderLayout.WEST);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setPreferredSize(new Dimension(160, 40));
		btnRegresar.setBackground(Color.decode("#f7ec72"));
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegresar.setFocusPainted(false);
		btnRegresar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panelSuperior.add(btnRegresar, BorderLayout.EAST);

		fondo.add(panelSuperior, BorderLayout.NORTH);

		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		contenedor.setBackground(Color.WHITE);
		contenedor.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		JPanel botonesPanel = new JPanel(new GridLayout(2, 2, 40, 40));
		botonesPanel.setBackground(Color.WHITE);

		btnNuevoProducto = new JButton("Nuevo Producto");
		btnEditarProducto = new JButton("Editar Producto");
		btnVerProductos = new JButton("Ver Productos Con Su Stock");
		btnEliminarProducto = new JButton("Eliminar Un Producto");

		estiloBoton(btnNuevoProducto);
		estiloBoton(btnEditarProducto);
		estiloBoton(btnVerProductos);
		estiloBoton(btnEliminarProducto);

		botonesPanel.add(btnNuevoProducto);
		botonesPanel.add(btnEditarProducto);
		botonesPanel.add(btnVerProductos);
		botonesPanel.add(btnEliminarProducto);

		contenedor.add(Box.createVerticalGlue());
		contenedor.add(botonesPanel);
		contenedor.add(Box.createVerticalGlue());

		fondo.add(contenedor, BorderLayout.CENTER);
	}

	private void estiloBoton(JButton boton) {
		boton.setPreferredSize(new Dimension(300, 80));
		boton.setBackground(Color.decode("#e0e0e0"));
		boton.setFocusPainted(false);
		boton.setFont(new Font("Arial", Font.BOLD, 16));
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
	}

	// Getters
	public JLabel getLbTitulo() {
		return lbTitulo;
	}

	public JButton getBtnNuevoProducto() {
		return btnNuevoProducto;
	}

	public JButton getBtnEditarProducto() {
		return btnEditarProducto;
	}

	public JButton getBtnVerProductos() {
		return btnVerProductos;
	}

	public JButton getBtnEliminarProducto() {
		return btnEliminarProducto;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}
}
