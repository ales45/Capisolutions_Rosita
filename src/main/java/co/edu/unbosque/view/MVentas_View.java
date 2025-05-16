package prueba_usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MVentas_View extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton btnNuevaVenta, btnHistorial, btnCorregir, btnEliminar, btnRegresar;
    private JLabel lblLogo;

    public MVentas_View() {
        setTitle("Papelería de Rosita - Módulo de Ventas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Fondo verde claro
        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(Color.decode("#a8d5ba"));
        getContentPane().add(fondo);

        // Panel superior (botón regresar)
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        // Logo a la izquierda
        ImageIcon icon = null;
        try { 
            icon = new ImageIcon(MVentas_View.class.getResource("/logo.png"));
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblLogo = new JLabel(new ImageIcon(img));
        } catch (NullPointerException e) {
            System.err.println("Error: No se pudo cargar la imagen 'logo.png'. Verifica la ruta del archivo.");
            lblLogo = new JLabel("Logo no disponible");
        }
        panelSuperior.add(lblLogo, BorderLayout.WEST);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(140, 40));
        btnRegresar.setBackground(new Color(150, 200, 170));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(130, 170, 150)));

        panelSuperior.add(btnRegresar, BorderLayout.EAST);
        fondo.add(panelSuperior, BorderLayout.NORTH);

        // Caja blanca central
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JPanel botonesPanel = new JPanel(new GridLayout(2, 2, 40, 40));
        botonesPanel.setBackground(Color.WHITE);

        btnNuevaVenta = new JButton("Nueva Venta");
        btnHistorial = new JButton("Ver Historial de Ventas");
        btnCorregir = new JButton("Corregir Ventas Pasadas / Devoluciones");
        btnEliminar = new JButton("Eliminar Ventas Pasadas");

        estiloBoton(btnNuevaVenta);
        estiloBoton(btnHistorial);
        estiloBoton(btnCorregir);
        estiloBoton(btnEliminar);

        botonesPanel.add(btnNuevaVenta);
        botonesPanel.add(btnHistorial);
        botonesPanel.add(btnCorregir);
        botonesPanel.add(btnEliminar);

        contenedor.add(Box.createVerticalGlue());
        contenedor.add(botonesPanel);
        contenedor.add(Box.createVerticalGlue());

        fondo.add(contenedor, BorderLayout.CENTER);
    }

    private void estiloBoton(JButton boton) {
        boton.setPreferredSize(new Dimension(300, 80));
        boton.setBackground(Color.decode("#a8d5ba"));
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createLineBorder(new Color(130, 170, 150), 2, true));
    }

    // Getters si necesitas conectarlos con controladores
    public JButton getBtnNuevaVenta() {
        return btnNuevaVenta;
    }

    public JButton getBtnHistorial() {
        return btnHistorial;
    }

    public JButton getBtnCorregir() {
        return btnCorregir;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

}