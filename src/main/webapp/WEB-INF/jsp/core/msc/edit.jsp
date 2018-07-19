<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../include/header.jsp"%>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Quản lý MSC</h3>
                </div>
                <form:form method="post" action="update" commandName="item">

                    <div class="box-body">
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Mã node</label>
                                <input  type="text" class="form-control " required
                                        value="${item.code}"  id="code" name="code"/>                    
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon"  style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Nhập mã Node cha</label>
                                <form:input readonly="true" class="form-control" path="tenNodeCha" id="btsCodeId"  ></form:input>
                                <form:hidden name="nodeChaId"  id="nodeChaId" path="nodeChaId" />                                                    
                                <span class="input-group-btn">
                                    <button type="button"  class="btn btn-success" data-toggle="modal" data-target="#myModalBTSCode" id="btn_bts"  >Tìm Node cha</button>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">                                
                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Đơn vị</label>
                                <form:select name="donViId" path="donViId" class="form-control"  required="true">
                                    <form:option value="">--- Đơn vị ---</form:option>
                                    <form:options  items="${dvList}"  itemValue="donViId"  itemLabel="tenDonVi"/>                                                
                                </form:select>  
                            </div>                                                                                                
                        </div>

                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn Vendor</label>
                                <select name="thietBiId" id="thietBiId" class="form-control" required> >
                                    <option value="">--- Chọn Vendor ---</option>
                                    <c:forEach var="tbBO" items="${thietBiList}">
                                        <option  
                                            value="${tbBO.thietBiId}"  <c:choose>
                                                <c:when test="${tbBO.thietBiId == item.thietBiId}">
                                                    selected    
                                                </c:when>    
                                            </c:choose>

                                            >${tbBO.tenThietBi}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Nhập mã building</label>
                                <input required="true" type="text"  value="${item.codeBuilding}"  class="form-control" id="codeBuilding" placeholder="Mã building" disabled  /> 
                                <input type="hidden" name="buildingId" value="${item.buildingId}" id="buildingId" required />
                                <span class="input-group-btn">
                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm building</button>
                                </span>
                            </div>
                        </div>

                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Loại Trạm</label>
                                <select name="loaiTramId" id="neTypeId" class="form-control"  > 
                                    <option value="">--- Chọn loại trạm ---</option>
                                    <c:forEach var="item" items="${tramList}">
                                        <c:choose>
                                            <c:when test="${item.neTypeId == 18}">  
                                                <option  
                                                    <c:choose>
                                                        <c:when test="${item.loaiTramId == model.loaiTramId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>  
                                                    value="${item.loaiTramId}">${item.tenLoaiTram}</option>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên người quản lý</label>                                    
                                <input required value="${item.tenNgQLy}" type="text" class="form-control" name="tenNgQLy" placeholder="Tên người quản lý"  />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">SDT người quản lý</label>                                    
                                <input required value="${item.SDTQLy}" type="text" class="form-control" name="SDTQLy" placeholder="SDT người quản lý"  />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên</label>                               
                                <form:hidden path="nodeId" title="${item.nodeId}"></form:hidden>
                                <input required type="text" class="form-control" name="name" placeholder="Tên" value="${item.name}" />
                            </div>
                        </div>   
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày hoạt động</label>
                                <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="yyyy-mm-dd" 
                                                value="${item.ngayHoatDong}" />" id="ngayHoatDong" name="ngayHoatDong"  />                    
                            </div>
                            <script>
                                $(document).ready(function() {
                                    $('#ngayHoatDong').datepicker({
                                        format: 'yyyy-mm-dd',
                                        todayHighlight: true,
                                        autoclose: true,
                                    });
                                });
                            </script>
                        </div> 
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Hoàn cảnh ra đời</label>

                                <input required type="text" class="form-control" name="hoanCanhRaDoi" placeholder="Hoàn cảnh ra đời" value="${item.hoanCanhRaDoi}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Type</label>                             
                                <input required type="type" class="form-control" name="type" placeholder="TYPE" value="${item.type}" />
                            </div>   
                        </div>
                        <div class="form-group" class="form-control">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ngày đăng ký</label>
                                <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="yyyy-mm-dd" 
                                                value="${item.ngayDangKy}" />" id="ngayDangKy" name="ngayDangKy"  />                    
                            </div>
                            <script>
                                $(document).ready(function() {
                                    $('#ngayDangKy').datepicker({
                                        format: 'yyyy-mm-dd',
                                        todayHighlight: true,
                                        autoclose: true,
                                    });
                                });
                            </script>
                        </div>

                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>           
</section>
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
<div id="myBuilding" class="modal fade" role="dialog">
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
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
                                $(document).ready(function() {
                                    $('#btn_building').click(function() {
                                        $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                    });

                                    $('#myBuilding iframe').on('load', function(e) {
                                        var iframe1 = $('#myBuilding iframe').contents();
                                        iframe1.find('#example1 tbody').on('click', 'tr', function() {
                                            //alert($(this).text());
                                            $('#codeBuilding').val($(this).find('input.txtcode').val());
                                            $('#buildingId').val($(this).find('input.txtid').val());
                                            $('#lat').val($(this).find('input.txtlat').val());
                                            $('#lon').val($(this).find('input.txtlon').val());
                                        });
                                        iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                                            //alert($(this).text());
                                            $('#codeBuilding').val($(this).find('input.txtcode').val());
                                            $('#buildingId').val($(this).find('input.txtid').val());
                                            $('#lat').val($(this).find('input.txtlat').val());
                                            $('#lon').val($(this).find('input.txtlon').val());
                                            $('#myBuilding').modal('hide');
                                        });
                                    });




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
                                });
</script>