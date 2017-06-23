/**
 * 
 */
package br.com.caelum.agiletickets.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.agiletickets.models.Estabelecimento;

/**
 * @author agil6790
 *
 */
public class JPAEstabelecimentoDaoTest {

	private static EntityManagerFactory emf;
	private EntityManager manager;
	private JPAEstabelecimentoDao dao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("tests");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.manager = emf.createEntityManager();
		this.manager.getTransaction().begin();
		this.dao = new JPAEstabelecimentoDao(manager);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.manager.getTransaction().rollback();
		this.manager.close();
	}

	@AfterClass
	public static void afterClass() {
		emf.close();
	}

	@Test
	public void deveAdicionarUmEstabelecimento() {
		Estabelecimento novo = new Estabelecimento();
		novo.setNome("Novo	Estabelecimento	de	Testes");
		novo.setEndereco("Endereco	do	Estabelecimento	de	Teste");
		novo.setTemEstacionamento(true);
		dao.adiciona(novo);
		Estabelecimento adicionado = manager.find(Estabelecimento.class,
				novo.getId());
		Assert.assertEquals(adicionado, novo);
	}

}
