package SuitTest;

import java.sql.SQLException;

import org.junit.Test;

import Dao.ProjetoDao;
import Dao.TipoProjetoDao;
import Model.Projeto;
import Model.TipoProjeto;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import Util.Util;

public class TestProjetoDao extends TestCase {
	
	ProjetoDao dao = new ProjetoDao();
	TipoProjetoDao daoTipoProj = new TipoProjetoDao();
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
			
			daoTipoProj.insere(tipoProjeto);
			
			tipoProjeto.setId(daoTipoProj.lastReg().getId()); // Pega o ID do Tipo Projeto inserido
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static TestSuite suite(){
		return new TestSuite(TestProjetoDao.class);
	}
	
	@Test
	public void testInsere(){
		
			projeto.setDescricao("Teste");
			projeto.setTipoProjeto(tipoProjeto);
			projeto.setStatusProjeto(true);
			projeto.setNivelDificuldade('B');
			
			assertTrue("TEST INSERE", dao.insere(projeto));

			// Verifica se foi inserido corretamente
			assertEquals(projeto.getDescricao(), dao.lastReg().getDescricao());
			
			// Deleta o Projeto inserido
			assertTrue("DELETA O PROJETO INSERIDO", dao.exclui(dao.lastReg()));
			
			// Deleta o Tipo Projeto Inserido
			assertTrue("DELETA TIPO PROJETO", daoTipoProj.exclui(tipoProjeto));
	
	}
	
	@Test
	public void testAtualiza(){
		
		// Insere Projeto a ser atualizado
		projeto.setDescricao("PROJETO INSERIDO");
		projeto.setTipoProjeto(tipoProjeto);
		projeto.setStatusProjeto(true);
		projeto.setNivelDificuldade('B');
		
		assertTrue("TEST INSERE", dao.insere(projeto));
		//////////////////////////////////////////////////
		
		projeto.setId(dao.lastReg().getId()); // Busco o id do ultimo registro inserido
		projeto.setDescricao("PROJETO ATUALIZADO");
		projeto.setStatusProjeto(false);
		projeto.setTipoProjeto(tipoProjeto);
		
		assertTrue("TEST ATUALIZA REGISTRO", dao.atualiza(projeto));
		
		//Verifica se o registro foi atualizado
		assertEquals(projeto.getDescricao(), dao.lastReg().getDescricao());
		
		// Deleta o Projeto inserido
		assertTrue("DELETA O PROJETO INSERIDO", dao.exclui(dao.lastReg()));
					
		// Deleta o Tipo Projeto Inserido
		assertTrue("DELETA TIPO PROJETO", daoTipoProj.exclui(tipoProjeto));
		
	}
	
	@Test
	public void testExclui(){
		
		// Insere Projeto a ser atualizado
		projeto.setDescricao("PROJETO INSERIDO");
		projeto.setTipoProjeto(tipoProjeto);
		projeto.setStatusProjeto(true);
		projeto.setNivelDificuldade('B');
				
		assertTrue("TEST INSERE", dao.insere(projeto));
		//////////////////////////////////////////////////
		
		projeto.setId(dao.lastReg().getId()); // Busco o ID do ultimo registro inserido
		
		assertTrue("TEST DELETA O REGISTRO", dao.exclui(projeto));
		
		//Verifica se o registro foi excluído
		assertEquals(null, dao.lastReg().getDescricao());
		
		
		// Deleta o Tipo Projeto Inserido
		assertTrue("DELETA TIPO PROJETO", daoTipoProj.exclui(tipoProjeto));
		
		
	}
	
	@Test
	public void testBuscaPorId(){
	
		// Insere Projeto a ser atualizado
		projeto.setDescricao("PROJETO INSERIDO");
		projeto.setTipoProjeto(tipoProjeto);
		projeto.setStatusProjeto(true);
		projeto.setNivelDificuldade('B');
				
		assertTrue("INSERE REGISTRO PARA BUSCA", dao.insere(projeto));
		//////////////////////////////////////////////////

		projeto.setId(dao.lastReg().getId()); // Pega o ID do ultimo registro inserido
		
		//Verifica se foi inserido corretamente
		assertEquals(projeto.getDescricao(), dao.buscaPorId((long) projeto.getId()).getDescricao());
		
		// Deleta o Projeto inserido
		assertTrue("DELETA O PROJETO INSERIDO", dao.exclui(projeto));
							
		// Deleta o Tipo Projeto Inserido
		assertTrue("DELETA TIPO PROJETO", daoTipoProj.exclui(tipoProjeto));
		
	}
	
	@Test
	public void testListaTodos(){
		
		// Insere Projeto a ser atualizado
		projeto.setDescricao("PROJETO INSERIDO");
		projeto.setTipoProjeto(tipoProjeto);
		projeto.setStatusProjeto(true);
		projeto.setNivelDificuldade('B');
						
		assertTrue("INSERE REGISTRO PARA BUSCA", dao.insere(projeto));
		//////////////////////////////////////////////////
		
		projeto.setId(dao.lastReg().getId()); // Pega o ID do ultimo registro inserido
		
		assertEquals(dao.listaTodos().size(), 1);
		
		// Deleta o Projeto inserido
		assertTrue("DELETA O PROJETO INSERIDO", dao.exclui(projeto));
									
		// Deleta o Tipo Projeto Inserido
		assertTrue("DELETA TIPO PROJETO", daoTipoProj.exclui(tipoProjeto));
		
	}

}
