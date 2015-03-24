package SuitTest;

import org.junit.Test;

import Controller.ProjetoController;
import Controller.TipoProjetoController;
import Dao.ProjetoDao;
import Dao.TipoProjetoDao;
import Model.Projeto;
import Model.TipoProjeto;
import Util.Util;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestProjetoController extends TestCase {
	
	ProjetoController projControl = new ProjetoController();
	TipoProjetoController tpControl = new TipoProjetoController();
	
	Projeto projeto;
	TipoProjeto tipoProjeto;
	
	@Override
	public void setUp(){
		
		try {
			super.setUp();
			projeto = new Projeto();
			
			tipoProjeto = new TipoProjeto();
			
			// Insere um tipo projeto a ser utilizado
			tipoProjeto.setDescricao("TESTE");
			tipoProjeto.setDtCadastro(Util.stringParaDate("01/01/2012"));
			tipoProjeto.setStatusTipo(true);
			
			tpControl.insere(tipoProjeto);
			
			tipoProjeto.setId(tpControl.lastReg().getId()); // Pega o ID do Tipo Projeto inserido
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static TestSuite suite(){
		return new TestSuite(TestProjetoController.class);
	}
	
	@Test
	public void testInsere(){
		
			assertFalse("TEST DESCRIÇÃO NÃO PODE SER NULL", projControl.insere(projeto));
			
		projeto.setDescricao("NOVO PROJETO");
			
			assertFalse("TEST TIPO PROJETO NÃO PODE SER NULL", projControl.insere(projeto));
			
		// Seta o Tipo Projeto
		projeto.setTipoProjeto(tipoProjeto);
			
			assertFalse("TEST STATUS PROJETO NÃO PODE SER NULL. TRUE OU FALSE", projControl.insere(projeto));
			
		projeto.setStatusProjeto(true);
			
			assertFalse("TEST NIVEL DIFICULADE NÃO PODE SER NULL", projControl.insere(projeto));
			
		projeto.setNivelDificuldade('Z');
			
			assertFalse("TEST NIVEL DIFICULADE NÃO PODE SER DIFERENTE DE A, B OU C", projControl.insere(projeto));
			
		projeto.setNivelDificuldade('A');
		
		//Testa DATA INICIO
		projeto.setDtInicio(Util.stringParaDate("11/08"));
		
			assertFalse("DATA DEVE SER COMPLETA", projControl.insere(projeto));
		
		//Testa DATA INICIO
		projeto.setDtInicio(Util.stringParaDate("31/02/2012"));
			
			assertFalse("DATA DEVE SER COMPLETA", projControl.insere(projeto));
			
		//Testa DATA FIM
		projeto.setDtFim(Util.stringParaDate("11/08"));
			
				assertFalse("DATA DEVE SER COMPLETA", projControl.insere(projeto));
			
		//Testa DATA FIM
		projeto.setDtFim(Util.stringParaDate("31/02/2012"));
				
				assertFalse("DATA DEVE SER COMPLETA", projControl.insere(projeto));
		
		projeto.setDtInicio(Util.stringParaDate("11/08/2012"));
		projeto.setDtFim(Util.stringParaDate("12/12/2012"));
		
			assertTrue("INSERÇÃO PADRÃO. OK", projControl.insere(projeto));
	
	projeto.setId(projControl.lastReg().getId()); // Busco o ID do projeto Inserido
	
	// Verifica se foi inserido corretamente
	assertEquals(projeto.getDescricao(), projControl.lastReg().getDescricao());
	
	//Deleta Tipo Projeto
	assertTrue("DELETA TIPO PROJETO", tpControl.exclui(tipoProjeto));
	
	//Deleta o Projeto Inserido
	assertTrue("DELETA PROJETO", projControl.exclui(projeto));
	
	}
	
	@Test
	public void testAtualiza(){
		
		// Insere Projeto para ser atualizado
		projeto.setDescricao("PROJETO INSERIDO");
		projeto.setNivelDificuldade('A');
		projeto.setStatusProjeto(true);
		projeto.setTipoProjeto(tipoProjeto);
		projeto.setDtInicio(Util.stringParaDate("01/01/2012"));
		projeto.setDtFim(Util.stringParaDate("11/08/2012"));
		
		assertTrue("INSERE PROJETO", projControl.insere(projeto));
		////////////////////////////////////////////
		
		projeto.setId(projControl.lastReg().getId()); // Busca o ID do registro inserido
		
		Projeto proj = new Projeto(); // Objeto será atualizado
		
			assertFalse("TEST ID NÃO PODE SER NULL", projControl.atualiza(proj));
		
		proj.setId(projeto.getId());
		
			assertFalse("TEST DESCRIÇÃO NÃO PODE SER NULL", projControl.atualiza(proj));
		
		proj.setDescricao("PROJETO ATUALIZADO");
		
			assertFalse("TEST TIPO PROJETO NÃO PODE SER NULL", projControl.atualiza(proj));
		
		proj.setTipoProjeto(projeto.getTipoProjeto());
		
			assertFalse("TEST STATUS PROJETO NÃO PODE SER NULL. TRUE OU FALSE", projControl.atualiza(proj));
		
		proj.setStatusProjeto(true);
		
			assertFalse("TEST NIVEL DIFICULADE NÃO PODE SER NULL", projControl.atualiza(proj));
		
		proj.setNivelDificuldade('Z');
		
			assertFalse("TEST NIVEL DIFICULADE NÃO PODE SER DIFERENTE DE A, B OU C", projControl.atualiza(proj));
		
		proj.setNivelDificuldade('A');
		
		
		//Testa DATA INICIO
		proj.setDtInicio(Util.stringParaDate("11/08"));
				
				assertFalse("DATA DEVE SER COMPLETA", projControl.insere(proj));
				
		//Testa DATA INICIO
		proj.setDtInicio(Util.stringParaDate("31/02/2012"));
					
				assertFalse("DATA DEVE SER COMPLETA", projControl.insere(proj));
					
		//Testa DATA FIM
		proj.setDtFim(Util.stringParaDate("11/08"));
					
				assertFalse("DATA DEVE SER COMPLETA", projControl.insere(proj));
					
		//Testa DATA FIM
		proj.setDtFim(Util.stringParaDate("31/02/2012"));
						
				assertFalse("DATA DEVE SER COMPLETA", projControl.insere(proj));
				
		proj.setDtInicio(Util.stringParaDate("11/08/2012"));
		proj.setDtFim(Util.stringParaDate("12/12/2012"));
		
		
	assertTrue("ATUALIZAÇÃO PADRÃO. OK", projControl.atualiza(proj));
	
	// Verifica se foi atualizado corretamente
	assertEquals(proj.getDescricao(), projControl.lastReg().getDescricao());
	
	//Deleta Tipo Projeto
	assertTrue("DELETA TIPO PROJETO", tpControl.exclui(tipoProjeto));
		
	//Deleta o Projeto Inserido
	assertTrue("DELETA PROJETO", projControl.exclui(proj));
		
	}
	
	@Test
	public void testInsereOuAtualiza(){
		
		
		assertFalse("TEST DESCRIÇÃO NÃO PODE SER NULL", projControl.insereOuAtualiza(projeto));
		
			projeto.setDescricao("NOVO PROJETO");
		
		assertFalse("TEST TIPO PROJETO NÃO PODE SER NULL", projControl.insereOuAtualiza(projeto));
		
			// Seta o TIPOPROJETO
			projeto.setTipoProjeto(tipoProjeto);
		
		assertFalse("TEST STATUS PROJETO NÃO PODE SER NULL. TRUE OU FALSE", projControl.insereOuAtualiza(projeto));
		
			projeto.setStatusProjeto(true);
		
		assertFalse("TEST NIVEL DIFICULADE NÃO PODE SER NULL", projControl.insereOuAtualiza(projeto));
		
			projeto.setNivelDificuldade('Z');
		
		assertFalse("TEST NIVEL DIFICULADE NÃO PODE SER DIFERENTE DE A, B OU C", projControl.insereOuAtualiza(projeto));
		
			projeto.setNivelDificuldade('A');
			
			projeto.setDtInicio(Util.stringParaDate("01/01/2012"));
			projeto.setDtFim(Util.stringParaDate("11/08/2012"));
		
		assertTrue("INSERÇÃO PADRÃO. OK", projControl.insereOuAtualiza(projeto));
		
		// Verifica se foi inserido
		assertEquals(projeto.getDescricao(), projControl.lastReg().getDescricao());
	
			projeto.setId(projControl.lastReg().getId()); // Busca o ID do registro inserido
	
			projeto.setDescricao("REGISTRO ATUALIZADO");
			
		assertTrue("ATUALIZAÇÃO PADRÃO. OK", projControl.insereOuAtualiza(projeto));
		
		// Verifica se foi atualizado
		assertEquals(projeto.getDescricao(), projControl.lastReg().getDescricao());
		
		//Deleta Tipo Projeto
		assertTrue("DELETA TIPO PROJETO", tpControl.exclui(tipoProjeto));
			
		//Deleta o Projeto Inserido
		assertTrue("DELETA PROJETO", projControl.exclui(projeto));
	}
	
	@Test
	public void testExclui(){
		
		// Insere Projeto para ser excluido
		projeto.setDescricao("PROJETO INSERIDO");
		projeto.setNivelDificuldade('A');
		projeto.setStatusProjeto(true);
		projeto.setTipoProjeto(tipoProjeto);
		projeto.setDtInicio(Util.stringParaDate("01/01/2012"));
		projeto.setDtFim(Util.stringParaDate("11/08/2012"));
				
		assertTrue("INSERE PROJETO", projControl.insere(projeto));
		////////////////////////////////////////////
				
		assertFalse("TEST ID NÃO PODE SER NULL", projControl.exclui(projeto));
		
		projeto.setId(projControl.lastReg().getId()); // Busca o ID do registro inserido
		
		assertTrue("TEST EXCLUSÃO PADRÃO", projControl.exclui(projeto));
		
		//Verifica se o registro foi excluído
		assertEquals(null, projControl.lastReg().getDescricao());
		
		//Deleta Tipo Projeto
		assertTrue("DELETA TIPO PROJETO", tpControl.exclui(tipoProjeto));
		
	}
	
	@Test
	public void testListaTodos(){
		
		// Insere Projeto para ser excluido
		projeto.setDescricao("PROJETO INSERIDO");
		projeto.setNivelDificuldade('A');
		projeto.setStatusProjeto(true);
		projeto.setTipoProjeto(tipoProjeto);
		projeto.setDtInicio(Util.stringParaDate("01/01/2012"));
		projeto.setDtFim(Util.stringParaDate("11/08/2012"));
						
		assertTrue("INSERE PROJETO", projControl.insere(projeto));
		////////////////////////////////////////////
		
		projeto.setId(projControl.lastReg().getId()); // Busca o ID do registro inserido
		
		assertEquals(projControl.listaTodos().size(), 1);
		
		//Deleta Tipo Projeto
		assertTrue("DELETA TIPO PROJETO", tpControl.exclui(tipoProjeto));
					
		//Deleta o Projeto Inserido
		assertTrue("DELETA PROJETO", projControl.exclui(projeto));

	}
	
	@Test
	public void testBuscaPorId(){

		// Insere Projeto para ser excluido
		projeto.setDescricao("PROJETO INSERIDO");
		projeto.setNivelDificuldade('A');
		projeto.setStatusProjeto(true);
		projeto.setTipoProjeto(tipoProjeto);
		projeto.setDtInicio(Util.stringParaDate("01/01/2012"));
		projeto.setDtFim(Util.stringParaDate("11/08/2012"));
								
		assertTrue("INSERE PROJETO", projControl.insere(projeto));
		////////////////////////////////////////////
				
		projeto.setId(projControl.lastReg().getId()); // Busca o ID do registro inserido
	
		assertNull("TEST ID MENOR QUE 0",projControl.buscaPorId((long) -5));
	
		assertNull("TEST ID NULL",projControl.buscaPorId(null));
	
		// Test de busca por ID
		assertEquals(projeto.getDescricao(), projControl.buscaPorId((long) projeto.getId()).getDescricao());
		
		
		//Deleta Tipo Projeto
		assertTrue("DELETA TIPO PROJETO", tpControl.exclui(tipoProjeto));
							
		//Deleta o Projeto Inserido
		assertTrue("DELETA PROJETO", projControl.exclui(projeto));
		
	}

}
