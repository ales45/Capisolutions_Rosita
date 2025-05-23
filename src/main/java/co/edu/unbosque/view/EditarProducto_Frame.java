package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class EditarProducto_Frame extends JFrame {
	private JTextField txtId, txtNombre, txtDescripcion, txtPrecio, txtIVA;
	private JComboBox<String> comboCategoria;
	private JButton btnGuardar, btnRegresar;

	public EditarProducto_Frame() {
		setTitle("Editar Producto");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(252, 243, 174));
		fondo.setLayout(new GridBagLayout());
		add(fondo);

		JPanel box = new JPanel(new BorderLayout());
		box.setBackground(Color.WHITE);
		box.setPreferredSize(new Dimension(450, 430));
		box.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)));

		// Panel superior con logo y título
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);

		ImageIcon originalIcon = new ImageIcon("src/logo.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
		topPanel.add(logoLabel, BorderLayout.WEST);

		JLabel lblTitulo = new JLabel("Editar Producto", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		topPanel.add(lblTitulo, BorderLayout.CENTER);

		box.add(topPanel, BorderLayout.NORTH);

		// Panel de formulario
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		txtId = new JTextField(20);
		txtNombre = new JTextField(20);
		comboCategoria = new JComboBox<>(new String[] { "Seleccione", "Bebidas", "Comida", "Aseo", "Otro" });
		txtDescripcion = new JTextField(20);
		txtPrecio = new JTextField(20);
		txtIVA = new JTextField(20);

		agregarFiltroNumerico(txtPrecio);
		agregarFiltroNumerico(txtIVA);

		int row = 0;
		agregarCampo(formPanel, gbc, row++, "ID de producto:", txtId);
		agregarCampo(formPanel, gbc, row++, "Nombre:", txtNombre);
		agregarCampo(formPanel, gbc, row++, "Categoría:", comboCategoria);
		agregarCampo(formPanel, gbc, row++, "Descripción:", txtDescripcion);
		agregarCampo(formPanel, gbc, row++, "Precio:", txtPrecio);
		agregarCampo(formPanel, gbc, row++, "IVA:", txtIVA);

		box.add(formPanel, BorderLayout.CENTER);

		// Panel inferior con botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.WHITE);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(33, 150, 243));
		btnGuardar.setForeground(Color.WHITE);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(240, 230, 140));
		btnRegresar.setForeground(Color.DARK_GRAY);
		btnRegresar.setFocusPainted(false);
		btnRegresar.setBorderPainted(false);

		buttonPanel.add(btnRegresar);
		buttonPanel.add(btnGuardar);

		box.add(buttonPanel, BorderLayout.SOUTH);

		fondo.add(box);

		// Eventos
		btnGuardar.addActionListener(e -> guardarProducto());
		btnRegresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Regresando..."));
	}

	private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent campo) {
		gbc.gridx = 0;
		gbc.gridy = fila;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(new JLabel(texto), gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(campo, gbc);
	}

	private void guardarProducto() {
		String id = txtId.getText().trim();
		String nombre = txtNombre.getText().trim();
		String categoria = (String) comboCategoria.getSelectedItem();
		String descripcion = txtDescripcion.getText().trim();
		String precio = txtPrecio.getText().trim();
		String iva = txtIVA.getText().trim();

		if (id.isEmpty() || nombre.isEmpty() || categoria.equals("Seleccione") || descripcion.isEmpty()
				|| precio.isEmpty() || iva.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!Pattern.matches("\\d+(\\.\\d{1,2})?", precio)) {
			JOptionPane.showMessageDialog(this, "El precio debe ser un número válido (puede tener decimales).",
					"Error en precio", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!Pattern.matches("\\d+(\\.\\d{1,2})?", iva)) {
			JOptionPane.showMessageDialog(this, "El IVA debe ser un número válido (puede tener decimales).",
					"Error en IVA", JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void agregarFiltroNumerico(JTextField campo) {
		campo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = campo.getText();

				if (!Character.isDigit(c) && c != '.') {
					e.consume();
				}
				if (c == '.' && text.contains(".")) {
					e.consume();
				}
			}
		});
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}
}
