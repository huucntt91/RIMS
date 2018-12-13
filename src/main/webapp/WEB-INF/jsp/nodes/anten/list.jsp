
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
        <i class="fa fa-wifi"></i> Quản lý Node Anten
    </h1>
    <ol class="breadcrumb">
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/anteninfo/view/{id}')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/anteninfo/preAdd'" >
                <span><i class="fa fa-fw fa-plus"></i>Thêm Anten</span> 
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
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" style="min-width: 150px;">Khu vực</label>
                                    <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control"
                                            > 
                                        <!--<option value="">--- Chọn Tỉnh/Thành Phố ---</option>-->
                                        <c:forEach var="tinhBO" items="${khuvucList}">
                                            <option  <c:if test='${fn:contains(khuvucId,tinhBO.id)}' >  selected="selected" </c:if>
                                                                                                        value="${tinhBO.id}"  
                                                                                                        >${tinhBO.name}</option>
                                        </c:forEach>
                                    </select>                                  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon" style="min-width: 150px;">Tỉnh/TP</label>


                                    <select  multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen(0);"> >
                                        <!--<option value="">--- Chọn Tỉnh/Thành Phố ---</option>-->
                                        <c:forEach var="tinhBO" items="${tinhList}">
                                            <option  
                                                value="${tinhBO.tinhTpId}" 
                                                <c:if test='${fn:contains(tinhTpId,tinhBO.tinhTpId)}' >  selected="selected" </c:if>

                                                    >${tinhBO.tenTinhTp}</option>
                                        </c:forEach>

                                    </select>  

                                </div>
                            </div>

                        </div>
                        <div class="col-md-12">
                              <div class="form-group">
                            
                                 <input name="code" value="${code}"
                                   type="text" class="form-control" id="code"
                                   placeholder="Mã Node hoặc tên Anten">
                           
                           
                        </div>
                        </div>
                                   <div class="col-md-12">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i>  <spring:message code="admin.common.search" /></button>
                            </div>
                            
                        </div>
                        <div class="clearfix" ></div>
                      
                    </div>
                  
                </form:form>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Anten</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <button type="button" id="export" class="btn btn-primary" 
                            onclick="location.href = '<%=request.getContextPath()%>/jasper/exportAnten?code='
                                            + $('#code').val() + '&khuvucId=' + $('#khuvucId').val() 
                                            + '&tinhTpId=' + $('#tinhTpId').val()">Export excel</button>
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã Node</th>
                                <th>Tên Anten</th> 
                                <th>Mã Building</th>
                                <th>Tỉnh/TP</th> 
                                <th>Loại Anten</th>
                                <th>Hãng SX</th>
                                <th>Trọng lượng</th>
                                <th>Độ cao</th>  
                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>                                       
                            <c:forEach var="item" items="${list}" varStatus="status">                                        
                                <tr>
                                    <td>${startRow + (status.index)}</td>

                                    <td>${item.code}</td>
                                    <td>${item.name}</td>
                                    <td>${item.codeBuilding}</td>
                                    <td>${item.tenTinhTp}</td>
                                    <td>${item.loaiAnten}</td>
                                    <td>${item.hangSX}</td>
                                    <td>${item.trongLuong}</td>
                                    <td>${item.doCao}</td>
                                    <td>
                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/anteninfo/view/{id}')}">
                                            <a href="<%=request.getContextPath()%>/anteninfo/view/${item.id}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                        </c:if>
                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/anteninfo/delete/{id}')}">
                                            <a href="<%=request.getContextPath()%>/anteninfo/delete/${item.id}"
                                               title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                            </a>
                                        </c:if>
                                        &nbsp;
                                       
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>

            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <iframe width="100%" height="450" frameborder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>


<script>
    $(document).ready(function () {
        $('#tinhTpId').multiselect(({
            maxHeight: 200,
            buttonWidth: '100%',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
//                var brands = $('#kpiLst option:selected');
//                var textTmp = '';
//                $(brands).each(function (index, brand) {
//                    textTmp += $(this).val() + ",";
//                });
//                $('#txtEvents').val(textTmp);
            }
        }));
        $('#khuvucId').multiselect(({
            maxHeight: 200,
            buttonWidth: '100%',
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
//                var brands = $('#kpiLst option:selected');
//                var textTmp = '';
//                $(brands).each(function (index, brand) {
//                    textTmp += $(this).val() + ",";
//                });
//                $('#txtEvents').val(textTmp);
            }
        }));
    });
   
</script>

