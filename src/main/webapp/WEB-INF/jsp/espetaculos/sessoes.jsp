<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>${espetaculo.nome}</title>
	</head>

	<body>
		<h2>Criar novas sessões</h2>
		
		<form action="<c:url value="/espetaculo/${espetaculo.id}/sessoes"/>" method="post">
			<label for="inicio">Início</label>
			<input type="text" name="inicio" id="inicio" />
			
			<label for="fim">Fim</label>
			<input type="text" name="fim" id="fim" />
			
			<label for="horario">Horário</label>
			<input type="text" name="horario" id="horario" />
			
			<label for="periodo">Periodicidade</label>
			<select name="periodicidade" id="periodicidade">
				<option value="DIARIA">Diária</option>
				<option value="SEMANAL">Semanal</option>
			</select>
			
			<input type="submit" value="Adiciona" />
		</form>
		
		<table cellpadding="0" cellspacing="0" width="100%">
			<caption>Lista de sessões do espetáculo</caption>
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
					<th>Duração</th>
					<th>Preço</th>
					<th>Total de ingressos</th>
					<th>Ingressos disponíveis</th>
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
