<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>${espetaculo.nome}</title>
	</head>

	<body>
		<h2>Criar novas sess�es</h2>
		
		<form action="<c:url value="/espetaculo/${espetaculo.id}/sessoes"/>" method="post">
			<label for="inicio">In�cio</label>
			<input type="text" name="inicio" id="inicio" />
			
			<label for="fim">Fim</label>
			<input type="text" name="fim" id="fim" />
			
			<label for="horario">Hor�rio</label>
			<input type="text" name="horario" id="horario" />
			
			<label for="periodo">Periodicidade</label>
			<select name="periodicidade" id="periodicidade">
				<option value="DIARIA">Di�ria</option>
				<option value="SEMANAL">Semanal</option>
			</select>
			
			<input type="submit" value="Adiciona" />
		</form>
		
		<table cellpadding="0" cellspacing="0" width="100%">
			<caption>Lista de sess�es do espet�culo</caption>
			<colgroup>
				<col width="5%">
				<col width="40%">
				<col width="30%">
				<col width="10%">
				<col width="15%">
			</colgroup>
		
			<thead>
				<tr>
					<th>Inicio</th>
					<th>Dura��o</th>
					<th>Pre�o</th>
					<th>Total de ingressos</th>
					<th>Ingressos dispon�veis</th>
				</tr>
			</thead>
		
			<tbody>
				<c:forEach items="${espetaculo.sessoes}" var="sessao">
					<tr>
						<td>${sessao.inicio}</td>
						<td>${sessao.duracaoEmMinutos}</td>
						<td>${sessao.preco}</td>
						<td>${sessao.totalIngressos}</td>
						<td>${sessao.ingressosReservados}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
	</body>
</html>
