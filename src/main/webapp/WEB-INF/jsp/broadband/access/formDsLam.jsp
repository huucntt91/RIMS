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

<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <!--<h3 class="box-title">Quản lý Mane</h3>-->
                </div>
                <form method="post" action="${pageContext.request.contextPath}/access/updateDsLam" commandName="access">
                    <div class="box-body" id="mydiv" >                        
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin DsLam</h3>
                            </div>
                            <div class="panel-body">  
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Mã Node</label>
                                            <input type="text" class="form-control" placeholder="Nhập mã node" maxlength="100"
                                                   name="code" id="TNODE_CODE" value="${access.code}" required="true">
                                        </div>
                                    </div>                                                
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tên Node</label>
                                            <input type="text" class="form-control" placeholder="Nhập tên node" maxlength="100"
                                                   name="name" id="TNODE_NAME" value="${access.name}" required="true">
                                        </div>
                                    </div>                                                
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">  
                                            <label class=" input-group-addon" style="min-width:150px;" >Nhập mã building</label>
                                            <input required="true" type="text"  class="form-control" id="ma_building" name="buildingCode" 
                                                   disabled="true" placeholder="Mã building" value="${access.buildingCode}"  /> 
                                            <input type="hidden" name="buildingId" value="${access.buildingId}" id="BUILDING_ID" required="true"/>
                                            <input type="hidden" name="id" value="${access.id}" id="TNODE_ID" required="true"/>
                                            <span class="input-group-btn">
                                                <button  class="btn btn-success"  data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm building</button>
                                            </span>
                                        </div>
                                        <span style="color: red; display: none" id="lbErrorBuilding">Phải nhập mã building</span>
                                    </div>    
                                </div>        
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" for="exampleInputEmail1">Node cha</label>
                                            <div id="divWrapper">
                                            </div>
                                            <span class="input-group-btn">
                                                <button type="button"  class="btn btn-success btn_tnode2" data-toggle="modal" data-target="#myModalTnode2" id="btn_bts"  >Tìm node cha</button>
                                                <input type="hidden" name="chaId" value="${access.chaId}" id="TLIST_NODE_CHA_ID"/>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">                                    
                                            <label class=" input-group-addon">Dòng thiết bị</label>
                                            <select  name="dongTBiId" id="DONG_TBI_ID" class="form-control" required="true">
                                                <option value="">--- Chọn dòng thiết bị ---</option>
                                                <c:forEach var="it" items="${dongTbis}">
                                                    <option value="${it.dongTbiId}" <c:choose> 
                                                                <c:when test="${it.dongTbiId == access.dongTBiId}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose> 
                                                            >${it.tenDongTbi}</option>
                                                </c:forEach>
                                            </select>                                  
                                        </div>
                                    </div>
                                </div>

                                

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tổng slot</label>
                                            <input type="number" class="form-control" maxlength="3" placeholder="Nhập tổng slot" 
                                                   name="totalSlot" id="TOTAL_SLOT" min="1" value="${access.totalSlot}">
                                        </div>
                                    </div>                                                
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ip</label>
                                            <input type="text" class="form-control" maxlength="15" 
                                                   placeholder="Nhập Ip" name="ip" id="IP" value="${access.ip}" required="true">
                                        </div>
                                        <span id="lbErrorIp" style="color: red; display: none">IP đã tồn tại</span>
                                    </div>                                                
                                </div>

                                <!--                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <div class="input-group">                                    
                                                                            <label class=" input-group-addon">Loại thiết bị</label>
                                                                            <select  name="TNODE_TYPE_ID" id="TNODE_TYPE_ID" class="form-control"> >
                                                                                <option value="1" c:if test = '{mane.TNODE_TYPE_ID  == 1}'> selected="selected" /c:if> >AGG</option>
                                                                                <option value="2" c:if test = '{mane.TNODE_TYPE_ID  == 2}'> selected="selected" /c:if> >UPE</option>
                                                                                <option value="3" c:if test = '{mane.TNODE_TYPE_ID  == 3}'> selected="selected" /c:if> >NPE</option>                                                    
                                                                                </select>                                  
                                                                            </div>
                                                                        </div>
                                                                    </div>-->
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >serial number</label>
                                            <input type="text" class="form-control" placeholder="Nhập serialNo" name="serialNo" 
                                                   maxlength="500" id="NOTE" value="${access.serialNo}">
                                        </div>
                                    </div>                                                
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >VOD Lan</label>
                                            <input type="text" class="form-control" placeholder="Nhập vodLan" name="vodLan" 
                                                   maxlength="500" id="NOTE" value="${access.vodLan}">
                                        </div>
                                    </div>                                                
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Note</label>
                                            <input type="text" class="form-control" placeholder="Nhập note" name="note" 
                                                   maxlength="1000" id="NOTE" value="${access.note}">
                                        </div>
                                    </div>                                                
                                </div>
                            </div>
                        </div>
                    </div>    
                    <!-- /.box-body -->
                    <div class="clearfix" ></div>
                    <div class="box-footer" align="center">
                        <button onclick="return checkValid()" type="submit"  class="btn btn-success">${btnName}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>           
</section>

