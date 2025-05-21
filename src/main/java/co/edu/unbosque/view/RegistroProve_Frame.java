package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class RegistroProve_Frame extends JFrame {
	private JTextField txtNombre, txtCedula, txtTelefono, txtCorreo, txtDireccion;
	private JComboBox<String> comboTipoProveedor;
	private JButton btnLimpiar, btnRegistrar, btnRegresar;
	private JComboBox<String> comboProductos;

	public RegistroProve_Frame() {
		setTitle("Registrar Proveedor");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Fondo azul oscuro
		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(109, 141, 164));
		fondo.setLayout(new GridBagLayout());
		add(fondo);

		// Caja blanca central
		JPanel caja = new JPanel(new BorderLayout());
		caja.setPreferredSize(new Dimension(500, 500));
		caja.setBackground(Color.WHITE);
		caja.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)));

		// Logo + título
		JPanel top = new JPanel(new BorderLayout());
		top.setBackground(Color.WHITE);

		ImageIcon logo = new ImageIcon("src/logo.png");
		Image scaledLogo = logo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(scaledLogo));
		top.add(lblLogo, BorderLayout.WEST);

		JLabel titulo = new JLabel("Registrar Proveedor", SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		top.add(titulo, BorderLayout.CENTER);

		caja.add(top, BorderLayout.NORTH);

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
		comboTipoProveedor = new JComboBox<>(new String[] { "Seleccione", "Persona Natural", "Empresa" });
		comboProductos = new JComboBox<>();

		int fila = 0;
		agregarCampo(formPanel, gbc, fila++, "Nombre del proveedor:", txtNombre);
		agregarCampo(formPanel, gbc, fila++, "NIT o Cédula:", txtCedula);
		agregarCampo(formPanel, gbc, fila++, "Teléfono:", txtTelefono);
		agregarCampo(formPanel, gbc, fila++, "Correo:", txtCorreo);
		agregarCampo(formPanel, gbc, fila++, "Dirección:", txtDireccion);

		// Tipo de proveedor
		gbc.gridx = 0;
		gbc.gridy = fila;
		formPanel.add(new JLabel("Tipo de proveedor (opcional):"), gbc);
		gbc.gridx = 1;
		formPanel.add(comboTipoProveedor, gbc);
		fila++;

		// Campo para seleccionar Producto
		gbc.gridx = 0;
		gbc.gridy = fila;
		formPanel.add(new JLabel("Producto Asociado:"), gbc);
		gbc.gridx = 1;
		formPanel.add(comboProductos, gbc);

		caja.add(formPanel, BorderLayout.CENTER);

		// Botones
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		botones.setBackground(Color.WHITE);

		btnLimpiar = new JButton("Limpiar formulario");
		btnRegistrar = new JButton("Registrar Proveedor");
		btnRegresar = new JButton("Regresar");

		btnRegistrar.setBackground(new Color(0, 128, 0));
		btnRegistrar.setForeground(Color.WHITE);
		btnLimpiar.setBackground(Color.LIGHT_GRAY);
		btnRegresar.setBackground(new Color(70, 100, 120));
		btnRegresar.setForeground(Color.WHITE);

		botones.add(btnLimpiar);
		botones.add(btnRegistrar);
		botones.add(btnRegresar);

		caja.add(botones, BorderLayout.SOUTH);
		fondo.add(caja);

		// Eventos
		btnLimpiar.addActionListener(e -> limpiarFormulario());
		btnRegistrar.addActionListener(e -> registrarProveedor());
		btnRegresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Regresando..."));
	}

	public void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent campo) {
		gbc.gridx = 0;
		gbc.gridy = fila;
		panel.add(new JLabel(texto), gbc);
		gbc.gridx = 1;
		panel.add(campo, gbc);
	}

	public void limpiarFormulario() {
		txtNombre.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtDireccion.setText("");
		comboTipoProveedor.setSelectedIndex(0);
		comboProductos.setSelectedIndex(0);
	}

	public void registrarProveedor() {
		String nombre = txtNombre.getText().trim();
		String cedula = txtCedula.getText().trim();
		String telefono = txtTelefono.getText().trim();
		String correo = txtCorreo.getText().trim();
		String direccion = txtDireccion.getText().trim();

		if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || correo.isEmpty() || direccion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!cedula.matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "La cédula/NIT debe contener solo números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!telefono.matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", correo)) {
			JOptionPane.showMessageDialog(this, "Correo electrónico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, "Proveedor registrado exitosamente.", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// Getters y setters para los botones

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(JButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JTextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	public void setTxtCorreo(JTextField txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JComboBox<String> getComboTipoProveedor() {
		return comboTipoProveedor;
	}

	public void setComboTipoProveedor(JComboBox<String> comboTipoProveedor) {
		this.comboTipoProveedor = comboTipoProveedor;
	}

	public JComboBox<String> getComboProductos() {
		return comboProductos;
	}

	public void setComboProductos(JComboBox<String> comboProductos) {
		this.comboProductos = comboProductos;
	}
}
