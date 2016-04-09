<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>People Form</title>
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
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="adminDashBord"/>" >Wellcome <%=username %></a>
		</div>
		<!-- /.navbar-header -->


		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					 <li> <a href="<c:url value="adminDashBord"/>">Upload csv</a></li>
                     <li> <a href="<c:url value="clubTable"/>"  >Club Details</a></li>
                     <li> <a href="<c:url value="peopleTable"/>" class="active" >People Details</a></li>
                     <li> <a href="<c:url value="createEvent"/>" >Create Event</a></li>
                     <li> <a href="<c:url value="logout"/>" >LogOut</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Edit User Detail</h1>
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
									<form action="<c:url value="updatePeopleDetail"/>" method="post">
										<div class="form-group">
											<label>User Name</label> <input class="form-control"
												name="name" value="${peopleEntity.name}"
												placeholder="Enter text">
										</div>

										<div class="form-group">
											<label>Date Of Birth</label> <input class="form-control"
												name="dob" value="${peopleEntity.dob}" id="datepicker" 
												placeholder="Enter text">
										</div>

										<div class="form-group">
											<label>Phone</label> <input class="form-control" name="phone"
												value="${peopleEntity.phone}" placeholder="Enter text">
										</div>

										<div class="form-group">
											<label>Intrests</label>
											<textarea class="form-control" name="intrests"
												placeholder="Enter text">${peopleEntity.intrests}</textarea>
										</div>

										<div class="form-group">
											<label>Member</label><br>
											<c:forEach items="${results}" var="result">
												<c:choose>
													<c:when test="${result.checkbox }">
														<input type="checkbox" value="${result.clubname}"
															name="member" checked="checked" > ${result.clubname}
													</c:when>
													
													<c:otherwise>
														<c:if test="${result !=null and result.clubname != null}">
														<input type="checkbox" value="${result.clubname}"
															name="member" > ${result.clubname}</c:if>
													</c:otherwise>
												</c:choose>
												<%-- <div class="checkbox">
													<label> <input type="checkbox" value="${clubname}"
														name="member"
														<c:if test="${clubname == peopleEntity.member}">checked="checked"</c:if> />
														${clubname}
													</label>
												</div> --%>
											</c:forEach>
										</div>

										<div class="form-group input-group">
											<input type="submit"
												class="btn btn-default" />
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
<div style="margin-top:1em;margin-bottom:1em;text-align: center">Developed by Shile Fasugba</div>
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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-example').dataTable();
			
			$( "#datepicker" ).datepicker({
   		        changeMonth: true,
   		        changeYear: true,
   		     	maxDate: 'today',
   		     	yearRange: "-100:+0", 
	    	});
		});
	</script>
</body>
</html>
