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
<style>
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: red;
    color: white;
}
</style>
</head>
</head>
<body>
	<jsp:include page="navBar.jsp" />

	<div class = "container">
		<br> <br> 
		<h3>
		<div class = "well">
			<table class ="table">
				<col width="300">
 				<col width="300">
 				<col width="300">
 				<col width="300">
				<thead>
					<tr>

						<th><font color="White">First</font></th>
						<th><font color="White">Last</font></th>
						<th><font color="White">Year Graduated</font></th>
						<th><font color="White">Degree</font></th>

					</tr>

				</thead>
			
				<tbody>
					<% for (int i = 0; i < alumni.size(); i++) { %>
						<%if (alumni.get(i).getRole() == 2) {%>
							
					<tr>
						<td><a href=" /user/<%=alumni.get(i).getId()%>"><font color="Black"><%=(alumni.get(i).getFirstName())%></font></a></td>
						<td><font color="Black"><%=(alumni.get(i).getLastName())%></font></td>
						<td><font color="Black"><%=(alumni.get(i).getGraduationYear())%></font></td>
					  <%if(alumni.get(i).getMajor().get(0).getName() != null){ %> 
						<td><font color="Black"><%=(alumni.get(i).getMajor().get(0).getName())%></font></td>
					<%} else{%>
						<td><font color="Black">None</font></td>
						<%} %>
					</tr> 

					<%
						}
					}
					%>


				</tbody>
			</table>
		
			</div>
			<div class ="row">
			<div class = "col-md-4" style = "width: 1215px; padding-left: 550px;">
				<ul class="pagination">
			<%int i = 0; %>
			<%while(i < alumni.size() /15 + 1) {%>
   					 <li><a href="/alumniDirectory?page=<%=i%>"><%=++i%></a></li>
    		<%} %>
   
  				</ul>
  				</div>
  				</div>
		</h3>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<!--/#footer-->
</body>
</html>
