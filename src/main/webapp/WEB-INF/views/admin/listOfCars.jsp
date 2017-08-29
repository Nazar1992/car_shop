<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type='text/javascript' src="<c:url value="/resources/js/search.js"/>"></script>

<div>
    <div class="container">

        <form action="<c:url value="/car"/>" style="float: left; margin-top: 10px">
            <input type="submit" value="Add Car"/>
        </form>

        <div style="float: right; margin-top: 10px">
            <input type="text" class="form-control" id="searchIn" oninput="searchInTable();"
                   placeholder="Search..."/>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>Item Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Content</th>
                <th>Image</th>
                <th colspan=2>Action</th>
            </tr>
            </thead>
            <tbody id="searchResult">
            <c:forEach items="${cars.content}" var="car">
                <tr>
                    <td><c:out value="${car.id}"/></td>
                    <td><c:out value="${car.name}"/></td>
                    <td><c:out value="${car.price}"/></td>
                    <td><c:out value="${car.content}"/></td>
                    <td><img src="${car.pathImage}" alt=""></td>
                    <td><a href="/car_shop/deleteCar/${car.id}">Delete</a></td>
                    <td><a href="/car_shop/updateCar/${car.id}">Update</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div style="display: flex; justify-content: center;text-align: center; margin-bottom: 60px">

    <div class="col-md-12 col-xs-12">
        <div class="row">
            <div class="col-md-2 col-xs-6">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span
                            class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <custom:sort innerHtml="Name asc" paramValue="name"/>
                        <custom:sort innerHtml="Name desc" paramValue="name,desc"/>
                        <custom:sort innerHtml="Price asc" paramValue="price"/>
                        <custom:sort innerHtml="Price desc" paramValue="price,desc"/>
                    </ul>
                </div>
            </div>
            <div class="col-md-8 col-xs-12 text-center">
                <custom:pageable page="${cars}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
            </div>
            <div class="col-md-2 col-xs-6">
                <custom:size posibleSizes="1,2,5,10,25,50,100" size="${cars.size}"/>
            </div>
        </div>
    </div>
</div>

<input type="hidden" name="csrf_name"
       value="${_csrf.parameterName}"/>
<input type="hidden" name="csrf_value"
       value="${_csrf.token}"/>
