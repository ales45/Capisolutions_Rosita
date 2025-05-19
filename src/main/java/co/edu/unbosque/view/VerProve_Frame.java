package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VerProve_Frame extends JFrame {

	private JLabel lbTitulo;
	private JTextField txtBuscar;
	private JComboBox<String> cboxMetodoPago;
	private JComboBox<String> cboxFecha;
	private JComboBox<String> cboxEstado;
	private JTable tbProve;
	private JScrollPane spProve;
	private JButton btnRegresar;

	public VerProve_Frame() {
		setTitle("Proveedores");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Color.decode("#e8f0fe"));
		add(panelPrincipal, BorderLayout.CENTER);

		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(new EmptyBorder(20, 40, 20, 40));

		ImageIcon icon = new ImageIcon("src/logo.png");
		Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(img));
		panelSuperior.add(lblLogo, BorderLayout.WEST);

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

		JPanel contenedor = new JPanel();
		contenedor.setOpaque(false);
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		contenedor.setBorder(new EmptyBorder(20, 100, 20, 100));

		lbTitulo = new JLabel("Proveedores");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 30));
		lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbTitulo.setBorder(new EmptyBorder(10, 0, 20, 0));
		contenedor.add(lbTitulo);

		JPanel filtros = new JPanel(new GridLayout(2, 2, 15, 15));
		filtros.setOpaque(false);
		filtros.setMaximumSize(new Dimension(800, 80));
		filtros.setAlignmentX(Component.CENTER_ALIGNMENT);

		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));
		filtros.add(txtBuscar);

		cboxMetodoPago = new JComboBox<>(new String[] { "Todos", "Crédito", "Contado" });
		cboxMetodoPago.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cboxMetodoPago.setBorder(BorderFactory.createTitledBorder("Método de Pago"));
		filtros.add(cboxMetodoPago);

		cboxFecha = new JComboBox<>(new String[] { "Recientes", "Antiguos" });
		cboxFecha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cboxFecha.setBorder(BorderFactory.createTitledBorder("Fecha"));
		filtros.add(cboxFecha);

		cboxEstado = new JComboBox<>(new String[] { "Todos", "Activo", "Inactivo" });
		cboxEstado.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cboxEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
		filtros.add(cboxEstado);

		contenedor.add(filtros);

		JPanel tarjeta = new JPanel(new BorderLayout()) {
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

		String[] columnas = { "Nombre del Proveedor", "NIT o Cédula", "Teléfono", "Correo", "Dirección" };
		DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbProve = new JTable(modelo);
		tbProve.setRowHeight(30);
		tbProve.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		tbProve.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tbProve.getTableHeader().setBackground(new Color(197, 225, 250));
		tbProve.setGridColor(new Color(220, 220, 220));
		tbProve.setShowHorizontalLines(true);
		tbProve.setShowVerticalLines(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < tbProve.getColumnCount(); i++) {
			tbProve.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		spProve = new JScrollPane(tbProve);
		spProve.setBorder(BorderFactory.createEmptyBorder());
		tarjeta.add(spProve, BorderLayout.CENTER);

		contenedor.add(tarjeta);
		panelPrincipal.add(contenedor, BorderLayout.CENTER);
	}

	public JLabel getLbTitulo() {
		return lbTitulo;
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

	public JTable getTbProve() {
		return tbProve;
	}

	public JScrollPane getSpProve() {
		return spProve;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}
}
