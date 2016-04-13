<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="edu.ben.template.model.Event"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<Event> events;
	if (request.getAttribute("events") != null) {
		events = (ArrayList<Event>) request.getAttribute("events");
	} else {
		events = new ArrayList<Event>();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events</title>
<jsp:include page="headerTemplate.jsp" />
<link rel="stylesheet" type="text/css" href="/Alumni-Tracker/content/css/lists.css">
</head>
<body>
	<jsp:include page="navBarTemplate.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col col-xs-12">
				<div class="page-header">
					<h2 style="padding-left: 100px;">Events List</h2>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div id="itemList">
			<input class="search" placeholder="Search" />
			<ul class="list">
				<%
					for (int i = 0; i < events.size(); i++) {

						String name = events.get(i).getName() != null ? events.get(i).getName() : "";
						
						
						String date;
						
						if(events.get(i).getDate() != null) {
							String[] localeDate = events.get(i).getDate().toLocaleString().split(" ");
							date = localeDate[0] + " " +  localeDate[1] + " " + localeDate[2];
						}
						else {
							date = "";
						}
						
						
						
						String id = Long.toString(events.get(i).getId());
						
				%>
				
				<li>
					<h4 class="primary">
						<a href="/Alumni-Tracker/event/<%=id%>"><%=name%></a>
					</h4>
					<p class="secondary"><%=date%></p>
				</li>


				<%
					}
				%>
			</ul>
		</div>
		<script src="/Alumni-Tracker/content/jQuery/list.js"></script>
		<script src="/Alumni-Tracker/content/jQuery/listSearch.js"></script>
	</div>
	<jsp:include page="footerTemplate.jsp" />
</body>
</html>