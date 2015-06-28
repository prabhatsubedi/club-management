<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
    <title>View Event Details</title>
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
                        <li><a href="<c:url value="userDashboard"/>" >My Profile</a></li>
                        <li><a href="<c:url value="searchClub"/>" >Search Clubs</a></li>
                        <li> <a href="<c:url value="eventList"/>" class="active" >Events</a></li>
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
                    <h1 class="page-header">Event Details</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div>
                                	<input type="hidden" id="location" value="${eventEntity.place}">
                                	<b>Event Name : </b> <span>${eventEntity.eventName}</span><br><br><br>
                                	
                                	<b>Event Date : </b> <span><fmt:formatDate pattern="yyyy-MM-dd" value="${eventEntity.date}" /> </span><br><br><br>
                                	
                                	<b>Event Place : </b> <span>${eventEntity.place}</span><br><br><br>
                                	
                                	<b>Description : </b> <span>${eventEntity.description}</span><br><br><br>
                                	
                                	<b>Going : </b>
                                		<c:forEach items="${eventEntity.peopleEntities}" var="people">
                                			<li style="margin-left: 50px;">${people.name}</li>
	                                    </c:forEach>
                                	
                                </div>
	                            <c:if test="${join == 'join'}">
	                            	<a href="<c:url value="joinEvent/${eventEntity.id}" />">Join Event</a>
	                            </c:if>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                
                <div class="col-lg-6" id="map" style="width:500px; height:400px;">
                </div>
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
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
   
    $(function() {
    	var location = $('#location').val();
    	initialize(location);
    });
    	
    function initialize(eventAddress)
    {
    	$.ajax({
    		url:"http://maps.googleapis.com/maps/api/geocode/json?address="+eventAddress+"&sensor=false",
    		type: "POST",
    		success:function(res){
    			var lat = res.results[0].geometry.location.lat;
    			var lng = res.results[0].geometry.location.lng;
       
    			var mapProp = {
    				center:new google.maps.LatLng(lat,lng),
    				zoom:15,
    				mapTypeId:google.maps.MapTypeId.ROADMAP
    			};
    			
    			 var contentString = eventAddress;
    		      
    			var infowindow = new google.maps.InfoWindow({
    			     content: contentString
    			});
    			 
    			var map=new google.maps.Map(document.getElementById("map"),mapProp);
    	
    			var marker = new google.maps.Marker({
    				position: new google.maps.LatLng(lat,lng),
    				map: map,
    			});
    			
    			 google.maps.event.addListener(marker, 'click', function() {
    			    infowindow.open(map,marker);
    			 });
    		}
    	});

    }
    </script>
</body>
</html>