package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class TramDuAnBO {

    private Long id;
    private Long duAnId;
    private Long tramQHId;
    private String name;
    private String code;
    private String address;
    private String maDuAn;
    private String tenDuAn;
    private String tenQuyHoach;
    private String maQuyHoach;
    private String maSoHopDong;
    private String maTramDuAn;
    private String tenTramDuAn;
    private String maTramBTS;
    private String maTramNodeB;
    private String maTramQuyHoach;
    private String hienTrangTram;
    private String hienTrangTramName;
    private String longitude;
    private String latitude;
    private Long quanHuyenId;
    private String tenQuanHuyen;
    private Long tinhTpId;
    private String tenTinhTp;
    private Long trangThaiCsht;
//cam ket thiet bi
    private Long vnptNetPheDuyet;
    private String cauHinhThietBi;
    private String cauHinhThietBiName;
    private Long nguonThietBi;
    private String nguongThietBiName;
    private Long loaiCongNghe;
    private String loaiCongNgheName;
    private String chungLoaiThietBi;
    private String chungLoaiAnten;

    private String strNguonThietBi;
    private String strLoaiCongNghe;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayCungCapTb;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngaySwapTb;
//cam ket ha tang
    private String longitudeKhaoSat;
    private String latitudeKhaoSat;
    private Long nhaTram;
    private Long cotAnten;
    private Long cauCapNgoai;
    private Long tuNguon;
    private String strTuNguon;

    private String dungLuongTuNguon;
    private String soModuleTuNguon;
    private Long chungLoaiAccu;
    private String chungLoaiAccuName;
    private String dungLuongAccu;
    private String soLuongToAccu;
    private Long truyenDan;
    private Long dieuHoa;
    private Long dienAc;
    private Long dienAcNoiTram;
    private Long duDkLapEnodeb;
    private Long capMoiTuNguonDc;
    private Long capMoiAccu;
    private String swapAnten;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHoanThanhKs;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayGuiSoLieu;
    private String dauMoiNhanThietBi;
    private String dauMoiQLCSHT;
    private String donViLapDat;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDuDkLapDatThietBi;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHTLapDatAnten;

//trang thai trien khai netx
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayPheDuyetKhaoSat;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayTiepNhanTruyenDan;
//trang thai trien khai
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayXuatAntenThucTe;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date keHoachXuatThietBi;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayXuatThietBiThucTe;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayTiepNhanTb;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date keHoachTbDenSite;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date keHoachLapDat;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayBatDauLapDat;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHTLapDatTb;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date keHoachHoaMang;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayHoaMangThucTe;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date keHoachPhatSongCt;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayPhatSongCt;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date keHoachNghiemThu;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayNghiemThu;
    private String dauMoiVnptNet;
    private String donViVanChuyen;
    private String ghiChu;
    private Long trangThaiTram;
    private String tenTrangThaiTram;
    private Long trangThaiTramAction;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public Long getTrangThaiCsht() {
        return trangThaiCsht;
    }

    public void setTrangThaiCsht(Long trangThaiCsht) {
        this.trangThaiCsht = trangThaiCsht;
    }

    public Date getNgayCungCapTb() {
        return ngayCungCapTb;
    }

    public void setNgayCungCapTb(Date ngayCungCapTb) {
        this.ngayCungCapTb = ngayCungCapTb;
    }

    public Date getNgaySwapTb() {
        return ngaySwapTb;
    }

    public void setNgaySwapTb(Date ngaySwapTb) {
        this.ngaySwapTb = ngaySwapTb;
    }

    public Date getNgayXuatAntenThucTe() {
        return ngayXuatAntenThucTe;
    }

    public void setNgayXuatAntenThucTe(Date ngayXuatAntenThucTe) {
        this.ngayXuatAntenThucTe = ngayXuatAntenThucTe;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStrNguonThietBi() {
        return strNguonThietBi;
    }

    public void setStrNguonThietBi(String strNguonThietBi) {
        this.strNguonThietBi = strNguonThietBi;
    }

    public String getStrLoaiCongNghe() {
        return strLoaiCongNghe;
    }

    public void setStrLoaiCongNghe(String strLoaiCongNghe) {
        this.strLoaiCongNghe = strLoaiCongNghe;
    }

    public String getStrTuNguon() {
        return strTuNguon;
    }

    public void setStrTuNguon(String strTuNguon) {
        this.strTuNguon = strTuNguon;
    }

    public String getTenTrangThaiTram() {
        return tenTrangThaiTram;
    }

    public void setTenTrangThaiTram(String tenTrangThaiTram) {
        this.tenTrangThaiTram = tenTrangThaiTram;
    }

    public Long getTrangThaiTramAction() {
        return trangThaiTramAction;
    }

    public void setTrangThaiTramAction(Long trangThaiTramAction) {
        this.trangThaiTramAction = trangThaiTramAction;
    }

    public Date getNgayHTLapDatTb() {
        return ngayHTLapDatTb;
    }

    public void setNgayHTLapDatTb(Date ngayHTLapDatTb) {
        this.ngayHTLapDatTb = ngayHTLapDatTb;
    }

    public Long getVnptNetPheDuyet() {
        return vnptNetPheDuyet;
    }

    public void setVnptNetPheDuyet(Long vnptNetPheDuyet) {
        this.vnptNetPheDuyet = vnptNetPheDuyet;
    }

    public String getCauHinhThietBi() {
        return cauHinhThietBi;
    }

    public void setCauHinhThietBi(String cauHinhThietBi) {
        this.cauHinhThietBi = cauHinhThietBi;
    }

    public Long getNguonThietBi() {
        return nguonThietBi;
    }

    public void setNguonThietBi(Long nguonThietBi) {
        this.nguonThietBi = nguonThietBi;
    }

    public Long getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(Long loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    public String getChungLoaiThietBi() {
        return chungLoaiThietBi;
    }

    public void setChungLoaiThietBi(String chungLoaiThietBi) {
        this.chungLoaiThietBi = chungLoaiThietBi;
    }

    public String getChungLoaiAnten() {
        return chungLoaiAnten;
    }

    public void setChungLoaiAnten(String chungLoaiAnten) {
        this.chungLoaiAnten = chungLoaiAnten;
    }

    public Long getNhaTram() {
        return nhaTram;
    }

    public void setNhaTram(Long nhaTram) {
        this.nhaTram = nhaTram;
    }

    public Long getCotAnten() {
        return cotAnten;
    }

    public void setCotAnten(Long cotAnten) {
        this.cotAnten = cotAnten;
    }

    public Long getCauCapNgoai() {
        return cauCapNgoai;
    }

    public void setCauCapNgoai(Long cauCapNgoai) {
        this.cauCapNgoai = cauCapNgoai;
    }

    public Long getTuNguon() {
        return tuNguon;
    }

    public void setTuNguon(Long tuNguon) {
        this.tuNguon = tuNguon;
    }

    public String getDungLuongTuNguon() {
        return dungLuongTuNguon;
    }

    public void setDungLuongTuNguon(String dungLuongTuNguon) {
        this.dungLuongTuNguon = dungLuongTuNguon;
    }

    public String getSoModuleTuNguon() {
        return soModuleTuNguon;
    }

    public void setSoModuleTuNguon(String soModuleTuNguon) {
        this.soModuleTuNguon = soModuleTuNguon;
    }

    public Long getChungLoaiAccu() {
        return chungLoaiAccu;
    }

    public void setChungLoaiAccu(Long chungLoaiAccu) {
        this.chungLoaiAccu = chungLoaiAccu;
    }

    public String getDungLuongAccu() {
        return dungLuongAccu;
    }

    public void setDungLuongAccu(String dungLuongAccu) {
        this.dungLuongAccu = dungLuongAccu;
    }

    public String getSoLuongToAccu() {
        return soLuongToAccu;
    }

    public void setSoLuongToAccu(String soLuongToAccu) {
        this.soLuongToAccu = soLuongToAccu;
    }

    public Long getTruyenDan() {
        return truyenDan;
    }

    public void setTruyenDan(Long truyenDan) {
        this.truyenDan = truyenDan;
    }

    public Long getDieuHoa() {
        return dieuHoa;
    }

    public void setDieuHoa(Long dieuHoa) {
        this.dieuHoa = dieuHoa;
    }

    public Long getDienAc() {
        return dienAc;
    }

    public void setDienAc(Long dienAc) {
        this.dienAc = dienAc;
    }

    public Long getDienAcNoiTram() {
        return dienAcNoiTram;
    }

    public void setDienAcNoiTram(Long dienAcNoiTram) {
        this.dienAcNoiTram = dienAcNoiTram;
    }

    public Long getDuDkLapEnodeb() {
        return duDkLapEnodeb;
    }

    public void setDuDkLapEnodeb(Long duDkLapEnodeb) {
        this.duDkLapEnodeb = duDkLapEnodeb;
    }

    public Long getCapMoiTuNguonDc() {
        return capMoiTuNguonDc;
    }

    public void setCapMoiTuNguonDc(Long capMoiTuNguonDc) {
        this.capMoiTuNguonDc = capMoiTuNguonDc;
    }

    public Long getCapMoiAccu() {
        return capMoiAccu;
    }

    public void setCapMoiAccu(Long capMoiAccu) {
        this.capMoiAccu = capMoiAccu;
    }

    public String getSwapAnten() {
        return swapAnten;
    }

    public void setSwapAnten(String swapAnten) {
        this.swapAnten = swapAnten;
    }

    public Date getNgayHoanThanhKs() {
        return ngayHoanThanhKs;
    }

    public void setNgayHoanThanhKs(Date ngayHoanThanhKs) {
        this.ngayHoanThanhKs = ngayHoanThanhKs;
    }

    public Date getNgayGuiSoLieu() {
        return ngayGuiSoLieu;
    }

    public void setNgayGuiSoLieu(Date ngayGuiSoLieu) {
        this.ngayGuiSoLieu = ngayGuiSoLieu;
    }

    public String getDauMoiNhanThietBi() {
        return dauMoiNhanThietBi;
    }

    public void setDauMoiNhanThietBi(String dauMoiNhanThietBi) {
        this.dauMoiNhanThietBi = dauMoiNhanThietBi;
    }

    public String getDauMoiQLCSHT() {
        return dauMoiQLCSHT;
    }

    public void setDauMoiQLCSHT(String dauMoiQLCSHT) {
        this.dauMoiQLCSHT = dauMoiQLCSHT;
    }

    public String getDonViLapDat() {
        return donViLapDat;
    }

    public void setDonViLapDat(String donViLapDat) {
        this.donViLapDat = donViLapDat;
    }

    public Date getNgayPheDuyetKhaoSat() {
        return ngayPheDuyetKhaoSat;
    }

    public void setNgayPheDuyetKhaoSat(Date ngayPheDuyetKhaoSat) {
        this.ngayPheDuyetKhaoSat = ngayPheDuyetKhaoSat;
    }

    public Date getNgayTiepNhanTruyenDan() {
        return ngayTiepNhanTruyenDan;
    }

    public void setNgayTiepNhanTruyenDan(Date ngayTiepNhanTruyenDan) {
        this.ngayTiepNhanTruyenDan = ngayTiepNhanTruyenDan;
    }

    public Date getKeHoachXuatThietBi() {
        return keHoachXuatThietBi;
    }

    public void setKeHoachXuatThietBi(Date keHoachXuatThietBi) {
        this.keHoachXuatThietBi = keHoachXuatThietBi;
    }

    public Date getNgayXuatThietBiThucTe() {
        return ngayXuatThietBiThucTe;
    }

    public void setNgayXuatThietBiThucTe(Date ngayXuatThietBiThucTe) {
        this.ngayXuatThietBiThucTe = ngayXuatThietBiThucTe;
    }

    public Date getNgayTiepNhanTb() {
        return ngayTiepNhanTb;
    }

    public void setNgayTiepNhanTb(Date ngayTiepNhanTb) {
        this.ngayTiepNhanTb = ngayTiepNhanTb;
    }

    public Date getKeHoachTbDenSite() {
        return keHoachTbDenSite;
    }

    public void setKeHoachTbDenSite(Date keHoachTbDenSite) {
        this.keHoachTbDenSite = keHoachTbDenSite;
    }

    public Date getKeHoachLapDat() {
        return keHoachLapDat;
    }

    public void setKeHoachLapDat(Date keHoachLapDat) {
        this.keHoachLapDat = keHoachLapDat;
    }

    public Date getNgayBatDauLapDat() {
        return ngayBatDauLapDat;
    }

    public void setNgayBatDauLapDat(Date ngayBatDauLapDat) {
        this.ngayBatDauLapDat = ngayBatDauLapDat;
    }

    public Date getKeHoachHoaMang() {
        return keHoachHoaMang;
    }

    public void setKeHoachHoaMang(Date keHoachHoaMang) {
        this.keHoachHoaMang = keHoachHoaMang;
    }

    public Date getNgayHoaMangThucTe() {
        return ngayHoaMangThucTe;
    }

    public void setNgayHoaMangThucTe(Date ngayHoaMangThucTe) {
        this.ngayHoaMangThucTe = ngayHoaMangThucTe;
    }

    public Date getKeHoachPhatSongCt() {
        return keHoachPhatSongCt;
    }

    public void setKeHoachPhatSongCt(Date keHoachPhatSongCt) {
        this.keHoachPhatSongCt = keHoachPhatSongCt;
    }

    public Date getNgayPhatSongCt() {
        return ngayPhatSongCt;
    }

    public void setNgayPhatSongCt(Date ngayPhatSongCt) {
        this.ngayPhatSongCt = ngayPhatSongCt;
    }

    public Date getKeHoachNghiemThu() {
        return keHoachNghiemThu;
    }

    public void setKeHoachNghiemThu(Date keHoachNghiemThu) {
        this.keHoachNghiemThu = keHoachNghiemThu;
    }

    public Date getNgayNghiemThu() {
        return ngayNghiemThu;
    }

    public void setNgayNghiemThu(Date ngayNghiemThu) {
        this.ngayNghiemThu = ngayNghiemThu;
    }

    public String getDauMoiVnptNet() {
        return dauMoiVnptNet;
    }

    public void setDauMoiVnptNet(String dauMoiVnptNet) {
        this.dauMoiVnptNet = dauMoiVnptNet;
    }

    public String getDonViVanChuyen() {
        return donViVanChuyen;
    }

    public void setDonViVanChuyen(String donViVanChuyen) {
        this.donViVanChuyen = donViVanChuyen;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Long getTrangThaiTram() {
        return trangThaiTram;
    }

    public void setTrangThaiTram(Long trangThaiTram) {
        this.trangThaiTram = trangThaiTram;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getTenTinhTp() {
        return tenTinhTp;
    }

    public void setTenTinhTp(String tenTinhTp) {
        this.tenTinhTp = tenTinhTp;
    }

    public Long getQuanHuyenId() {
        return quanHuyenId;
    }

    public void setQuanHuyenId(Long quanHuyenId) {
        this.quanHuyenId = quanHuyenId;
    }

    public Long getTinhTpId() {
        return tinhTpId;
    }

    public void setTinhTpId(Long tinhTpId) {
        this.tinhTpId = tinhTpId;
    }

    public Long getDuAnId() {
        return duAnId;
    }

    public void setDuAnId(Long duAnId) {
        this.duAnId = duAnId;
    }

    public Long getTramQHId() {
        return tramQHId;
    }

    public void setTramQHId(Long tramQHId) {
        this.tramQHId = tramQHId;
    }

    public String getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(String maDuAn) {
        this.maDuAn = maDuAn;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public String getTenQuyHoach() {
        return tenQuyHoach;
    }

    public void setTenQuyHoach(String tenQuyHoach) {
        this.tenQuyHoach = tenQuyHoach;
    }

    public String getMaQuyHoach() {
        return maQuyHoach;
    }

    public void setMaQuyHoach(String maQuyHoach) {
        this.maQuyHoach = maQuyHoach;
    }

    public String getMaSoHopDong() {
        return maSoHopDong;
    }

    public void setMaSoHopDong(String maSoHopDong) {
        this.maSoHopDong = maSoHopDong;
    }

    public String getMaTramDuAn() {
        return maTramDuAn;
    }

    public void setMaTramDuAn(String maTramDuAn) {
        this.maTramDuAn = maTramDuAn;
    }

    public String getTenTramDuAn() {
        return tenTramDuAn;
    }

    public void setTenTramDuAn(String tenTramDuAn) {
        this.tenTramDuAn = tenTramDuAn;
    }

    public String getMaTramBTS() {
        return maTramBTS;
    }

    public void setMaTramBTS(String maTramBTS) {
        this.maTramBTS = maTramBTS;
    }

    public String getMaTramNodeB() {
        return maTramNodeB;
    }

    public void setMaTramNodeB(String maTramNodeB) {
        this.maTramNodeB = maTramNodeB;
    }

    public String getMaTramQuyHoach() {
        return maTramQuyHoach;
    }

    public void setMaTramQuyHoach(String maTramQuyHoach) {
        this.maTramQuyHoach = maTramQuyHoach;
    }

    public String getHienTrangTram() {
        return hienTrangTram;
    }

    public void setHienTrangTram(String hienTrangTram) {
        this.hienTrangTram = hienTrangTram;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitudeKhaoSat() {
        return longitudeKhaoSat;
    }

    public void setLongitudeKhaoSat(String longitudeKhaoSat) {
        this.longitudeKhaoSat = longitudeKhaoSat;
    }

    public String getLatitudeKhaoSat() {
        return latitudeKhaoSat;
    }

    public void setLatitudeKhaoSat(String latitudeKhaoSat) {
        this.latitudeKhaoSat = latitudeKhaoSat;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public Date getNgayDuDkLapDatThietBi() {
        return ngayDuDkLapDatThietBi;
    }

    public void setNgayDuDkLapDatThietBi(Date ngayDuDkLapDatThietBi) {
        this.ngayDuDkLapDatThietBi = ngayDuDkLapDatThietBi;
    }

    public Date getNgayHTLapDatAnten() {
        return ngayHTLapDatAnten;
    }

    public void setNgayHTLapDatAnten(Date ngayHTLapDatAnten) {
        this.ngayHTLapDatAnten = ngayHTLapDatAnten;
    }

    public String getNguongThietBiName() {
        return nguongThietBiName;
    }

    public void setNguongThietBiName(String nguongThietBiName) {
        this.nguongThietBiName = nguongThietBiName;
    }

    public String getLoaiCongNgheName() {
        return loaiCongNgheName;
    }

    public void setLoaiCongNgheName(String loaiCongNgheName) {
        this.loaiCongNgheName = loaiCongNgheName;
    }

    public String getChungLoaiAccuName() {
        return chungLoaiAccuName;
    }

    public void setChungLoaiAccuName(String chungLoaiAccuName) {
        this.chungLoaiAccuName = chungLoaiAccuName;
    }

    public String getHienTrangTramName() {
        return hienTrangTramName;
    }

    public void setHienTrangTramName(String hienTrangTramName) {
        this.hienTrangTramName = hienTrangTramName;
    }

    public String getCauHinhThietBiName() {
        return cauHinhThietBiName;
    }

    public void setCauHinhThietBiName(String cauHinhThietBiName) {
        this.cauHinhThietBiName = cauHinhThietBiName;
    }

}
