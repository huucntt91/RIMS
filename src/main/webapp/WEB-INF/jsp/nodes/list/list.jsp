<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- ADD HEADER PAGE -->
<%@include file="../../include/header.jsp"%>

<script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>

<!--<script src="js/jquery.multi-select.js"></script>-->
<section class="content-header">
    <h1>
        Quản lý Nodes  <button class="btn btn-default btn-sm" onclick="hdsd('HDSD_TRAM_RIMS.mp4');" >Hướng dẫn</button>
    </h1>
    <ol class="breadcrumb">
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'excelUpdateNode/init/1')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/cellsExcel/update/init'" >
                <span>  <img width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Cập nhật cell với Excel</span>
            </button>
        </c:if>
        <c:if test="${fn:containsIgnoreCase(sessionScope.function, 'excelUpdateNode/init/2')}">
            <button class="btn btn-info btn-sm"  onclick="location.href = '<%=request.getContextPath()%>/excelUpdateNode/init/2'" >
                <span>  <img  width="17" src="${pageContext.request.contextPath}/resources/img/excel.png" alt=""/>Cập nhật BTS/NODEB/ENODEB với Excel</span> 
            </button>
        </c:if>      
    </ol>
</section>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><spring:message code="admin.common.search" /></h3>
                </div>
                <form:form method="GET" id="frm_search">
                    <div class="box-body" id ="boxSearch">
                        <div class="col-md-4">
                            <div class="form-group">
                                <select name="neTypeId" id="neTypeId" class="form-control"> >
                                    <!--<option value="">--- Chọn NE TYPE ---</option>-->
                                    <c:forEach var="neBO" items="${neList}">
                                        <%--c:if test='${neBO.id != 9 && neBO.id != 10}'--%>
                                        <c:if test='${neBO.id == 2 ||  neBO.id == 3
                                                      ||  neBO.id == 8 ||  neBO.id == 5
                                                      ||  neBO.id == 6
                                                      ||  neBO.id == 7
                                                      ||  neBO.id == 11}'>
                                              <option  
                                                  value="${neBO.id}"  <c:choose>
                                                      <c:when test="${neBO.id == neTypeId}">
                                                          selected    
                                                      </c:when>    
                                                  </c:choose>

                                                  >${neBO.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>  
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <select name="thietBiId" id="thietBiId" class="form-control"> >
                                    <option value="">--- Chọn Vendor ---</option>
                                    <c:forEach var="tbBO" items="${thietBiList}">
                                        <option  
                                            value="${tbBO.thietBiId}"  <c:choose>
                                                <c:when test="${tbBO.thietBiId == thietBiId}">
                                                    selected    
                                                </c:when>    
                                            </c:choose>

                                            >${tbBO.tenThietBi}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <select name="status" id="status" class="form-control"> >
                                    <option  value="">--- Chọn trạng thái ---</option>
                                    <option ${status}   <c:if test='${status==NE_REG_ON}'> selected </c:if> value="${NE_REG_ON}">Đăng ký on air</option>
                                    <option <c:if test='${status==NE_REG_OFF}'> selected </c:if> value="${NE_REG_OFF}">Đăng ký off air</option>
                                    <option <c:if test='${status==NE_APPROVE_ON}'> selected </c:if> value="${NE_APPROVE_ON}">On air</option>
                                    <option <c:if test='${status==NE_APPROVE_OFF}'> selected </c:if> value="${NE_APPROVE_OFF}">Off air</option>
                                    <option <c:if test='${status==NE_UNAPPROVE_ON}'> selected </c:if> value="${NE_UNAPPROVE_ON}">Hủy on air</option>
                                    <option <c:if test='${status==NE_UNAPPROVE_OFF}'> selected </c:if> value="${NE_UNAPPROVE_OFF}">Hủy off air</option>

                                    </select>  
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class=" input-group-addon">Khu vực</label>
                                        <select multiple="multiple" name="khuvucId" id="khuvucId" class="form-control" onchange="getTinhTp();"
                                                > 
                                        <c:forEach var="tinhBO" items="${khuvucList}">
                                            <option  <c:if test='${fn:contains(khuvucId,tinhBO.id)}' >  selected="selected" </c:if>
                                                                                                        value="${tinhBO.id}"  
                                                                                                        >${tinhBO.name}</option>
                                        </c:forEach>


                                    </select>                                  
                                </div>
                            </div>
                        </div> 

                        <div class="col-md-8">
                            <div class="form-group">
                                <input name="code" value="${code}"
                                       type="text" class="form-control" id="code"
                                       placeholder="Mã Node, tên hoặc địa chỉ">
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">Tỉnh TP</label>
                                    <select multiple="multiple" name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen();"  > 
                                    </select>
                                    <input type="hidden" value="${tinhTpId}" id="tinhTpIds"/>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">Quận/Huyện</label>
                                    <select multiple="multiple" name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa();"> 
                                    </select>
                                    <input type="hidden" value="${quanHuyenId}" id="quanHuyenIds"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4"> 
                            <div class="form-group">
                                <div class="input-group">
                                    <label class=" input-group-addon">Phường/Xã</label>
                                    <select multiple="multiple" name="phuongXaId" id="phuongXaId" class="form-control"> 
                                    </select>
                                    <input type="hidden" value="${phuongXaId}" id="phuongXaIds"/>     
                                    <input type="hidden" name="strFilter" value="${strFilter}"  id="strFilter" />
                                </div>
                            </div>
                        </div>

                        <div class="clearfix" ></div>
                        <div class="col-md-4"> 
                            <div class="form-group">
                                <button onclick="return afterText()" class="btn btn-primary">Add Filter (+)</button>
                                
                            </div>
                        </div>    
                        <div class="clearfix" ></div>

                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="button" class="btn btn-primary" onclick="filter()"><spring:message code="admin.common.search" /></button>
                        <button type="button" id="export" class="btn btn-primary" disabled="disabled"
                                data-toggle="modal" data-target="#exportExcel"   onclick="exportExcel();">Export excel</button>
                    </div>
                    
                        
                </form:form>
            </div>
        </div>
    </div>


    <div class="row">

        <div class="col-md-12" >

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh sách Nodes</h3>
                </div>
                <!-- /.box-header -->

                <c:if test="${neTypeId==2 || neTypeId==3 || neTypeId==8}">
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                            <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
                                            <th>FM</th>                                        
                                            <th>PM</th>
                                            </c:if>
                                        <th style="width: 100px;">Chức năng</th>
                                        <th>Trạng thái</th>
                                        <th>Mã Node</th>
                                            <c:if test="${neTypeId==8}">          
                                            <th>ENODEB ID</th>
                                            </c:if>
                                        <th>Ne Type</th> 
                                        <th>Mã BSC/RNC</th>
                                        <th>Mã trạm dự án</th>

                                        <th>Mã CSHT</th>
                                        <th>Đơn vị quản lý</th>
                                        <th>Quận huyện</th>
                                        <th>Phường xã</th>
                                        <th>Địa chỉ</th>
                                        <th>Tên người quản lý</th>
                                        <th>SĐT quản lý</th>
                                        <th>Tên cho quản lý</th>
                                        <th>Hoàn cảnh ra đời</th>
                                        <th>Ngày hoạt động</th>
                                        <th>Tên trên hệ thống</th>

                                        <th>Tên BSC/RNC</th>
                                            <c:if test="${neTypeId==3}">
                                            <th>DC-HSDPA 42M</th>
                                            </c:if>
                                            <c:if test="${neTypeId==8}">                                        
                                            <th>DC-HSDPA 42M</th>
                                            <th>MSC/MSS</th>
                                            <th>SGSN</th>
                                            </c:if>
                                        <th>Filter User</th>
                                        <th>Frequency Band</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                            <c:if test="${neTypeId!=8}">
                                            <th>Cosite 2G - 3G Type</th>
                                            <th>Mã Cosite 2G 3G</th>
                                            </c:if>

                                        <th>Thiết bị</th>
                                        <th>Loại Trạm</th>
                                        <th>Cấu hình</th>
                                        <th>Trạng thái đặt máy nổ</th>   

                                        <th>Node Links</th>

                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>

                                            <td> 
                                                <input type="hidden" class="node_id" value="${item.id}"/>
                                                <input type="hidden" class="type_id" value="${item.neTypeId}"/>
                                                ${startRow + (status.index)}
                                            </td>
                                            <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
                                                <td> <a href="#" onclick="myFM('${item.code}', '${item.tenNeType}')" data-toggle="modal" data-target="#myFM">FM</a> </td>
                                                <td> <a href="#" onclick="myPM('${item.code}', '${item.tenNeType}')" data-toggle="modal" data-target="#myPM">PM</a> </td>
                                            </c:if>
                                            <td>
                                                <!--public static int NE_REG_ON = 112;
                                                    public static int NE_APPROVE_ON = 211;
                                                    public static int NE_UNAPPROVE_ON = 121;
                                                    public static int NE_REG_OFF = 212;
                                                    public static int NE_APPROVE_OFF = 111;
                                                    public static int NE_UNAPPROVE_OFF = 221;-->

                                                <c:if test='${item.neTypeId == 5 || item.neTypeId == 6 || item.neTypeId == 7}'>   
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/history/init')}">
                                                        <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/init?type=CELL&code=${item.id}"
                                                           title="Lịch sử cell" >
                                                            <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_ON}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/cells/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>                                                 
                                                    <c:if test="${item.status==NE_UNAPPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preRegUpdate')}">
                                                            <%--<c:if test="${fn:containsIgnoreCase((UserBO)(sessionScope.user).id, item.userInsert)}">--%>
                                                            <c:if test="${sessionScope.user.id==item.userInsert}">                                                        
                                                                <a href="<%=request.getContextPath()%>/cell/preRegUpdate/${item.id}/${item.neTypeId}"
                                                                   title="Cập nhật thông tin đăng ký cell" >
                                                                    <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                                </a>   
                                                            </c:if>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_UNAPPROVE_OFF}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/cells/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>

                                                </c:if>

                                                <c:if test='${item.neTypeId == 2 || item.neTypeId == 3 || item.neTypeId == 8}'>   
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/history/init')}">
                                                        <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/init?type=BTS&code=${item.id}"
                                                           title="Lịch sử BTS/NodeB/eNodeB" >
                                                            <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                                        </a>
                                                    </c:if>

                                                    <c:if test="${item.status==NE_REG_ON}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/nodes/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>                                                 

                                                    <c:if test="${item.status==NE_UNAPPROVE_ON || item.status==NE_REG_ON }">

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preRegUpdate')}">
                                                            <%--<c:if test="${fn:containsIgnoreCase((UserBO)(sessionScope.user).id, item.userInsert)}">--%>
                                                            <c:if test="${sessionScope.user.id==item.userInsert}">                                                        
                                                                <a href="<%=request.getContextPath()%>/nodes/preRegUpdate/${item.id}/${item.neTypeId}"
                                                                   title="Cập nhật thông tin đăng ký" >
                                                                    <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                                </a>   
                                                            </c:if>
                                                        </c:if>

                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_UNAPPROVE_OFF}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/nodes/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>
                                                    <a style="cursor: pointer"  href="<%=request.getContextPath()%>/equipment/init?nodeId=${item.id}"
                                                       title="Danh sách thiết bị" >
                                                        <img src="<%=request.getContextPath()%>/resources/img/equipment/rack-16.png">
                                                    </a>
                                                </c:if>   

