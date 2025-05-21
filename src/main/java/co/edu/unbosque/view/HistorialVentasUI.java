package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class HistorialVentasUI extends JFrame {

	private JButton btnRegresar;
	private JTable tablaVentas;
	private JTextField txtBuscar;
	private JComboBox<String> cbMetodoPago;
	private JComboBox<String> cbEstado;
	private JFormattedTextField txtFecha;

	public HistorialVentasUI() {
		setTitle("Historial de Ventas");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Panel principal con fondo verde claro
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(200, 230, 210));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(mainPanel);

		// Panel superior con filtros
		JPanel filterPanel = new JPanel();
		filterPanel.setBackground(new Color(200, 230, 210));
		filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));

		// Crear un panel contenedor para los filtros y el logo
		JPanel filterContainer = new JPanel(new BorderLayout());
		filterContainer.setBackground(new Color(200, 230, 210));
		filterContainer.add(filterPanel, BorderLayout.CENTER);

		// Crear el logo y ubicarlo a la derecha
		ImageIcon logoIcon = new ImageIcon("src/logo.png");
		JLabel lblLogo = new JLabel(logoIcon);
		filterContainer.add(lblLogo, BorderLayout.EAST);

		// Agregar el contenedor al mainPanel
		mainPanel.add(filterContainer, BorderLayout.NORTH);

		// Componentes de filtro
		txtBuscar = new JTextField(15);
		txtBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));
		filterPanel.add(txtBuscar);

		txtFecha = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		txtFecha.setColumns(10);
		txtFecha.setBorder(BorderFactory.createTitledBorder("Fecha:"));
		filterPanel.add(txtFecha);

		cbMetodoPago = new JComboBox<>(new String[] { "", "Efectivo", "Tarjeta" });
		cbMetodoPago.setBorder(BorderFactory.createTitledBorder("Método de pago:"));
		filterPanel.add(cbMetodoPago);

		cbEstado = new JComboBox<>(new String[] { "", "Pagado", "Pendiente", "Anulado" });
		cbEstado.setBorder(BorderFactory.createTitledBorder("Estado:"));
		filterPanel.add(cbEstado);

		// Tabla de ventas
		String[] columnas = { "Nº de Venta", "Fecha", "Cliente", "Método de pago", "Estado", "Total", "Ver" };
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaVentas = new JTable(modelo);
		tablaVentas.setRowHeight(30);
		tablaVentas.setFont(new Font("Arial", Font.PLAIN, 14));
		tablaVentas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tablaVentas.setFillsViewportHeight(true);

		// Colorear columna "Estado"
		tablaVentas.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setValue(Object value) {
				setForeground(Color.BLACK);
				if ("Pagado".equals(value)) {
					setForeground(new Color(0, 153, 0));
				} else if ("Pendiente".equals(value)) {
					setForeground(new Color(0, 0, 128));
				} else if ("Anulado".equals(value)) {
					setForeground(Color.RED);
				}
				setText(value != null ? value.toString() : "");
			}
		});

		// Colorear "Ver"
		tablaVentas.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setValue(Object value) {
				setForeground(new Color(0, 102, 204));
				setText("Ver");
			}
		});

		JScrollPane scroll = new JScrollPane(tablaVentas);
		mainPanel.add(scroll, BorderLayout.CENTER);

		// Botón de regresar
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(180, 220, 190));
		btnRegresar.setFocusPainted(false);
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		JPanel regresarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		regresarPanel.setBackground(new Color(200, 230, 210));
		regresarPanel.add(btnRegresar);

		mainPanel.add(regresarPanel, BorderLayout.SOUTH);
	}

	// Getters
	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JTable getTablaVentas() {
		return tablaVentas;
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}

	public JComboBox<String> getCbMetodoPago() {
		return cbMetodoPago;
	}

	public JComboBox<String> getCbEstado() {
		return cbEstado;
	}

	public JFormattedTextField getTxtFecha() {
		return txtFecha;
	}

	// Método para actualizar la tabla
	public void actualizarTabla(Object[][] datos) {
		DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
		modelo.setRowCount(0);
		for (Object[] fila : datos) {
			modelo.addRow(fila);
		}
	}
}
