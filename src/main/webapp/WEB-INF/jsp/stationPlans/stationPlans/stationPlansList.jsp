
<%@page import="com.vnpt.media.rims.common.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@include file="../../include/header.jsp"%>
     
<!--         AdminLTE App 
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
         Bootstrap 
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        call ajax
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>-->

        <!-- multiselect -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
        
        

        <section class="content-header">
            <h1><i class="fa fa-building"></i> Quản lý trạm dự án<small></small> <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_TRAM_KH_RIMS.mp4');" >Hướng dẫn</button> </h1>
            <ol class="breadcrumb">
                <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'ADD_TRAM_DA')}">
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/stationPlans/preAdd'" >
                        <span><i class="fa fa-fw fa-plus"></i>Thêm trạm DA</span> 
                    </button>
                </c:if>
                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/stationPlansExcelImport/init')}">
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/stationPlansExcelImport/init'" >

                        <span>  <img width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Thêm trạm DA với Excel</span> 
                    </button>
                </c:if>
                <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/stationPlansExcelImport/edit')}">
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/stationPlansExcelImport/edit'" >
                        <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Sửa trạm DA với Excel</span> 
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
                        <form method="GET" action="search">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon">Khu vực</label>
                                            <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control" onchange="getTinhTp();"
                                                    > 
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
                                        <label class=" input-group-addon" style="width:120px;">Tỉnh TP</label>
                                        <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" > 
                                        </select>
                                        <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                    </div>

                                </div>
                            </div>  
                                    
                            <div class="col-md-6">
                                <div class="form-group">
                                   
                                    <div class="input-group">
                                         <label class=" input-group-addon" style="width:120px;">Mã trạm dự án</label>
                                    <input name="maTramDuAn" value="${maTramDuAn}"
                                           type="text" class="form-control" id="maTramDuAn"
                                           placeholder="Mã trạm dự án"/>
                                    </div>
                                   
                                </div>                                                                                     
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group">
                                         <label class=" input-group-addon" style="width:120px;">Tên trạm dự án</label>
                                    <input name="tenTramDuAn" value="${tenTramDuAn}"
                                           type="text" class="form-control" id="tenTramDuAn"
                                           placeholder="Tên trạm dự án"/>
                                    </div>
                                   
                                </div>                                                                                     
                            </div>
                                         
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary"> <i class="fa fa-search"></i> <spring:message code="admin.common.search" /></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>



            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Danh sách trạm dự án</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <div id="tablePagingId">
                                <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/stationPlans/exportExcel?maTramDuAn=' + $('#maTramDuAn').val()
                                                + '&tenTramDuAn=' + $('#tenTramDuAn').val() + '&khuvucId=' + $('#khuvucId').val() + '&tinhTpId=' + $('#tinhTpId').val()" >
                                    <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Xuất Excel</span> 
                                </button>