<!--                                        <a href="<%=request.getContextPath()%>/nodes/off/${item.id}"
title="Off" onclick="return confirm('Bạn có off không ?')">
<img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
</a>-->
                                            </td>
                                            <td><c:choose>
                                                    <c:when test="${item.status==NE_REG_ON}">                                                            
                                                        Đăng ký on air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_ON}">
                                                        On air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_ON}">
                                                        Hủy duyệt On air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_REG_OFF}">
                                                        Đăng ký off air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_OFF}">
                                                        Off air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_OFF}">
                                                        Hủy duyệt off air
                                                        <br />
                                                    </c:when> 

                                                    <c:otherwise>
                                                        Orther
                                                        <br />
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${item.code}</td>
                                            <c:if test="${neTypeId==8}">          
                                                <td>${item.enodebId}</td>
                                            </c:if>
                                            <td>${item.tenNeType}</td>
                                            <td>${item.maNodeCha}</td>
                                            <td>${item.codeTramDA}</td>
                                            <td>${item.codeBuilding}</td>
                                            <td>${item.donViName}</td>
                                            <td>${item.tenQuan}</td>
                                            <td>${item.tenPhuong}</td>
                                            <td>${item.address}</td>
                                            <td>${item.tenNgQLy}</td>
                                            <td>${item.SDTQLy}</td>
                                            <td>${item.name}</td>
                                            <td>${item.hoanCanhRaDoi}</td>
                                            <td>${item.ngayHoatDong}</td>
                                            <td>${item.tenTrenHeThong}</td>
                                            <td>${item.tenBSCRNC}</td>

                                            <c:if test="${neTypeId==3}">
                                                <td>${item.dCHSPDA42M}</td>
                                            </c:if>
                                            <c:if test="${neTypeId==8}">                                        
                                                <td>${item.dCHSPDA42M}</td>
                                                <td>${item.mSCMSS}</td>
                                                <td>${item.sGSN}</td>
                                            </c:if>


                                            <td>${item.filterUser}</td>
                                            <td>${item.frequencyBand}</td>
                                            <td>${item.lat}</td>
                                            <td>${item.lon}</td>
                                            <c:if test="${neTypeId!=8}">
                                                <td>${item.cosite2G3GType}</td>
                                                <td>${item.maCosite}</td>
                                            </c:if>

                                            <td>${item.tenThietBi}</td>
                                            <td>${item.tenLoaiTram}</td>
                                            <td>${item.cauHinh}</td>
                                            <td><c:choose>
                                                    <c:when test="${item.trangThaiMayNo == 'CĐ'}">                                                            
                                                        Cố định
                                                    </c:when>    
                                                    <c:when test="${item.trangThaiMayNo == 'DĐ'}">
                                                        Di động

                                                    </c:when> 
                                                    <c:when test="${item.trangThaiMayNo == 'KC'}">
                                                        Không có
                                                    </c:when> 
                                                    <c:otherwise>

                                                    </c:otherwise>
                                                </c:choose>

                                            </td>    
                                            <td> <a href="#" onclick="getNodeLink(${item.id}, '${item.code}')" data-toggle="modal" data-target="#myModal">Chi tiết</a> </td>

                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                    </div>
                </c:if>

                <!--cell-->
                <c:if test="${neTypeId==5}">
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>                                        
                                        <th>STT</th>
                                            <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
                                            <th>FM</th>                                        
                                            <th>PM</th>
                                            </c:if>
                                        <th style="width: 100px;">Chức năng</th>
                                        <th>Trạng thái</th>
                                        <th>Mã Node</th> 
                                        <th>Đơn vị</th>
                                        <th>Thiết bị</th>
                                        <th>Mã CSHT</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                        <th>Ne Type</th> 
                                        <th>Loại trạm</th>

                                        <th>Tên người quản lý</th>
                                        <th>SĐT người quản lý</th>
                                        <th>Note</th>
                                        <th>Mã node cha</th>
                                        <th>Mã trạm dự án</th>
                                        <th>Tên trên hệ thống</th>
                                        <th>Lac</th>
                                        <th>Ci</th>
                                        <th>Băng tần</th>
                                        <th>Bcch</th>
                                        <th>bsic</th>
                                        <th>Tch</th>
                                        <th>TrxConfig</th>
                                        <th>Cell Type</th>
                                        <th>Ngày hoặt động</th>
                                        <th>Hoàn cảnh ra đời</th>
                                        <th>Tên cell</th>
                                        <th>Tên nhóm</th>
                                      
                                        <th>Ngày đăng ký</th>
                                        <th>Ngày kiểm duyệt</th>
                                        <th>Ngày cấp phép</th>
                                        <th>Azimuth</th>
                                        <th>MechanicalTilt</th>
                                        <th>ElectricalTilt</th>
                                        <th>TotalTilt</th>
                                        <th>AntennaType</th>
                                        <th>AntennaModel</th>
                                        <th>AntennaPattern</th>
                                        <th>AntennaHigh</th>
                                        <th>BosterTma</th>
                                        <th>SpecialCoverage</th>
                                        <th>AntennaGain</th>


                                        <th>Node Links</th>

                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>
                                            <td> 
                                                <input type="hidden" class="node_id" value="${item.id}"/>
                                                <input type="hidden" class="type_id" value="${item.neTypeId}"/>
                                                ${startRow + (status.index)}
                                            </td>
                                            <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
                                                <td> <a href="#" onclick="myFM('${item.code}', '${item.tenNeType}')" data-toggle="modal" data-target="#myFM">FM</a> </td>
                                                <td> <a href="#" onclick="myPM('${item.code}', '${item.tenNeType}')" data-toggle="modal" data-target="#myPM">PM</a> </td>
                                            </c:if>
                                            <td>

                                                <c:if test='${item.neTypeId == 5 || item.neTypeId == 6 || item.neTypeId == 7}'>   
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/history/init')}">
                                                        <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/init?type=CELL&code=${item.id}"
                                                           title="Lịch sử cell" >
                                                            <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_ON}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/cells/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>
                                                        &nbsp;
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>                                                 
                                                    <c:if test="${item.status==NE_UNAPPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preRegUpdate')}">
                                                            <%--<c:if test="${fn:containsIgnoreCase((UserBO)(sessionScope.user).id, item.userInsert)}">--%>
                                                            <c:if test="${sessionScope.user.id==item.userInsert}">                                                        
                                                                <a href="<%=request.getContextPath()%>/cells/preRegUpdate/${item.id}/${item.neTypeId}"
                                                                   title="Cập nhật thông tin đăng ký cell" >
                                                                    <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                                </a>   
                                                            </c:if>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_UNAPPROVE_OFF}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/cells/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>
                                                        &nbsp;
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>

                                                </c:if>                                                
                                            </td>
                                            <td><c:choose>
                                                    <c:when test="${item.status==NE_REG_ON}">                                                            
                                                        Đăng ký on air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_ON}">
                                                        On air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_ON}">
                                                        Hủy duyệt On air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_REG_OFF}">
                                                        Đăng ký off air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_OFF}">
                                                        Off air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_OFF}">
                                                        Hủy duyệt off air
                                                        <br />
                                                    </c:when> 

                                                    <c:otherwise>
                                                        Orther
                                                        <br />
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${item.code}</td>
                                            <td>${item.donViName}</td>
                                            <td>${item.tenThietBi}</td>
                                            <td>${item.codeBuilding}</td>
                                            <td>${item.lat}</td>
                                            <td>${item.lon}</td>
                                            <td>${item.tenNeType}</td>
                                            <td>${item.tenLoaiTram}</td>

                                            <td>${item.tenNgQLy}</td>
                                            <td>${item.SDTQLy}</td>
                                            <td>${item.note}</td>
                                            <td>${item.maNodeCha}</td>
                                            <td>${item.codeTramDA}</td>
                                            <td>${item.tenTrenHeThong}</td>
                                            <td>${item.lac}</td>
                                            <td>${item.ci}</td>
                                            <td>${item.tenBangTan}</td>
                                            <td>${item.bcch}</td>
                                            <td>${item.bsic}</td>
                                            <td>${item.tch}</td>
                                            <td>${item.trxConfig}</td>
                                            <td>${item.cellType}</td>
                                            <td>${item.ngayHoatDong}</td>
                                            <td>${item.hoanCanhRaDoi}</td>
                                            <td>${item.tenCell}</td>

                                            <!--<td>{item.listCellGroupId}</td>-->

                                            <td>
                                                <c:set var="listCellGroupId" value="${fn:split(item.listCellGroupId, ',')}" />
                                                <c:forEach items="${listCellGroupId}" var="entry">
                                                    ${hashMapListCellGroup[entry].name},</br>
                                                </c:forEach>

                                            </td>

                                  
                                            <td>${item.ngayDangKy}</td>
                                            <td>${item.ngayKiemDuyet}</td>
                                            <td>${item.ngayCapPhep}</td>
                                            <td>${item.azimuth}</td>
                                            <td>${item.mechanicalTilt}</td>
                                            <td>${item.electricalTilt}</td>
                                            <td>${item.totalTilt}</td>
                                            <td>${item.antennaType}</td>
                                            <td>${item.antennaModel}</td>
                                            <td>${item.antennaPattern}</td>
                                            <td>${item.antennaHigh}</td>
                                            <td>${item.bosterTma}</td>
                                            <td>${item.specialCoverage}</td>
                                            <td>${item.antennaGain}</td>


                                            <td> <a href="#" onclick="getNodeLink(${item.id}, '${item.code}')" data-toggle="modal" data-target="#myModal">Chi tiết</a> </td>

                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                    </div>
                </c:if>
                <c:if test="${neTypeId==6}">
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                            <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
                                            <th>FM</th>                                        
                                            <th>PM</th>
                                            </c:if>
                                        <th style="width: 100px;">Chức năng</th>
                                        <th>Trạng thái</th>
                                        <th>Mã Node</th> 
                                        <th>Đơn vị</th>
                                        <th>Thiết bị</th>
                                        <th>Mã CSHT</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                        <th>Ne Type</th> 
                                        <th>Loại trạm</th>

                                        <th>Tên người quản lý</th>
                                        <th>SĐT người quản lý</th>
                                        <th>Note</th>
                                        <th>Mã node cha</th>
                                        <th>Mã trạm dự án</th>

                                        <th>Tên trên hệ thống</th>
                                        <th>Lac</th>
                                        <th>Ci</th>
                                        <th>Tên băng tần</th>
                                        <th>dlPsc</th>
                                        <th>cpichPower</th>
                                        <th>totalPower</th>
                                        <th>maxPower</th>

                                        <th>Cell Type</th>
                                        <th>Ngày hoạt động</th>
                                        <th>Hoàn cảnh ra đời</th>
                                        <th>Tên cell</th>
                                        <th>Tên nhóm</th>
                                        <th>Ngày đăng ký</th>
                                        <th>Ngày kiểm duyệt</th>
                                        <th>Ngày cấp phép</th>
                                        <th>Azimuth</th>
                                        <th>MechanicalTilt</th>
                                        <th>ElectricalTilt</th>
                                        <th>TotalTilt</th>
                                        <th>AntennaType</th>
                                        <th>AntennaModel</th>
                                        <th>AntennaPattern</th>
                                        <th>AntennaHigh</th>
                                        <th>noOfCarrier</th>
                                        <th>BosterTma</th>
                                        <th>SpecialCoverage</th>

                                        <th>Lý do</th>
                                        <th>AntennaGain</th>

                                        <th>Node Links</th>

                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>

                                            <td> 
                                                <input type="hidden" class="node_id" value="${item.id}"/>
                                                <input type="hidden" class="type_id" value="${item.neTypeId}"/>
                                                ${startRow + (status.index)}
                                            </td>
                                            <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
                                                <td> <a href="#" onclick="myFM('${item.code}', '${item.tenNeType}')" data-toggle="modal" data-target="#myFM">FM</a> </td>
                                                <td> <a href="#" onclick="myPM('${item.code}', '${item.tenNeType}')" data-toggle="modal" data-target="#myPM">PM</a> </td>
                                            </c:if>
                                            <td>

                                                <c:if test='${item.neTypeId == 5 || item.neTypeId == 6 || item.neTypeId == 7}'>   
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/history/init')}">
                                                        <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/init?type=CELL&code=${item.id}"
                                                           title="Lịch sử cell" >
                                                            <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_ON}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/cells/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>                                                 
                                                    <c:if test="${item.status==NE_UNAPPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preRegUpdate')}">
                                                            <%--<c:if test="${fn:containsIgnoreCase((UserBO)(sessionScope.user).id, item.userInsert)}">--%>
                                                            <c:if test="${sessionScope.user.id==item.userInsert}">                                                        
                                                                <a href="<%=request.getContextPath()%>/cells/preRegUpdate/${item.id}/${item.neTypeId}"
                                                                   title="Cập nhật thông tin đăng ký cell" >
                                                                    <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                                </a>   
                                                            </c:if>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_UNAPPROVE_OFF}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/cells/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>

                                                </c:if>                                                
                                            </td>
                                            <td><c:choose>
                                                    <c:when test="${item.status==NE_REG_ON}">                                                            
                                                        Đăng ký on air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_ON}">
                                                        On air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_ON}">
                                                        Hủy duyệt On air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_REG_OFF}">
                                                        Đăng ký off air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_OFF}">
                                                        Off air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_OFF}">
                                                        Hủy duyệt off air
                                                        <br />
                                                    </c:when> 

                                                    <c:otherwise>
                                                        Orther
                                                        <br />
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${item.code}</td>
                                            <td>${item.donViName}</td>
                                            <td>${item.tenThietBi}</td>
                                            <td>${item.codeBuilding}</td>
                                            <td>${item.lat}</td>
                                            <td>${item.lon}</td>
                                            <td>${item.tenNeType}</td>
                                            <td>${item.tenLoaiTram}</td>

                                            <td>${item.tenNgQLy}</td>
                                            <td>${item.SDTQLy}</td>
                                            <td>${item.note}</td>
                                            <td>${item.maNodeCha}</td>
                                            <td>${item.codeTramDA}</td>

                                            <td>${item.tenTrenHeThong}</td>
                                            <td>${item.lac}</td>
                                            <td>${item.ci}</td>
                                            <td>${item.tenBangTan}</td>
                                            <td>${item.dlPsc}</td>
                                            <td>${item.cpichPower}</td>
                                            <td>${item.totalPower}</td>
                                            <td>${item.maxPower}</td>

                                            <td>${item.cellType}</td>
                                            <td>${item.ngayHoatDong}</td>
                                            <td>${item.hoanCanhRaDoi}</td>
                                            <td>${item.tenCell}</td>
                                            <td>
                                                <c:set var="listCellGroupId" value="${fn:split(item.listCellGroupId, ',')}" />
                                                <c:forEach items="${listCellGroupId}" var="entry">
                                                    ${hashMapListCellGroup[entry].name},</br>
                                                </c:forEach>

                                            </td>
                                            <td>${item.ngayDangKy}</td>
                                            <td>${item.ngayKiemDuyet}</td>
                                            <td>${item.ngayCapPhep}</td>
                                            <td>${item.azimuth}</td>
                                            <td>${item.mechanicalTilt}</td>
                                            <td>${item.electricalTilt}</td>
                                            <td>${item.totalTilt}</td>
                                            <td>${item.antennaType}</td>
                                            <td>${item.antennaModel}</td>
                                            <td>${item.antennaPattern}</td>
                                            <td>${item.antennaHigh}</td>
                                            <td>${item.noOfCarrier}</td>
                                            <td>${item.bosterTma}</td>
                                            <td>${item.specialCoverage}</td>

                                            <td>${item.lyDo}</td>
                                            <td>${item.antennaGain}</td>


                                            <td> <a href="#" onclick="getNodeLink(${item.id}, '${item.code}')" data-toggle="modal" data-target="#myModal">Chi tiết</a> </td>

                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                    </div>
                </c:if>
                <!--cell 4g-->
                <c:if test="${neTypeId==7}">
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                            <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
                                            <th>FM</th>                                        
                                            <th>PM</th>
                                            </c:if>
                                        <th style="width: 100px;">Chức năng</th>
                                        <th>Trạng thái</th>
                                        <th>Mã Node</th>

                                        <th>Đơn vị</th>
                                        <th>Thiết bị</th>
                                        <th>Mã CSHT</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>
                                        <th>Ne Type</th> 
                                        <th>Loại trạm</th>

                                        <th>Tên người quản lý</th>
                                        <th>SĐT người quản lý</th>
                                        <th>Note</th>
                                        <th>Mã ENODEB</th>
                                        <th>ENODEB ID</th>
                                        <th>Mã trạm dự án</th>

                                        <th>Tên trên hệ thống</th>
                                        <th>Lac</th>
                                        <th>Ci</th>
                                        <th>Tên băng tần</th>
                                        <th>Pci</th>
                                        <th>tac</th>
                                        <th>lcrid</th>

                                        <th>Cell Type</th>
                                        <th>Ngày hoạt động</th>
                                        <th>Hoàn cảnh ra đời</th>
                                        <th>Tên cell</th>
                                        <th>Tên nhóm</th>
                                        <th>Ngày đăng ký</th>
                                        <th>Ngày kiểm duyệt</th>
                                        <th>Ngày cấp phép</th>
                                        <th>Azimuth</th>
                                        <th>MechanicalTilt</th>
                                        <th>ElectricalTilt</th>
                                        <th>TotalTilt</th>
                                        <th>AntennaType</th>
                                        <th>AntennaModel</th>
                                        <th>AntennaPattern</th>
                                        <th>AntennaHigh</th>
                                        <th>noOfCarrier</th>
                                        <th>BosterTma</th>
                                        <th>SpecialCoverage</th>
                                        <th>Lý do</th>
                                        <th>AntennaGain</th>



                                        <th>Node Links</th>

                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>

                                            <td> 
                                                <input type="hidden" class="node_id" value="${item.id}"/>
                                                <input type="hidden" class="type_id" value="${item.neTypeId}"/>
                                                ${startRow + (status.index)}
                                            </td>
                                            <c:if test='${fn:contains(classAtrrView,"FM_PM")}'>
                                                <td> <a href="#" onclick="myFM('${item.code}', '${item.tenNeType}')" data-toggle="modal" data-target="#myFM">FM</a> </td>
                                                <td> <a href="#" onclick="myPM('${item.code}', '${item.tenNeType}')" data-toggle="modal" data-target="#myPM">PM</a> </td>
                                            </c:if>
                                            <td>

                                                <c:if test='${item.neTypeId == 5 || item.neTypeId == 6 || item.neTypeId == 7}'>   
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/history/init')}">
                                                        <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/init?type=CELL&code=${item.id}"
                                                           title="Lịch sử cell" >
                                                            <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_ON}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/cells/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>                                                 
                                                    <c:if test="${item.status==NE_UNAPPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preRegUpdate')}">
                                                            <%--<c:if test="${fn:containsIgnoreCase((UserBO)(sessionScope.user).id, item.userInsert)}">--%>
                                                            <c:if test="${sessionScope.user.id==item.userInsert}">                                                        
                                                                <a href="<%=request.getContextPath()%>/cells/preRegUpdate/${item.id}/${item.neTypeId}"
                                                                   title="Cập nhật thông tin đăng ký cell" >
                                                                    <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                                </a>   
                                                            </c:if>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_UNAPPROVE_OFF}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/cells/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/cell/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off cell" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>

                                                </c:if>

                                                <c:if test='${item.neTypeId == 2 || item.neTypeId == 3 || item.neTypeId == 8}'>   
                                                    <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/history/init')}">
                                                        <a style="cursor: pointer" href="<%=request.getContextPath()%>/history/init?type=BTS&code=${item.id}"
                                                           title="Lịch sử BTS/NodeB/eNodeB" >
                                                            <img src="<%=request.getContextPath()%>/image/icon/history.png">
                                                        </a>
                                                    </c:if>

                                                    <c:if test="${item.status==NE_REG_ON}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_ON}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/nodes/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>                                                 
                                                    <c:if test="${item.status==NE_UNAPPROVE_ON || item.status==NE_REG_ON }">

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preRegUpdate')}">
                                                            <%--<c:if test="${fn:containsIgnoreCase((UserBO)(sessionScope.user).id, item.userInsert)}">--%>
                                                            <c:if test="${sessionScope.user.id==item.userInsert}">                                                        
                                                                <a href="<%=request.getContextPath()%>/nodes/preRegUpdate/${item.id}/${item.neTypeId}"
                                                                   title="Cập nhật thông tin đăng ký" >
                                                                    <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                                </a>   
                                                            </c:if>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${item.status==NE_REG_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_APPROVE_OFF}">

                                                    </c:if>
                                                    <c:if test="${item.status==NE_UNAPPROVE_OFF}">
                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/preUpdate')}">
                                                            <a href="<%=request.getContextPath()%>/nodes/preUpdate/${item.id}/${item.neTypeId}"
                                                               title="Cập nhật" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_edit.png">
                                                            </a>    
                                                        </c:if>

                                                        <c:if test="${fn:containsIgnoreCase(sessionScope.function, '/nodes/regOffAir')}">
                                                            <a style="cursor: pointer" data-toggle="modal" data-target="#myModalOff" onclick="regOff(${item.id}, '${item.code}', '${item.neTypeId}');"
                                                               title="Đăng ký Off" >
                                                                <img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
                                                            </a>
                                                        </c:if>
                                                    </c:if>

                                                </c:if>   

