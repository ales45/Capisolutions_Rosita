package co.edu.unbosque.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import co.edu.unbosque.model.daosYdtos.ClientesDto;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ReporteClientesPDF {

	private Facada_Model facadaModel;

	public ReporteClientesPDF() {
		facadaModel = new Facada_Model();
	}

	public void generarReportePDF() {
		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setDialogTitle("Guardar Reporte de Clientes");
		selectorArchivo.setSelectedFile(new File("ReporteClientes.pdf"));

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
			List<ClientesDto> clientes = facadaModel.getClientes().obtener_todos_los_clientes();

			if (clientes == null || clientes.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay clientes para generar el reporte.", "Advertencia",
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
			Paragraph titulo = new Paragraph("Reporte de Clientes", tituloFont);
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);
			documento.add(Chunk.NEWLINE);

			PdfPTable tabla = new PdfPTable(6);
			tabla.setWidthPercentage(100);
			tabla.setWidths(new int[] { 2, 4, 3, 5, 4, 4 });

			agregarCeldaEncabezado(tabla, "ID");
			agregarCeldaEncabezado(tabla, "Nombre");
			agregarCeldaEncabezado(tabla, "Rol");
			agregarCeldaEncabezado(tabla, "Correo");
			agregarCeldaEncabezado(tabla, "Cédula");
			agregarCeldaEncabezado(tabla, "Teléfono");

			for (ClientesDto c : clientes) {
				tabla.addCell(String.valueOf(c.getIdCliente()));
				tabla.addCell(c.getNombre());
				tabla.addCell(c.getRol());
				tabla.addCell(c.getCorreo());
				tabla.addCell(String.valueOf(c.getCedula()));
				tabla.addCell(String.valueOf(c.getTelefono()));
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




