package co.edu.unbosque.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import co.edu.unbosque.model.daosYdtos.ProductoDto;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ReporteProductosPDF {

	private Facada_Model facadaModel;

	public ReporteProductosPDF() {
		facadaModel = new Facada_Model();
	}

	public void generarReportePDF() {
		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setDialogTitle("Guardar Reporte de Productos");
		selectorArchivo.setSelectedFile(new File("ReporteProductos.pdf")); // nombre por defecto

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
			List<ProductoDto> productos = facadaModel.getProductos().obtenerTodosLosProductos();

			if (productos == null || productos.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay productos para generar el reporte.", "Advertencia",
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
			Paragraph titulo = new Paragraph("Reporte de Productos", tituloFont);
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);
			documento.add(Chunk.NEWLINE);

			PdfPTable tabla = new PdfPTable(6);
			tabla.setWidthPercentage(100);
			tabla.setWidths(new int[] { 2, 4, 2, 5, 2, 2 });

			agregarCeldaEncabezado(tabla, "ID");
			agregarCeldaEncabezado(tabla, "Nombre");
			agregarCeldaEncabezado(tabla, "ID Cat.");
			agregarCeldaEncabezado(tabla, "Descripción");
			agregarCeldaEncabezado(tabla, "Precio");
			agregarCeldaEncabezado(tabla, "IVA");

			for (ProductoDto p : productos) {
				tabla.addCell(String.valueOf(p.getIdProducto()));
				tabla.addCell(p.getNombre());
				tabla.addCell(String.valueOf(p.getIdCategoriaP()));
				tabla.addCell(p.getDescripcion());
				tabla.addCell(String.format("%.2f", p.getPrecio()));
				tabla.addCell(String.format("%.2f", p.getIva()));
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