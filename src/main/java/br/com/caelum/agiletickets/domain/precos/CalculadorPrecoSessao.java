package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculadorPrecoSessao {

	public static BigDecimal retornarPrecoSessaoComFator(Sessao sessao, BigDecimal fator) {
		return sessao.getPreco().add(getAumentoPreco(sessao, fator));
	}
	
	public static BigDecimal retornarPrecoSessaoComFator(Sessao sessao, double fator) {
		return retornarPrecoSessaoComFator(sessao, BigDecimal.valueOf(fator));
	}	
	
	public static BigDecimal calcularPrecoTotalSessao(Sessao sessao, double percentualDisponivel, double fator){
		if(sessao.getPercentualDisponivel() <= percentualDisponivel) { 
			return CalculadorPrecoSessao.retornarPrecoSessaoComFator(sessao, BigDecimal.valueOf(fator));
		} else {
			return sessao.getPreco();
		}	
		
	}
	
	public static BigDecimal getAumentoPreco(Sessao sessao, BigDecimal fator){
		return sessao.getPreco().multiply(fator);		
	}
}
