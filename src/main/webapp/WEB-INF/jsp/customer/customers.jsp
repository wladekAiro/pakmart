<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/jsp/common/adminHeader.jsp">
        <jsp:param name="title" value="PAKMART"/>
    </jsp:include>
</head>
<jsp:include page="/WEB-INF/jsp/common/usersHead.jsp"/>

<!-- Carousel
================================================== -->
<%--body--%>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">CUSTOMER(S) MANAGEMENT</h3>
        <div class="box-tools">
            <%--<div class="input-group">--%>
                <%--<input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>--%>
                <%--<div class="input-group-btn">--%>
                    <%--<button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
    </div><!-- /.box-header -->
    <div class="box-body">
        <div class="box">
            <div class="box-header">
                <%--<div class="box-tools">
                    <a type="button" class="btn btn-primary btn-sm" href="#">
                        Users
                    </a>
                    &nbsp;&nbsp;
                    <a type="button" class="btn btn-primary btn-sm" href="#">
                        Roles
                    </a>
                </div>--%>

                <c:if test="${message}">
                    <div class="col-sm-8">
                        <div class="alert alert-success">
                                ${content}
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="box-body">
                <div class="col-sm-7">
                    <div class="box">
                        <div class="box-header">
                            <div class="box-title">
                                Customers
                            </div>
                            <div class="box-tools">
                                <div class="input-group">
                                    <form:form method="post" action="/administrator/manage/customers/search" modelAttribute="rewardForm">
                                        <form:input path="phoneNumber" type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search by phone"/>
                                        <div class="input-group-btn">
                                            <input class="btn btn-sm btn-default" type="submit"><i class="fa fa-search"></i></input>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                        <div class="box-body">
                            <div class="table-responsive">
                                <c:choose>
                                    <c:when test="${empty customerPage.content}">
                                        <div class="alert alert-war">
                                            No Customers registered
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>Full name</th>
                                                <th>Phone number</th>
                                                <th>Id number</th>
                                                <th>Total Points</th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${customerPage.content}" var="customer">
                                                <tr>
                                                    <td>${customer.getFullName()}</td>
                                                    <td>${customer.phoneNumber}</td>
                                                    <td>${customer.idNumber}</td>
                                                    <td>
                                                            ${customer.points}
                                                    </td>
                                                    <td>
                                                        <a href="/administrator/manage/customers/${customer.id}/view">Show</a>
                                                    </td>
                                                    <td>
                                                        <a href="#">Delete</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div style="margin-top: 50px">
                        <div class="box-body">
                                    <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">
                                        Add Customer
                                    </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--modal--%>
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Register Customer</h4>
                    </div>
                    <div class="modal-body">
                        <div class="box">
                            <div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">
                                <form:form acceptCharset="UTF-8" action="/administrator/manage/customers" method="post" modelAttribute="customer" cssClass="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="firstName" class="col-sm-3 control-label">First Name</label>
                                        <div class="col-sm-9">
                                            <form:input path="firstName" id="firstName" type="text" cssClass="form-control" placeholder="Fisrt name" />
                                            <form:input path="id" id="id" type="hidden"/>
                                            <form:errors path="firstName" cssClass="form-inline" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="lastName" class="col-sm-3 control-label">Second Name</label>
                                        <div class="col-sm-9">
                                            <form:input path="secondName" id="lastName" type="text" cssClass="form-control" placeholder="Second/Middle name" />
                                            <form:errors path="secondName" cssClass="form-inline" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="sirName" class="col-sm-3 control-label">Sir name</label>
                                        <div class="col-sm-9">
                                            <form:input path="sirName" id="sirName" cssClass="form-control" type="text" placeholder="Sir name" />
                                            <form:errors path="sirName" cssClass="form-inline" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="idNumber" class="col-sm-3 control-label">Id Number</label>
                                        <div class="col-sm-9">
                                            <form:input path="idNumber" id="idNumber" cssClass="form-control" type="text" placeholder="ID/Pasport number" />
                                            <form:errors path="idNumber" cssClass="form-inline" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="phoneNumber" class="col-sm-3 control-label">Phone Number</label>
                                        <div class="col-sm-9">
                                            <form:input path="phoneNumber" id="phoneNumber" cssClass="form-control" type="text" placeholder="mobile phone number" />
                                            <form:errors path="phoneNumber" cssClass="form-inline" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-10">
                                            <input class="btn btn-success" type="submit" value="Submit">
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
    </div>
</div>
<%--end body--%>
<!-- start of footer section -->
<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
