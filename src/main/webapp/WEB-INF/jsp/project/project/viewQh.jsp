<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : Cyano
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- multiselect -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<%@include file="../../include/header.jsp"%>
<section class="content-header">



</section>
<section class="content">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Thông tin quy hoạch</h3>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/project/addProject" commandName="projectBO">
            <div class="panel-body">    

                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Mã quy hoạch</label>

                            <input type="text" class="form-control" placeholder="Mã quy hoạch" name="projectCode" id="projectCode" value="${qh.projectCode}" required="true">
                            <input type="hidden" name="qhId" id="qhId" value="${qh.qhId}" >
                        </div>
                    </div>                                                
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group">
                            <label class=" input-group-addon" >Tên quy hoạch</label>
                            <input type="text" class="form-control" placeholder="Tên quy hoạch" name="projectName" id="projectName" value="${qh.projectName}" required="true">
                        </div>
                    </div>                                                                                                
                </div>
                <div class="col-md-6">
                    <div class="form-group" > 
                        <div class="input-group">
                            <label class=" input-group-addon" >Dự án cha</label>
                            <select name="parentId" id="parentId" class="form-control" >
                                <option value="">-Chọn-</option>
                                <c:forEach var="item" items="${qhs}">
                                    <option value="${item.qhId}" <c:choose> 
                                                <c:when test="${item.qhId == qh.parentId}">
                                                    selected    
                                                </c:when>    
                                            </c:choose> 
                                            >${item.projectName}</option>
                                </c:forEach>
                            </select>  
                        </div>    
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="form-group" > 
                        <div class="input-group">
                            <button type="submit"  class="btn btn-success" id="btnAdd"  > ${btnName}</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="box-footer">

            </div>
        </form>
    </div>


</section>

<%@include file="../../include/footer.jsp"%> 
<script>
    $(document).ready(function () {
//        $('#qhId').val($('#quyHoachId').val());
//        $('#tinhTpId').val($('#province').val());
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
    $('#projectParent').on('change', function (e) {
        var valueSelected = this.value;
        $('#parentId').val(valueSelected);
    });

    $('#quyHoachId').on('change', function (e) {
        var valueSelected = this.value;
        $('#qhId').val(valueSelected);
    });
    $('#province').on('change', function (e) {
        var valueSelected = this.value;
        $('#tinhTpId').val(valueSelected);
    });

</script>
