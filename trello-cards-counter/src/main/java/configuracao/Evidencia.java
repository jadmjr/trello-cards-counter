package configuracao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class Evidencia {

	public String saidaEvidencia = "target\\evidencias\\";
	public Document document = new Document();
	String caminho = "";
	Image image;
	PdfWriter writer;
	Font ffont = new Font(Font.FontFamily.COURIER, 8, Font.ITALIC);
	Font ffon1 = new Font(Font.FontFamily.COURIER, 12, Font.UNDEFINED);

	public String retornaImagem(WebDriver driver) {

		String pasta = "target\\evidencias\\";

		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File(pasta, "temp.png"));
			caminho = pasta + "temp.png";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return caminho;
	}

	public void geraPDF() {
		Random randon = new Random();
		saidaEvidencia = saidaEvidencia + randon.nextInt() + ".pdf";
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(saidaEvidencia));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.open();

	}

	public void adcionaParagrafo(String paragrafo) {
		try {
			document.add(new Paragraph(paragrafo, ffon1));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	public void adcionaImagem(WebDriver driver) {
		try {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			image = Image.getInstance(retornaImagem(driver));
			image.scaleAbsolute(500, 300);
			image.setAlignment(Image.MIDDLE);
			document.add(image);
			onEndPage(writer, document);
			document.add(Chunk.NEXTPAGE);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}
	
	public void print(String titulo , WebDriver driver) {
		try {
			document.add(new Paragraph(titulo, ffon1));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		try {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			image = Image.getInstance(retornaImagem(driver));
			image.scaleAbsolute(500, 300);
			image.setAlignment(Image.MIDDLE);
			document.add(image);
			onEndPage(writer, document);
			document.add(Chunk.NEXTPAGE);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	public void fecharDocumentoPDF() {
		File file = new File(caminho);
		file.delete();
		document.addTitle("Cedro Technologies");
		document.addAuthor("Julimar Junior");
		document.addSubject("Execucao automatizada");
		document.addKeywords("Cedro, Teste, Julimar");
		document.addCreator("JMJR - https://br.linkedin.com/in/julimar-ant%C3%B4nio-de-miranda-j%C3%BAnior-b33a4b58");
		document.close();
	}

	public void onEndPage(PdfWriter writer, Document document) {

		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

		PdfContentByte cb = writer.getDirectContent();
		Phrase header = new Phrase("Evidencia de teste automatizado - " + data + " " + hora, ffont);
		Phrase footer = new Phrase("Cedro Technologies", ffont);
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,
				(document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
				(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
	}

}
