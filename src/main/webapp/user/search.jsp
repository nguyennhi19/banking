<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30/7/2021
  Time: 8:26 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="../bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="table-responsive">
    <div class="table-title mb-4">
        <div class="row" style="background-color:blue">
            <div class="col-sm-8">
                <h2>User Management</h2>
            </div>
            <caption>
                <div style="text-align: center; clear: both;">
                    <c:if test="${success !=null}">
                        <span style="color: green;"><c:out value="${success}"/></span> &emsp;&emsp;
                    </c:if>
                    <c:if test="${error !=null}">
                        <span style="color: red;"><c:out value="${error}"/></span>
                    </c:if>
                </div>
            </caption>
            <div class="col-sm-4">
                <a href="/users" class="btn btn-outline-light"><i class="fa fa-list" aria-hidden="true"></i> <span>Customer list</span></a>
            </div>
        </div>
    </div>
    <div align="center">
        <table class="table table-bordered">
            <div style="text-align: center; clear: both;">
                <c:if test="${delete !=null}">
                    <span style="color: green;"><c:out value="${delete}"/></span>
                </c:if>
            </div>
            <h2>
                <form action="users?action=search" method="post">
                    <input type="text" placeholder="Search..." name="key">
                    <input type="submit" value="Search">
                </form>
            </h2>
            <thead class="thead-dark">
            <tr>

                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Salary</th>
                <th colspan="5" style="text-align: center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${list}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.age}"/></td>
                    <c:if test="${user.gender == 1}">
                        <td><c:out value="Male"/></td>
                    </c:if>
                    <c:if test="${user.gender == 0}">
                        <td><c:out value="Female"/></td>
                    </c:if>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.phone}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.salary}"/></td>
                    <td><a href="/users?action=withdraw&id=${user.id}" class="btn-sm btn-secondary" title="withdraw" size="30"><i class="fa fa-window-minimize"></i></a></td>
                    <td><a href="/users?action=deposit&id=${user.id}" class="btn-sm btn-secondary" title="deposit" size="30"><i class="fa fa-plus"></i></a></td>
                    <td>
                        <a href="/users?action=edit&id=${user.id}" class="btn-sm btn-primary" title="Edit" size="30"><i class="fa fa-edit"></i></a>
                    </td>
                    <td>
                        <a href="/users?action=delete&id=${user.id}" class="btn-sm btn-danger " title="Delete" size="30"><i class="fa fa-trash"></i></a>
                    </td>
                    <td>
                        <a href="/users?action=transfer&id=${user.id}" class="btn-sm btn-secondary" title="Transfer" size="30"><i class="fa fa-exchange"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
