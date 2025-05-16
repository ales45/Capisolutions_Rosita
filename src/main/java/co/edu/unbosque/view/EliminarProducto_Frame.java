package prueba_usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EliminarProducto_Frame extends JFrame {
	private JTextField txtId, txtNombre, txtDescripcion, txtPrecio, txtIVA;

	public EliminarProducto_Frame() {
		setTitle("Eliminar Producto");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// ... dentro del constructor EliminarProducto_Frame()

		// Logo en la esquina superior izquierda
		JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelLogo.setBackground(new Color(252, 243, 174)); // mismo color de fondo

		ImageIcon icono = new ImageIcon("src/logo.png");
		Image imagen = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // ajusta tamaño si es necesario
		JLabel lblLogo = new JLabel(new ImageIcon(imagen));
		panelLogo.add(lblLogo);

		add(panelLogo, BorderLayout.NORTH);

		// Fondo
		JPanel fondo = new JPanel(new GridBagLayout());
		fondo.setBackground(new Color(252, 243, 174)); // amarillo claro
		add(fondo, BorderLayout.CENTER);

		// Recuadro central
		JPanel recuadro = new JPanel(new GridBagLayout());
		recuadro.setBackground(Color.WHITE);
		recuadro.setPreferredSize(new Dimension(500, 470));
		recuadro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
				BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Título
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		JLabel lblTitulo = new JLabel("Eliminar Producto");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		recuadro.add(lblTitulo, gbc);

		// Campos
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

		// Precio
		gbc.gridy = fila++;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		recuadro.add(new JLabel("Precio:"), gbc);
		gbc.gridx = 1;
		recuadro.add(txtPrecio, gbc);

		// IVA
		gbc.gridy = fila++;
		gbc.gridx = 0;
		recuadro.add(new JLabel("IVA:"), gbc);

		JPanel ivaPanel = new JPanel(new BorderLayout());
		ivaPanel.setBackground(Color.WHITE);
		ivaPanel.add(txtIVA, BorderLayout.CENTER);
		ivaPanel.add(new JLabel(" %"), BorderLayout.EAST);

		gbc.gridx = 1;
		recuadro.add(ivaPanel, gbc);

		// Botones: Eliminar y Regresar
		gbc.gridy = fila++;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.EAST;

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		botonesPanel.setBackground(Color.WHITE);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(244, 67, 54));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFocusPainted(false);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(new Color(240, 230, 140));
		btnRegresar.setFocusPainted(false);

		botonesPanel.add(btnRegresar);
		botonesPanel.add(btnEliminar);
		recuadro.add(botonesPanel, gbc);

		fondo.add(recuadro); // Añadir recuadro al centro

		// Eventos
		btnEliminar.addActionListener(e -> eliminarProducto());
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

	private void eliminarProducto() {
		String id = txtId.getText().trim();
		String nombre = txtNombre.getText().trim();
		String descripcion = txtDescripcion.getText().trim();
		String precio = txtPrecio.getText().trim();
		String iva = txtIVA.getText().trim();

		if (id.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || precio.isEmpty() || iva.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			Double.parseDouble(precio);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error en precio",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			int ivaNum = Integer.parseInt(iva);
			if (ivaNum < 0 || ivaNum > 100) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El IVA debe ser un número entre 0 y 100.", "Error en IVA",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);
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

	
}
