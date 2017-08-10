<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link href="css/sidebar.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
    </head>
    <body>
        <div class='panel panel-primary'>
            <div class="panel-heading"><h3>Administrative Services</h3></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-2">
                        <div class='nav-sidebar'>
                            <ul class='nav'>
                                <li align='center'><b>Menu</b></li>
                                <li><a href='AdminController?action=goHome'>Home</a></li>
                                <li><a href='AdminController?action=listProduct'>List All Product</a></li>
                                <li><a href='AdminController?action=addProduct'>Add Product</a></li>
                                <li><a href='DistributorController?action=listDistributors'>List All Distributors</a></li>
                                <li><a href='DistributorController?action=addDistributor'>Add Distributor</a></li>
                                <li>
                                    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                                        <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Off</a>
                                    </sec:authorize>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <c:choose>
                            <c:when test="${not empty userName}">
                                <p>Welcome ${userName}</p>
                            </c:when>
                        </c:choose>
                        <form method='POST' id='name' name='name' action='DistributorController?action=addEditDistributor'>
                            <table class='table'>
                                <tr>
                                    <th></th>
                                    <th>Distributor Name</th>
                                    <th>Distributor City</th>
                                    <th>Distributor State</th>
                                </tr>
                                <tbody id='listDistributors'>

                                </tbody>
                            </table>
                            <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                                <input type="submit" name="addEdit" id="addEdit" value="Add/Edit Distributor" /> 
                            </sec:authorize>
                        </form>
                        <br>

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
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" type="text/javascript"></script>
            <script src="resources/js/distributors.js" type="text/javascript"></script>
    </body>
</html>
