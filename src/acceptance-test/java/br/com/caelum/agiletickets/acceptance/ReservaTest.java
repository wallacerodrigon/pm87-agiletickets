package br.com.caelum.agiletickets.acceptance;

import java.math.BigDecimal;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.agiletickets.PreencheBanco;
import br.com.caelum.agiletickets.acceptance.page.ReservaPage;

public class ReservaTest {
	
	private static WebDriver browser;
	private ReservaPage reservaPage;

	
	@BeforeClass
	public static void abreBrowser() {
		browser = new FirefoxDriver();
		new PreencheBanco();
	}

	@Before
	public void setUp() throws Exception {
		reservaPage = new ReservaPage(browser);
	}

	@AfterClass
	public static void teardown() {
		browser.close();
	}	
	
	@Test
	public void deveReservarComMenosDe5IngressosEAcrescimoDezPorcento(){
		reservaPage.abreListagem();
		
		reservaPage.selecioneSessaoMenosOuIgual5Ingressos(2);
		
		String totalStr = BigDecimal
							.valueOf(reservaPage.preencherReserva(2))
							.multiply(BigDecimal.valueOf(1.1))
							.toPlainString()
							.replace('.', ',');
		
		Assert.assertTrue(reservaPage.mensagemContemValorEsperado(totalStr));
	}
	
	@Test
	public void deveReservarComMaisDe5IngressosMesmoValor(){
		reservaPage.abreListagem();
		
		reservaPage.selecioneSessaoMais5Ingressos(5);
		
		String totalStr = BigDecimal
				.valueOf(reservaPage.preencherReserva(5))
				.toPlainString()
				.replace('.', ',');
		
		Assert.assertTrue(reservaPage.mensagemContemValorEsperado(totalStr));
	}	
}
