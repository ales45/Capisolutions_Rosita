package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class NuevoCliente_Frame extends JFrame {
	private JTextField txtNombre, txtCedula, txtTelefono, txtCorreo, txtDireccion;
	private JComboBox<String> comboTipoCliente;

	private JButton btnLimpiar;
	private JButton btnRegistrar;
	private JButton btnRegresar;

	public NuevoCliente_Frame() {
		setTitle("Registro de Cliente");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Fondo azul claro
		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(230, 242, 255));
		fondo.setLayout(new GridBagLayout()); // Centrar el box
		add(fondo);

		// Box central blanco
		JPanel box = new JPanel(new BorderLayout());
		box.setBackground(Color.WHITE);
		box.setPreferredSize(new Dimension(500, 500));
		box.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)));

		// Top con logo y título
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);

		// Logo redimensionado
		ImageIcon originalIcon = new ImageIcon("src/logo.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
		topPanel.add(logoLabel, BorderLayout.WEST);

		// Título centrado
		JLabel titulo = new JLabel("Registrar Cliente", SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		topPanel.add(titulo, BorderLayout.CENTER);

		box.add(topPanel, BorderLayout.NORTH);

		// Formulario
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		txtNombre = new JTextField(20);
		txtCedula = new JTextField(20);
		txtTelefono = new JTextField(20);
		txtCorreo = new JTextField(20);
		txtDireccion = new JTextField(20);
		comboTipoCliente = new JComboBox<>(new String[] { "Natural", "Jurídico" });

		// Filtros para que solo acepten números
		setNumericFilter(txtCedula);
		setNumericFilter(txtTelefono);

		int row = 0;
		addField(formPanel, gbc, row++, "Nombre completo:", txtNombre);
		addField(formPanel, gbc, row++, "Cédula/NIT:", txtCedula);
		addField(formPanel, gbc, row++, "Teléfono:", txtTelefono);
		addField(formPanel, gbc, row++, "Correo electrónico:", txtCorreo);
		addField(formPanel, gbc, row++, "Dirección:", txtDireccion);
		addField(formPanel, gbc, row++, "Tipo de cliente:", comboTipoCliente);

		box.add(formPanel, BorderLayout.CENTER);

		// Botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		buttonPanel.setBackground(Color.WHITE);

		btnLimpiar = new JButton("Limpiar formulario");
		btnRegistrar = new JButton("Registrar Cliente");
		btnRegresar = new JButton("Regresar");

		btnLimpiar.setBackground(Color.LIGHT_GRAY);
		btnRegistrar.setBackground(new Color(0, 153, 102));
		btnRegistrar.setForeground(Color.WHITE);

		buttonPanel.add(btnLimpiar);
		buttonPanel.add(btnRegistrar);
		buttonPanel.add(btnRegresar);

		btnLimpiar.addActionListener(e -> limpiarFormulario());
		btnRegistrar.addActionListener(e -> registrarCliente());
		btnRegresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Regresando..."));

		box.add(buttonPanel, BorderLayout.SOUTH);

		fondo.add(box); // Añadir el box centrado al fondo
	}

	private void addField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent inputField) {
		gbc.gridx = 0;
		gbc.gridy = row;
		panel.add(new JLabel(labelText), gbc);

		gbc.gridx = 1;
		panel.add(inputField, gbc);
	}

	private void limpiarFormulario() {
		txtNombre.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtDireccion.setText("");
		comboTipoCliente.setSelectedIndex(0);
	}

	private void registrarCliente() {
		// Verificar campos vacíos
		if (txtNombre.getText().trim().isEmpty() || txtCedula.getText().trim().isEmpty()
				|| txtTelefono.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()
				|| txtDireccion.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos incompletos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Validar correo electrónico
		String correo = txtCorreo.getText().trim();
		String regexCorreo = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		if (!Pattern.matches(regexCorreo, correo)) {
			JOptionPane.showMessageDialog(this, "Correo electrónico inválido", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Mostrar información
		String info = String.format("Nombre: %s\nCédula/NIT: %s\nTeléfono: %s\nCorreo: %s\nDirección: %s\nTipo: %s",
				txtNombre.getText(), txtCedula.getText(), txtTelefono.getText(), correo, txtDireccion.getText(),
				comboTipoCliente.getSelectedItem());
		JOptionPane.showMessageDialog(this, info, "Cliente Registrado", JOptionPane.INFORMATION_MESSAGE);
	}

	// Método para permitir solo números en un campo de texto
	private void setNumericFilter(JTextField textField) {
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				if (string.matches("\\d+")) {
					super.insertString(fb, offset, string, attr);
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (text.matches("\\d+")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});
	}

	// GETTERS de los botones
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	
}
