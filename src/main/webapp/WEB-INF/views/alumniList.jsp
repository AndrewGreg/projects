<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alumni</title>
<jsp:include page="header.jsp" />
<link rel="stylesheet" type="text/css" href="/content/css/lists.css">
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col col-xs-12">
				<div class="page-header">
					<h2 style="padding-left: 100px;">Alumni</h2>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div id="itemList">
			<input class="search" placeholder="Search" />
			<ul class="list">

				<%
					for (int i = 0; i < alumni.size(); i++) {

						String name = alumni.get(i) != null && alumni.get(i).getLastName() != null
								&& alumni.get(i).getFirstName() != null
										? alumni.get(i).getLastName() + ", " + alumni.get(i).getFirstName() : "";
						String id = alumni.get(i) != null ? Long.toString(alumni.get(i).getId()) : "";
				%>

				<li>
					<h4 class="primary">
						<a href="/user/<%=id%>"><%=name%></a>
					</h4>
				</li>

				<%
					}
				%>
			</ul>
		</div>
		<script src="/content/jQuery/list.js"></script>
		<script src="/content/jQuery/listSearch.js"></script>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>