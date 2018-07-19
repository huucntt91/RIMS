<%-- 
    Document   : bcll
    Created on : Nov 15, 2016, 11:01:34 AM
    Author     : Cyano
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@include file="../../include/header.jsp"%>
<!-- multiselect -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Báo cáo lưu lượng tuần</h3>
                </div>
                <div class="box-body"> 
                    <div class="panel-body">
                        <form method="post" action="${pageContext.request.contextPath}/bcll/download">

<!--                            <div class="col-md-12">
                                <div class="form-group" >    
                                    <div class="input-group">
                                        <label class=" input-group-addon" >Vendor</label>
                                        <select name="vendor_id" multiple="multiple" id="vendor_id" class="form-control" >
                                            <c:forEach var="vendor" items="${vendors}">
                                                <option value="${vendor.thietBiId}" >${vendor.tenThietBi}</option>
                                            </c:forEach>
                                        </select>  
                                    </div> 
                                </div>
                            </div>-->

                            <div class="col-md-12">
                                <div class="form-group" >    
                                    <div class="input-group">
                                        <button type="submit" class="btn btn-primary">Download file</button>
                                    </div> 
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="box-footer">

                </div>

            </div>
        </div>
    </div>           
</section>

<%@include file="../../include/footer.jsp"%>

<script>
    $(document).ready(function () {
        $('#vendor_id').multiselect(({
            maxHeight: 450,
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
//                $('#txtKpi').val($('select#kpiLst').val());
//                var  x = $('select#kpiLst').val();
//                console.log(x);
            }

        }));
    });
</script>