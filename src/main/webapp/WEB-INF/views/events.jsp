<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="edu.ben.template.model.Event"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.ben.template.dao.EventDao"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navBar.jsp" />
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad col-md-offset-2 ">

				<div style="border-color: black" class="panel panel-info">
					<div class="panel-heading">
						<h3 style="color: black">Events</h3>
					</div>
					<div class="panel-body">

						<%
							Event event = new Event();

							ArrayList<Event> events = (ArrayList<Event>) request.getAttribute("events");
				
							int count = 0;
							while (events != null && count < events.size() && (events.get(count) != null) && (events.get(count).getName() != null)) {
						%>

						<table class="table table-user-information">
							<tbody>
								<tr>
									<td><%=events.get(count).getName()%></td>
									<td></td>

								</tr>
							</tbody>
						</table>

						<%
							count++;
							}
						%>

					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>