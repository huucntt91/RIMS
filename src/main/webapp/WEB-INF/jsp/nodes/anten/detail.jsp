<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="box-body" id="mydiv">                        
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Thông tin hạ tầng</h3>
            <input type="hidden" name="id" value="${model.id}" />
        </div>
        <div class="panel-body">
            <div class="form-group">  
                <label for="exampleInputEmail1">Nhập mã building</label>
                <input required type="text" value="${model.code}"  class="form-control" id="codeBuilding" placeholder="Mã building" disabled  /> 
                <input type="hidden" name="buildingId" value="${model.buildingId}" id="buildingId" required />
                <span class="input-group-btn">
                    <button  class="btn btn-success" data-toggle="modal" data-target="#myBuilding" id="btn_building" >Tìm building</button>
                </span>
            </div>

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Loại tủ nguồn</label>
                <select name="loaiTuNguonId" id="loaiTuNguonId" class="form-control" required >
                    <option value="">--- Chọn loại tủ nguồn ---</option>
                    <c:forEach var="temp" items="${tunguonList}">
                        <option  
                            <c:if test='${model.loaiTuNguonId == temp.id}' >  selected="selected" </c:if>
                            value="${temp.id}"

                            >${temp.name}</option>
                    </c:forEach>

                </select>

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày hoạt động tủ nguồn</label>
                <input value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                value="${model.ngayHDTuNguon}" />"   type="text" class="form-control date_form" id="ngayHDTuNguon" name="ngayHDTuNguon"  />                    
                       
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Số module tủ nguồn</label>
                <input  value="${model.soModuleTuNguon}" type="number" class="form-control" placeholder="Số module tủ nguồn" name="soModuleTuNguon"  />                    

            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Loại máy nổ</label>
                <select name="loaiMayNoId" id="loaiMayNoId" class="form-control" required >
                    <option value="">--- Chọn loại máy nổ ---</option>
                    <c:forEach var="temp" items="${loaimaynoList}">
                        <option  
                            <c:if test='${model.loaiMayNoId == temp.id}' >  selected="selected" </c:if>
                            value="${temp.id}"

                            >${temp.name}</option>
                    </c:forEach>

                </select>

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày hoạt động máy nổ</label>
                <input  value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                value="${model.ngayHDMayNo}" />" type="text" class="form-control date_form" id="ngayHDMayNo" name="ngayHDMayNo"  />                    
                          
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Công suất máy nổ</label>
                <input value="${model.congSuatMayNo}"  type="number" step="any" class="form-control" id="congSuatMayNo" placeholder="Công suất máy nổ" name="congSuatMayNo"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Trạng thái đặt máy nổ</label>
                <select name="trangThaiMayNo" id="trangThaiMayNo" class="form-control" required >
                    <option   <c:if test='${model.trangThaiMayNo == 0}' >  selected="selected" </c:if> value="0">Cố định</option>
                    <option  <c:if test='${model.trangThaiMayNo == 1}' >  selected="selected" </c:if> value="1">Di động</option>
                    </select>

                </div> 

                <div class="form-group" class="form-control">
                    <label for="exampleInputEmail1">Loại Accu</label>
                    <select name="loaiAcQuyId" id="loaiAcQuyId" class="form-control" required >
                        <option value="">--- Chọn loại Accu---</option>
                    <c:forEach var="temp" items="${loaiacquyList}">
                        <option  
                            <c:if test='${model.loaiAcQuyId == temp.id}' >  selected="selected" </c:if>
                            value="${temp.id}"

                            >${temp.name}</option>
                    </c:forEach>

                </select>

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày hoạt động Accu</label>
                <input value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                value="${model.ngayHDAccu}" />"  type="text" class="form-control date_form" id="ngayHDAccu" name="ngayHDAccu"  />                    
                      
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Dung lượng Accu</label>
                <input step="any" value="${model.dungLuongAccu}" type="number" class="form-control" placeholder="Dung lượng Accu" id="dungLuongAccu" name="dungLuongAccu"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Thời gian hoạt động sau mất điện (giờ)</label>
                <input step="any" value="${model.thoigianHDSauMatDien}" type="number" class="form-control" placeholder="0" name="thoigianHDSauMatDien"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày bảo dưỡng Accu</label>
                <input value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                value="${model.ngayBaoDuongAccu}" />"   type="text" class="form-control date_form" id="ngayBDAccu" name="ngayBaoDuongAccu"  />                    
              

            </div>


            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Loại Truyền dẫn</label>
                <select name="loaiTruyenDanId" id="loaiTruyenDanId" class="form-control" required >
                    <option value="">--- Chọn loại truyền dẫn---</option>
                    <c:forEach var="temp" items="${loaitruyendanList}">
                        <option  
                            <c:if test='${model.loaiTruyenDanId == temp.id}' >  selected="selected" </c:if>
                            value="${temp.id}"

                            >${temp.name}</option>
                    </c:forEach>

                </select>

            </div>

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Dung lượng Truyền Dẫn</label>
                <input step="any" value="${model.dungLuongTruyenDan}"  type="number" class="form-control"  name="dungLuongTruyenDan"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Điện trở tiếp đất</label>
                <input step="any" value="${model.dienTroTiepDia}" type="number" class="form-control"  name="dienTroTiepDia"  />                    

            </div>

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Loại Ănten</label>
                <select name="loaiAnTenId" id="loaiAnTenId" class="form-control" required >
                    <option value="">--- Chọn loại Ăn ten---</option>
                    <c:forEach var="temp" items="${loaiantenList}">
                        <option  
                            <c:if test='${model.loaiAnTenId == temp.id}' >  selected="selected" </c:if>
                            value="${temp.id}"

                            >${temp.name}</option>
                    </c:forEach>

                </select>

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Độ cao Ănten</label>
                <input step="any" value="${model.doCaoAnTen}" type="number" class="form-control" id="doCaoAnTen"  name="doCaoAnTen"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Độ cao nhà đặt Ănten</label>
                <input step="any" value="${model.doCaoNhaDatAnTen}" type="number" class="form-control" id="doCaoNhaDatAnTen"  name="doCaoNhaDatAnTen"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Chung CSHT</label>
                <input type="text" value="${model.chungCsht}" class="form-control" name="chungCsht" placeholder="Chung CSHT"  />                    
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Loại trạm CSHT</label>
                <select name="loaiTramCsht" id="loaiTramCsht" class="form-control" >
                    <option value="">--- Chọn loại CSHT--</option>
                    <c:forEach var="loaiNhaTram" items="${loaiNhaTramLst}">
                        <option value="${loaiNhaTram.loai_nha_tram_id}" <c:if test="${loaiNhaTram.loai_nha_tram_id == model.loaiTramCsht}">
                                selected    
                            </c:if> ${loaiNhaTram.ten_loai_nha_tram}</option>
                    </c:forEach>

                </select>

            </div>


        </div>
    </div>
</div>    

<script>
    $('.panel-body').each(function () {
        $(this).find('input').attr('disabled', 'disabled');
        $(this).find('select').attr('disabled', 'disabled');
    });
</script> 
