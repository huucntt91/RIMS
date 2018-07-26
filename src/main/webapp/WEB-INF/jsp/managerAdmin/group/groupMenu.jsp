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
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <section class="content-header">
            <h1>Quản lý Phân quyền<small></small></h1>
            <ol class="breadcrumb">

            </ol>
        </section>
        <section class="content">             
            <form:form method="post" action="updateGroupMenu/${groupId}" modelAttribute="groupMenuForm">
                <table id="example123" class="table table-bordered table-hover">
                    <thead>
                        <c:forEach items="${groupMenuForm.groupMenuItem}" var="item" varStatus="status" end="0">
                            <tr>
                                <th align="center">STT</th>
                                <th>Tên Menu</th>
                                    <c:forEach items="${item.groupBeanBO}" var="itemgroupBeanBO" varStatus="statusgroupBeanBO">
                                    <th align="center">${itemgroupBeanBO.name}</th>
                                    </c:forEach> 
                                <!--                            <th style="width: 50px"   align="center">Nhóm thành viên</th>
                                                            <th style="width: 50px"   align="center">Administrator</th>
                                                            <th style="width: 50px"   align="center">Admin</th>
                                                            <th style="width: 50px"   align="center">CSKH</th>-->
                            </tr>
                        </c:forEach>
                    </thead>
                    <tbody>
                        <c:forEach items="${groupMenuForm.groupMenuItem}" var="item" varStatus="status" begin="1">
                            <tr>
                                <!--<td align=center ><b><input name="groupMenuItem[{status.index}].menuName" value="{item.stt}"/></b> </td>-->
                                <td align=center >${status.index}</td>
                                <c:choose>
                                    <c:when test="${item.level==1}">
                                        <td align=left  > <b>${item.menuName}</b>  </td>
                                    </c:when>
                                    <c:when test="${item.level==2}">
                                        <td align=left style="padding-left: 20px;"> ${item.menuName}</td>
                                    </c:when>
                                    <c:when test="${item.level==3}">
                                        <td align=left style="padding-left: 40px;"> ${item.menuName}</td>
                                    </c:when>
                                    <c:when test="${item.level==4}">
                                        <td align=left style="margin-left: 60px;"> ${item.menuName}</td>
                                    </c:when>
                                       <c:otherwise>
                                        <td align=left style="margin-left: 80px;"> ${item.menuName}</td>
                                    </c:otherwise>
                                </c:choose>
                                
                        <input type="hidden" name="groupMenuItem[${status.index}].menuName" value="${item.menuName}"/>
                        <input type="hidden" name="groupMenuItem[${status.index}].menuId" value="${item.menuId}"/>
                        <c:forEach items="${item.groupBeanBO}" var="itemgroupBeanBO" varStatus="statusgroupBeanBO">
                            <td align=left> 
                                <input type="hidden" name="groupMenuItem[${status.index}].groupBeanBO[${statusgroupBeanBO.index}].id" value="${itemgroupBeanBO.id}"/>
                                <c:choose>
                                    <c:when test="${itemgroupBeanBO.check}">
                                        <input type="checkbox" name="groupMenuItem[${status.index}].groupBeanBO[${statusgroupBeanBO.index}].check"
                                               checked/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name="groupMenuItem[${status.index}].groupBeanBO[${statusgroupBeanBO.index}].check"
                                               />
                                    </c:otherwise>
                                </c:choose>


                            </td>
                        </c:forEach>
                        <!--                                <td   align="center" id="usermod2_0"   style="cursor:pointer" > <i      class="fa fa-fw fa-check-square-o"></i> </td>
                                                        <td   align="center" id="usermod2_1"   style="cursor:pointer" > <i      class="fa fa-fw fa-check-square-o"></i></td>
                                                        <td   align="center" id="usermod2_2" style="cursor:pointer" > <i      class="fa fa-fw fa-check-square-o"></i></td>
                                                        <td  align="center" id="usermod2_3"  style="cursor:pointer" > <i     class="fa fa-fw fa-square-o"></i>   </td>-->
                        </tr>  
                    </c:forEach>
                    <!--                    <tr>
                                            <td align=center ><b> 10</b> </td>
                                            <td align=left  > <b>Tổng Quan</b>  </td>
                                            <td   align="center" id="usermod2_0"   style="cursor:pointer" > <i      class="fa fa-fw fa-check-square-o"></i> </td>
                                            <td   align="center" id="usermod2_1"   style="cursor:pointer" > <i      class="fa fa-fw fa-check-square-o"></i></td>
                                            <td   align="center" id="usermod2_2" style="cursor:pointer" > <i      class="fa fa-fw fa-check-square-o"></i></td>
                                            <td  align="center" id="usermod2_3"  style="cursor:pointer" > <i     class="fa fa-fw fa-square-o"></i>   </td>
                                        </tr>                  
                                        <tr>
                                            <td align=center ><b> 15</b> </td>
                                            <td align=left  > <b>Quản lý Nội dung</b>  </td>
                                            <td  align="center" id="usermod201605061508035985922_0" style="cursor:pointer"> <i  class="fa fa-fw fa-square-o"></i></td>
                                            <td   align="center" id="usermod201605061508035985922_1" style="cursor:pointer" > <i      class="fa fa-fw fa-check-square-o"></i>   </td>
                                            <td   align="center" id="usermod201605061508035985922_2" style="cursor:pointer" > <i      class="fa fa-fw fa-check-square-o"></i>   </td>
                                            <td  align="center" id="usermod201605061508035985922_3"  style="cursor:pointer" > <i     class="fa fa-fw fa-square-o"></i>   </td>
                                        </tr> 
                                        <tr>
                                            <td align=center > 5 </td>
                                            <td align=left  > &nbsp; &nbsp; &nbsp; - Nhóm nội dung  </td> 
                                            <td  align="center" style="cursor:pointer"> <input type="checkbox" id="cbox2" value="second_checkbox"></td>
                                            <td  align="center" style="cursor:pointer"  > <i    class="fa fa-fw fa-check-square-o"></i> </td>
                                            <td  align="center" style="cursor:pointer"  > <i    class="fa fa-fw fa-check-square-o"></i>   </td>
                                            <td  align="center" style="cursor:pointer"  > <i     class="fa fa-fw fa-square-o"></i>   </td>
                                        </tr>-->
                    </tbody>
                </table>  
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary"><spring:message code="admin.common.update" /></button>
                </div>
            </form:form>
        </section>

        <!-- jQuery 2.0.2 -->
       <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
        
        
        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.6/css/jquery.dataTables.css"/>-->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"/>

        <script src="https://cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/process-form.js" type="text/javascript"></script>

        <script type="javascript">
            $("tr").click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            });​
        </script>
        <script language="javascript">
//     $("#dialog-form-user").dialog("open");
            $("#box1View").append($('<option>', {
                value: '1', // $(this).find("id").text(),
                text: 'name'//$(this).find("name").text()
            }));
        </script>
    </body>
</html>