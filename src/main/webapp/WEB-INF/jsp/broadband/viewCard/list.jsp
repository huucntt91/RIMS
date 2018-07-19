
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.min.js"></script> 
        <link href="${pageContext.request.contextPath}/resources/css/device.css" rel="stylesheet" type="text/css"/>
    </head>

    <body class="skin-blue">
        <section class="content" style="text-align: center"> 
            <c:if test="${row != null}">
            <div id="ASR9000DV" class="DeviceASR9000">
                <div class="lblMiddle">
                    <span class="lblMidleCss" name="deviceName" style="font-weight:bold; color: blue">${tnodeName}</span>
                </div>
                <div class="desk">
                    <div class="khung_left">
                    </div>
                    <div class="keyboard-black">
                        <c:forEach var = "i" begin = "1" end = "${colum}">
                        <div id="SlotStatus${i}" class="keyboard-b key-b1" style="">
                            <div class="keyboard-b-inside">
                                <span>${i}</span>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    
                    <div class="keyboard-white">
                        <c:forEach var = "i" begin = "1" end = "${colum}">
                                <div id="p${i}" class="keyboard-w key-w1 context-menu-one box menu-1" style="pointer-events:visible;" >
                                    <c:forEach var = "r" begin = "1" end = "${row}">
          
                                            <div onclick="openPort(<c:forEach var="item" items="${lstCard}" varStatus="status">${item.teq2_number == r && item.teq3_number == i ? item.teq3_id : '0' }</c:forEach>)"  id="SubSlot${i}${r}" class="keyboard-w key-w1 context-menu-one box menu-1 pressDiv" <c:forEach var="item" items="${lstCard}" varStatus="status">   ${item.teq2_number == r && item.teq3_number == i ? 'title="Đang hoạt động" style="cursor: pointer;left:1px;width:41px;background-color:#11ff00;" ' : ''}  </c:forEach>  >
                                                    <span id="lblSubSlot${i}${r}" class="verticaltext"><c:forEach var="item" items="${lstCard}" varStatus="status">${item.teq2_number == r && item.teq3_number == i ? item.teq3_name : ''} </c:forEach></span>
                                            </div>
                                       
                                        </c:forEach>
                                 
                                    
                                </div>
                        </c:forEach>
                    </div>
                    <div class="khung_left">
                    </div>
                </div>
                
            </div>
            </c:if>
            <c:if test="${row == null}">
                <h3 style="text-align: center; color: red">Chưa có dữ liệu</h3>
            </c:if>
        </section>

    </body>
    <script>
        function openPort(id){
            if(id != 0)
                window.open("${pageContext.request.contextPath}/tPortView/view/" + id, "_blank");
        }
    </script>
        
</html>

<!-- ADD Footer PAGE -->



