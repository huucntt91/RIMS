<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

    </head>
    <body>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Thêm dự án</h3>
                        </div>
                        <form:form method="post" action="add" commandName="daTinh">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <!--id, username, password, fullname, email, msisdn, status, is_admin,cp_id, create_date-->
                            <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thông tin dự án</h3>
                                    </div>
                                    <div class="panel-body">     
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Quy hoạch</label>
                                                <input type="hidden" name="duAnId" value="${daTinh.duAnId}" id="duAnId" />
                                                <select name="quyHoachTinhId"  id="quyHoachTinhId" class="form-control">
                                                    <option value="">---Chọn quy hoạch ---</option>
                                                    <c:forEach var="qhTinh" items="${qhTinhLst}">
                                                        <option value="${qhTinh.qhInforId}" 
                                                                <c:choose> <c:when test="${qhTinh.qhInforId == daTinh.quyHoachTinhId}">
                                                                        selected    
                                                                    </c:when>    
                                                                </c:choose> 
                                                                >Quy hoạch: ${qhTinh.projectName} - Tỉnh: ${qhTinh.province}  </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Mã dự án</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="maDuAn"
                                                    placeholder="Mã dự án" value="${daTinh.maDuAn}"/>   
                                            </div>
                                        </div> 
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Tên dự án</label>    
                                                <form:input
                                                    type="text" class="form-control" id="exampleInputEmail1" path="tenDuAn"
                                                    placeholder="Tên dự án" value="${daTinh.tenDuAn}"/> 
                                            </div>
                                        </div> 
                                        
                                            
                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Tỉnh</label>  
                                                <select name="tinhId" id="tinhId" class="form-control"> 
                                                    <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                                    <c:forEach var="tinhBO" items="${tinhList}">
                                                        <option  
                                                            value="${tinhBO.tinhTpId}"  <c:choose>
                                                                <c:when test="${tinhBO.tinhTpId == daTinh.tinhId}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose>

                                                            >${tinhBO.tenTinhTp}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                                                                                                                                                                                    
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

        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        
        <script>
 
            $(document).ready(function () {
                //$('.navbar-btn').click();
                var tinhId = $("#tinhTpId").val();
                if (tinhId != '')
                {
                    getListHuyen(${tinhTpId});
                    getListPhuongXa(${quanHuyenId});
                }
                
                
            });
        </script>
    </body>
</html>

