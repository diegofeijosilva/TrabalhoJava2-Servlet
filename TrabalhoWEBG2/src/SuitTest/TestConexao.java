package SuitTest;

import java.sql.SQLException;
import org.junit.Test;

import Util.Conexao;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestConexao extends TestCase {
	
	private Conexao conexao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		conexao = new Conexao();
	}
	
	public static TestSuite suite(){
		return new TestSuite(TestConexao.class);
	}
	
	@Test
	public void testGetConnection() throws SQLException {
		
			assertNotNull("TEST RETORNA CONEXÃO",conexao.getConnection());
			
	}

}
