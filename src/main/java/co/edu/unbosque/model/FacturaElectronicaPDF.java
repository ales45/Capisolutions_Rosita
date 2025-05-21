package co.edu.unbosque.model;

import co.edu.unbosque.model.daosYdtos.FacturasDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.io.FileOutputStream;
import java.util.List;

public class FacturaElectronicaPDF {

	public void emitirFacturaElectronica(String nombreUsuario, Facada_Model facadaModel) {
		try {
			String input = JOptionPane.showInputDialog("Ingrese el ID del cliente para emitir factura:");
			if (input == null || input.trim().isEmpty())
				return;

			int idCliente = Integer.parseInt(input.trim());

			List<FacturasDto> facturas = facadaModel.getFacturas().obtener_facturas_por_cliente(idCliente);
			if (facturas.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se encontraron facturas para el cliente.");
				return;
			}

			// Si hay varias facturas, podrías mostrar una lista para seleccionar
			FacturasDto factura = facturas.get(0); // Usamos la primera factura encontrada
			generarFacturaElectronica(factura, nombreUsuario);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ID inválido.");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al generar la factura: " + e.getMessage());
		}
	}

	public void generarFacturaElectronica(FacturasDto factura, String nombreEmisor) {
		try {
			JFileChooser selector = new JFileChooser();
			selector.setDialogTitle("Guardar Factura Electrónica");
			selector.setSelectedFile(new java.io.File("Factura_" + factura.getIdFactura() + ".pdf"));

			if (selector.showSaveDialog(null) != JFileChooser.APPROVE_OPTION)
				return;

			String ruta = selector.getSelectedFile().getAbsolutePath();
			if (!ruta.toLowerCase().endsWith(".pdf")) {
				ruta += ".pdf";
			}

			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(ruta));
			doc.open();

			// Logo
			try {
				Image logo = Image.getInstance("src/logo.png");
				logo.scaleToFit(80, 80);
				logo.setAlignment(Image.ALIGN_LEFT);
				doc.add(logo);
			} catch (Exception e) {
				// El logo es opcional, no detenemos el proceso si no está.
			}

			// Título
			Paragraph titulo = new Paragraph("Factura Electrónica",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
			titulo.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo);
			doc.add(Chunk.NEWLINE);

			// Datos del emisor
			doc.add(new Paragraph("Emisor: " + nombreEmisor));
			doc.add(new Paragraph("NIT:458796321-7"));
			doc.add(new Paragraph("Dirección: Carrera 18 Sur #52B-14, Barrio San Carlos, Bogotá"));
			doc.add(Chunk.NEWLINE);

			// Datos del receptor
			doc.add(new Paragraph("Receptor (ID Cliente): " + factura.getIdCliente()));
			doc.add(new Paragraph("Fecha de emisión: " + factura.getFecha()));
			doc.add(Chunk.NEWLINE);

			// Número de factura
			doc.add(new Paragraph("Número de factura: " + factura.getIdFactura()));
			doc.add(Chunk.NEWLINE);

			// Tabla de productos (simulada)
			PdfPTable tabla = new PdfPTable(4);
			tabla.setWidthPercentage(100);
			tabla.setWidths(new float[] { 4, 1, 2, 2 });
			tabla.addCell("Descripción");
			tabla.addCell("Cant");
			tabla.addCell("Precio Unitario");
			tabla.addCell("Subtotal");

			tabla.addCell("Lápiz Mongol");
			tabla.addCell("2");
			tabla.addCell("$1.000");
			tabla.addCell("$2.000");

			tabla.addCell("Cuaderno Norma");
			tabla.addCell("1");
			tabla.addCell("$5.000");
			tabla.addCell("$5.000");

			doc.add(tabla);
			doc.add(Chunk.NEWLINE);

			// Impuestos y total
			doc.add(new Paragraph("IVA (19%): $1.330"));
			doc.add(new Paragraph("TOTAL A PAGAR: $8.330"));
			doc.add(new Paragraph("Forma de pago: Efectivo"));
			doc.add(Chunk.NEWLINE);

			// Estado de pago
			doc.add(new Paragraph("Estado de Pago: " + factura.getEstadoPago()));

			doc.close();

			JOptionPane.showMessageDialog(null, "Factura generada exitosamente:\n" + ruta);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al generar la factura: " + e.getMessage());
		}
	}
}
