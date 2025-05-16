package prueba_usuarios;

import javax.swing.*;
import java.awt.*;

public class MClientes_View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblLogo;
	private JButton btnNuevoCliente_View;
	private JButton btnEditarCliente_View;
	private JButton btnVerCliente_View;
	private JButton btnEliminarCliente_View;
	private JButton btnRegresar;

	public MClientes_View() {
		setTitle("Papelería de Rosita - Gestión de Clientes");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Fondo azul claro
		JPanel fondo = new JPanel(new BorderLayout());
		fondo.setBackground(Color.decode("#e8f0fe"));
		getContentPane().add(fondo);

		// Panel superior (logo + botón regresar)
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setOpaque(false);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		// Logo
		ImageIcon icon = new ImageIcon("src/logo.png");
		Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		lblLogo = new JLabel(new ImageIcon(img));
		panelSuperior.add(lblLogo, BorderLayout.WEST);

		// Botón regresar
		btnRegresar = new JButton("Regresar");
		btnRegresar.setPreferredSize(new Dimension(160, 40));
		btnRegresar.setBackground(Color.decode("#e8f0fe"));
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setFocusPainted(false);
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegresar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panelSuperior.add(btnRegresar, BorderLayout.EAST);

		fondo.add(panelSuperior, BorderLayout.NORTH);

		// Contenedor central
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		contenedor.setBackground(Color.WHITE);
		contenedor.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		JPanel botonesPanel = new JPanel(new GridLayout(2, 2, 40, 40));
		botonesPanel.setBackground(Color.WHITE);

		// Botones con texto y nombres correctos
		btnNuevoCliente_View = new JButton("Agregar Cliente");
		btnEditarCliente_View = new JButton("Editar Cliente");
		btnVerCliente_View = new JButton("Ver Clientes");
		btnEliminarCliente_View = new JButton("Eliminar Cliente");

		estiloBoton(btnNuevoCliente_View);
		estiloBoton(btnEditarCliente_View);
		estiloBoton(btnVerCliente_View);
		estiloBoton(btnEliminarCliente_View);

		botonesPanel.add(btnNuevoCliente_View);
		botonesPanel.add(btnEditarCliente_View);
		botonesPanel.add(btnVerCliente_View);
		botonesPanel.add(btnEliminarCliente_View);

		contenedor.add(Box.createVerticalGlue());
		contenedor.add(botonesPanel);
		contenedor.add(Box.createVerticalGlue());

		fondo.add(contenedor, BorderLayout.CENTER);
	}

	private void estiloBoton(JButton boton) {
		boton.setPreferredSize(new Dimension(300, 80));
		boton.setBackground(Color.decode("#fff3e0"));
		boton.setFocusPainted(false);
		boton.setFont(new Font("Arial", Font.BOLD, 18));
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 128), 2, true));
	}

	// Getters
	public JButton getBtnNuevoCliente_View() {
		return btnNuevoCliente_View;
	}

	public JButton getBtnEditarCliente_View() {
		return btnEditarCliente_View;
	}

	public JButton getBtnVerCliente_View() {
		return btnVerCliente_View;
	}

	public JButton getBtnEliminarCliente_View() {
		return btnEliminarCliente_View;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	
}
