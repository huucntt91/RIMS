<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="../../include/header.jsp"%>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Quản lý Đơn Vị</h3>
                        </div>
                        <form:form method="post" action="update" commandName="dvbo">
                 
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên Đơn Vị</label>                                    
                                    <form:hidden path="donViId" title="${dvbo.donViId}"></form:hidden>
                                  
                                        <input required type="text" class="form-control" name="tenDonVi" placeholder="Tên Đơn Vị" value="${dvbo.tenDonVi}" />
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Địa chỉ</label>
                                    <input required type="text" class="form-control" name="diaChiDonVi" placeholder="Địa chỉ" value="${dvbo.diaChiDonVi}" />
                                                           
                                </div>   
                               <div class="form-group">
                                    <label for="exampleInputEmail1">Đơn vị cha</label>
                                    <select name="parentId" class="form-control" required >
                                        <option value="">Đơn vị cha</option>
                                          <c:forEach var="dvBO" items="${cpList}">
                                              <option <c:choose>
                                                        <c:when test="${dvBO.donViId == dvbo.parentId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose> value="${dvBO.donViId}">${dvBO.tenDonVi}</option>
                                          </c:forEach>
                                    </select>  
                                  
                                </div> 
                           
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Chọn tỉnh quản lý</label>
                                     <select name="tinhTpId" id="tinhTpId" class="form-control" required onchange="getListHuyen(0);"> >
                                        <option value="">--- Tỉnh/Thành Phố ---</option>
                                          <c:forEach var="tinhBO" items="${tinhList}">
                                              <option  
                                                  value="${tinhBO.tinhTpId}"  <c:choose>
                                                        <c:when test="${tinhBO.tinhTpId == dvbo.tinhTpId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>
                                                           
                                                  >${tinhBO.tenTinhTp}</option>
                                          </c:forEach>
                                        
                                    </select>  
                                </div> 
                                <div class="form-group">
                                    <label  for="exampleInputEmail1">Chọn huyện</label>
                                    <select name="quanHuyenId" id="quanHuyenId" class="form-control" required onchange="getListPhuongXa(0);"> 
                                        <option value="">--- Quận / Huyện ---</option>
          
                                    </select>  
                                   
                                </div> 
                                <div class="form-group" class="form-control">
                                    <label for="exampleInputEmail1">Chọn phường xã</label>
                                      <select name="phuongXaId" id="phuongXaId" class="form-control" required > 
                                          <option value="">--- Phường / Xã ---</option>
                                    </select>                              
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

                <%@include file="../../include/footer.jsp"%>
        
        <script>
 
            function getListHuyen(tinh)
            {
                var id = $("#tinhTpId").val();
                if(tinh != 0)
                    id = tinh;
                $.get("${pageContext.request.contextPath}/dv/getHuyen/" + id, function (data) {
                    var html = '<option value="" >--- Quận / Huyện ---</option>';
                    if (data.length > 0) {
                        data.forEach(function (entry) {
                            var htmlx = '<option value="'+entry.quanHuyenId+'">'+entry.tenQuanHuyen+'</option>';
                            html +=  htmlx;       
                            
                        });
                        
                    };
                    $('#quanHuyenId').html(html);
                    if(tinh != 0)
                       $('#quanHuyenId').val(${dvbo.quanHuyenId});
                });
            }
            
            function getListPhuongXa(huyen)
            {
                
                var id = $("#quanHuyenId").val();
                if(huyen != 0)
                    id = huyen;
                $.get("${pageContext.request.contextPath}/dv/getPhuong/" + id, function (data) {
                    var html = '<option value="">--- Phường / Xã ---</option>';
                    if (data.length > 0) {
                        data.forEach(function (entry) {
                            var htmlx = '<option value="'+entry.phuongXaId+'">'+entry.tenPhuongXa +'</option>';
                            html +=  htmlx;       
                            
                        });
                       
                    };
                    $('#phuongXaId').html(html);
                    if(huyen != 0)
                       $('#phuongXaId').val(${dvbo.phuongXaId});
                });
            }
            
            $( document ).ready(function() {
                 var tinhId = $("#tinhTpId").val();
                 if(tinhId != '')
                 {
                     getListHuyen(${dvbo.tinhTpId});
                     getListPhuongXa(${dvbo.quanHuyenId});
                 }
            });
            
         </script>
   