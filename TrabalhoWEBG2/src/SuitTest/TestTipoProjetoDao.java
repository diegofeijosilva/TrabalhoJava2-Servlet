package SuitTest;

import java.sql.SQLException;

import org.junit.Test;

import Dao.TipoProjetoDao;
import Model.TipoProjeto;
import Util.Util;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestTipoProjetoDao extends TestCase {
	
	TipoProjetoDao dao = new TipoProjetoDao();
	TipoProjeto tipoProjeto;
	
	@Override
	public void setUp(){
		
		try {
			super.setUp();
			tipoProjeto = new TipoProjeto(); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static TestSuite suite(){
		return new TestSuite(TestTipoProjetoDao.class);
	}
	
	@Test
	public void testInsere() throws SQLException {
		
			tipoProjeto.setDescricao("TESTE INSERT");
			tipoProjeto.setStatusTipo(true);
			tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
			
			assertTrue("TEST INSERE", dao.insere(tipoProjeto));

			// Verifica se foi inserido corretamente
			// A comparação é feita pelo último registro inserido
			assertEquals(tipoProjeto.getDescricao(), dao.lastReg().getDescricao());
			
			// Deleta o registro inserido de teste
			assertTrue("DELETA O REGISTRO", dao.exclui(dao.lastReg()));
		
	}
	
	@Test
	public void testAtualiza(){
		
		tipoProjeto.setDescricao("TESTE PARA ATUALIZA");
		tipoProjeto.setStatusTipo(true);
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
		
		assertTrue("INSERE REGISTRO PARA SER ATUALIZADO", dao.insere(tipoProjeto));
		
		//Atualiza o Registro
		tipoProjeto.setId(dao.lastReg().getId()); // pega o id do último registro
		tipoProjeto.setDescricao("ATUALIZADO");
		tipoProjeto.setStatusTipo(true);
		assertTrue("ATUALIZA REGISTRO", dao.atualiza(tipoProjeto));
		
		// Verifica se foi inserido corretamente
		// Pega o último registro inserido e verifica se foi atualizado
		assertEquals(tipoProjeto.getDescricao(), dao.lastReg().getDescricao());
		
		// Deleta o registro inserido e atualizado
		assertTrue("DELETA O REGISTRO", dao.exclui(dao.lastReg()));
	}
	
	@Test
	public void testExclui(){
		
		tipoProjeto.setDescricao("TESTE EXCLUSÃO");
		tipoProjeto.setStatusTipo(true);
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
		
		assertTrue("TEST INSERE", dao.insere(tipoProjeto));
		
		//Deleta o Registro
		tipoProjeto.setId(dao.lastReg().getId()); // Pega o id do último registro inserido
		assertTrue("DELETA PELO ID", dao.exclui(tipoProjeto));
		
		//Verifica se foi deletado
		//assertEquals(null, dao.buscaPorId((long) 2).getDescricao());

		// Verifica se foi deletado
		// Verificação é feita pelo ID que foi retornado antes de deletar
		assertEquals(null, dao.buscaPorId( (long) tipoProjeto.getId()).getDescricao());

	}
	
	@Test
	public void testBuscaPorId(){
		
		tipoProjeto.setDescricao("TESTE BUSCA POR ID");
		tipoProjeto.setStatusTipo(false);
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
		
		assertTrue("INSERE REGISTRO", dao.insere(tipoProjeto));
		
		// Busca pelo id
		tipoProjeto.setId(dao.lastReg().getId()); // Pego o ID do último registro inserido
		
		assertEquals(tipoProjeto.getDescricao(), dao.buscaPorId((long) tipoProjeto.getId()).getDescricao());
		

		assertTrue("DELETA O REGISTRO", dao.exclui(dao.lastReg()));
	}
	
	@Test
	public void testListaTodos(){
		
		tipoProjeto.setDescricao("NOVO TESTE");
		tipoProjeto.setStatusTipo(true);
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
		
		assertTrue("INSERE NOVO REGISTRO", dao.insere(tipoProjeto));
		
		// Retorno da lista
		assertEquals(dao.listaTodos().size(), 1);
		
		assertTrue("DELETA O REGISTRO", dao.exclui(dao.lastReg()));
		
	}

}
