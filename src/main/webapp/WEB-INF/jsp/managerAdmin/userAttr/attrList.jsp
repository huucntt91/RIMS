<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
               <title>RIMS</title>
               <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
               <!-- bootstrap 3.0.2 -->
               <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
               <!-- font Awesome -->
               <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
               <!-- Ionicons -->
               <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
               <!-- Theme style -->
               <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />



               <link href="${pageContext.request.contextPath}/resources/js/js2/style.min.css" rel="stylesheet" type="text/css" />

               <!--        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
                       <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
                       <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
                       <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
                       <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
                       <script src="${pageContext.request.contextPath}/resources/js/js2/jstree.min.js"></script>-->
               <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
               <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.0.9/themes/default/style.min.css" />
               <script src="//cdnjs.cloudflare.com/ajax/libs/jstree/3.0.9/jstree.min.js"></script>
               <link href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
         <script type="text/javascript">
                    function remove(arr, item) {
                        for(var i = arr.length; i--;) {
                            if(arr[i] === item) {
                                arr.splice(i, 1);
                            }
                        }
                    }
                    $(function() {
                        //document.getElementById("plugins1").innerHTML = $('#data').val();
                        $("#checkview").jstree({
                                //"plugins": ["checkbox"],
                                core : {
                                    expand_selected_onload : false
                                }
                            })
                            .on("ready.jstree", function(e, data){
                                var urlParams = new URLSearchParams(window.location.search);
                                $('#checkview').jstree(true).select_node("CLASS_" + urlParams.get("cid"), {OK: 1});

                                $(document).on('click', '.jstree-anchor', function(e) {
                                     var id = $(this).attr('id');
                                     if(id.indexOf('CLASS') >= 0){
                                            window.location.href = "${pageContext.request.contextPath}/permission/attrList?cid=" + id.split('_')[1];
                                     }
                                });
                            });
                    });
                </script>

        <section class="content-header">
            <h1>Quản lý thuộc tính<small></small>
        </h1>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <!-- /.box-header -->

                        <div class="box-body">
                                 <div class="form-group">
                                     <div class="col-xs-4" style="max-width: 250px">
                                         <div id="checkview" class="demo plugin-demo">
                                             ${data}
                                         </div>
                                     </div>
                                    <div class="col-xs-8">
                                        <form method="POST" name="attrForm" action="${pageContext.request.contextPath}/permission/updateAttr">
                                          <div class="form-group">
                                            <label>Tên</label>
                                              <input type="text" class="form-control" id="name" name="attrName"  required>
                                              <input type="hidden" class="form-control" id="id" name="id">
                                              <input type="hidden" class="form-control" id="attrClassId" name="attrClassId" value="${cid}">
                                          </div>
                                          <div class="form-group">
                                            <label>Code</label>
                                            <input type="text" class="form-control" id="code" name="attrCode" required>
                                          </div>
                                         <div class="form-group">
                                            <label>Alias code</label>
                                             <input type="text" class="form-control" id="aliasCode" name="aliasExcelAttr">
                                          </div>
                                          <div class="form-group">
                                              <button id="submit" type="submit" class="btn btn-primary">Thêm mới</button>
                                          </div>
                                        </form>
                                        <table id="example2" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Tên</th>
                                                    <th>Code</th>
                                                    <th>ALIAS Code</th>
                                                    <th>Xử lý</th>
                                                </tr>
                                            </thead>
                                            <div align="right" style="margin-right: 50px;">${paging}</div>
                                            <tbody>
                                                <c:forEach var="attr" items="${listAttrs}" varStatus="status">
                                                    <tr>
                                                        <td>${status.index+1}</td>
                                                        <td>${attr.attrName}</td>
                                                        <td>${attr.attrCode}</td>
                                                        <td>${attr.aliasExcelAttr}</td>
                                                        <td>
                                                            <a class="edit" href="#" title="Sửa"
                                                                data-id="${attr.id}"
                                                                data-name="${attr.attrName}"
                                                                data-code="${attr.attrCode}"
                                                                data-aliasCode="${attr.aliasExcelAttr}"
                                                                data-attrClassId="${attr.attrClassId}" ><img src="/RIMS/image/icon/edit_icon.png"></a>
                                                            <a class="delete" href="#" title="Xoá" data-id="${attr.id}"><img src="/RIMS/image/icon/delete.png"></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                     </div>
                                 </div>
                         </div>
                        <!-- /.box-body -->
                        <div class="clearfix"></div>
                          <br>
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>

         <!-- jQuery 2.0.2 -->

                <!-- Bootstrap -->
                <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
                <!-- AdminLTE App -->
                <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
                <!--call ajax-->
                <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
                <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.6/css/jquery.dataTables.css"/>-->

                <script src="https://cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
                <script src="${pageContext.request.contextPath}/resources/js/process-form.js" type="text/javascript"></script>
                <script type="text/javascript">
                         $('.edit').click(function() {
                            var data = $(this).data();
                            $('#id').val(data.id);
                            $('#name').val(data.name);
                            $('#code').val(data.code);
                            $('#aliasCode').val(data.aliascode);
                            $('#submit').html('Cập nhật');
                         });
                         $('.delete').click(function() {
                            if(!confirm('Bạn có muốn thực hiện xoá không ?'))
                                return;
                            var id =  $(this).data().id;
                            $.post("${pageContext.request.contextPath}/permission/deleteAttr", {id: id}, function(response){
                                window.location.reload();
                            })
                        });
                </script>
    </body>
</html>
