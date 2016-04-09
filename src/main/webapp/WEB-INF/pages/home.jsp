<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <link href="css/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<title>Welcome</title>
</head>
<body>
	<a style="float: right;margin: 20px;" href="<c:url value="registration"/>">Registration</a>
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
            
            	<c:if test="${success != null}">
	            	<div class="alert alert-success alert-dismissable">
	            		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">X</button>
	                     ${success}
				    </div>
			    </c:if>
            	
            	<c:if test="${error != null}">
	            	<div class="alert alert-danger alert-dismissable">
	            		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">X</button>
	                     ${error}
				    </div>
			    </c:if>
            
                <div class="login-panel panel panel-default">
                	<div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="<c:url value="adminDashBord"/>" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Username" required="required" name="username" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" required="required" name="password" type="password" value="">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Login"/>
                            </fieldset>
                        </form>
                    </div>
                </div>
                
            </div>
        </div>
    </div>

    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/metisMenu.min.js"></script>
    <script src="js/sb-admin-2.js"></script>
</body>
</html>
