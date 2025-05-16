package prueba_usuarios;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class HistorialVentasUI extends JFrame {

	private JButton btnProductos;
	private JButton btnRegresar;

	public HistorialVentasUI() {

		setTitle("Historial de Ventas");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar ventana
		setUndecorated(true); // Opcional: quitar bordes
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

		JTextField txtBuscar = new JTextField(15);
		txtBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));
		filterPanel.add(txtBuscar);

		JFormattedTextField txtFecha = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		txtFecha.setColumns(10);
		txtFecha.setBorder(BorderFactory.createTitledBorder("Fecha:"));
		filterPanel.add(txtFecha);

		JComboBox<String> cbMetodoPago = new JComboBox<>(new String[] { "", "Efectivo", "Tarjeta" });
		cbMetodoPago.setBorder(BorderFactory.createTitledBorder("Método de pago:"));
		filterPanel.add(cbMetodoPago);

		JComboBox<String> cbEstado = new JComboBox<>(new String[] { "", "Pagado", "Pendiente", "Anulado" });
		cbEstado.setBorder(BorderFactory.createTitledBorder("Estado:"));
		filterPanel.add(cbEstado);

		// Botón para mostrar productos (desplegable)
		btnProductos = new JButton("Productos");
		btnProductos.setFocusPainted(false);
		btnProductos.setFont(new Font("Arial", Font.PLAIN, 13));
		filterPanel.add(btnProductos);

		// Menú desplegable
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("Croquetas para perro");
		JMenuItem item2 = new JMenuItem("Arena para gato");
		JMenuItem item3 = new JMenuItem("Vacuna antirrábica");
		JMenuItem item4 = new JMenuItem("Shampoo para mascotas");

		popupMenu.add(item1);
		popupMenu.add(item2);
		popupMenu.add(item3);
		popupMenu.add(item4);

		// Acción del botón para mostrar el menú
		btnProductos.addActionListener(e -> {
			popupMenu.show(btnProductos, 0, btnProductos.getHeight());
		});

		// Tabla de ventas
		String[] columnas = { "Nº de Venta", "Fecha", "Cliente", "Método de pago", "Estado", "" };
		Object[][] datos = { { "1001", "20/03/2024 09:45", "Juan Pérez", "Efectivo", "Pagado", "Ver" },
				{ "1000", "18/03/2024 14:12", "María López", "Tarjeta", "Pagado", "Ver" },
				{ "999", "15/03/2024 10:30", "-", "Efectivo", "Pendiente", "Ver" },
				{ "998", "12/03/2024 10:20", "Carlos Gómez", "Efectivo", "Anulado", "Ver" }, };

		JTable tabla = new JTable(new DefaultTableModel(datos, columnas)) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tabla.setRowHeight(30);
		tabla.setFont(new Font("Arial", Font.PLAIN, 14));
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.setFillsViewportHeight(true);

		// Colorear columna "Estado"
		tabla.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setValue(Object value) {
				setForeground(Color.BLACK);
				if ("Pagado".equals(value)) {
					setForeground(new Color(0, 153, 0)); // verde
				} else if ("Pendiente".equals(value)) {
					setForeground(new Color(0, 0, 128)); // azul oscuro
				} else if ("Anulado".equals(value)) {
					setForeground(Color.RED);
				}
				setText(value != null ? value.toString() : "");
			}
		});

		// Colorear "Ver"
		tabla.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setValue(Object value) {
				setForeground(new Color(0, 102, 204)); // azul claro
				setText("Ver");
			}
		});

		JScrollPane scroll = new JScrollPane(tabla);
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

	// Getters y Setters
	public JButton getBtnProductos() {
		return btnProductos;
	}

	public void setBtnProductos(JButton btnProductos) {
		this.btnProductos = btnProductos;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

}
