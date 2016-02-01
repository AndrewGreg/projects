<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Benedictine Alumni</title>
<jsp:include page="header.jsp"/>
    

</head>
<body>
<!-- Navigation -->

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" >
   
    
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            
            <div class="navbar-header">
            <a href="/index"><img src="content/img/BenedictineLogo.gif" height= 100% width= 100%></a>
            
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/index">Home</a>
                    </li>
                    <li>
                        <a href="/alumniDirectory">Alumni Directory</a>
                    </li>
                     <li>
                        <a href="/index">Empty tab</a>
                    </li>
                     <li>
                        <a href="/index">Empty Tab</a>
                    </li>
                    
                    
                    
                    
                    
                    <!-- When the user is logged in the session go here. -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Profile <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="/userProfile">Home</a>
                            </li>
                            <li>
                                <a href="#">Settings</a>
                            </li>
                            <li>
                                <a href="#">Logout</a>
                            </li>
                         
                        </ul>
                        
                    </li>
              
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

</body>
</html>