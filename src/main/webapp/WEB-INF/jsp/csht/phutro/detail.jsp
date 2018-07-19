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
                
            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Chung CSHT</label>
                <input type="number" value="${model.chungCsht}" class="form-control" name="chungCsht" placeholder="Chung CSHT"  />                    
            </div> 

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Loại CSHT</label>
                <select name="loaiCSHT" id="loaiCSHT" class="form-control" >
                    <option value="">--- Chọn CSHT--</option>
                    <c:forEach var="loaiNhaTram" items="${loaiNhaTramLst}">
                        <option value="${loaiNhaTram.loai_nha_tram_id}" <c:if test="${loaiNhaTram.loai_nha_tram_id == model.loaiTramCsht}">
                                selected    
                            </c:if>> ${loaiNhaTram.ten_loai_nha_tram}</option>
                    </c:forEach>

                </select>
            </div>

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Loại trạm CSHT</label>
                <input type="number" value="${model.loaiTramCsht}" class="form-control" name="loaiTramCsht" placeholder="Loại trạm CSHT"  />                    
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
                <label for="exampleInputEmail1">Loại cột ĂnTen</label>
                <select name="loaiCotAnTen" id="loaiCotAnTen" class="form-control"  >
                    <c:forEach var="temp" items="${loaicotantenList}">
                        <option  
                            <c:if test='${model.loaiCotAnTen == temp.loai_cot_anten_id}' >  selected="selected" </c:if>
                            value="${temp.loai_cot_anten_id}"

                            >${temp.loai_cot_anten}</option>
                    </c:forEach>

                </select>

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày hoạt động tủ nguồn</label>
                <input value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                value="${model.ngayHDTuNguon}" />"   type="text" class="form-control date_form" id="ngayHDTuNguon" name="ngayHDTuNguon"  />                    
                <script>
                    $(document).ready(function () {
                        $('#ngayHDTuNguon').datepicker({
                            format: 'dd/mm/yyyy',
                            todayHighlight: true,
                            autoclose: true
                        });
                    });
                </script>            
            </div> 

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Chủng loại tủ nguồn</label>
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
                <label for="exampleInputEmail1">Dòng cung cấp của tủ nguồn (A)</label>
                <input  value="${model.dongCungCapTuNguon}" type="number" class="form-control" placeholder="Dòng cung cấp của tủ nguồn (A)" name="dongCungCapTuNguon"  />                    

            </div>    

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Số module tủ nguồn</label>
                <input  value="${model.soModuleTuNguon}" type="number" class="form-control" placeholder="Số module tủ nguồn" name="soModuleTuNguon"  />                    

            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Dòng tiêu thụ của tủ nguồn (A)</label>
                <input  value="${model.dongTieuThuTuNguon}" type="number" class="form-control" placeholder="Dòng tiêu thụ của tủ nguồn (A)" name="dongTieuThuTuNguon"  />                    
            </div> 

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày hoạt động máy nổ</label>
                <input  value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                value="${model.ngayHDMayNo}" />" type="text" class="form-control date_form" id="ngayHDMayNo" name="ngayHDMayNo"  />                    
                <script>
                    $(document).ready(function () {
                        $('#ngayHDMayNo').datepicker({
                            format: 'dd/mm/yyyy',
                            todayHighlight: true,
                            autoclose: true
                        });
                    });
                </script>            
            </div> 

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Chủng loại máy nổ</label>
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
                <label for="exampleInputEmail1">Loại hình máy nổ</label>
                <input value="${model.loaiHinhMayNo}"  type="text" step="any" class="form-control" id="loaiHinhMayNo" placeholder="Loại hình máy nổ" name="loaiHinhMayNo"  />                    

            </div>


            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Công suất máy nổ (KVA)</label>
                <input value="${model.congSuatMayNo}"  type="number" step="any" class="form-control" id="congSuatMayNo" placeholder="Công suất máy nổ" name="congSuatMayNo"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Trạng thái đặt máy nổ</label>
                <select name="trangThaiMayNo" id="trangThaiMayNo" class="form-control" required >
                    <option   <c:if test='${model.trangThaiMayNo == "CĐ"}' >  selected="selected" </c:if> value="CĐ">Cố định</option>
                    <option  <c:if test='${model.trangThaiMayNo == "DĐ"}' >  selected="selected" </c:if> value="DĐ">Di động</option>
                    <option  <c:if test='${model.trangThaiMayNo == "KC"}' >  selected="selected" </c:if> value="KC">Không có</option>
                    </select>

                </div> 
                <div class="form-group" class="form-control">
                    <label for="exampleInputEmail1">Ngày hoạt động Accu</label>
                    <input value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                value="${model.ngayHDAccu}" />"  type="text" class="form-control date_form" id="ngayHDAccu" name="ngayHDAccu"  />                    
                <script>
                    $(document).ready(function () {
                        $('#ngayHDAccu').datepicker({
                            format: 'dd/mm/yyyy',
                            todayHighlight: true,
                            autoclose: true
                        });
                    });
                </script>            
            </div> 
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Chủng loại Accu</label>
                <select name="loaiAcQuyId" id="loaiAcQuyId" class="form-control" required >
                    <option value="">--- Chọn chủng loại Accu---</option>
                    <c:forEach var="temp" items="${loaiacquyList}">
                        <option  
                            <c:if test='${model.loaiAcQuyId == temp.id}' >  selected="selected" </c:if>
                            value="${temp.id}"

                            >${temp.name}</option>
                    </c:forEach>

                </select>

            </div>

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Dung lượng Accu(AH)</label>
                <input value="${model.dungLuongAccu}" type="text" class="form-control" placeholder="Dung lượng Accu" name="dungLuongAccu"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Điện áp ACCU/bình (V)</label>
                <input value="${model.dienApAccu}" type="text" class="form-control" placeholder="Điện áp ACCU/bình (V)" id="dienApAccu" name="dienApAccu"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Số lượng tổ ACCU/Số bình accu trong 1 tổ</label>
                <input value="${model.slAccuBinh}" type="text" class="form-control" placeholder="Số lượng tổ ACCU/Số bình" id="dienApAccu" name="slAccuBinh"  />                    

            </div>

            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Thời gian hoạt động sau mất điện (giờ)</label>
                <input step="any" value="${model.thoigianHDSauMatDien}" type="number" class="form-control" placeholder="0" name="thoigianHDSauMatDien"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Ngày bảo dưỡng Accu</label>
                <input value="<fmt:formatDate pattern="dd/MM/yyyy" 
                                value="${model.ngayBaoDuongAccu}" />"   type="text" class="form-control date_form" id="ngayBDAccu" name="ngayBaoDuongAccu"  />                    
                <script>
                    $(document).ready(function () {
                        $('#ngayBDAccu').datepicker({
                            format: 'dd/mm/yyyy',
                            todayHighlight: true,
                            autoclose: true
                        });
                    });
                </script>

            </div>


            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Phương Thức Truyền dẫn</label>
                <select name="loaiTruyenDanId" id="loaiTruyenDanId" class="form-control" required >
                    <option value="">--- Chọn loại phương thức truyền dẫn---</option>
                    <c:forEach var="temp" items="${loaitruyendanList}">
                        <option  
                            <c:if test='${model.loaiTruyenDanId == temp.id}' >  selected="selected" </c:if>
                            value="${temp.id}"

                            >${temp.name}</option>
                    </c:forEach>

                </select>

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Giao Diện Truyền Dẫn</label>
                <input type="text" value="${model.giaoDienTruyenDan}"  class="form-control" placeholder="Giao Diện Truyền Dẫn"  name="giaoDienTruyenDan"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Dung lượng Truyền Dẫn</label>
                <input type="number" value="${model.dungLuongTruyenDan}"  class="form-control"  name="dungLuongTruyenDan"  />                    

            </div>
            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Điện trở tiếp đất</label>
                <input step="any" value="${model.dienTroTiepDia}" type="number" class="form-control"  name="dienTroTiepDia"  />                    

            </div>



            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">SL Điều Hòa</label>
                <input type="number" value="${model.slDieuHoa}" class="form-control" name="slDieuHoa" placeholder="SL Điều Hòa"  />                    
            </div> 


            <div class="form-group" class="form-control">
                <label for="exampleInputEmail1">Tổng Công Suất Điều Hòa</label>
                <input type="number" value="${model.csDieuHoa}" class="form-control" name="csDieuHoa" placeholder="Tổng Công Suất Điều Hòa"  />                    
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
