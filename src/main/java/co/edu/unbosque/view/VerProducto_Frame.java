package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VerProducto_Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable tablaProductos;
    private JButton btnRegresar;
    private JLabel lbTitulo, lbAdvertencia;

    public VerProducto_Frame() {
        setTitle("Productos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.decode("#ffff99")); // fondo amarillo claro
        add(panelPrincipal, BorderLayout.CENTER);

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.setBorder(new EmptyBorder(20, 40, 20, 40));

        ImageIcon icon = new ImageIcon("src/logo.png");
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        panelSuperior.add(lblLogo, BorderLayout.WEST);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(140, 40));
        btnRegresar.setBackground(Color.decode("#ffff99"));
        btnRegresar.setForeground(Color.BLACK);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setFocusPainted(false);
        btnRegresar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSuperior.add(btnRegresar, BorderLayout.EAST);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        // Contenido central
        JPanel contenedor = new JPanel();
        contenedor.setOpaque(false);
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBorder(new EmptyBorder(20, 100, 20, 100));

        lbTitulo = new JLabel("Productos");
        lbTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(lbTitulo);


        // Panel tarjeta
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(121, 134, 203), 2, true),
                new EmptyBorder(20, 20, 20, 20)));
        tarjeta.setMaximumSize(new Dimension(1000, 400));
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] columnas = { "ID", "Nombre", "Categoría", "Descripción", "Precio", "IVA", "Stock" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

   

        tablaProductos = new JTable(modelo);
        tablaProductos.setRowHeight(28);
        tablaProductos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaProductos.getTableHeader().setBackground(new Color(224, 242, 241));
        tablaProductos.setGridColor(new Color(220, 220, 220));

        // Centrado de columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablaProductos.getColumnCount(); i++) {
            tablaProductos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollTabla = new JScrollPane(tablaProductos);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder());
        tarjeta.add(scrollTabla, BorderLayout.CENTER);

        contenedor.add(tarjeta);
        panelPrincipal.add(contenedor, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VerProducto_Frame::new);
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JTable getTablaProductos() {
        return tablaProductos;
    }
}
