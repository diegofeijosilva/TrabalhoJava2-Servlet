<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Cadastro Projeto</title>
	<link rel="stylesheet" type="text/css" href="layout/css/style.css"/>
	
	<!-- Formata campo para data -->
	<script language="javascript">
		function barra(objeto){
			if (objeto.value.length == 2 || objeto.value.length == 5 ){
				objeto.value = objeto.value+"/";
			}
		}
	</script>
	
	<!-- Valida Data -->
	<script>
 
		 function validaDat(campo,valor) {
			var date=valor;
			var ardt=new Array;
			var ExpReg=new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
			ardt=date.split("/");
			erro=false;
			if ( date.search(ExpReg)==-1){
				erro = true;
				}
			else if (((ardt[1]==4)||(ardt[1]==6)||(ardt[1]==9)||(ardt[1]==11))&&(ardt[0]>30))
				erro = true;
			else if ( ardt[1]==2) {
				if ((ardt[0]>28)&&((ardt[2]%4)!=0))
					erro = true;
				if ((ardt[0]>29)&&((ardt[2]%4)==0))
					erro = true;
			}
			if (erro) {
				alert("\"" + valor + "\" não é uma data válida!!!");
				campo.focus();
				campo.value = "";
				return false;
			}
			return true;
		}

 </script>

</head>
<body>

<div id="principal">

	<jsp:include page="layout/template/topo.jsp"/>
	<jsp:include page="layout/template/menu.jsp"/>
	
	<div id="conteudo">
		<br>
    	
    	<form action="ServletControle?acao=insereProjeto" method="post">
    	
    		<table cellpadding=0 cellspacing=0>
    			<tr  bgcolor="E6F3FF">
    				<td></td>
    				<td>Cadastro Projeto</td>
    			</tr>
    		
    			<tr>
    				<td>ID: </td>
    				<td> <input type="text" readonly="readonly" name="id" value="${projeto.id}"/> </td>
    			</tr>
    			<tr>
    				<td>Tipo Projeto: </td>
    				<td> <select style="width:335px" name="tipoProjeto" > 
    				
    						<c:forEach var="tipoProjeto" items="${listTpProjetos}">  
      							<option value="${tipoProjeto.id}"  <c:if test="${tipoProjeto.id == projeto.tipoProjeto.id}">selected</c:if> >${tipoProjeto.descricao}</option>  
  							</c:forEach> 

						</select> 
						
					</td>
    			<tr>
    				<td> Descrição Projeto: </td>
    				<td> <input type="text" size="50" name="descricao" value="${projeto.descricao}"/> </td>
    				<td><p id="mensagemErro">${requestScope.erroCadastro}</p></td>
    			</tr>
    			<tr>
    				<td>Data Inicio: </td>
    				<td> <input type="text" name="dtInicio" value="<fmt:formatDate value="${projeto.dtInicio}" pattern="dd/MM/yyyy"/>" maxlength="10" onKeyUp="barra(this)" onblur="validaDat(this,this.value)"/> </td>
    				<td><p id="mensagemErro">${requestScope.erroDtCadastro}</p></td>
    			</tr>
    			<tr>
    				<td>Data Fim: </td>
    				<td> <input type="text" name="dtFim" value="<fmt:formatDate value="${projeto.dtFim}" pattern="dd/MM/yyyy"/>" maxlength="10" onKeyUp="barra(this)" onblur="validaDat(this,this.value)"/> </td>
    				<td><p id="mensagemErro">${requestScope.erroDtCadastro}</p></td>
    			</tr>
    			<tr>
    				<td>Status: </td>
    				<td> <select name="statusProjeto" > 
   						
   							<option value="true" ${projeto.statusProjeto == "false" ? 'selected' : ''} >ATIVO</option> 
							<option value="false" ${projeto.statusProjeto == "false" ? 'selected' : ''}>INATIVO</option> 
						
						</select> </td>
    			</tr>
    			<tr>
    				<td>Nível de Dificuldade: </td>
    				<td> <select name="nivelDificuldade" > 
   						
   							<option value="A" <c:if test="${projeto.x eq 'A'}">selected</c:if> >A</option> 
							<option value="B" <c:if test="${projeto.x eq 'B'}">selected</c:if> >B</option> 
							<option value="C" <c:if test="${projeto.x eq 'C'}">selected</c:if> >C</option> 
						
						</select> 
					</td>
    			</tr>
 
    			<tr>
    				<td> <input type="submit" value="Salvar" style="width:100px"> </td>
    			</tr>
    		
    			<tr>
    				<td><p id="mensagemErro">${requestScope.erroCadastro}</p></td>
    			</tr>
    		
    		</table>

    	</form>
    	<hr size="1">
    		
    		<!-- Tabela HTML5 -->
    		<table width="900px">
    		
			    <caption style="color:#0000CC;font-weight: bold;background-color:#E6F3FF;">Registros Cadastrados</caption>

			<!-- Topo da tabela  -->
			    <thead>
			           <tr bgcolor="E6F3FF">
			                 <th align="center">Id
			                 <th align="center">Tipo Projeto
			                 <th align="center">Descrição 
			                 <th align="center">Data Início
			                 <th align="center">Data Fim
			                 <th align="center">Status
			                 <th align="center">Nivel
			                 <th align="center">Ação

			<!-- Corpo da tabela -->    
			    <tbody>
			    	<!-- O varStatus permite fazer uma contagem. Utilizo ele para realizar alternar as cores
			    	do fundo da tabela -->
			        <c:forEach items="${projetos}" var="projeto" varStatus="id">
                
                	<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">
                
	                    <td><c:out value="${projeto.id}" /></td>
	                    <td><c:out value="${projeto.tipoProjeto.descricao}" /></td>
	                    <td><c:out value="${projeto.descricao}" /></td>
	                    <td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dtInicio}" /></td>
	                    <td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dtFim}" /></td>
	                    <td align="center"> ${projeto.statusProjeto == "false" ? '<img src="layout/imagens/inativo.png">' : '<img src="layout/imagens/ativo.png">'} </td>
	                     <td align="center"><c:out value="${projeto.nivelDificuldade}" /></td>
	 
	                    <td align="center"><a href="ServletControle?acao=editProjeto&id=<c:out value="${projeto.id}"/>">Update</a>
	                    	<a href="ServletControle?acao=delProjeto&id=<c:out value="${projeto.id}"/>" onclick="return confirm('Deseja deletar o Registro?')" >Delete</a>
	                    </td>
                	</tr>
            </c:forEach>
	</table>
    <hr size="1">
	
	</div>

	<jsp:include page="layout/template/rodape.jsp"/>
</div>

</body>
</html>