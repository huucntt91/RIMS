<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table id="example1" class="table table-bordered table-striped">
    <thead>
        <tr>
            <th>STT</th>
            <th>CODE</th>   
            <th>Name</th>
            <th>Tỉnh/TP</th> 
            <th>Loại thiết bị</th>
            <th>Dòng thiết bị</th> 
            <th>Trạng thái</th> 
            <th>Tổng slot</th> 
            <th>Building</th>
            <th>IP</th>
            <th>RING</th>
            <th>NOTE</th>

        </tr>
    </thead>
    <tbody>                                       
        <c:forEach var="item" items="${list}" varStatus="status">                                        
            <tr>
                <td>${startRow + (status.index)}</td>
                <td>${item.TNODE_CODE}</td>
                <td>${item.TNODE_NAME}</td>
                <td>${item.tenTinh}</td>
                <td>${item.typeName}</td>
                <td>${item.TEN_DONG_TBI}</td>
                <td>${item.statusValue}</td>
                <td>${item.TOTAL_SLOT}</td>
                <td>${item.ma_building}</td>
                <td>${item.IP}</td>                               
                <td>${item.RING}</td>     
            </tr>
        </c:forEach>                                       							
    </tbody>                                    
</table>   