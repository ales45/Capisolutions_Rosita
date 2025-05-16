package prueba_usuarios;

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
		btnAgregarProducto.addActionListener(e -> mostrarVentanaProductos());
		caja.add(btnAgregarProducto);
		caja.add(Box.createVerticalStrut(10));

		// Tabla productos
		String[] columnas = { "Producto", "Cantidad", "Precio Unitario", "Subtotal" };
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][] {}, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1; // Solo la columna "Cantidad" es editable
			}
		};

		tablaProductos = new JTable(modeloTabla);
		tablaProductos.getModel().addTableModelListener(e -> {
			if (e.getColumn() == 1) {
				int fila = e.getFirstRow();
				DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
				try {
					int cantidad = Integer.parseInt(modelo.getValueAt(fila, 1).toString());
					double precio = Double.parseDouble(modelo.getValueAt(fila, 2).toString());
					double subtotal = cantidad * precio;
					modelo.setValueAt(subtotal, fila, 3);
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
			total += Double.parseDouble(modelo.getValueAt(i, 3).toString());
		}
		double iva = total * 0.21;
		double totalConIva = total + iva;

		txtIva.setText(String.format("$ %.2f", iva));
		txtTotal.setText(String.format("$ %.2f", totalConIva));
	}

	private void calcularCambio() {
	    txtCambio.setText("");

	    try {
	        // Reemplaza el símbolo $ y cambia la coma decimal a punto
	        String totalTexto = txtTotal.getText().replace("$", "").replace(",", ".").trim();
	        System.out.println("Total leído: [" + totalTexto + "]");

	        double totalConIva = Double.parseDouble(totalTexto);

	        String pagaTexto = txtPaga.getText().trim();
	        System.out.println("Paga leído: [" + pagaTexto + "]");

	        double paga = Double.parseDouble(pagaTexto);

	        double cambio = paga - totalConIva;

	        if (cambio < 0) {
	            txtCambio.setText("Insuficiente");
	        } else {
	            txtCambio.setText(String.format("$ %.2f", cambio));
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        txtCambio.setText("Error");
	    }
	}




	private void mostrarVentanaProductos() {
		JDialog dialogo = new JDialog(this, "Seleccionar Producto", true);
		dialogo.setSize(500, 400);
		dialogo.setLocationRelativeTo(this);

		JPanel panel = new JPanel(new BorderLayout());
		String[] columnas = { "Nombre", "Precio", "Stock" };
		Object[][] datos = { { "Lápiz", 1000.0, 50 }, { "Cuaderno", 3500.0, 30 }, { "Borrador", 500.0, 100 } };

		JTable tabla = new JTable(new DefaultTableModel(datos, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
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

	private void seleccionarProductoDesdeTabla(JTable tabla, JDialog dialogo) {
		int fila = tabla.getSelectedRow();
		if (fila != -1) {
			String nombre = tabla.getValueAt(fila, 0).toString();
			double precio = Double.parseDouble(tabla.getValueAt(fila, 1).toString());
			int cantidad = 1;
			double subtotal = precio * cantidad;

			DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
			modelo.addRow(new Object[] { nombre, cantidad, precio, subtotal });

			actualizarTotales();
			dialogo.dispose();
		}
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

}
