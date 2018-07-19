<%-- 
    Document   : editTab2
    Created on : Dec 26, 2016, 8:49:10 AM
    Author     : Cyano
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../include/header.jsp"%>
<section class="content">  
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Quản lý cơ sở hạ tầng của trạm</h3>
                </div>
                <div class="box-body"> 
                    <form method="post" action="${pageContext.request.contextPath}/project_station/updateCSHT" commandName="tramQhBo">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin chung</h3>
                            </div>
                            <div class="panel-body">    
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Mã trạm quy hoạch</label>
                                            <input type="text" class="form-control"  id="maQh" name="maQh" placeholder="nhập mã trạm dự án" value="${tramQuyHoach.maQh}" disabled="true"/>
                                            <input type="hidden" class="form-control" name="tramQhId" id="tramQhId">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tên trạm quy hoạch</label>
                                            <input type="text" class="form-control"  id="tenQh" name="tenQh" placeholder="Tên trạm dự án" value="${tramQuyHoach.tenQh}" disabled="true" />
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" > 
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Đơn vị chịu trách nhiệm</label>
                                            <select name="DVI_TRACH_NHIEM_CSHT_ID" id="DVI_TRACH_NHIEM_CSHT_ID" class="form-control"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="donViChiuTrachNhiem" items="${donViLst}">
                                                    <option value="${donViChiuTrachNhiem.don_vi_phe_duyet_id}" <c:choose> <c:when test="${donViChiuTrachNhiem.don_vi_phe_duyet_id == tramQuyHoach.DVI_TRACH_NHIEM_CSHT_ID}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${donViChiuTrachNhiem.ten_don_vi_phe_duyet}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" >          
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tỉnh/Thành phố</label>                                    
                                            <select name="tinhTpId" id="tinhTpId" class="form-control" onchange="getListHuyen(0);" > 
                                                <option value="">--- Chọn Tỉnh/Thành Phố ---</option>
                                                <c:forEach var="tinhBO" items="${tinhThanhPhoLst}">
                                                    <option  
                                                        value="${tinhBO.tinhTpId}"  <c:choose>
                                                            <c:when test="${tinhBO.tinhTpId == tramQuyHoach.tinhTpId}">
                                                                selected    
                                                            </c:when>    
                                                        </c:choose>

                                                        >${tinhBO.tenTinhTp}</option>
                                                </c:forEach>
                                            </select>
                                        </div> 
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" >          
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Quận/Huyện</label>                                    
                                            <select name="quanHuyenId" id="quanHuyenId" class="form-control" onchange="getListPhuongXa(0);" > 
                                                <option value="">---Chọn Quận / Huyện ---</option>
                                            </select>
                                        </div> 
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group" >
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Phường/Xã</label>
                                            <select name="phuongXaId" id="phuongXaId" class="form-control"> 
                                                <option value="">---Chọn Phường/Xã ---</option>
                                            </select>
                                        </div>    
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Địa chỉ</label>
                                            <input type="text" class="form-control"   name="DIA_CHI" id="DIA_CHI" value="${tramQuyHoach.DIA_CHI}"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tên trạm</label>
                                            <input type="text" class="form-control"   name="TEN_TRAM" id="TEN_TRAM" value="${tramQuyHoach.TEN_TRAM}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group" >   
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Cách thức xây dựng</label>
                                            <select name="CACH_XAY_CSHT_ID" id="CACH_XAY_CSHT_ID" class="form-control"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="cachThucXayDung" items="${cachThucXayDungLst}">
                                                    <option value="${cachThucXayDung.cach_xay_csht_id}" <c:choose> <c:when test="${cachThucXayDung.cach_xay_csht_id == tramQuyHoach.CACH_XAY_CSHT_ID}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${cachThucXayDung.ten_cach_xay_csht}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày được phê duyệt cấp vốn CSHT</label>
                                            <input type="text" class="form-control date_form"   name="ngay_duoc_pd_cap_von_csht" id="ngay_duoc_pd_cap_von_csht"
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.ngay_duoc_pd_cap_von_csht}" />"  />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin đất</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-md-6">
                                    <div class="form-group" >
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Loại đất</label>
                                            <select name="LOAI_DAT_ID" id="LOAI_DAT_ID" class="form-control"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="loaiDat" items="${loaiDatLst}">
                                                    <option value="${loaiDat.loai_dat_id}" <c:choose> <c:when test="${loaiDat.loai_dat_id == tramQuyHoach.LOAI_DAT_ID}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${loaiDat.ten_loai_dat}</option>
                                                </c:forEach>
                                            </select>  
                                        </div> 
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày cấp/thuê đất</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_CAP_DAT" id="NGAY_CAP_DAT" placeholder="Ngày cấp/thuê đất" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_CAP_DAT}" />"  />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Thông tin nhà trạm</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày xin phép xây dựng nhà trạm</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_XIN_PHEP_XD_NHA_TRAM" id="NGAY_XIN_PHEP_XD_NHA_TRAM" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_XIN_PHEP_XD_NHA_TRAM}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày hoàn thành thủ tục xây </label>
                                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_THU_TUC" id="NGAY_HOAN_THANH_THU_TUC" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_THU_TUC}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày khởi công xây dựng </label>
                                            <input type="text" class="form-control date_form"   name="NGAY_KHOI_CONG_XD_NHA_TRAM" id="NGAY_KHOI_CONG_XD_NHA_TRAM"  
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_KHOI_CONG_XD_NHA_TRAM}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày hoàn thành xây </label>
                                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_XD_NHA_TRAM" id="NGAY_HOAN_THANH_XD_NHA_TRAM"  
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_XD_NHA_TRAM}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group" >   
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Loại nhà trạm</label>
                                            <select name="LOAI_NHA_TRAM_ID" id="LOAI_NHA_TRAM_ID" class="form-control"   >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="loaiNhaTram" items="${loaiNhaTramLst}">
                                                    <option value="${loaiNhaTram.loai_nha_tram_id}" <c:choose> 
                                                                <c:when test="${loaiNhaTram.loai_nha_tram_id == tramQuyHoach.LOAI_NHA_TRAM_ID}">
                                                                    selected    
                                                                </c:when>    
                                                            </c:choose> >${loaiNhaTram.ten_loai_nha_tram}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>     
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group" >   
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tình trạng nhà trạm</label>
                                            <select name="tinh_trang_nha_tram" id="tinh_trang_nha_tram" class="form-control"   >
                                                <option value="">--Chọn--</option>
                                                <option value="1" <c:if test="${tramQuyHoach.tinh_trang_nha_tram == '1'}"> selected="true" </c:if> >OK </option>               
                                                <option value="0" <c:if test="${tramQuyHoach.tinh_trang_nha_tram == '0'}"> selected="true" </c:if>>NOK </option>
                                                </select>  
                                            </div>     
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Cột Antena</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Ngày xin phép độ cao cột</label>
                                                <input type="text" class="form-control date_form"   name="NGAY_XIN_PHEP_DO_CAO_COT" id="NGAY_XIN_PHEP_DO_CAO_COT"  
                                                       value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_XIN_PHEP_DO_CAO_COT}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày cấp phép độ cao cột</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_CAP_PHEP_DO_CAO_COT" id="NGAY_CAP_PHEP_DO_CAO_COT"  
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_CAP_PHEP_DO_CAO_COT}" />"  />
                                        </div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày hoàn thành thủ tục xây dựng cột</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_THU_TUC_XD_COT" id="NGAY_HOAN_THANH_THU_TUC_XD_COT" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_THU_TUC_XD_COT}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày khởi công dựng cột</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_KHOI_CONG_XD_COT" id="NGAY_KHOI_CONG_XD_COT"  
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_KHOI_CONG_XD_COT}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày hoàn thành cột</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_XD_COT" id="NGAY_HOAN_THANH_XD_COT" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_XD_COT}" />"  />
                                        </div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group" > 
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Loại cột</label>
                                            <select name="LOAI_COT_ANTEN_ID" id="tramQuyHoach" class="form-control"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="loaiCot" items="${loaiCotLst}">
                                                    <option value="${loaiCot.loai_cot_anten_id}" <c:choose> <c:when test="${loaiCot.loai_cot_anten_id == tramQuyHoach.LOAI_COT_ANTEN_ID}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${loaiCot.loai_cot_anten}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>
                                    </div>     
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Độ cao cột</label>
                                            <input type="number" class="form-control"   name="DO_CAO_COT" id="DO_CAO_COT"  value="${tramQuyHoach.DO_CAO_COT}" />
                                        </div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Độ cao chân cột so với mực nước biển</label>
                                            <input type="number" class="form-control"   name="DO_CAO_VI_TRI_XAY_COT_ANTTEN" id="DO_CAO_VI_TRI_XAY_COT_ANTTEN"  value="${tramQuyHoach.DO_CAO_VI_TRI_XAY_COT_ANTTEN}" />
                                        </div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group" >   
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tình trạng cột anten</label>
                                            <select name="tinh_trang_cot_anten" id="tinh_trang_cot_anten" class="form-control"   >
                                                <option value="">--Chọn--</option>
                                                <option value="1" <c:if test="${tramQuyHoach.tinh_trang_cot_anten == '1'}"> selected="true" </c:if> >OK </option>               
                                                <option value="0" <c:if test="${tramQuyHoach.tinh_trang_cot_anten == '0'}"> selected="true" </c:if>>NOK </option>
                                                </select>  
                                            </div>     
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Truyền dẫn</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="col-md-6">
                                        <div class="form-group" > 
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Phương thức truyền dẫn</label>
                                                <select name="LOAI_TRUYEN_DAN_ID" id="LOAI_TRUYEN_DAN_ID" class="form-control"  >
                                                    <option value="">-Chọn-</option>
                                                <c:forEach var="loaiTruyenDan" items="${loaiTruyenDanLst}">
                                                    <option value="${loaiTruyenDan.id}" <c:choose> <c:when test="${loaiTruyenDan.id == tramQuyHoach.LOAI_TRUYEN_DAN_ID}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${loaiTruyenDan.name}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>
                                    </div>     
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Giao diện truyền dẫn E1</label>
                                            <input type="number" class="form-control"   name="GIAODIEN_TD_E1" id="GIAODIEN_TD_E1"  value="${tramQuyHoach.GIAODIEN_TD_E1}" />
                                        </div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group" > 
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Giao diện truyền dẫn FE</label>
                                            <select name="GIAODIEN_TD_FE_ID" id="GIAODIEN_TD_FE_ID" class="form-control"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="gdTruyenDan" items="${gdTruyenDanLst}">
                                                    <option value="${gdTruyenDan.giaodien_td_id}" <c:choose> <c:when test="${gdTruyenDan.giaodien_td_id == tramQuyHoach.GIAODIEN_TD_FE_ID}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${gdTruyenDan.ten_giaodien_td}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>
                                    </div>     
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group" > 
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Giao diện truyền dẫn GE</label>
                                            <select name="GIAODIEN_TD_GE_ID" id="GIAODIEN_TD_GE_ID" class="form-control"  >
                                                <option value="">-Chọn-</option>
                                                <c:forEach var="gdTruyenDan" items="${gdTruyenDanLst}">
                                                    <option value="${gdTruyenDan.giaodien_td_id}" <c:choose> <c:when test="${gdTruyenDan.giaodien_td_id == tramQuyHoach.GIAODIEN_TD_GE_ID}">
                                                            selected    
                                                            </c:when>    
                                                        </c:choose> >${gdTruyenDan.ten_giaodien_td}</option>
                                                </c:forEach>
                                            </select>  
                                        </div>
                                    </div>     
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Giao diện truyền dẫn STM1</label>
                                            <input type="number" class="form-control"   name="GIAODIEN_TD_STM1" id="GIAODIEN_TD_STM1"  value="${tramQuyHoach.GIAODIEN_TD_STM1}" />
                                        </div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày khởi công truyền dẫn</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_KHOI_CONG_TD" id="NGAY_KHOI_CONG_TD" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_KHOI_CONG_TD}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày hoàn thành truyền dẫn</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_TD" id="NGAY_HOAN_THANH_TD" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_TD}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày đáp ứng điện AC</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_DAP_UNG_DIEN_AC" id="NGAY_DAP_UNG_DIEN_AC" 
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_DAP_UNG_DIEN_AC}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group" >   
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Tình trạng truyền dẫn</label>
                                            <select name="tinh_trang_truyen_dan" id="tinh_trang_truyen_dan" class="form-control"   >
                                                <option value="">--Chọn--</option>
                                                <option value="1" <c:if test="${tramQuyHoach.tinh_trang_truyen_dan == '1'}"> selected="true" </c:if> >OK </option>               
                                                <option value="0" <c:if test="${tramQuyHoach.tinh_trang_truyen_dan == '0'}"> selected="true" </c:if>>NOK </option>
                                                </select>  
                                            </div>     
                                        </div>
                                    </div>
                                </div>             
                            </div>

                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Phụ trợ</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="col-md-6">
                                        <div class="form-group" > 
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Hệ thống điện trong nhà trạm</label>
                                                <select name="HE_THONG_DIEN_NHA_TRAM_ID" id="HE_THONG_DIEN_NHA_TRAM_ID" class="form-control"  >
                                                    <option value="">-Chọn-</option>
                                                    <option  value="0">Chưa có</option>
                                                    <option value="1">Đã có</option>
                                                </select>  
                                            </div>
                                        </div> 
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group" > 
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Hệ thống điều hòa</label>
                                                <select name="HE_THONG_DIEU_HOA_ID" id="HE_THONG_DIEU_HOA_ID" class="form-control"  >
                                                    <option value="">-Chọn-</option>
                                                    <option  value="0">Chưa có</option>
                                                    <option value="1">Đã có</option>
                                                </select>  
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group" > 
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Hệ thống tiếp đất</label>
                                                <select name="HE_THONG_TIEP_DAT_ID" id="HE_THONG_TIEP_DAT_ID" class="form-control"  >
                                                    <option value="">-Chọn-</option>
                                                    <option  value="0">Chưa có</option>
                                                    <option value="1">Đã có</option>
                                                </select>  
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group" > 
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Máy nổ</label>
                                                <select name="HE_THONG_MAY_NO_ID" id="HE_THONG_MAY_NO_ID" class="form-control"  >
                                                    <option value="">-Chọn-</option>
                                                    <option  value="0">Chưa có</option>
                                                    <option value="1">Đã có</option>
                                                </select>  
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class=" input-group-addon" >Ngày hoàn thành phụ trợ</label>
                                                <input type="text" class="form-control date_form"   name="NGAY_HOAN_THANH_PHU_TRO" id="NGAY_HOAN_THANH_PHU_TRO" 
                                                       value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_HOAN_THANH_PHU_TRO}" />"  />
                                        </div>
                                    </div>
                                </div>
                            </div>     
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Văn bản thông báo hoàn thành CSHT</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Đối tượng thông báo</label>
                                            <input type="text" class="form-control"   name="DOI_TUONG_THONG_BAO" id="DOI_TUONG_THONG_BAO"  value="${tramQuyHoach.DOI_TUONG_THONG_BAO}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Số hiệu thông báo</label>
                                            <input type="text" class="form-control"   name="SO_HIEU_THONG_BAO" id="SO_HIEU_THONG_BAO"  value="${tramQuyHoach.SO_HIEU_THONG_BAO}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ngày thông báo hoàn thành CSHT</label>
                                            <input type="text" class="form-control date_form"   name="NGAY_THONG_BAO_HT_CSHT" id="NGAY_THONG_BAO_HT_CSHT"  
                                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tramQuyHoach.NGAY_THONG_BAO_HT_CSHT}" />"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Khó khăn vướng mắc</label>
                                            <input type="text" class="form-control"   name="KHO_KHAN_VUONG_MAC_CSHT" id="KHO_KHAN_VUONG_MAC_CSHT"  value="${tramQuyHoach.KHO_KHAN_VUONG_MAC_CSHT}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Đánh giá của Netx</label>
                                            <input type="text" class="form-control"   name="csht_danh_gia_netx" id="csht_danh_gia_netx"  value="${tramQuyHoach.csht_danh_gia_netx}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label class=" input-group-addon" >Ý kiến đánh giá của Netx</label>
                                            <input type="text" class="form-control"   name="csht_y_kien_netx" id="csht_y_kien_netx"  value="${tramQuyHoach.csht_y_kien_netx}" />
                                        </div>
                                    </div>
                                </div>
                            </div>             
                        </div>

                        <div class="box-footer">
                            <button type="submit"  class="btn btn-success"  >${btnName}</button>
                        </div>
                        </from>
                </div>
            </div>
        </div>
    </div>
