package br.org.meg.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.org.meg.dao.UtilDAO;

public class UtilDAOTest {
	private UtilDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = new UtilDAO();
	}

	@Test
	public void getNomeEstadoTest() {
		String nome = "Acre";
		assertEquals(dao.getNomeEstado(480), nome);
	}

	@Test
	public void getSiglaEstadoTest(){
		String sigla = "AC";
		assertEquals(dao.getSiglaEstado(1), sigla);
	}
	
	@Test
	public void getIdEstadoTest(){
		int id = 1;
		assertEquals(dao.getIdEstado("Acre"), id);
	}
	
	@Test
	public void getNomeSecaoTest(){
		String nome = "Número de empresas e outras organizações (Unidades)";
		assertEquals(dao.getNomeSecao(1), nome);
	}
	
	@Test
	public void getIdSecaoTest(){
		int id = 1;
		assertEquals(dao.getIdSecao("Número de empresas e outras organizações (Unidades)"), id);
	}
	
	@Test
	public void getNomeDescricaoTest(){
		String nome = "Agricultura, pecuária, produção florestal, pesca e aquicultura";
		assertEquals(dao.getNomeDescricao(1), nome);
	}
	
	@Test
	public void getIdDescricaoTest(){
		int id = 1;
		assertEquals(dao.getIdDescricao("Agricultura, pecuária, produção florestal, pesca e aquicultura"), id);
	}
}