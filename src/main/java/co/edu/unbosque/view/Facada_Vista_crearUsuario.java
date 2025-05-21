package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class Facada_Vista_crearUsuario extends JFrame {
	private static final long serialVersionUID = 7526983547000071456L;

	private JLabel lblLogo, lblTitulo, lblSubtitulo, lblUsuario, lblContraseña, lblConfirmarContraseña;
	private JTextField txtUsuario;
	private JPasswordField jpContraseña, jpConfirmarContraseña;
	private JButton btnCrearUsuario;

	public Facada_Vista_crearUsuario() {
		setTitle("Crear Usuario - Papelería de Rosita");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Panel principal
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(255, 245, 225)); // Color crema
		getContentPane().add(mainPanel);

		// Panel blanco central
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(500, 480));
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Logo
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
		lblSubtitulo = new JLabel("Crear Nuevo Usuario");
		lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(lblSubtitulo);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Usuario
		lblUsuario = new JLabel("Nuevo Usuario");
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

		centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Confirmar contraseña
		lblConfirmarContraseña = new JLabel("Confirmar Contraseña");
		lblConfirmarContraseña.setFont(new Font("Arial", Font.BOLD, 14));
		jpConfirmarContraseña = new JPasswordField(20);
		jpConfirmarContraseña.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		jpConfirmarContraseña.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
		centerPanel.add(lblConfirmarContraseña);
		centerPanel.add(jpConfirmarContraseña);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Botón crear
		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.setBackground(new Color(180, 240, 200)); // Verde claro
		btnCrearUsuario.setFocusPainted(false);
		btnCrearUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCrearUsuario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnCrearUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCrearUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		centerPanel.add(btnCrearUsuario);
		btnCrearUsuario.addActionListener(e -> {
			if (validarContraseñas()) {
				// Aquí podrías continuar con el registro, guardar en base de datos, etc.
				JOptionPane.showMessageDialog(this, "Usuario creado con éxito.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				// Podrías cerrar la ventana con: dispose();
			}
		});

		mainPanel.add(centerPanel);

		setVisible(true);
	}

	public static void main(String[] args) {
		Facada_Vista_crearUsuario f = new Facada_Vista_crearUsuario();
		f.setVisible(true);
	}

	public boolean validarContraseñas() {
		String contraseña = new String(jpContraseña.getPassword());
		String confirmar = new String(jpConfirmarContraseña.getPassword());

		if (contraseña.isEmpty() || confirmar.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, completa ambos campos de contraseña.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!contraseña.equals(confirmar)) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Getters y Setters

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JPasswordField getJpContraseña() {
		return jpContraseña;
	}

	public JPasswordField getJpConfirmarContraseña() {
		return jpConfirmarContraseña;
	}

	public JButton getBtnCrearUsuario() {
		return btnCrearUsuario;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}

	public void setLblLogo(JLabel lblLogo) {
		this.lblLogo = lblLogo;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JLabel getLblSubtitulo() {
		return lblSubtitulo;
	}

	public void setLblSubtitulo(JLabel lblSubtitulo) {
		this.lblSubtitulo = lblSubtitulo;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JLabel getLblContraseña() {
		return lblContraseña;
	}

	public void setLblContraseña(JLabel lblContraseña) {
		this.lblContraseña = lblContraseña;
	}

	public JLabel getLblConfirmarContraseña() {
		return lblConfirmarContraseña;
	}

	public void setLblConfirmarContraseña(JLabel lblConfirmarContraseña) {
		this.lblConfirmarContraseña = lblConfirmarContraseña;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public void setJpContraseña(JPasswordField jpContraseña) {
		this.jpContraseña = jpContraseña;
	}

	public void setJpConfirmarContraseña(JPasswordField jpConfirmarContraseña) {
		this.jpConfirmarContraseña = jpConfirmarContraseña;
	}

	public void setBtnCrearUsuario(JButton btnCrearUsuario) {
		this.btnCrearUsuario = btnCrearUsuario;
	}
	
}