<!--                                <button type="button" id="export" class="btn btn-primary" 
                                    onclick="location.href = '<%=request.getContextPath()%>/jasper/exportTramDuAn?maTramDuAn='
                                                    + $('#maTramDuAn').val() + '&tenTramDuAn=' + $('#tenTramDuAn').val()">Export excel</button>-->
                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã trạm DA</th>
                                            <th>Tên Trạm DA</th>
                                            <th>Mã quy hoạch</th>
                                            <th>Mã HĐ</th>
                                            <th>Tỉnh</th>                                            
                                            <th>Huyện</th>
                                            <th>Địa chỉ lắp đặt</th>
                                       
                                            <th>Mã trạm BTS</th>
                                            <th>Mã trạm NodeB</th>
                                            <th>Mã trạm QH</th>
                                            <th>Hiện trạng</th>
                                            <th>Longitude</th>
                                            <th>Latitude</th>
                                            <th>Trạng thái trạm</th>
                                            <th>Thông tin chung</th>
                                            <th>Cam kết TB</th>
                                            <th>Cam kết HT</th>
                                            <th>Triển khai NETx</th>
                                            <th>Triển khai QLHT</th>
                                            <th>Xem</th>
                                            <th>Xóa</th>
                                            <th>Xử lý</th>
                                        </tr>
                                    </thead>
                                    <div align="right" style="margin-right: 50px;">${paging}</div>
                                    <tbody>                                       
                                        <c:forEach var="planStationBO" items="${list_tramDA}" varStatus="status">                                        
                                            <tr>
                                                <td>${startRow + (status.index)}</td>
                                                <td>${planStationBO.maTramDuAn}</td>
                                                <td>${planStationBO.tenTramDuAn}</td>
                                                <td>${planStationBO.maQuyHoach}</td>
                                                <td>${planStationBO.maSoHopDong}</td>
                                                <td>${planStationBO.tenTinhTp}</td>
                                                <td>${planStationBO.tenQuanHuyen}</td>
                                                <td>${planStationBO.address}</td>
                                           
                                                <td>${planStationBO.maTramBTS}</td>
                                                <td>${planStationBO.maTramNodeB}</td>
                                                <td>${planStationBO.maTramQuyHoach}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${planStationBO.hienTrangTram=='1'}">
                                                            Đang hoạt động
                                                            <br />
                                                        </c:when>    
                                                        <c:otherwise>
                                                            Trạm quy hoạch
                                                            <br />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${planStationBO.longitude}</td>
                                                <td>${planStationBO.latitude}</td>
                                                <td>${planStationBO.tenTrangThaiTram}</td>
                                                <td>
                                                    <c:if test='${fn:contains(classAtrrEdit,"BIRD_INFO")}'>

                                                        <a href="<%=request.getContextPath()%>/stationPlans/preUpdate/${planStationBO.id}/1"
                                                           title="Cập nhật thông tin chung">
                                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                        </a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test='${fn:contains(classAtrrEdit,"COMMIT_DEVICE")}'>

                                                        <a href="<%=request.getContextPath()%>/stationPlans/preUpdate/${planStationBO.id}/2"
                                                           title="Cập nhật thông tin cam kết thiết bị">
                                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                        </a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test='${fn:contains(classAtrrEdit,"DEVICE_INFRA")}'>

                                                        <a href="<%=request.getContextPath()%>/stationPlans/preUpdate/${planStationBO.id}/3"
                                                           title="Cập nhật thông tin cam kết cơ sở hạ tầng">
                                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                        </a>
                                                    </c:if>
                                                </td>
                                                <td> 
                                                    <c:if test='${fn:contains(classAtrrEdit,"NETX_TRIEN_KHAI_DA")}'>
                                                        <a href="<%=request.getContextPath()%>/stationPlans/preUpdate/${planStationBO.id}/4"
                                                           title="NetX cập nhật thông tin triển khai dự án">
                                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                        </a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test='${fn:contains(classAtrrEdit,"QLHT_TRIEN_KHAI_DA")}'>

                                                        <a href="<%=request.getContextPath()%>/stationPlans/preUpdate/${planStationBO.id}/5"
                                                           title="Ban quản lý hạ tầng cập nhật thông tin triển khai dự án">
                                                            <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                        </a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <a href="<%=request.getContextPath()%>/stationPlans/preUpdate/${planStationBO.id}/0"
                                                       title="Thông tin chi tiết trạm dự án">   <img src="<%=request.getContextPath()%>/image/icon/view.png"/>
                                                    </a>
                                                </td>

                                                <td>
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'DELETE_TRAM_DA')}">
                                                        <a href="<%=request.getContextPath()%>/stationPlans/delete/${planStationBO.id}"
                                                           title="Xóa">
                                                            <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                        </a>
                                                    </c:if>
                                                </td>    
                                                <td>
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'APPROVE_TRAM_DA')}">
                                                        <c:choose>                                                        
                                                            <c:when test="${planStationBO.trangThaiTram==0 || planStationBO.trangThaiTram==1 || planStationBO.trangThaiTram==2 || planStationBO.trangThaiTram==3 || planStationBO.trangThaiTram==4 || planStationBO.trangThaiTram==5 || planStationBO.trangThaiTram==7}">                                                            
                                                                <a style="cursor: pointer"  onclick="approvePopup(${planStationBO.id}, 0);"
                                                                   title="Duyệt thông tin" >
                                                                    <img src="<%=request.getContextPath()%>/image/icon/icon_check.png">
                                                                </a>    
                                                            </c:when>

                                                            <c:when test="${planStationBO.trangThaiTram==6}">
                                                                <a style="cursor: pointer" onclick="approvePopup(${planStationBO.id}, 1);"
                                                                   title="Hủy duyệt thông tin" >
                                                                    <img width="18" src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                                </a>
                                                            </c:when>                                                                                                                
                                                        </c:choose> 
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>                                       							
                                    </tbody>                                    
                                </table>
                            </div>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <!-- ADD PAGE INFO -->
                            <%@include file="../../include/page_info.jsp"%>
                        </div>
                    </div>
                    <!-- /.box -->
