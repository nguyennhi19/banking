<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/8/2021
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Deposit</title>
    <link rel="stylesheet" href="../bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container">
    <div class="table-title mb-4">
        <div class="row" style="background-color:blue">
            <div class="col-sm-8">
                <h2>Deposit money into customer's account</h2>
            </div>
            <div class="col-sm-4">
                <a href="/users" class="btn btn-outline-light"><i class="fa fa-list" aria-hidden="true"></i> <span>Customer list</span></a>
            </div>
        </div>
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
    <form class="row g-3" method="post" action="">
        <c:if test="${user != null}">
            <input type="hidden" name="id" class="form-control" value="<c:out value='${user.id}' />"/>
        </c:if>
        <div class="col-md-6">
            <label  class="form-label">Id</label>
            <input type="text" name="name" size="45" class="form-control" value="<c:out value='${user.id}'/>"/>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Name</label>
            <input type="text" name="name" size="45" class="form-control" value="<c:out value='${user.name}'/>"/>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Balance</label>
            <input type="text" name="name" size="45" class="form-control" value="<c:out value='${user.salary}'/>"/>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Amount</label>
            <input type="number" name="amount" size="15" class="form-control" required value=""/>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Deposit</button>
        </div>
    </form>
</div>
</body>
</html>
