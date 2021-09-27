<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27/7/2021
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Transfer</title>
    <link rel="stylesheet" href="../bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container">
    <div class="table-title mb-4">
        <div class="row" style="background-color:blue">
            <div class="col-sm-8">
                <h2>Transfer money Information</h2>
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
            <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
        </c:if>
        <div class="col-md-6">
            <label  class="form-label">Transfer Id</label>
            <input type="text" name="name" size="45" class="form-control"
                   value="<c:out value='${user.id}'/>"
            />
        </div>
        <div class="col-md-6">
            <label  class="form-label">Transfer Name</label>
            <td>
                <input type="text" name="name" size="45" class="form-control"
                       value="<c:out value='${user.name}'/>"
                />
            </td>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Balance</label>
            <td>
                <input type="text" name="name" size="45" class="form-control"
                       value="<c:out value='${user.salary}'/>"
                />
            </td>
        </div>
        <div class="col-md-6">
            <label  class="form-label">Receiver</label>
            <select name="receiverId" id="receiverID" class="form-control">
                <c:forEach items="${users}" var="user">
                    <option>${user.id}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-6">
            <label class="form-label">Amount</label>
            <input type="number" name="amount" size="45" required class="form-control"
                   value=""
            />
        </div>
        <div class="col-md-6">
            <label class="form-label">Discount</label>
            <input type="text" name="discount" size="45" required class="form-control"
                   value="5"
            />
        </div>
        <div class="col-12">
            <button type="submit"  class="btn btn-primary " >Transfer</button>
        </div>
    </form>
</div>

</body>
</html>
