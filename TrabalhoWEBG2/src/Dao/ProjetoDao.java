package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Projeto;
import Model.TipoProjeto;
import Util.Conexao;


public class ProjetoDao{
	
	Connection conn = null; // Gerencia a conexao
	Statement statement = null; // instrucao SQL
	
	public ProjetoDao() {
		
		try{
			
			Conexao conex = new Conexao();
			conn = conex.getConnection();
			
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO CONECTAR ???");
			sqlException.printStackTrace();
		}	
	
	}
	
	public Boolean insere(Projeto projeto){
		
		try{
			
			String sql = "insert into Projeto (idTipoProjeto, descricao, dtInicio, dtFim, statusProjeto, nivelDificuldade) values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement (sql);

			pstmt.setInt(1, projeto.getTipoProjeto().getId()); //pega o id do tipo do projeto
			pstmt.setString(2, projeto.getDescricao());
			pstmt.setDate(3, projeto.getDtInicio());
			pstmt.setDate(4, projeto.getDtFim());
			pstmt.setBoolean(5, projeto.getStatusProjeto());
			
			pstmt.setString(6, String.valueOf(projeto.getNivelDificuldade()));
	
			pstmt.execute();
			
			System.out.println("### PROJETO INSERIDO ###");
			
			return true;
			
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO INSERIR PROJETO ???");
			sqlException.printStackTrace();
			
			return false;
		}	
		
	}
	
	public Boolean exclui(Projeto projeto){
		
		try{
			
			PreparedStatement pstmt = conn.prepareStatement ("delete from Projeto where id=?");

			pstmt.setInt(1, projeto.getId());
			
			pstmt.execute();
			System.out.println("### PROJETO DELETADO ###");
			
			return true;
			
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO DELETAR PROJETO ???");
			sqlException.printStackTrace();
			
			return false;
		}	
		
		
	}
	public Boolean atualiza(Projeto projeto){
		
		try{
			
			String sql="update Projeto set idTipoProjeto=?, descricao=?, dtInicio=?, dtFim=?, statusProjeto=?, nivelDificuldade=? where id=?";
			
			PreparedStatement pstmt = conn.prepareStatement (sql);
			
			pstmt.setInt(1, projeto.getTipoProjeto().getId());
			pstmt.setString(2, projeto.getDescricao());
			pstmt.setDate(3, projeto.getDtInicio());
			pstmt.setDate(4, projeto.getDtFim());
			pstmt.setBoolean(5, projeto.getStatusProjeto());
			
			pstmt.setString(6, String.valueOf(projeto.getNivelDificuldade()));

			pstmt.setInt(7, projeto.getId());
			
			pstmt.execute();
			
			System.out.println("### PROJETO ATUALIZADO ###");
			
			return true;
			
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO ATUALIZAR PROJETO ???");
			sqlException.printStackTrace();
			
			return false;
		}	
		
	}
	
	
	public List<Projeto> listaTodos(){
		
		List<Projeto> listaProjeto = new ArrayList<Projeto>();
		
		TipoProjetoDao dao = new TipoProjetoDao();
		
		try{

			PreparedStatement pstmt = conn.prepareStatement ("select * from Projeto");
			ResultSet resultSet = pstmt.executeQuery();
			
			// Imprime as colunas
			while (resultSet.next()){
				
				Projeto projeto = new Projeto();
				TipoProjeto tipoProjeto = new TipoProjeto();
				
				//Busca o tipo projeto
				tipoProjeto = dao.buscaPorId((long) resultSet.getInt(2));
				
				projeto.setId(resultSet.getInt(1));
				projeto.setDescricao(resultSet.getString(3));
				projeto.setDtInicio(resultSet.getDate(4));
				projeto.setDtFim(resultSet.getDate(5));
				projeto.setStatusProjeto(resultSet.getBoolean(6));
				projeto.setNivelDificuldade(resultSet.getString(7).charAt(0));
				
				projeto.setTipoProjeto(tipoProjeto);
				
				listaProjeto.add(projeto);
				
			}
	
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO BUSCAR PROJETO ???");
			sqlException.printStackTrace();
		}	
		
		
		
		return listaProjeto;
	}
	
	
	
	public Projeto buscaPorId(Long id){
		
		Projeto projeto = new Projeto();
		TipoProjeto tipoProjeto = new TipoProjeto();
		
		TipoProjetoDao dao = new TipoProjetoDao();
		
		try{

			PreparedStatement pstmt = conn.prepareStatement ("select * from Projeto where id=?");

			pstmt.setLong(1, id);
			//pstmt.execute();
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()){
				
				projeto.setId(resultSet.getInt(1));
				
				//Busco o tipo de projeto atráves do fk
				tipoProjeto = dao.buscaPorId((long) resultSet.getInt(2));
				
				// Seta o tipo projeto
				projeto.setTipoProjeto(tipoProjeto);
				
				projeto.setDescricao(resultSet.getString(3));
				projeto.setDtInicio(resultSet.getDate(4));
				projeto.setDtFim(resultSet.getDate(5));
				projeto.setStatusProjeto(resultSet.getBoolean(6));
				projeto.setNivelDificuldade(resultSet.getString(7).charAt(0));
				
			}
	
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO PESQUISAR PROJETO ???");
			sqlException.printStackTrace();
		}	
		
		return projeto;
	}
	
	// Retorna o ultimo registro inserido
	public Projeto lastReg(){
		
		Projeto projeto = new Projeto();
		TipoProjeto tipoProjeto = new TipoProjeto();
		
		TipoProjetoDao dao = new TipoProjetoDao();
		
		try{

			PreparedStatement pstmt = conn.prepareStatement ("SELECT * FROM projeto order by id desc limit 1");

			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()){
				
				projeto.setId(resultSet.getInt(1));
				
				//Busco o tipo de projeto atráves do fk
				tipoProjeto = dao.buscaPorId((long) resultSet.getInt(2));
				
				projeto.setTipoProjeto(tipoProjeto);
				
				projeto.setDescricao(resultSet.getString(3));
				projeto.setDtInicio(resultSet.getDate(4));
				projeto.setDtFim(resultSet.getDate(5));
				projeto.setStatusProjeto(resultSet.getBoolean(6));
				projeto.setNivelDificuldade(resultSet.getString(7).charAt(0));
				
			}
	
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO BUSCAR ULTIMO REGISTRO ???");
			sqlException.printStackTrace();
		}	
		
		return projeto;
		
	}

}
