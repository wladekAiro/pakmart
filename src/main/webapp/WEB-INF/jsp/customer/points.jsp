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
        <h3 class="box-title">POINTS MANAGEMENT</h3>

        <div class="box-tools">
            <div class="input-group">
                <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;"
                       placeholder="Search"/>

                <div class="input-group-btn">
                    <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                </div>
            </div>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <div class="box">
            <div class="box-header">
                <div class="box-tools">
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#buyingModal">
                        Buying cost
                    </button>
                    &nbsp;&nbsp;
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#sellingModal">
                        Selling cost
                    </button>
                </div>

                <c:if test="${message}">
                    <div class="alert alert-success">
                            ${content}
                    </div>
                </c:if>
            </div>
            <div class="box-body">
                <div class="col-sm-7">
                    <h3>Point Costs</h3>

                    <div class="row">
                        <h3>Selling(Cost of redeeming)</h3>
                        <div class="table-responsive">
                            <c:choose>
                                <c:when test="${setSellingCost == null}">
                                    <div class="alert alert-warning">
                                        Selling Cost of point not set
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>Date set</th>
                                            <th>Point(s) per Ksh</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>${setSellingCost.dateCreated()}</td>
                                            <td>${setSellingCost.cost}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="row">
                        <h3>Buying(Cost of getting a point)</h3>
                        <div class="table-responsive">
                            <c:choose>
                                <c:when test="${setBuyingCost == null}">
                                    <div class="alert alert-warning">
                                        Cost of getting a point is not set
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>Date set</th>
                                            <th>Ksh per point</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>${setBuyingCost.dateCreated()}</td>
                                                <td>${setBuyingCost.cost}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>


                </div>
                <%--<div class="col-sm-3">--%>
                    <%--<div style="margin-top: 50px">--%>
                        <%--<div class="box-body">--%>
                            <%--<button type="button" class="btn btn-default btn-sm" data-toggle="modal"--%>
                                    <%--data-target="#buyingModal">--%>
                                <%--Add Item--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>

<%--modal--%>
<div class="modal fade bs-example-modal-lg" id="buyingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Set buying point cost</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">
                        <form:form acceptCharset="UTF-8" action="/administrator/manage/setBuying" method="post"
                                   modelAttribute="buyingPointCost" cssClass="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="cost" class="col-sm-3 control-label">Cost per point</label>

                                <div class="col-sm-9">
                                    <form:input path="cost" id="cost" type="text" cssClass="form-control"
                                                placeholder="Cost per point"/>
                                    <form:errors path="cost" cssClass="form-inline"/>
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


<div class="modal fade bs-example-modal-lg" id="sellingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Set Selling point cost</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">
                        <form:form acceptCharset="UTF-8" action="/administrator/manage/setSelling" method="post"
                                   modelAttribute="sellingPointCost" cssClass="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="cost" class="col-sm-3 control-label">Cost per point</label>

                                <div class="col-sm-9">
                                    <form:input path="cost" id="cost" type="text" cssClass="form-control"
                                                placeholder="Cost per point"/>
                                    <form:errors path="cost" cssClass="form-inline"/>
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