<!--                                        <a href="<%=request.getContextPath()%>/nodes/off/${item.id}"
title="Off" onclick="return confirm('Bạn có off không ?')">
<img src="<%=request.getContextPath()%>/image/icon/icon_off.png">
</a>-->
                                            </td>
                                            <td><c:choose>
                                                    <c:when test="${item.status==NE_REG_ON}">                                                            
                                                        Đăng ký on air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_ON}">
                                                        On air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_ON}">
                                                        Hủy duyệt On air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_REG_OFF}">
                                                        Đăng ký off air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_OFF}">
                                                        Off air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_OFF}">
                                                        Hủy duyệt off air
                                                        <br />
                                                    </c:when> 

                                                    <c:otherwise>
                                                        Orther
                                                        <br />
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${item.code}</td>
                                            <td>${item.donViName}</td>
                                            <td>${item.tenThietBi}</td>
                                            <td>${item.codeBuilding}</td>
                                            <td>${item.lat}</td>
                                            <td>${item.lon}</td>

                                            <td>${item.tenNeType}</td>
                                            <td>${item.tenLoaiTram}</td>

                                            <td>${item.tenNgQLy}</td>
                                            <td>${item.SDTQLy}</td>
                                            <td>${item.note}</td>
                                            <td>${item.maNodeCha}</td>
                                            <td>${item.enodebId}</td>
                                            <td>${item.codeTramDA}</td>

                                            <td>${item.tenTrenHeThong}</td>
                                            <td>${item.lac}</td>
                                            <td>${item.ci}</td>
                                            <td>${item.tenBangTan}</td>
                                            <td>${item.pci}</td>
                                            <td>${item.tac}</td>
                                            <td>${item.lcrid}</td>


                                            <td>${item.cellType}</td>
                                            <td>${item.ngayHoatDong}</td>
                                            <td>${item.hoanCanhRaDoi}</td>
                                            <td>${item.tenCell}</td>
                                            <td>
                                                <c:set var="listCellGroupId" value="${fn:split(item.listCellGroupId, ',')}" />
                                                <c:forEach items="${listCellGroupId}" var="entry">
                                                    ${hashMapListCellGroup[entry].name},</br>
                                                </c:forEach>

                                            </td>
                                            <td>${item.ngayDangKy}</td>
                                            <td>${item.ngayKiemDuyet}</td>
                                            <td>${item.ngayCapPhep}</td>
                                            <td>${item.azimuth}</td>
                                            <td>${item.mechanicalTilt}</td>
                                            <td>${item.electricalTilt}</td>
                                            <td>${item.totalTilt}</td>
                                            <td>${item.antennaType}</td>
                                            <td>${item.antennaModel}</td>
                                            <td>${item.antennaPattern}</td>
                                            <td>${item.antennaHigh}</td>
                                            <td>${item.noOfCarrier}</td>
                                            <td>${item.bosterTma}</td>
                                            <td>${item.specialCoverage}</td>
                                            <td>${item.lyDo}</td>
                                            <td>${item.antennaGain}</td>

                                            <td> <a href="#" onclick="getNodeLink(${item.id}, '${item.code}')" data-toggle="modal" data-target="#myModal">Chi tiết</a> </td>

                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                    </div>
                </c:if>
                <!--bsc rnc-->
                <!--bsc rnc-->
                <c:if test="${neTypeId==11}">
                    <div class="box-body table-responsive">
                        <div id="tablePagingId">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Trạng thái</th>
                                        <th>Mã Node</th> 
                                        <th>Ne Type</th> 
                                        <th>Mã CSHT</th>   
                                        <!--///-->
                                        <th>Đơn vị</th>
                                        <th>Địa chỉ</th>
                                        <th>Vender</th>                                        
                                        <th>Loại Trạm</th>
                                        <th>Node cha</th>
                                        <th>Latitude</th>
                                        <th>Longitude</th>                                        
                                        <th>Tên BSC/RNC</th>
                                        <th>Tên người quản lý</th>
                                        <th>SĐT quản lý</th>
                                        <th>Hoàn cảnh ra đời</th>
                                        <th>Loại BSC/RNC/MBSC</th>
                                        <th>Ngày cấp phép</th>
                                        <th>Ngày đăng ký</th>
                                        <th>Ngày kiểm duyệt</th>


                                        <th>Node Links</th>
                                    </tr>
                                </thead>
                                <tbody>                                       
                                    <c:forEach var="item" items="${list}" varStatus="status">                                        
                                        <tr>
                                            <td> 
                                                <input type="hidden" class="node_id" value="${item.id}"/>
                                                <input type="hidden" class="type_id" value="${item.neTypeId}"/>
                                                ${startRow + (status.index)}
                                            </td>
                                            <td><c:choose>
                                                    <c:when test="${item.status==NE_REG_ON}">                                                            
                                                        Đăng ký on air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_ON}">
                                                        On air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_ON}">
                                                        Hủy duyệt On air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_REG_OFF}">
                                                        Đăng ký off air
                                                        <br />
                                                    </c:when>    
                                                    <c:when test="${item.status==NE_APPROVE_OFF}">
                                                        Off air
                                                        <br />
                                                    </c:when> 
                                                    <c:when test="${item.status==NE_UNAPPROVE_OFF}">
                                                        Hủy duyệt off air
                                                        <br />
                                                    </c:when> 

                                                    <c:otherwise>
                                                        Orther
                                                        <br />
                                                    </c:otherwise>
                                                </c:choose></td>
                                            <td>${item.code}</td>
                                            <td>${item.tenNeType}</td>
                                            <td>${item.codeBuilding}</td>
                                            <td>${item.donViName}</td>
                                            <td>${item.address}</td>
                                            <td>${item.tenThietBi}</td>
                                            <td>${item.tenLoaiTram}</td>
                                            <td>${item.maNodeCha}</td>
                                            <td>${item.lat}</td>
                                            <td>${item.lon}</td>
                                            <td>${item.name}</td>
                                            <td>${item.tenNgQLy}</td>
                                            <td>${item.SDTQLy}</td>
                                            <td>${item.hoanCanhRaDoi}</td>
                                            <td>${item.typeBSCRNC}</td>
                                            <td>${item.ngayCapPhep}</td>
                                            <td>${item.ngayDangKy}</td>
                                            <td>${item.ngayKiemDuyet}</td>    

                                            <td> <a href="#" onclick="getNodeLink(${item.id}, '${item.code}')" data-toggle="modal" data-target="#myModal">Chi tiết</a> </td>                                            

                                        </tr>
                                    </c:forEach>                                       							
                                </tbody>                                    
                            </table>   
                        </div>
                    </div>
                </c:if>
                <!-- /.box-body -->
                <div class="box-footer">
                    <!-- ADD PAGE INFO -->
                    <%@include file="../../include/page_info.jsp"%>
                </div>
            </div>

        </div>

        <!--        <div class="col-md-4">
                    <div class="box">
                        <div class="box-header"><h3 class="box-title">Thông tin chi tiết</h3></div>
                        <div class="box-body table-responsive">
                        <div id="detailDiv">
        
                        </div>
        
                        </div>
                    </div>
                </div>-->
    </div>
    <div class="modal fade" id="myModalOff" tabindex="-1" role="dialog" aria-labelledby="myModalLabelOff" aria-hidden="true">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="myModalLabel"></h4>

                </div>

                <div class="modal-body">

                    <%--<form:form method="POST" action="approve" commandName="approveForm">--%>
                    <div class="box-body table-responsive">
                        <!--<iframe width="100%" height="150" frameborder="0"></iframe>-->
                        <form:form  method="POST" action="${pageContext.request.contextPath}/cell/approve" commandName="approveForm">
                            <div class="form-group" style="padding: 0 5px">
                                <form:textarea rows="4" type="text" class="form-control" required="true"
                                               path="comment"
                                               placeholder="Lý do"/>
                                <form:hidden path="status" id="status"></form:hidden>
                                <form:hidden path="nodeId" id="nodeId"></form:hidden>
                                <form:hidden path="parentCode" id="parentCode"></form:hidden>
                                <form:hidden path="nodeChaId" id="nodeChaId"></form:hidden>
                                <form:hidden path="type" id="type"></form:hidden>
                                </div>

                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button id="btnUpdateComment" type="summit" class="btn btn-primary">Đồng ý</button>
                                </div>
                        </form:form>
                    </div>

                    <%--</form:form>--%>
                </div>

            </div>
        </div>                 
    </div>
