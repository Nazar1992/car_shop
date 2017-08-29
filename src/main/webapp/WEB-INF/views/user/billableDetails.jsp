<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div>
    <div class="container">

        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Time</th>
                <th>Items</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><c:out value="${billable.id}" /></td>
                <td><c:out value="${billable.localDateTime}" /></td>
                <td>
                    <c:forEach var="car" items="${billable.car}">
                    <c:out value="${car.name}" /><br  />
                    </c:forEach>
                </td>
                <td><c:out value="${billable.price}" /></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
