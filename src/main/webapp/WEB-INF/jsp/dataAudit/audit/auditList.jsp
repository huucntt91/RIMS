<%@page import="com.vnpt.media.rims.common.Message"%>
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
                    <h3 class="box-title">Audit</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div id="tablePagingId">

                        <table id="example1" class="table table-bordered table-hover">
                            <thead>
                                <tr>                                            
                                    <th>#</th>
                                    <th>Không có trên Inventory</th>
                                    <th>Không có trên RIMS</th>                                            
                                    <th>Dũ liệu sai khác trên RIMS và Inventory</th>                                            
                                </tr>
                            </thead>                                    
                            <tbody>                                       
                                <c:forEach var="userBO" items="${list_audit_summary}" varStatus="status">                                        
                                    <tr>                                                
                                        <td>${userBO.title}</td>
                                        <td>
                                            <a href="<%=request.getContextPath()%>/dataAuditSummary/detail/${userBO.name}/1"
                                               title="Chi tiết" >
                                                ${userBO.totalRim}
                                            </a> 

                                        </td>
                                        <td><a href="<%=request.getContextPath()%>/dataAuditSummary/detail/${userBO.name}/3"
                                               title="Chi tiết" >
                                                ${userBO.totalInventory}
                                            </a>
                                        </td>
                                        <td><a href="<%=request.getContextPath()%>/dataAuditSummary/detail/${userBO.name}/2"
                                               title="Chi tiết" >
                                                ${userBO.totalDifference}
                                            </a>
                                        </td>  
                                    </tr>
                                </c:forEach>                                       							
                            </tbody>                                    
                        </table>
                    </div>
                </div>
                
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<%@include file="../../include/footer.jsp"%>


<script>

    $(document).ready(function() {

        $('#example1 tbody').on('click', 'tr', function() {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#example1 tbody').find('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    });



</script>