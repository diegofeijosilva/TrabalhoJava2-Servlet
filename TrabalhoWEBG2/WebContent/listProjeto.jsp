<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Listagem Projeto</title>
	<link rel="stylesheet" type="text/css" href="layout/css/style.css"/>
</head>
<body>

<div id="principal">
	<jsp:include page="layout/template/topo.jsp"/>
	<jsp:include page="layout/template/menu.jsp"/>
	
	<div id="conteudo">
	
    	<hr size="1">
    		
    		<!-- Tabela HTML5 -->
    		<table width="900px" >
    		
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
	                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dtInicio}" /></td>
	                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dtFim}" /></td>
	                    <td align="center"> ${projeto.statusProjeto == "false" ? '<img src="layout/imagens/inativo.png">' : '<img src="layout/imagens/ativo.png">'} </td>
	                     <td align="center"><c:out value="${projeto.nivelDificuldade}" /></td>
	 
	                    <td align="center"><a href="ServletControle?acao=editProjeto&id=<c:out value="${projeto.id}"/>">Update</a>
	                    	<a href="ServletControle?acao=delProjeto&id=<c:out value="${projeto.id}"/>">Delete</a>
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