</section>
<div id="addFiller" style="display: none">
                        <div class="groupFilter">
                            <div class="col-md-2">                            
                                <div class="form-group">
                                    <select name="objectFill"  class="form-control objectFill"  onchange="changeObjectFill(this)">
                                        <option value="-1">Chọn Object</option>
                                        <option value="2">BTS</option>
                                        <option value="3">NodeB</option>
                                        <option value="8">eNodeB</option>
                                        <option value="5">Cell2G</option>
                                        <option value="6">Cell3G</option>
                                        <option value="7">Cell4G</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">                            
                                <div class="form-group">
                                    <select name="column"  class="form-control column"  onchange="changeAtrColum(this)">
                                        <option value="-1">Chọn thuộc tính</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="filterType" class="form-control filterType"> 
                                        <option value="Contains">Contains</option>
                                        <option value="Not contains">Not contains</option>
                                        <option value="startWith">startWith</option>
                                        <option value="endWith">endWith</option>
                                        <option value="NULL">NULL</option>
                                        <option value="NOT NULL">NOT NULL</option>
                                    </select>  
                                </div>
                            </div>                        
                            <div class="col-md-2">
                                <div class="form-group">
                                    <input  name="value" class="form-control value_" placeholder="Giá trị" required="true"                        
                                            type="text" value=" "/>                

                                </div>
                            </div>  
                            <div class="col-md-2">
                                <div class="form-group">
                                    <button type="button" onclick="removeText(this)" class="btn btn-danger">Remove (-)</button>    
                                </div>
                            </div>
                            <div class="clearfix" ></div>
                        </div>
                    </div>
                    <div style="display: none">
                        <select id="numberOption"> 
                            <option value=">">></option>
                            <option value=">=">>=</option>
                            <option value="<"><</option>
                            <option value="<="><=</option>
                            <option value="NULL">NULL</option>
                            <option value="NOT NULL">NOT NULL</option>
                        </select> 
                        <select id="varcharOption"> 
                            <option value="Contains">Contains</option>
                            <option value="Not contains">Not contains</option>
                            <option value="startWith">startWith</option>
                            <option value="endWith">endWith</option>
                            <option value="NULL">NULL</option>
                            <option value="NOT NULL">NOT NULL</option>
                        </select> 
                    </div>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách Node Links đến Node <span style="font-weight: bold" id="mynodes"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <iframe width="100%" height="450" frameborder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>
