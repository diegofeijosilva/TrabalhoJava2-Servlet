<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Listagem Tipo Projeto</title>
	<link rel="stylesheet" type="text/css" href="layout/css/style.css"/>
</head>
<body>

<div id="principal">
	<jsp:include page="layout/template/topo.jsp"/>
	<jsp:include page="layout/template/menu.jsp"/>
	
	<div id="conteudo">
	
    	<hr size="1">
    		
    		<!-- Tabela HTML5 -->
    		<table width="700px" >
    		
			    <caption style="color:#0000CC;font-weight: bold;background-color:#E6F3FF;">Registros Cadastrados</caption>

			<!-- Topo da tabela  -->
			    <thead>
			           <tr bgcolor="E6F3FF">
			                 <th align="center">Id
			                 <th align="center">Descrição 
			                 <th align="center">Data
			                 <th align="center">Status
			                 <th align="center">Ação

			<!-- Corpo da tabela -->    
			    <tbody>
			        <c:forEach items="${tpProjetos}" var="tpProjeto" varStatus="id">
			        
                <tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">
                
                    <td><c:out value="${tpProjeto.id}" /></td>
                    <td><c:out value="${tpProjeto.descricao}" /></td>
                   <td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${tpProjeto.dtCadastro}" /></td>
                   <td align="center"> ${tpProjeto.statusTipo == "false" ? '<img src="layout/imagens/inativo.png">' : '<img src="layout/imagens/ativo.png">'} </td>
 
                    <td align="center"><a href="ServletControle?acao=editTipoProjeto&id=<c:out value="${tpProjeto.id}"/>">Update</a>
                    <a href="ServletControle?acao=delTipoProjeto&id=<c:out value="${tpProjeto.id}"/>" onclick="return confirm('Deseja deletar o Registro?')">Delete</a></td>
                </tr>
            </c:forEach>
	</table>
    
    <hr size="1">
	
	</div>

	<jsp:include page="layout/template/rodape.jsp"/>
</div>

</body>
</html>