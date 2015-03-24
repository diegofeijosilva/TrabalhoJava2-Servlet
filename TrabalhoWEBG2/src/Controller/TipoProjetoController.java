package Controller;

import java.sql.Date;
import java.util.List;

import Dao.TipoProjetoDao;
import Model.TipoProjeto;
import Util.Util;

public class TipoProjetoController {
	
	private TipoProjetoDao dao;
	
	public TipoProjetoController() {
		dao = new TipoProjetoDao();
	}
	
	public boolean insere(TipoProjeto tipoProjeto){
		
		// Descrição não pode ser nulo
		if(tipoProjeto.getDescricao() == null) return false;
		
		// Descrição não pode ser vazio
		if(tipoProjeto.getDescricao().equals("")) return false;
		
		// Descrição não pode ser maior que 50 caracteres
		if(tipoProjeto.getDescricao().length() > 49) return false;
		
		// Data não pode ser null
		if(tipoProjeto.getDtCadastro() == null) return false;
		
		// Verifica se data é valida. Testa ano bisexto
		if(!Util.VerificaData(tipoProjeto.getDtCadastro())) return false;
		
		// StatusTipo não pode ser nulo
		if(tipoProjeto.getStatusTipo() == null) return false;
		
		
		dao.insere(tipoProjeto);
		
		return true;
	}
	
	public boolean atualiza(TipoProjeto tipoProjeto){
		
		if(tipoProjeto.getId() == null) return false;
		
		if(tipoProjeto.getDescricao() == null) return false;
		
		if(tipoProjeto.getStatusTipo() == null) return false;
		
		if(tipoProjeto.getDescricao().length() > 49) return false;
		
		// Data não pode ser null
		if(tipoProjeto.getDtCadastro() == null) return false;
				
		// Verifica se data é valida. Testa ano bisexto
		if(!Util.VerificaData(tipoProjeto.getDtCadastro())) return false;
		
		dao.atualiza(tipoProjeto);
		
		return true;
	}
	
	public boolean insereOuAtualiza(TipoProjeto tipoProjeto){

		if(tipoProjeto.getId() == null){
			if(insere(tipoProjeto)) { return true; }
		
		}else {
			if(atualiza(tipoProjeto)) { return true; }
		}
		
		return false;
	}
	
	public boolean exclui(TipoProjeto tipoProjeto){
		
		if(tipoProjeto.getId() == null) return false;
		
		dao.exclui(tipoProjeto);
		
		return true;
		
	}
	
	public List<TipoProjeto> listaTodos(){
		
		List<TipoProjeto> listaTipoProjeto;
		
		listaTipoProjeto = dao.listaTodos();
		
		return listaTipoProjeto;
	}
	
	public TipoProjeto buscaPorId(Long id){

		if(id == null || id < 0) return null;
		
		TipoProjeto tipoProjeto = new TipoProjeto();
		
		tipoProjeto = dao.buscaPorId(id);
		
		if(tipoProjeto.getId() == null) 
			return null;
		
		
		return tipoProjeto;
	}
	
	// Retorna o último registro inserido
	public TipoProjeto lastReg(){
		
		return dao.lastReg();
		
	}

}
