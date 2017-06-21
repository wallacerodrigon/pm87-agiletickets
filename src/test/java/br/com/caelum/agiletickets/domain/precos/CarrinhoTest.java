package br.com.caelum.agiletickets.domain.precos;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.agiletickets.domain.Carrinho;

public class CarrinhoTest {

	@Test
	public void valorDaCompraDeveSerZeroQuandoCarrinhoEhNovo(){
		Carrinho c = new Carrinho();
		double total = c.getTotal();
		
		Assert.assertEquals(0, total, 2);
	}
}
