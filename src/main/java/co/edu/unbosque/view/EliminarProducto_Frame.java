package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EliminarProducto_Frame extends JFrame {
	private JTextField txtId, txtNombre, txtDescripcion, txtPrecio, txtIVA;
	private JButton btnEliminar, btnRegresar; // Declarar botones como variables de instancia

	public EliminarProducto_Frame() {
		setTitle("Eliminar Producto");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelLogo.setBackground(new Color(252, 243, 174));

		ImageIcon icono = new ImageIcon("src/logo.png");
		Image imagen = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(imagen));
		panelLogo.add(lblLogo);

		add(panelLogo, BorderLayout.NORTH);

		JPanel fondo = new JPanel(new GridBagLayout());
		fondo.setBackground(new Color(252, 243, 174));
		add(fondo, BorderLayout.CENTER);

		JPanel recuadro = new JPanel(new GridBagLayout());
		recuadro.setBackground(Color.WHITE);
		recuadro.setPreferredSize(new Dimension(500, 470));
		recuadro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
				BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		JLabel lblTitulo = new JLabel("Eliminar Producto");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		recuadro.add(lblTitulo, gbc);

		txtId = new JTextField(20);
		txtNombre = new JTextField(20);
		txtDescripcion = new JTextField(20);
		txtPrecio = new JTextField(10);
		txtIVA = new JTextField(5);

		agregarFiltroNumerico(txtId, false);
		agregarFiltroNumerico(txtPrecio, true);
		agregarFiltroIVA(txtIVA);

		int fila = 1;
		fila = agregarCampo(recuadro, "ID del producto:", txtId, fila, gbc);
		fila = agregarCampo(recuadro, "Nombre:", txtNombre, fila, gbc);
		fila = agregarCampo(recuadro, "Descripción:", txtDescripcion, fila, gbc);

		gbc.gridy = fila++;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		recuadro.add(new JLabel("Precio:"), gbc);
		gbc.gridx = 1;
		recuadro.add(txtPrecio, gbc);

		gbc.gridy = fila++;
		gbc.gridx = 0;
		recuadro.add(new JLabel("IVA:"), gbc);

		JPanel ivaPanel = new JPanel(new BorderLayout());
		ivaPanel.setBackground(Color.WHITE);
		ivaPanel.add(txtIVA, BorderLayout.CENTER);
		ivaPanel.add(new JLabel(" %"), BorderLayout.EAST);

		gbc.gridx = 1;
		recuadro.add(ivaPanel, gbc);

		gbc.gridy = fila++;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.EAST;

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		botonesPanel.setBackground(Color.WHITE);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(244, 67, 54));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFocusPainted(false);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(240, 230, 140));
		btnRegresar.setFocusPainted(false);

		botonesPanel.add(btnRegresar);
		botonesPanel.add(btnEliminar);
		recuadro.add(botonesPanel, gbc);

		fondo.add(recuadro);

		btnRegresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Regresando..."));
	}

	private int agregarCampo(JPanel panel, String label, JTextField campo, int fila, GridBagConstraints gbc) {
		gbc.gridy = fila;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(new JLabel(label), gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(campo, gbc);

		return fila + 1;
	}

	

	private void agregarFiltroNumerico(JTextField campo, boolean permitirDecimal) {
		campo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = campo.getText();
				if (!Character.isDigit(c) && (!permitirDecimal || c != '.')) {
					e.consume();
				}
				if (c == '.' && text.contains(".")) {
					e.consume();
				}
			}
		});
	}

	private void agregarFiltroIVA(JTextField campo) {
		campo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				} else {
					String text = campo.getText() + c;
					try {
						int value = Integer.parseInt(text);
						if (value > 100)
							e.consume();
					} catch (NumberFormatException ex) {
						e.consume();
					}
				}
			}
		});
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public void setTxtPrecio(JTextField txtPrecio) {
		this.txtPrecio = txtPrecio;
	}

	public JTextField getTxtIVA() {
		return txtIVA;
	}

	public void setTxtIVA(JTextField txtIVA) {
		this.txtIVA = txtIVA;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}
	
}
