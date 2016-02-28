<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="edu.ben.template.model.Event"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.ben.template.dao.EventDao"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<Event> events;
	if (request.getAttribute("events") != null) {
		events = (ArrayList<Event>) request.getAttribute("events");
	} else {
		events = new ArrayList<Event>();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events</title>
<jsp:include page="header.jsp" />
<link rel="stylesheet" type="text/css" href="/content/css/lists.css">
</head>
<body>
	<jsp:include page="navBar.jsp" />
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
		<div id="events">
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
					<h4 class="name">
						<a href="/eventDisplay/<%=id%>"><%=name%></a>
					</h4>
					<p class="date"><%=date%></p>
				</li>


				<%
					}
				%>
			</ul>
		</div>
		<script src="/content/jQuery/list.js"></script>
		<script src="/content/jQuery/searchEvents.js"></script>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>