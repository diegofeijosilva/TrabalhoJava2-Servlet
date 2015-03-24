<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Administração do Banco de Dados</title>
	<link rel="stylesheet" type="text/css" href="layout/css/style.css"/>

</head>
<body>

<div id="principal">

	<jsp:include page="layout/template/topo.jsp"/>
	<jsp:include page="layout/template/menu.jsp"/>
	
	<div id="conteudo">
		<br>
		
		<!-- CRIA OU DELETA ESTRUTURA -->
		<form action="ServletControle?acao=admBanco" method="post">
			<table cellpadding=0 cellspacing=0>
    			<tr  bgcolor="E6F3FF">
    				
    				<td align="left">CRIA OU DELETA ESTRUTURA</td>
    				<td></td>
	
    			</tr>
    			
    			<tr>
    				<td> <select name="aBanco" > 
   						
   							<option value="criaEstrutura" >CRIA ESTRUTURA</option> 
							<option value="delEstrutura" >DELETA ESTRUTURA</option> 
	
						</select> 
		
					 </td>
					 
					 <td> <input type="submit" value="Executar" style="width:100px"> </td>
					 
    			</tr>
    			
    			<tr>
    				<td ><p  id="mensagem">${requestScope.mensagem}</p></td>	
    			</tr>
    		
    		</table>
		
		</form>
		
		<br>
		<hr size="1">
		<br>
    	
    	<form action="ServletControle?acao=populaBanco" method="post">
    	
    		<table cellpadding=0 cellspacing=0>
    			<tr  bgcolor="E6F3FF">
    				<td></td>
    				<td>Selecione os arquivos para importação</td>
    				<td></td>
    				<td></td>
    			</tr>

    			<tr>
    				<td>Arquivo TipoProjeto: </td>
    				<td> <input type="text" name="arqTP"/> </td>
    				<td> Prioridade:  </td>
    				<td> <select name="priorTP" > 
   						
   							<option value="1" >1</option> 
							<option value="2" >2</option>
							<option value="3" >3</option>
							<option value="4" >4</option>
							<option value="5" >5</option>
							<option value="6" >6</option>
							<option value="7" >7</option>
							<option value="8" >8</option>
							<option value="9" >9</option>
							<option value="10" >10</option>
							
						</select> 
					</td>
    			</tr>
    			<tr>
    				<td>Arquivo Projeto </td>
    				<td> <input type="text" name="arqProj"/> </td>
    				<td> Prioridade:  </td>
    				<td> <select name="priorProjeto" > 
   						
   							<option value="1" >1</option> 
							<option value="2" >2</option>
							<option value="3" >3</option>
							<option value="4" >4</option>
							<option value="5" >5</option>
							<option value="6" >6</option>
							<option value="7" >7</option>
							<option value="8" >8</option>
							<option value="9" >9</option>
							<option value="10" >10</option>
							
						</select> 
					</td>
    			</tr>
 
    			<tr>
    				<td> <input type="submit" value="Executar" style="width:100px"> </td>
    			</tr>
    		
    			<tr>
    				<td><p id="mensagemErro">${requestScope.erroCadastro}</p></td>
    			</tr>
    		
    		</table>

    	</form>
    	
    	<hr size="1">
    	
    	<table>
    		<caption style="color:#0000CC;font-weight: bold;background-color:#E6F3FF;">Status da Importação</caption>
    		
    		<!-- Topo da tabela  -->
			    <thead>
			           <tr bgcolor="E6F3FF">
			           		 <th align="center">Tabela
			           		 <th align="center">Total de Registros
			                 <th align="center">Importados com Sucesso
			                 <th align="center">Registros com Erros
			                 
			    </thead>
			    
			    <tbody>
			    
			    	<!-- Apresenta o resultado do arquivo tipoProjeto -->
			    	<tr bgcolor="aaee88">
			    		
			    		<td> Tipo Projeto </td>
			    		<td align="center"> </td>
			    		<td align="center"></td>
			    		<td align="center"></td>
			    		
			    	 </tr>
			    	 
			    	 <!-- Apresenta o resultado do arquivo projeto -->
			    	 <tr bgcolor="aaee88">
			    		
			    		<td> Projeto </td>
			    		<td align="center">   </td>
			    		<td align="center"> </td>
			    		<td align="center"> </td>
			    		
			    	 </tr>
			    </tbody>

    	</table>
	
	</div>

	<jsp:include page="layout/template/rodape.jsp"/>
</div>

</body>
</html>