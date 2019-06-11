package controlador;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;

import configuracao.Config;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import dominio.Google;

public class Controle extends Config {

	// TELAS
	Google google;

	public Controle() {
		inicializaNavegador();
		inicializaEvidencia();
	}

	// GOOGLE
	@Dado("^o endereco do Google \"([^\"]*)\"$")
	public void o_endere_o_do_Google(String endereco) {
		abrirURL(endereco);
		google = new Google(driver);
		evidencia.print("O endero do Google", driver);
	}

	@Dado("^pesquisar o valor \"([^\"]*)\"$")
	public void pesquisar_o_valor(String valor) {
		preencherCampo(google.getCampoDePesquisa(), valor);
		evidencia.print("Pesquisar o valor", driver);
	}

	@Dado("^clicar no botao pesquisa$")
	public void clicar_no_botao_pesquisa() {
		esperarAparecer(google.getBotaoPesquisar());
		clicarEmElemento(google.getBotaoPesquisar());
		evidencia.print("Clicar no botão pesquisar", driver);
	}

	@Entao("^verificar se o primerio resultado e a pagina do Linkedin$")
	public void verificar_se_o_primerio_resultado_e_a_pagina_do_Linkedin() {
		String resultadoEsperado = "https://br.linkedin.com/";
		String resultado = pegarTexto(google.getLinkPrimeiroResultado());
		Assert.assertEquals(resultadoEsperado, resultado);
		evidencia.print("Verificar se o primeiro resultado é: " + resultadoEsperado, driver);
		finalizarEvidencia();
	}

	// TRELLO
	@Dado("^o meu robo fazer um report do Trello$")
	public void o_meu_robo_fazer_um_report_do_Trello() {
		driver.get("https://trello.com/b/jq5Cd5Ng/sgs-backlog-corre%C3%A7%C3%B5es");

		clicarEmElemento(buscarPorXpath(driver, "//*[@id=\"surface\"]/div[1]/div/div[2]/a[2]", true));

		buscarPorId(driver, "user", true).sendKeys("julimar.miranda@cedrotech.com");
		buscarPorId(driver, "password", false).sendKeys("azerpl#90");
		buscarPorId(driver, "login", false).click();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> colunas = driver.findElements(By.className("js-list-content"));

		System.out.println(colunas.size());
		for (WebElement coluna : colunas) {
			System.out.println("Coluna: " + coluna.findElement(By.className("list-header-name-assist")).getText());
		}

	}

}
