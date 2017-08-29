<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<c:url var="save" value="/updateCar/${car.id}?${_csrf.parameterName}=${_csrf.token}"/>
<form:form modelAttribute="car" method="post" action="${save}" enctype="multipart/form-data">
    <table>
        <tr>
            <td><form:label path="name">Name:</form:label></td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td><form:label path="price">Price:</form:label></td>
            <td><form:input type="number" path="price"/></td>
        </tr>

        <tr>
            <td><form:label path="content">Description:</form:label></td>
            <td><form:input path="content"/></td>
        </tr>

        <tr>
            <td><form:label path="brand">Brand:</form:label></td>
            <td><form:select path="brand" items="${brands}" itemLabel="name" itemValue="id"/></td>
        </tr>

        <tr>
            <td><form:label path="pathImage">Image:</form:label></td>
            <td><img src="/${car.pathImage}" alt=""></td>
            <td><form:input type="hidden" path="pathImage"/></td>
        </tr>

        <tr>
            <input type="file" name="image">
        </tr>
    </table>
    <input type="submit" value="Save" />
</form:form>
