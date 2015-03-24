package SuitTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import Util.Util;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestUtil extends TestCase {
	
	@Override
	public void setUp(){
		
	}
	
	public static TestSuite suite(){
		return new TestSuite(TestUtil.class);
	}
	
	@Test
	public void testStringParaDate(){
		
		
		assertNull("DATA DEVE SER COMPLETA", Util.stringParaDate("01/08"));
		
		assertNull("VERIFICA SE DATA É VÁLIDA", Util.stringParaDate("31/02/2012"));
		
		assertNotNull("DATA VÁLIDA", Util.stringParaDate("01/01/2012"));
		
	}
	
	@Test
	public void testVerificaDataString(){
		
		// PARÂMETRO DO TIPO STRING
		
		assertFalse("DATA DEVE ESTAR COMPLETA", Util.VerificaData("01/08"));
		
		assertFalse("VERIFICA SE DATA É VÁLIDA", Util.VerificaData("31/02/2012"));
		
		assertTrue("DATA VALIDA", Util.VerificaData("01/01/2012"));
		
	}
	
	@Test
	public void testVerificaDataDate(){
		
		// PARÂMETRO DO TIPO DATE
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		format.setLenient(false);
		java.sql.Date data = null;
		
		try {
			data = new java.sql.Date(format.parse("01/01/2012").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		assertTrue("DATA VALIDA", Util.VerificaData(data));
		
		
	}

}