<!--                    <div class="box-footer">
                     ADD PAGE INFO 
                        <%--<%@include file="../../include/page_info.jsp"%>--%>
                    </div>-->
                </div>
            </div>
        </section>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Nhập lý do</span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="box-body table-responsive">
                            <form:form  method="POST" action="approve" commandName="tramDABO">
                                <div class="form-group" style="padding: 0 5px">
                                    <form:textarea rows="4" type="text" class="form-control" 
                                                   path="ghiChu"
                                                   placeholder="Lý do"/>
                                    <form:hidden path="trangThaiTram" id="trangThaiTram"></form:hidden>
                                    <form:hidden path="id" id="id"></form:hidden>

                                    </div>

                                    <!-- /.box-body -->
                                    <div class="box-footer">
                                        <button id="btnUpdateComment" type="summit" class="btn btn-primary">Đồng ý</button>
                                    </div>
                            </form:form>
                        </div>

                        <%--</form:form>--%>
                    </div>

                </div>
            </div>                 
        </div>             
                    
        <%@include file="../../include/footer.jsp"%>      
        
        <script type="text/javascript">
                function approvePopup(tramDAId, status) {
                    $(".modal-body #trangThaiTram").val(status);
                    $(".modal-body #id").val(tramDAId);
                    $('#myModal').modal('show');
                }


                $(document).ready(function () {

                    $('#myModal iframe').on('load', function (e) {
                        var iframe1 = $('#myModal iframe').contents();
                        iframe1.find('#btnUpdateComment').on('click', function () {
                        $('#myModal').modal('hide');

                        });
                    });
                    $('#khuvucId').multiselect(({
                        maxHeight: 200,
                        buttonWidth:'100%',
                        enableFiltering: true,
                        includeSelectAllOption: true,
                        onChange: function (element, checked) {
                        }
                    }));

                    $('#tinhTpId').multiselect(({
                        maxHeight: 200,
                        buttonWidth:'100%',
                        enableFiltering: true,
                        includeSelectAllOption: true,
                        onChange: function (element, checked) {
                        }
                    }));
                    getTinhTp();
                });
                
                function hdsd(media) {
                    window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
                }  
                //lay ra danh sach tinhtp theo khu vuc
                function getTinhTp() {
                    var id = $("#khuvucId").val();
                    var tinhTpIds = $("#tinhTpIds").val();
                    $.get("${pageContext.request.contextPath}/mane/getTinhTp?khuVucId=" + id, function (data) {
                        var html = '';
                        if (data.length > 0) {
                            data.forEach(function (data) {
                                var htmlx = '<option value="' + data.tinhTpId + '" ';
                                if (tinhTpIds.indexOf(data.tinhTpId) > -1) {
                                    htmlx += ' selected="selected" ';
                                }
                                htmlx += '>' + data.tenTinhTp + '</option>';
                                html += htmlx;
                            });
                        }
                        $('#tinhTpId').html(html);
                        $('#tinhTpId').r('rebuild');
                    });
                }
        </script>
           

       
  