<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div>
    <div class="container">

        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Time</th>
                <th>User</th>
                <th>Items</th>
                <th>Price</th>
                <th colspan=2>Action</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                <c:forEach items="${billables.content}" var="billable">
                    <td><c:out value="${billable.id}" /></td>
                    <td><c:out value="${billable.localDateTime}" /></td>
                    <td><c:out value="${billable.user.name}" /></td>
                    <td>
                    <c:forEach items="${billable.car}" var="car">
                        <c:out value="${car.name}" /><br />
                    </c:forEach>
                    </td>
                    <td><c:out value="${billable.price}" /></td>
                    <td><a href="deleteBillable/${billable.id}">Delete</a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div style="display: flex; justify-content: center;text-align: center; margin-bottom: 40px">

    <div class="col-md-12 col-xs-12">
        <div class="row">
            <div class="col-md-2 col-xs-6">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span
                            class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <custom:sort innerHtml="Date asc" paramValue="localDateTime"/>
                        <custom:sort innerHtml="Date desc" paramValue="localDateTime,desc"/>
                    </ul>
                </div>
            </div>
            <div class="col-md-8 col-xs-12 text-center">
                <custom:pageable page="${billables}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
            </div>
            <div class="col-md-2 col-xs-6">
                <custom:size posibleSizes="1,2,5,10,25,50,100" size="${billables.size}"/>
            </div>
        </div>
    </div>
</div>
