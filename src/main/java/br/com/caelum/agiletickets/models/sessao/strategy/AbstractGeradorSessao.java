/**
 * 
 */
package br.com.caelum.agiletickets.models.sessao.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.caelum.agiletickets.models.Sessao;

/**
 * @author agil6790
 *
 */
public abstract class AbstractGeradorSessao implements IGeradorSessoesPeriodicidade {

	/* (non-Javadoc)
	 * @see br.com.caelum.agiletickets.models.sessao.strategy.IGeradorSessoesPeriodicidade#gerarSessoes(int, org.joda.time.LocalDate, org.joda.time.LocalDate, org.joda.time.LocalTime)
	 */
	@Override
	public List<Sessao> gerarSessoes(Espetaculo espetaculo, LocalDate inicio, LocalDate fim,
			LocalTime horario) {
		
		if (! isPeriodoValido(inicio, fim)){
			return new ArrayList<Sessao>();
		} else {
			return criarSessoes(espetaculo, inicio, fim, horario);	
			
		}
	}

	private List<Sessao> criarSessoes(Espetaculo espetaculo, LocalDate inicio,
			LocalDate fim, LocalTime horario) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		int qtd = calcularQuantidadeSessoes(inicio, fim);
		
		
		for(int i = 0; i < qtd; i++){
			DateTime dataSessao = inicio.toDateTimeAtStartOfDay().plusHours(horario.getHourOfDay());
			dataSessao = calcularProximoIntervalo(dataSessao, i);
			
			Sessao sessao = new Sessao();
			sessao.setDuracaoEmMinutos(1);
			sessao.setEspetaculo(espetaculo);
			sessao.setIngressosReservados(0);
			sessao.setInicio(dataSessao);
			sessao.setPreco(BigDecimal.ZERO);
			sessao.setTotalIngressos(10);
			
			sessoes.add(sessao);
			
		}
		
		return sessoes;
	}

	public abstract DateTime calcularProximoIntervalo(DateTime dataSessao, int offset);

	public abstract int calcularQuantidadeSessoes(LocalDate inicio, LocalDate fim);

	private boolean isPeriodoValido(LocalDate inicio, LocalDate fim) {
		// TODO Auto-generated method stub
		return fim.equals(inicio) ||  fim.isAfter(inicio);
	}	
}