</section>
<link href="${pageContext.request.contextPath}/resources/css/daterangepicker/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<%@include file="../../include/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/daterangepicker/bootstrap-datepicker.min.js"></script>

<script>
                                                function getListHuyen(tinh)
                                                {
                                                    var id = $("#tinhTpId").val();
                                                    if (tinh !== 0)
                                                        id = tinh;
                                                    $.get("${pageContext.request.contextPath}/dv/getHuyen/" + id, function (data) {
                                                        var html = '<option value="" >--- Quận / Huyện ---</option>';
                                                        if (data.length > 0) {
                                                            data.forEach(function (entry) {
                                                                var htmlx = '<option value="' + entry.quanHuyenId + '">' + entry.tenQuanHuyen + '</option>';
                                                                html += htmlx;

                                                            });

                                                        }
                                                        ;
                                                        $('#quanHuyenId').html(html);
                                                        if (tinh !== 0)
                                                            $('#quanHuyenId').val(${tramQuyHoach.quanHuyenId});
                                                    });
                                                }

                                                function getListPhuongXa(huyen)
                                                {

                                                    var id = $("#quanHuyenId").val();
                                                    if (huyen != 0)
                                                        id = huyen;
                                                    $.get("${pageContext.request.contextPath}/dv/getPhuong/" + id, function (data) {
                                                        var html = '<option value="">--- Phường / Xã ---</option>';
                                                        if (data.length > 0) {
                                                            data.forEach(function (entry) {
                                                                var htmlx = '<option value="' + entry.phuongXaId + '">' + entry.tenPhuongXa + '</option>';
                                                                html += htmlx;
                                                            });
                                                        }
                                                        ;
                                                        $('#phuongXaId').html(html);
                                                        if (huyen != 0)
                                                            $('#phuongXaId').val(${tramQuyHoach.phuongXaId});
                                                    });
                                                }

                                                $(document).ready(function () {
                                                    $('#tramQhId').val(${tramQuyHoach.tramQhId});
                                                    var tinhId = $("#tinhTpId").val();
                                                    if (tinhId != '')
                                                    {
                                                        getListHuyen(${tramQuyHoach.tinhTpId});
                                                        getListPhuongXa(${tramQuyHoach.quanHuyenId});
                                                    }
                                                    $('#HE_THONG_DIEN_NHA_TRAM_ID').val(${tramQuyHoach.HE_THONG_DIEN_NHA_TRAM_ID});
                                                    $('#HE_THONG_DIEU_HOA_ID').val(${tramQuyHoach.HE_THONG_DIEU_HOA_ID});
                                                    $('#HE_THONG_TIEP_DAT_ID').val(${tramQuyHoach.HE_THONG_TIEP_DAT_ID});
                                                    $('#HE_THONG_MAY_NO_ID').val(${tramQuyHoach.HE_THONG_MAY_NO_ID});

                                                    $('#NGAY_CAP_DAT').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });

                                                    $('#NGAY_XIN_PHEP_XD_NHA_TRAM').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_HOAN_THANH_THU_TUC').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_KHOI_CONG_XD_NHA_TRAM').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_HOAN_THANH_XD_NHA_TRAM').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_XIN_PHEP_DO_CAO_COT').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_CAP_PHEP_DO_CAO_COT').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_HOAN_THANH_THU_TUC_XD_COT').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_KHOI_CONG_XD_COT').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_HOAN_THANH_XD_COT').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_KHOI_CONG_TD').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_HOAN_THANH_TD').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_HOAN_THANH_PHU_TRO').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_THONG_BAO_HT_CSHT').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                    $('#NGAY_DAP_UNG_DIEN_AC').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });

                                                    $('#ngay_duoc_pd_cap_von_csht').datepicker({
                                                        format: 'dd/mm/yyyy',
                                                        todayHighlight: true,
                                                        autoclose: true
                                                    });
                                                });



</script>
