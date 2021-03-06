<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cursos Registro, Actualizacion y Borrado</title>

<link rel="stylesheet"
	href='<c:url value="/resources/css/pure-0.4.2.css"/>'>

<link rel="stylesheet"
	href='<c:url value="/resources/css/font-awesome-4.0.3/css/font-awesome.css"/>'>

<link rel="stylesheet"
	href='<c:url value="/resources/css/jquery-ui-1.10.4.custom.css"/>'>

<style type="text/css">
th {
	text-align: left
}
</style>


</head>

<body>
<%@ include file="/WEB-INF/views/templates/menu.jsp"%>
	<div style="width: 95%; margin: 0 auto;">
	
	<div id="AddCtCurso_Dialog" style="display: none;">
			<%@ include file="ctCurso_Form.jsp"%>
		</div>

		<h1>Lista de Cursos</h1>

		<button class="pure-button pure-button-primary" onclick="add_ctCurso()">
			<i class="fa fa-plus"></i> Agregar Curso
		</button>
		<br>
		<table class="pure-table pure-table-bordered pure-table-striped">
			<thead>
				<tr>
					<th width="4%">No.</th>
					<th width="12%">Nombre</th>					
					<th width="12%">Cupo Minimo</th>
					<th width="12%">Cupo Maximo</th>
					<th width="12%">Precio</th>
					<th width="12%">IVA</th>
					<th width="12%">Total</th>
					<th width="12%">Estatus</th>
					<th width="36%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lista_ctCurso}" var="ctCurso">
					<tr>
						<td><c:out value="${ctCurso.iIdCurso}" /></td>
						<td><c:out value="${ctCurso.cNombre}" /></td>						
						<td><c:out value="${ctCurso.iMinCup}" /></td>
						<td><c:out value="${ctCurso.iMaxCup}" /></td>
						<td><c:out value="${ctCurso.dePrecio}" /></td>
						<td><c:out value="${ctCurso.deIva}" /></td>
						<td><c:out value="${ctCurso.deTotal}" /></td>
						<td><c:if test="${ctCurso.lEstatus == true}"><c:out value="Activo" /></c:if>
						<c:if test="${ctCurso.lEstatus == false}"><c:out value="Desactivo" /></c:if></td>

						<td><nobr>

								<button class="pure-button pure-button-primary"
									onclick="edit_ctCurso('${ctCurso.iIdCurso}');">
									<i class="fa fa-pencil"></i> Editar
								</button>

								<a class="pure-button pure-button-primary"
									onclick="return confirm('�Desea Eliminar el usuario  selecionado?');"
									href="ctCurso/remove/${ctCurso.iIdCurso}"> 
									<i class="fa fa-times"></i>Eliminar
								</a>

							</nobr></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<!--  It is advised to put the <script> tags at the end of the document body so they don't block rendering of the page -->
	<script type="text/javascript"
		src='<c:url value="/resources/js/lib/jquery-1.10.2.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/lib/jquery-ui-1.10.4.custom.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/lib/jquery.ui.datepicker.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/ctCurso.js"/>'></script>
</body>
</html>