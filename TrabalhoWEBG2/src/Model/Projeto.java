package Model;

import java.sql.Date;

public class Projeto {
	
	private Integer id;
	private TipoProjeto tipoProjeto; // FK
	private String descricao;
	private Date dtInicio;
	private Date dtFim;
	private Boolean statusProjeto; //True = Ativo; False = Inativo
	private char nivelDificuldade; // Classificação do projeto baseado no nível de dificuldade. A,B e C.
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoProjeto getTipoProjeto() {
		return tipoProjeto;
	}
	public void setTipoProjeto(TipoProjeto tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Date getDtFim() {
		return dtFim;
	}
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	public Boolean getStatusProjeto() {
		return statusProjeto;
	}
	public void setStatusProjeto(Boolean statusProjeto) {
		this.statusProjeto = statusProjeto;
	}
	public Character getNivelDificuldade() {
		return nivelDificuldade;
	}
	public void setNivelDificuldade(Character nivelDificuldade) {
		this.nivelDificuldade = nivelDificuldade;
	}
	
	public String getX(){
		return new Character(nivelDificuldade).toString();
	}

	
}
