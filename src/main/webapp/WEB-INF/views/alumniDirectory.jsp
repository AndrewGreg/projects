<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.ben.template.dao.UserDao"%>
<%@ page isELIgnored="false"%>

<%
	ArrayList<User> alumni;
	if (request.getAttribute("alumni") != null) {
		alumni = (ArrayList<User>) request.getAttribute("alumni");
	} else {
		alumni = new ArrayList<User>();
	}
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
</head>
<body background=" ">
	<jsp:include page="navBar.jsp" />

	<!-- Navigation Bar -->
	<div align="center">
		<br> <br> <br> <br>
		<h3>
			<table border=1>
				<thead>
					<tr>

						<th><font color="Red">First</font></th>
						<th><font color="Red">Last</font></th>
						<th><font color="Red">Year Graduated</font></th>
						<!-- <th><font color="Red">Degree</font></th> -->


					</tr>

				</thead>
				<tbody>
					<%
						for (int i = 0; i < alumni.size(); i++) {
					%>

					<tr>
						<td><font color="Black"><%=(alumni.get(i).getRole())%></font></td>
						<td><font color="Black"><%=(alumni.get(i).getFirstName())%></font></td>
						<td><font color="Black"><%=(alumni.get(i).getLastName())%></font></td>
						<td><font color="Black"><%=(alumni.get(i).getGraduationYear())%></font></td>
						 <%-- <td><font color="Black"><%=(alumni.get(i))%></font></td>  --%>
					</tr>

					<%
						}
					%>


				</tbody>
			</table>
		</h3>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<!--/#footer-->
</body>
</html>