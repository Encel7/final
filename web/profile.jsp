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
    <title>Profile</title>
</head>
<body background="images/bg.jpg">
<c:if test="${success != null}">
    <div class="alert alert-success block1" role="alert">
            ${success}
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
    <div class="col-lg-4 col-md-4 col-sm-12">
        <div class="card" style="width: 25rem; background-color: gainsboro">
            <c:if test="${User.getRole() == 'USER'}">
                <img class="card-img-top" src="images/user.jpg" alt="Card image cap">
                <span class="badge badge-primary"><fmt:message bundle="${locale}" key="body.user"/></span>
            </c:if>
            <c:if test="${User.getRole() == 'ADMINISTRATOR'}">
                <img class="card-img-top" src="images/admin.png" alt="Card image cap">
                <span class="badge badge-danger"><fmt:message bundle="${locale}" key="body.admin"/></span>
            </c:if>
            <div class="card-body">
                <h5 class="card-title">${User.getFirstName()} ${User.getLastName()}</h5>
                <p class="card-text"><fmt:message bundle="${locale}" key="body.login"/> : ${User.getLogin()}</p>
                <p class="card-text"><fmt:message bundle="${locale}" key="body.discount"/> : ${User.getDiscount()*100}%</p>
                <p class="card-text"><fmt:message bundle="${locale}" key="body.curbalance"/> : ${Balance.getCurrentBalance()}</p>
                <p class="card-text"><fmt:message bundle="${locale}" key="body.avloverdruft"/> : ${Balance.getOverdraft()}</p>
                <form method="post" action="translate.html">
                    <label for="select"><fmt:message bundle="${locale}" key="body.setlanguage"/></label>
                    <select id="select" name="select">
                        <option selected="selected">English</option>
                        <option>Russian</option>
                    </select>
                    <button type="submit" class="btn btn-danger"><fmt:message bundle="${locale}"
                                                                                                    key="head.i18n"/></button>
                </form>
                <a href="#" class="btn btn-danger" data-toggle="modal"
                   data-target="#exampleModal"><fmt:message bundle="${locale}" key="body.addsouvenir"/></a>
                <a href="#" class="btn btn-danger" data-toggle="modal"
                   data-target="#exampleModalBalance"><fmt:message bundle="${locale}" key="body.replbalance"/></a>
            </div>
        </div>
    </div>
    <div class="col-lg-8 col-md-8 col-sm-12 desc">
        <ul class="list-group-item" style="background-color: gainsboro" class="list-group" style="background-color: gainsboro">
            <li><h3><fmt:message bundle="${locale}" key="body.purchase"/>:</h3></li>
            <c:forEach var="orders" items="${orders}">
                <li class="list-group-item" style="background-color: gainsboro">
                    <fmt:message bundle="${locale}" key="body.date"/> : ${orders.getDate()} <fmt:message bundle="${locale}" key="body.totalprice"/> : ${orders.getTotalPrice()}
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message bundle="${locale}" key="body.addsouvenir"/></h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="add_souvenir.html" enctype="multipart/form-data">
                <div class="modal-body">

                    <div>
                        <label>
                            <fmt:message bundle="${locale}" key="modal.size"/>
                            <input min="1" max="100" type="number" name="size" required>
                        </label>
                    </div>
                    <div>
                        <label>
                            <fmt:message bundle="${locale}" key="modal.color"/>
                            <input type="text" name="color" required>
                        </label>
                    </div>
                    <div>
                        <label>
                            <fmt:message bundle="${locale}" key="modal.price"/>
                            <input min="1" max="500" type="number" name="price" required>
                        </label>
                    </div>
                    <div>
                        <label for="file"><fmt:message bundle="${locale}" key="modal.choosefile"/>
                            <input type="file" id="file" name="file" multiple>
                        </label>
                    </div>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-secondary" data-dismiss="modal"><fmt:message bundle="${locale}" key="modal.close"/></button>
                    <button class="btn btn-primary" type="submit"><fmt:message bundle="${locale}" key="modal.submit"/></button>
                </div>
                <input type="hidden" name="filename">
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModalBalance" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalBalance" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabelBalance"><fmt:message bundle="${locale}" key="body.replbalance"/></h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="replenish_balance.html" enctype="multipart/form-data">
                <div class="modal-body">
                    <div>
                        <label>
                            <fmt:message bundle="${locale}" key="modal.sum"/>
                            <input type="number" min="1" name="sum" required >
                        </label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" data-dismiss="modal"><fmt:message bundle="${locale}" key="modal.close"/></button>
                    <button class="btn btn-danger" type="submit"><fmt:message bundle="${locale}" key="modal.submit"/></button>
                </div>
                <input type="hidden" name="filename">
            </form>
        </div>
    </div>
</div>

</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
</html>
