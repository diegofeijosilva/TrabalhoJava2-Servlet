package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	    private static String JDBC_DRIVER = "org.hsqldb.jdbcDriver";  
	    private static String DATABASE_URL = "jdbc:hsqldb:file:c:/bd/banco.bd";   
	    
	    private Connection conn = null; 
	      
	    public Connection getConnection() throws SQLException{  

	        try {  
	            Class.forName(JDBC_DRIVER);  
	            conn = DriverManager.getConnection( DATABASE_URL, "sa", ""); 
	            System.out.println("Conectado: " + conn);
	            return conn;
	            
	        } catch (ClassNotFoundException e){  
	            throw new SQLException(e.getMessage());   
	        }        
	        
	    }     

}
