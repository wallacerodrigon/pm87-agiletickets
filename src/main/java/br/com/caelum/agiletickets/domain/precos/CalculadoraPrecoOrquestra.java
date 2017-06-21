package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculadoraPrecoOrquestra implements ICalculadoraPrecos {

	@Override
	public BigDecimal calcularPreco(Sessao sessao) {
		BigDecimal preco = CalculadorPrecoSessao.calcularPrecoTotalSessao(sessao, CINQUENTA_PORCENTO, VINTE_PORCENTO);

		if(sessao.getDuracaoEmMinutos() > ICalculadoraPrecos.UMA_HORA_MINUTOS){
			preco = preco.add(CalculadorPrecoSessao.getAumentoPreco(sessao, BigDecimal.valueOf(DEZ_PORCENTO)));
		}
		return preco;
	}

}
