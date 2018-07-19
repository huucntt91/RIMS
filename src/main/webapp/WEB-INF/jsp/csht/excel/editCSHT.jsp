<%@page import="java.util.List"%>
<%@page import="com.vnpt.media.rims.common.utils.Convert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../../include/header.jsp"%>
        <section class="content-header">
            <h1>Cập nhật CSHT</h1>
            <h2 style="display: none; color: blue;" id="mes_">Vui lòng đợi trong giây lát và check kết quả file download đi kèm khi xong</h2>
        </section>
        <section class="content">            


            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Upload file excel Cập nhật CSHT</h3>
                        </div>
                        <form:form method="post" action="${pageContext.request.contextPath}/building/CapNhapExcel" commandName="groupContactForm"
                                   enctype="multipart/form-data">

                            <div class="box-body" >
                                
                                <div class="form-group">

                                    <div class="col-xs-6">                                    
                                        <div class="input-group">                                
                                            <label class=" input-group-addon" style="min-width:150px;" for="exampleInputEmail1">Chọn file import</label>
                                            <form:input class="form-control" 
                                                        type="file"  path="file" 
                                                        value="${groupContactForm.name}"/>                                                                            
                                        </div>
                                    </div>

                                    <div class="col-xs-6">                                    
                                        <button type="button" onclick="btDownloadTemplate();" class="btn btn-facebook">Download biểu mẫu</button>
                                    </div>
                                </div>
                            </div>
                            </br>
                            <div class="box-footer">
                                <button type="submit" id="upload" class="btn btn-primary">Cập nhật dữ liệu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
         
        </section>   
        
        <%@include file="../../include/footer.jsp"%>
        <script>
            function btDownloadTemplate() {
                window.location.href = "${pageContext.request.contextPath}/resources/excel/Template_Update_CSHT.xlsx";
            }

            $(document).ready(function () {
                $('#upload').bind("click", function ()
                {
                    var imgVal = $('#file').val();
                    if (imgVal == '')
                    {
                        alert("Bạn phải chọn file trước khi cập nhật dữ liệu");
                        return false;
                    }
                    $('#mes_').show();
                    return true;

                });

            });


        </script>
   