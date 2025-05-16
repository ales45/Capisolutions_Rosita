package prueba_usuarios;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class RegistroPedido_Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lbTitulo, lbLogo, lbProvedor, lbFecha, lbProducto, lbCantidad;
	private JTextField txtProvedor, txtCantidad;
	private JComboBox<String> txtProducto;
	private JButton btnGuardar, btnRegresar;
	private JDateChooser dateChooser;

	private final String GUARDARPEDIDO = "GuardarPedido";

	public RegistroPedido_Frame() {
		setTitle("Registrar Pedido - Papelería Rosita");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(new Color(108, 142, 191)); // fondo azul elegante
		getContentPane().add(panelPrincipal);

		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		// Logo
		ImageIcon icon = new ImageIcon("src/logo.png");
		Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		lbLogo = new JLabel(new ImageIcon(img));
		panelSuperior.add(lbLogo, BorderLayout.WEST);

		// Botón Regresar
		btnRegresar = new JButton("Regresar");
		btnRegresar.setPreferredSize(new Dimension(120, 40));
		btnRegresar.setBackground(new Color(108, 142, 191));
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setFocusPainted(false);
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegresar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		panelSuperior.add(btnRegresar, BorderLayout.EAST);

		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

		// Panel central con layout centrado
		JPanel panelCentro = new JPanel(new GridBagLayout());
		panelCentro.setOpaque(false);
		panelPrincipal.add(panelCentro, BorderLayout.CENTER);

		// Panel blanco tipo tarjeta
		JPanel panelFormulario = new JPanel();
		panelFormulario.setBackground(Color.WHITE);
		panelFormulario.setPreferredSize(new Dimension(500, 500));
		panelFormulario.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true),
						BorderFactory.createEmptyBorder(30, 40, 30, 40)));
		panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));

		// Título
		lbTitulo = new JLabel("Registrar Pedido");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 26));
		lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbTitulo.setForeground(new Color(33, 33, 33));
		panelFormulario.add(lbTitulo);
		panelFormulario.add(Box.createRigidArea(new Dimension(0, 30)));

		Font labelFont = new Font("Arial", Font.BOLD, 16);
		Font fieldFont = new Font("Arial", Font.PLAIN, 15);
		Dimension fieldDim = new Dimension(300, 30);

		// Proveedor
		lbProvedor = new JLabel("Proveedor:");
		lbProvedor.setFont(labelFont);
		txtProvedor = new JTextField();
		txtProvedor.setMaximumSize(fieldDim);
		txtProvedor.setFont(fieldFont);

		panelFormulario.add(lbProvedor);
		panelFormulario.add(txtProvedor);
		panelFormulario.add(Box.createRigidArea(new Dimension(0, 20)));

		// Fecha
		lbFecha = new JLabel("Fecha:");
		lbFecha.setFont(labelFont);
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setFont(fieldFont);
		dateChooser.setMaximumSize(fieldDim);

		panelFormulario.add(lbFecha);
		panelFormulario.add(dateChooser);
		panelFormulario.add(Box.createRigidArea(new Dimension(0, 20)));

		// Producto
		lbProducto = new JLabel("Producto:");
		lbProducto.setFont(labelFont);
		txtProducto = new JComboBox<>(new String[] { "Lápiz", "Cuaderno", "Borrador", "Tijeras" });
		txtProducto.setMaximumSize(fieldDim);
		txtProducto.setFont(fieldFont);

		panelFormulario.add(lbProducto);
		panelFormulario.add(txtProducto);
		panelFormulario.add(Box.createRigidArea(new Dimension(0, 20)));

		// Cantidad
		lbCantidad = new JLabel("Cantidad:");
		lbCantidad.setFont(labelFont);
		txtCantidad = new JTextField();
		txtCantidad.setMaximumSize(fieldDim);
		txtCantidad.setFont(fieldFont);

		panelFormulario.add(lbCantidad);
		panelFormulario.add(txtCantidad);
		panelFormulario.add(Box.createRigidArea(new Dimension(0, 30)));

		// Botón registrar
		btnGuardar = new JButton("Registrar Pedido");
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
		btnGuardar.setBackground(new Color(224, 224, 224));
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGuardar.setMaximumSize(new Dimension(200, 40));
		btnGuardar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

		panelFormulario.add(btnGuardar);

		// Añadir formulario al centro
		panelCentro.add(panelFormulario);

		setVisible(true);
	}

	// Getters y métodos auxiliares
	public JTextField getTxtProvedor() {
		return txtProvedor;
	}

	public JComboBox<String> getTxtProducto() {
		return txtProducto;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public String getGUARDARPEDIDO() {
		return GUARDARPEDIDO;
	}

	public String getFechaSeleccionada() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = dateChooser.getDate();
		return (fecha != null) ? sdf.format(fecha) : "";
	}

	public Date getFechaDate() {
		return dateChooser.getDate();
	}

}
