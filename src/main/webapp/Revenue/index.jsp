<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22/9/2021
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Revenue</title>
    <link rel="stylesheet" href="../bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="table-responsive">
    <div class="table-title mb-4">
        <div class="row" style="background-color:blue">
            <div class="col-sm-8">
                <h2>List History</h2>
            </div>
            <div class="col-sm-4">
                <a href="/users" class="btn btn-outline-light"><i class="fa fa-list" aria-hidden="true"></i> <span>Customer list</span></a>
            </div>
        </div>
    </div>
    <div align="center">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">ID Sender</th>
                <th scope="col">Name Sender</th>
                <th scope="col">ID Receiver</th>
                <th scope="col">Name Receiver</th>
                <th scope="col">Amount</th>
                <th scope="col">Fee(%)</th>
                <th scope="col">Fee_Amount</th>
                <th scope="col">total_amount</th>
                <th scope="col">datetime</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="transfer" items="${transfers}">
                <tr>
                    <td><c:out value="${transfer.id}"/></td>
                    <td><c:out value="${transfer.idSender}"></c:out></td>
                    <td><c:out value="${transfer.name_Sender}"></c:out></td>
                    <td><c:out value="${transfer.idReceiver}"></c:out></td>
                    <td><c:out value="${transfer.name_Receiver}"></c:out></td>
                    <td><c:out value="${transfer.amount}"/></td>
                    <td><c:out value="${transfer.fee}"/></td>
                    <td><c:out value="${transfer.amount_fee}"/></td>
                    <td><c:out value="${transfer.total_amount}"/></td>
                    <td><c:out value="${transfer.datetime}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p  style="float: right;font-weight: bold; margin-right: 150px ">Total Revenue:<c:out value="${total}"/></p>
    </div>
</div>


</body>
</html>