<div id="myPM" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Thông tin PM</span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <iframe width="100%" height="450" frameborder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>
<div id="myFM" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Thông tin FM</span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <iframe width="100%" height="450" frameborder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>
<div id="exportExcel" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Chọn thuộc tính cần xuất Excel</span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <div class="row" style="padding-left: 28px;">
                        <input type="checkbox" id="checkall"><label id="lbcheckall" for="checkall"> Chọn tất cả</label> 
                    </div>
                    <div id="divColumn">
                        
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="export" class="btn btn-primary" data-dismiss="modal" onclick="exportFinish();">Export</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>                 
</div>

<!-- ADD Footer PAGE -->
<%@include file="../../include/footer.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/fileSaver.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/tableexport.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {

        if ($("#quanHuyenId").val() != '')
            getListPhuongXa(${quanHuyenId});
        $('#example1 tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                //$(this).removeClass('selected');
            } else {
            $('#example1 tbody').find('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            }
            var node_id = $(this).find('.node_id').val();
            var type_id = $(this).find('.type_id').val();
            viewDetail(node_id, type_id);
        });
        
//        var where = ' AND 1 = 1 ';
//        var neTypeId = $("#neTypeId").val();
//        // check addfilter
//        var listObjectFill = "";
//        $("#boxSearch .groupFilter").each(function (i) {
//        listObjectFill += $(this).find('.objectFill').val() + ",";
//        where = where + convertQueryFilter(neTypeId, $(this).find('.objectFill').val(), $(this).find('.column').val(), $(this).find('.filterType').val(), $(this).find('.value_').val());
//        });
//        if (listObjectFill.length > 0)
//        {
//        if (listObjectFill.indexOf(neTypeId + ",") == - 1)
//                where = " AND 1 = 2 ";
//        }
//        where = EscapeCommasSemiColons(where);
//        alert(where)
    });
                                                
    function myPM(code, tenNeType) {
        $("#myPM iframe").prop({'src': '${pageContext.request.contextPath}/pm_fm/popup?vnpCode=' + code + '&nodeType=' + tenNeType});
    }

    function myFM(code, tenNeType) {
        $("#myFM iframe").prop({'src': '${pageContext.request.contextPath}/pm_fm/popup_fm?vnpCode=' + code + '&nodeType=' + tenNeType});
    }
    
    function regOff(nodeId, code, type) {

        var status =<%=Constants.NE_REG_OFF%>;


        $("#myModalLabel").html('Đăng ký off node ' + code);
        $(".modal-body #status").val(status);
        $(".modal-body #nodeId").val(nodeId);
        $(".modal-body #type").val(type);

    }

    //lay ra danh sach tinhtp theo khu vuc
    function getTinhTp() {
        var id = $("#khuvucId").val();
        var tinhTpIds = $("#tinhTpIds").val();
        $.get("${pageContext.request.contextPath}/mane/getTinhTp?khuVucId=" + id, function (data) {
        var html = '';
        if (data.length > 0) {
            data.forEach(function (data) {
            var htmlx = '<option value="' + data.tinhTpId + '" ';
            if (tinhTpIds.indexOf(data.tinhTpId) > -1) {
                htmlx += ' selected="selected" ';
                }
                htmlx += '>' + data.tenTinhTp + '</option>';
                html += htmlx;
            });
        }
        $('#tinhTpId').html(html);
        $('#tinhTpId').multiselect('rebuild');
        });
    }
    function getListHuyen(tinh)
        {
            var id = $("#tinhTpId").val();
            if (id === null) {
                id = $("#tinhTpIds").val();
            }
            var quanHuyenIds = $("#quanHuyenIds").val();
            $.get("${pageContext.request.contextPath}/mane/getQuanHuyen?tinhTpId=" + id, function (data) {
                var html = '';
                if (data.length > 0) {
                    data.forEach(function (data) {
                        var htmlx = '<option value="' + data.quanHuyenId + '" ';
                        if (quanHuyenIds.indexOf(data.quanHuyenId) > -1) {
                            htmlx += ' selected="selected" ';
                        }
                        htmlx += '>' + data.tenQuanHuyen + '</option>';
                        html += htmlx;
                    });
                }
                $('#quanHuyenId').html(html);
                $('#quanHuyenId').multiselect('rebuild');
            });
        }

    function getListPhuongXa(huyen)
        {
            var id = $("#quanHuyenId").val();
            if (id === null) {
                id = $("#quanHuyenIds").val();
            }
            if (id == null || id == '') {
                return;
            }
            var phuongXaIds = $("#phuongXaIds").val();
                $.get("${pageContext.request.contextPath}/mane/getPhuongXa?quanHuyenId=" + id, function (data) {
                var html = '';
                if (data.length > 0) {
                    data.forEach(function (data) {
                        var htmlx = '<option value="' + data.phuongXaId + '" ';
                        if (phuongXaIds.indexOf(data.phuongXaId) > -1) {
                            htmlx += ' selected="selected" ';
                        }
                            htmlx += '>' + data.tenPhuongXa + '</option>';
                            html += htmlx;
                    });
                }
                $('#phuongXaId').html(html);
                $('#phuongXaId').multiselect('rebuild');
            });
        }


        function getNodeLink(id, code)
            {
                $('#mynodes').text(code);
                $("#myModal iframe").prop({'src': '${pageContext.request.contextPath}/nodes/getNodeLink/' + id});
            }

                                                
        function viewDetail(id, type) {
            var linkDetail = '${pageContext.request.contextPath}/nodes/view/' + type + '/' + id;

            if (type == 5 || type == 6 || type == 7) {
                linkDetail = '${pageContext.request.contextPath}/cell/detail/' + id + '/' + type;

            } else if (type == 2 || type == 3 || type == 8) {
                linkDetail = '${pageContext.request.contextPath}/nodes/detail/' + id + '/' + type

            }
//        $.get(linkDetail, function(data) {
//            $('#detailDiv').html(data);
//        });
        }
