
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
<html>
    <head>
        <title>RIMS</title>
        <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- bootstrap 3.0.2 -->
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <!-- jQuery 2.0.2 -->
       <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
       <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.min.js"></script> 
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
<style>
    .truncate {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;

    }
    .tablePagingId table {
        table-layout: fixed;
        word-wrap: break-word;
        width: 1450px !important;
    }
    span.indenter{
        width: 20px;
        height: 20px;
    }
    span.indenter a{
        display: none !important;
    }


</style>
<style>
    .modal-dialog {
        width: 900px;
    }
</style>
</head>
<body class="skin-blue">
<section class="content-header">
    <h1>
        Thông tin Eq
    </h1>
    <ol class="breadcrumb">
        <button type="button" id="export" class="btn btn-primary" 
                            onclick="exportExcel();"><spring:message code="admin.common.export.excel" /></button>
        <c:if test="${tnodeName!=null}">
            <a href="<%=request.getContextPath()%>/eq/preAddEq1?tnodeId=${tnodeId}&tnodeName=${tnodeName}"
               title="Thêm">
                <img src="<%=request.getContextPath()%>/image/icon/add.png">
            </a>
        </c:if>
    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body table-responsive"  >
                    <div  style=" overflow-x:scroll !important;;overflow-y:hidden !important;;">
                        <table id="treeadvanced" class="table table-bordered">
                            <thead>
                                <tr>

                                    <th width="180" >Tên</th>                                       
                                    <th>Number</th>
                                    <th>Vendor</th>
                                    <th>Note</th>
                                    <th>SeriaNumber</th>
                                    <th>Trạng Thái</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>                                       
                                <c:forEach var="eq1Item" items="${list}" varStatus="status">                             
                                    <tr data-tt-id='eq1_${eq1Item.tEq1BO.id}'>

                                        <td class="truncate" ><span class='rackIcon'>${eq1Item.tEq1BO.name}</span></td>
                                        <td>${eq1Item.tEq1BO.number}</td>
                                        <td>${eq1Item.tEq1BO.vendor}</td>
                                        <td>${eq1Item.tEq1BO.note}</td>
                                        <td>N/A</td>
                                        <td>N/A</td>
                                        <td class="function">
                                            <a href="<%=request.getContextPath()%>/eq/preAddEq1?tnodeId=${eq1Item.tEq1BO.tnodeId}&tnodeName=${eq1Item.tEq1BO.tnodeName}"
                                               title="Thêm">
                                                <img src="<%=request.getContextPath()%>/image/icon/add.png">
                                            </a>
                                            <a href="<%=request.getContextPath()%>/eq/preAddEq2?tnodeId=${eq1Item.tEq1BO.tnodeId}&eq1Id=${eq1Item.tEq1BO.id}&eq1Name=${eq1Item.tEq1BO.name}"
                                               title="Thêm EQ2">
                                                &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/add_child.png">

                                            </a>
                                            <a href="<%=request.getContextPath()%>/eq/preUpdateEq1/${eq1Item.tEq1BO.id}?tnodeId=${tnodeId}"
                                               title="Sửa">
                                                &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                            </a>
                                            <a href="<%=request.getContextPath()%>/eq/deleteEq1/${eq1Item.tEq1BO.id}?tnodeId=${tnodeId}"
                                               title="Xóa">
                                                &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                            </a>
                                        </td>         

                                    </tr>
                                    <c:forEach var="eq2Item" items="${eq1Item.tEq1BO.tEq2BOList}">
                                        <tr data-tt-id='eq2_${eq2Item.id}' data-tt-parent-id='eq1_${eq1Item.tEq1BO.id}'>
                                            <td class="truncate" ><span class='subrackIcon'>${eq2Item.name}</span></td>
                                            <td>${eq2Item.number}</td>
                                            <td>${eq2Item.vendor}</td>
                                            <td>${eq2Item.note}</td>
                                            <td>N/A</td>
                                            <td>N/A</td>
                                            <td class="function">
                                                <a href="<%=request.getContextPath()%>/eq/preAddEq2?tnodeId=${tnodeId}&eq1Id=${eq2Item.eq1Id}&eq1Name=${eq2Item.eq1Name}"
                                                   title="Thêm">
                                                    <img src="<%=request.getContextPath()%>/image/icon/add.png">
                                                </a>
                                                <a href="<%=request.getContextPath()%>/eq/preAddEq3?tnodeId=${eq1Item.tEq1BO.tnodeId}&eq2Id=${eq2Item.id}&eq2Name=${eq2Item.name}"                                                
                                                   title="Thêm EQ3">
                                                    &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/add_child.png">

                                                </a>
                                                <a href="<%=request.getContextPath()%>/eq/preUpdateEq2/${eq2Item.id}?tnodeId=${tnodeId}"
                                                   title="Sửa">
                                                    &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                </a>
                                                <a href="<%=request.getContextPath()%>/eq/deleteEq2/${eq2Item.id}?tnodeId=${tnodeId}"
                                                   title="Xóa">
                                                    &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                </a>
                                            </td>                                                                                      
                                        </tr>
                                        <c:forEach var="itemEq3" items="${eq2Item.tEq3BOList}">                                            
                                            <tr data-tt-id='eq3_${itemEq3.id}' data-tt-parent-id='eq2_${eq2Item.id}'>
                                                <td class="truncate" ><span class='cardIcon'>${itemEq3.name}</span></td>
                                                <td>${itemEq3.number}</td>
                                                <td>${itemEq3.vendor}</td>
                                                <td>${itemEq3.note}</td>
                                                <td>${itemEq3.seriaNumber}</td>
                                                <td>N/A</td>
                                                <td class="function">
                                                    <a href="<%=request.getContextPath()%>/eq/preAddEq3?tnodeId=${tnodeId}&eq2Id=${itemEq3.eq2Id}&eq2Name=${itemEq3.eq2Name}"
                                                       title="Thêm">
                                                        <img src="<%=request.getContextPath()%>/image/icon/add.png">
                                                    </a>
                                                    <a href="<%=request.getContextPath()%>/eq/preAddTport?tnodeId=${eq1Item.tEq1BO.tnodeId}&eq3Id=${itemEq3.id}&eq3Name=${itemEq3.name}"                                                    
                                                       title="Thêm port">
                                                        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/add_child.png">

                                                    </a>
                                                    <a href="<%=request.getContextPath()%>/eq/preUpdateEq3/${itemEq3.id}?tnodeId=${tnodeId}"
                                                       title="Sửa">
                                                        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                    </a>
                                                    <a href="<%=request.getContextPath()%>/eq/deleteEq3/${itemEq3.id}?tnodeId=${tnodeId}"
                                                       title="Xóa">
                                                        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                    </a>
                                                    <a href="<%=request.getContextPath()%>/barcode/detail?partnumber=${itemEq3.partNumber}&SerialNumber=${itemEq3.seriaNumber}&MaVach=${itemEq3.maVach}"
                                                       title="BarCode">
                                                        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/barcode.png">
                                                    </a>
                                                </td>
                                            </tr>


                                            <c:forEach var="itemPort" items="${itemEq3.tPortBOList}">
                                                <tr data-tt-id='eq4_${itemPort.id}' data-tt-parent-id='eq3_${itemEq3.id}'>
                                                    <td class="truncate" ><span class='portIcon'>${itemPort.tportName}</span></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>${itemPort.note}</td>
                                                    <td>${itemPort.serialNo}</td>
                                                    <td>${itemPort.status == '1' ? 'Đang hoạt động' : 'Không hoạt động'}</td>
                                                    <td class="function">
                                                        <a href="<%=request.getContextPath()%>/eq/preAddTport?tnodeId=${eq1Item.tEq1BO.tnodeId}&eq3Id=${itemPort.eq3Id}&eq3Name=${itemPort.eq3Name}"
                                                           title="Thêm">
                                                            <img src="<%=request.getContextPath()%>/image/icon/add.png">
                                                        </a>
                                                        <a href="<%=request.getContextPath()%>/eq/preAddTquang?tnodeId=${eq1Item.tEq1BO.tnodeId}&tportId=${itemPort.id}&tportName=${itemPort.tportName}"                                                    
                                                           title="Thêm module quang">
                                                            <img src="<%=request.getContextPath()%>/image/icon/add_child.png">
                                                        </a>
                                                        <a href="<%=request.getContextPath()%>/eq/preUpdateTport/${itemPort.id}?tnodeId=${tnodeId}"
                                                           title="Sửa">
                                                            &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                        </a>
                                                        <a href="<%=request.getContextPath()%>/eq/deleteTport?id=${itemPort.id}?tnodeId=${tnodeId}"
                                                           title="Xóa">
                                                            &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                        </a>
                                                    </td>
                                                </tr>
                                                <c:forEach var="itemQuang" items="${itemPort.tModuleQuangBOList}">
                                                    <tr data-tt-id='eq5_${itemQuang.id}' data-tt-parent-id='eq4_${itemPort.id}'>
                                                        <td class="truncate" ><span class='portIcon'>${itemQuang.name}</span></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td>${itemQuang.note}</td>
                                                        <td>${itemPort.serialNo}</td>
                                                        <td>${itemPort.status == '1' ? 'Đang hoạt động' : 'Không hoạt động'}</td>
                                                        <td class="function">
                                                            <a href="<%=request.getContextPath()%>/eq/preAddTquang?tnodeId=${eq1Item.tEq1BO.tnodeId}&tportId=${itemQuang.portId}&tportName=${itemQuang.portName}"
                                                               title="Thêm">
                                                                <img src="<%=request.getContextPath()%>/image/icon/add.png">
                                                            </a>
                                                            <a href="<%=request.getContextPath()%>/eq/preUpdateTquang/${itemQuang.id}?tnodeId=${tnodeId}"
                                                               title="Sửa">
                                                                &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/edit_icon.png">
                                                            </a>
                                                            <a href="<%=request.getContextPath()%>/eq/deleteTquang?id=${itemQuang.id}?tnodeId=${tnodeId}"
                                                               title="Xóa">
                                                                &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/icon/delete.png">
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach> 
                                            </c:forEach> 

                                        </c:forEach> 
                                    </c:forEach>  
                                </c:forEach>       
                            </tbody>                                    
                        </table>   
                    </div>
                </div>
              
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<!-- ADD Footer PAGE -->
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

