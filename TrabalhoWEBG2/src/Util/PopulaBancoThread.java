package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Controller.ProjetoController;
import Controller.TipoProjetoController;
import Model.Projeto;
import Model.TipoProjeto;

public class PopulaBancoThread implements Runnable {
	
	private String caminhoArquivo;
	private String tabela;
	Util util = new Util();
	
	// Variáveis Tipo Projeto
	private Integer errosRegistrosTP = 0;
	private Integer registrosTP = 0;
	private Integer registrosInseridosTP = 0;
	
	// Variáveis do Projeto
	private Integer errosRegistrosProj = 0;
	private Integer registrosProj = 0;
	private Integer registrosInseridosProj = 0;
	
	public PopulaBancoThread(String arquivo, String tabela) {
		// TODO Auto-generated constructor stub
		
		this.caminhoArquivo  = arquivo;
		this.tabela = tabela;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("VALOR DA TABELA: "+this.tabela);
		System.out.println("CAMINHO DO ARQUIVO: "+this.caminhoArquivo);
		
		if(this.tabela.equals("TIPOPROJETO")){
			
			try {
				
				populaTipoProjeto();
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} else if(this.tabela.equals("PROJETO")){
			
			try {
				populaProjeto();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
	}
	
	
	//Popula a tabela TipoProjeto com o arquivo TipoProjeto.csv
	public boolean populaTipoProjeto() throws IOException{
		
		File nomeArquivo = new File(caminhoArquivo);
		BufferedReader arquivoLeitura = new BufferedReader(new FileReader(nomeArquivo));
	    String linha;
	    
	    TipoProjetoController tipoProjetoControl = new TipoProjetoController();
		
	    while ((linha = arquivoLeitura.readLine()) != null){
	    	
	    	TipoProjeto tipoProjeto = new TipoProjeto();
	    	
	    	System.out.println("*** TIPO PROJETO: "+linha);
	    	
	    	String[] ss = linha.split(";"); 
	    	
	 
	    		tipoProjeto.setId(Integer.parseInt(ss[0]));

	    		tipoProjeto.setDescricao(ss[1]);

	    		tipoProjeto.setDtCadastro(util.stringParaDate( ss[2] ));

	    		tipoProjeto.setStatusTipo(Boolean.parseBoolean(ss[3]));
	    	
	    	if(!tipoProjetoControl.insere(tipoProjeto))
	    		errosRegistrosTP ++;
	    	else
	    		registrosInseridosTP ++;
	    	
	    registrosTP ++;
        }
        arquivoLeitura.close();
		
        System.out.println("TIPO PROJETO - TOTAL DE REGISTROS: " + registrosTP);
        System.out.println("TIPO PROJETO - TOTAL REGISTROS INSERIDOS: " + registrosInseridosTP);
        System.out.println("TIPO PROJETO - ERROS NA INSERÇÃO: " + errosRegistrosTP);
        
        
		return true;
	}
	
	

	public Integer getRegistrosTP() {
		return registrosTP;
	}

	public void setRegistrosTP(Integer registrosTP) {
		this.registrosTP = registrosTP;
	}

	//Popula a tabela Projeto com o arquivo Projeto.csv
	public boolean populaProjeto() throws IOException{
			
			File nomeArquivo = new File(caminhoArquivo);
			BufferedReader arquivoLeitura = new BufferedReader(new FileReader(nomeArquivo));
		    String linha;
		    
		    ProjetoController projetoControl = new ProjetoController();
		    TipoProjetoController tipoProjetoControl = new TipoProjetoController();
			
		    while ((linha = arquivoLeitura.readLine()) != null){
		    	
		    	Projeto projeto = new Projeto();
		    	TipoProjeto tipoProjeto = new TipoProjeto();
		    	
		    	System.out.println("*** PROJETO: "+linha);
		    	
		    	String[] ss = linha.split(";"); 
		    	
		    	projeto.setId(Integer.parseInt(ss[0]));
		    	
		    	// Através do FK procuro o TIPO PROJETO
		    	tipoProjeto = tipoProjetoControl.buscaPorId((long) Integer.parseInt(ss[1]));
		    	
		    	projeto.setTipoProjeto(tipoProjeto); //Seta o Tipo Projeto
		    	
		    	projeto.setDescricao(ss[2]);
		    	projeto.setDtInicio(util.stringParaDate( ss[3] ));
		    	projeto.setDtFim(util.stringParaDate( ss[4] ));
		    	projeto.setStatusProjeto(Boolean.parseBoolean(ss[5]));
		    	
		    	projeto.setNivelDificuldade(ss[6].charAt(0));
		    	
		    	
		    	if(!projetoControl.insere(projeto))
		    		errosRegistrosProj ++;
		    	else
		    		registrosInseridosProj ++;
		    	
		    registrosProj ++;

	        System.out.println("PROJETO - TOTAL DE REGISTROS: " + registrosProj);
	        System.out.println("PROJETO - TOTAL REGISTROS INSERIDOS: " + registrosInseridosProj);
	        System.out.println("PROJETO - ERROS NA INSERÇÃO: " + errosRegistrosProj);

		    	
	        }
	        arquivoLeitura.close();
			
			return true;
	}
	
	
}

