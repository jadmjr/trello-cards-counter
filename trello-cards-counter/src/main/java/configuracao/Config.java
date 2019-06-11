package configuracao;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Config {

	public static WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor executor;
	public String pathChromeServer = "resources\\chromedriver\\chromedriver.exe";
	public Evidencia evidencia;

	public void preencherCampo(WebElement campo, String valor) {
		campo.clear();
		campo.sendKeys(valor);
	}

	public void apagarCampo(WebElement campo) {
		campo.clear();
	}

	public void clicarEmElemento(WebElement elemento) {
		try {
			elemento.click();
		} catch (Exception e) {
			try {
				executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", elemento);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void selecionarPorTexto(WebElement select, String texto) {
		new org.openqa.selenium.support.ui.Select(select).selectByVisibleText(texto);
	}

	public WebElement buscarPorId(WebDriver driver, String id, Boolean esperar) {
		if (esperar == false)
			return driver.findElement(By.id(id));
		else {
			wait = new WebDriverWait(driver, 10);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		}
	}

	public WebElement buscarPorClasse(WebDriver driver, String classe, Boolean esperar) {
		if (esperar == false)
			return driver.findElement(By.className(classe));
		else {
			wait = new WebDriverWait(driver, 10);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(classe)));
		}
	}

	public WebElement buscarPorNome(WebDriver driver, String nome, Boolean esperar) {
		if (esperar == false)
			return driver.findElement(By.name(nome));
		else {
			wait = new WebDriverWait(driver, 10);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(nome)));
		}
	}

	public WebElement buscarPorXpath(WebDriver driver, String xpath, Boolean esperar) {
		if (esperar == false)
			return driver.findElement(By.xpath(xpath));
		else {
			wait = new WebDriverWait(driver, 10);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}
	}
	

	

	public boolean elementoEstaVisivel(WebElement elemento) {
		if (elemento.isDisplayed())
			return true;
		return false;
	}

	public void esperarAparecer(WebElement elemento) {
		while (!elementoEstaVisivel(elemento)) {
			// NADA A FAZER
		}
	}

	public String pegarTexto(WebElement elemento) {
		String texto = elemento.getText();
		return texto;
	}

	public void inicializaNavegador() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver", pathChromeServer);
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 10);
	}

	public void inicializaEvidencia() {
		evidencia = new Evidencia();
		evidencia.geraPDF();
	}

	public void finalizarEvidencia() {
		evidencia.fecharDocumentoPDF();
	}

	public void abrirURL(String url) {
		driver.get(url);
	}

}
