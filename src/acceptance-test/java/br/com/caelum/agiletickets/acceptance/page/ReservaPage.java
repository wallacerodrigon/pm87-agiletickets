package br.com.caelum.agiletickets.acceptance.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservaPage {

	private static final String BASE_URL = "http://localhost:8080";
	private final WebDriver driver;
	private String valorSessao = "0";
	

	public ReservaPage(WebDriver driver) {
		this.driver = driver;
	}

	public void abreListagem() {
		driver.get(BASE_URL);
	}	
	
	public void selecioneSessaoMais5Ingressos(int quantidadeReservar){
		List<WebElement> listaSessoes = driver.findElements(By.className("wd-sessao"));
		for(WebElement elemento : listaSessoes){
			String texto[] = elemento.getText().split(":");
			Integer ingressosDisponiveis = Integer.valueOf(texto[1].trim());
			
			if (ingressosDisponiveis > 5 && ingressosDisponiveis >= quantidadeReservar ){
				elemento.click();
				break;
			}
		}
	}	
	
	public void selecioneSessaoMenosOuIgual5Ingressos(int quantidadeReservar){
		List<WebElement> listaSessoes = driver.findElements(By.className("wd-sessao"));
		for(WebElement elemento : listaSessoes){
			String texto[] = elemento.getText().split(":");
			Integer ingressosDisponiveis = Integer.valueOf(texto[1].trim());
			
			if (ingressosDisponiveis >= quantidadeReservar && ingressosDisponiveis <= 5){
				elemento.click();
				break;
			}
		}
	}
	
	public double preencherReserva(int quantidade){
		List<WebElement> tagsParagrafo = driver.findElements(By.tagName("p"));
		
		for(WebElement paragrafo : tagsParagrafo){
			if (paragrafo.findElement(By.className("label")).getText().contains("Preco:")){
				this.valorSessao = paragrafo.getText().substring( 
									paragrafo.getText().indexOf("R$") + 3);
				this.valorSessao = this.valorSessao.replace(',', '.');
				break;
			}
		}
		driver.findElement(By.id("qtde")).sendKeys(String.valueOf(quantidade));
		driver.findElement(By.tagName("form")).submit();
		
		return quantidade * Double.valueOf(valorSessao); 
	}
	
	public boolean mensagemContemValorEsperado(String mensagemEsperada){
		return driver.findElement(By.id("message")).getText().contains(mensagemEsperada);
	}
}
