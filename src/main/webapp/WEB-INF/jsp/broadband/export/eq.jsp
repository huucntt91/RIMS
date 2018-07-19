<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table id="treeadvanced" class="table table-bordered">
    <thead>
        <tr>
            <th width="180" >Tên</th>
            <th width="180" >Type</th>
            <th width="180" >Parent</th>     
            <th>Number</th>
            <th>Vendor</th>
            <th>Note</th>
            <th>SeriaNumber</th>
            <th>Trạng Thái</th>
        </tr>
    </thead>
    <tbody>                                       
        <c:forEach var="eq1Item" items="${list}" varStatus="status">                             
            <tr data-tt-id='eq1_${eq1Item.tEq1BO.id}'>
                <td>${eq1Item.tEq1BO.name}</td>
                <td>Rack (shelf)</td>
                <td></td>
                <td>${eq1Item.tEq1BO.number}</td>
                <td>${eq1Item.tEq1BO.vendor}</td>
                <td>${eq1Item.tEq1BO.note}</td>
                <td>N/A</td>
                <td>N/A</td>    
            </tr>
            <c:forEach var="eq2Item" items="${eq1Item.tEq1BO.tEq2BOList}">
                <tr data-tt-id='eq2_${eq2Item.id}' data-tt-parent-id='eq1_${eq1Item.tEq1BO.id}'>
                    <td>${eq2Item.name}</td>
                    <td>Slot</td>
                    <td>${eq1Item.tEq1BO.name}</td>
                    <td>${eq2Item.number}</td>
                    <td>${eq2Item.vendor}</td>
                    <td>${eq2Item.note}</td>
                    <td>N/A</td>
                    <td>N/A</td>                                                                            
                </tr>
                <c:forEach var="itemEq3" items="${eq2Item.tEq3BOList}">                                            
                    <tr data-tt-id='eq3_${itemEq3.id}' data-tt-parent-id='eq2_${eq2Item.id}'>
                        <td>${itemEq3.name}</td>
                        <td>Card</td>
                        <td>${eq2Item.name}</td>
                        <td>${itemEq3.number}</td>
                        <td>${itemEq3.vendor}</td>
                        <td>${itemEq3.note}</td>
                        <td>${itemEq3.seriaNumber}</td>
                        <td>N/A</td>
                    </tr>


                    <c:forEach var="itemPort" items="${itemEq3.tPortBOList}">
                        <tr data-tt-id='eq4_${itemPort.id}' data-tt-parent-id='eq3_${itemEq3.id}'>
                            <td>${itemPort.tportName}</td>
                            <td>Port</td>
                            <td>${itemEq3.name}</td>
                            <td></td>
                            <td></td>
                            <td>${itemPort.note}</td>
                            <td>${itemPort.serialNo}</td>
                            <td>${itemPort.status == '1' ? 'Đang hoạt động' : 'Không hoạt động'}</td>
                        </tr>
                        <c:forEach var="itemQuang" items="${itemPort.tModuleQuangBOList}">
                            <tr data-tt-id='eq5_${itemQuang.id}' data-tt-parent-id='eq4_${itemPort.id}'>
                                <td>${itemQuang.name}</td>
                                <td>Module Quang</td>
                                <td>${itemPort.tportName}</td>
                                <td></td>
                                <td></td>
                                <td>${itemQuang.note}</td>
                                <td>${itemPort.serialNo}</td>
                                <td>${itemPort.status == '1' ? 'Đang hoạt động' : 'Không hoạt động'}</td>
                            </tr>
                        </c:forEach> 
                    </c:forEach> 

                </c:forEach> 
            </c:forEach>  
        </c:forEach>       
    </tbody>                                    
</table>   
