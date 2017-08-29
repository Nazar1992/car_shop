<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%--<link rel="stylesheet" href="/resources/css/index.css">--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="<c:url value="/resources/js/index.js"/>"></script>
<script src="<c:url value="/resources/js/search.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $('.add-to-billable').on('click', function () {
            //Scroll to top if billable icon is hidden on top
            $('html, body').animate({
                'scrollTop': $(".cart_anchor").position().top
            });
            //Select car image and pass to the function
            var itemImg = $(this).parent().find('img').eq(0);
            flyToElement($(itemImg), $('.cart_anchor'));
        });
    });

    $(function () {
        searchCategories();
    });
</script>

<div>

<div style="margin-bottom: 10px; margin-top: 10px; width: 20%; float: right; padding-right: 10px">
    <div data-role="rangeslider">
        <p>
            <label for="amount">Price range:</label>
            <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
        </p>
        <label for="price-min">Min:</label>
        <input type="range" name="price-min" id="price-min" value="200" min="0" max="1000" onchange="changeLabel()"
               oninput="searchPrices()">
        <label for="price-max">Max:</label>
        <input type="range" name="price-max" id="price-max" value="800" min="0" max="1000" onchange="changeLabel()"
               oninput="searchPrices()">
    </div>
    <input style="margin-top: 5px" type="text" class="form-control" id="searchIn" oninput="searchItems()"
           placeholder="Search..."/>
</div>

<%--<div class="dropdown" style="float: left; padding-left: 10px; margin-top: 10px;">
    <button class="dropdown-toggle; btn btn-default" data-toggle="dropdown" role="button" aria-expanded="false">
        Categories
    </button>
    <ul class="dropdown-menu" role="menu" style="margin: 1%; padding: 0; list-style: none">
        <li><span class="mySpan">All</span></li>
        <li><span class="mySpan">CPU</span></li>
        <li><span class="mySpan">GPU</span></li>
        <li><span class="mySpan">RAM</span></li>
        <li><span class="mySpan">PSU</span></li>
        <li><span class="mySpan">Case</span></li>
        <li><span class="mySpan">Storage</span></li>
        <li><span class="mySpan">Motherboard</span></li>
    </ul>
</div>--%>

<div class="wrapper">
    <span style="margin-top: 90px"><i class="cart_anchor"></i></span>

    <div class="clear"></div>
    <div id="searchDiv" class="cars">
        <c:forEach items="${cars.content}" var="car">
            <div class="car">
                <img src="${car.pathImage}" alt="">
                <h2><c:out value="${car.name}"/><p style="float: right">$<em>${car.price}</em></p></h2>
                <h2><c:out value="${car.content}"/></h2>
                <sec:authorize access="isAuthenticated() && hasRole('ROLE_USER')">
                    <button style="margin: auto; display: block;" class="add-to-billable"
                            onclick="window.location.href='addToCart/${car.id}'">AddToCart
                    </button>
                </sec:authorize>
            </div>
        </c:forEach>
    </div>
</div>

<div class="pageable" style="display: flex; justify-content: center;text-align: center">

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
</div>