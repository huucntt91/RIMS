
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>

    .table-condensed{
        font-size: 10px !important;
    }
</style>

<div id="mydiv" >
    <ul class="nav nav-tabs" id="tabs">
        <c:if test="${listBTS != null}">
            <li data-id="2" data-position="7" class=""><a data-toggle="tab" href="#tabBts">BTS</a></li>
            </c:if>
            <c:if test="${listNODEB != null}">
            <li data-id="3" data-position="6" class=""><a data-toggle="tab" href="#tabNodeB">NodeB</a></li>
            </c:if>
            <c:if test="${listENODEB != null}">
            <li data-id="1" data-position="5" class=""><a data-toggle="tab" href="#tabeNodeB">eNodeB</a></li>
            </c:if>
            <c:if test="${listCELL2G != null}">
            <li data-id="5" data-position="10" class=""><a data-toggle="tab" href="#tabCell2G">Cell2G</a></li>
            </c:if>
            <c:if test="${listCELL3G != null}">
            <li data-id="6" data-position="9" class=""><a data-toggle="tab" href="#tabCell3G">Cell3G</a></li>
            </c:if>
            <c:if test="${listCELL4G != null}">
            <li data-id="7" data-position="8" class=""><a data-toggle="tab" href="#tabCell4G">Cell4G</a></li>
            </c:if>
            <c:if test="${listBUILDING != null}">
            <li data-id="4" data-position="1" class=""><a data-toggle="tab" href="#tabBuilding">CSHT</a></li>
            </c:if>
            <c:if test="${listBSCRNC != null}">
            <li data-id="11" data-position="4" class=""><a data-toggle="tab" href="#tabBscRnc">Bsc Rnc</a></li>
            </c:if>
            <c:if test="${listQH != null}">
            <li data-id="9" data-position="11" class=""><a data-toggle="tab" href="#tabQH">Node QH</a></li>
            </c:if>
            <c:if test="${listDA != null}">
            <li data-id="10" data-position="12" class=""><a data-toggle="tab" href="#tabDA">Node DA</a></li>
            </c:if>
            <c:if test="${listCS != null}">
            <li data-id="20" data-position="3" class=""><a data-toggle="tab" href="#tabCS">CS Core</a></li>
            </c:if>
            <c:if test="${listPS != null}">
            <li data-id="25" data-position="2" class=""><a data-toggle="tab" href="#tabPS">PS Core</a></li>
            </c:if>
    </ul>
    <div id="divContent">
        <c:if test="${listBTS != null}">
            <div id="tabBts" class="tab-pane fade">
                <table  class="table table-condensed table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã Node</th>
                            <th>Tên BSC/RNC</th>
                            <th>Ne Type</th> 
                            <th>Tên</th>
                            <th>Tên trên hệ thống</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Thiết bị</th>
                            <th>Cấu hình</th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>

                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listBTS}" varStatus="status">                                        
                            <tr>
                                <td> 

                                    ${startRow + (status.index + 1)}
                                </td>
                                <td><a href="#" onclick="approveDialogMap(${item.id},${item.neTypeId})" >${item.code} 
                                    </a>
                                </td>
                                <td>${item.tenBSCRNC}</td>
                                <td>${item.tenNeType}</td>
                                <td>${item.name}</td>
                                <td>${item.tenTrenHeThong}</td>
                                <td>${item.lat}</td>
                                <td>${item.lon}</td>
                                <td>${item.tenThietBi}</td>
                                <td>${item.cauHinh}</td>
                                <td><a href="#" onclick="setDisplayLink(1, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(2, ${item.id})" > Chi tiết </a></td>
                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>   
            </div>
        </c:if>
        <c:if test="${listNODEB != null}">
            <div id="tabNodeB" class="tab-pane fade">
                <table class="table  table-condensed table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã Node</th> 
                            <th>Tên BSC/RNC</th>
                            <th>Ne Type</th> 
                            <th>Tên</th>
                            <th>Tên trên hệ thống</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Thiết bị</th>
                            <th>Cấu hình</th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>        
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listNODEB}" varStatus="status">                                        
                            <tr>
                                <td> 

                                    ${startRow + (status.index + 1)}
                                </td>
                                <td><a href="#" onclick="approveDialogMap(${item.id},${item.neTypeId})" >${item.code} 
                                    </a>
                                </td>
                                <td>${item.tenBSCRNC}</td>
                                <td>${item.tenNeType}</td>
                                <td>${item.name}</td>
                                <td>${item.tenTrenHeThong}</td>
                                <td>${item.lat}</td>
                                <td>${item.lon}</td>
                                <td>${item.tenThietBi}</td>
                                <td>${item.cauHinh}</td>
                                <td>
                                    <a href="#" onclick="setDisplayLink(1, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(3, ${item.id})" > Chi tiết </a></td>
                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>   
            </div>
        </c:if>
        <c:if test="${listENODEB != null}">
            <div id="tabeNodeB" class="tab-pane fade">
                <table  class="table table-condensed table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã Node</th> 
                            <th>Tên BSC/RNC</th>
                            <th>Ne Type</th> 
                            <th>Tên</th>
                            <th>Tên trên hệ thống</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Thiết bị</th>
                            <th>Cấu hình</th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listENODEB}" varStatus="status">                                        
                            <tr>
                                <td> 

                                    ${startRow + (status.index + 1)}
                                </td>
                                <td><a href="#" onclick="approveDialogMap(${item.id},${item.neTypeId})" >${item.code} 
                                    </a>
                                </td>
                                <td>${item.tenBSCRNC}</td>
                                <td>${item.tenNeType}</td>
                                <td>${item.name}</td>
                                <td>${item.tenTrenHeThong}</td>
                                <td>${item.lat}</td>
                                <td>${item.lon}</td>
                                <td>${item.tenThietBi}</td>
                                <td>${item.cauHinh}</td>
                                <td>
                                    <a href="#" onclick="setDisplayLink(1, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(8, ${item.id})" > Chi tiết </a></td>
                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>   
            </div>
        </c:if>
        <c:if test="${listCELL2G != null}">
            <div id="tabCell2G" class="tab-pane fade">
                <table class="table  table-condensed table-bordered table-striped">
                    <thead>
                        <tr>                                        
                            <th>STT</th>
                            <th>Mã Node</th> 
                            <th>Tên BTS</th>
                            <th>Tên cell</th>
                            <th>Tên trên hệ thống</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Thiết bị</th>
                            <th>Ne Type</th> 
                            <th>LAC</th>
                            <th>CI</th>
                            <th>Băng tần</th>
                            <th>Azimuth</th>
                            <th>Total Tilt</th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listCELL2G}" varStatus="status">                                        
                            <tr>
                                <td> 
                                    ${startRow + (status.index + 1)}
                                </td>
                                <td>
                                    <a href="#" onclick="approveDialogMap(${item.id},${item.neTypeId})" >${item.code} 
                                    </a></td>
                                <td>${item.tenNodeCha}</td>
                                <td>${item.tenCell}</td>
                                <td>${item.tenTrenHeThong}</td>
                                <td>${item.lat}</td>
                                <td>${item.lon}</td>

                                <td>${item.tenThietBi}</td>
                                <td>${item.tenNeType}</td>

                                <td>${item.lac}</td>
                                <td>${item.ci}</td>
                                <td>${item.tenBangTan}</td>
                                <td>${item.azimuth}</td>
                                <td>${item.totalTilt}</td>
                                <td>
                                    <a href="#" onclick="setDisplayLink(1, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(5, ${item.id})" > Chi tiết </a></td>

                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>   
            </div>
        </c:if>
        <c:if test="${listCELL3G != null}">
            <div id="tabCell3G" class="tab-pane fade">
                <table class="table table-condensed table-bordered table-striped">
                    <thead>
                        <tr>                                        
                            <th>STT</th>
                            <th>Mã Node</th> 
                            <th>Tên NodeB</th>
                            <th>Tên cell</th>
                            <th>Tên trên hệ thống</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Thiết bị</th>
                            <th>Ne Type</th> 
                            <th>LAC</th>
                            <th>CI</th>
                            <th>Băng tần</th>
                            <th>Azimuth</th>
                            <th>Total Tilt</th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listCELL3G}" varStatus="status">                                        
                            <tr>
                                <td> 
                                    ${startRow + (status.index + 1)}
                                </td>
                                <td>
                                    <a href="#" onclick="approveDialogMap(${item.id},${item.neTypeId})" >${item.code} 
                                    </a></td>
                                <td>${item.tenNodeCha}</td>
                                <td>${item.tenCell}</td>
                                <td>${item.tenTrenHeThong}</td>
                                <td>${item.lat}</td>
                                <td>${item.lon}</td>
                                <td>${item.tenThietBi}</td>
                                <td>${item.tenNeType}</td>

                                <td>${item.lac}</td>
                                <td>${item.ci}</td>
                                <td>${item.tenBangTan}</td>
                                <td>${item.azimuth}</td>
                                <td>${item.totalTilt}</td>
                                <td><a href="#" onclick="setDisplayLink(1, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(6, ${item.id})" > Chi tiết </a></td>

                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>   
            </div>
        </c:if>
        <c:if test="${listCELL4G != null}">
            <div id="tabCell4G" class="tab-pane fade">
                <table class="table table-condensed table-condensed table-bordered table-striped">
                    <thead>
                        <tr>                                        
                            <th>STT</th>
                            <th>Mã Node</th> 
                            <th>Tên eNodeB</th>
                            <th>Tên cell</th>
                            <th>Tên trên hệ thống</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Thiết bị</th>

                            <th>Ne Type</th> 
                            <th>PCI</th>
                            <th>CI</th>
                            <th>TAC</th>
                            <th>Băng tần</th>
                            <th>Azimuth</th>
                            <th>Total Tilt</th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listCELL4G}" varStatus="status">                                        
                            <tr>
                                <td> 
                                    ${startRow + (status.index + 1)}
                                </td>
                                <td>
                                    <a href="#" onclick="approveDialogMap(${item.id},${item.neTypeId})" >${item.code} 
                                    </a></td>
                                <td>${item.tenNodeCha}</td>
                                <td>${item.tenCell}</td>
                                <td>${item.tenTrenHeThong}</td>
                                <td>${item.lat}</td>
                                <td>${item.lon}</td>
                                <td>${item.tenThietBi}</td>
                                <td>${item.tenNeType}</td>

                                <td>${item.pci}</td>
                                <td>${item.ci}</td>
                                <td>${item.tac}</td>
                                <td>${item.tenBangTan}</td>
                                <td>${item.azimuth}</td>
                                <td>${item.totalTilt}</td>
                                <td>
                                    <a href="#" onclick="setDisplayLink(1, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(7, ${item.id})" > Chi tiết </a></td>

                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>  
            </div>
        </c:if>
        <c:if test="${listBUILDING != null}">
            <div id="tabBuilding" class="tab-pane fade">
                <table id="example1" class="table table-condensed table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã Building</th>   
                            <th>Tỉnh/TP</th> 
                            <th>Quận/Huyện</th> 
                            <th>Phường/Xã</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Địa chỉ</th> 
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listBUILDING}" varStatus="status">                                        
                            <tr>
                                <td>${startRow + (status.index + 1)}</td>

                                <td>
                                    <a href="#" onclick="approveDialogMap(${item.id}, 4)" >${item.code} 
                                    </a>

                                </td>

                                <td>${item.tinhName}</td>
                                <td>${item.quanName}</td>
                                <td>${item.phuongName}</td>
                                <td>${item.lat}</td>
                                <td>${item.lon}</td>
                                <td>${item.address}</td>
                                <td><a href="#" onclick="displayEdit(0, ${item.id})" > Chi tiết </a></td>

                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>   
            </div>
        </c:if>
        <c:if test="${listBSCRNC != null}">
            <div id="tabBscRnc" class="tab-pane fade">
                <table class="table table-condensed table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên</th>                                            
                            <th>Địa chỉ</th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listBSCRNC}" varStatus="status">                                        
                            <tr>
                                <td>${startRow + (status.index + 1)}</td>
                                <td>
                                    <a href="#" onclick="approveDialogMap(${item.id},${item.neTypeId})" >${item.name} 
                                    </a></td>
                                <td>${item.address}</td>
                                <td><a href="#" onclick="setDisplayLink(1, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(11, ${item.id})" > Chi tiết </a></td>
                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>  
            </div>
        </c:if>
        <c:if test="${listQH != null}">
            <div id="tabQH" class="tab-pane fade">
                <table class="table table-condensed table-condensed table-bordered table-striped">
                    <thead>
                        <tr>                                        
                            <th>STT</th>
                            <th>Mã Quy hoạch</th>
                            <th>Tên quy hoạch</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Năm khởi tạo</th>
                            <th>Loại CN</th>
                            <th>Băng tần</th>
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listQH}" varStatus="status">                                        
                            <tr>
                                <td> 
                                    ${startRow + (status.index + 1)}
                                </td>
                                <td>
                                    <a href="#" onclick="approveDialogMap(${item.tramQhId}, 9)" >${item.maQh} 
                                    </a></td>
                                <td>${item.tenQh}</td>
                                <td>${item.latitude}</td>
                                <td>${item.longitude}</td>
                                <td>${item.namKhoiTao}</td>
                                <td>${item.loaiCongNghe}</td>
                                <td>${item.bangTan}</td> 
                                <td><a href="#" onclick="displayEdit(9, ${item.tramQhId})" > Chi tiết </a></td>
                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>  
            </div>
        </c:if>
        <c:if test="${listDA != null}">
            <div id="tabDA" class="tab-pane fade">
                <table class="table table-condensed table-condensed table-bordered table-striped">
                    <thead>
                        <tr>                                        
                            <th>STT</th>
                            <th>Mã trạm DA</th>
                            <th>Tên Trạm DA</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Mã HĐ</th>
                            <th>Tỉnh</th>                                            
                            <th>Huyện</th>
                            <th>Địa chỉ lắp đặt</th>
                            <th>Mã trạm BTS</th>
                            <th>Mã trạm NodeB</th>
                            <th>Sửa</th> 
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listDA}" varStatus="status">                                        
                            <tr>
                                <td>${startRow + (status.index + 1)}</td>
                                <td>
                                    <a href="#" onclick="approveDialogMap(${item.id}, 10)" >${item.maTramDuAn} 
                                    </a></td>
                                <td>${item.tenTramDuAn}</td>
                                <td>${item.latitude}</td>
                                <td>${item.longitude}</td>
                                <td>${item.maSoHopDong}</td>
                                <td>${item.tenTinhTp}</td>
                                <td>${item.tenQuanHuyen}</td>
                                <td>${item.address}</td>

                                <td>${item.maTramBTS}</td>
                                <td>${item.maTramNodeB}</td>
                                <td><a href="#" onclick="displayEdit(10, ${item.id})" > Chi tiết </a></td>
                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>  
            </div>
        </c:if>

        <c:if test="${listCS != null}">
            <div id="tabCS" class="tab-pane fade">
                <table class="table table-condensed table-condensed table-bordered table-striped">
                    <thead>
                        <tr>                                        
                            <th>STT</th>
                            <th>Mã node</th>                                  
                            <th>Địa chỉ </th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listCS}" varStatus="status">                                        
                            <tr>
                                <td>${startRow + (status.index + 1)}</td>
                                <td>${item.code}</td>                   
                                <td>${item.address}</td>
                                <td><a href="#" onclick="setDisplayLink(1, ${item.nodeId}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.nodeId}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(20, ${item.nodeId})" > Chi tiết </a></td>
                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>  
            </div>
        </c:if>

        <c:if test="${listPS != null}">
            <div id="tabPS" class="tab-pane fade">
                <table class="table table-condensed table-condensed table-bordered table-striped">
                    <thead>
                        <tr>                                        
                            <th>STT</th>    
                            <th>Mã node</th>                                  
                            <th>Địa chỉ </th>
                            <th>Link cha con</th>
                            <th>Link liên kết</th>
                            <th>Sửa</th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <c:forEach var="item" items="${listPS}" varStatus="status">                                        
                            <tr>
                                <td>${startRow + (status.index + 1)}</td>
                                <td>${item.code}</td>                   
                                <td>${item.address}</td>
                                <td><a href="#" onclick="setDisplayLink(1, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="setDisplayLink(2, ${item.id}, '${item.code}')" > Chi tiết </a></td>
                                <td><a href="#" onclick="displayEdit(25, ${item.id})" > Chi tiết </a></td>
                            </tr>
                        </c:forEach>                                       							
                    </tbody>                                    
                </table>  
            </div>
        </c:if>
    </div>
</div><!-- ./wrapper -->
