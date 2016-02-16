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
<body>
	<jsp:include page="navBar.jsp" />

	<div align="center">
		<br> <br> <br> <br>
		<h3>
			<table border=1>
				<col width="300">
 				<col width="300">
 				<col width="300">
 				<col width="300">
				<thead>
					<tr>

						<th><font color="Red">First</font></th>
						<th><font color="Red">Last</font></th>
						<th><font color="Red">Year Graduated</font></th>
						<th><font color="Red">Degree</font></th>

					</tr>

				</thead>
				
				<tbody>
					<% for (int i = 0; i < alumni.size(); i++) { %>
						<%if (alumni.get(i).getRole() == 2) {%>
							
					<tr>
						<td><font color="Black"><%=(alumni.get(i).getFirstName())%></font></td>
						<td><font color="Black"><%=(alumni.get(i).getLastName())%></font></td>
						<td><font color="Black"><%=(alumni.get(i).getGraduationYear())%></font></td>
						<td><font color="Black"></font></td>
					</tr>

					<%
						}
					}
					%>


				</tbody>
			</table>
				<ul class="pagination">
			
   					 <li><a href="/alumniDirectory?page=0">1</a></li>
    				 <li><a href="/alumniDirectory?page=1">2</a></li>
     				 <li><a href="/alumniDirectory?page=2">3</a></li>
    				 <li><a href="/alumniDirectory?page=3">4</a></li>
    				 <li><a href="/alumniDirectory?page=4">5</a></li>
    				 
  				</ul>
		</h3>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<!--/#footer-->
</body>
</html>