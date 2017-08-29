<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div class="container" style="margin-bottom: 40px; margin-top: 10px">
    <div class="panel">
        <div style="display: flex; justify-content: center ">
            <div class="form-group">
                <input type="text" class="form-control" id="categoryName"
                       placeholder="brand name"/>
                <button class="btn btn-default" id="saveBrand">Add Brand</button>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th colspan="2">Action</th>
            </tr>
            </thead>
            <tbody id="result">
            </tbody>
        </table>
    </div>
</div>

<input type="hidden" name="csrf_name"
       value="${_csrf.parameterName}" />
<input type="hidden" name="csrf_value"
       value="${_csrf.token}" />

<script src="<c:url value="/resources/js/brand.js"/>"></script>

