package prueba_usuarios;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class Devoluciones_Frame extends JFrame {
	private JTextField txtIdVenta, txtCliente, txtTotal;
	private JComboBox<String> comboEstado;
	private JDateChooser dateChooser;
	private JButton btnGuardar;
	private JButton btnRegresar;

	public Devoluciones_Frame() {
		setTitle("Devolución de productos");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(199, 233, 209));
		fondo.setLayout(new GridBagLayout());
		add(fondo);

		JPanel box = new JPanel(new BorderLayout());
		box.setBackground(Color.WHITE);
		box.setPreferredSize(new Dimension(450, 430));
		box.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)));

		// Título y logo
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);

		ImageIcon originalIcon = new ImageIcon("src/logo.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
		topPanel.add(logoLabel, BorderLayout.WEST);

		JLabel titulo = new JLabel("Devolución de productos", SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		topPanel.add(titulo, BorderLayout.CENTER);

		box.add(topPanel, BorderLayout.NORTH);

		// Formulario
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 8, 10, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		txtIdVenta = new JTextField();
		dateChooser = new JDateChooser();
		txtCliente = new JTextField();
		txtTotal = new JTextField();
		comboEstado = new JComboBox<>(new String[] { "Completada", "Pendiente", "Cancelada" });

		int row = 0;
		addField(formPanel, gbc, row++, "ID de venta:", txtIdVenta);
		addField(formPanel, gbc, row++, "Fecha de venta:", dateChooser);
		addField(formPanel, gbc, row++, "Cliente:", txtCliente);
		addField(formPanel, gbc, row++, "Total:", txtTotal);
		addField(formPanel, gbc, row++, "Estado:", comboEstado);

		box.add(formPanel, BorderLayout.CENTER);

		// Botones: Guardar y Regresar
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBackground(Color.WHITE);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(220, 220, 220));
		btnRegresar.addActionListener(e -> dispose()); // Puedes reemplazar esto con otra acción
		buttonPanel.add(btnRegresar, BorderLayout.WEST);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(0, 153, 102));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(e -> guardarCambios());
		buttonPanel.add(btnGuardar, BorderLayout.EAST);

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
		String idVenta = txtIdVenta.getText().trim();
		String cliente = txtCliente.getText().trim();
		String total = txtTotal.getText().trim();
		String estado = (String) comboEstado.getSelectedItem();

		// Validaciones
		if (idVenta.isEmpty() || cliente.isEmpty() || total.isEmpty() || dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!idVenta.matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "El ID de venta debe contener solo números.", "Error de formato",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!total.matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "El total debe ser un valor numérico.", "Error de formato",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaVenta = sdf.format(dateChooser.getDate());

		String mensaje = String.format("Devolución registrada:\nID: %s\nFecha: %s\nCliente: %s\nTotal: %s\nEstado: %s",
				idVenta, fechaVenta, cliente, total, estado);
		JOptionPane.showMessageDialog(this, mensaje, "Devolución Guardada", JOptionPane.INFORMATION_MESSAGE);
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

	
}
