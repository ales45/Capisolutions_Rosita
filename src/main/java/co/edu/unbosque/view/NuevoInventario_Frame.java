package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NuevoInventario_Frame extends JFrame {

	private JComboBox<String> comboProducto;
	private JTextField txtStock, txtStockMinimo, txtUbicacion;
	private JLabel lblFecha;
	private JButton btnGuardar, btnRegresar;

	public NuevoInventario_Frame() {
		setTitle("Registrar Inventario");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(252, 243, 174));
		fondo.setLayout(new GridBagLayout());
		add(fondo);

		JPanel box = new JPanel(new BorderLayout());
		box.setBackground(Color.WHITE);
		box.setPreferredSize(new Dimension(450, 400));
		box.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)
		));

		// Panel superior
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);

		ImageIcon originalIcon = new ImageIcon("src/logo.png");
		Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
		topPanel.add(logoLabel, BorderLayout.WEST);

		JLabel lblTitulo = new JLabel("Registrar Inventario", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		topPanel.add(lblTitulo, BorderLayout.CENTER);

		box.add(topPanel, BorderLayout.NORTH);

		// Panel de formulario
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		comboProducto = new JComboBox<>(new String[] { "Seleccione", "Producto 1", "Producto 2" }); // ← Llenar dinámicamente
		txtStock = new JTextField(20);
		txtStockMinimo = new JTextField(20);
		txtUbicacion = new JTextField(20);
		lblFecha = new JLabel(obtenerFechaActual());

		txtStock.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}
		});

		txtStockMinimo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}
		});

		int row = 0;
		agregarCampo(formPanel, gbc, row++, "Producto:", comboProducto);
		agregarCampo(formPanel, gbc, row++, "Stock Inicial:", txtStock);
		agregarCampo(formPanel, gbc, row++, "Stock Mínimo:", txtStockMinimo);
		agregarCampo(formPanel, gbc, row++, "Ubicación:", txtUbicacion);
		agregarCampo(formPanel, gbc, row++, "Fecha Registro:", lblFecha);

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

		buttonPanel.add(btnRegresar);
		buttonPanel.add(btnGuardar);

		box.add(buttonPanel, BorderLayout.SOUTH);
		fondo.add(box);

		// Eventos
		btnGuardar.addActionListener(e -> guardarInventario());
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

	private String obtenerFechaActual() {
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ahora.format(formatter);
	}

	private void guardarInventario() {
		String producto = (String) comboProducto.getSelectedItem();
		String stock = txtStock.getText().trim();
		String stockMinimo = txtStockMinimo.getText().trim();
		String ubicacion = txtUbicacion.getText().trim();

		if (producto.equals("Seleccione") || stock.isEmpty() || stockMinimo.isEmpty() || ubicacion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, "Inventario registrado exitosamente.", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);
	}
public static void main(String[] args) {
	NuevoInventario_Frame n=new NuevoInventario_Frame();
	n.setVisible(true);
}
	// Getters para el controlador (si lo necesitas)
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JComboBox<String> getComboProducto() {
		return comboProducto;
	}

	public JTextField getTxtStock() {
		return txtStock;
	}

	public JTextField getTxtStockMinimo() {
		return txtStockMinimo;
	}

	public JTextField getTxtUbicacion() {
		return txtUbicacion;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}
}
