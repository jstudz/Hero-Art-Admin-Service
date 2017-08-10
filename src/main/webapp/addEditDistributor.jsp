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
        <title>Add/Edit Distributor</title>
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
                                <li><a href='DistributorController?action=addDistributor'>Add Distributor</a>
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
                                <p><b>Welcome ${userName}</b></p>
                            </c:when>
                        </c:choose>       
                        <p>Use the form below to add a new distributor or edit the appropriate fields that need to be
                            edit and update the distributor as you see fit.</p>
                        <form method="POST" id="save" name="save" action="DistributorController?action=save">
                            <p>Distributor ID:</p>
                            <p><input readonly type="text" id="id" name="id" value="${distributor.distributorId}"/></p>
                            <p>Distributor Name: </p>
                            <p><input type="text" id="name" name="name" value="${distributor.distributorName}"></p>
                            <p>Distributor City: </p>
                            <p><input type="text" id="city" name="city" value="${distributor.city}"></p>
                            <p>Distributor State: </p>
                            <p><input type="text" id="state" name="state" value="${distributor.state}"></p>
                            <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                                <input type="submit" name="save" id="save" value="Save Distributor" />
                            </sec:authorize>
                        </form>
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
        </div>
    </body>
</html>
