<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.ben.template.dao.UserDao"%>
<%@ page isELIgnored="false"%>

<%
	
	
<%
		
	ArrayList<Alumni> alumni = new ArrayList<Alumni>(); 
	alumni = (ArrayList<Alumni>) request.getAttribute("alumni");
	
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

						<th><font color="White">First</font></th>
						<th><font color="white">Last</font></th>
						<th><font color="white">Year Graduated</font></th>
						<th><font color="white">Degree</font></th>
						

					</tr>

				</thead>
				
				<tbody>
					<% for (int i = 0; i < alumni.size(); i++) { %>
						<%if (alumni.get(i).getRole() == 2) {%>
							
					<tr>
						<td><font color="White"><%=(alumni.get(i).getFirst())%></font></td>
						<td><font color="White"><%=(alumni.get(i).getLast())%></font></td>
						<td><font color="White"><%=(alumni.get(i).getYearGraduated())%></font></td>
						<td><font color="White"><%=(alumni.get(i).getDegree())%></font></td>
					</tr>

					<%
						}
					}
					%>
					


				</tbody>
			</table>
				<ul class="pagination">
			<%int i = 0; %>
			<%while(i < alumni.size() /15 + 1) {%>
				
   					 <li><a href="/alumniDirectory?page=<%=i%>"><%=++i%></a></li>
    				<!--  <li><a href="/alumniDirectory?page=1">2</a></li>
     				 <li><a href="/alumniDirectory?page=2">3</a></li>
    				 <li><a href="/alumniDirectory?page=3">4</a></li>
    				 <li><a href="/alumniDirectory?page=4">5</a></li> -->
    				 
  
    		<%} %>
   
  				</ul>
		</h3>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<!--/#footer-->
</body>
</html>