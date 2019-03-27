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
    <title>Salon</title>
</head>
<body background="images/bg.jpg">

<c:if test="${paid != null}">
    <div class="alert alert-success block1" role="alert">
            ${paid}
    </div>
</c:if>
<c:if test="${credit != null}">
    <div class="alert alert-warning block1" role="alert">
            ${credit}
    </div>
</c:if>
<c:if test="${fail != null}">
    <div class="alert alert-danger bl block1" role="alert">
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


<div class="container-fluid p-0">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li class="active" data-target="#carouselExampleIndicators" data-slide-to="0">
            </li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2">

            </li>
            <li data-target="#carouselExampleIndicators" data-slide-to="3">

            </li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="images/slider-1.jpg" alt="" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="images/slider-2.jpg" alt="" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="images/slider-3.jpg" alt="" class="d-block w-100">
            </div>
        </div>
        <a href="#carouselExampleIndicators" class="carousel-control-prev"
           role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a href="#carouselExampleIndicators" class="carousel-control-next"
           role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<h3></h3>
<h1 class="text-center" style="color: black"><span
        class="badge badge-danger"><fmt:message bundle="${locale}"
                                                key="body.search"/></span></h1>
<form method="post" action="search.html">
    <div class="container">
        <div class="d-flex justify-content-center">
            <div class="searchbar">
                <input type="hidden" name="start" value="${startIndex/8.0}">
                <input class="search_input" type="text" name="color" placeholder="Search by color">
                <button type="submit" class="search_icon"><i class="fas fa-search"><img src="images/search.png"
                                                                                        style="height: 30px; width: 30px"
                                                                                        alt=""/></i>
                </button>
            </div>
        </div>
    </div>
</form>
<h3></h3>
<div class="container-fluid">
    <div class="container">
        <div class="row text-center">
            <c:forEach begin="${startIndex}" end="${startIndex + content - 1}" var="souvenir" items="${souvenir}">
                <div style="background-color: gainsboro" class="col-xs-6 col-sm-4 col-lg-3 ex2">
                    <form method="post" action="add_to_order.html">
                        <img src="${souvenir.getImage()}" alt="" width="200" height="250">
                        <h4>${souvenir.getPrice()} BYN</h4>
                        <p><fmt:message bundle="${locale}" key="body.author"/>
                            : ${souvenir.getAuthor().getFirstName()}</p>
                        <p><fmt:message bundle="${locale}" key="body.color"/>: ${souvenir.getColor()}</p>
                        <p><fmt:message bundle="${locale}" key="body.size"/> :${souvenir.getSize()}</p>
                        <input type="hidden" name="start" value="${startIndex/8.0}">
                        <input type="hidden" name="id" value="${souvenir.getIdentity()}">
                        <c:if test="${User != null}">
                            <button type="submit" class="btn btn-danger center-block"><fmt:message bundle="${locale}"
                                                                                                   key="body.cartadd"/></button>
                        </c:if>
                        <h3></h3>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<h3></h3>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:if test="${startIndex == 0}">
            <form>
                <button class="btn btn-danger my-2 my-sm-0 disabled" type="" disabled><fmt:message bundle="${locale}"
                                                                                                   key="body.previous"/></button>
            </form>
        </c:if>

        <c:if test="${startIndex != 0}">
            <form method="post" action="usermain.html">
                <button class="btn btn-danger my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}"
                                                                                       key="body.previous"/></button>
                <input type="hidden" name="index" value="-1">
                <input type="hidden" name="start" value="${startIndex/8.0}">
            </form>
        </c:if>

        <li class="page-item"><a class="page-link">${startIndex/8.0 + 1}</a></li>

        <c:if test="${content + startIndex + 1  < size}">
            <form method="post" action="usermain.html">
                <button class="btn btn-danger my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}"
                                                                                       key="body.next"/></button>
                <input type="hidden" name="index" value="1">
                <input type="hidden" name="start" value="${startIndex/8.0}">
            </form>
        </c:if>

        <c:if test="${content + startIndex + 1  >= size}">
            <form>
                <button class="btn btn-danger my-2 my-sm-0 disabled" type="" disabled><fmt:message bundle="${locale}"
                                                                                                   key="body.next"/></button>
            </form>
        </c:if>
    </ul>
</nav>

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
