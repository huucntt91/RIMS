
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<table id="example1" class="table table-bordered table-striped">
    <thead>
        <tr>
            <th>STT</th>
            <th>Tỉnh/TP</th>
            <th>Quận/Huyện</th> 
            <th>Tên</th>
            <th>Mã</th>
            <th>Latitude</th> 
            <th>Longitude</th>
            <th>Địa chỉ</th>
            <th>Mô tả</th>
        </tr>
    </thead>
    <tbody>                                       
        <c:forEach var="item" items="${list}" varStatus="status">                                        
            <tr>
                <td>${startRow + (status.index + 1)}</td>
                <td>${item.tenTinhTp}</td>
                <td>${item.tenQuanHuyen}</td>
                <td>${item.name}</td>
                <td>${item.code}</td>
                <td>${item.lat.replace('.',',')}</td>
                <td>${item.lon.replace('.',',')}</td>
                <td>${item.address}</td>
                <td>${item.des}</td>
            </c:forEach>                                       							
    </tbody>                                    
</table>   
