package SuitTest;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {
	
	public static Test suite(){
		
		TestSuite testSuite = new TestSuite("Roda Todos os Testes");
		
		testSuite.addTest(TestConexao.suite()); //Teste de conexão
		testSuite.addTest(TestEstruturaBanco.suite()); // Teste Create e Drop da estrutura do banco
		testSuite.addTest(TestUtil.suite()); // Test da classe util
		
		testSuite.addTest(TestTipoProjetoDao.suite()); // Test TIPO PROJETO DAO
		testSuite.addTest(TestTipoProjetoController.suite()); // TEST TIPO PROJETO CONTROLLER
		testSuite.addTest(TestProjetoDao.suite()); // Test ProjetoDao
		testSuite.addTest(TestProjetoController.suite()); // Test ProjetoController
		
		return testSuite;
		
	}

}