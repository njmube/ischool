<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

		


<c:url var="actionUrl" value="ctPrograma/add" />


<form:form id="Form_ctPrograma" commandName="ctPrograma" method="post"
	action="${actionUrl}" class="pure-form pure-form-aligned">

	<fieldset>
		<legend></legend>


		<table>
			<tr>
				<td><form:label path="iIdPrograma">
						<spring:message text="ID Programa" />
					</form:label></td>
				<td><form:input path="iIdPrograma" readonly="true" /></td>
			</tr>


			<tr>
				<c:choose>
					<c:when test="${vlHabila_menu == true}">
						<td><form:label path="iIdMenu">
								<spring:message text="Id Menu" />
							</form:label></td>
						<td><form:input path="iIdMenu" readonly="true" /></td>


						<td><form:label path="menu.cMenu">
								<spring:message text="Menu" />
							</form:label></td>
						<td><form:input path="menu.cMenu" readonly="true" /></td>

					</c:when>
					<c:otherwise>
						<td><form:label path="iIdMenu">
								<spring:message text="Menu" />
							</form:label></td>
						<td><form:select path="iIdMenu" items="${lista_ctMenu}"
								itemValue="iIdMenu" itemLabel="cMenu"
								disabled="${vlHabila_menu}" /></td>
					</c:otherwise>
				</c:choose>


			</tr>


			<tr>
				<td><form:label path="cNombre">
						<spring:message text="Nombre" />
					</form:label></td>
				<td><form:input path="cNombre" /></td>
			</tr>


			<tr>
				<td><form:label path="cPrograma">
						<spring:message text="Programa" />
					</form:label></td>
				<td><form:input path="cPrograma" /></td>
			</tr>



			<tr>
				<td><form:label path="lActivo">
						<spring:message text="Activo" />
					</form:label></td>
				<td><form:checkbox path="lActivo" /></td>

			</tr>
		</table>

	</fieldset>


</form:form>