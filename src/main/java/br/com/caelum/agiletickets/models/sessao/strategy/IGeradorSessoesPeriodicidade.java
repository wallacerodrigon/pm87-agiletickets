package br.com.caelum.agiletickets.models.sessao.strategy;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.caelum.agiletickets.models.Sessao;

public interface IGeradorSessoesPeriodicidade {

	List<Sessao> gerarSessoes(Espetaculo espetaculo, LocalDate inicio, LocalDate fim, LocalTime horario) throws Exception;
	
}
