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
                    <h3 class="box-title">Quản lý DSR</h3>
                </div>
                <form:form method="post" action="update" commandName="item" enctype="multipart/form-data">

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
                                <input type="hidden" name="buildingId" value="${item.buildingId}" id="buildingId"  />
                                <span class="input-group-btn">
                                    <button  class="btn btn-success" data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm building</button>
                                </span>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên người quản lý</label>                                    
                                <input value="${item.tenNgQLy}" type="text" class="form-control" name="tenNgQLy" placeholder="Tên người quản lý"  />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">SDT người quản lý</label>                                    
                                <input value="${item.SDTQLy}" type="text" class="form-control" name="SDTQLy" placeholder="SDT người quản lý"  />
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
                                <input  type="text" class="form-control date_form" value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                                value="${item.ngayHoatDong}" />" id="ngayHoatDong" name="ngayHoatDong"  />                    
                            </div>
                            <script>
                                $(document).ready(function () {
                                    $('#ngayHoatDong').datepicker({
                                        format: 'dd/mm/yyyy',
                                        todayHighlight: true,
                                        autoclose: true,
                                    });
                                });
                            </script>
                        </div> 
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">IP Ne</label>

                                <input type="text" class="form-control" name="ipNe" placeholder="IP Ne" value="${item.ipNe}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Ne Status</label>                             
                                <input  type="text" class="form-control" name="neStatus" placeholder="Ne Status" value="${item.neStatus}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">OPC</label>                             
                                <input  type="text" class="form-control" name="opc" placeholder="OPC" value="${item.opc}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Software Version</label>                             
                                <input  type="text" class="form-control" name="softVersion" placeholder="Software Version" value="${item.softVersion}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Hw Flatform</label>                             
                                <input  type="text" class="form-control" name="hwFlatForm" placeholder="Hw Flatform" value="${item.hwFlatForm}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Thuộc dự án</label>                             
                                <input  type="text" class="form-control" name="thuocDuAn" placeholder="Thuộc dự án" value="${item.thuocDuAn}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Địa điểm lắp đặt</label>                             
                                <input  type="text" class="form-control" name="address" placeholder="Địa điểm lắp đặt" value="${item.address}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">LICENSE</label>                             
                                <textarea  type="text" class="form-control" name="license" placeholder="LICENSE">${item.license}</textarea>
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Tên Card</label>                             
                                <input  type="text" class="form-control" name="tenCard" placeholder="Tên Card" value="${item.tenCard}" />
                            </div>   
                        </div>

                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">SERIAL PART NUMBER</label>                             
                                <input  type="text" class="form-control" name="seria" placeholder="SERIAL PART NUMBER" value="${item.seria}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Card Status</label>                             
                                <input  type="text" class="form-control" name="cardStatus" placeholder="Card Status" value="${item.cardStatus}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Số lượng Card</label>                             
                                <input  type="number" class="form-control" name="cardSL" placeholder="Số lượng Card" value="${item.cardSL}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Card version</label>                             
                                <input  type="text" class="form-control" name="cardVersion" placeholder="Card Status" value="${item.cardVersion}" />
                            </div>   
                        </div>
                        <div class="form-group">
                            <div class="input-group">  
                                <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Attack File HLD-LLD</label>                             
                                <form:input type="file" path="fileAttack" class="form-control" value="${item.file}"/>
                            </div>   
                            <c:if test='${item.file != null && item.file != "" }'>
                                <a href="${item.file}">Download file HLD-LLD</a>
                            </c:if>
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
                                $(document).ready(function () {
                                    $('#btn_building').click(function () {
                                        $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
                                    });

                                    $('#myBuilding iframe').on('load', function (e) {
                                        var iframe1 = $('#myBuilding iframe').contents();
                                        iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                            //alert($(this).text());
                                            $('#codeBuilding').val($(this).find('input.txtcode').val());
                                            $('#buildingId').val($(this).find('input.txtid').val());
                                            $('#lat').val($(this).find('input.txtlat').val());
                                            $('#lon').val($(this).find('input.txtlon').val());
                                        });
                                        iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                            //alert($(this).text());
                                            $('#codeBuilding').val($(this).find('input.txtcode').val());
                                            $('#buildingId').val($(this).find('input.txtid').val());
                                            $('#lat').val($(this).find('input.txtlat').val());
                                            $('#lon').val($(this).find('input.txtlon').val());
                                            $('#myBuilding').modal('hide');
                                        });
                                    });




                                    $('#btn_bts').click(function () {
                                        $.ajax({
                                            type: 'GET',
                                            url: "<%=request.getContextPath()%>/cell/getParentNeTypeId/" + $('#neTypeid').val(),
                                            dataType: "xml",
                                            beforeSend: function () {
                                            },
                                            complete: function () {
                                            },
                                            success: function (data) {
                                                var code = "";
                                                $(data).find("responseCode").each(function () {
                                                    code = code + $(this).text() + "";
                                                });
                                                $("#myModalBTSCode iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popup?neTypeId=' + code});
                                            }
                                            ,
                                            error: function (xhr, textStatus, error) {
                                                alert('error_ajax_xml?');
                                            }
                                        });


                                    });
                                    $('#myModalBTSCode iframe').on('load', function (e) {
                                        var iframe1 = $('#myModalBTSCode iframe').contents();
                                        iframe1.find('#example1 tbody').on('click', 'tr', function () {
                                            //alert($(this).text());
                                            $('#nodeChaId').val($(this).find('input.node_id').val());
                                            $('#btsCodeId').val($(this).find('input.code_id').val());
                                        });
                                        iframe1.find('#example1 tbody').on('dblclick', 'tr', function () {
                                            //alert($(this).text());
                                            $('#nodeChaId').val($(this).find('input.node_id').val());
                                            $('#btsCodeId').val($(this).find('input.code_id').val());
                                            $('#myModalBTSCode').modal('hide');
                                        });
                                    });
                                });
</script>