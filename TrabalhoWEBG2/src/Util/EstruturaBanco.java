package Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EstruturaBanco {
	
	Connection conn = null; // Gerencia a conexao
	Statement statement = null; // instrucao SQL
	
	public boolean criaEstrutura(){
		
		//Deleta Estrutura caso esteja em memória
		dropEstrutura();
		
		Conexao conex = new Conexao();

		try{
			conn = conex.getConnection();
			statement = conn.createStatement();

			statement.executeUpdate("CREATE TABLE TipoProjeto ( id INTEGER IDENTITY, descricao VARCHAR(256), dtCadastro date, statusTipo BOOLEAN)");
			statement.executeUpdate("CREATE TABLE Projeto ( id INTEGER IDENTITY, idTipoProjeto INTEGER, descricao VARCHAR(256), dtInicio date, dtFim date, statusProjeto BOOLEAN, nivelDificuldade CHAR)");
			
			statement.close();
			
		}catch (SQLException sqlException) {
			System.out.println("Erro ao Criar as Tabelas no banco");
			sqlException.printStackTrace();
			return false;
		}
	
	return true;	
	}
	
	public boolean dropEstrutura(){
		
		Conexao conex = new Conexao();

		try{
			conn = conex.getConnection();
			statement = conn.createStatement();
			
			statement.executeUpdate("DROP TABLE TipoProjeto IF EXISTS");
			statement.executeUpdate("DROP TABLE Projeto IF EXISTS");
	
			statement.close();
			
		}catch (SQLException sqlException) {
			System.out.println("Erro ao Deletar Estrutura.");
			sqlException.printStackTrace();
			return false;
		}

	return true;	
	}

}
