<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Cập nhật BTS/ NodeB/ ENodeB</h3>
                </div>

                <form:form method="post" action="update" >
                    <div class="box-body" id="mydiv" >
                        <div class="form-group">
                            <div class="input-group">
                                <label class=" input-group-addon" for="exampleInputEmail1">Mã đối tượng</label>
                                <input type="text" disabled class="form-control" placeholder="Mã đối tượng"  
                                       />  
                            </div>
                        </div>
                    </div>
                    <c:if test='${fn:contains(classAtrr,"AREA")}'>   
                        <div class="box-body" id="mydiv" >                           
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Thông tin khu vực</h3>
                                </div>
                                <div class="panel-body">          
                                    <div class="form-group">
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Địa chỉ</label>
                                            <input  class="form-control" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <%--<c:if test='${fn:contains(classAtrr,"POS_INFO")}'>--%>   
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Thông tin tọa độ</h3>
                        </div>
                        <div class="panel-body">        
                            <div class="form-group">
                                <div class="input-group">                                
                                    <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Latitude</label>
                                    <input  class="form-control" />
                                </div>                                                
                            </div>
                            <div class="form-group">
                                <div class="input-group">                                                                                    
                                    <label class=" input-group-addon" style="min-width:150px;">Longitude</label>
                                    <input  class="form-control" />
                                </div>                                                                                                
                            </div>                                
                        </div>
                    </div>
                    <%--</c:if>--%>
                    <%--<c:if test='${fn:contains(classAtrr,"CONNECT_INFO")}'>--%>   
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Thông tin liên kết</h3>
                        </div>
                        <div class="panel-body">        
                            <div class="form-group">
                                <div class="input-group">                                
                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Cosite2G-3G</label>

                                </div>                                                                                                
                            </div>
                            <div class="form-group">
                                <div class="input-group">                                
                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Mã Cosite2G-3G</label>

                                </div>
                            </div>                                
                        </div>
                    </div>
                    <%--</c:if>--%>
                    <%--<c:if test='${fn:contains(classAtrr,"MAINTENANCE_INFO")}'>--%>   
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Thông tin bảo dưỡng</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="input-group">                                
                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Thời gian bảo dưỡng</label>
                                </div>
                            </div>
                            <div class="form-group" class="form-control">
                                <div class="input-group">                                
                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Đơn vị thực hiện</label>                                        
                                </div>
                            </div>                                

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">                                
                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">No of carrier</label>

                        </div>
                    </div>
                    <%--</c:if>--%>

                    <%--<c:if test='${fn:contains(classAtrr,"INFRASTRUCTURE_INFO")}'>--%>   
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Thông tin hạ tầng</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="input-group">                                
                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Chung csht</label>

                                    <select name="phuongXaId" id="phuongXaId" class="form-control" > 
                                        <option value="0">--- Không chung CSHT ---</option>
                                        <option value="1">--- VNPT cho đơn vị khác thuê trạm và cột antena ---</option>
                                        <option value="2">--- VNPT thuê nhà trạm và cột antena của đơn vị khác ---</option>
                                        <option value="3">--- VNPT cho đơn vị khác thuê cột antena ---</option>
                                        <option value="4">--- VNPT thuê cột antena của đơn vị khác ---</option>
                                    </select>              

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">                                
                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Loại trạm csht</label>

                                    <select name="phuongXaId" id="phuongXaId" class="form-control" > 
                                        <option value="0">--- 0 ---</option>
                                        <option value="1">--- H>=1.25 ---</option>
                                        <option value="2">--- 1.25>H>=1 ---</option>
                                        <option value="3">--- H<1 ---</option>                                        
                                    </select>              

                                </div>
                            </div>
                            <div class="form-group" class="form-control">
                                <div class="input-group">                                
                                    <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Độ cao cột antena</label>                                        
                                </div>
                            </div>                                

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">                                
                            <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">No of carrier</label>

                        </div>
                    </div>
                    <%--</c:if>--%>
                </div>
            </div>

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
<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>
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
            $("#myModalBTSCode iframe").prop({'src': '${pageContext.request.contextPath}/nodes/popup?neTypeId=' + $('#cellTypeId').val()});
        });
        $('#myModalBTSCode iframe').on('load', function(e) {
            var iframe1 = $('#myModalBTSCode iframe').contents();
            iframe1.find('#example1 tbody').on('click', 'tr', function() {
                //alert($(this).text());
                $('#btsCode').val($(this).find('input.node_id').val());
                $('#btsCodeId').val($(this).find('input.code_id').val());
            });
            iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                //alert($(this).text());
                $('#btsCode').val($(this).find('input.node_id').val());
                $('#btsCodeId').val($(this).find('input.code_id').val());
                $('#myModalBTSCode').modal('hide');
            });
        });
    });
</script>
