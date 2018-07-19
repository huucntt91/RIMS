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
                            <h3 class="box-title">Quản lý Phường/Xã</h3>
                        </div>
                        <form:form method="post" action="update" commandName="phuongBO">
                 
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên Phường/Xã</label>                                    
                                    <form:hidden path="phuongXaId" title="${phuongBO.phuongXaId}"></form:hidden>
                                        
                                        <input required type="text" class="form-control" name="tenPhuongXa" placeholder="Tên Phường/Xã" value="${phuongBO.tenPhuongXa}" />
                                </div>   
                                 
                               <div class="form-group">
                                    <label for="exampleInputEmail1">Tỉnh/TP</label>
                                    <select name="tinhTpId" id="tinhTpId" class="form-control" required onchange="getListHuyen(0);">
                                        <option value="">---Chọn Tỉnh/TP---</option>
                                          <c:forEach var="tinhBO" items="${tinhList}">
                                              <option  
                                                  value="${tinhBO.tinhTpId}"  <c:choose>
                                                         <c:when test="${tinhBO.tinhTpId == tinhTpId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>
                                                           
                                                  >${tinhBO.tenTinhTp}</option>
                                          </c:forEach>
                                    </select>  
                                   
                                </div> 
                                <div class="form-group">
                                    <label  for="exampleInputEmail1">Chọn huyện</label>
                                    <select name="quanHuyenId" id="quanHuyenId" class="form-control" required> 
                                        <option value="">--- Quận / Huyện ---</option>
          
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
                       $('#quanHuyenId').val(${phuongBO.quanHuyenId});
                });
            }
            
            $( document ).ready(function() {
                 var tinhId = $("#tinhTpId").val();
                 if(tinhId != '')
                 {
                     getListHuyen(${tinhTpId});
                 }
            });
            </script>
   