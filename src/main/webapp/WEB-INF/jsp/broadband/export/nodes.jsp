<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- ADD HEADER PAGE -->

<c:if test="${neTypeId==2 || neTypeId==3 || neTypeId==8}">
    <table id="example1" class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>STT</th>
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

                <th>Chọn thiết bị</th>
                <th>Loại Trạm</th>
                <th>Cấu hình</th>
                <th>Trạng thái đặt máy nổ</th>   
                <th>Trạng thái</th>
            </tr>
        </thead>
        <tbody>                                       
            <c:forEach var="item" items="${list}" varStatus="status">                                        
                <tr>
                    <td> 
                        ${startRow + (status.index)}
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
                    <td>${item.loaiTramId}</td>
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
                                Inactive
                                <br />
                            </c:otherwise>
                        </c:choose></td>   
                </tr>
            </c:forEach>                                       							
        </tbody>                                    
    </table>   

</c:if>

<!--cell-->
<c:if test="${neTypeId==5}">

    <table id="example1" class="table table-bordered table-striped">
        <thead>
            <tr>                                        
                <th>STT</th>

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
                <th>vnp code</th>
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
                <th>Trạng thái</th>



            </tr>
        </thead>
        <tbody>                                       
            <c:forEach var="item" items="${list}" varStatus="status">                                        
                <tr>
                    <td> 

                        ${startRow + (status.index)}
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

                    <td>${item.vnpCode}</td>
                    <td>${item.ngayDangKy}</td>
                    <td>${item.ngayKiemDuyet}</td>
                    <td>${item.ngayCapPhep}</td>
                    <td>${item.azimuth}</td>
                    <td>${item.mechanicalTilt}</td>
                    <td>${item.electricalTilt}</td>
                    <td>${item.totalTilt}</td>
                    <td>${item.antennaName}</td>
                    <td>${item.antennaModel}</td>
                    <td>${item.antennaPattern}</td>
                    <td>${item.antennaHigh}</td>
                    <td>${item.bosterTma}</td>
                    <td>${item.specialCoverage}</td>
                    <td>${item.antennaGain}</td>

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
                                Inactive
                                <br />
                            </c:otherwise>
                        </c:choose></td>
                </tr>
            </c:forEach>                                       							
        </tbody>                                    
    </table>   

</c:if>
<c:if test="${neTypeId==6}">

    <table id="example1" class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>STT</th>

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
                <th>Trạng thái</th>


            </tr>
        </thead>
        <tbody>                                       
            <c:forEach var="item" items="${list}" varStatus="status">                                        
                <tr>

                    <td> 

                        ${startRow + (status.index)}
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
                    <td>${item.antennaName}</td>
                    <td>${item.antennaModel}</td>
                    <td>${item.antennaPattern}</td>
                    <td>${item.antennaHigh}</td>
                    <td>${item.noOfCarrier}</td>
                    <td>${item.bosterTma}</td>
                    <td>${item.specialCoverage}</td>

                    <td>${item.lyDo}</td>
                    <td>${item.antennaGain}</td>

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
                                Inactive
                                <br />
                            </c:otherwise>
                        </c:choose></td>


                </tr>
            </c:forEach>                                       							
        </tbody>                                    
    </table>   

</c:if>
<!--cell 4g-->
<c:if test="${neTypeId==7}">


    <table id="example1" class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>STT</th>
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

                <th>Trạng thái</th>


            </tr>
        </thead>
        <tbody>                                       
            <c:forEach var="item" items="${list}" varStatus="status">                                        
                <tr>

                    <td> 

                        ${startRow + (status.index)}
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
                    <td>${item.antennaName}</td>
                    <td>${item.antennaModel}</td>
                    <td>${item.antennaPattern}</td>
                    <td>${item.antennaHigh}</td>
                    <td>${item.noOfCarrier}</td>
                    <td>${item.bosterTma}</td>
                    <td>${item.specialCoverage}</td>
                    <td>${item.lyDo}</td>
                    <td>${item.antennaGain}</td>

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
                                Inactive
                                <br />
                            </c:otherwise>
                        </c:choose></td>                  
                </tr>
            </c:forEach>                                       							
        </tbody>                                    
    </table>   

</c:if>
<!--bsc rnc-->
<!--bsc rnc-->
<c:if test="${neTypeId==11}">  
    <table id="example1" class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>STT</th>
                <th>Mã Node</th> 
                <th>Ne Type</th> 
                <th>Mã Building</th>   
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
                <th>Trạng thái</th>

            </tr>
        </thead>
        <tbody>                                       
            <c:forEach var="item" items="${list}" varStatus="status">                                        
                <tr>
                    <td> 

                        ${startRow + (status.index)}
                    </td>
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
                                Inactive
                                <br />
                            </c:otherwise>
                        </c:choose></td>

                </tr>
            </c:forEach>                                       							
        </tbody>                                    
    </table>   
</c:if>
