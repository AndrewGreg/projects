<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="edu.ben.template.dao.UserDao"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="edu.ben.template.model.UploadFile"%>
<%
	ArrayList<User> student;
	if (request.getAttribute("student") != null) {
		student = (ArrayList<User>) request.getAttribute("student");
	} else {
		student = new ArrayList<User>();
	}
	
	ArrayList<UploadFile> photos;
	photos = (ArrayList<UploadFile>) request.getAttribute("photos");
%>
<!DOCTYPE html>
<html>
<head>
<title>Student Directory</title>

<jsp:include page="headerTemplate.jsp" />

</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />
		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper ben-container">
				<header class="page-heading clearfix pageHeading">
					<h1 class="heading-title pull-left">Student Directory</h1>
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current">Student Directory</li>
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
										<th class="text-center"><a
											href="javascript:SortTable(1,'T');">First Name <i
												class="fa fa-retweet"></i></a></th>
										<th class="text-center"><a
											href="javascript:SortTable(2,'T');">Last Name <i
												class="fa fa-retweet"></i></a></th>
										<th class="text-center"><a
											href="javascript:SortTable(3,'T');">Occupation <i
												class="fa fa-retweet"></i></a></th>
										<th class="text-center"><a
											href="javascript:SortTable(4,'N');">Major <i
												class="fa fa-retweet"></i></a></th>
									</tr>
									<tr class="warning no-result">
										<td colspan="4"><i class="fa fa-warning"></i> No result</td>
									</tr>
								</thead>
								<tbody>
									<%
										if (student.size() == 0) {
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
											for (int i = 0; i < student.size(); i++) {
												if(student.get(i).isActive()){
													if (student.get(i).getRole() == 1) {
														String firstName = student.get(i) != null && student.get(i).getFirstName() != null
															? student.get(i).getFirstName() : "N/A";
														String lastName = student.get(i) != null && student.get(i).getLastName() != null
															? student.get(i).getLastName() : "N/A";
														String occupation = student.get(i) != null && student.get(i).getOccupation() != null
															? student.get(i).getOccupation() : "N/A";
														String major = student.get(i) != null && student.get(i).getMajorAtIndex(0) != null
															&& student.get(i).getMajorAtIndex(0).getName() != null ? student.get(i).getFirstName()
																	: "N/A";
															int k = 0;		
									%>
									
									<tr class='clickable-row row-link'
										data-href='/Alumni-Tracker/user/<%=student.get(i).getId()%>'>
										<%
											if(photos.size() == 0){
										%>
											<td align="center"><img id="empty-profile-pic"
														src="/Alumni-Tracker/content/img/empty-profile.png"
														alt="Profile picture" ></td>
										<%
											}else{
										%>
										
										<%
											while(k < photos.size()){
												if(student.get(i).getId() == photos.get(k).getProfile().getId()){
										%>	
													<td align="center"><img id="profile-pic" 
														src="/Alumni-Tracker/getImage/<%=student.get(i).getId()%>.do"
														alt="Profile Picture" width=35px height=40px ></td>
										<%
												k++;		
												
												}else if(k == photos.size() - 1){
													k++;
										%>	
													<td align="center"><img id="empty-profile-pic"
														src="/Alumni-Tracker/content/img/empty-profile.png"
														alt="Profile picture" ></td>	
										<%
											}else{
												k++;
											}
											
											}
											}
										%>
												<%-- <img id="empty-profile-pic"
											src="/Alumni-Tracker/content/img/empty-profile.png"
											alt="Empty profile picture"></td>--%>
										
										<td align="center"><%=firstName%></td>
										<td align="center"><%=lastName%></td>
										<td align="center"><%=occupation%></td>
										<td align="center"><%=major%></td>
									</tr>

									<%
													}	
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
										int count = (Integer) request.getAttribute("studentCount");
										int pages = (count - 1) / 15;
									%>
									<%
										while (i <= pages) {
									%>
									<li><a href="/Alumni-Tracker/students?page=<%=i%>"><%=++i%></a></li>

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

	<script type="text/javascript"
		src="/Alumni-Tracker/content/jQuery/sortColumns.js"></script>

</body>

</html>