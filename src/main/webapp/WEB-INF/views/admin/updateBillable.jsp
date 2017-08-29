<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<c:url var="save" value="${billableAttribute.id}"/>
<form:form modelAttribute="billableAttribute" method="post" action="${save}">
    <table>
        <tr>billableAttribute
            <td><form:label path="localDateTime">Time:</form:label></td>
            <td><form:input type="datetime-local" path="localDateTime"/></td>
        </tr>

        <tr>
            <td><form:label path="user">User:</form:label></td>
            <td><form:select path="user" items="${users}" itemLabel="name" itemValue="id"/></td>
        </tr>

        <tr>
            <td><form:label path="car">Items:</form:label></td>
            <td><form:select multiple="true" path="car" items="${cars}" itemLabel="name" itemValue="id"/></td>
        </tr>
    </table>
    <input type="submit" value="Save" />
</form:form>
