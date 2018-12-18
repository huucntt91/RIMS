<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div style="width:100%;max-height: 500px;overflow-y: scroll;" class="table-responsive">
    <table class="table" id="listNodes">
    <thead>
        <tr>
         
            <th>Mã </th>
             <c:if test="${objectType!='-1'}">
                              <th>Tên hệ thống</th>
                          </c:if>
            
            <th>Loại</th>
            <th>Địa chỉ </th>
            <th>Vị trí</th>
        </tr>
    </thead>
    <tbody >
           <c:forEach var="item" items="${list}" >   
                      <tr>
                         
                          <td class="text-left" >${item.code}</td>
                          <c:if test="${objectType!='-1'}">
                              <td class="text-left" >${item.tenHeThong}</td>
                          </c:if>
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
<script>
   
    $('#listNodes').DataTable();
     <c:if test="${resultCount==1}">
         SetLocation(${list[0].longitude},${list[0].latitude});                     
     </c:if>

</script>