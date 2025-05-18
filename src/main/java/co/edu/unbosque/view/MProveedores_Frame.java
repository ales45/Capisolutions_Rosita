package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class MProveedores_Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	// Constantes para acciones
	public static final String NUEVAPROVE = "NUEVAPROVE";
	public static final String EDITARPROVE = "EDITARPROVE";
	public static final String VERPROVE = "VERPROVE";
	public static final String ELIMINARPROVE = "ELIMINARPROVE";
	public static final String DEVOLUCIONPROVE = "DEVOLUCIONPROVE";

	// Componentes de la vista
	private JLabel lbTitulo;
	private JButton btnRegresar;
	private JButton btnNuevaProve;
	private JButton btnEditarProve;
	private JButton btnVerProve;
	private JButton btnRegistrarPedido;
	private JButton btnDevolucionProve;
	private JButton btnVerPedidos_View;

	public MProveedores_Frame() {
		setTitle("Papelería de Rosita - Gestión de Proveedores");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JPanel fondo = new JPanel(new BorderLayout());
		fondo.setBackground(Color.decode("#799bb7"));
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
		btnRegresar.setBackground(Color.decode("#799bb7"));
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

		JPanel botonesPanel = new JPanel(new GridLayout(3, 2, 40, 40));
		botonesPanel.setBackground(Color.WHITE);

		// Botones renombrados
		btnNuevaProve = new JButton("Registrar Proveedor");
		btnEditarProve = new JButton("Editar Datos Proveedor");
		btnVerProve = new JButton("Ver Proveedores");
		btnRegistrarPedido = new JButton("Registrar Pedido");
		btnDevolucionProve = new JButton("Gestionar Devolución");
		btnVerPedidos_View = new JButton("Ver Pedidos");

		estiloBoton(btnNuevaProve);
		estiloBoton(btnEditarProve);
		estiloBoton(btnVerProve);
		estiloBoton(btnRegistrarPedido);
		estiloBoton(btnVerPedidos_View);
		estiloBoton(btnDevolucionProve);

		botonesPanel.add(btnNuevaProve);
		botonesPanel.add(btnEditarProve);
		botonesPanel.add(btnVerProve);
		botonesPanel.add(btnRegistrarPedido);
		botonesPanel.add(btnVerPedidos_View);
		botonesPanel.add(btnDevolucionProve);

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

	public JButton getBtnNuevaProve() {
		return btnNuevaProve;
	}

	public JButton getBtnEditarProve() {
		return btnEditarProve;
	}

	public JButton getBtnVerProve() {
		return btnVerProve;
	}

	public JButton getBtnRegistrarPedido() {
		return btnRegistrarPedido;
	}

	public void setBtnRegistrarPedido(JButton btnRegistrarPedido) {
		this.btnRegistrarPedido = btnRegistrarPedido;
	}

	public JButton getBtnDevolucionProve() {
		return btnDevolucionProve;
	}

	public JButton getBtnVerPedidos_View() {
		return btnVerPedidos_View;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}
}
