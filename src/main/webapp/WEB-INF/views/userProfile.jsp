<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="edu.ben.template.model.User"%>

<%
	User currentUser = (User) request.getAttribute("profileUser");
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
	<!-- 	<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
				<br>
			</div>
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
					 -->
				<div style="border-color:black" class="panel panel-info">
					<div style="background-color:#990b0b" class="panel-heading">
						<h2 class="panel-title"><font color="White"><%=currentUser.getFirstName()%> &nbsp;<%=currentUser.getLastName()%>,&nbsp;
						<%if(currentUser.getTitle() != null) {%>
						<%=currentUser.getTitle()%>
						<%}else{ %>
						&nbsp;
						<%} %>
						</font></h2>
						
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<!-- Grab picture from database. -->
								<img alt="User Pic" src="/content/img/ernieEagle.jpg"
									class="img-rounded img-responsive" height=1000px width=500px>
									
								<br>	
							<p>Work Phone:&nbsp;<%=currentUser.getEmail()%>
							<br>
							Email:&nbsp;<a href="mailfrom:info@support.com"><%=currentUser.getEmail()%></a></p>
							</div>
							


							<!-- Start of information -->
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-striped">
								
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
											<%if(currentUser.getInterest() != null){ %>
											<td><%=currentUser.getInterest()%></td>
											<%}else{ %>
										</tr>
										<%} %>

										<tr>
										<tr>
											<td>Benedictine Email:</td>
											<td><a href="mailfrom:info@support.com"><%=currentUser.getEmail()%></a></td>
											<tr>
											<td>Personal Email:</td>
											<td><a href="mailfrom:info@support.com"><%=currentUser.getPersonalEmail()%></a></td>
									
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
											
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="panel-footer">
					
						<a href="/edit" style="background-color:black;" class="btn btn-primary">Edit Account</a> 
						
						
					</div>

				</div>
			</div>

<jsp:include page="footer.jsp" />

</body>
</html>