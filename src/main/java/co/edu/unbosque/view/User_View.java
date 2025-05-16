package prueba_usuarios;

import javax.swing.*;
import java.awt.*;

public class User_View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblLogo;
	private JButton btnventas, btnclientes, btnReportes, btnCerrarSesion;

	public User_View() {
		setTitle("Papelería de Rosita - Menú Usuario");
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

		// Logo a la izquierda
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
		JPanel botonesPanel = new JPanel(new GridLayout(2, 2, 40, 40)); // Ajustado a 2x2 por estética
		botonesPanel.setBackground(Color.WHITE);

		btnventas = new JButton("Módulo de Ventas");
		btnclientes = new JButton("Módulo de Clientes");
		btnReportes = new JButton("Módulo de Reportes");

		estiloBoton(btnventas);
		estiloBoton(btnclientes);
		estiloBoton(btnReportes);

		botonesPanel.add(btnventas);
		botonesPanel.add(btnclientes);
		botonesPanel.add(btnReportes);

		contenedor.add(Box.createVerticalGlue());
		contenedor.add(botonesPanel);
		contenedor.add(Box.createVerticalGlue());

		fondo.add(contenedor, BorderLayout.CENTER);
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

	public JButton getBtnclientes() {
		return btnclientes;
	}

	public JButton getBtnReportes() {
		return btnReportes;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	
}