<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

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

<div class="modal fade" id="myModalTnode2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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


<script>
    function checkValid(){
        var flag = true;
        if($('#ma_building').val() == '')
        {
            $('#lbErrorBuilding').show();
            $("#lbErrorBuilding").parent().addClass("has-error");
            flag = false;
        }
        $(".form-group").each(function (i) {
            //alert($(this).hasClass("has-error"));
            if($(this).hasClass("has-error") == true){
                //alert($(this).hasClass("has-error"));
                flag = false;
            } 
        });
        return flag;
    }
    
    $(document).ready(function() {
        $("#TOTAL_SLOT").change(function(){
            $(this).val($(this).val().replace("-", ""));
        });
        $("#IP").blur(function() {
            $.get("${pageContext.request.contextPath}/mane/checkIP?ip=" + $(this).val(), function (data) {
                if(parseInt(data) > 0) {
                    $("#IP").parent().parent().addClass("has-error");
                    $("#lbErrorIp").show();
                }else{
                    $("#IP").parent().parent().removeClass("has-error");
                    $("#lbErrorIp").hide();
                }
                    
            });
        });
        loadNodeCha();
        $('.btn_tnode2').click(function() {
            $("#myModalTnode2 iframe").prop({'src': '${pageContext.request.contextPath}/tnode/popup'});
        });
        $('#btn_building').click(function() {
            $("#myBuilding iframe").prop({'src': '${pageContext.request.contextPath}/building/popup'});
        });
        $('#myBuilding iframe').on('load', function(e) {
            var iframe1 = $('#myBuilding iframe').contents();
            iframe1.find('#example1 tbody').on('click', 'tr', function() {
                //alert($(this).text());
                $('#ma_building').val($(this).find('input.txtcode').val());
                $('#BUILDING_ID').val($(this).find('input.txtid').val());
                $('#lbErrorBuilding').hide();
                $("#lbErrorBuilding").parent().removeClass("has-error");

            });
            iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
                //alert($(this).text());
                $('#ma_building').val($(this).find('input.txtcode').val());
                $('#BUILDING_ID').val($(this).find('input.txtid').val());
                $('#myBuilding').modal('hide');
                
                $('#lbErrorBuilding').hide();
                $("#lbErrorBuilding").parent().removeClass("has-error");
            });
        });
    });
    $('#myModalTnode2 iframe').on('load', function(e) {
        var iframe1 = $('#myModalTnode2 iframe').contents();
        iframe1.find('#example1 tbody').on('click', 'tr', function() {
            $('#nodeId2').val($(this).find('input.txtid').val());
        });
        iframe1.find('#example1 tbody').on('dblclick', 'tr', function() {
            //alert($(this).text());
            if (document.getElementById($(this).find('input.txtname').val()) === null) {
                var nodeId = $(this).find('input.txtid').val();
                var nodeChaIds = $('#TLIST_NODE_CHA_ID').val();
                if (nodeChaIds == null || nodeChaIds == '')
                    nodeChaIds = ',';
                var arrStr = nodeChaIds.split(',');
                var isExist = 0;
                if (arrStr != null) {
                    for (var i = 0; i < arrStr.length; i++) {
                        if (arrStr[i].trim() == nodeId) {
                            isExist = 1;
                            break;
                        }
                    }
                }
                if (isExist == 0) {
                    nodeChaIds += nodeId + ",";
                    $('#TLIST_NODE_CHA_ID').val(nodeChaIds);
                    var b = document.createElement('button');
                    b.setAttribute('class', 'btn btn-info');
                    b.setAttribute('id', nodeId);
                    b.innerHTML = $(this).find('input.txtname').val();
                    b.onclick = function(event) {
                        //wrapper.removeChild(document.getElementById(b.innerHTML));
                        var nodeId = b.getAttribute('id');
                        var nodeChaIds = $('#TLIST_NODE_CHA_ID').val();
                        nodeChaIds = nodeChaIds.replace(',' + nodeId + ',', ',');
                        $('#TLIST_NODE_CHA_ID').val(nodeChaIds);
                        wrapper.removeChild(b);
                        return false;
                    };
                    var wrapper = document.getElementById("divWrapper");
                    wrapper.appendChild(b);
                }
            }
            $('#myModalTnode2').modal('hide');
        });
    });

    function loadNodeCha() {
        var wrapper = document.getElementById("divWrapper");
    <c:forEach var="it" items="${list}">
        var nodeId = '${it.TNODE_ID}';
        var b = document.createElement('button');
        b.setAttribute('class', 'btn btn-info');
        b.setAttribute('id', nodeId);
        b.innerHTML = '${it.TNODE_NAME}';
        b.onclick = function(event) {
            var nodeId = this.getAttribute('id');
            var nodeChaIds = $('#TLIST_NODE_CHA_ID').val();
            alert(nodeChaIds);
            nodeChaIds = nodeChaIds.replace(',' + nodeId + ',', ',');            
            $('#TLIST_NODE_CHA_ID').val(nodeChaIds);            
            wrapper.removeChild(this);            
            return false;
        };
        wrapper.appendChild(b);

    </c:forEach>


    }
    ;
</script>

