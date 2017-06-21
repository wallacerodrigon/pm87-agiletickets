package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public interface ICalculadoraPrecos {

	public static final double DEZ_PORCENTO = 0.10;
	public static final double CINCO_PORCENTO = 0.05;
	public static final double VINTE_PORCENTO = 0.20;
	public static final double CINQUENTA_PORCENTO = 0.50;
	public static final double UMA_HORA_MINUTOS = 60;
	
	
	
	BigDecimal calcularPreco(Sessao sessao);
}
