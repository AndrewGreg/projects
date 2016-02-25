<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="edu.ben.template.model.Major"%>
<%@ page import="java.util.ArrayList;"%>
<%
	//User u = (User) request.getSession().getAttribute("user");
	User u = (User) request.getAttribute("user");
	ArrayList<Major> m = (ArrayList<Major>) request.getAttribute("majors");
	HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit My Account!</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<div class="container">

		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1 well">
				<div class="panel panel-success">

					<div class="panel-body">
						<h3 class="text-center text-danger">Edit My Account</h3>
						<h4 class="text-center"><%=u.getEmail()%></h4>
						<form action="" method="post">
							<%
								if (errors != null && errors.get("title") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("title")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12 col-sm-2">
								<div class="form-group">
									<label class="control-label">Title:</label> <input type="text"
										class="form-control" name="title" id="title"
										placeholder="Enter Title" <%if (u.getTitle() != null) {%>
										value="<%=u.getTitle()%>" <%}%> />
								</div>
							</div>

							<%
								if (errors != null && errors.get("fName") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("fName")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
									<label class="control-label">First Name:</label> <input
										type="text" class="form-control" name="fName" id="fName"
										placeholder="Enter First Name"
										<%if (u.getFirstName() != null) {%>
										value="<%=u.getFirstName()%>" <%}%> required />
								</div>
							</div>

							<%
								if (errors != null && errors.get("lName") != null) {
							%>
							<div class="col-xs-12 ">
								<p class="alert alert-danger text-center"><%=errors.get("lName")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
									<label class="control-label">Last Name:</label> <input
										type="text" class="form-control" name="lName" id="lName"
										placeholder="Enter Last Name"
										<%if (u.getLastName() != null) {%>
										value="<%=u.getLastName()%>" <%}%> />
								</div>
							</div>

							<%
								if (errors != null && errors.get("suffix") != null) {
							%>
							<div class="col-xs-12 col-sm-2">
								<p class="alert alert-danger text-center"><%=errors.get("suffix")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12 col-sm-2">
								<div class="form-group">
									<label class="control-label">Suffix:</label> <input type="text"
										class="form-control" name="suffix" id="suffix"
										placeholder="Enter Suffix" <%if (u.getSuffix() != null) {%>
										value="<%=u.getSuffix()%>" <%}%> />
								</div>
							</div>

							<%
								if (errors != null && errors.get("personalEmail") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("personalEmail")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12">
								<div class="form-group">
									<label class="control-label">Personal Email:</label> <input
										type="email" class="form-control" name="personalEmail"
										id="personalEmail" placeholder="example@web.com"
										<%if (u.getPersonalEmail() != null) {%>
										value="<%=u.getPersonalEmail()%>" <%}%> />
								</div>
							</div>

							<%
								if (errors != null && errors.get("graduationYear") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("graduationYear")%></p>
							</div>
							<%
								}
							%>
							<div class="ccol-xs-12 col-sm-3">
								<div class="form-group">
									<label class="control-label">Graduation Year:<%
										if (5 > u.getRole() && u.getRole() >= 2) {
									%>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
									</label>
									<jsp:useBean id="today" class="java.util.Date" />
									<fmt:formatDate pattern="yyyy" value="${today}"
										var="current_year" />
									<select class="" name="graduationYear" id="graduationYear">
										<option>Select</option>
										<%
											for (int r = 1960; r <= 2025; r++) {
										%>
										<option
											<%if (u.getGraduationYear() != 0 && u.getGraduationYear() == r) {%>
											selected <%}%>><%=r%></option>
										<%
											}
										%>
									</select>
									<%
										} else if (u.getRole() == 5) {
									%>
									<div class="" name="graduationYear" id="graduationYear">
										<%
											if (u.getGraduationYear() != 0) {
										%>
										<h3><%=u.getGraduationYear()%></h3>
										<%
											}
										%>
									</div>
									<%
										}
									%>
								</div>
							</div>

							<%
								if (errors != null && errors.get("major") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("major")%></p>
							</div>
							<%
								}
							%>

							<div class="col-xs-12 col-sm-3">
								<div class="form-group">
									<label class="control-label">Major:<%
										if (5 > u.getRole() && u.getRole() >= 2) {
									%> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
									</label> <select class="" name="major" id="major">
										<option>Select</option>
										<%
											if (m != null && m.size() != 0) {
													for (Major major : m) {
										%>
										<option
											<%if (u.getMajor() != null && u.getMajor().size() > 0 && u.getMajor().get(0) != null
								&& u.getMajor().get(0).getName().equals(major.getName())) {%>
											selected <%}%>><%=major.getName()%></option>
										<%
											}
												}
										%>
									</select>
									<%
										} else if (u.getRole() == 5 && u.getMajor().size() > 0 && u.getMajor().get(0) != null) {
									%><div class="" name="major" id="major">
										<h3><%=u.getMajor().get(0).getName()%></h3>
									</div>
									<%
										}
									%>
								</div>

							</div>

							<%
								if (errors != null && errors.get("doubleMajor") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("doubleMajor")%></p>
							</div>
							<%
								}
							%>

							<div class="col-xs-12 col-sm-3">
								<div class="form-group">
									<label class="control-label">Double Major:<%
										if (5 > u.getRole() && u.getRole() >= 2) {
									%> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
									</label> <select class="" name="doubleMajor" id="doubleMajor">
										<option>Select</option>
										<%
											if (m != null && m.size() != 0) {
													for (Major major : m) {
										%>
										<option
											<%if (u.getMajor() != null && u.getMajor().size() > 1 && u.getMajor().get(1) != null
								&& u.getMajor().get(1).getName().equals(major.getName())) {%>
											selected <%}%>><%=major.getName()%></option>
										<%
											}
												}
										%>
									</select>
									<%
										} else if (u.getRole() == 5 && u.getMajor().size() > 1 && u.getMajor().get(1) != null) {
									%><div class="" name="doubleMajor" id="doubleMajor">
										<h3><%=u.getMajor().get(1).getName()%></h3>
									</div>
									<%
										}
									%>
								</div>

							</div>

							<%
								if (errors != null && errors.get("thirdMajor") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("thirdMajor")%></p>
							</div>
							<%
								}
							%>

							<div class="col-xs-12 col-sm-3">
								<div class="form-group">
									<label class="control-label">Third Major:<%
										if (5 > u.getRole() && u.getRole() >= 2) {
									%> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
									</label> <select class="" name="thirdMajor" id="thirdMajor">
										<option>Select</option>
										<%
											if (m != null && m.size() != 0) {
													for (Major major : m) {
										%>
										<option
											<%if (u.getMajor() != null && u.getMajor().size() > 2 && u.getMajor().get(2) != null
								&& u.getMajor().get(2).getName().equals(major.getName())) {%>
											selected <%}%>><%=major.getName()%></option>
										<%
											}
												}
										%>
									</select>
									<%
										} else if (u.getRole() == 5 && u.getMajor().size() > 2 && u.getMajor().get(2) != null) {
									%><div class="" name="thirdMajor" id="thirdMajor">
										<h3><%=u.getMajor().get(2).getName()%></h3>
									</div>
									<%
										}
									%>
								</div>

							</div>


							<%
								if (errors != null && errors.get("occupation") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("occupation")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12">
								<div class="form-group">
									<label class="control-label">Occupation:</label> <input
										type="text" class="form-control" name="occupation"
										id="occupation" placeholder="Enter Occupation"
										<%if (u.getOccupation() != null) {%>
										value="<%=u.getOccupation()%>" <%}%> />
								</div>
							</div>

							<%
								if (errors != null && errors.get("biography") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("biography")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12">
								<div class="form-group">
									<label class="control-label">Biography:</label>
									<textarea type="text" class="form-control" name="bio" id="bio"
										placeholder="Enter Biography" rows="5"><%
											if (u.getBio() != null) {
										%><%=u.getBio()%><%
											}
										%></textarea>
								</div>
							</div>
							<%
								if (errors != null && errors.get("experience") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("experience")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12">
								<div class="form-group">
									<label class="control-label">Experience:</label>
									<textarea type="text" class="form-control" name="experience"
										id="experience" placeholder="Enter Experience" rows="5"><%
											if (u.getExperience() != null) {
										%><%=u.getExperience()%><%
											}
										%></textarea>
								</div>
							</div>
							<%
								if (errors != null && errors.get("password") != null) {
							%>
							<div class="col-xs-12">
								<p class="alert alert-danger text-center"><%=errors.get("password")%></p>
							</div>
							<%
								}
							%>
							<div class="col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="control-label">Password:</label> <input
										type="password" class="form-control" name="password"
										id="password" placeholder="Enter Password" required />
								</div>
							</div>
							<div class="col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="control-label">Confirm Password:</label><input
										type="password" class="form-control" name="confirmPassword"
										id="confirmPassword" placeholder="Confirm Password" required />
								</div>
							</div>

							<div class="col-xs-12">
								<button type="submit"
									class="btn btn-large btn btn-danger btn-lg btn-block">
									<b>Submit Changes</b>
								</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>

	</div>





	<jsp:include page="footer.jsp" />
</body>
</html>