package br.com.rao.main;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class RaoOcrMain {

	public static void main(String[] args) throws IOException {
		File imageFile = new File(
				"C:\\development\\timesheet\\09-03-2018\\tessa\\Downloads\\Rita\\2018-01-31 - CIEE NF 1060524 - OCR.pdf");
		PDDocument document = null;
		try {
			document = PDDocument.load(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = null;
		try {
			pdfStripper = new PDFTextStripper();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Retrieving text from PDF document
		String text = pdfStripper.getText(document);
		System.out.println(text);

		// Closing the document
		document.close();
	}

}
