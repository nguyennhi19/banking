<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27/7/2021
  Time: 8:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="../bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<div class="container">
    <div class="table-title mb-4">
        <div class="row" style="background-color:blue">
            <div class="col-sm-8">
                <h2>Create Customer</h2>
            </div>
            <caption>
                <div style="text-align: center; clear: both;">
                    <c:if test="${success !=null}">
                        <span style="color: green;"><c:out value="${success}"/></span>
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
    <form class="row g-3" method="post">
        <div class="col-md-6">
            <label  class="form-label">User  Name</label>
            <input type="text" name="name" id="name" size="45" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Age</label>
            <input type="number" name="age" id="age" size="45" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Gender</label> <br>
            <input type="radio" name="gender" id="2" value="0"  />nữ &emsp;&emsp;
            <input type="radio" name="gender" id="1" value="1"  />nam <br>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Address</label>
            <input type="text" name="address" id="address" size="45" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Phone</label>
            <input type="text" name="phone" id="phone" size="45" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Email</label>
            <input type="text" name="email" id="email" size="45" class="form-control" required/>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
</div>
</body>
</html>
