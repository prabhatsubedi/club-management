<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Admin DashBoard</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="css/plugins/dataTables.bootstrap.css" rel="stylesheet">
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <link href="css/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
</head>
<body>
		<%
			response.setHeader("Cache-Control","no-cache");
			response.setHeader("Cache-Control","no-store");
			response.setHeader("Pragma","no-cache");
			response.setDateHeader ("Expires", 0);
			 HttpSession s=request.getSession();
			if(s.getAttribute("userName")==null){
				  response.sendRedirect("");
			}
			 String username = (String) session.getAttribute("userName");
         %> 
         
	<div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value="adminDashBord"/>" >Wellcome <%=username %></a>
            </div>
            <!-- /.navbar-header -->

                
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li><a href="<c:url value="adminDashBord"/>" >Upload csv</a></li>
                        <li><a href="<c:url value="clubTable"/>" >Club Details</a></li>
                        <li> <a href="<c:url value="peopleTable"/>" >People Details</a></li>
                        <li> <a href="<c:url value="createEvent"/>" class="active" >Create Event</a></li>
                        <li> <a href="<c:url value="logout"/>" >LogOut</a></li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-6">
                    <h1 class="page-header">Create Event </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <c:if test="${eventSucc != null}">
	           	<div class="alert alert-success alert-dismissable">
	           		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">X</button>
	                    ${eventSucc}
			    </div>
		    </c:if>
            
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                            	 <form role="form" action="<c:url value="createEvent"/>" method="post">
		                            <fieldset>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="Event Name" required="required" name="eventName" type="text" autofocus>
		                                </div>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="Place" required="required" name="place" type="text" id="searchTextField">
		                                </div>
		                                <div class="form-group">
		                                    <input class="form-control" placeholder="Event date" id="datepicker" required="required" name="date" type="text">
		                                </div>
		                                <div class="form-group">
		                                    <textarea class="form-control" placeholder="Description" required="required" name="description"></textarea>
		                                </div>
		                                <!-- Change this to a button or input when using this as a form -->
		                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Post"/>
		                            </fieldset>
		                        </form>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/metisMenu.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <script type="text/javascript" src="js/google.js"></script>
    
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
    	
    	var input = document.getElementById('searchTextField');
    	var options = {componentRestrictions: {country: 'us'}};
    	    
    	new google.maps.places.Autocomplete(input, options);
        
        $( "#datepicker" ).datepicker({
	        changeMonth: true,
	        changeYear: true,
	     	minDate: 'today',
	     	yearRange: "-100:+0", 
		});
    });
    </script>
</body>
</html>