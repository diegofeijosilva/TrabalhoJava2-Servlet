package SuitTest;

import org.junit.Test;

import Controller.TipoProjetoController;
import Model.TipoProjeto;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import Util.Util;

public class TestTipoProjetoController extends TestCase {
	
	private TipoProjetoController controll = new TipoProjetoController();
	private TipoProjeto tipoProjeto;
	
	public void setUp(){
		try {
			super.setUp();
			tipoProjeto = new TipoProjeto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static TestSuite suite(){
		return new TestSuite(TestTipoProjetoController.class);
	}
	
	@Test
	public void testInsere(){
		
		assertFalse("DESCRI��O N�O PODE SER NULO", controll.insere(tipoProjeto));
		
			tipoProjeto.setDescricao("testetestetestetestetestetestetestetestetestetesteetestetesteetestetestetesteetestetestetesteetesteteste");
		assertFalse("DESCRI��O N�O PODE TER MAIS DE 50 CHARS", controll.insere(tipoProjeto));
		
		assertFalse("STATUS N�O PODE SER NULO",controll.insere(tipoProjeto));
		
			tipoProjeto.setDescricao("Teste, teste, 1234");
			tipoProjeto.setStatusTipo(true);
		
		assertFalse("DATA N�O PODE SER NULL",controll.insere(tipoProjeto));
		
			tipoProjeto.setDtCadastro(Util.stringParaDate("11/08"));
		
		assertFalse("DATA DEVE SER COMPLETA",controll.insere(tipoProjeto));
		
			tipoProjeto.setDtCadastro(Util.stringParaDate("31/02/2012"));
		
		assertFalse("TEST PARA VALIDAR DATA",controll.insere(tipoProjeto));	
		
			//Data v�lida
			tipoProjeto.setDtCadastro(Util.stringParaDate("01/01/2012"));
		
		assertTrue("TESTE INSER��O OK", controll.insere(tipoProjeto));
		
		// Pego o id do registro inserido
		tipoProjeto.setId(controll.lastReg().getId());
		
		// Test para ver se inseriu corretamente
		assertEquals(tipoProjeto.getDescricao(), controll.buscaPorId((long) tipoProjeto.getId()).getDescricao());
		
		// Deleta o registro inserido
		controll.exclui(tipoProjeto);
		
	}
	
	@Test
	public void testAtualiza(){
		
		// Insere registro para ser atualizado
		TipoProjeto tp = new TipoProjeto();
		tp.setDescricao("INSERE");
		tp.setDtCadastro(Util.stringParaDate("01/01/2012"));
		tp.setStatusTipo(false);
		
		assertTrue("INSERE REGISTRO A SER ATUALIZADO", controll.insere(tp));
		////////////////////////////////////////////////////////////////////
		
		// Atualiza Registro
		
			assertFalse("ID N�O PODE SER NULO", controll.atualiza(tipoProjeto));
		
		tipoProjeto.setId(controll.lastReg().getId()); // Pego o ID do �ltimo registro inserido
		
			assertFalse("DESCRI��O N�O PODE SER NULL", controll.atualiza(tipoProjeto));
		
		tipoProjeto.setDescricao("testetestetestetestetestetestetestetestetestetesteetestetesteetestetestetesteetestetestetesteetesteteste");
			assertFalse("DESCRI��O N�O PODE TER MAIS DE 50 CHARS", controll.atualiza(tipoProjeto));
		
		tipoProjeto.setDescricao("TESTE DE ATUALIZA��O");
			assertFalse("STATUS N�O PODE SER NULL", controll.atualiza(tipoProjeto));
			
			assertFalse("DATA N�O PODE SER NULL",controll.atualiza(tipoProjeto));
			
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08"));
		
			assertFalse("DATA DEVE SER COMPLETA",controll.atualiza(tipoProjeto));
		
		tipoProjeto.setDtCadastro(Util.stringParaDate("31/02/2012"));
		
			assertFalse("TEST PARA VALIDAR DATA",controll.atualiza(tipoProjeto));	
		
		//Data v�lida
		tipoProjeto.setDtCadastro(Util.stringParaDate("01/01/2012"));
		
		tipoProjeto.setStatusTipo(true);
			assertTrue("ATUALIZA��O PADR�O", controll.atualiza(tipoProjeto));
		
		//Test para confirmar atualiza��o
		assertEquals(tipoProjeto.getDescricao(), controll.buscaPorId((long) tipoProjeto.getId()).getDescricao());
		
		// Deleta o registro
		assertTrue("DELETA O REGISTRO", controll.exclui(tipoProjeto));
		
	}
	
	@Test
	public void testInsereOuAtualiza(){
		
		// Insere Registro para teste
		tipoProjeto.setDescricao("Teste, teste, 1234");
		tipoProjeto.setStatusTipo(true);
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
		
		assertTrue("TESTE INSER��O OK", controll.insereOuAtualiza(tipoProjeto));
		
		tipoProjeto.setId(controll.lastReg().getId()); // Pego o ID do registro inserido
		
		//Test para ver se inseriu corretamente
		assertEquals(tipoProjeto.getDescricao(), controll.buscaPorId((long) tipoProjeto.getId()).getDescricao());
		
		
		// Test atualiza
		// O ID j� foi definido quando peguei o registro inserido
		tipoProjeto.setDescricao("NOVO, NOVO");
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
		tipoProjeto.setStatusTipo(false);
		
		assertTrue("TESTE ATUALIZA��O OK", controll.insereOuAtualiza(tipoProjeto));
		
		//Test para ver se inseriu corretamente
		assertEquals(tipoProjeto.getDescricao(), controll.buscaPorId((long) tipoProjeto.getId()).getDescricao());
		
		// Deleta o registro
		assertTrue("DELETA O REGISTRO INSERIDO", controll.exclui(tipoProjeto));
	}
	
	@Test
	public void testExclui(){
		
		assertFalse("ID N�O PODE SER NULL", controll.exclui(tipoProjeto));
		
		//Insere Registro a ser exclu�do
		tipoProjeto.setDescricao("REGISTRO A SER EXCLU�DO");
		tipoProjeto.setStatusTipo(true);
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
		
		assertTrue("INSERE REGISTRO A SER EXCLUIDO", controll.insere(tipoProjeto));
		
		
		tipoProjeto.setId(controll.lastReg().getId()); // Pego o ID do registro inserido

		assertTrue("EXCLUS�O PADR�O", controll.exclui(tipoProjeto));
		
		// Verifica se foi excluido
		assertEquals(null, controll.buscaPorId( (long) tipoProjeto.getId()));
		
	}
	
	@Test
	public void testListaTodos(){
		
		//Insere Registro
		tipoProjeto.setDescricao("REGISTRO A SER EXCLU�DO");
		tipoProjeto.setStatusTipo(true);
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
		
		assertTrue("INSERE REGISTRO", controll.insere(tipoProjeto));
		
		assertEquals(controll.listaTodos().size(), 1);
		
		tipoProjeto.setId(controll.lastReg().getId()); // Pega o id do Registro inserido
		
		assertTrue("DELETA REGISTRO", controll.exclui(tipoProjeto));
	}
	
	@Test
	public void testBuscaPorId(){
		
		assertNull("ID N�O PODE SER NULL", controll.buscaPorId(null));
		
		//Insere Registro
		tipoProjeto.setDescricao("REGISTRO A SER EXCLU�DO");
		tipoProjeto.setStatusTipo(true);
		tipoProjeto.setDtCadastro(Util.stringParaDate("11/08/2012"));
				
		assertTrue("INSERE REGISTRO", controll.insere(tipoProjeto));
		
		tipoProjeto.setId(controll.lastReg().getId()); // Pego o ID do registro inserido
		
		assertNotNull("BUSCA PADR�O", controll.buscaPorId((long) tipoProjeto.getId()));
		
		assertTrue("DELETA O REGISTRO INSERIDO", controll.exclui(tipoProjeto));
	}

}
