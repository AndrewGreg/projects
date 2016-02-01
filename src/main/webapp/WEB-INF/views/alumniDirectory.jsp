<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<%-- <%
		
	ArrayList<Alumni> alumni = new ArrayList<Alumni>(); 
	alumni = (ArrayList<Alumni>) request.getAttribute("alumni");
	
%>  --%>
 
<!DOCTYPE html>
<html>
<head>
<jsp:include page="navBar.jsp" />
</head>
<body background=" ">
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
					<%-- <%
						int i = 0;
					%>
					<%
						while (i < alumni.size()) {
					%>

					<tr>
						<td><font color="White"><%=(alumni.get(i).getFirst())%></font></td>
						<td><font color="White"><%=(alumni.get(i).getLast())%></font></td>
						<td><font color="White"><%=(alumni.get(i).getYearGraduated())%></font></td>
						<td><font color="White"><%=(alumni.get(i).getDegree())%></font></td>
					</tr>

					<%
						i++;
					%>
					<%
						}
					%> --%>


				</tbody>
			</table>
		</h3>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<!--/#footer-->
</body>
</html>