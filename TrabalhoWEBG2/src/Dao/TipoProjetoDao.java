package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.TipoProjeto;
import Util.Conexao;

public class TipoProjetoDao {
	
	Connection conn = null; // Gerencia a conexao
	Statement statement = null; // instrucao SQL
	
	public TipoProjetoDao() {
		
		try{
			
			Conexao conex = new Conexao();
			conn = conex.getConnection();
			
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO CONECTAR ???");
			sqlException.printStackTrace();
		}	
	}
	
	public Boolean insere(TipoProjeto tipoProjeto){
		
		try{
			
			String sql = "insert into TipoProjeto (descricao, dtCadastro, statusTipo) values (?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement (sql);

			pstmt.setString(1, tipoProjeto.getDescricao());
			pstmt.setDate(2, tipoProjeto.getDtCadastro());
			pstmt.setBoolean(3, tipoProjeto.getStatusTipo());
			
			pstmt.execute();
			
			System.out.println("### TIPO PROJETO INSERIDO COM SUCESSO ###");
			
			return true;
			
			
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO INSERIR TIPO PROJETO ???");
			sqlException.printStackTrace();
			
			return false;
		}	
		
	}
	
	public Boolean exclui(TipoProjeto tipoProjeto){
		
		try{
			
			PreparedStatement pstmt = conn.prepareStatement ("delete from TipoProjeto where id=?");

			pstmt.setInt(1, tipoProjeto.getId());
			
			pstmt.execute();
			System.out.println("### TIPO PROJETO DELETADO ###");
			
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO DELETAR TIPO PROJETO ???");
			sqlException.printStackTrace();
			return false;
		}	
		
		return true;
	}
	
	public Boolean atualiza(TipoProjeto tipoProjeto){
		
		try{
			
			String sql="update TipoProjeto set descricao=?, dtCadastro=?, statusTipo=? where id=?";
			
			PreparedStatement pstmt = conn.prepareStatement (sql);

			pstmt.setString(1, tipoProjeto.getDescricao());
			pstmt.setDate(2, tipoProjeto.getDtCadastro());
			pstmt.setBoolean(3, tipoProjeto.getStatusTipo());
			
			pstmt.setInt(4, tipoProjeto.getId());
			
			pstmt.execute();
			
			System.out.println("### TIPO PROJETO ATUALIZADO ###");
			
			return true;
			
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO ATUALIZAR TIPO PROJETO ???");
			sqlException.printStackTrace();
			
			return false;
		}	
		
		
	}
	
	public List<TipoProjeto> listaTodos(){
		
		List<TipoProjeto> listaTipoProjeto = new ArrayList<TipoProjeto>();
		
		try{

			PreparedStatement pstmt = conn.prepareStatement ("select * from TipoProjeto");
			ResultSet resultSet = pstmt.executeQuery();
			
			// Imprime as colunas
			while (resultSet.next()){
				
				TipoProjeto tipoProjeto = new TipoProjeto();
				
				tipoProjeto.setId(resultSet.getInt(1));
				tipoProjeto.setDescricao(resultSet.getString(2));
				tipoProjeto.setDtCadastro(resultSet.getDate(3));
				tipoProjeto.setStatusTipo(resultSet.getBoolean(4));
				
				listaTipoProjeto.add(tipoProjeto);
				
			}
	
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO LISTAR TIPO PROJETO ???");
			sqlException.printStackTrace();
		}	
		
		return listaTipoProjeto;
		
		
	}
	
	public TipoProjeto buscaPorId(Long id){
		
		TipoProjeto tipoProjeto = new TipoProjeto();
		
		try{

			PreparedStatement pstmt = conn.prepareStatement ("select * from TipoProjeto where id=?");

			pstmt.setLong(1, id);
			//pstmt.execute();
			
			ResultSet resultSet = pstmt.executeQuery();
			
			// Imprime as colunas
			while (resultSet.next()){
				
				tipoProjeto.setId(resultSet.getInt(1));
				tipoProjeto.setDescricao(resultSet.getString(2));
				tipoProjeto.setDtCadastro(resultSet.getDate(3));
				tipoProjeto.setStatusTipo(resultSet.getBoolean(4));
				
			}
	
		}catch (SQLException sqlException) {
			System.out.println("??? ERRO NA BUSCA POR ID ???");
			sqlException.printStackTrace();
		}	
		
		return tipoProjeto;

	}
	
	//Retorna o último registro inserido
	public TipoProjeto lastReg(){
		
		TipoProjeto tipoProjeto = new TipoProjeto();
		
		try{

			PreparedStatement pstmt = conn.prepareStatement ("SELECT * FROM tipoprojeto order by id desc limit 1");

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()){
				
				tipoProjeto.setId(resultSet.getInt(1));
				tipoProjeto.setDescricao(resultSet.getString(2));
				tipoProjeto.setDtCadastro(resultSet.getDate(3));
				tipoProjeto.setStatusTipo(resultSet.getBoolean(4));
				
			}

		}catch (SQLException sqlException) {
			System.out.println("??? ERRO AO BUSCAR ULTIMO REGISTRO ???");
			sqlException.printStackTrace();
		}	
		
		return tipoProjeto;

	}

}
