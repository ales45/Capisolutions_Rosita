package prueba_usuarios;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class VerCliente_Frame extends JFrame {

	private JTable tbTablaClientes;
	private JScrollPane spScrollClientes;
	private JLabel lbTitulo;
	private JButton btnRegresar;

	public VerCliente_Frame() {
		setTitle("Clientes");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Fondo general azul claro
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Color.decode("#e8f0fe"));
		add(panelPrincipal, BorderLayout.CENTER);

		// Panel superior con logo y botón regresar
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(new EmptyBorder(20, 40, 20, 40));

		// Logo
		ImageIcon icon = new ImageIcon("src/logo.png");
		Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(img));
		panelSuperior.add(lblLogo, BorderLayout.WEST);

		// Botón regresar
		btnRegresar = new JButton("Regresar");
		btnRegresar.setPreferredSize(new Dimension(140, 40));
		btnRegresar.setBackground(new Color(235, 245, 255));
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegresar.setFocusPainted(false);
		btnRegresar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelSuperior.add(btnRegresar, BorderLayout.EAST);

		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

		// Contenedor general
		JPanel contenedor = new JPanel();
		contenedor.setOpaque(false);
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		contenedor.setBorder(new EmptyBorder(20, 100, 20, 100));

		// Título
		lbTitulo = new JLabel("Listado de Clientes");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 30));
		lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbTitulo.setBorder(new EmptyBorder(10, 0, 20, 0));

		contenedor.add(lbTitulo);

		// Panel blanco que contiene la tabla
		JPanel tarjeta = new JPanel(new BorderLayout()) {
			// Para dar sombra
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(new Color(0, 0, 0, 30));
				g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
			}
		};
		tarjeta.setBackground(Color.WHITE);
		tarjeta.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(121, 134, 203), 2, true),
				new EmptyBorder(20, 20, 20, 20)));
		tarjeta.setMaximumSize(new Dimension(1200, 450));
		tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Tabla no editable
		String[] columnas = { "Nombre", "Cédula/NIT", "Teléfono", "Correo electrónico", "Dirección" };

		DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Cargar datos desde la base de datos
		String url = "jdbc:sqlite:tiendaRosita.db";

		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT nombre, idCliente, telefono, correo, direccion FROM clientes")) {

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String cedula = rs.getString("idCliente");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");

				modeloTabla.addRow(new Object[] { nombre, cedula, telefono, correo, direccion });
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar los clientes: " + e.getMessage());
		}

		tbTablaClientes = new JTable(modeloTabla);
		tbTablaClientes.setRowHeight(30);
		tbTablaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		tbTablaClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbTablaClientes.getTableHeader().setBackground(new Color(197, 225, 250));
		tbTablaClientes.setGridColor(new Color(220, 220, 220));
		tbTablaClientes.setShowHorizontalLines(true);
		tbTablaClientes.setShowVerticalLines(false);

		// Centrado del contenido
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < tbTablaClientes.getColumnCount(); i++) {
			tbTablaClientes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		spScrollClientes = new JScrollPane(tbTablaClientes);
		spScrollClientes.setBorder(BorderFactory.createEmptyBorder());
		tarjeta.add(spScrollClientes, BorderLayout.CENTER);

		contenedor.add(tarjeta);
		panelPrincipal.add(contenedor, BorderLayout.CENTER);

	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JTable getTbTablaClientes() {
		return tbTablaClientes;
	}

	public JScrollPane getSpScrollClientes() {
		return spScrollClientes;
	}

	public JLabel getLbTitulo() {
		return lbTitulo;
	}
}
