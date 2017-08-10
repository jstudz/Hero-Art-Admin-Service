<%-- 
    Document   : loginerror
    Created on : Nov 29, 2015, 7:06:59 PM
    Author     : Jamie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link href="css/sidebar.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Logging In</title>
    </head>
    <body>
        <div class='panel panel-primary'>
            <div class="panel-heading"><h3>Login Error</h3></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-2">
                        <div class='nav-sidebar'>
                            <ul class='nav'>
                                <li align='center'><b>Menu</b></li>
                                <li><a href='AdminController?action=goHome'>Home</a></li>
                                <li><a href='AdminController?action=listProduct'>List All Product</a></li>
                                <li><a href='AdminController?action=addProduct'>Add Product</a></li>
                                <li><a href='DistributorController?action=listDistributors'>List All Distributors</a>
                                <li><a href='DistributorController?action=addDistributor'>Add Distributor</a>
                                <li><a href='AdminController?action=logOff'>Log Off</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <p>Sorry! You are not authorize to visit this site. Please contact 
                            the Site Admin for more information. Thank you!</p>
                        
                          <c:if test="${error != null}">
                            <p><b>${error}</b></p>
                        </c:if>
                    </div>
                </div>
                <div class="panel-footer" align='right'>
                    <address>
                        &copy; <a href="mailto:${email}">2015 Heroes Art Production</a>
                    </address>
                </div>
            </div>
    </body>
</html>
