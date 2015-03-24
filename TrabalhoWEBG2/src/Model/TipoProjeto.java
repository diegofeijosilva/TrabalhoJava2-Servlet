package Model;

import java.sql.Date;

public class TipoProjeto {
	
	private Integer id;
	private String descricao;
	private Date dtCadastro;
	private Boolean statusTipo; //True = Ativo; False = Inativo
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public Boolean getStatusTipo() {
		return statusTipo;
	}
	public void setStatusTipo(Boolean statusTipo) {
		this.statusTipo = statusTipo;
	}
	
	

}
