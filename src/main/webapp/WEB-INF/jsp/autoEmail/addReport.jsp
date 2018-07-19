<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
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
                            <h3 class="box-title">Thêm report</h3>
                        </div>
                        <form:form method="post" action="update" commandName="lstE">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <!--id, username, password, fullname, email, msisdn, status, is_admin,cp_id, create_date-->
                            <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Thêm chủ đề email</h3>
                                    </div>
                                    <div class="panel-body">        
                                        <div class="form-group">
                                            <div class="input-group">  
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Tên chủ đề</label>
                                                <input type="hidden" name="idReportMail" value="${lstE.idReportMail}" id="idReportMail" required />
                                                <input required type="text"  class="form-control" id="reportMailName" name="reportMailName" value="${lstE.reportMailName}" placeholder="Tên chủ đề"   /> 

                                            </div>
                                        </div>        
                                        
              
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Nội dung email</label>    
                                                <textarea class="form-control" id="emailDetail" name="emailDetail" rows="8" placeholder="Nội dung email">${lstE.emailDetail}</textarea>
                                            </div>
                                        </div>      

                                        
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;" for="exampleInputEmail1">Danh sách email</label>    
                                                <textarea class="form-control" id="listMail" name="listMail" rows="8" placeholder="Các email được cách nhau bởi dấu phảy ','">${lstE.listMail}</textarea>
                                            </div>
                                        </div>     


                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" style="font-weight: bold; min-width:150px;"  for="exampleInputEmail1">Thời gian gửi email</label>    
                                                <input
                                                    type="text" class="form-control" id="exampleInputEmail1" name="timeSend"
                                                    placeholder="Thời gian gửi email theo định dạng CronTrigger (vd: 0 15 10 * * ?)" value="${lstE.timeSend}"/>   
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
        
        <!-- Modal -->        

        <!-- jQuery 2.0.2 -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        
        <script>
     
        </script>
    </body>
</html>

