<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<form:form modelAttribute="car" action="saveCar?${_csrf.parameterName}=${_csrf.token}"
           method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><form:label path="name">Name:</form:label></td>
            <td><form:input path="name"/></td>
            <td style="color: red">${itemnameException}</td>
        </tr>

        <tr>
            <td><form:label path="price">Price:</form:label></td>
            <td><form:input type="number" path="price"/></td>
            <td style="color: red">${itempriceException}</td>
        </tr>

        <tr>
            <td><form:label path="content">Description:</form:label></td>
            <td><form:input path="content"/></td>
            <td style="color: red">${itemcontentException}</td>
        </tr>

        <tr>
            <td><form:label path="brand">Brand:</form:label></td>
            <td><form:select path="brand" items="${brands}" itemLabel="name" itemValue="id"/></td>
        </tr>

        <tr>
            <td><form:label path="pathImage">Image:</form:label></td>
            <td><input  type="file" name="image" required="required"/></td>
        </tr>
    </table>
    <input type="submit" value="Save" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>
