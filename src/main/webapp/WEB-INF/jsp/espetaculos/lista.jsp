<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Lista de Espet�culos</title>
	</head>

	<body>
		<h2>Adicionar Espetaculo</h2>
		
		<form id="addForm" action="" method="post">
			<label for="nome">Nome:</label>
			<input type="text" name="espetaculo.nome" id="nome" />
			
			<label for="descricao">Descri��o:</label>
			<input type="text" name="espetaculo.descricao" id="descricao" />
			
			<label for="tipo">Tipo:</label> 
			<select id="tipo" name="espetaculo.tipo">
				<option value="CINEMA">Cinema</option>
				<option value="SHOW">Show</option>
				<option value="TEATRO">Teatro</option>
				<option value="BALLET">Bal�</option>
				<option value="ORQUESTRA">Orquestra</option>
			</select>
			
			<label for="local">Local:</label>
			<select id="local" name="espetaculo.estabelecimento.id">
				<c:forEach items="${estabelecimentos}" var="estabelecimento">
					<option value="${estabelecimento.id}">${estabelecimento.nome}</option>
				</c:forEach>
			</select>
			
			<input type="submit" value="Adicionar" />
		</form>

		<table cellpadding="0" cellspacing="0" width="100%">
			<caption>Lista de espet�culos</caption>
			<colgroup>
				<col width="5%">
				<col width="40%">
				<col width="30%">
				<col width="10%">
				<col width="15%">
			</colgroup>
		
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Descri��o</th>
					<th>Tipo</th>
					<th></th>
				</tr>
			</thead>
		
			<tbody>
				<c:forEach items="${espetaculoList}" var="espetaculo">
					<tr>
						<td>${espetaculo.id}</td>
						<td>${espetaculo.nome}</td>
						<td>${espetaculo.descricao}</td>
						<td>${espetaculo.tipo}</td>
						<td>
							<a href="<c:url value="/espetaculo/${espetaculo.id}/sessoes"/>">Sess�es</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>