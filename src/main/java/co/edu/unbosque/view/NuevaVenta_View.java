package co.edu.unbosque.view;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class NuevaVenta_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnRegresar, btnConfirmar, btnAgregarProducto, btnCalcularCambio;
	private JTable tablaProductos;
	private JTextField txtCliente, txtCambio, txtIva, txtTotal, txtPaga;
	private JComboBox<String> metodoPago;
	private JDateChooser fechaVenta;

	public NuevaVenta_View() {
		setTitle("Papelería de Rosita - Nueva Venta");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JPanel fondo = new JPanel(new BorderLayout());
		fondo.setBackground(Color.decode("#a8d5ba")); // Verde claro
		getContentPane().add(fondo);

		// Panel superior
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		ImageIcon logoIcon = new ImageIcon("src/logo.png");
		JLabel logo = new JLabel(logoIcon);
		panelSuperior.add(logo, BorderLayout.WEST);

		btnRegresar = new JButton("Regresar");
		estiloBoton(btnRegresar);
		btnRegresar.setPreferredSize(new Dimension(160, 40));
		panelSuperior.add(btnRegresar, BorderLayout.EAST);

		fondo.add(panelSuperior, BorderLayout.NORTH);

		// Panel central
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		JPanel caja = new JPanel();
		caja.setLayout(new BoxLayout(caja, BoxLayout.Y_AXIS));
		caja.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),
				BorderFactory.createEmptyBorder(20, 30, 20, 30)));
		caja.setBackground(Color.WHITE);
		caja.setMaximumSize(new Dimension(900, 750));
		caja.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Título
		JLabel lblTitulo = new JLabel("Registrar Nueva Venta");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
		caja.add(lblTitulo);

		// Cliente + Fecha
		JPanel panelDatos = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		panelDatos.setOpaque(false);

		JLabel lblCliente = new JLabel("Cliente:");
		txtCliente = new JTextField(20);
		estiloCampoTexto(txtCliente);

		JLabel lblFecha = new JLabel("Fecha:");
		fechaVenta = new JDateChooser();
		fechaVenta.setPreferredSize(new Dimension(150, 30));
		fechaVenta.setDate(new Date());

		panelDatos.add(lblCliente);
		panelDatos.add(txtCliente);
		panelDatos.add(lblFecha);
		panelDatos.add(fechaVenta);
		caja.add(panelDatos);

		// Botón agregar producto
		btnAgregarProducto = new JButton("Agregar Producto");
		estiloBoton(btnAgregarProducto);
		btnAgregarProducto.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAgregarProducto.setMaximumSize(new Dimension(200, 40));
		caja.add(btnAgregarProducto);
		caja.add(Box.createVerticalStrut(10));

		// Tabla productos
		String[] columnas = { "ID", "Producto", "Cantidad", "Precio Unitario", "Subtotal" };
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][] {}, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 2; // Solo la columna "Cantidad" es editable
			}
		};

		tablaProductos = new JTable(modeloTabla);
		tablaProductos.getModel().addTableModelListener(e -> {
			if (e.getColumn() == 2) {
				int fila = e.getFirstRow();
				DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
				try {
					int cantidad = Integer.parseInt(modelo.getValueAt(fila, 2).toString());
					double precio = Double.parseDouble(modelo.getValueAt(fila, 3).toString());
					double subtotal = cantidad * precio;
					modelo.setValueAt(subtotal, fila, 4);
					actualizarTotales();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Cantidad inválida.");
				}
			}
		});

		JScrollPane scrollTabla = new JScrollPane(tablaProductos);
		scrollTabla.setPreferredSize(new Dimension(800, 200));
		caja.add(scrollTabla);
		caja.add(Box.createVerticalStrut(15));

		// Totales
		JPanel panelTotales = new JPanel(new GridLayout(5, 2, 15, 10));
		panelTotales.setOpaque(false);
		panelTotales.setMaximumSize(new Dimension(800, 150));

		JLabel lblMetodo = new JLabel("Método de Pago:");
		metodoPago = new JComboBox<>(new String[] { "Efectivo", "Tarjeta", "Transferencia" });
		estiloComboBox(metodoPago);

		JLabel lblIva = new JLabel("IVA (21%):");
		txtIva = new JTextField(10);
		txtIva.setEditable(false);
		estiloCampoTexto(txtIva);

		JLabel lblTotal = new JLabel("Total con IVA:");
		txtTotal = new JTextField(10);
		txtTotal.setEditable(false);
		estiloCampoTexto(txtTotal);

		JLabel lblPaga = new JLabel("Paga con:");
		JPanel panelPaga = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		panelPaga.setOpaque(false);
		txtPaga = new JTextField(10);
		estiloCampoTexto(txtPaga);
		btnCalcularCambio = new JButton("Calcular Cambio");
		estiloBoton(btnCalcularCambio);
		btnCalcularCambio.setPreferredSize(new Dimension(150, 30));
		btnCalcularCambio.addActionListener(e -> calcularCambio());
		panelPaga.add(txtPaga);
		panelPaga.add(btnCalcularCambio);

		JLabel lblCambio = new JLabel("Cambio:");
		txtCambio = new JTextField(10);
		txtCambio.setEditable(false);
		estiloCampoTexto(txtCambio);

		panelTotales.add(lblMetodo);
		panelTotales.add(metodoPago);
		panelTotales.add(lblIva);
		panelTotales.add(txtIva);
		panelTotales.add(lblTotal);
		panelTotales.add(txtTotal);
		panelTotales.add(lblPaga);
		panelTotales.add(panelPaga);
		panelTotales.add(lblCambio);
		panelTotales.add(txtCambio);

		caja.add(Box.createVerticalStrut(15));
		caja.add(panelTotales);

		// Botón confirmar
		btnConfirmar = new JButton("Confirmar Venta");
		estiloBoton(btnConfirmar);
		btnConfirmar.setPreferredSize(new Dimension(200, 50));
		btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
		caja.add(Box.createVerticalStrut(15));
		caja.add(btnConfirmar);

		panelCentral.add(caja);
		fondo.add(panelCentral, BorderLayout.CENTER);
	}

	private void estiloBoton(JButton boton) {
		boton.setBackground(Color.decode("#80c9a9"));
		boton.setForeground(Color.BLACK);
		boton.setFocusPainted(false);
		boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setBorder(BorderFactory.createLineBorder(new Color(90, 140, 120), 2, true));
	}

	private void estiloCampoTexto(JTextField campo) {
		campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		campo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		campo.setPreferredSize(new Dimension(150, 30));
		campo.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	private void estiloComboBox(JComboBox<?> combo) {
		combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		combo.setBackground(Color.WHITE);
		combo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		combo.setPreferredSize(new Dimension(150, 30));
	}

	private void actualizarTotales() {
		DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
		double total = 0;
		for (int i = 0; i < modelo.getRowCount(); i++) {
			total += Double.parseDouble(modelo.getValueAt(i, 4).toString());
		}
		double iva = total * 0.21;
		double totalConIva = total + iva;

		txtIva.setText(String.format("$ %.2f", iva));
		txtTotal.setText(String.format("$ %.2f", totalConIva));
	}

	private void calcularCambio() {
		try {
			String totalStr = txtTotal.getText().replace("$", "").trim();
			String pagaStr = txtPaga.getText().trim();
			String metodoPagoStr = metodoPago.getSelectedItem().toString();

			// Si el método de pago es tarjeta, no calcular cambio
			if (metodoPagoStr.equalsIgnoreCase("Tarjeta")) {
				txtCambio.setText("$ 0.00");
				return;
			}

			// Validar que los campos contengan números válidos
			if (totalStr.isEmpty() || pagaStr.isEmpty()) {
				JOptionPane.showMessageDialog(this,
					"Por favor ingrese el total y el monto a pagar",
					"Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
				return;
			}

			double total = Double.parseDouble(totalStr);
			double paga = Double.parseDouble(pagaStr);

			if (paga < total) {
				JOptionPane.showMessageDialog(this,
					"El monto a pagar debe ser mayor o igual al total",
					"Monto insuficiente",
					JOptionPane.WARNING_MESSAGE);
				return;
			}

			double cambio = paga - total;
			txtCambio.setText(String.format("$ %.2f", cambio));

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,
				"Por favor ingrese valores numéricos válidos",
				"Error de formato",
				JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
				"Error al calcular el cambio: " + ex.getMessage(),
				"Error",
				JOptionPane.ERROR_MESSAGE);
		}
	}

	public void mostrarVentanaProductos(Object[][] datos) {
		JDialog dialogo = new JDialog(this, "Seleccionar Producto", true);
		dialogo.setSize(800, 600);
		dialogo.setLocationRelativeTo(this);

		JPanel panel = new JPanel(new BorderLayout());
		String[] columnas = { "ID", "Nombre", "Precio", "Stock", "Cantidad", "Precio Unit.", "Subtotal" };
		
		DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 4; // Solo la columna de cantidad es editable
			}
		};

		JTable tabla = new JTable(modeloTabla);
		
		// Agregar listener para calcular subtotal cuando cambia la cantidad
		tabla.getModel().addTableModelListener(e -> {
			if (e.getColumn() == 4) { // Columna de cantidad
				int row = e.getFirstRow();
				try {
					int cantidad = Integer.parseInt(modeloTabla.getValueAt(row, 4).toString());
					double precioUnitario = Double.parseDouble(modeloTabla.getValueAt(row, 5).toString());
					double subtotal = cantidad * precioUnitario;
					modeloTabla.setValueAt(subtotal, row, 6);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(dialogo, "Por favor ingrese una cantidad válida", 
						"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					seleccionarProductoDesdeTabla(tabla, dialogo);
				}
			}
		});

		JScrollPane scroll = new JScrollPane(tabla);
		panel.add(scroll, BorderLayout.CENTER);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(e -> seleccionarProductoDesdeTabla(tabla, dialogo));

		JPanel panelBtn = new JPanel();
		panelBtn.add(btnSeleccionar);
		panel.add(panelBtn, BorderLayout.SOUTH);

		dialogo.add(panel);
		dialogo.setVisible(true);
	}

	public void seleccionarProductoDesdeTabla(JTable tabla, JDialog dialogo) {
		int fila = tabla.getSelectedRow();
		if (fila != -1) {
			int idProducto = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
			String nombre = tabla.getValueAt(fila, 1).toString();
			double precio = Double.parseDouble(tabla.getValueAt(fila, 2).toString());
			int cantidad = Integer.parseInt(tabla.getValueAt(fila, 4).toString());
			double subtotal = Double.parseDouble(tabla.getValueAt(fila, 6).toString());

			DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
			modelo.addRow(new Object[] { idProducto, nombre, cantidad, precio, subtotal });

			actualizarTotales();
			dialogo.dispose();
		}
	}

	public void guardarVenta() {
		// Validar campos obligatorios
		if (txtCliente.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del cliente", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (tablaProductos.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "Por favor agregue al menos un producto", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (metodoPago.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Por favor seleccione un método de pago", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Obtener datos de la venta
		String nombreCliente = txtCliente.getText().trim();
		Date fecha = fechaVenta.getDate();
		String metodoPagoStr = metodoPago.getSelectedItem().toString();
		double total = Double.parseDouble(txtTotal.getText().replace("$", "").trim());
		double iva = Double.parseDouble(txtIva.getText().replace("$", "").trim());

		// Obtener detalles de los productos
		DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
		Object[][] detallesProductos = new Object[modelo.getRowCount()][5];
		
		for (int i = 0; i < modelo.getRowCount(); i++) {
			detallesProductos[i][0] = modelo.getValueAt(i, 0); // ID Producto
			detallesProductos[i][1] = modelo.getValueAt(i, 2); // Cantidad
			detallesProductos[i][2] = modelo.getValueAt(i, 3); // Precio Unitario
			detallesProductos[i][3] = modelo.getValueAt(i, 4); // Subtotal
			detallesProductos[i][4] = metodoPagoStr; // Método de pago
		}

		// Crear objeto con todos los datos de la venta
		Object[] datosVenta = new Object[] {
			nombreCliente,
			fecha,
			metodoPagoStr,
			total,
			iva,
			detallesProductos
		};

		// Notificar al controlador
		firePropertyChange("guardarVenta", null, datosVenta);
	}

	// Getters
	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JButton getBtnConfirmar() {
		return btnConfirmar;
	}

	public JButton getBtnCalcularCambio() {
		return btnCalcularCambio;
	}

	public JTable getTablaProductos() {
		return tablaProductos;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JTextField getTxtCambio() {
		return txtCambio;
	}

	public JTextField getTxtIva() {
		return txtIva;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public JTextField getTxtPaga() {
		return txtPaga;
	}

	public JComboBox<String> getMetodoPago() {
		return metodoPago;
	}

	public JDateChooser getFechaVenta() {
		return fechaVenta;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

	public void setBtnConfirmar(JButton btnConfirmar) {
		this.btnConfirmar = btnConfirmar;
	}

	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}

	public void setBtnAgregarProducto(JButton btnAgregarProducto) {
		this.btnAgregarProducto = btnAgregarProducto;
	}

	public void setBtnCalcularCambio(JButton btnCalcularCambio) {
		this.btnCalcularCambio = btnCalcularCambio;
	}

	public void setTablaProductos(JTable tablaProductos) {
		this.tablaProductos = tablaProductos;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public void setTxtCambio(JTextField txtCambio) {
		this.txtCambio = txtCambio;
	}

	public void setTxtIva(JTextField txtIva) {
		this.txtIva = txtIva;
	}

	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	public void setTxtPaga(JTextField txtPaga) {
		this.txtPaga = txtPaga;
	}

	public void setMetodoPago(JComboBox<String> metodoPago) {
		this.metodoPago = metodoPago;
	}

	public void setFechaVenta(JDateChooser fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	
}
