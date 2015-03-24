package ServletController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Projeto;
import Model.TipoProjeto;
import Util.EstruturaBanco;
import Util.PopulaBancoThread;
import Util.Util;

import Controller.ProjetoController;
import Controller.TipoProjetoController;


/**
 * Servlet implementation class Servlet
 */
@WebServlet("/ServletControle")
public class ServletControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TipoProjetoController tpControl = new TipoProjetoController();
	private TipoProjeto tipoProjeto;
	
	private ProjetoController projControl = new ProjetoController();
	private Projeto projeto;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletControle() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	//Manipulação do TIPO PROJETO
		
		if(request.getParameter("acao").equals("insereTipoProjeto")){
			//Insere o tipo projeto no banco
			
			tipoProjeto = new TipoProjeto();
			
			request.setAttribute("erroCadastro", "");
			
			if(request.getParameter("descricao").equals("")){
				request.setAttribute("erroDescricao", "Campo Obrigatório!"); 
			}
			if(request.getParameter("dtCadastro").equals("")){
				request.setAttribute("erroDtCadastro", "Campo Obrigatório");
			}
			
			if(!request.getParameter("id").equals(""))
				tipoProjeto.setId(Integer.parseInt(request.getParameter("id")));
			
			tipoProjeto.setDtCadastro(Util.stringParaDate(request.getParameter("dtCadastro")));
			
			tipoProjeto.setDescricao(request.getParameter("descricao"));
			tipoProjeto.setStatusTipo( Boolean.parseBoolean(request.getParameter("statusTipo")));
			
			if(!tpControl.insereOuAtualiza(tipoProjeto)){
				System.out.println("ERRO AO INSERIR");
				request.setAttribute("erroCadastro", "Houve um Erro ao Cadastrar!");
			}
			
			
			//Atualiza a listagem
			request.setAttribute("tpProjetos", tpControl.listaTodos());
			
			String dispatch = "/FrmTipoProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
			
		}
		
		if(request.getParameter("acao").equals("delTipoProjeto")){
			//Deleta Tipo Projeto
			tipoProjeto = new TipoProjeto();
			
			tipoProjeto.setId(Integer.parseInt(request.getParameter("id")));
			
			tpControl.exclui(tipoProjeto);
			
			//Atualiza a listagem
			request.setAttribute("tpProjetos", tpControl.listaTodos());
			
			String dispatch = "/FrmTipoProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
			
		}
		
		// Atualiza Tipo Projeto
		if(request.getParameter("acao").equals("editTipoProjeto")){
			//Deleta Tipo Projeto
			tipoProjeto = new TipoProjeto();
			
			tipoProjeto = tpControl.buscaPorId( Long.parseLong(request.getParameter("id")) );
			
			request.setAttribute("tpProjeto", tipoProjeto);
			
			//Atualiza a listagem
			request.setAttribute("tpProjetos", tpControl.listaTodos());
			
			String dispatch = "/FrmTipoProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
			
		}
		
		if(request.getParameter("acao").equals("listTipoProjeto")){
			//Atualiza a listagem
			request.setAttribute("tpProjetos", tpControl.listaTodos());
			
			String dispatch = "/FrmTipoProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
		}
		
		if(request.getParameter("acao").equals("listagemTipoProjeto")){
			//Atualiza a listagem
			request.setAttribute("tpProjetos", tpControl.listaTodos());
			
			String dispatch = "/listTipoProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
		}

		//response.sendRedirect("index.jsp");
		
		
	//MANIPULAÇÃO PROJETO
		
		if(request.getParameter("acao").equals("insereProjeto")){
			//Insere o tipo projeto no banco
			
			projeto = new Projeto();
			
			request.setAttribute("erroCadastro", ""); // Validação do cadastro
			
			if(request.getParameter("descricao").equals("")){
				request.setAttribute("erroDescricao", "Campo Obrigatório!"); 
			}
			if(request.getParameter("dtInicio").equals("")){
				request.setAttribute("erroDtCadastro", "Campo Obrigatório");
			}
			if(request.getParameter("dtFim").equals("")){
				request.setAttribute("erroDtCadastro", "Campo Obrigatório");
			}
			
			if(!request.getParameter("id").equals(""))
				projeto.setId(Integer.parseInt(request.getParameter("id")));
			
			// Através do id selecionado na view busco o tipo projeto no banco
			projeto.setTipoProjeto(tpControl.buscaPorId((long) Integer.parseInt(request.getParameter("tipoProjeto"))));
			
			projeto.setDescricao(request.getParameter("descricao"));
			
			projeto.setDtInicio(Util.stringParaDate(request.getParameter("dtInicio")));
			
			projeto.setDtFim(Util.stringParaDate(request.getParameter("dtFim")));
			
			projeto.setStatusProjeto(Boolean.parseBoolean(request.getParameter("statusProjeto")));
			
			projeto.setNivelDificuldade(request.getParameter("nivelDificuldade").charAt(0));
			
			if(!projControl.insereOuAtualiza(projeto)){
				System.out.println("ERRO AO INSERIR");
				request.setAttribute("erroCadastro", "Houve um Erro ao Cadastrar!");
			}
			
			
			//Atualiza a listagem
			request.setAttribute("projetos", projControl.listaTodos());
			request.setAttribute("listTpProjetos", tpControl.listaTodos()); // Lista todos os tipos projetos no combo
			
			String dispatch = "/FrmProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
			
		}
		
		if(request.getParameter("acao").equals("delProjeto")){
			//Deleta Tipo Projeto
			projeto = new Projeto();
			
			projeto.setId(Integer.parseInt(request.getParameter("id")));
			
			projControl.exclui(projeto);
			
			//Atualiza a listagem
			request.setAttribute("projetos", projControl.listaTodos());
			request.setAttribute("listTpProjetos", tpControl.listaTodos()); // Lista todos os tipos projetos no combo
			
			String dispatch = "/FrmProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
			
		}
		
		if(request.getParameter("acao").equals("editProjeto")){
			
			projeto = new Projeto();
			tipoProjeto = new TipoProjeto();
			
			projeto = projControl.buscaPorId( Long.parseLong(request.getParameter("id")) );
			
			//Atualiza a listagem
			request.setAttribute("projetos", projControl.listaTodos());
			request.setAttribute("listTpProjetos", tpControl.listaTodos()); // Lista todos os tipos projetos no combo
			request.setAttribute("projeto", projeto);
			
			String dispatch = "/FrmProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
			
		}
		
		if(request.getParameter("acao").equals("listProjeto")){
			//Atualiza a listagem
			request.setAttribute("projetos", projControl.listaTodos());
			request.setAttribute("listTpProjetos", tpControl.listaTodos()); // Lista todos os tipos projetos no combo
			
			String dispatch = "/FrmProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
		}
		
		if(request.getParameter("acao").equals("listagemProjeto")){
			//Atualiza a listagem
			request.setAttribute("projetos", projControl.listaTodos());
			
			String dispatch = "/listProjeto.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
		}
		
		
		// ADMINISTRAÇÃO DO BANCO DE DADOS
		
		if(request.getParameter("acao").equals("populaBanco")){
			
			// Popula Tabela TIPO PROJETO
			if(!request.getParameter("arqTP").equals("")){
				
				PopulaBancoThread thread1 = new PopulaBancoThread(request.getParameter("arqTP"), "TIPOPROJETO");
				Thread t = new Thread(thread1, "Thread Popula TipoProjeto"); 
				
				//Prioridade da Thread
				if(!request.getParameter("priorTP").equals(""))
					t.setPriority(new Integer(Integer.parseInt(request.getParameter("priorTP"))));
				
				
				t.start();
			}
			
			// Popula Tabela Projeto 
			if(!request.getParameter("arqProj").equals("")){
				
				PopulaBancoThread thread2 = new PopulaBancoThread(request.getParameter("arqProj"), "PROJETO");
				Thread t2 = new Thread(thread2, "Thread Popula Projeto"); 
				
				//Prioridade da Thread
				if(!request.getParameter("priorProjeto").equals(""))
					t2.setPriority(new Integer(Integer.parseInt(request.getParameter("priorProjeto"))));
				
				
				t2.start();
			}
			
			String dispatch = "/admBanco.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
			
		}
		
		// Cria ou deleta estrutura banco
		if(request.getParameter("acao").equals("admBanco")){
			
			
			EstruturaBanco estr = new EstruturaBanco();
			
			if(request.getParameter("aBanco").equals("criaEstrutura")){
				
				if(estr.criaEstrutura()){
					request.setAttribute("mensagem", "Estrutura Criada com Sucesso!");
				}
				
			} else if (request.getParameter("aBanco").equals("delEstrutura")){
				
				if(estr.dropEstrutura())
					request.setAttribute("mensagem", "Estrutura deletada com Sucesso!");
				
			}
			
			
			String dispatch = "/admBanco.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatch);
			dispatcher.forward (request, response) ;
			
		}
		
	}


}
