package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {


	private static Map<TipoDeEspetaculo, ICalculadoraPrecos> mapaEstrategias = new HashMap<TipoDeEspetaculo, ICalculadoraPrecos>();
	
	static {
		mapaEstrategias.put(TipoDeEspetaculo.CINEMA, new CalculadoraPrecoCinema());
		mapaEstrategias.put(TipoDeEspetaculo.SHOW, new CalculadoraPrecoShow());
		mapaEstrategias.put(TipoDeEspetaculo.BALLET, new CalculadoraPrecoBallet());
		mapaEstrategias.put(TipoDeEspetaculo.ORQUESTRA, new CalculadoraPrecoOrquestra());
	}

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = 
				mapaEstrategias.containsKey(sessao.getEspetaculo().getTipo()) ?
						mapaEstrategias.get(sessao.getEspetaculo().getTipo()).calcularPreco(sessao):
						sessao.getPreco();
		
		return calcularTotal(quantidade, preco);
	}

	private static BigDecimal calcularTotal(Integer quantidade, BigDecimal preco) {
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}