<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.ben.template.model.User"%>
<%@page import="edu.ben.template.model.Title"%>
<%
	User currentUser = (User) request.getAttribute("profileUser");
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
						<%=currentUser.getFirstName()%>
						<%=currentUser.getLastName()%></h1>
					<div class="breadcrumbs pull-right" id="breadcrumbs-profile">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
							<li class="current">Profile</li>
						</ul>
					</div>
					<!--//breadcrumbs--> </header>
					<div class="page-content">
						<div class="row page-row">
							<div class="jobs-wrapper col-md-8 col-sm-7">
								<div class="panel panel-default page-row">
									<div class="panel-heading">
										<h3 class="panel-title">
											<a>Profile Information</a>
										</h3>

									</div>
									<ul class="list-group">
										<li class="list-group-item"><strong>Role:</strong> <%
 	if (currentUser.getRole() == 1) {
 %>Student <%
 	} else if (currentUser.getRole() == 2) {
 %>Alumni <%
 	} else if (currentUser.getRole() == 3) {
 %>Faculty <%
 	} else {
 %>Administrator <%
 	}
 %></li>
										<li class="list-group-item"><strong>Major:</strong> <%
 	if (currentUser.getMajorAtIndex(0) != null && currentUser.getMajorAtIndex(0).getName() != null) {
 %><%=currentUser.getMajorAtIndex(0).getName()%> <%
 	} else {
 %>None <%
 	}
 %></li>
 
 <!-- ADD MINOR AND CONCENTRATION -->
 
										<li class="list-group-item"><strong>Graduation
												Year:</strong> <%
 	if (currentUser.getGraduationYear() != 1) {
 %><%=currentUser.getGraduationYear()%> <%
 	} else {
 %>No graduation year.<%
 	}
 %></li>
										<li class="list-group-item"><strong>Occupation:</strong>
											<%
												if (currentUser.getOccupation() != null) {
											%><%=currentUser.getOccupation()%> <%
 	} else {
 %>No user occupation.<%
 	}
 %></li>
										<li class="list-group-item"><strong>Company:</strong> <%
 	if (currentUser.getCompany() != null) {
 %><%=currentUser.getCompany()%> <%
 	} else {
 %>No user company.<%
 	}
 %></li>
										<li class="list-group-item"><strong>Experience:</strong>
											<%
												if (currentUser.getExperience() != null) {
											%><%=currentUser.getExperience()%> <%
 	} else {
 %>No user experience.<%
 	}
 %></li>
									</ul>
									<div class="panel-body">
										<%
											if (currentUser.getBiography() != null) {
										%><%=currentUser.getBiography()%>
										<%
											} else {
										%>No user bio.<%
											}
										%>
									</div>
									<div class="panel-footer">
										<div class="row">
											<div
												class="meta col-md-4 col-sm-6 col-xs-6 text-right pull-right">
												<a href="/edit"><small>Edit Information</small></a>
											</div>
										</div>
									</div>
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
									if (currentUser.getFirstName() != null) {
								%>
								<%=currentUser.getFirstName()%>
								<%
									} else {
								%>None<%
									}
								%>
							</p>
							<p>
								<i class="fa fa-user"></i>Last Name:
								<%
									if (currentUser.getLastName() != null) {
								%>
								<%=currentUser.getLastName()%>
								<%
									} else {
								%>None<%
									}
								%>
							</p>
							<p>
								<i class="fa fa-certificate"></i>Suffix:
								<%
									if (currentUser.getSuffix() != null) {
								%>
								<%=currentUser.getSuffix()%>
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
									href="<%if (currentUser.getEmail() != null) {%>mailto:<%=currentUser.getEmail()%>? <%} else {%> # <%}%>">
									<%
										if (currentUser.getEmail() != null) {
									%> <%=currentUser.getEmail()%> <%
 	} else {
 %> None <%
 	}
 %>
								</a>
							</p>
							<p class="email">
								<i class="fa fa-envelope"></i> Personal E-mail: <a
									href="<%if (currentUser.getPersonalEmail() != null) {%>mailto:<%=currentUser.getPersonalEmail()%>? <%} else {%> # <%}%>">
									<%
										if (currentUser.getPersonalEmail() != null) {
									%> <%=currentUser.getPersonalEmail()%> <%
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
	</div>
	<!--//wrapper-->
	<jsp:include page="footerTemplate.jsp" />
</body>
</html>