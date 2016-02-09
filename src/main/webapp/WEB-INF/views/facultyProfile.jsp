<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="edu.ben.template.model.User" %>  
<%@ page import="java.util.ArrayList" %>   
<%@ page import="edu.ben.template.dao.UserDao" %>
<%@ page isELIgnored="false" %>

<jsp:useBean id="user" class="edu.ben.template.model.User" scope="session" />
 		
	
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
			<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
				<A href="edit.jsp">Edit Profile</A> <A href="logout.jsp">Logout</A>

				<!-- 	<p class=" text-info">May 05,2014,03:00 pm</p> -->
			</div>
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">

				<!-- Profile Picture -->
				<div class="panel panel-info">
					<div class="panel-heading">
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
											<td>Amount of Students Registered</td>
											<td><% %></td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <br> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>
										<tr>
											<td>Amount of Students Registered</td>
											<td><%user.getGraduationYear(); %></td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <br> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>
										<tr>
											<td>Areas of interest:</td>
											<td><% %></td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <br> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>

										<tr>
										<tr>
											<td>Biography:</td>
											<td><% %></td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <br> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>
										<tr>
											<td>Experiences:</td>
											<td><% %></td>
											<td><a href="edit.html"
												data-original-title="Edit this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-warning"><i
													class="glyphicon glyphicon-edit"></i></a> <a
												data-original-title="Remove this user" data-toggle="tooltip"
												type="button" class="btn btn-sm btn-danger"><i
													class="glyphicon glyphicon-remove"></i></a></td>
										</tr>
										<tr>
											<td>Email</td>
											<td><a href="mailfrom:info@support.com"><%user.getEmail(); %></a></td>
											<td><a href="mailfrom:info@support.com"><%user.getPersonalEmail(); %></a></td>
									</tbody>
								</table>

								<a href="#" class="btn btn-primary">Settings</a> <a href="#"
									class="btn btn-primary">Create Job Posting</a>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a data-original-title="Broadcast Message" data-toggle="tooltip"
							type="button" class="btn btn-sm btn-primary"><i
							class="glyphicon glyphicon-envelope"></i></a> <span
							class="pull-right"> <a href="edit.html"
							data-original-title="Edit this user" data-toggle="tooltip"
							type="button" class="btn btn-sm btn-warning"><i
								class="glyphicon glyphicon-edit"></i></a> <a
							data-original-title="Remove this user" data-toggle="tooltip"
							type="button" class="btn btn-sm btn-danger"><i
								class="glyphicon glyphicon-remove"></i></a>
						</span>
					</div>

				</div>
			</div>
		</div>

	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>