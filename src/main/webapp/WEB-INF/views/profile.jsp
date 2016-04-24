<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.ben.template.model.User"%>
<%@page import="edu.ben.template.model.Title"%>
<%@page import="edu.ben.template.model.UploadFile"%>
<%
	UploadFile photo = (UploadFile) request.getAttribute("photo");
	User currentUser = (User) request.getAttribute("currentUser");
	User profileUser = (User) request.getAttribute("profileUser");
	Title userTitle = (Title) request.getAttribute("title");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
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
				<div class="page-wrapper table-container ben-container">D
					<header class="page-heading clearfix"> 
					<%
 						if (request.getAttribute("photo") == null) {
 					%> 
 						<img id="profile-pic"
							src="/Alumni-Tracker/content/img/empty-profile.png"
							alt="Profile Picture"> 
					<%
 						} else {
					 %> 
						<header class="page-heading clearfix"> 
						<img id="profile-pic"
							src="/Alumni-Tracker/getImage/<%=photo.getProfile().getId()%>.do" 
							alt="Profile Picture" width="128" height="128">
					<%
 						}
 					%>
						<h1 class="heading-title">
							<%=profileUser.getFirstName()%>
							<%=profileUser.getLastName()%></h1>
						<div class="pull-right">
							<%
								if (currentUser.getRole() == 4) {
							%>
							<form action="/Alumni-Tracker/deleteUser" method="POST"
								name="deleteUser">
								<button type="submit" class="btn btn-primary">Delete
									User</button>
							</form>

							<%
								}
							%>
						</div>

						<div class="breadcrumbs pull-right" id="breadcrumbs-profile">
							<ul class="breadcrumbs-list">
								<li class="breadcrumbs-label">You are here:</li>
								<li><a href="/Alumni-Tracker/">Home</a><i
									class="fa fa-angle-right"></i></li>
								<%
									if (currentUser.getId() != profileUser.getId()) {
								%>
								<li><a href="/Alumni-Tracker/alumni">Alumni</a><i
									class="fa fa-angle-right"></i></li>
								<%
									}
								%>
										<li class="current">Profile</li>
									</ul>
								</div>
								<!--//breadcrumbs-->
							</header>
							<div class="page-content">
								<div class="row page-row">
									<div class="jobs-wrapper col-md-8 col-sm-7">
										<div class="panel panel-default page-row">
											<div class="panel-heading">
												<div
													class="meta col-md-4 col-sm-6 col-xs-6 text-right pull-right"
													id="editLink">
													<%
														if (currentUser.getId() == profileUser.getId() || currentUser.getRole() == 4) {
													%>
													<a href="/Alumni-Tracker/edit/<%=profileUser.getId()%>"><span
														class="label label-info"><small><i
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
 %>Undecided <%
 	}
 %></li>
												<%
													if (profileUser.getMajorAtIndex(1) != null && profileUser.getMajorAtIndex(1).getName() != null) {
												%><li class="list-group-item"><strong> Double
														Major:</strong><%=profileUser.getMajorAtIndex(1).getName()%></li>
												<%
													}
												%>
												<%
													if (profileUser.getMajorAtIndex(2) != null && profileUser.getMajorAtIndex(2).getName() != null) {
												%><li class="list-group-item"><strong> Third
														Major:</strong><%=profileUser.getMajorAtIndex(2).getName()%></li>
												<%
													}
												%>

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

											<!-- If the profile id does not equal the current logged in user. -->
											<%
												if (currentUser.getId() != profileUser.getId()) {
											%>
											<p class="email">
												<i class="fa fa-envelope"></i>Ben E-Mail: <a
													href="<%if (profileUser.getEmail() != null) {%>
										mailto:<%=profileUser.getEmail()%>? 
										<%} else {%> # 
										<%}%>">
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
													href="<%if (profileUser.getPersonalEmail() != null) {%>
										mailto:<%=profileUser.getPersonalEmail()%>? 
										<%} else {%> # 
										<%}%>">
													<%
														if (profileUser.getPersonalEmail() != null) {
													%> <%=profileUser.getPersonalEmail()%> <%
 	} else {
 %> None <%
 	}
 %>
												</a>
											</p>
											<%
												} else {
											%>
											<p class="email">
												<i class="fa fa-envelope"></i>Ben E-Mail:
												<%
													if (profileUser.getEmail() != null) {
												%>
												<%=profileUser.getEmail()%>
												<%
													} else {
												%>
												None
												<%
													}
												%>
											</p>
											<p class="email">
												<i class="fa fa-envelope"></i>Personal E-mail:
												<%
													if (profileUser.getPersonalEmail() != null) {
												%>
												<%=profileUser.getPersonalEmail()%>
												<%
													} else {
												%>
												None
												<%
													}
												%>
											</p>
											<%
												}
											%>
										</section>
										<!--//widget-->

										<%
											if (currentUser.getId() == profileUser.getId()) {
										%>
										<section class="widget has-divider">
											<h3 class="section-heading text-highlight">Got any
												comments?</h3>

											<button type="button" class="btn btn-theme"
												data-toggle="modal" data-target="#testimonialModal">
												<i class="fa fa-comments"></i> Comments
											</button>

											<jsp:include page="testimonialModal.jsp" />

										</section>
										<%
											}
										%>
									</aside>
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