<!-- DataTables -->
<script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/resources/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/fastclick/fastclick.js"></script>


<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


<!--call ajax-->
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/jquery.treetable.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.treetable.theme.custom.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.treetable.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/fileSaver.js" type="text/javascript"></script>
</body>
</html>
<script>




            $(document).ready(function() {
                                                       
                $('.truncate').tooltip();




                $("#treeadvanced").treetable({expandable: true});

                // Highlight selected row
                $(".indenter").each(function() {
                    $(this).css('background-image', $(this).find('a').css('background-image'));
                });

                $("#treeadvanced tbody").on("mousedown", "td", function() {
                    if(!$(this).hasClass('function')) {
                        $(".selected").not(this).removeClass("selected");
                        $(this).parent().toggleClass("selected");
                        $(this).find('.indenter a').click();
                        $(this).find('.indenter').css('background-image', $(this).find('.indenter a').css('background-image'));
                    }
                });

            });
            
            function exportExcel(){
                var url = window.location.href;
                var param = '';
                if(url.indexOf('?') > -1){
                    param = url.substring(url.indexOf('?'), url.length);
                }
                var urlGet = '<%=request.getContextPath()%>/broadband/export/eq'  + param;
                $.get(urlGet, function (data) {
                    fnExcelReport(data,"Danh_sach_Equipment");
                });
            }
</script>

