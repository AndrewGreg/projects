<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.ben.template.model.Major"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%
	HashMap<String, String> errors;
	if (request.getAttribute("errors") != null) {
		errors = (HashMap<String, String>) request.getAttribute("errors");
	} else {
		errors = new HashMap<String, String>();
	}
	
	ArrayList<Major> m =  (ArrayList<Major>) request.getAttribute("majors");

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Selection</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>
	<div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />

		<!-- ******CONTENT****** -->
		<div class="content container content-container">
			<div class="page-wrapper ben-container">
				<header class="page-heading clearfix pageHeading">
					<h1 class="heading-title pull-left">Create New Selections for Users</h1>
					<div class="breadcrumbs pull-right">
						<ul class="breadcrumbs-list">
							<li class="breadcrumbs-label">You are here:</li>
							<li><a href="/Alumni-Tracker/">Home</a><i
								class="fa fa-angle-right"></i></li>
							<li><a href="/Alumni-Tracker/">Selections</a><i
								class="fa fa-angle-right"></i></li>
							<li class="current">Create Selections</li>
						</ul>
					</div>
					<!--//breadcrumbs-->
				</header>
				<br>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-success">
						<form action="/Alumni-Tracker/addMajor" method="POST"
							name="addMajor">
	
							<div class="col-sm-1"></div>
							<h3>New Major</h3>
							
							<div class="row">
							
								<!-- Input control group -->
								<div class="col-sm-1"></div>
								<div class="form-group col-sm-10">
									<!-- Title -->
									<label class="control-label" for="name">Major Name:</label>
									<div class="controls">
										<input type="text" id="major" name="major" placeholder="New Major"
											class="form-control" value="" autofocus>

										<%
											if (errors.get("major") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("major")%></span>

										<%
											}
										%>

									</div>
									
									<br>
									<div class="col-xs-6">
										<button type="submit"
											class="btn btn-primary form-control">
											<b>Add Major</b>
										</button>
									</div>
								</div>
							</div>
						</form>
						</div>
								
						<div class="panel panel-success">
						<form action="/Alumni-Tracker/addConcentration" method="POST"
							name="addConcentration">
							
							
							<div class="col-sm-1"></div>
							<h3>New Concentration</h3>
							<div class="row">
								<!-- Input control group -->
								<div class="col-sm-1"></div>
								<div class="form-group col-sm-10">
									<!-- Title -->
									
									<div class="form-group">
										<label class="select-label">Major:</label>
										 <select class="form-control" name="concentrationMajor"
											id="concentrationMajor">
											<%
												if (m != null && m.size() != 0) {
														for (Major major : m) {
											%>
											<option><%=major.getName()%></option>
											<%
												}
													}
											%>
										</select> 
  										
  									</div>
									
									<label class="control-label" for="name">Concentration Name:</label>
									<div class="controls">
										<input type="text" id="concentration" name="concentration" placeholder="New Concentration"
											class="form-control" value="" autofocus>

										<%
											if (errors.get("concentration") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("concentration")%></span>

										<%
											}
										%>

									</div>
									
									<br>
									<div class="col-xs-6">
										<button type="submit"
											class="btn btn-primary form-control">
											<b>Add Concentration</b>
										</button>
									</div>
								</div>
							</div>
						</form>
						</div>
								
						<div class="panel panel-success">
						<form action="/Alumni-Tracker/addInterest" method="POST"
							name="addInterest">


							<div class="col-sm-1"></div>
							<h3>New Interest</h3>
							<div class="row">
								<!-- Input control group -->
								<div class="col-sm-1"></div>
								<div class="form-group col-sm-10">
									<!-- Title -->
									<label class="control-label" for="name">Interest Name:</label>
									<div class="controls">
										<input type="text" id="interest" name="interest" placeholder="New Interest"
											class="form-control" value="" autofocus>

										<%
											if (errors.get("interest") != null) {
										%>

										<span class="help-block" style="color: #e60000"> <%=errors.get("interest")%></span>

										<%
											}
										%>

									</div>
									
									<br>
									<div class="col-xs-6">
										<button type="submit"
											class="btn btn-primary form-control">
											<b>Add Interest</b>
										</button>
									</div>
								</div>
							</div>
						</form>
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