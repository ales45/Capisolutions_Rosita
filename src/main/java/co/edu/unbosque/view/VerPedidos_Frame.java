package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class VerPedidos_Frame extends JFrame {

	private JLabel lbTitulo;
	private JTextField txtBuscar;
	private JComboBox<String> cboxMetodoPago, cboxFecha, cboxEstado;
	private JTable tbPedidos;
	private JScrollPane spPedidos;
	private JButton btnRegresar;

	public VerPedidos_Frame() {
		setTitle("Historial de Pedidos");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Color.decode("#89a8c2"));
		add(panelPrincipal, BorderLayout.CENTER);

		// Panel superior
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(new EmptyBorder(20, 20, 20, 20));

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(132, 163, 189));
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnRegresar.setFocusPainted(false);
		btnRegresar.setBorder(new RoundBorder(20));
		btnRegresar.setPreferredSize(new Dimension(140, 40));
		panelSuperior.add(btnRegresar, BorderLayout.EAST);

		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

		// Contenedor central
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		contenedor.setOpaque(false);
		contenedor.setBorder(new EmptyBorder(10, 80, 20, 80));

		lbTitulo = new JLabel("Historial de Pedidos");
		lbTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		contenedor.add(lbTitulo);
		contenedor.add(Box.createVerticalStrut(20));

		// Filtros
		JPanel filtros = new JPanel();
		filtros.setOpaque(false);
		filtros.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

		txtBuscar = new JTextField(15);
		txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBuscar.setBorder(BorderFactory.createTitledBorder("🔍 Buscar"));

		cboxFecha = new JComboBox<>(new String[] { "Fecha:" });
		cboxMetodoPago = new JComboBox<>(new String[] { "Producto:" });
		cboxEstado = new JComboBox<>(new String[] { "Proveedor:" });

		for (JComboBox<?> cb : new JComboBox[] { cboxFecha, cboxMetodoPago, cboxEstado }) {
			cb.setPreferredSize(new Dimension(160, 40));
			cb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}

		filtros.add(txtBuscar);
		filtros.add(cboxFecha);
		filtros.add(cboxMetodoPago);
		filtros.add(cboxEstado);

		contenedor.add(filtros);

		// Tabla
		String[] columnas = { "ID", "Producto", "Tipo Movimiento", "Cantidad", "Fecha", "Motivo", "ID Inventario" };
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbPedidos = new JTable(modelo);
		tbPedidos.setRowHeight(30);
		tbPedidos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tbPedidos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
		tbPedidos.getTableHeader().setBackground(new Color(222, 235, 247));
		tbPedidos.setGridColor(Color.LIGHT_GRAY);

		spPedidos = new JScrollPane(tbPedidos);
		spPedidos.setBorder(new EmptyBorder(10, 10, 10, 10));
		spPedidos.setPreferredSize(new Dimension(1000, 300));

		JPanel tarjetaTabla = new JPanel(new BorderLayout());
		tarjetaTabla.setBackground(Color.WHITE);
		tarjetaTabla.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.LIGHT_GRAY, 1, true),
				new EmptyBorder(10, 10, 10, 10)));
		tarjetaTabla.setMaximumSize(new Dimension(1200, 400));
		tarjetaTabla.add(spPedidos, BorderLayout.CENTER);

		contenedor.add(Box.createVerticalStrut(20));
		contenedor.add(tarjetaTabla);

		panelPrincipal.add(contenedor, BorderLayout.CENTER);
	}

	// Borde redondeado
	private static class RoundBorder extends LineBorder {
		public RoundBorder(int radius) {
			super(Color.GRAY, 1, true);
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(10, 20, 10, 20);
		}
	}

	// Render de estado
	private static class EstadoRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {
			Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
			String estado = value.toString();
			comp.setForeground(estado.contains("Entregado") ? new Color(46, 125, 50) : new Color(255, 87, 34));
			return comp;
		}
	}

	// Getters
	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}

	public JComboBox<String> getCboxMetodoPago() {
		return cboxMetodoPago;
	}

	public JComboBox<String> getCboxFecha() {
		return cboxFecha;
	}

	public JComboBox<String> getCboxEstado() {
		return cboxEstado;
	}

	public JTable getTbPedidos() {
		return tbPedidos;
	}

	public JScrollPane getSpPedidos() {
		return spPedidos;
	}

	public JLabel getLbTitulo() {
		return lbTitulo;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VerPedidos_Frame().setVisible(true));
	}
}
