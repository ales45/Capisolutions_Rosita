package prueba_usuarios;

import javax.swing.*;
import java.awt.*;

public class MProveedores_View extends JFrame {

	private static final long serialVersionUID = 1L;

	// Componentes de la vista
	private JLabel lblLogo;
	private JButton btnRegresar;
	private JButton btnProveC;
	private JButton btnProveR;
	private JButton btnProveU;
	private JButton btnProveD;
	private JButton btnVerPedidos_View;
	private JButton btnDevolucionProve_View;

	public MProveedores_View() {
		setTitle("Papelería de Rosita - Gestión de Proveedores");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Fondo azul personalizado
		JPanel fondo = new JPanel(new BorderLayout());
		fondo.setBackground(Color.decode("#799bb7"));
		getContentPane().add(fondo);

		// Panel superior
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		// Logo
		ImageIcon icon = new ImageIcon("src/logo.png");
		Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		lblLogo = new JLabel(new ImageIcon(img));
		panelSuperior.add(lblLogo, BorderLayout.WEST);

		// Botón regresar
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

		// Contenedor central
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		contenedor.setBackground(Color.WHITE);
		contenedor.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		JPanel botonesPanel = new JPanel(new GridLayout(3, 2, 40, 40));
		botonesPanel.setBackground(Color.WHITE);

		// Botones
		btnProveC = new JButton("Registrar Proveedor");
		btnProveR = new JButton("Editar Datos Proveedor");
		btnProveU = new JButton("Registrar Pedido a Proveedor");
		btnProveD = new JButton("Ver Proveedores");
		btnVerPedidos_View = new JButton("Ver Pedidos");
		btnDevolucionProve_View = new JButton("Gestionar Devolución");

		estiloBoton(btnProveC);
		estiloBoton(btnProveR);
		estiloBoton(btnProveU);
		estiloBoton(btnProveD);
		estiloBoton(btnVerPedidos_View);
		estiloBoton(btnDevolucionProve_View);

		botonesPanel.add(btnProveC);
		botonesPanel.add(btnProveR);
		botonesPanel.add(btnProveU);
		botonesPanel.add(btnProveD);
		botonesPanel.add(btnVerPedidos_View);
		botonesPanel.add(btnDevolucionProve_View);

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

	// Getters de botones
	public JButton getBtnProveC() {
		return btnProveC;
	}

	public JButton getBtnProveR() {
		return btnProveR;
	}

	public JButton getBtnProveU() {
		return btnProveU;
	}

	public JButton getBtnProveD() {
		return btnProveD;
	}

	public JButton getBtnVerPedidos_View() {
		return btnVerPedidos_View;
	}

	public JButton getBtnDevolucionProve_View() {
		return btnDevolucionProve_View;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

}
