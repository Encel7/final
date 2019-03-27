<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="by.epam.shop.text" var="locale"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <title>Order List</title>
</head>
<body background="images/bg.jpg">

<c:if test="${fail != null}">
    <div class="alert alert-danger block1" role="alert">
            ${fail}
    </div>
</c:if>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <a href="usermain.html" class="navbar-brand">
        <img src="images/logo.jpg" width="70" height="70" alt="logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <c:if test="${User != null}">
            <h4 style="color: darkred">${User.getLogin()}</h4>
        </c:if>
        <c:if test="${User != null}">
            <form method="post" action="log_out.html">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}"
                                                                                                key="head.logout"/></button>
            </form>
        </c:if>
        <c:if test="${User != null}">
            <form method="post" action="profile.html">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}"
                                                                                                key="head.profile"/></button>
            </form>
        </c:if>
        <c:if test="${User == null}">
            <form method="post" action="visit_log_in.html">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}"
                                                                                                key="head.login"/></button>
            </form>
        </c:if>

        <c:if test="${User.getRole() == 'ADMINISTRATOR'}">
            <form method="post" action="userlist.html">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}"
                                                                                                key="head.users"/></button>
            </form>
        </c:if>
        <c:if test="${Order != null}">
            <form method="post" action="orderlist.html">
                <button class="btn btn-outline-warning my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}"
                                                                                                key="head.checkout"/></button>
            </form>
        </c:if>
    </div>

</nav>

<div class="row">
    <div class="col-lg-4 col-md-4 col-sm-4"></div>
    <div class="col-lg-4 col-md-4 col-sm-4 " style="background-color: dimgrey">
        <ul class="list-group" style="background-color: gainsboro">
            <c:forEach var="order" items="${order}">
                <li class="list-group-item" style="background-color: gainsboro">
                    <img src="${order.getImage()}" alt="" width="100" height="125"> ${order.getPrice()} <fmt:message bundle="${locale}" key="body.currency"/>
                    <h3></h3>
                    <form method="post" action="delete.html">
                        <input type="hidden" name="id" value="${order.getIdentity()}">
                        <button type="submit" class="btn btn-danger center-block"><fmt:message bundle="${locale}" key="body.deleteorder"/></button>
                    </form>
                </li>
            </c:forEach>
            <li class="list-group-item" style="background-color: gainsboro"><fmt:message bundle="${locale}" key="body.totalprice"/> ${sum}
                <form method="post" action="checkout.html">
                    <button type="submit" class="btn btn-danger center-block"><fmt:message bundle="${locale}" key="head.checkout"/></button>
                </form>
            </li>
        </ul>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>

</body>
</html>
