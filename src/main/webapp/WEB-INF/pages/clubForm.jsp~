<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Club Form</title>
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
                <a class="navbar-brand" href="<c:url value="adminDashBord"/>" >Welcome <%=username %></a>
            </div>
            <!-- /.navbar-header -->

                
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li> <a href="<c:url value="adminDashBord"/>">Upload csv</a></li>
                        <li><a href="<c:url value="clubTable"/>" class="active" >Club Details</a></li>
                        <li><a href="<c:url value="peopleTable"/>" >People Details</a></li>
                        <li> <a href="<c:url value="createEvent"/>" >Create Event</a></li>
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
                    <h1 class="page-header">Club Data</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                         <!-- /.panel-heading -->
                       <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form action="<c:url value="updateClubDetail"/>" method="post">
                                    	
                                        <div class="form-group">
                                            <label>Club Name</label>
                                            <input type="text" name="name" class="form-control" value="${clubEntity.name}" maxLength="20" required="required" />
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Website</label>
                                            <input type="text" name="website" class="form-control" value="${clubEntity.website}" maxLength="20" required="required" />
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Email</label>
                                            <input type="text" name="email" class="form-control" value="${clubEntity.email}"  maxLength="20" required="required" />
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Phone</label>
                                            <input type="text" name="phone" class="form-control" value="${clubEntity.phone}" maxLength="20" required="required" />
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea name="description" class="form-control" required="required"> ${clubEntity.description}</textarea>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Dues</label>
                                            <input name="dues" class="form-control" required="required" value="${clubEntity.dues}"/>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Activities</label>
                                            <textarea name="activities" class="form-control" required="required">${clubEntity.activities}</textarea>
                                        </div>
                                        
                                        <div class="form-group input-group">
                                            <span class="input-group-btn">
                                                <input class="btn btn-default" type="submit">
                                            </span>
                                        </div>
                                    </form>
                                 </div>
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

<div style="margin-top:1em;margin-bottom:1em;text-align: center">Developed by Shile Fasugba</div>

    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/metisMenu.min.js"></script>
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/sb-admin-2.js"></script>
    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>
</body>
</html>
