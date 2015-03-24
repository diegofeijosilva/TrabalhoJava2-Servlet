package SuitTest;

import java.sql.SQLException;

import org.junit.Test;

import Util.Conexao;
import Util.EstruturaBanco;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestEstruturaBanco extends TestCase {
	
	EstruturaBanco banco;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		banco = new EstruturaBanco();
	}
	
	public static TestSuite suite(){
		return new TestSuite(TestEstruturaBanco.class);
	}
	
	@Test
	public void testDropEstrutura() throws SQLException {
		
		assertTrue("CRIA A ESTRUTURA PARA SER DELETADA", banco.criaEstrutura());
		assertTrue("TEST DROP ESTRUTURA", banco.dropEstrutura());
			
	}
	
	@Test
	public void testCriaEstrutura() throws SQLException {
		
		assertTrue("DELETA ESTRUTURA PARA SER CRIADA NOVAMENTE", banco.dropEstrutura());
		assertTrue("TEST CRIA ESTRUTURA", banco.criaEstrutura());
		//assertFalse("ERRO AO TENTAR CRIAR NOVAMENTE A ESTRUTURA", banco.criaEstrutura());
			
	}

}
