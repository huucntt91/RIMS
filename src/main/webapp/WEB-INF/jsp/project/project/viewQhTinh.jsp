<%-- 
    Document   : viewQhTinh
    Created on : Mar 10, 2017, 4:32:34 PM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- multiselect -->
<!--<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>-->

<%@include file="../../include/header.jsp"%>
<section class="content-header">



</section>
<section class="content">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Thông tin quy hoạch tỉnh</h3>
        </div>
        <div class="panel-body"> 
            <form method="post" action="${pageContext.request.contextPath}/project/addProjectInfor" >
                <input type="hidden" class="form-control" name="qhInforId"  value="${qhTinh.qhInforId}"  >
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Quy hoạch</label>

                            <select name="qhId" id="qhId" class="form-control" required="true" onchange=""> 
                                <option value="">--- Chọn Quy hoạch---</option>
                                <c:forEach var="project" items="${qhs}">
                                    <option value="${project.qhId}" <c:choose>
                                                <c:when test="${project.qhId == qhTinh.qhId}">
                                                    selected    
                                                </c:when>    
                                            </c:choose> >${project.projectName}</option>
                                </c:forEach>
                            </select> 
                        </div> 
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">  
                        <div class="input-group">
                            <label class=" input-group-addon" >Tỉnh/Thành phố</label>
                            <select name="tinhTpId"  id="tinhTpId" class="form-control" required="true" > 
                                <option value="">--- Tỉnh/Thành Phố---</option>
                                <c:forEach var="province" items="${tinhThanhPhoLst}">
                                    <option value="${province.tinhTpId}"
                                            <c:choose>
                                                <c:when test="${province.tinhTpId == qhTinh.tinhTpId}">
                                                    selected    
                                                </c:when>    
                                            </c:choose>
                                            >${province.tenTinhTp}</option>
                                </c:forEach>
                            </select>  

                        </div>
                    </div> 
                </div>
                <div class="col-md-6">
                    <div class="form-group">  
                        <div class="input-group">
                            <label class=" input-group-addon" >Chỉ 2G</label>
                            <input type="number" class="form-control" placeholder="Số lượng chỉ 2G" name="soLuongChi2G" value="${qhTinh.soLuongChi2G}">
                        </div>
                    </div>

                </div>
                <div class="col-md-6">
                    <div class="form-group">  
                        <div class="input-group">
                            <label class=" input-group-addon" >Số lượng 2G&3G</label>
                            <input type="number" class="form-control" placeholder="Số lượng 2G&3G" name="soLuong2G3G" value="${qhTinh.soLuong2G3G}" >
                        </div>
                    </div>
                </div>
                <!--                <div class="col-md-6">
                                    <div class="form-group">  
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tổng thiết bị 2G & 3G</label>
                                            <input type="number" class="form-control" placeholder="Tổng thiết bị 2G & 3G" name="tongThietBi2G3G" value="${qhTinh.tongThietBi2G3G}">
                                        </div>
                                    </div>
                                </div> -->
                <div class="col-md-6">
                    <div class="form-group">  
                        <div class="input-group">
                            <label class=" input-group-addon" >3G 2100Mhz Lắp trên CSHT có sẵn</label>
                            <input type="number" class="form-control" placeholder="Lắp trên CSHT có sẵn" name="chstActive" value="${qhTinh.chstActive}">
                        </div>
                    </div>
                </div>    

                <div class="col-md-6">
                    <div class="form-group">  
                        <div class="input-group">
                            <label class=" input-group-addon" >3G 2100Mhz Lắp trên CSHT mới</label>
                            <input type="number" class="form-control" placeholder="Lắp trên CSHT mới" name="cshtNew" value="${qhTinh.cshtNew}">
                        </div>
                    </div>
                </div>
                        <!--                <div class="col-md-6">
                                    <div class="form-group">  
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tổng thiết bị</label>
                                            <input type="number" class="form-control" placeholder="Tổng thiết bị" name="tongThieBi" value="${qhTinh.tongThieBi}">
                                        </div>
                                    </div>
                                </div>-->
                <div class="col-md-6">
                    <div class="form-group">  
                        <div class="input-group">
                            <label class=" input-group-addon" >Tổng xây mới CSHT (cả 2G&3G)</label>
                            <input type="number" class="form-control" placeholder="Tổng xây mới CSHT (cả 2G&3G)" name="tongCshtXaymoi" value="${qhTinh.tongCshtXaymoi}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">  
                        <div class="input-group">
                            <label class=" input-group-addon" >Tổng thiết bị 3G 900MHz</label>
                            <input type="number" class="form-control" placeholder="Tổng thiết bị 3G 900MHz" name="tongThietBi3G" value="${qhTinh.tongThietBi3G}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">  
                        <div class="input-group">
                            <label class=" input-group-addon" >LTE</label>
                            <input type="number" class="form-control" placeholder="LTE" name="lte" value="${qhTinh.lte}">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group" > 
                        <div class="input-group">
                            <button type="submit"  class="btn btn-success" id="addProjectInforBtn"  >${btnName}</button>
                        </div>
                    </div>
                </div>
            </form>


        </div>
    </div>

<!--    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">2G</h3>
        </div>
        <div class="panel-body"> 


        </div>
    </div>-->
</section>

<%@include file="../../include/footer.jsp"%> 
<script>
    $(document).ready(function () {



        $("#table1").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });
        $("#table2").DataTable({
            "language": {
                "url": "${pageContext.request.contextPath}/resources/js/plugins/datatables/vi.json"
            }
        });

        var tab = '${tab}';
        if (tab === 1)
            $('#tabs a[href="#menu1"]').tab('show');
        else
            $('#tabs a[href="#home"]').tab('show');

    });

    //post ajax
//     $("#btnAdd").click(function () {
//
//            $.post("${pageContext.request.contextPath}/project/update",
//                    {
//                        projectCode: $('#projectCode').val(),
//                        projectName: $('#projectName').val()
//                    },
//                    function (data, status) {
//                        alert("Data: " + data + "\nStatus: " + status);
//                    });
//        });

    //xu ly su kien khi select option
//    $('#projectParent').on('change', function (e) {
//        var valueSelected = this.value;
//        $('#parentId').val(valueSelected);
//    });
//
//    $('#quyHoachId').on('change', function (e) {
//        var valueSelected = this.value;
//        $('#qhId').val(valueSelected);
//    });
//    $('#province').on('change', function (e) {
//        var valueSelected = this.value;
//        $('#tinhTpId').val(valueSelected);
//    });

</script>

