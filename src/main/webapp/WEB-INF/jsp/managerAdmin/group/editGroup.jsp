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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>          
        <![endif]-->
    </head>
    <body>
        <section class="content">            
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Quản lý nhóm quyền</h3>
                        </div>
                        <form:form method="post" action="update?${_csrf.parameterName}=${_csrf.token}" commandName="gbo">
                            <!--id, username, password, fullname, email, msisdn, status, is_admin,cp_id, create_date-->
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tên nhóm quyền</label>                                    
                                    <form:hidden path="id" title="${group.id}"></form:hidden>
                                    <form:input
                                        type="text" class="form-control" id="exampleInputEmail1" path="name"
                                        placeholder="Tên nhóm quyền" value="${group.name}"/>                                    
                                </div>                                    
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Phân cấp</label>
                                    <form:select path="phancapId"  class="form-control" >
                                        <form:options items="${phancapList}" itemValue="id"  itemLabel="name"/>
                                    </form:select>
                                </div> 
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Network Type</label>
                                    <form:select path="networkTypeId"  class="form-control" >
                                        <form:options items="${networkTypeList}" itemValue="id"  itemLabel="name"/>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Family Type</label>
                                    <form:select path="familyTypeId"  class="form-control" >
                                        <form:options items="${familyTypeList}" itemValue="id"  itemLabel="name"/>
                                    </form:select>
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
    </body>
</html>