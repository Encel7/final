
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="by.epam.shop.text" var="locale"/>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <title>Tattoo Salon</title>
</head>

<c:if test="${fail != null}">
    <div class="alert alert-danger block1" role="alert">
            ${fail}
    </div>
</c:if>

<body background="images/bg.jpg">

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

<div class="bg">

    <form method="post" action="log_in.html">
        <div class="form-group">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-5"></div>
                <div class="col-lg-2 col-md-2 col-sm-2 ex1" style="background-color: dimgrey">
                    <label for="exampleInputLogin"><fmt:message bundle="${locale}" key="body.login"/></label>
                    <input type="text" name="login" id="exampleInputLogin"
                           class="form-control" placeholder="Login" required>
                    <label for="exampleInputPassword"><fmt:message bundle="${locale}" key="body.password"/></label>
                    <input type="password" name="password" id="exampleInputPassword"
                           class="form-control" placeholder="Password" required>

                    <button type="submit" class="btn btn-danger center-block"><fmt:message bundle="${locale}" key="body.singin"/></button>
                    <h5 align=""><a style="color: darkred" href="registration.jsp"><fmt:message bundle="${locale}" key="body.registerhere"/></a></h5>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="https://code.j query.com/jquery-3.3.1.slim.min.js"
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
