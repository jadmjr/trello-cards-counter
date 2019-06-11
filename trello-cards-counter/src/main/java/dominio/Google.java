package dominio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import configuracao.Config;

public class Google extends Config {

	WebElement campoDePesquisa, botaoPesquisar, botaoEstouComSorte, linkPrimeiroResultado;

	public Google(WebDriver driver) {

		campoDePesquisa = buscarPorNome(driver, "q", true);

		botaoPesquisar = buscarPorNome(driver, "btnK", false);

		botaoEstouComSorte = buscarPorNome(driver, "btnI", false);
	}

	public WebElement getCampoDePesquisa() {
		return campoDePesquisa;
	}

	public WebElement getBotaoPesquisar() {
		return botaoPesquisar;
	}

	public WebElement getBotaoEstouComSorte() {
		return botaoEstouComSorte;
	}

	public WebElement getLinkPrimeiroResultado() {
		linkPrimeiroResultado = buscarPorClasse(driver, "iUh30", true);
		return linkPrimeiroResultado;
	}

}
