<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../include/header.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js"></script>
        <div class="content-header">
            <h1>Cập nhật thông tin kiểm định</h1>
            <ol class="breadcrumb">                
                <div class="btn-group">
                        <a href="${pageContext.request.contextPath}/resources/excel/Template_NET_KiemDinh.xlsx">
                            <button type="button" class="btn btn-danger">
                                <i class="fa fa-download"></i> 
                                Download Template
                            </button>
                        </a>
                </div>
            </ol>
        </div>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"></h3>
                        </div>
                        <form:form method="POST" action="${pageContext.request.contextPath}/building/kiemdinh/update" commandName="cellNewExcelBO"
                                   enctype="multipart/form-data">
                            <div class="box-body" >
                                <div class="form-group">
                                   
                                    <div class="col-xs-8">                                    
                                        <div class="input-group">                                
                                            <form:input type="file" path="file"  id="BSbtndanger" value="${groupContactForm.name}"/>
                                            <script>
                                                $('#BSbtndanger').filestyle({
                                                    buttonName: 'btn-info',
                                                    buttonText: ' Chọn File import'
                                                });
                                            </script>
                                        </div>
                                    </div>                                   
                                    <div class="col-xs-4" >
                                    </div>
                                </div>
                            </div>
                            </br>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">Cập nhật dữ liệu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>                        
        </section>

<%@include file="../../include/footer.jsp"%>
