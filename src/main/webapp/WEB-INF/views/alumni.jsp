<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<User> alumni;
	if (request.getAttribute("alumni") != null) {
		alumni = (ArrayList<User>) request.getAttribute("alumni");
	} else {
		alumni = new ArrayList<User>();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title>Alumni Directory</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />
		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper table-container">
				<header class="page-heading clearfix" style="margin-bottom: 10px !important;">
				<h1 class="heading-title pull-left">Alumni Directory</h1>
				<div class="breadcrumbs pull-right">
					<ul class="breadcrumbs-list">
						<li class="breadcrumbs-label">You are here:</li>
						<li><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
						<li class="current">Alumni Directory</li>
					</ul>
				</div>
				<!--//breadcrumbs--> </header>
				<div class="page-content table-content">

					<div class="row">
						<div class="table-responsive">
							<div class="form-group pull-right">
								<input type="text" class="search form-control"
									placeholder="Search" style="margin-top: 5px;">
							</div>
							<span class="counter pull-right"></span>
							<table class="table table-hover results">
								<thead>
									<tr>
										<th>Profile</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Major</th>
										<th>Grad Year</th>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i> No result</td>
									</tr>
								</thead>
								<tbody>
									<%
										if (alumni.size() == 0) {
									%>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i>There are no
											available alumni.</td>
									</tr>

									<%
										} else {
											for (int i = 0; i < alumni.size(); i++) {
									%>
									<tr class='clickable-row row-link'
										data-href='/user/<%=alumni.get(i).getId()%>'
										<%if (i % 2 == 1) {%> style="background-color: #E8E8E8" <%}%>>
										<td><img id="empty-profile-pic"
											src="/content/img/empty-profile.png"
											alt="Empty profile picture"></td>
										<td><%=alumni.get(i).getFirstName()%></td>
										<td><%=alumni.get(i).getLastName()%></td>
										<td><%=alumni.get(i).getMajorAtIndex(0).getName()%></td>
										<td><%=alumni.get(i).getGraduationYear()%></td>
									</tr>

									<%
										}
										}
									%>
								</tbody>
							</table>
							<!--//table-->
						</div>
						<!--//table-responsive-->
					</div>

				</div>
			</div>
		</div>

		<!--//wrapper-->
		<jsp:include page="footerTemplate.jsp" />
</body>
</html>