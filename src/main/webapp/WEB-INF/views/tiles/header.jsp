<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="fixed-header">
    <div class="container">
        <nav>
            <sec:authorize access="hasRole('ROLE_USER')">
                <a href="<c:url value="/"/>">Home</a>
                <a href="<c:url value="/profile"/>">Profile</a>
                <a href="<c:url value="/billable"/>">Cart</a>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <a href="<c:url value="/signUp"/>">Login</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="<c:url value="/listOfUsers"/>">Users</a>
                <a href="<c:url value="/listOfCars"/>">Cars</a>
                <a href="<c:url value="/brands"/>">Brands</a>
                <a href="<c:url value="/listOfBillables"/>">Cart</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="<c:url value="/logout"/>">Logout</a>
            </sec:authorize>
        </nav>
    </div>
</div>