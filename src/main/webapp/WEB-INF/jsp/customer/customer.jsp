<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h3 class="box-title">PakMart Loyalty Reward Portal</h3>

        <div class="box-tools">
            <%--<div class="input-group">--%>
            <%--<input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>--%>
            <%--<div class="input-group-btn">--%>
            <%--<button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>--%>
            <%--</div>--%>
            <%--</div>--%>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <div class="box">
            <div class="box-header">
                <div class="box-title">${customer.getFullName()}</div>
            </div>
            <div class="box-body">
                <p>Total Points :${customer.points} </p>
                <c:if test="${message}">
                        <div class="alert alert-success">
                                ${content}
                        </div>
                </c:if>
                <div class="box">
                    <div class="box-body">
                        <h4>Redeem points</h4>
                        <form:form acceptCharset="UTF-8" action="/administrator/manage/customer/redeem" method="post" modelAttribute="rewardForm" cssClass="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="sirName" class="col-sm-3 control-label">Points</label>
                                <div class="col-sm-9">
                                    <form:input path="amount" id="sirName" cssClass="form-control" type="text" placeholder="points to redeem" />
                                    <form:input path="phoneNumber" type="hidden" value="${customer.phoneNumber}"/>
                                    <form:input path="id" type="hidden" value="${customer.id}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-10">
                                    <input class="btn btn-success" type="submit" value="Redeem">
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--end body--%>
<!-- start of footer section -->

<jsp:include page="/WEB-INF/jsp/common/adminFooter.jsp"/>
</html>
