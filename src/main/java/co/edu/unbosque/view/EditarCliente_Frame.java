package prueba_usuarios;

import javax.swing.*;
import java.awt.*;

public class EditarCliente_Frame extends JFrame {
	private JTextField txtNombre, txtCedula, txtTelefono, txtDireccion;
	private JComboBox<String> comboTipoCliente;
	private JButton btnGuardar;
	private JButton btnLimpiar;
	private JButton btnRegresar;

	public EditarCliente_Frame() {
		setTitle("Editar Cliente");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Fondo azul claro
		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(230, 242, 255));
		fondo.setLayout(new GridBagLayout());
		add(fondo);

		// Box central blanco
		JPanel box = new JPanel(new BorderLayout());
		box.setBackground(Color.WHITE);
		box.setPreferredSize(new Dimension(450, 430));
		box.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)));

		// Panel superior: título y logo
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);

		ImageIcon originalIcon = new ImageIcon("src/logo.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
		topPanel.add(logoLabel, BorderLayout.WEST);

		JLabel titulo = new JLabel("Editar Cliente", SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		topPanel.add(titulo, BorderLayout.CENTER);

		box.add(topPanel, BorderLayout.NORTH);

		// Formulario
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 8, 10, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		txtNombre = new JTextField(20);
		txtCedula = new JTextField(20);
		txtTelefono = new JTextField(20);
		txtDireccion = new JTextField(20);
		comboTipoCliente = new JComboBox<>(new String[] { "Natural", "Jurídico" });

		int row = 0;
		addField(formPanel, gbc, row++, "Nombre:", txtNombre);
		addField(formPanel, gbc, row++, "Cédula/NIT:", txtCedula);
		addField(formPanel, gbc, row++, "Teléfono:", txtTelefono);
		addField(formPanel, gbc, row++, "Dirección:", txtDireccion);
		addField(formPanel, gbc, row++, "Tipo de cliente:", comboTipoCliente);

		box.add(formPanel, BorderLayout.CENTER);

		// Panel inferior con botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.WHITE);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(0, 153, 102));
		btnGuardar.setForeground(Color.WHITE);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(255, 204, 0));
		btnLimpiar.setForeground(Color.BLACK);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(230, 230, 230));

		// Acciones de botones
		btnGuardar.addActionListener(e -> guardarCambios());
		btnLimpiar.addActionListener(e -> limpiarCampos());
		btnRegresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Regresando..."));

		buttonPanel.add(btnRegresar);
		buttonPanel.add(btnLimpiar);
		buttonPanel.add(btnGuardar);

		box.add(buttonPanel, BorderLayout.SOUTH);

		fondo.add(box);
	}

	private void addField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent inputField) {
		gbc.gridx = 0;
		gbc.gridy = row;
		panel.add(new JLabel(labelText), gbc);

		gbc.gridx = 1;
		panel.add(inputField, gbc);
	}

	private void guardarCambios() {
		if (txtNombre.getText().trim().isEmpty() || txtCedula.getText().trim().isEmpty()
				|| txtTelefono.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		String mensaje = String.format("Nombre: %s\nCédula/NIT: %s\nTeléfono: %s\nDirección: %s\nTipo: %s",
				txtNombre.getText(), txtCedula.getText(), txtTelefono.getText(), txtDireccion.getText(),
				comboTipoCliente.getSelectedItem());
		JOptionPane.showMessageDialog(this, mensaje, "Cliente Actualizado", JOptionPane.INFORMATION_MESSAGE);
	}

	private void limpiarCampos() {
		txtNombre.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		comboTipoCliente.setSelectedIndex(0);
	}

	// Getters y Setters de los botones

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(JButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

	
}
