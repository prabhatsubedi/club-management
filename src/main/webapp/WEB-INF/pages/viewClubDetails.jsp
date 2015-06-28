<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
    <title>View Club Details</title>
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
                <a class="navbar-brand" href="<c:url value="userDashboard"/>" >Wellcome <%=username %></a>
            </div>
            <!-- /.navbar-header -->

                
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li><a class="active">My Profile</a></li>
                        <li><a href="<c:url value="searchClub"/>" >Search Clubs</a></li>
                        <li> <a href="<c:url value="eventList"/>" >Events</a></li>
                        <li> <a href="<c:url value="logout"/>" >LogOut</a></li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Club Details</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div>
                                	<b>Club Name : </b> <span>${clubEntity.name}</span><br><br><br>
                                	
                                	<b>Website : </b> <span>${clubEntity.website}</span><br><br><br>
                                	
                                	<b>Email : </b> <span>${clubEntity.email}</span><br><br><br>
                                	
                                	<b>Phone : </b> <span>${clubEntity.phone}</span><br><br><br>
                                	
                                	<b>Description : </b> <span>${clubEntity.description}</span><br><br><br>
                                	
                                	<b>Dues : </b> <span>${clubEntity.dues}</span><br><br><br>
                                	
                                	<b>Activities : </b> <span>${clubEntity.activities}</span><br><br><br>
	                            </div>
	                            <c:if test="${clubExist == null}">
	                            	<a href="<c:url value="joinClub"/>">Join This Club</a>
	                            </c:if>
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
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>
</body>
</html>