<%@page import="es.curso.repositories.*"%>
<%@page import="es.curso.negocio.*"%>
<%@page import="java.util.ArrayList"%>
<%
PersonaRepository repo = new PersonaRepository();
ArrayList<Persona> lista = repo.buscarTodos();
%>
<!DOCTYPE html>
<html>
<body>
	<table>
		<%for (Persona p : lista) {  %>

		<tr>
			<td><%=p.getNombre()%></td>
			<td><%=p.getApellidos()%></td>
			<td><%=p.getEdad()%></td>

		</tr>
		<%}%>
	</table>
</body>
</html>