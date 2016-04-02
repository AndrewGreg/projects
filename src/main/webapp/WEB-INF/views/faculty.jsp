<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="edu.ben.template.dao.UserDao"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<User> faculty;
	if (request.getAttribute("faculty") != null) {
		faculty = (ArrayList<User>) request.getAttribute("faculty");
	} else {
		faculty = new ArrayList<User>();
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Faculty Directory</title>

<jsp:include page="headerTemplate.jsp" />

</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />
		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper ben-container">
				<header class="page-heading clearfix pageHeading">
					<h1 class="heading-title pull-left">Faculty Directory</h1>
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/">Home</a><i class="fa fa-angle-right"></i></li>
							<li class="current">Faculty Directory</li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>
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
										<th class="text-center" style="color: black;">Profile</th>
										<th class="text-center"><a href="javascript:SortTable(1,'T');">First Name <i class="fa fa-retweet"></i></a></th>
										<th class="text-center"><a href="javascript:SortTable(2,'T');">Last Name <i class="fa fa-retweet"></i></a></th>
										<th class="text-center"><a href="javascript:SortTable(3,'T');">Occupation <i class="fa fa-retweet"></i></a></th>
										<th class="text-center"><a href="javascript:SortTable(4,'N');">Major <i class="fa fa-retweet"></i></a></th>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i> No result</td>
									</tr>
								</thead>
								<tbody>
									<%
										if (faculty.size() == 0) {
									%>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i>There are no
											available faculty.</td>
									</tr>

									<%
										} else {
											for (int i = 0; i < faculty.size(); i++) {
												if (faculty.get(i).getRole() == 3) {
									%>
									<tr class='clickable-row row-link'
										data-href='/user/<%=faculty.get(i).getId()%>'>
										<td align="center"><img id="empty-profile-pic"
											src="/content/img/empty-profile.png"
											alt="Empty profile picture"></td>
										<td align="center"><%=faculty.get(i).getFirstName()%></td>
										<td align="center"><%=faculty.get(i).getLastName()%></td>
										<td align="center"><%=faculty.get(i).getOccupation()%></td>
										<td align="center"><%=faculty.get(i).getMajorAtIndex(0).getName()%></td>
									</tr>

									<%
										}
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
										int count = (int) request.getAttribute("facultyCount");
										int pages = (count - 1) / 15;
									%>
									<%
										while (i <= pages) {
									%>
									<li><a href="/faculty?page=<%=i%>"><%=++i%></a></li>

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