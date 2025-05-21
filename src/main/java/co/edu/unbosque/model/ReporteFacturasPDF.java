package co.edu.unbosque.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import co.edu.unbosque.model.daosYdtos.FacturasDto;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ReporteFacturasPDF {

    private Facada_Model facadaModel;

    public ReporteFacturasPDF() {
        facadaModel = new Facada_Model();
    }

    public void generarReportePDF() {
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setDialogTitle("Guardar Reporte de Facturas");
        selectorArchivo.setSelectedFile(new File("ReporteFacturas.pdf"));

        int resultado = selectorArchivo.showSaveDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            String rutaSeleccionada = selectorArchivo.getSelectedFile().getAbsolutePath();

            if (!rutaSeleccionada.toLowerCase().endsWith(".pdf")) {
                rutaSeleccionada += ".pdf";
            }

            generarReportePDFInterno(rutaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.", "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void generarReportePDFInterno(String rutaArchivo) {
        Document documento = new Document();

        try {
            List<FacturasDto> facturas = facadaModel.getFacturas().obtener_todas_las_facturas();

            if (facturas == null || facturas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay facturas para generar el reporte.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();
        	Image logo = Image.getInstance("src/logo.png");
			logo.scaleToFit(100, 100);
			logo.setAlignment(Image.ALIGN_LEFT);
			documento.add(logo);
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Reporte de Facturas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(4); // Ajustado al número de columnas
            tabla.setWidthPercentage(100);
            tabla.setWidths(new int[] { 2, 2, 3, 2 }); // Ajustar según diseño

            agregarCeldaEncabezado(tabla, "ID Factura");
            agregarCeldaEncabezado(tabla, "ID Cliente");
            agregarCeldaEncabezado(tabla, "Fecha");
            agregarCeldaEncabezado(tabla, "Estado de Pago");

            for (FacturasDto f : facturas) {
                tabla.addCell(String.valueOf(f.getIdFactura()));
                tabla.addCell(String.valueOf(f.getIdCliente()));
                tabla.addCell(f.getFecha().toString());
                tabla.addCell(f.getEstadoPago());
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte PDF generado exitosamente:\n" + rutaArchivo, "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte PDF:\n" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarCeldaEncabezado(PdfPTable tabla, String texto) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        PdfPCell celda = new PdfPCell(new Phrase(texto, font));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
    }
}
