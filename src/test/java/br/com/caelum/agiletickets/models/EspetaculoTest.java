package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.agiletickets.models.sessao.strategy.Espetaculo;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	/**
	 * para uso com TDD: 
	 * deveCriarSessao quando a data inicio e data fim forem iguais para periodicidade DIARIA;
	 * deveCriarSessao quando a data inicio e data fim forem distintas para periodicidade DIARIA;
	 * deveCriarSessao quando a data inicio e data fim forem iguais para periodicidade SEMANAL;
	 * deveCriarSessao quando a data inicio e data fim forem distintas para periodicidade SEMANAL;
	 * naoDeveCriarSessao quando a data fim for menor que a data inicio DIARIA
	 * naoDeveCriarSessao quando a data fim for menor que a data inicio SEMANAL
	 */
	
	@Test
	public void deveCriarSessaoDiariaInicioFimIguais(){
		LocalDate dataInicio = new LocalDate();
		LocalDate dataFim    = new LocalDate();
		LocalTime hora       = org.joda.time.LocalTime.parse("20:00:00");
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		
		Espetaculo showIvete =new Espetaculo();
		List<Sessao> sessoes = showIvete.criaSessoes(dataInicio, dataFim, hora, periodicidade);
		
		Assert.assertEquals(sessoes.size(), 1);
		Assert.assertEquals(sessoes.get(0).getInicio().toDateTime(), dataInicio.toDateTime(hora));
		Assert.assertEquals(sessoes.get(0).getHora(), "20:00");
		Assert.assertEquals(sessoes.get(0).getEspetaculo(), showIvete);
		
	}
	
	@Test
	public void deveCriarSessaoDiariaInicioFimDistintas(){
		LocalDate dataInicio = new LocalDate();
		LocalDate dataFim    = new LocalDate(dataInicio.plusDays(6));
		LocalTime hora       = org.joda.time.LocalTime.parse("21:00:00");
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		
		Espetaculo showIvete =new Espetaculo();
		List<Sessao> sessoes = showIvete.criaSessoes(dataInicio, dataFim, hora, periodicidade);
		
		Assert.assertEquals(sessoes.size(), 7);
		Assert.assertEquals(sessoes.get(0).getHora(), "21:00");
		Assert.assertEquals(sessoes.get(0).getEspetaculo(), showIvete);		
		Assert.assertFalse(sessoes.get(0).getInicio().equals(sessoes.get(1).getInicio()));
		Assert.assertEquals(1, Days.daysBetween(sessoes.get(0).getInicio(), sessoes.get(1).getInicio()).getDays());
	}	
	
	@Test
	public void deveCriarSessaoSemanalInicioFimIguais(){
		LocalDate dataInicio = new LocalDate();
		LocalDate dataFim    = new LocalDate();
		LocalTime hora       = org.joda.time.LocalTime.parse("21:00:00");
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		
		Espetaculo showIvete =new Espetaculo();
		List<Sessao> sessoes = showIvete.criaSessoes(dataInicio, dataFim, hora, periodicidade);
		
		Assert.assertEquals(sessoes.size(), 1);
		
	}	
	
	@Test
	public void deveCriarSessaoSemanalInicioFimDistintas(){
		LocalDate dataInicio = new LocalDate();
		LocalDate dataFim    = new LocalDate(dataInicio.plusDays(20));
		LocalTime hora       = org.joda.time.LocalTime.parse("21:00:00");
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		
		Espetaculo showIvete =new Espetaculo();
		List<Sessao> sessoes = showIvete.criaSessoes(dataInicio, dataFim, hora, periodicidade);
		
		Assert.assertEquals(sessoes.size(), 3);
		Assert.assertFalse(sessoes.get(0).getInicio().equals(sessoes.get(1).getInicio()));
		Assert.assertEquals(7, Days.daysBetween(sessoes.get(0).getInicio(), sessoes.get(1).getInicio()).getDays());
		
	}		
	
	@Test
	public void naoDeveCriarSessaoDiariaComDataFimMaiorDataInicio(){
		LocalDate dataInicio    = new LocalDate(new LocalDate().plusDays(2));
		LocalDate dataFim = new LocalDate();
		LocalTime hora       = org.joda.time.LocalTime.parse("21:00:00");
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		
		Espetaculo showIvete =new Espetaculo();
		List<Sessao> sessoes = showIvete.criaSessoes(dataInicio, dataFim, hora, periodicidade);
		
		Assert.assertEquals(sessoes.size(), 0);
	}		
	
	@Test
	public void naoDeveCriarSessaoSemanalComDataFimMaiorDataInicio(){
		LocalDate dataInicio    = new LocalDate(new LocalDate().plusDays(2));
		LocalDate dataFim = new LocalDate();
		LocalTime hora       = org.joda.time.LocalTime.parse("21:00:00");
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		
		Espetaculo showIvete =new Espetaculo();
		List<Sessao> sessoes = showIvete.criaSessoes(dataInicio, dataFim, hora, periodicidade);
		
		Assert.assertEquals(sessoes.size(), 0);
	}	
}
