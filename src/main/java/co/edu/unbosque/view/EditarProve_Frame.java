package prueba_usuarios;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class EditarProve_Frame extends JFrame {
	private JTextField txtNombre, txtCedula, txtTelefono, txtDireccion;
	private JComboBox<String> comboTipoProveedor;

	public EditarProve_Frame() {
		setTitle("Editar Proveedor");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(109, 141, 164));
		fondo.setLayout(new GridBagLayout());
		add(fondo);

		JPanel caja = new JPanel(new BorderLayout());
		caja.setPreferredSize(new Dimension(500, 450));
		caja.setBackground(Color.WHITE);
		caja.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)));

		JPanel top = new JPanel(new BorderLayout());
		top.setBackground(Color.WHITE);

		ImageIcon logo = new ImageIcon("src/logo.png");
		Image scaledLogo = logo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(scaledLogo));
		top.add(lblLogo, BorderLayout.WEST);

		JLabel titulo = new JLabel("Editar Proveedor", SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		top.add(titulo, BorderLayout.CENTER);

		caja.add(top, BorderLayout.NORTH);

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		txtNombre = new JTextField(20);

		txtCedula = new JTextField(20);
		soloNumeros(txtCedula);

		txtTelefono = new JTextField(20);
		soloNumeros(txtTelefono);

		txtDireccion = new JTextField(25); // más ancho

		comboTipoProveedor = new JComboBox<>(
				new String[] { "Seleccione", "Persona Natural", "Empresa", "Distribuidor" });

		int fila = 0;
		agregarCampo(formPanel, gbc, fila++, "Nombre:", txtNombre);
		agregarCampo(formPanel, gbc, fila++, "NIT o Cédula:", txtCedula);
		agregarCampo(formPanel, gbc, fila++, "Teléfono:", txtTelefono);

		gbc.gridx = 0;
		gbc.gridy = fila;
		formPanel.add(new JLabel("Dirección:"), gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		formPanel.add(txtDireccion, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = ++fila;
		formPanel.add(new JLabel("Tipo de proveedor:"), gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		formPanel.add(comboTipoProveedor, gbc);
		gbc.gridwidth = 1;

		caja.add(formPanel, BorderLayout.CENTER);

		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botones.setBackground(Color.WHITE);

		JButton btnGuardar = new JButton("Guardar");
		JButton btnRegresar = new JButton("Regresar");

		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setForeground(Color.WHITE);

		btnRegresar.setBackground(new Color(70, 100, 120));
		btnRegresar.setForeground(Color.WHITE);

		botones.add(btnGuardar);
		botones.add(btnRegresar);

		caja.add(botones, BorderLayout.SOUTH);
		fondo.add(caja);

		btnGuardar.addActionListener(e -> guardarCambios());
		btnRegresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Regresando..."));
	}

	private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent campo) {
		gbc.gridx = 0;
		gbc.gridy = fila;
		panel.add(new JLabel(texto), gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		panel.add(campo, gbc);
		gbc.gridwidth = 1;
	}

	private void guardarCambios() {
		String nombre = txtNombre.getText().trim();
		String cedula = txtCedula.getText().trim();
		String telefono = txtTelefono.getText().trim();
		String direccion = txtDireccion.getText().trim();

		if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!cedula.matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "La cédula/NIT debe contener solo números.", "Error de formato",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!telefono.matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.", "Error de formato",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);
	}	

	// Método para restringir solo números
	private void soloNumeros(JTextField campo) {
		((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {
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

}
