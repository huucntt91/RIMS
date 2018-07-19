
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content-header">
    <h1>
        Báo cáo kết nối access theo thiết bị
    </h1>
    
    <!--    <ol class="breadcrumb">
            <button class="btn btn-info btn-sm"  onclick="location.href = '%=request.getContextPath()%>/access/preAdd'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm access</span> 
            </button>
        </ol>-->
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">               
                <form:form method="GET" id="frm_search">
                    <div class="box-body"> 
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">                                    
                                    <input type="hidden" name="action" value="chungloaitb"/>
                                    <label class=" input-group-addon">Chọn Tỉnh TP</label>
                                    
                                    <select name="tinhTPId" id="tinhTPId" class="form-control" required> >
                                        <c:forEach var="item" items="${tinhTPList}">
                                            
                                            <option  
                                                <c:if test="${item.tinhTpId==tinhTPId}">selected</c:if>
                                                value="${item.tinhTpId}"
                                                >${item.tenTinhTp}</option>
                                            
                                        </c:forEach>                                 
                                    </select>  
                                </div>
                            </div>
                        </div>   
                        <div class="col-md-6">
                            <button type="submit" name="excel" value="excel" class="btn btn-primary">Excel</button>
                        </div>
                    </div>
                    <div class="clearfix" ></div>
                    <!-- /.box-body -->
                    
                </form:form>
            </div>
        </div>
    </div>


    
</section>

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>

