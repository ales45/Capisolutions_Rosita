package co.edu.unbosque.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import co.edu.unbosque.model.daosYdtos.DevoProveDto;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

public class ReporteDevolucionesProveedorPDF {

	private Facada_Model facadaModel;

	public ReporteDevolucionesProveedorPDF() {
		facadaModel = new Facada_Model();
	}

	public void generarReportePDF() {
		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setDialogTitle("Guardar Reporte de Devoluciones a Proveedor");
		selectorArchivo.setSelectedFile(new File("Reporte-Devoluciones-Proveedor.pdf"));

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
			List<DevoProveDto> devoluciones = facadaModel.getDevoProvedo().obtener_todas_las_devo_prove();

			if (devoluciones == null || devoluciones.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay devoluciones para generar el reporte.", "Advertencia",
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
			Paragraph titulo = new Paragraph("Reporte de Devoluciones a Proveedor", tituloFont);
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);
			documento.add(Chunk.NEWLINE);

			PdfPTable tabla = new PdfPTable(6);
			tabla.setWidthPercentage(100);
			tabla.setWidths(new int[] { 2, 2, 2, 2, 4, 3 });

			agregarCeldaEncabezado(tabla, "ID Devolución");
			agregarCeldaEncabezado(tabla, "ID Proveedor");
			agregarCeldaEncabezado(tabla, "ID Producto");
			agregarCeldaEncabezado(tabla, "Cantidad");
			agregarCeldaEncabezado(tabla, "Motivo");
			agregarCeldaEncabezado(tabla, "Fecha");

			for (DevoProveDto d : devoluciones) {
				tabla.addCell(String.valueOf(d.getIdDevoProve()));
				tabla.addCell(String.valueOf(d.getIdProveedor()));
				tabla.addCell(String.valueOf(d.getIdProducto()));
				tabla.addCell(String.valueOf(d.getCantidad()));
				tabla.addCell(d.getMotivo());
				tabla.addCell(d.getFecha().toString());
			}

			documento.add(tabla);
			documento.close();

			JOptionPane.showMessageDialog(null, "Reporte PDF generado exitosamente:\n" + rutaArchivo, "Éxito",
					JOptionPane.INFORMATION_MESSAGE);

		}  catch (Exception e) {
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
