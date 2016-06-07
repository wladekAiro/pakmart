<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/jsp/common/adminHeader.jsp">
        <jsp:param name="title" value="POCKET CARD"/>
    </jsp:include>
</head>
<jsp:include page="/WEB-INF/jsp/common/usersHead.jsp"/>

<!-- Carousel
================================================== -->
<%--body--%>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">SHOP ITEM(S) MANAGEMENT</h3>
        <div class="box-tools">
            <div class="input-group">
                <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>
                <div class="input-group-btn">
                    <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                </div>
            </div>
        </div>
    </div><!-- /.box-header -->
    <div class="box-body">
        <div class="box">
            <div class="box-header">
                <div class="box-tools">
                    <a type="button" class="btn btn-primary btn-sm" href="/admin/users">
                        Users
                    </a>
                    &nbsp;&nbsp;
                    <a type="button" class="btn btn-primary btn-sm" href="/admin/users?roles=true">
                        Roles
                    </a>
                </div>

                <c:if test="${message}">
                    <div class="alert alert-success">
                            ${content}
                    </div>
                </c:if>
            </div>
            <div class="box-body">
                <div class="col-sm-7">
                            <h3>Items</h3>
                            <div class="table-responsive">
                                <c:choose>
                                    <c:when test="${empty itemPage.content}">
                                        <div class="alert alert-war">
                                            No Items available yet
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>Name of item</th>
                                                <th>Unit price(Ksh)</th>
                                                <th>Description</th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${itemPage.content}" var="item">
                                                <tr>
                                                    <td>${item.name}</td>
                                                    <td>${item.unitPrice}</td>
                                                    <td>${item.description}</td>
                                                    <td>
                                                        <a href="#">Show</a>
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
                <div class="col-sm-3">
                    <div style="margin-top: 50px">
                        <div class="box-body">
                                    <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">
                                        Add Item
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
                        <h4 class="modal-title" id="myModalLabel">Create shop item</h4>
                    </div>
                    <div class="modal-body">
                        <div class="box">
                            <div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">
                                <form:form acceptCharset="UTF-8" action="/users/item/createItem" method="post" modelAttribute="item" cssClass="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="name" class="col-sm-3 control-label">Name</label>
                                        <div class="col-sm-9">
                                            <form:input path="name" id="name" type="text" cssClass="form-control" placeholder="Name of item" />
                                            <form:input path="id" id="id" type="hidden"/>
                                            <form:errors path="name" cssClass="form-inline" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="unitPrice" class="col-sm-3 control-label">Unit Price</label>
                                        <div class="col-sm-9">
                                            <form:input path="unitPrice" id="unitPrice" type="text" cssClass="form-control" placeholder="Set unit price" />
                                            <form:errors path="unitPrice" cssClass="form-inline" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="description" class="col-sm-3 control-label">Description</label>
                                        <div class="col-sm-9">
                                            <form:textarea path="description" id="description" cssClass="form-control" placeholder="Description of item"></form:textarea>
                                            <form:errors path="description" cssClass="form-inline" />
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
