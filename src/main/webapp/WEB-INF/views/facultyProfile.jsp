<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="edu.ben.template.model.User" %>  
<%@ page import="java.util.ArrayList" %>   
<%@ page import="edu.ben.template.dao.UserDao" %>
<%@ page isELIgnored="false" %>

<jsp:useBean id="user" class="edu.ben.template.model.User" scope="session" />
<%
	

	ArrayList<User> students;
	if (request.getAttribute("student") != null) {
		students = (ArrayList<User>) request.getAttribute("student");
	} else {
		students = new ArrayList<User>();
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
<link href="content/css/fileUpload.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<br>

	<!-- Start of Profile -->
	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-2 ">

				<p class=" text-info"><%=new java.util.Date()  %></p>
			</div>
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">

				<!-- Profile Picture -->
				<div style="border-color:black" class="panel panel-info">
					<div style="background-color:red" style="color:red" class="panel-heading"><a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" 
						type="button" class="btn btn-sm btn-warning"> </a>
						<h3 class="panel-title"><%user.getFirstName(); %>&nbsp;<%user.getLastName(); %></h3>
					</div>
					
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<!-- Grab picture from database. -->
								<img alt="User Pic" src="content/img/ernieEagle.jpg"
									class="img-circle img-responsive" height=1000px width=1000px>
								<!-- <form method="POST" enctype="multipart/form-data"
									action="/upload">
									 <font size="2">File to upload:</font> <input type="file" name="file"><br />
									<input type="submit" value="Upload"> <font size="2">Press here to
									upload the file!</font> 
								</form> -->

							</div>




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
											<td>Areas of interest:</td>
											<td><% %></td>
											
										</tr>

										<tr>
										<tr>
											<td>Biography:</td>
											<td><% %></td>
											
										</tr>
										<tr>
											<td>Experiences:</td>
											<td><% %></td>
											
										</tr>
										<tr>
											<td>Email</td>
											<td><a href="mailfrom:info@support.com"><%user.getEmail(); %></a></td>
											<td><a href="mailfrom:info@support.com"><%user.getPersonalEmail(); %></a></td>
									</tbody>
								</table>


 								<a href="#" class="btn btn-primary">Settings</a>
 								<a href="#" class="btn btn-primary">Create Job Posting</a>
 								<br>
								<br> 
								<form action="fileUpload" method="post">
									<div class="fileUpload btn btn-primary">
     									<span>Upload Profile Picture</span>
     									<input type="file" class="upload" />
 									</div>
									<button class="btn btn-primary" name="Submit"
									value="Submit" type="Submit">Submit</button> 
							</form>
								
							</div>
						</div>
					</div>
					<div class="panel-footer">
					</div>
				</div>
			</div>
		</div>

	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>