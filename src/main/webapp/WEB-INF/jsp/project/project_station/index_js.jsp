<%-- 
    Document   : index_js
    Created on : Jan 11, 2017, 11:15:21 AM
    Author     : Cyano
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    function activaTab(tab) {
        //show tab
        $('.nav-tabs a[href="#menu' + tab + '"]').tab('show');
    }
    ;
   

    function initCamKetThietBi(maTramQuyHoach, tenTramQuyHoach, tinhTpId, loaiCongNgheId) {
        $.get('${pageContext.request.contextPath}/project_station/initCamKetThietBi?MA_QUY_HOACH=' + maTramQuyHoach + '&TEN_QUY_HOACH=' + tenTramQuyHoach + '&ID_LOAI_CONG_NGHE=' + loaiCongNgheId + '&TINH_TP_ID=' + tinhTpId, function (data) {
            $('#tbody1').empty();
            for (var key in data) {
                if (data.hasOwnProperty(key)) {
                    var row = $('<tr>' +
                            '<td>' + key + '</td>' +
                            '<td>' + data[key].maQh + '</td>' +
                            '<td>' + data[key].tenQh + '</td>' +
                            '<td>' + (data[key].dvi_TRACH_NHIEM_CCTB == null ? '' : data[key].dvi_TRACH_NHIEM_CCTB) + '</td>' +
                            '<td>' + (data[key].nguon_THIET_BI == null ? '' : data[key].nguon_THIET_BI) + '</td>' +
                            '<td>' + (data[key].thoi_DIEM_DAP_UNG_DU_KIEN == null ? '' : data[key].thoi_DIEM_DAP_UNG_DU_KIEN) + '</td>' +
                            '<td>' + (data[key].ten_CONG_NGHE_DAP_UNG == null ? '' : data[key].ten_CONG_NGHE_DAP_UNG) + '</td>' +
                            '<td>' + (data[key].chung_LOAI_THIET_BI == null ? '' : data[key].chung_LOAI_THIET_BI) + '</td>' +
                            '<td>' + (data[key].thoi_DIEM_DAP_UNG_THUC_TE == null ? '' : data[key].thoi_DIEM_DAP_UNG_THUC_TE) + '</td>' +
                            '<td>' + (data[key].kho_KHAN_VUONG_MAC == null ? '' : data[key].kho_KHAN_VUONG_MAC) + '</td>' +
                            '</tr>');
                    var td = $('<td>' + '</td>');
                    var a = $('<a href="<%=request.getContextPath()%>/project_station/viewCamKet/' + data[key].tramQhId + '" title="Cập nhật">'
                            + '<img src="<%=request.getContextPath()%>/image/icon/edit_icon.png"/>' + '</a>');
                    td.append(a);
                    row.append(td);
                    $('#tbody1').append(row);
                }
            }
            
        });
    }
    ;
</script>
