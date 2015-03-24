<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Cadastro Tipo de Projeto</title>
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
    	
    	<form action="ServletControle?acao=insereTipoProjeto" method="post">
    	
    		<table cellpadding=0 cellspacing=0>
    			<tr  bgcolor="E6F3FF">
    				<td></td>
    				<td>Cadastro Tipo Projeto</td>
    			</tr>
    		
    			<tr>
    				<td>ID: </td>
    				<td> <input type="text" readonly="readonly" name="id" value="${tpProjeto.id}"/> </td>
    			</tr>
    			<tr>
    				<td>Descrição: </td>
    				<td> <input type="text" name="descricao" value="${tpProjeto.descricao}"/></td>
    				<td><p id="mensagemErro">${requestScope.erroDescricao}</p></td>
    			</tr>
    			<tr>
    				<td> Data: </td>
    				<td> <input type="text" name="dtCadastro" value="<fmt:formatDate value="${tpProjeto.dtCadastro}" pattern="dd/MM/yyyy"/>" maxlength="10" onKeyUp="barra(this)" onblur="validaDat(this,this.value)"/> </td>
    				<td><p id="mensagemErro">${requestScope.erroDtCadastro}</p></td>
    			</tr>
    			<tr>
    				<td>Status: </td>
    				<td> <select name="statusTipo" > 
   						
   							<option value="true" ${tpProjeto.statusTipo == "false" ? 'selected' : ''} >ATIVO</option> 
							<option value="false" ${tpProjeto.statusTipo == "false" ? 'selected' : ''}>INATIVO</option> 
						
						</select> </td>
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
    		<table width="600px">
    		
			    <caption style="color:#0000CC;font-weight: bold;background-color:#E6F3FF;">Registros Cadastrados</caption>

			<!-- Topo da tabela  -->
			    <thead>
			           <tr bgcolor="E6F3FF">
			                 <th align="center">Id
			                 <th align="center">Descrição 
			                 <th align="center">Data
			                 <th align="center">Status
			                 <th align="center">Ação
			  	 </thead>

			<!-- Corpo da tabela -->    
			    <tbody>
			    	<!-- O varStatus permite fazer uma contagem. Utilizo ele para realizar alternar as cores
			    	do fundo da tabela -->
			        <c:forEach items="${tpProjetos}" var="tpProjeto" varStatus="id">
                
	                	<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">
	                
		                    <td><c:out value="${tpProjeto.id}" /></td>
		                    <td><c:out value="${tpProjeto.descricao}" /></td>
		                    <td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${tpProjeto.dtCadastro}" /></td>
		                    <td align="center"> ${tpProjeto.statusTipo == "false" ? '<img src="layout/imagens/inativo.png">' : '<img src="layout/imagens/ativo.png">'} </td>
		 
		                    <td align="center"><a href="ServletControle?acao=editTipoProjeto&id=<c:out value="${tpProjeto.id}"/>">Update</a>
		                    	<a  href="ServletControle?acao=delTipoProjeto&id=<c:out value="${tpProjeto.id}"/>" onclick="return confirm('Deseja deletar o Registro?')" >Delete</a>
		                    </td>
	                	</tr>
           		 	</c:forEach>
           		 	
           		 </tbody>
           		 
	</table>
    <hr size="1">
	
	</div>

	<jsp:include page="layout/template/rodape.jsp"/>
</div>

</body>
</html>