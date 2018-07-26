<%@page import="com.vnpt.media.rims.common.utils.Convert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <div class="content-header">
            <h1>Khai báo cell 2G/cell 3G/cell 4G</h1>
            <ol class="breadcrumb">
                <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'cellExcelImport/init/1')}">
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/cellExcelImport/init/1'" >

                        <span>  <img width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Thêm cell với Excel</span>
                    </button>
                </c:if>
                <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'excelDeleteNode/init/1')}">
                    <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/excelDeleteNode/init/1'" >
                        <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Xóa cell với Excel</span> 
                    </button>
                </c:if>
            </ol>
        </div>
        <section class="content">            

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">

                        <div class="box-body" id="mydiv" >
                            <form:form method="post" action="${pageContext.request.contextPath}/cell/addCell" commandName="cellNewForm">
                                <input type="hidden" value="${action}" name="action" />
                                <form:hidden value="${cellNewForm.cellInfo.nodeId}" path="cellInfo.nodeId" />
                                <c:if test='${fn:contains(classAtrrView,"AREA")}'>   
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn kiểu Cell</label>
                                            <form:select  path="loaiNE.id" id="neTypeid" class="form-control"  >
                                                <form:option value="5">--- Cell 2G ---</form:option>
                                                <form:option value="6">--- Cell 3G ---</form:option>
                                                <form:option value="7">--- Cell 4G ---</form:option>
                                            </form:select>

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" for="exampleInputEmail1">Nhập mã BTS/NodeB/ENodeB</label>
                                            <form:input readonly="true" class="form-control" path="btsCode" id="btsCodeId"  ></form:input>
                                            <form:hidden name="nodeChaId"  id="nodeChaId" path="cellInfo.nodeChaId" />                                                    
                                            <span class="input-group-btn">
                                                <button type="button"  class="btn btn-success" data-toggle="modal" data-target="#myModalBTSCode" id="btn_bts"  >Tìm BTS</button>
                                            </span>
                                        </div>
                                    </div>

                                </c:if>
                                <c:if test='${fn:contains(classAtrrView,"BIRTH_INFO")}'>   
                                    <div class="panel panel-primary">                                    
                                        <div class="panel-heading">                                        
                                            <h3 class="panel-title">
                                                Thông tin khai sinh</h3>
                                        </div>
                                        <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"BIRTH_INFO")}'> disableGroup="disable" </c:if> >                
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên cho quản lý</label>
                                                    <form:input  type="text" class="form-control" path="cellInfo.tenNgQLy" placeholder="Tên cho quản lý" 
                                                                 value="${cellNewForm.cellInfo.tenNgQLy}"/>
                                                </div>                                                
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">                                                                                    
                                                    <label class=" input-group-addon" style="min-width:150px;">Hoàn cảnh ra đời</label>
                                                    <form:input  type="text" class="form-control" placeholder="Hoàn cảnh ra đời " 
                                                                 path="cellInfo.hoanCanhRaDoi" />
                                                </div>                                                                                                
                                            </div>
                                            <div class="form-group" class="form-control">                                              
                                                <div class="input-group">                                                                                    
                                                    <label class=" input-group-addon" style="min-width:150px;">Ngày hoạt động</label>
                                                    <form:input  class="form-control date_form" id="ngayHoatDong" 
                                                                 path="cellInfo.ngayHoatDong" />                    
                                                </div>                                                                                                
                                                <script>
                                                    $(document).ready(function() {
                                                        $('#ngayHoatDong').datepicker({
                                                            format: 'mm/dd/yyyy',
                                                            todayHighlight: true,
                                                            autoclose: true
                                                        });
                                                    });
                                                </script>
                                            </div> 

                                        </div>
                                    </div>
                                </c:if>
                                <c:if test='${fn:contains(classAtrrView,"OMC_INFO")}'>   
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Thông tin trên OMC</h3>
                                        </div>
                                        <div class="panel-body" <c:if test='${!fn:contains(classAtrrEdit,"OMC_INFO")}'> disableGroup="disable" </c:if> >         
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Tên trên hệ thống</label>
                                                    <form:input  class="form-control" placeholder="Tên trên hệ thống" name="temTrenHeThong" 
                                                                 path="omcCellInfo.tenTrenHeThong"/>
                                                </div>                                                                                                
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">                                
                                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">LAC</label>
                                                    <form:input  class="form-control" placeholder="LAC" name="lac" type="number"
                                                                 path="omcCellInfo.lac"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">                                
                                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">CI</label>
                                                    <form:input class="form-control" placeholder="CI" name="ci" type="number"
                                                                path="omcCellInfo.ci"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">                                
                                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Frequency Band</label>

                                                    <form:select name="bang_tan_id" path="omcCellInfo.bangTanId" class="form-control"  >
                                                        <form:option value="">--- Băng tần ---</form:option>
                                                        <form:options  items="${bangTanList}"  itemValue="bang_tan_id"  itemLabel="ten_bang_tan"/>                                                
                                                    </form:select>  
                                                </div>                                                                                                
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test='${fn:contains(classAtrrView,"CONFIG_INFO")}'>   
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Thông tin cấu hình</h3>
                                        </div>
                                        <div class="panel-body"  <c:if test='${!fn:contains(classAtrrEdit,"CONFIG_INFO")}'> disableGroup="disable" </c:if> >         
                                                <div class="form-group">
                                                    <div class="input-group">                                
                                                        <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Chọn thiết bị</label>
                                                    <form:select name="thietBiId" path="thietBi.thietBiId" id="thietBiId" class="form-control"  >
                                                        <form:option value="">--- Chọn thiết bị ---</form:option>
                                                        <form:options  items="${thietBiList}"  itemValue="thietBiId"  itemLabel="tenThietBi"/>
                                                    </form:select>  
                                                </div>
                                            </div>
                                            <div class="form-group" class="form-control">
                                                <div class="input-group">                                
                                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                                    <form:select path="loaiTram.loaiTramId" class="form-control"  > 
                                                        <form:option value="">--- Chọn loại trạm ---</form:option>
                                                        <form:options  items="${tramList}"  itemValue="loaiTramId"  itemLabel="tenLoaiTram"/>
                                                    </form:select>     
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">                                
                                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">No of carrier</label>
                                                    <form:input  class="form-control" placeholder="No of carrier" 
                                                                 type="number"  path="cellInfo.noOfCarrier"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>           
        </section>


        <!--<div id="myModal1" class="modal fade" role="dialog">-->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Tìm Kiếm</span></h4>
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

        <div class="modal fade" id="myModalBTSCode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Tìm Kiếm</span></h4>
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
        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>

        <link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>
        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        <script type="text/javascript">
                                                    $(document).ready(function() {

                                                        $('#btn_building').click(function() {
                                                            $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                                        });
                                                        $('#myModal iframe').on('load', function(e) {
                                                            var iframe1 = $('#myModal iframe').contents();

                                                            iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                                                //alert($(this).text());
                                                                $('#codeBuilding').val($(this).find('input.txtcode').val());
                                                                $('#buildingId').val($(this).find('input.txtid').val());

                                                            });
                                                            iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                                                //alert($(this).text());
                                                                $('#codeBuilding').val($(this).find('input.txtcode').val());
                                                                $('#buildingId').val($(this).find('input.txtid').val());
                                                                $('#myModal').modal('hide');
                                                            });
                                                        });
                                                        //$('#ifram-value').val($('#myModal iframe').find('tr.selected').text());
                                                        //BTS CODE
                                                        $('#btn_bts').click(function() {
                                                            $.ajax({
                                                                type: 'GET',
                                                                url: "<%=request.getContextPath()%>/cell/getParentNeTypeId/" + $('#neTypeid').val(),
                                                                dataType: "xml",
                                                                beforeSend: function() {
                                                                },
                                                                complete: function() {
                                                                },
                                                                success: function(data) {
                                                                    var code = "";
                                                                    $(data).find("responseCode").each(function() {
                                                                        code = code + $(this).text() + "";
                                                                    });
                                                                    $("#myModalBTSCode iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popup?neTypeId=' + code});
                                                                }
                                                                ,
                                                                error: function(xhr, textStatus, error) {
                                                                    alert('error_ajax_xml?');
                                                                }
                                                            });


                                                        });
                                                        $('#myModalBTSCode iframe').on('load', function(e) {
                                                            var iframe1 = $('#myModalBTSCode iframe').contents();
                                                            iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                                                //alert($(this).text());
                                                                $('#nodeChaId').val($(this).find('input.node_id').val());
                                                                $('#btsCodeId').val($(this).find('input.code_id').val());
                                                            });
                                                            iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                                                //alert($(this).text());
                                                                $('#nodeChaId').val($(this).find('input.node_id').val());
                                                                $('#btsCodeId').val($(this).find('input.code_id').val());
                                                                $('#myModalBTSCode').modal('hide');
                                                            });
                                                        });
                                                        $('.panel-body').each(function() {
                                                            if ($(this).attr('disableGroup') !== undefined) {
                                                                $(this).find('input').attr('disabled', 'disabled');
                                                                $(this).find('select').attr('disabled', 'disabled');
                                                            }
                                                        });
                                                    });
                                                    $('#neTypeid').change(function() {
//                                                        alert('The option with value ' + $(this).val() + ' and text ' + $(this).text() + ' was selected.');
                                                        $('#btsCodeId').val('');
                                                        $('#nodeChaId').val('');
                                                        window.location = '${pageContext.request.contextPath}/cell/init?neTypeId=' + $('#neTypeid').val();
                                                    });
        </script>
    </body>
</html>