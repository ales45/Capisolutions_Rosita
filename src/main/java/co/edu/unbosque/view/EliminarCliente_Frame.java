package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class EliminarCliente_Frame extends JFrame {
	private JTextField txtNombre, txtCedula, txtTelefono, txtDireccion;
	private JComboBox<String> comboTipoCliente;
	private JButton btnEliminar, btnRegresar; // ← Botones ahora son atributos de clase

	public EliminarCliente_Frame() {
		setTitle("Eliminar Cliente");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(230, 242, 255));
		fondo.setLayout(new GridBagLayout());
		add(fondo);

		JPanel box = new JPanel(new BorderLayout());
		box.setBackground(Color.WHITE);
		box.setPreferredSize(new Dimension(450, 430));
		box.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)));

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);

		ImageIcon originalIcon = new ImageIcon("src/logo.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
		topPanel.add(logoLabel, BorderLayout.WEST);

		JLabel titulo = new JLabel("Eliminar Cliente", SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		topPanel.add(titulo, BorderLayout.CENTER);

		box.add(topPanel, BorderLayout.NORTH);

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

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.WHITE);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(204, 0, 0));
		btnEliminar.setForeground(Color.WHITE);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(230, 230, 230));

		btnEliminar.addActionListener(e -> eliminarCliente());
		btnRegresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Regresando..."));

		buttonPanel.add(btnRegresar);
		buttonPanel.add(btnEliminar);

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

	private void eliminarCliente() {
		if (txtNombre.getText().trim().isEmpty() || txtCedula.getText().trim().isEmpty()
				|| txtTelefono.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos para eliminar el cliente.",
					"Campos vacíos", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar al cliente?",
				"Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.", "Eliminado",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

	
}
