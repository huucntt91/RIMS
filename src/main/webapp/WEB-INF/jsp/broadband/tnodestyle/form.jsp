<%-- 
    Document   : form
    Created on : Aug 8, 2017, 10:48:07 AM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../../include/header.jsp"%>

<section class="content">            
    <div class="row">
        <div class="col-xs-12">



            <div class="box box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">Thông tin style</h3>
                </div>
                <!-- /.box-header -->

                <form:form id="addForm" name="addForm" class="form-horizontal"  method="post"
                           modelAttribute="tnodeStyleBO" action="${pageContext.request.contextPath}/tnodestyle/update?${_csrf.parameterName}=${_csrf.token}"
                           enctype="multipart/form-data">
                    <div class="box-body" id="mydiv" >                        
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin tnode style</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-3"></div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">                                    
                                                <label class=" input-group-addon">Loại TNODE</label>
                                                <select  name="typeId" id="typeId" class="form-control" required="true">
                                                    <option value="">--- Chọn loại tnode ---</option>
                                                    <c:forEach var="it" items="${tnodeTypeList}">
                                                        <option value="${it.id}" <c:choose> 
                                                                    <c:when test="${it.id == tnodeStyleBO.id}">
                                                                        selected    
                                                                    </c:when>    
                                                                </c:choose> 
                                                                >${it.name}</option>
                                                    </c:forEach>
                                                </select>                                  
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3"></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3"></div>
                                    <div class="col-md-6">
                                        <div class="form-group" id="addForm-div-imgIcon">
                                            <div class="input-group">
                                                <input type="hidden" id="id" name="id" value="${tnodeStyleBO.id}"/>
                                                <label for="imgIcon"  class=" input-group-addon">Kiểu</label>
                                                <input type="text" id="status" name="status" value="${tnodeStyleBO.status}"/>                                        
                                            </div>
                                        </div>                                            
                                    </div>
                                    <div class="col-md-3"></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3"></div>
                                    <div class="col-md-6">
                                        <div class="form-group" id="addForm-div-imgIcon">
                                            <div class="input-group">
                                                <label for="imgIcon"  class=" input-group-addon">NOTE</label>
                                                <input type="text" id="note" name="note" value="${tnodeStyleBO.note}"/>
                                            </div>
                                        </div>                                            
                                    </div>
                                    <div class="col-md-3"></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3"></div>
                                    <div class="col-md-6">
                                        <div class="form-group" id="addForm-div-imgIcon">
                                            <div class="input-group">
                                                <img src="${pageContext.request.contextPath}/${urlImage}//${tnodeStyleBO.url}" width="50" id="addForm-imgIcon-show"/>
                                            </div>
                                        </div>                                            
                                    </div>
                                    <div class="col-md-3"></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3"></div>
                                    <div class="col-md-6">                                    
                                        <form:input type="file"  class="control-label" path="file"/>                                    
                                    </div>
                                    <div class="col-md-3"></div>
                                </div>
                            </div>
                            <div class="row">
                                  <div class="col-md-3"></div>
                                    <div class="col-md-6">
                                        <div class="form-group" id="addForm-div-imgIcon">
                                            <div class="input-group">
                                                <label for="size"  class=" input-group-addon">Size</label>
                                                <input type="text" id="note" name="size" value="${tnodeStyleBO.size}"/>
                                            </div>
                                        </div>                                            
                                    </div>
                                    <div class="col-md-3"></div>  
                            </div>
                            <div class="box-footer" align="center">
                                <button type="submit"  class="btn btn-success">Cập nhật</button>
                            </div>
                        </div>
                    </div>    
                    <!-- /.box-body -->

                </form:form>
                <!-- /.box-body -->
            </div>
        </div>           
</section>

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>



