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
                            <h3 class="box-title">Quản lý Quận/Huyện</h3>
                        </div>
                        <form:form method="post" action="update" commandName="quanBO">
                 
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên Quận/Huyện</label>                                    
                                    <form:hidden path="quanHuyenId" title="${quanBO.quanHuyenId}"></form:hidden>
                                        <input required type="text" class="form-control" name="tenQuanHuyen" placeholder="Tên Quận/Huyện" value="${quanBO.tenQuanHuyen}" />
                                </div>   
                                 
                               <div class="form-group">
                                    <label for="exampleInputEmail1">Tỉnh/TP</label>
                                    <select name="tinhTpId" id="tinhTpId" class="form-control" required >
                                        <option value="">---Chọn Tỉnh/TP---</option>
                                          <c:forEach var="tinhBO" items="${tinhList}">
                                              <option  
                                                  value="${tinhBO.tinhTpId}"  <c:choose>
                                                        <c:when test="${tinhBO.tinhTpId == quanBO.tinhTpId}">
                                                            selected    
                                                        </c:when>    
                                                    </c:choose>
                                                           
                                                  >${tinhBO.tenTinhTp}</option>
                                          </c:forEach>
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