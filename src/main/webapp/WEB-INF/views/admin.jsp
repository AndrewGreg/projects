<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<User> users;
	if (request.getAttribute("users") != null) {
		users = (ArrayList<User>) request.getAttribute("users");
	} else {
		users = new ArrayList<User>();
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Users</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />
		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper table-container">
				<header class="page-heading clearfix"
					style="margin-bottom: 10px !important;">
				<h1 class="heading-title pull-left">Manage Users</h1>
				<div class="breadcrumbs pull-right">
					<ul class="breadcrumbs-list">
						<li class="breadcrumbs-label">You are here:</li>
						<li><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
						<li class="current">Manage Users</li>
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
										<th><a href="javascript:SortTable(1,'T');">Profile</a></th>
										<th><a href="javascript:SortTable(1,'T');">First Name</a></th>
										<th><a href="javascript:SortTable(2,'T');">Last Name</a></th>
										<th><a href="javascript:SortTable(3,'T');">Role</a></th>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i> No result</td>
									</tr>
								</thead>
								<tbody>
									<%
										if (users.size() == 0) {
									%>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i>There are no
											available users.</td>
									</tr>

									<%
										} else {
											String role = "";
											
											for (int i = 0; i < users.size(); i++) {
												if(users.get(i).getRole() == 1){
													role = "Student";
												}else if(users.get(i).getRole() == 2){
													role = "Alumni";
												}else if(users.get(i).getRole() == 3){
													role = "Faculty";
												}else{
													role = "Admin";
												}
												
									%>
									
									<tr class='clickable-row row-link'
										data-href='/user/<%=users.get(i).getId()%>'
										<%if (i % 2 == 1) {%> style="background-color: #E8E8E8" <%}%>>
										<td><img id="empty-profile-pic"
											src="/content/img/empty-profile.png"
											alt="Empty profile picture"></td>
										<td><%=users.get(i).getFirstName()%></td>
										<td><%=users.get(i).getLastName()%></td>
										<td><%=role%></td>
									</tr>

									<%
											}
										}
									%>
								</tbody>
							</table>
							<!--//table-->
							<div class="col-md-4" style="width: 1215px; padding-left: 500px;">
								<ul class="pagination">
									<%
										int i = 0;
									%>
									<%
										while (i < users.size() / 15 + 1) {
									%>
									<li><a href="/allUsers?page=<%=i%>"><%=++i%></a></li>
									<li><a href="/allUsers?page=<%=i++%>"><%=i++%></a></li>
									<%
										}
									%>

								</ul>
							</div>
						</div>
						<!--//table-responsive-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footerTemplate.jsp" />
	
	<script type="text/javascript" src="/content/jQuery/sortColumns.js"></script>
	
</body>
</html>