<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.HashMap"%>
<%
	String title = (String) request.getAttribute("title");
	String concen = (String) request.getAttribute("concen");
	String interest = (String) request.getAttribute("interest");
	String major = (String) request.getAttribute("major");
	User u = (User) request.getAttribute("user");
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Account</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>
	<jsp:include page="navBarTemplate.jsp" />
	<div class="container">

		<div class="row">

			<div class="col-xs-12 col-sm-10 col-sm-offset-1 well">
				<div class="panel panel-success">

					<div class="panel-body">
						<h3 class="text-center text-danger">Confirm Creation of New Selection: <%=title %></h3>

						<form action="/Alumni-Tracker/confirmSelection" method="post"
							enctype="multipart/form-data">
							
							<%
								if (concen.equals("true")){
							%>
								<h3>Parent Major: <b><div class=""><input type="hidden" id="parent" name="parent" value="<%=major%>"><%=major%></div></b></h3>
								<h3>New Concentration Name: <b><div class=""><input type="hidden" id="concentration" name="concentration" value="<%=title %>"><%=title %></div></b></h3>
							<% 
							} else if (interest.equals("true")){
							%>
								<h3>New Interest Name: <b><div class=""><input type="hidden" id="interest" name="interest" value="<%=title %>"><%=title %></div></b></h3>
							<% 
							} else {
							%>
								<h3>New Major Name: <b><div class=""><input type="hidden" id="major" name="major" value="<%=title %>"><%=title %></div></b></h3>
							<% 
							}
							%>
							
							<div class="col-xs-3"></div>
							<div class="col-xs-3">
								<button type="reset" onclick="location.href='/Alumni-Tracker/user/<%=u.getId()%>'"
									class="btn btn btn-danger btn-lg btn-block">
									<b>Cancel</b>
								</button>
							</div>	
							
							<div class="col-xs-3">
								<button type="submit"
									class="btn btn btn-success btn-lg btn-block">
									<b>Confirm</b>
								</button>
							<div class="col-xs-3"></div>
							
						</form>
					</div>

				</div>
			</div>
		</div>

	</div>





	<jsp:include page="footerTemplate.jsp" />
</body>
</html>