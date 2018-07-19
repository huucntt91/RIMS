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
            <th>TNode cha</th> 
            <th>Dòng thiết bị</th> 
            <th>Trạng thái</th> 
            <th>Tổng slot</th> 
            <th>Building</th>
            <th>IP</th>                                    
            <th>NOTE</th>
            <th>vodLan</th>
            <th>serialNo</th>

        </tr>
    </thead>
    <tbody>                                       
        <c:forEach var="item" items="${list}" varStatus="status">                                        
            <tr>
                <td>${startRow + (status.index)}</td>
                <td>${item.code}</td>
                <td>${item.name}</td>
                <td>${item.tenTinh}</td>
                <td>${item.typeName}</td>
                <td>${item.chaName}</td>
                <td>${item.dongTBiName}</td>
                <td>${item.status}</td>
                <td>${item.totalSlot}</td>
                <td>${item.buildingCode}</td>
                <td>${item.ip}</td>                                                                       
                <td>${item.note}</td> 
                <td>${item.vodLan}</td> 
                <td>${item.serialNo}</td> 
            </tr>
        </c:forEach>                                       							
    </tbody>                                    
</table>   
