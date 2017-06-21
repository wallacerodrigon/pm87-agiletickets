package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculadoraPrecoCinema implements ICalculadoraPrecos {

	@Override
	public BigDecimal calcularPreco(Sessao sessao) {
		return CalculadorPrecoSessao.calcularPrecoTotalSessao(sessao, CINCO_PORCENTO, DEZ_PORCENTO);
	}
}
