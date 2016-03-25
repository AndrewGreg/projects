<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.ben.template.model.User"%>
<%@page import="edu.ben.template.model.Title"%>
<%
	User currentUser = (User) request.getAttribute("currentUser");
	User profileUser = (User) request.getAttribute("profileUser");
	Title userTitle = (Title) request.getAttribute("title");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title>Profile</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container">
			<div class="content container content-container">
				<div class="page-wrapper table-container">
					<header class="page-heading clearfix"> <img
						id="profile-pic" src="/content/img/empty-profile.png"
						alt="Profile Picture">
					<h1 class="heading-title">
						<%=profileUser.getFirstName()%>
						<%=profileUser.getLastName()%></h1>
					<div class="pull-right">
						<%if(currentUser.getRole() == 4){%>
							<form action="/deleteUser" method="POST"
							name="deleteUser">
								<button type="submit" class="btn btn-primary">Delete User</button>
							</form>
							
						<%}	%>
					</div>
					
					<div class="breadcrumbs pull-right" id="breadcrumbs-profile">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
							<%
								if (currentUser.getId() != profileUser.getId()) {
							%>
							<li><a href="/alumni">Alumni</a><i class="fa fa-angle-right"></i></li>
							<%
								}
							%>
							<li class="current">Profile</li>
						</ul>
					</div>
					<!--//breadcrumbs--> </header>
					<div class="page-content">
						<div class="row page-row">
							<div class="jobs-wrapper col-md-8 col-sm-7">
								<div class="panel panel-default page-row">
									<div class="panel-heading">
										<div
											class="meta col-md-4 col-sm-6 col-xs-6 text-right pull-right"
											id="editLink">
											<%
												if (currentUser.getId() == profileUser.getId()) {
											%>
											<a href="/edit"><span class="label label-info"><small><i
														class="fa fa-pencil"></i> Edit Information</small></span></a>
											<%
												}
											%>

										</div>
										<h3 class="panel-title">
											<a>Profile Information</a>
										</h3>

									</div>
									<ul class="list-group">
										<li class="list-group-item"><strong>Role:</strong> <%
 	if (profileUser.getRole() == 1) {
 %>Student <%
 	} else if (profileUser.getRole() == 2) {
 %>Alumni <%
 	} else if (profileUser.getRole() == 3) {
 %>Faculty <%
 	} else {
 %>Administrator <%
 	}
 %></li>
										<li class="list-group-item"><strong>Major:</strong> <%
 	if (profileUser.getMajorAtIndex(0) != null && profileUser.getMajorAtIndex(0).getName() != null) {
 %><%=profileUser.getMajorAtIndex(0).getName()%> <%
 	} else {
 %>None <%
 	}
 %></li>

										<!-- ADD MINOR AND CONCENTRATION -->

										<li class="list-group-item"><strong>Graduation
												Year:</strong> <%
 	if (profileUser.getGraduationYear() != 1) {
 %><%=profileUser.getGraduationYear()%> <%
 	} else {
 %>No graduation year.<%
 	}
 %></li>
										<li class="list-group-item"><strong>Occupation:</strong>
											<%
												if (profileUser.getOccupation() != null) {
											%><%=profileUser.getOccupation()%> <%
 	} else {
 %>No user occupation.<%
 	}
 %></li>
										<li class="list-group-item"><strong>Company:</strong> <%
 	if (profileUser.getCompany() != null) {
 %><%=profileUser.getCompany()%> <%
 	} else {
 %>No user company.<%
 	}
 %></li>
										<li class="list-group-item"><strong>Experience:</strong>
											<%
												if (profileUser.getExperience() != null) {
											%><%=profileUser.getExperience()%> <%
 	} else {
 %>No user experience.<%
 	}
 %></li>
									</ul>
									<div class="panel-body">
										<strong>Biography:</strong>
										<%
											if (profileUser.getBiography() != null) {
										%><%=profileUser.getBiography()%>
										<%
											} else {
										%>No user bio.<%
											}
										%>
									</div>
									<div class="panel-footer"></div>
								</div>
								<!--//panel-->
							</div>
							<!--//jobs-wrapper-->
							<aside
								class="page-sidebar  col-md-3 col-md-offset-1 col-sm-4 col-sm-offset-1">
							<section class="widget">
							<h3 class="title">Contact</h3>
							<p>
								<i class="fa fa-star"></i>Title:
								<%
									if (userTitle != null && userTitle.getName() != null) {
								%>
								<%=userTitle.getName()%>
								<%
									} else {
								%>None<%
									}
								%>
							</p>
							<p>
								<i class="fa fa-user"></i>First Name:
								<%
									if (profileUser.getFirstName() != null) {
								%>
								<%=profileUser.getFirstName()%>
								<%
									} else {
								%>None<%
									}
								%>
							</p>
							<p>
								<i class="fa fa-user"></i>Last Name:
								<%
									if (profileUser.getLastName() != null) {
								%>
								<%=profileUser.getLastName()%>
								<%
									} else {
								%>None<%
									}
								%>
							</p>
							<p>
								<i class="fa fa-certificate"></i>Suffix:
								<%
									if (profileUser.getSuffix() != null) {
								%>
								<%=profileUser.getSuffix()%>
								<%
									} else {
								%>None<%
									}
								%>
							</p>
							<p class="tel">
								<i class="fa fa-phone"></i>Tel: None
							</p>

							<p class="email">
								<i class="fa fa-envelope"></i>Ben E-Mail: <a
									href="<%if (profileUser.getEmail() != null) {%>mailto:<%=profileUser.getEmail()%>? <%} else {%> # <%}%>">
									<%
										if (profileUser.getEmail() != null) {
									%> <%=profileUser.getEmail()%> <%
 	} else {
 %> None <%
 	}
 %>
								</a>
							</p>
							<p class="email">
								<i class="fa fa-envelope"></i>Personal E-mail: <a
									href="<%if (profileUser.getPersonalEmail() != null) {%>mailto:<%=profileUser.getPersonalEmail()%>? <%} else {%> # <%}%>">
									<%
										if (profileUser.getPersonalEmail() != null) {
									%> <%=profileUser.getPersonalEmail()%> <%
 	} else {
 %> None <%
 	}
 %>
								</a>
							</p>

							</section><!--//widget--> </aside>


						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!--//wrapper-->
	<jsp:include page="footerTemplate.jsp" />
</body>
</html>