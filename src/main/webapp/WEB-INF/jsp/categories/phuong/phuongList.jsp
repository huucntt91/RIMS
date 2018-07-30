<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>
        Quản lý Phường/Xã
    </h1>
    <ol class="breadcrumb">
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'ADD_PHUONGXA')}">
           <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/phuong/preAdd'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm Phường Xã</span> 
            </button>
        </c:if>
        

    </ol>
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
                        <div class="form-group">
                            <select name="tinhTpId" id="tinhTpId" class="form-control" required onchange="getListHuyen(0);"> >
                                <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                <c:forEach var="tinhBO" items="${tinhList}">
                                    <option  
                                        value="${tinhBO.tinhTpId}"  <c:choose>
                                            <c:when test="${tinhBO.tinhTpId == tinhTpId}">
                                                selected    
                                            </c:when>    
                                        </c:choose>

                                        >${tinhBO.tenTinhTp}</option>
                                </c:forEach>

                            </select>  



                        </div>
                        <div class="form-group">
                            <select name="quanHuyenId" id="quanHuyenId" class="form-control" required onchange="loadPhuongXa();"> 
                                <option value="">---Chọn Quận / Huyện ---</option>

                            </select>  
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.search" /></button>
                        
                        <button type="button" class="btn btn-primary"onclick="location.href = '<%=request.getContextPath()%>/phuong/reportPhuongXa?tinhTpId='+ $('#tinhTpId').val()+ '&quanHuyenId=' + $('#quanHuyenId').val()"> <spring:message code="admin.common.export.excel" /></button>
                                                                                                                                                                            
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Phường/Xã</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên Phường/Xã</th>                                            
                                <th>Tên Quận/Huyện</th>
                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="phuongBO" items="${phuongList}" varStatus="status">                                        
                                <tr>
                                    <td>${status.index+1}</td>
                                    <td>${phuongBO.tenPhuongXa}</td>
                                    <td>${phuongBO.tenQuanHuyen}</td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/phuong/view/${phuongBO.phuongXaId}"
                                           title="Sửa">
                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                        </a>  
                                        <a href="<%=request.getContextPath()%>/phuong/delete/${phuongBO.phuongXaId}"
                                           title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                            <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                        </a>
                                        &nbsp;

                                    </td>
                                </tr>
                            </c:forEach>                                       							
                        </tbody>                                    
                    </table>   
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>

<script>
    $(function () {
        $("#example1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
    });
    function getListHuyen(tinh)
    {
        var id = $("#tinhTpId").val();
        if (tinh != 0)
            id = tinh;
        $.get("${pageContext.request.contextPath}/dv/getHuyen/" + id, function (data) {
            var html = '<option value="" >---Chọn Quận / Huyện ---</option>';
            if (data.length > 0) {
                data.forEach(function (entry) {
                    var htmlx = '<option value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>';
                    html += htmlx;

                });

            }
            ;
            $('#quanHuyenId').html(html);
            if (tinh != 0)
                $('#quanHuyenId').val(${quanHuyenId});
        });
    }

    function loadPhuongXa()
    {
        $("#frm_search").submit();
    }

    $(document).ready(function () {
        var tinhId = $("#tinhTpId").val();
        if (tinhId != '')
        {
            getListHuyen(${tinhTpId});

        }
    });
</script>
