<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>


<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>-->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>

<!--<script src="js/jquery.multi-select.js"></script>-->
<section class="content-header">

    <h1>Quản lý OPC DPC</h1>
    <ol class="breadcrumb">
        <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/nelink/preAdd/${nodeId}/${neTypeId}'" >
            <span><i class="fa fa-fw fa-plus"></i>Thêm OPC DPC ${nodeId}</span> 
        </button>
    </ol>
</section>                                       
<section class="content">                        
    <%--div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Thêm DPC cho Node</h3>
                </div>
                <form:form method="GET" id="frm_search">
                    <input type="hidden" class="nodeCode" value="${nodeCode}"/>

                    <div class="box-body">                                
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">DPC</label>
                                    <select multiple="multiple" name="node2Id" id="node2Id" class="form-control"> >
                                        <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                        <c:forEach var="nodeBO" items="${nodeList}">
                                            <option 
                                                <c:if test='${fn:contains(nodeId,nodeBO.id)}' >  selected="selected" </c:if>
                                                value="${nodeBO.id}"  
                                                >${nodeBO.code}</option>
                                        </c:forEach>

                                    </select>                                  
                                </div>

                            </div>
                        </div>

                        <div class="col-md-4"> 
                            <div class="form-group">

                                <select name="loaiTruyenDanId" id="loaiTruyenDanId" class="form-control"> >
                                    <option value="">--- Chọn loại truyền dẫn ---</option>
                                    <c:forEach var="tbBO" items="${loaitruyendanList}">
                                        <option  
                                            value="${tbBO.id}"  <c:choose>
                                                <c:when test="${tbBO.id == truyendanId}">
                                                    selected    
                                                </c:when>    
                                            </c:choose>

                                            >${tbBO.name}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                        </div>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-primary">Thêm</button>
                        </div>
                        <div class="clearfix" ></div>
                    </div>
                    /.box-body 

                </form:form>
            </div>
        </div>
    </div--%> 
    <div class="row">
        <div class="col-xs-12">
            <input type="hidden" class="nodeCode" value="${nodeCode}"/>

            <div id="detailDiv">

            </div>

        </div>

    </div>

</section>
<%@include file="../../include/footer.jsp"%>

<script>
    $(document).ready(function() {
        var nodeCode = $(this).find('.nodeCode').val();
        var neLink = '${pageContext.request.contextPath}/nelink/popup?node_code=' + nodeCode;
        $('#node2Id').multiselect(({
            maxHeight: 200,
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function(element, checked) {
            }
        }));
        $.get(neLink, function(data) {
            $('#detailDiv').html(data);
        });

    });
</script>