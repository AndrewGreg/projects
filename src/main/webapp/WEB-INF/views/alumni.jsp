<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="edu.ben.template.dao.UserDao" %>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<User> alumni;
	if (request.getAttribute("alumni") != null) {
		alumni = (ArrayList<User>) request.getAttribute("alumni");
	} else {
		alumni = new ArrayList<User>();
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Alumni Directory</title>

<jsp:include page="headerTemplate.jsp" />

</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />
		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper ben-container">
				<header class="page-heading clearfix pageHeading">
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
							<table id= indextable class="table table-hover results">
								<thead>
									<tr>
										<th><a href="javascript:SortTable(1,'T');">Profile</a></th>
										<th><a href="javascript:SortTable(1,'T');">First Name</a></th>
										<th><a href="javascript:SortTable(2,'T');">Last Name</a></th>
										<th><a href="javascript:SortTable(3,'T');">Major</a></th>
										<th><a href="javascript:SortTable(4,'N');">Grad Year</a></th>
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
										<td align="center"><img id="empty-profile-pic"
											src="/content/img/empty-profile.png"
											alt="Empty profile picture"></td>
										<td align="center"><%=alumni.get(i).getFirstName()%></td>
										<td align="center"><%=alumni.get(i).getLastName()%></td>
										<td align="center"><%=alumni.get(i).getMajorAtIndex(0).getName()%></td>
										<td align="center"><%=alumni.get(i).getGraduationYear()%></td>
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
										while (i < alumni.size() / 15 + 1) {
									%>
									<li><a href="/alumni?page=<%=i%>"><%=++i%></a></li>
									<li><a href="/alumni?page=<%=i++%>"><%=i++%></a></li>
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
	<!--//wrapper-->
	<jsp:include page="footerTemplate.jsp" />
	
	<script type="text/javascript" src="/content/jQuery/sortColumns.js"></script>
	
</body>

</html>