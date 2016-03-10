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
		<div class="content container directory-container">
			<div class="page-wrapper">
				<header class="page-heading clearfix">
				<h1 class="heading-title pull-left">Alumni Directory</h1>
				<div class="breadcrumbs pull-right">
					<ul class="breadcrumbs-list">
						<li class="breadcrumbs-label">You are here:</li>
						<li><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
						<li class="current">Alumni Directory</li>
					</ul>
				</div>
				<!--//breadcrumbs--> </header>
				<div class="page-content">

					<div class="row">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>#</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>Mark</td>
										<td>Otto</td>
										<td>@mdo</td>
									</tr>
									<tr>
										<td>2</td>
										<td>Jacob</td>
										<td>Thornton</td>
										<td>@mdo</td>
									</tr>
									<tr>
										<td>3</td>
										<td>Larry</td>
										<td>the Bird</td>
										<td>@fat</td>
									</tr>
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