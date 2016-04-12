<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.ben.template.model.Job"%>
<%@ page import="edu.ben.template.model.User"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<Job> jobs;
	if (request.getAttribute("jobs") != null) {
		jobs = (ArrayList<Job>) request.getAttribute("jobs");
	} else {
		jobs = new ArrayList<Job>();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jobs</title>
<jsp:include page="headerTemplate.jsp" />
</head>
<body>

 <div class="wrapper">
		<jsp:include page="navBarTemplate.jsp" />
		
          <!-- ******CONTENT****** --> 
        <div class="content container">
            <div class="page-wrapper">
                <header class="page-heading clearfix">
                    <h1 class="heading-title pull-left" style="color: white">Jobs</h1>
                    <div class="breadcrumbs pull-right">
                        <ul class="breadcrumbs-list">
                            <li class="breadcrumbs-label">You are here:</li>
                            <li><a href="/Alumni-Tracker/">Home</a><i class="fa fa-angle-right"></i></li>
                            <li class="current" style="color: white">Jobs</li>
                        </ul>
                    </div><!--//breadcrumbs-->
                </header> 
                <div class="page-content">
                    <div class="row page-row">
                        <div class="jobs-wrapper col-md-8 col-sm-7">           
                            <%
								for (int i = 0; i < jobs.size(); i++) {

									String name = jobs.get(i).getName() != null ? jobs.get(i).getName() : "";
									String company = jobs.get(i).getCompany() != null ? jobs.get(i).getCompany() : "";
									String id = Long.toString(jobs.get(i).getId());
									String description = jobs.get(i).getDescription() != null ? jobs.get(i).getDescription() : "";
									String location = jobs.get(i).getLocation() != null ? jobs.get(i).getLocation() : "";
									int hours = jobs.get(i).getHours() != 0 ? jobs.get(i).getHours() : 0;
									int startSalary = jobs.get(i).getStart_salary() != 0 ? jobs.get(i).getStart_salary() : 0;
									int endSalary = jobs.get(i).getEnd_salary() != 0 ? jobs.get(i).getEnd_salary() : 0;
									float startWage = jobs.get(i).getStart_wage() != 0 ? jobs.get(i).getStart_wage() : 0;
									float endWage = jobs.get(i).getEnd_wage() != 0 ? jobs.get(i).getEnd_wage() : 0;
									String hoursDisplay = "";
									String payDisplay = "";
									if(hours == 1){
										hoursDisplay = "Full-time";
										//payDisplay = "Salary";
									}else{
										hoursDisplay = "Part-time";
										//payDisplay = "Wage";
									}
							%>
                            <div class="panel panel-default page-row">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><a href="/Alumni-Tracker/jobs/<%=id%>"><%=name%></a> <span class="label label-success pull-right"><%=hoursDisplay%></span></h3>
                                    
                                </div>
                                <div class="panel-body">
                                    <%=description%>
                                </div>
                                <ul class="list-group">
                                    <li class="list-group-item"><strong>Location:</strong> <%=location%></li>
                                    <%if(hours==1){ %>
                                    	<li class="list-group-item"><strong>Salary:</strong> <%=startSalary%> - <%=endSalary%></li>
                                    <%}else{%>
                                    	<li class="list-group-item"><strong>Wage:</strong> <%=startWage%> - <%=endWage%></li>
                                    <%} %>
                                </ul>
                                <div class="panel-footer">
                                    <div class="row">
                                        <ul class="list-inline col-md-8 col-sm-6 col-xs-6">
                                            <li><a href="/Alumni-Tracker/jobs/<%=id%>">More details</a></li>
                                        </ul>
                                        <div class="meta col-md-4 col-sm-6 col-xs-6 text-right" style="color: white">
                                            <small>Posted 3 days ago</small>
                                        </div>
                                    </div>
                                </div>
                            </div><!--//panel-->
                            <%
								}
                            %>
                        </div>
                    </div>
                </div>
            </div>
         </div>
        
       </div>
	<jsp:include page="footerTemplate.jsp" />
</body>
</html>