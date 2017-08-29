<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div>
    <div class="container">

        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Address</th>
                <th>Role</th>
                <th>Billable</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.address}" /></td>
                    <td><c:out value="${user.role}" /></td>
                    <td>
                    <c:forEach items="${user.billable}" var="billable">
                    <a href="billableDetails/${billable.id}">BL000${billable.id}</a><br />
                    </c:forEach>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
