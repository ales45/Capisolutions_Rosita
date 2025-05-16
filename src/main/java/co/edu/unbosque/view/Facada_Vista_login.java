package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class Facada_Vista_login extends JFrame {
	private static final long serialVersionUID = 1596789877655874267L;
	private JLabel lblLogo, lblTitulo, lblInicioS, lblUsuario, lblContraseña;
	private JTextField txtUsuario;
	private JPasswordField jpContraseña;
	private JButton btnInicio;

	public Facada_Vista_login() {
		setTitle("Papelería de Rosita");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Panel principal (color crema)
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(255, 245, 225)); // Fondo pastel
		getContentPane().add(mainPanel);

		// Panel blanco central
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(500, 400));
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Logo (más pequeño)
		ImageIcon originalIcon = new ImageIcon("src/logo.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		lblLogo = new JLabel(new ImageIcon(scaledImage));
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(lblLogo);

		// Título
		lblTitulo = new JLabel("Papelería de Rosita");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(lblTitulo);

		// Subtítulo
		lblInicioS = new JLabel("Inicio de Sesión");
		lblInicioS.setFont(new Font("Arial", Font.PLAIN, 16));
		lblInicioS.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(lblInicioS);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Usuario
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		txtUsuario = new JTextField(20);
		txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
		centerPanel.add(lblUsuario);
		centerPanel.add(txtUsuario);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Contraseña
		lblContraseña = new JLabel("Contraseña");
		lblContraseña.setFont(new Font("Arial", Font.BOLD, 14));
		jpContraseña = new JPasswordField(20);
		jpContraseña.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		jpContraseña.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
		centerPanel.add(lblContraseña);
		centerPanel.add(jpContraseña);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Botón
		btnInicio = new JButton("Iniciar Sesión");
		btnInicio.setBackground(new Color(180, 240, 200)); // Verde claro
		btnInicio.setFocusPainted(false);
		btnInicio.setFont(new Font("Arial", Font.PLAIN, 14));
		btnInicio.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnInicio.setCursor(new Cursor(Cursor.HAND_CURSOR));
		centerPanel.add(btnInicio);

		mainPanel.add(centerPanel); // Agrega el centro al grid central

		setVisible(true);
	}

	
}