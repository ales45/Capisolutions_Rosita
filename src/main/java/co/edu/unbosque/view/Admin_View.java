package prueba_usuarios;

import javax.swing.*;
import java.awt.*;

public class Admin_View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblLogo;
	private JButton btnventas, btnproveedor, btnclientes, btnproducto, btnReportes, btnCerrarSesion;

	public Admin_View() {
		setTitle("Papelería de Rosita - Menú Principal");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Fondo rosado
		JPanel fondo = new JPanel(new BorderLayout());
		fondo.setBackground(new Color(255, 230, 230));
		getContentPane().add(fondo);

		// Panel superior (logo + cerrar sesión)
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		// Logo centrado
		ImageIcon icon = new ImageIcon("src/logo.png");
		Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		lblLogo = new JLabel(new ImageIcon(img));
		panelSuperior.add(lblLogo, BorderLayout.WEST);

		// Botón cerrar sesión a la derecha
		btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setPreferredSize(new Dimension(160, 40));
		btnCerrarSesion.setBackground(new Color(255, 150, 150));
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 14));
		btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCerrarSesion.setBorder(BorderFactory.createLineBorder(Color.PINK));
		panelSuperior.add(btnCerrarSesion, BorderLayout.EAST);

		fondo.add(panelSuperior, BorderLayout.NORTH);

		// Caja blanca principal
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		contenedor.setBackground(Color.WHITE);
		contenedor.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		// Panel botones centrales
		JPanel botonesPanel = new JPanel(new GridLayout(3, 2, 40, 40));
		botonesPanel.setBackground(Color.WHITE);

		btnventas = new JButton("Módulo de Ventas");
		btnproveedor = new JButton("Módulo de Proveedores");
		btnclientes = new JButton("Módulo de Clientes");
		btnproducto = new JButton("Módulo de Productos");
		btnReportes = new JButton("Módulo de Reportes");

		estiloBoton(btnventas);
		estiloBoton(btnproveedor);
		estiloBoton(btnclientes);
		estiloBoton(btnproducto);
		estiloBoton(btnReportes);

		botonesPanel.add(btnventas);
		botonesPanel.add(btnproveedor);
		botonesPanel.add(btnclientes);
		botonesPanel.add(btnproducto);
		botonesPanel.add(btnReportes);

		contenedor.add(Box.createVerticalGlue());
		contenedor.add(botonesPanel);
		contenedor.add(Box.createVerticalGlue());

		fondo.add(contenedor, BorderLayout.CENTER);
		setVisible(true);
	}

	private void estiloBoton(JButton boton) {
		boton.setPreferredSize(new Dimension(300, 80));
		boton.setBackground(new Color(180, 230, 200));
		boton.setFocusPainted(false);
		boton.setFont(new Font("Arial", Font.BOLD, 18));
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setBorder(BorderFactory.createLineBorder(new Color(160, 200, 180), 2, true));
	}

	public JButton getBtnventas() {
		return btnventas;
	}

	public JButton getBtnproveedor() {
		return btnproveedor;
	}

	public JButton getBtnclientes() {
		return btnclientes;
	}

	public JButton getBtnproducto() {
		return btnproducto;
	}

	public JButton getBtnReportes() {
		return btnReportes;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Admin_View::new);
	}
}
