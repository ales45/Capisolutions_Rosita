package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class RegistroPedido_Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lbTitulo, lbLogo, lbProvedor, lbFecha, lbProducto, lbCantidad, lbMotivo, lbTipoMovimiento;
	private JComboBox<String> txtProvedor, txtProducto;
	private JTextField txtCantidad, txtTipoMovimiento;
	private JTextArea txtMotivo;
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
		panelFormulario.setPreferredSize(new Dimension(500, 600)); // Aumentado el tamaño para los nuevos campos
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
		txtProvedor = new JComboBox<>();
		txtProvedor.setMaximumSize(fieldDim);
		txtProvedor.setFont(fieldFont);
		txtProvedor.addItem("Seleccione un proveedor");

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

		// Tipo de Movimiento
		lbTipoMovimiento = new JLabel("Tipo de Movimiento:");
		lbTipoMovimiento.setFont(labelFont);
		txtTipoMovimiento = new JTextField();
		txtTipoMovimiento.setMaximumSize(fieldDim);
		txtTipoMovimiento.setFont(fieldFont);

		panelFormulario.add(lbTipoMovimiento);
		panelFormulario.add(txtTipoMovimiento);
		panelFormulario.add(Box.createRigidArea(new Dimension(0, 20)));

		// Producto
		lbProducto = new JLabel("Producto:");
		lbProducto.setFont(labelFont);
		txtProducto = new JComboBox<>();
		txtProducto.setMaximumSize(fieldDim);
		txtProducto.setFont(fieldFont);
		txtProducto.addItem("Seleccione un producto");

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
		panelFormulario.add(Box.createRigidArea(new Dimension(0, 20)));

		// Motivo
		lbMotivo = new JLabel("Motivo del Pedido:");
		lbMotivo.setFont(labelFont);
		txtMotivo = new JTextArea();
		txtMotivo.setFont(fieldFont);
		txtMotivo.setLineWrap(true);
		txtMotivo.setWrapStyleWord(true);
		JScrollPane scrollMotivo = new JScrollPane(txtMotivo);
		scrollMotivo.setMaximumSize(new Dimension(300, 100));

		panelFormulario.add(lbMotivo);
		panelFormulario.add(scrollMotivo);
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
	}

	// Getters y métodos auxiliares
	public JComboBox<String> getTxtProvedor() {
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

	public String getFechaFormatoCorto() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = dateChooser.getDate();
		return (fecha != null) ? sdf.format(fecha) : "";
	}

	public String getFechaFormatoLargo() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new java.util.Locale("es", "ES"));
		Date fecha = dateChooser.getDate();
		return (fecha != null) ? sdf.format(fecha) : "";
	}

	public boolean hayFechaSeleccionada() {
		return dateChooser.getDate() != null;
	}

	public void setFecha(Date fecha) {
		dateChooser.setDate(fecha);
	}

	public void setFechaActual() {
		dateChooser.setDate(new Date());
	}

	public JTextField getTxtTipoMovimiento() {
		return txtTipoMovimiento;
	}

	public JTextArea getTxtMotivo() {
		return txtMotivo;
	}

	public JLabel getLbTitulo() {
		return lbTitulo;
	}

	public void setLbTitulo(JLabel lbTitulo) {
		this.lbTitulo = lbTitulo;
	}

	public JLabel getLbLogo() {
		return lbLogo;
	}

	public void setLbLogo(JLabel lbLogo) {
		this.lbLogo = lbLogo;
	}

	public JLabel getLbProvedor() {
		return lbProvedor;
	}

	public void setLbProvedor(JLabel lbProvedor) {
		this.lbProvedor = lbProvedor;
	}

	public JLabel getLbFecha() {
		return lbFecha;
	}

	public void setLbFecha(JLabel lbFecha) {
		this.lbFecha = lbFecha;
	}

	public JLabel getLbProducto() {
		return lbProducto;
	}

	public void setLbProducto(JLabel lbProducto) {
		this.lbProducto = lbProducto;
	}

	public JLabel getLbCantidad() {
		return lbCantidad;
	}

	public void setLbCantidad(JLabel lbCantidad) {
		this.lbCantidad = lbCantidad;
	}

	public JLabel getLbMotivo() {
		return lbMotivo;
	}

	public void setLbMotivo(JLabel lbMotivo) {
		this.lbMotivo = lbMotivo;
	}

	public JLabel getLbTipoMovimiento() {
		return lbTipoMovimiento;
	}

	public void setLbTipoMovimiento(JLabel lbTipoMovimiento) {
		this.lbTipoMovimiento = lbTipoMovimiento;
	}

	public void setTxtProvedor(JComboBox<String> txtProvedor) {
		this.txtProvedor = txtProvedor;
	}

	public void setTxtProducto(JComboBox<String> txtProducto) {
		this.txtProducto = txtProducto;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public void setTxtTipoMovimiento(JTextField txtTipoMovimiento) {
		this.txtTipoMovimiento = txtTipoMovimiento;
	}

	public void setTxtMotivo(JTextArea txtMotivo) {
		this.txtMotivo = txtMotivo;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}
	
}
