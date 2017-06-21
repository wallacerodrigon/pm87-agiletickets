package br.com.caelum.agiletickets.models;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;

import br.com.caelum.agiletickets.models.sessao.strategy.AbstractGeradorSessao;
import br.com.caelum.agiletickets.models.sessao.strategy.IGeradorSessoesPeriodicidade;

public enum Periodicidade {
	
	DIARIA(
			new AbstractGeradorSessao() {

				@Override
				public DateTime calcularProximoIntervalo(DateTime dataSessao, int offset) {
					// TODO Auto-generated method stub
					return dataSessao.plusDays(offset);
				}

				@Override
				public int calcularQuantidadeSessoes(LocalDate inicio, LocalDate fim) {
					// TODO Auto-generated method stub
					return Days.daysBetween(inicio, fim).getDays()+1;
				}
				
			}		
	), 
	SEMANAL(
			new AbstractGeradorSessao() {

				@Override
				public DateTime calcularProximoIntervalo(DateTime dataSessao, int offset) {
					// TODO Auto-generated method stub
					return dataSessao.plusWeeks(offset);
				}

				@Override
				public int calcularQuantidadeSessoes(LocalDate inicio, LocalDate fim) {
					// TODO Auto-generated method stub
					return Weeks.weeksBetween(inicio, fim).getWeeks() + 1;
				}
				
			}
	);
	
	private IGeradorSessoesPeriodicidade gerador;
	
	private Periodicidade(IGeradorSessoesPeriodicidade gerador){
		this.setGerador(gerador);
		
	}

	public IGeradorSessoesPeriodicidade getGerador() {
		return gerador;
	}

	public void setGerador(IGeradorSessoesPeriodicidade gerador) {
		this.gerador = gerador;
	}
}


