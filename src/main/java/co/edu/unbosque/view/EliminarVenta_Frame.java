package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class EliminarVenta_Frame extends JFrame {
	private JTextField txtIdVenta, txtFechaVenta, txtCliente, txtTotal, txtMotivo;
	private JComboBox<String> comboEstado;
	private JButton btnEliminar, btnRegresar;

	public EliminarVenta_Frame() {
		setTitle("Eliminar Venta");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Logo en la esquina superior izquierda
		JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelLogo.setBackground(new Color(144, 238, 144));

		ImageIcon icono = new ImageIcon("src/logo.png");
		Image imagen = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(imagen));
		panelLogo.add(lblLogo);

		add(panelLogo, BorderLayout.NORTH);

		// Fondo
		JPanel fondo = new JPanel(new GridBagLayout());
		fondo.setBackground(new Color(144, 238, 144));
		add(fondo, BorderLayout.CENTER);

		// Recuadro central
		JPanel recuadro = new JPanel(new GridBagLayout());
		recuadro.setBackground(Color.WHITE);
		recuadro.setPreferredSize(new Dimension(500, 470));
		recuadro.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
			BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Título
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		JLabel lblTitulo = new JLabel("Eliminar Venta");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		recuadro.add(lblTitulo, gbc);

		// Campos
		txtIdVenta = new JTextField(20);
		txtFechaVenta = new JTextField(20);
		txtCliente = new JTextField(20);
		txtTotal = new JTextField(10);
		txtMotivo = new JTextField(20);

		agregarFiltroNumerico(txtIdVenta, false);
		agregarFiltroNumerico(txtTotal, true);

		int fila = 1;
		fila = agregarCampo(recuadro, "ID de venta:", txtIdVenta, fila, gbc);
		fila = agregarCampo(recuadro, "Fecha de venta:", txtFechaVenta, fila, gbc);
		fila = agregarCampo(recuadro, "Cliente:", txtCliente, fila, gbc);
		fila = agregarCampo(recuadro, "Total:", txtTotal, fila, gbc);

		// Estado
		gbc.gridy = fila++;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		recuadro.add(new JLabel("Estado:"), gbc);
		String[] estados = { "Completado", "Pendiente", "Pagando" };
		comboEstado = new JComboBox<>(estados);
		comboEstado.setSelectedIndex(0);
		gbc.gridx = 1;
		recuadro.add(comboEstado, gbc);

		// Motivo
		gbc.gridy = fila++;
		gbc.gridx = 0;
		recuadro.add(new JLabel("Motivo de eliminación:"), gbc);
		gbc.gridx = 1;
		recuadro.add(txtMotivo, gbc);

		// Botones
		gbc.gridy = fila++;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.EAST;

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		botonesPanel.setBackground(Color.WHITE);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(244, 67, 54));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFocusPainted(false);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(240, 230, 140));
		btnRegresar.setFocusPainted(false);

		botonesPanel.add(btnRegresar);
		botonesPanel.add(btnEliminar);
		recuadro.add(botonesPanel, gbc);

		fondo.add(recuadro);

		// Eventos
		btnEliminar.addActionListener(e -> eliminarVenta());
		btnRegresar.addActionListener(e -> dispose());
	}

	private int agregarCampo(JPanel panel, String label, JTextField campo, int fila, GridBagConstraints gbc) {
		gbc.gridy = fila;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(new JLabel(label), gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(campo, gbc);

		return fila + 1;
	}

	private void eliminarVenta() {
		String idVenta = txtIdVenta.getText().trim();
		String fechaVenta = txtFechaVenta.getText().trim();
		String cliente = txtCliente.getText().trim();
		String total = txtTotal.getText().trim();
		String motivo = txtMotivo.getText().trim();
		String estado = (String) comboEstado.getSelectedItem();

		if (idVenta.isEmpty() || fechaVenta.isEmpty() || cliente.isEmpty() || total.isEmpty() || motivo.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
				"Por favor, complete todos los campos.", 
				"Campos vacíos",
				JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			Double.parseDouble(total);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, 
				"El total debe ser un número válido.", 
				"Error en total",
				JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Crear objeto con los datos de la venta a eliminar
		Object[] datosVenta = new Object[] {
			Integer.parseInt(idVenta),
			fechaVenta,
			cliente,
			Double.parseDouble(total),
			estado,
			motivo
		};

		// Notificar al controlador
		firePropertyChange("eliminarVenta", null, datosVenta);
	}

	private void agregarFiltroNumerico(JTextField campo, boolean permitirDecimal) {
		campo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = campo.getText();
				if (!Character.isDigit(c) && (!permitirDecimal || c != '.')) {
					e.consume();
				}
				if (c == '.' && text.contains(".")) {
					e.consume();
				}
			}
		});
	}

	// Getters para los botones
	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	// Getters para los campos
	public JTextField getTxtIdVenta() {
		return txtIdVenta;
	}

	public JTextField getTxtFechaVenta() {
		return txtFechaVenta;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public JTextField getTxtMotivo() {
		return txtMotivo;
	}

	public JComboBox<String> getComboEstado() {
		return comboEstado;
	}
}
