<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div style="width:100%;max-height: 300px;overflow-y: scroll;">
    <table class="table">
    <thead>
        <tr>
         
            <th>Code</th>
            <th>Name</th>
            <th>Type</th>
            <th>Địa chỉ </th>
            <th>Vị trí</th>
        </tr>
    </thead>
    <tbody>
           <c:forEach var="item" items="${list}" >   
                      <tr>
                         
                          <td class="text-left" >${item.code}</td>
                          <td class="text-left" >${item.tenHeThong}</td>
                          <td class="text-left" >${item.tenNeType}</td>
                          <td class="text-left" alight="left">${item.address}</td>
                          <td>
                              <a onclick="SetLocation(${item.longitude},${item.latitude})"><i class="fa fa-search-plus"></i></a>  
                          </td>
                      </tr>
           </c:forEach>    
    </tbody>
</table>
</div>
