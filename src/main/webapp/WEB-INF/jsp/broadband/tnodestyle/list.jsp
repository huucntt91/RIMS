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
<section class="content-header">
    <h1>Quản lý Style<small></small></h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/tnodestyle/preAdd'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm style</span> 
        </button>
    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">


            <form method="post" id="addForm" name="addForm"  action="" commandName="tnodeStyle">
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title">Thông tin style</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="box-group" id="accordion">
                            <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
                            <div class="panel box box-primary">                                
                                <div id="collapseOne" class="panel-collapse collapse in">
                                    <div class="box-body table-responsive">
                                        <div id="tablePagingId">
                                            <table id="example1" class="table table-bordered table-hover">
                                                <thead> 
                                                    <tr>
                                                        <th>STT</th>
                                                        <th>Loại</th>
                                                        <th>Status</th>
                                                        <th>Image</th>
                                                        <th>Size</th>
                                                        <th>Xử lý</th>
                                                    </tr>
                                                </thead>                            
                                                <tbody> 
                                                    <c:forEach var="it" items="${tnodeStype}" varStatus="status">
                                                        <tr>
                                                            <td>${status.index+1}</td>
                                                            <td>${it.typeName}</td>
                                                            <td>${it.status}</td>
                                                            <td>
                                                                <img src="${pageContext.request.contextPath}${urlImage}/${it.url}" width="31" id="viewForm-imgSquare-show"/>
                                                            </td>
                                                            <td>
                                                                ${it.size}
                                                            </td>
                                                            <td>
                                                                <a href="<%=request.getContextPath()%>/tnodestyle/preUpdate/${it.id}"
                                                                   title="Sửa">
                                                                    <img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                                </a>  

                                                                <a href="<%=request.getContextPath()%>/tlink/delete/${it.id}"
                                                                   title="Xoá" onclick="return confirm('Bạn có muốn thực hiện xoá không ?')">
                                                                    <img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>                                    
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                        <!-- /.box-body -->
                    </div>    





                    <!-- /.box-body -->
                    <div class="clearfix" ></div>
                   
            </form>

        </div>
    </div>    
</div>
</section>

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>


<script type="text/javascript" lang="javascript">
                                                                       jQuery(document).ready(function($) {
                                                                           $("#addForm").submit(function(event) {
                                                                               // Disble the search button
                                                                               enableAddButton(false);
                                                                               // Prevent the form from submitting via the browser.
                                                                               event.preventDefault();
                                                                               addViaAjax();
                                                                           });
                                                                       });
                                                                       function addViaAjax() {
                                                                           var add = {}
                                                                           add["typeId"] = $("#addForm-type-id").val().trim();
                                                                           add["status"] = $("#addForm-status").val().trim();
                                                                           add["note"] = $("#addForm-note").val().trim();

                                                                           var curDate = new Date();
                                                                           var miniCurrentTime = curDate.getTime();
                                                                           if ($("#addForm-img-url").get(0).files.length != 0) {
                                                                               var fileName = document.getElementById("addForm-img-url").files[0].name;
                                                                               var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
                                                                               add["imgContract"] = "${cateName}" + "/" + "_img_url_" + miniCurrentTime + "." + ext;
                                                                           } else {
                                                                               add["imgContract"] = "";
                                                                           }

                                                                           $.ajax({
                                                                               type: "POST",
                                                                               contentType: "application/json",
                                                                               url: "<%=request.getContextPath()%>/tnotestyle/add",
                                                                               data: JSON.stringify(add),
                                                                               dataType: 'json',
                                                                               timeout: 100000,
                                                                               success: function(data) {
                                                                                   if (data.code == "0") {
                                                                                       fail(data);
                                                                                   } else {
                                                                                       success(data);
                                                                                       var result = data.result;
                                                                                       var param = new FormData();
                                                                                       var aryType = [];
                                                                                       var aryName = [];
                                                                                       jQuery.each(jQuery('#addForm-img-url')[0].files, function(i, file) {
                                                                                           param.append('file', file);
                                                                                           aryType.push("${cateName}");
                                                                                           var fileName = file.name;
                                                                                           var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
                                                                                           aryName.push("_img_url_" + miniCurrentTime + "." + ext);
                                                                                       });
                                                                                       param.append('name', aryName);
                                                                                       param.append('type', aryType);
                                                                                       $.ajax({
                                                                                           url: '<%=request.getContextPath()%>/uploadMultipleFile',
                                                                                           data: param,
                                                                                           cache: false,
                                                                                           contentType: false,
                                                                                           processData: false,
                                                                                           type: 'POST',
                                                                                           success: function(data) {
                                                                                               document.location = document.location.href;
                                                                                           },
                                                                                           error: function(e) {
                                                                                               console.log(e);
                                                                                           }
                                                                                       });
                                                                                   }
                                                                               },
                                                                               error: function(e) {
                                                                                   console.log("ERROR: ", e);
                                                                                   fail(e);
                                                                               },
                                                                               done: function(e) {
                                                                                   console.log("DONE");
                                                                                   enableSearchButton(true);
                                                                               }
                                                                           });
                                                                       }
                                                                       function enableAddButton(flag) {
                                                                           $("#btn-add").prop("disabled", flag);
                                                                       }

</script>

