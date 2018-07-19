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
                            <h3 class="box-title">Quản lý Tỉnh/TP</h3>
                        </div>
                        <form:form method="post" action="update" commandName="tinhBO">
                 
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên Tỉnh/Thành Phố</label>                                    
                                    <form:hidden path="tinhTpId" title="${tinhBO.tinhTpId}"></form:hidden>
                                  
                                        <input required type="text" class="form-control" name="tenTinhTp" placeholder="Tên Tỉnh/Thành Phố" value="${tinhBO.tenTinhTp}" />
                                </div>   
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Mã Tỉnh/TP</label>
                                    <input required type="text" class="form-control" name="maTinhTp" placeholder="Mã Tỉnh/TP" value="${tinhBO.maTinhTp}" />
                                                           
                                </div>   
                               <div class="form-group">
                                    <label for="exampleInputEmail1">Khu Vực</label>
                                    <select name="khuVuc" id="khuVuc" class="form-control" required >
                                        <option value="">---Chọn Khu Vực---</option>
                                         <option  value="1">--- Khu Vực 1---</option>
                                           <option value="2">--- Khu Vực 2---</option>
                                              <option value="3">--- Khu Vực 3---</option>
                                      
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
              $(function () {
                                        if(${tinhBO.khuVuc} > 0)
                                            $('#khuVuc').val(${tinhBO.khuVuc});
                            });
         </script>
   