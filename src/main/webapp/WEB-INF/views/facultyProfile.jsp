<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="edu.ben.template.model.User" %>  
<%@ page import="java.util.ArrayList" %>   
<%@ page import="edu.ben.template.dao.UserDao" %>
<%@ page isELIgnored="false" %>

<%User currentUser = (User) request.getAttribute("facultyUser"); %>
<%
	

	ArrayList<User> students;
	if (request.getAttribute("student") != null) {
		students = (ArrayList<User>) request.getAttribute("student");
	} else {
		students = new ArrayList<User>();
	} 
	
	ArrayList<User> faculty;
	if (request.getAttribute("faculty") != null) {
		faculty = (ArrayList<User>) request.getAttribute("faculty");
	} else {
		faculty = new ArrayList<User>();
	} 
	
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
<jsp:include page="header.jsp" />
</head>

<body>
	<jsp:include page="navBar.jsp" />
	<br>

	<!-- Start of Profile -->
	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-2 ">
			</div>
		
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
			
				<!-- Profile Picture -->
				<div style="border-color:black" class="panel panel-info">
					<div style="background-color:#990b0b" class="panel-heading">
						<h3 class="panel-title"><font color="White"><%=currentUser.getFirstName()%> &nbsp;<%=currentUser.getLastName()%></font></h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<!-- Grab picture from database. -->
								<img alt="User Pic" src="/content/img/BenedictineLogo.gif"
									class="img-circle img-responsive" height=1000px width=1000px>
							</div>
							<br>




							<!-- Start of information -->
							<div class=" col-md-9 col-lg-9 ">

								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Amount of Students Registered:</td>
											<td><%=students.size() %></td>
											
										</tr>
										<tr>
											<td>Amount of Alumni Registered:</td>
											<td><%=alumni.size() %></td>
											
										</tr>
										<tr>
											<td>Amount of Faculty:</td>
											<td><%=faculty.size()%></td>
										</tr>

										<tr>
										
										<tr>
											<td>Benedictine Email:</td>
											<td><a href="mailfrom:info@support.com"><%=currentUser.getEmail()%></a></td>
											<tr>
											<td>Personal Email:</td>
											<td><a href="mailfrom:info@support.com"><%=currentUser.getPersonalEmail()%></a></td>
										</tr>
										<tr>
											<td>Experiences:</td>
											<%if(currentUser.getExperience() != null){ %>
											<td><%=currentUser.getExperience()%></td>
											<%}else{ %>
											<td>None</td>
											<%} %>
										</tr>
										<tr>
											<td>Biography:</td>
											<%if(currentUser.getBio() != null){ %>
											<td><%=currentUser.getBio()%></td>
											<%}else{ %>
											<td>None</td>
											<%} %>
										
									</tbody>
								</table>

								<br>
								<br>
								
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a href="/edit" style="background-color:black;" class="btn btn-primary">Edit Account</a> 
						<a href="mailfrom:<%currentUser.getEmail();%>" style="float: right; background-color:black;" class="btn btn-primary"> Send Email</a>
					</div>

				</div>
			</div>
		</div>	
	</div>

<jsp:include page="footer.jsp" />

</body>
</html>