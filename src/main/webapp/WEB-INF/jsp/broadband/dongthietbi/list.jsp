
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
        Quản lý dòng thiết bị
    </h1>
    <ol class="breadcrumb">                
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/dongthietbi/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm mới</span> 
        </button>
    </ol>
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
                <div class="box-header">
                    <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                </div>

                <form:form method="GET" id="frm_search">
                    <div class="box-body"> 
                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">                                    
                                    <label class=" input-group-addon">Hãng cung cấp</label>
                                    <select name="tvendorId" id="tvendorId" class="form-control" required> >
                                        <c:forEach var="vendorBO" items="${vendor}">
                                            <option  
                                                value="${vendorBO.id}" 
                                                <c:choose>
                                                    <c:when test="${vendorBO.id == tvendorId}">
                                                        selected    
                                                    </c:when>    
                                                </c:choose>
                                                >${vendorBO.name}</option>
                                        </c:forEach>                                 
                                    </select>  
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group" style="padding: 0 15px">
                                <div class="input-group">       
                                    <label class=" input-group-addon">Tên dòng thiết bị</label>
                                    <input name="name" value="${name}"
                                           type="text" class="form-control" id="exampleInputEmail1"
                                           placeholder="Tên">
                                </div>
                            </div>
                        </div>                        
                    </div>
                    <div class="clearfix" ></div>
                    <!-- /.box-body -->
                    <div class="box-footer" align="center" >
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12">            
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách dòng thiết bị</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên dòng thiết bị</th>   
                                <th>Hãng cung cấp</th> 
                                <th>Ghi chú</th> 
                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="item" items="${list}" varStatus="status">                                        
                                <tr>
                                    <td>${startRow + (status.index)}</td>
                                    <td>${item.tenDongTbi}</td>
                                    <td>${item.tvendorName}</td>
                                    <td>${item.note}</td>
                                    <td>

                                        <a href="<%=request.getContextPath()%>/dongthietbi/preUpdate/${item.dongTbiId}"
                                           title="Sửa">
                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                        </a>  

                                        <a href="<%=request.getContextPath()%>/dongthietbi/delete/${item.dongTbiId}"
                                           title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                            <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                        </a>

                                    </td>
                                </tr>
                            </c:forEach>                                       							
                        </tbody>                                    
                    </table>   
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <!-- ADD PAGE INFO -->
                    <%@include file="../../include/page_info.jsp"%>
                </div>
            </div>           
            <!-- /.box -->
        </div>
    </div>
</section>

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>

