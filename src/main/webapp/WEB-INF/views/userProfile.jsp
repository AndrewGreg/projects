<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import= "edu.ben.template.model.User"%>
<%User currentUser = (User) request.getAttribute("currentUser"); %>

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
	<br>

	<!-- Start of Profile -->
	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
				
				<br>
				<!-- 	<p class=" text-info">May 05,2014,03:00 pm</p> -->
			</div>
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad"><h4>
					<A style="float: right;" href="/logout"><font color="Red">Logout</font></A> &nbsp;</h4>
					
				<div style="border-color:black" class="panel panel-info">
					<div style="background-color:red" class="panel-heading">
						<h3 class="panel-title"><font color="White"><%=currentUser.getFirstName()%> &nbsp;<%=currentUser.getLastName()%></font></h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<!-- Grab picture from database. -->
								<img alt="User Pic" src="content/img/BenedictineLogo.gif"
									class="img-circle img-responsive" height=1000px width=1000px>
							</div>
							<br>


							<!-- Start of information -->
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
								
									<tbody>
										<tr>
											<td>Current Occupation:</td>
											<%if(currentUser.getOccupation() != null){ %>
											<td><%=currentUser.getOccupation()%></td>
											<%}else{ %>
											<td>None</td>
											<%} %>
										</tr>
										<tr>
											<td>Year Expected to Graduate:</td>
											<td><%=currentUser.getGraduationYear()%></td>
										</tr>
										<tr>
											<td>Areas of interest:</td>
											<td><%=currentUser.getMajor()%></td>
										</tr>

										<tr>
										<tr>
											<td>Biography:</td>
											<%if(currentUser.getBio() != null){ %>
											<td><%=currentUser.getBio()%></td>
											<%}else{ %>
											<td>None</td>
											<%} %>
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
											<td>Email</td>
											<td><a href="mailfrom:info@support.com"><%=currentUser.getEmail()%></a></td>
									</tbody>
								</table>

								
								<br>
								<br>
								<form action="userProfile" method="post">	
									<div style="background-color:black;" class="fileUpload btn btn-primary">
    									<span>Upload Profile Picture</span>
    									<input type="file" class="upload" />
									</div>
									<br>
									<br>
									<div style="background-color:black;" class="fileUpload btn btn-primary">
    									<span>Upload Resume</span>
    									<input type="file" class="upload" />		
									</div>
									<button style="background-color:black;" class="btn btn-primary" name="Upload" 
										value="Upload" type="Submit">Upload</button>
								</form>
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