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
<!DOCTYPE html>
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
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current">Manage Users</li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>
				<a href="/Alumni-Tracker/register"><button type="button" class="btn btn-primary">Add
						User</button></a>
				<div style="margin-top: 20px">
					<form action="/Alumni-Tracker/massRegister" method="post" enctype="multipart/form-data">
						<label>Upload Multiple Users:</label>
						<input type="file" accept=".xls,.xlsx" name="multiple" id="" value="">
						<button type="submit" class="btn btn-primary">Upload</button>
					</form>
				</div>
				<div class="page-content table-content">

					<div class="row">
						<div class="table-responsive">
							<div class="form-group pull-right">
								<input type="text" class="search form-control"
									placeholder="Search" style="margin-top: 5px;">
							</div>
							<span class="counter pull-right"></span>
							<table id=indextable class="table table-hover results">
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
												//if(users.get(i).isActive()){
													if(users.get(i).getRole() == 1){
														role = "Student";
													}else if(users.get(i).getRole() == 2){
														role = "Alumni";
													}else if(users.get(i).getRole() == 3){
														role = "Faculty";
													}else if (users.get(i).getRole() == 4){
														role = "Admin";
													}else{
														role = "Role Error.";
													}

												String firstName = users.get(i) != null && users.get(i).getFirstName() != null
														? users.get(i).getFirstName() : "N/A";
												String lastName = users.get(i) != null && users.get(i).getLastName() != null
														? users.get(i).getLastName() : "N/A";
									%>

									<tr class='clickable-row row-link'
										data-href='/Alumni-Tracker/user/<%=users.get(i).getId()%>'
										<%if (i % 2 == 1) {%> 
										style="background-color: #E8E8E8" 
										<%}%>
										<%if (users.get(i).isHidden()){ %>
										style="background-color: red"
										<%} %>>
										<td><img id="empty-profile-pic"
											src="/Alumni-Tracker/content/img/empty-profile.png"
											alt="Empty profile picture"></td>
										<td><%=firstName%></td>
										<td><%=lastName%></td>
										<td><%=role%></td>
									</tr>

									<%

												}
											}
									//	}
									%>
								</tbody>
							</table>
							<!--//table-->
							<div class="col-md-4" style="width: 1215px; padding-left: 500px;">
								<ul class="pagination">
									<%
										int i = 0;
										int count = (Integer) request.getAttribute("listCount");
										int pages = (count - 1) / 15;
									%>
									<%
										while (i <= pages) {
									%>
									<li><a href="/Alumni-Tracker/allUsers?page=<%=i%>"><%=++i%></a></li>
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

	<script type="text/javascript"
		src="/Alumni-Tracker/content/jQuery/sortColumns.js"></script>

</body>
</html>