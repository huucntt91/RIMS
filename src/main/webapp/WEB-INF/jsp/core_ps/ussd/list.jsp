<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<section class="content-header">
    <h1>
        Quản lý USSD
    </h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/ussd/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm USSD</span> 
        </button>
    </ol>
</section>
<section class="content">   

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách USSD</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div >
                        <button type="button" id="export" class="btn btn-primary" 
                            onclick="location.href = '<%=request.getContextPath()%>/jasper/exportUssd'">Export excel</button>
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên</th>         
                                    <th>Mã node</th>
                                    <th>Ngày hoạt động</th>
                                    <th>IP NE</th>
                                    <th>Vender</th>
                                    <th>Địa điểm lắp đặt</th>
                                    <th>Hw Flatform</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="temp" items="${list}" varStatus="status">                                        
                                    <tr>
                                        <td>${status.index+1}</td>
                                        <td>${temp.name}</td>
                                        <td>${temp.code}</td>
                                        <td>${temp.ngayHoatDong}</td>
                                        <td>${temp.ipNe}</td>
                                        <td>${temp.vender}</td>
                                        <td>${temp.address}</td>
                                        <td>${temp.hwFlatForm}</td>    
                                        <td>
                                            <a href="<%=request.getContextPath()%>/ussd/view/${temp.nodeId}"
                                               title="Sửa">
                                                <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>  
                                            <a href="<%=request.getContextPath()%>/ussd/delete/${temp.nodeId}"
                                               title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                            </a>
                                            &nbsp;
  
                                             <button type="button"  class="btn btn-link" style="border: 0px; padding:0px;"  data-toggle="modal" data-target="#myModal" onclick="nelink('${temp.code}',${temp.nodeId},${temp.neTypeId})"id="btn_bts"  >
                                                <img src="<%=request.getContextPath()%>/image/icon/link-open-flat.png">
                                            </button>
                                            <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/ps?type=USSD&code=${temp.nodeId}"
                                               title="Lịch sử USSD" >
                                                <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                            </a>    

                                            &nbsp;

                                        </td>
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>   
                    </div>
                </div>
                <!-- /.box-body -->
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
<%@include file="../../include/footer.jsp"%>

<script>
    $(function () {
        $("#example1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
    });
</script>

<script type="text/javascript">

    nelink = function (code, nodeId, neTypeId) {
        $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/nelink/popup?node_code=' + code + '&nodeId=' + nodeId + '&neTypeId=' + neTypeId});

    };
</script>
