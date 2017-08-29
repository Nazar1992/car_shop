<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div class="container">

    <table class="table table-hover">
        <thead>
        <tr>
            <c:if test="${userCart.cars.size() == 0}">
                Cart is empty!!!
            </c:if>
            <c:if test="${userCart.cars.size() != 0}">
                <th>Item Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Content</th>
                <th>Category</th>
                <th>Quantity</th>
                <th>Delete from billable</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <form:form action="buy" method="post">
            <c:forEach items="${userCart.cars}" var="car">
                <tr>
                    <td><c:out value="${car.id}"/></td>
                    <td><c:out value="${car.name}"/></td>
                    <td><c:out value="${car.price}"/></td>
                    <td><c:out value="${car.content}"/></td>
                    <td><c:out value="${car.brand}"/></td>
                    <td>
                        <label>
                            <input type="number" name="itemQuantity" value="${car.quantity}" min="1">
                        </label>
                    </td>
                    <td><a href="deleteFromCart/${userCart.id}/${car.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${userCart.cars.size() != 0}">
                <button class="btn btn-default">Buy</button>
            </c:if>
        </form:form>
        </tbody>
    </table>
</div>