</script>
<script>
    $(document).ready(function () {
        $('#tinhTpId').multiselect(({
            maxHeight: 200,
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));

        $('#quanHuyenId').multiselect(({
            maxHeight: 200,
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));

        $('#phuongXaId').multiselect(({
            maxHeight: 200,
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
//                var brands = $('#kpiLst option:selected');
//                var textTmp = '';
//                $(brands).each(function (index, brand) {
//                    textTmp += $(this).val() + ",";
//                });
//                $('#txtEvents').val(textTmp);
            }
        }));
        $('#khuvucId').multiselect(({
            maxHeight: 200,
            enableFiltering: true,
            includeSelectAllOption: true,
            onChange: function (element, checked) {
            }
        }));
        getTinhTp();
        if ($('#tinhTpIds').val() != null) {
            getListHuyen();
        }

        if ($('#quanHuyenIds').val() != null) {
            getListPhuongXa();
        }

        var node = new URLSearchParams(window.location.search);
        if (node.has('neTypeId')) {
            $('#export').removeAttr('disabled');
        }

        $('.iCheck-helper').click(function () {
            var parent = $(this).parent().get(0);
            //var checkboxId = parent.getElementsByTagName('input')[0].id;
            $(parent).find('input').click();
        });
        
        
        $('#checkall').click(function () {
            var isChecked = $("#checkall").prop("checked");
            if (!isChecked) {
                $('input[type="checkbox"]').iCheck('check');
            } else {
                $('input[type="checkbox"]').iCheck('uncheck');
            }
        });
        $('#lbcheckall').click(function () {
             $('#checkall').click();
        });

    });

    function hdsd(media) {
        window.open('${pageContext.request.contextPath}/hdsd/init?media=' + media, '_blank', 'width=700,height=500');
    }

    function exportExcel() {
        var ne_type = $('#neTypeId option:selected').text();
        if(ne_type == 'CELL2G')
            ne_type = 'CELL_2G';
        else if(ne_type == 'CELL4G')
            ne_type = 'CELL_4G'; 
        else if(ne_type == 'CELL3G')
            ne_type = 'CELL_3G';
        else if(ne_type == 'BSC_RNC_MBSC')    
            ne_type = 'BSC_RNC';  
        $.get("${API_RIMS}/listcolumn?ne_type=" + ne_type, function (data) {
            var html = '';
            if (data.length > 0) {
                var htmlNode = '<fieldset class="scheduler-border"><legend class="scheduler-border">Đối tượng</legend>';
                var htmlCHA = '<fieldset class="scheduler-border"><legend class="scheduler-border">Đối tượng cha</legend>';
                var htmlCSHT = '<fieldset class="scheduler-border"><legend class="scheduler-border">CSHT</legend>';
                data.forEach(function (data) {
                    var columnName = data.column_name;
                    var columnDes = data.column_desc == '' ? columnName : data.column_desc;
                    var htmlx = '<div class="col-sm-3"><input type="checkbox" value="'+ columnName +'" class="checkitem"/><label> ' + columnDes + '</label></div>';
                    if(data.group_object == 'NODE')
                        htmlNode += htmlx;
                    else if(data.group_object == 'CHA')
                        htmlCHA += htmlx;
                    else if(data.group_object == 'CSHT')
                        htmlCSHT += htmlx;
                });
                htmlNode += '</fieldset>';
                htmlCHA += '</fieldset>';
                htmlCSHT += '</fieldset>';
                html += htmlNode + htmlCHA + htmlCSHT;
                $('#divColumn').html(html);
            }
            

        });
    }

    function exportFinish(){
        var flag = false;
        var listColumn = '';
        $('#divColumn input[type="checkbox"]').each(function (i) {
            if ($(this).prop("checked")) {
                flag = true;
                listColumn += $(this).val() + ',';
            }
        });
        if (!flag) {
            alert('Bạn cần click chọn cột cần export');
            return;
        }  
        var ne_type = $('#neTypeId option:selected').text();
        if(ne_type == 'CELL2G')
            ne_type = 'CELL_2G';
        else if(ne_type == 'CELL3G')
            ne_type = 'CELL_3G'; 
        else if(ne_type == 'CELL4G')
            ne_type = 'CELL_4G';
        else if(ne_type == 'BSC_RNC_MBSC')    
            ne_type = 'BSC_RNC';
        var neTypeId = $('#neTypeId').val();
        var khuVuc = $('#khuvucId').val() == null ? '' : $('#khuvucId').val();
        var tinhTp = $('#tinhTpId').val() == null ? '' : $('#tinhTpId').val();
        var quanHuyen = $('#quanHuyenId').val() == null ? '' : $('#quanHuyenId').val();
        var phuongXa = $('#phuongXaId').val() == null ? '' : $('#phuongXaId').val();
        var vender =  $('#thietBiId').val() == null ? '' : $('#thietBiId').val();
        var statusList = $('#status').val() == null ? '' : $('#status').val();
        var nameSearch = $('#code').val() == null ? '' : $('#code').val();
        var u = '${userId}';      
        var filterSearch = $('#strFilter').val() == null ? '' : $('#strFilter').val();
        var urlDownload = '${API_RIMS}/exportdsdt?ne_type=' + ne_type + '&ne_id=' + neTypeId + '&start_row=&end_row=&node_id=&name=' + nameSearch + '&list_khuvuc_id=' + khuVuc + '&list_tinh_id=' + tinhTp + '&list_quan_id=' + quanHuyen + '&list_phuong_id=' + phuongXa + '&vender_id=' + vender + '&status_list=' + statusList + '&list_column=' + listColumn + '&u=' + u + '&filterSearch='+ filterSearch.replace(/%/gi, "%25");
        $('input[type="checkbox"]').iCheck('uncheck');
        $("#export").prop('disabled', true);
        window.location.href = urlDownload;
    }
    
    function afterText() {
        $('#boxSearch').append($('#addFiller').html());
        return false;
        }
    
    function changeObjectFill(that){
        $.get("${pageContext.request.contextPath}/nodes/fillAttrObject/" + $(that).val(), function (data) {
        var html = '<option value="">Chọn thuộc tính</option>';
        if (data.length > 0) {
        data.forEach(function (entry) {
        var htmlx = '<option  value="' + entry.columnId + '">' + entry.columnName + '</option>';
        html += htmlx;
        });
        $(that).parents('.groupFilter').find('.column').html(html);
        };
        });
        }
    
    function changeAtrColum(that)
        {
            var colum = $(that).val();
            var i = colum.substring(0, 1);
            if (i == 0) {
            $(that).parents('.groupFilter').find('.filterType').html($('#varcharOption').html());
            } else if (i == 1) {
            $(that).parents('.groupFilter').find('.filterType').html($('#numberOption').html());
            }
        }
    
    function convertQueryFilter(neTypeId, type, colum, logic, value)
        {
        value = value.trim();
        var str = " ";
        if (type == neTypeId)
        {
        // logic
        str += " AND " + colum.substring(1);
        if (logic == "Contains")
                str += " like '%" + value + "%'";
        else if (logic == "startWith")
                str += " like '" + value + "%'";
        else if (logic == "endWith")
                str += " like '%" + value + "'";
        else if (logic == "NULL")
                str += " IS NULL ";
        else if (logic == "NOT NULL")
                str += " IS NOT NULL ";
        // toan tu
        else if (logic == "<")
                str += " < " + value;
        else if (logic == "<=")
                str += " <= " + value;
        else if (logic == ">")
                str += " > " + value;
        else if (logic == ">=")
                str += " >= " + value;
        }
        return str;
        }
    
    function filter(){
        var checkValue = false;
        $("#boxSearch .groupFilter").each(function (i) {
            if($(this).find('.filterType').val().trim() != 'NULL' && $(this).find('.filterType').val().trim() != 'NOT NULL'){
                if($(this).find('.value_').val().trim() == ''){
                    checkValue = true;
                }
            }
//            if (($(this).find('.value_').val().trim() == '' 
//                    && $(this).find('.filterType').val().trim() != 'NULL')
//                ||
//                    ($(this).find('.value_').val().trim() == '' 
//                            && $(this).find('.filterType').val().trim() != 'NOT NULL')) {
//                checkValue = true;
//            }
        });
        if (checkValue){
            alert('Bạn phải nhập dữ liệu cho filter');
            return;
        }else{
            var where = ' AND 1 = 1 ';
            var neTypeId = $("#neTypeId").val();
            // check addfilter
            var listObjectFill = "";
            $("#boxSearch .groupFilter").each(function (i) {
            listObjectFill += $(this).find('.objectFill').val() + ",";
            where = where + convertQueryFilter(neTypeId, $(this).find('.objectFill').val(), $(this).find('.column').val(), $(this).find('.filterType').val(), $(this).find('.value_').val());
            });
            if (listObjectFill.length > 0)
            {
            if (listObjectFill.indexOf(neTypeId + ",") == - 1)
                    where = " AND 1 = 2 ";
            }
    //        where = EscapeCommasSemiColons(where);
            document.getElementById("strFilter").value = where;
            
            document.getElementById("frm_search").submit();
        }
        
    }
    function removeText(that) {
        $(that).parents('.groupFilter').remove();
        }
        
    function EscapeCommasSemiColons(input){
        var output=input.replaceAll(",", "\\,"); //replace all the commas
        output=output.replaceAll(";", "\\;"); //replace all the SemiColons
        return output;
    }    
</script>


<style>
    #exportExcel label{
        padding-left: 5px;
    }
    fieldset.scheduler-border {
        border: 1px groove #ddd !important;
        padding: 0 1.4em 1.4em 1.4em !important;
        margin: 0 0 1.5em 0 !important;
        -webkit-box-shadow:  0px 0px 0px 0px #000;
                box-shadow:  0px 0px 0px 0px #000;
    }

    legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
    }
</style>