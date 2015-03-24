package Controller;

import java.util.ArrayList;
import java.util.List;

import Dao.ProjetoDao;
import Model.Projeto;
import Util.Util;

public class ProjetoController {
	
	private ProjetoDao dao;
	
	public ProjetoController() {
		dao = new ProjetoDao();
	}
	
	public Boolean insere(Projeto projeto){
		
			// Descri��o n�o pode ser nulo
			if(projeto.getDescricao() == null) return false;
			
			// TipoProjeto n�o pode ser nulo
			if(projeto.getTipoProjeto() == null) return false;

			// Descri��o n�o pode ser maior que 50 chars
			if(projeto.getDescricao().length() > 49) return false;
			
			// Status deve ser True ou False
			if(projeto.getStatusProjeto() == null) return false;
			
			// DATA INICIO n�o pode ser null
			if(projeto.getDtInicio() == null) return false;
			
			// Verifica se DATA INICIO � uma data v�lida. Testa Bisexto
			if(!Util.VerificaData(projeto.getDtInicio())) return false;
			
			// Testa DATA FIM
			if(projeto.getDtFim() == null) return false;
			
			// Verifica se DATA FIM � uma data v�lida. Testa Bisexto
			if(!Util.VerificaData(projeto.getDtFim())) return false;
			
			// N�vel Dificuldade n�o pode ser NULL
			if(projeto.getNivelDificuldade() == null) return false;
			
			// N�vel Dificuldade n�o pode ser diferente de A, B ou C
			if(projeto.getNivelDificuldade() != 'A' && projeto.getNivelDificuldade() != 'B' && projeto.getNivelDificuldade() != 'C') return false;
		
		dao.insere(projeto);
		
		return true;
	}
	
	public Boolean atualiza(Projeto projeto){
		
			// Descri��o n�o pode ser nulo
			if(projeto.getId() == null) return false;
		
			// TipoProjeto n�o pode ser nulo
			if(projeto.getTipoProjeto() == null) return false;
						
			// Descri��o n�o pode ser maior que 50 chars
			if(projeto.getDescricao().length() > 49) return false;
			
			// DATA INICIO n�o pode ser null
			if(projeto.getDtInicio() == null) return false;
						
			// Verifica se DATA INICIO � uma data v�lida. Testa Bisexto
			if(!Util.VerificaData(projeto.getDtInicio())) return false;
						
			// Testa DATA FIM
			if(projeto.getDtFim() == null) return false;
						
			// Verifica se DATA FIM � uma data v�lida. Testa Bisexto
			if(!Util.VerificaData(projeto.getDtFim())) return false;
						
			// Status deve ser True ou False
			if(projeto.getStatusProjeto() == null) return false;
			
			// N�vel Dificuldade n�o pode ser diferente de A, B ou C
			if(projeto.getNivelDificuldade() != 'A' && projeto.getNivelDificuldade() != 'B' && projeto.getNivelDificuldade() != 'C') return false;
			
		dao.atualiza(projeto);
			
		return true;
	}
	
	public Boolean insereOuAtualiza(Projeto projeto){
		
		if(projeto.getId() == null)
			return insere(projeto);
		else
			return atualiza(projeto);
		
	}
	
	public Boolean exclui(Projeto projeto){
		
		if(projeto.getId() == null) return false;
		
		dao.exclui(projeto);
		
		return true;
	}
	
	public List<Projeto> listaTodos(){
		List<Projeto> listaProjeto = new ArrayList<Projeto>();
		
		listaProjeto = dao.listaTodos();
		
		return listaProjeto;
		
	}
	
	public Projeto buscaPorId(Long id){
		
		if(id == null || id < 0) return null;
		
		Projeto projeto = new Projeto();
		
		projeto = dao.buscaPorId(id);
		
		return projeto;
	}
	
	public Projeto lastReg(){
		
		Projeto projeto = new Projeto();
		
		return projeto = dao.lastReg();
		
	}

}
