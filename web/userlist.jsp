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
    <title>All Users</title>
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

<div class="container-fluid">
    <div class="container">
        <div class="row text-center">

            <c:forEach begin="${startIndex}" end="${startIndex + content - 1}" var="balances" items="${balances}">


                <c:if test="${User.getIdentity() == balances.getUser().getIdentity()}">
                    <div style="background-color: gainsboro" class="col-sm-4 col-lg-3 ex2">
                        <form method="post" action="edit_user.html">
                            <c:if test="${balances.getUser().getRole() == 'USER'}">
                                <img class="card-img-top" src="images/user.jpg" alt="Card image cap">
                                <span class="badge badge-primary"><fmt:message bundle="${locale}" key="body.user"/></span>
                                <label for="select"><fmt:message bundle="${locale}" key="body.setnewrole"/></label>
                                <select id="select" name="select" disabled>

                                    <option selected="selected">USER</option>

                                    <option>ADMINISTRATOR</option>

                                </select>
                            </c:if>
                            <c:if test="${balances.getUser().getRole() == 'ADMINISTRATOR'}">
                                <img class="card-img-top" src="images/admin.png" alt="Card image cap">
                                <span class="badge badge-danger"><fmt:message bundle="${locale}" key="body.admin"/></span>
                                <label for="select"><fmt:message bundle="${locale}" key="body.setnewrole"/></label>
                                <select id="select" name="select" disabled>

                                    <option>USER</option>

                                    <option selected="selected">ADMINISTRATOR</option>

                                </select>
                            </c:if>

                            <p>${balances.getUser().getFirstName()} ${balances.getUser().getLogin()}
                                    ${balances.getUser().getLastName()}</p>

                            <label for="discount"><fmt:message bundle="${locale}" key="body.setnewdiscount"/></label>
                            <input class="form-control" id="discount" type="number" name="discount"
                                   min="0" max="99" value="${balances.getUser().getDiscount()*100}" disabled>

                            <label for="balance"><fmt:message bundle="${locale}" key="body.setnewcurrentbalance"/></label>
                            <input class="form-control" id="balance" type="number" name="balance"
                                   min="0" value="${balances.getCurrentBalance()}" disabled>

                            <label for="overdraft"><fmt:message bundle="${locale}" key="body.setnewcurrentbalance"/></label>
                            <input class="form-control" id="overdraft" type="number" name="overdraft"
                                   min="0" value="${balances.getOverdraft()}" disabled>

                            <h3></h3>
                            <input type="hidden" name="id" value="${balances.getUser().getIdentity()}">
                            <input type="hidden" name="start" value="${startIndex/8.0}">
                            <button type="submit" class="btn btn-danger center-block" disabled><fmt:message bundle="${locale}" key="body.setChanges"/></button>
                            <h3></h3>

                        </form>
                    </div>
                </c:if>


                <c:if test="${User.getIdentity() != balances.getUser().getIdentity()}">
                    <div style="background-color: gainsboro" class="col-sm-4 col-lg-3 ex2">
                        <form method="post" action="edit_user.html">
                            <c:if test="${balances.getUser().getRole() == 'USER'}">
                                <img class="card-img-top" src="images/user.jpg" alt="Card image cap">
                                <span class="badge badge-primary"><fmt:message bundle="${locale}" key="body.user"/></span>
                                <label for="select"><fmt:message bundle="${locale}" key="body.setnewrole"/></label>
                                <select id="select" name="select">

                                    <option selected="selected">USER</option>

                                    <option>ADMINISTRATOR</option>

                                </select>
                            </c:if>
                            <c:if test="${balances.getUser().getRole() == 'ADMINISTRATOR'}">
                                <img class="card-img-top" src="images/admin.png" alt="Card image cap">
                                <span class="badge badge-danger"><fmt:message bundle="${locale}" key="body.admin"/></span>
                                <label for="select"><fmt:message bundle="${locale}" key="body.setnewrole"/></label>
                                <select id="select" name="select">

                                    <option>USER</option>

                                    <option selected="selected">ADMINISTRATOR</option>

                                </select>
                            </c:if>

                            <p>${balances.getUser().getFirstName()} ${balances.getUser().getLogin()}
                                    ${balances.getUser().getLastName()}</p>

                            <label for="discount"><fmt:message bundle="${locale}" key="body.setnewdiscount"/></label>
                            <input class="form-control" id="discount" type="number" name="discount"
                                   min="0" max="99" value="${balances.getUser().getDiscount()*100}">

                            <label for="balance"><fmt:message bundle="${locale}" key="body.setnewcurrentbalance"/></label>
                            <input class="form-control" id="balance" type="number" name="balance"
                                   min="0" value="${balances.getCurrentBalance()}">

                            <label for="overdraft"><fmt:message bundle="${locale}" key="body.setnewoverdraft"/></label>
                            <input class="form-control" id="overdraft" type="number" name="overdraft"
                                   min="0" value="${balances.getOverdraft()}">

                            <h3></h3>
                            <input type="hidden" name="id" value="${balances.getUser().getIdentity()}">
                            <input type="hidden" name="start" value="${startIndex/8.0}">
                            <button type="submit" class="btn btn-danger center-block"><fmt:message bundle="${locale}" key="body.setChanges"/></button>
                            <h3></h3>

                        </form>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:if test="${startIndex == 0}">
            <form>
                <button class="btn btn-danger my-2 my-sm-0 disabled" type="" disabled><fmt:message bundle="${locale}" key="body.previous"/></button>
            </form>
        </c:if>

        <c:if test="${startIndex != 0}">
            <form method="post" action="userlist.html">
                <button class="btn btn-danger my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}" key="body.previous"/></button>
                <input type="hidden" name="index" value="-1">
                <input type="hidden" name="start" value="${startIndex/8.0}">
            </form>
        </c:if>

        <li class="page-item"><a class="page-link">${startIndex/8.0 + 1}</a></li>

        <c:if test="${content + startIndex + 1  < size}">
            <form method="post" action="userlist.html">
                <button class="btn btn-danger my-2 my-sm-0" type="submit"><fmt:message bundle="${locale}" key="body.next"/></button>
                <input type="hidden" name="index" value="1">
                <input type="hidden" name="start" value="${startIndex/8.0}">
            </form>
        </c:if>

        <c:if test="${content + startIndex + 1  >= size}">
            <form>
                <button class="btn btn-danger my-2 my-sm-0 disabled" type="" disabled><fmt:message bundle="${locale}" key="body.next"/></button>
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
