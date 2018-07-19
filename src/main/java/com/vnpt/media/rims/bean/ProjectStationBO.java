/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Cyano
 */
public class ProjectStationBO {

    //thong tin chung
    Integer tramQhId;
    Integer qhTinhId;
    Integer tramDaId;
    String maQh;
    String tenQh;
    Integer buildingId;
    String buildingCode;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date namKhoiTao;
    Float longitude;
    Float latitude;
    
    
    Integer loaiCongNgheId;
    String loaiCongNghe;
    Integer bangTanId;
    String bangTan;
    Integer loaiPtCshtId;
    String ptCsht;
    Integer trangThaiCshtId;
    String trangThaiCsht;
    Integer donViPheDuyet;
    String donviPheDuyet;
    String soHieuVanBan;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngayPheDuyet;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngayDieuChinhGanNhat;
    Integer donViDieuChinhId;
    String donViDieuChinh;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngayPhatSong;
    //thong tin cam ket
    Integer DVI_TRACH_NHIEM_CCTB_ID;
    String DVI_TRACH_NHIEM_CCTB;
    Integer NGUON_THIET_BI_ID;
    String NGUON_THIET_BI;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date THOI_DIEM_DAP_UNG_DU_KIEN;
    Integer CONG_NGHE_DAP_UNG;
    String TEN_CONG_NGHE_DAP_UNG;
    String CHUNG_LOAI_THIET_BI;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date THOI_DIEM_DAP_UNG_THUC_TE;
    String KHO_KHAN_VUONG_MAC;
    //thong tin co so ha tang
    Integer DVI_TRACH_NHIEM_CSHT_ID;
    String DVI_TRACH_NHIEM_CSHT;
    Integer tinhTpId;
    String  tinhTp;
    Integer quanHuyenId;
    String quanHuyen;
    Integer phuongXaId;
    String phuongXa;
    String DIA_CHI;
    String TEN_TRAM;
    Integer CACH_XAY_CSHT_ID;
    String CACH_XAY_CSHT;
    Integer LOAI_DAT_ID;
    String LOAI_DAT;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_CAP_DAT;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_XIN_PHEP_XD_NHA_TRAM;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_HOAN_THANH_THU_TUC;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_KHOI_CONG_XD_NHA_TRAM;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_HOAN_THANH_XD_NHA_TRAM;
    Integer LOAI_NHA_TRAM_ID;
    String LOAI_NHA_TRAM;
   
    //Cot antena
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_XIN_PHEP_DO_CAO_COT;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_CAP_PHEP_DO_CAO_COT;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_HOAN_THANH_THU_TUC_XD_COT;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_KHOI_CONG_XD_COT;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_HOAN_THANH_XD_COT;
    Integer LOAI_COT_ANTEN_ID;
    String LOAI_COT_ANTEN;
    Integer DO_CAO_COT;
    Integer DO_CAO_VI_TRI_XAY_COT_ANTTEN;
    //truyen dan
    Integer LOAI_TRUYEN_DAN_ID;
    String LOAI_TRUYEN_DAN;
    Integer GIAODIEN_TD_E1;
    Integer GIAODIEN_TD_FE_ID;
    String GIAODIEN_TD_FE;
    Integer GIAODIEN_TD_GE_ID;
    String GIAODIEN_TD_GE;
    Integer GIAODIEN_TD_STM1;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_KHOI_CONG_TD;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_HOAN_THANH_TD;
    //dien AC
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_DAP_UNG_DIEN_AC;
    //phu tro
    Integer HE_THONG_DIEN_NHA_TRAM_ID;
    Integer HE_THONG_DIEU_HOA_ID;
    Integer HE_THONG_TIEP_DAT_ID;
    Integer HE_THONG_MAY_NO_ID;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_HOAN_THANH_PHU_TRO;
    //van ban thong bao hoan thanh CSHT
    String DOI_TUONG_THONG_BAO;
    String SO_HIEU_THONG_BAO;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_THONG_BAO_HT_CSHT;
    String KHO_KHAN_VUONG_MAC_CSHT;
    //nguon DC
    Integer DVI_TRACH_NHIEM_NGUON_DC_ID;
    Integer NGUON_THIET_BI_TU_NGUON_ID;
    Integer LOAI_TU_NGUON_ID;
    Integer DUNG_LUONG_TU_NGUON;
    Integer SO_RACTIFIER;
    Integer LOAI_AC_QUY_ID;
    Integer DUNG_LUONG_ACCU;
    Integer SO_LUONG_ACCU;
    Integer DIEN_AP_ACCU;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_DAP_UNG_NGUON_DC_DU_KIEN;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_DAP_UNG_NGUON_DC_TT;
//    trunglk_start
    String DVI_TRACH_NHIEM_TU_NGUON;
    String TU_NGUON_DC;
    String TEN_LOAI_TU_NGUON;
    //antena
    Integer DVI_TRACH_NHIEM_ANTEN;
    Integer LOAI_ANTEN_ID1;
    String TEN_ANTENA1;
    String HANG_SX_ANTENA1;
    Integer SO_LUONG_ANTENA1;
    Integer BANG_TANG_ANTENA_ID1;
    Integer CAU_HINH_PORT_ID1;
    String CAU_HINH_GAIN1;
    String CAU_HINH_BEAM_WIDTH1;
    Integer TRONG_LUONG1;
    Integer KICH_CO_CAO1;
    Integer KICH_CO_RONG1;
    Integer KICH_CO_SAU1;
    Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT1;
    Integer LOAI_ANTEN_ID2;
    String TEN_ANTENA2;
    String HANG_SX_ANTENA2;
    Integer SO_LUONG_ANTENA2;
    Integer BANG_TANG_ANTENA_ID2;
    Integer CAU_HINH_PORT_ID2;
    String CAU_HINH_GAIN2;
    String CAU_HINH_BEAM_WIDTH2;
    Integer TRONG_LUONG2;
    Integer KICH_CO_CAO2;
    Integer KICH_CO_RONG2;
    Integer KICH_CO_SAU2;
    Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT2;
    Integer LOAI_ANTEN_ID3;
    String TEN_ANTENA3;
    String HANG_SX_ANTENA3;
    Integer SO_LUONG_ANTENA3;
    Integer BANG_TANG_ANTENA_ID3;
    Integer CAU_HINH_PORT_ID3;
    String CAU_HINH_GAIN3;
    String CAU_HINH_BEAM_WIDTH3;
    Integer TRONG_LUONG3;
    Integer KICH_CO_CAO3;
    Integer KICH_CO_RONG3;
    Integer KICH_CO_SAU3;
    Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT3;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_DAP_UNG_ANTENA_DU_KIEN;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_DAP_UNG_ANTENA_THUC_TE;
    String TRANG_THAI_TRAM;
    String NOTE;
    //bo sung  column
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngay_duoc_pd_cap_von_csht;
    Integer tinh_trang_nha_tram;
    Integer tinh_trang_cot_anten;
    Integer tinh_trang_truyen_dan;
    Integer tinh_trang_nguon_dien;
    String csht_danh_gia_netx;
    String csht_y_kien_netx;

    public String getGIAODIEN_TD_FE() {
        return GIAODIEN_TD_FE;
    }

    public void setGIAODIEN_TD_FE(String GIAODIEN_TD_FE) {
        this.GIAODIEN_TD_FE = GIAODIEN_TD_FE;
    }

    public String getGIAODIEN_TD_GE() {
        return GIAODIEN_TD_GE;
    }

    public void setGIAODIEN_TD_GE(String GIAODIEN_TD_GE) {
        this.GIAODIEN_TD_GE = GIAODIEN_TD_GE;
    }
    
    

    public String getLOAI_COT_ANTEN() {
        return LOAI_COT_ANTEN;
    }

    public void setLOAI_COT_ANTEN(String LOAI_COT_ANTEN) {
        this.LOAI_COT_ANTEN = LOAI_COT_ANTEN;
    }

    public String getLOAI_TRUYEN_DAN() {
        return LOAI_TRUYEN_DAN;
    }

    public void setLOAI_TRUYEN_DAN(String LOAI_TRUYEN_DAN) {
        this.LOAI_TRUYEN_DAN = LOAI_TRUYEN_DAN;
    }

    
    
    public String getDVI_TRACH_NHIEM_CSHT() {
        return DVI_TRACH_NHIEM_CSHT;
    }
    
    

    public void setDVI_TRACH_NHIEM_CSHT(String DVI_TRACH_NHIEM_CSHT) {
        this.DVI_TRACH_NHIEM_CSHT = DVI_TRACH_NHIEM_CSHT;
    }
    
    

    public Date getNgay_duoc_pd_cap_von_csht() {
        return ngay_duoc_pd_cap_von_csht;
    }

    public void setNgay_duoc_pd_cap_von_csht(Date ngay_duoc_pd_cap_von_csht) {
        this.ngay_duoc_pd_cap_von_csht = ngay_duoc_pd_cap_von_csht;
    }

    public Integer getTinh_trang_nha_tram() {
        return tinh_trang_nha_tram;
    }

    public void setTinh_trang_nha_tram(Integer tinh_trang_nha_tram) {
        this.tinh_trang_nha_tram = tinh_trang_nha_tram;
    }

    public Integer getTinh_trang_cot_anten() {
        return tinh_trang_cot_anten;
    }

    public void setTinh_trang_cot_anten(Integer tinh_trang_cot_anten) {
        this.tinh_trang_cot_anten = tinh_trang_cot_anten;
    }

    public Integer getTinh_trang_truyen_dan() {
        return tinh_trang_truyen_dan;
    }

    public void setTinh_trang_truyen_dan(Integer tinh_trang_truyen_dan) {
        this.tinh_trang_truyen_dan = tinh_trang_truyen_dan;
    }

    public Integer getTinh_trang_nguon_dien() {
        return tinh_trang_nguon_dien;
    }

    public void setTinh_trang_nguon_dien(Integer tinh_trang_nguon_dien) {
        this.tinh_trang_nguon_dien = tinh_trang_nguon_dien;
    }

    public String getCsht_danh_gia_netx() {
        return csht_danh_gia_netx;
    }

    public void setCsht_danh_gia_netx(String csht_danh_gia_netx) {
        this.csht_danh_gia_netx = csht_danh_gia_netx;
    }

    public String getCsht_y_kien_netx() {
        return csht_y_kien_netx;
    }

    public void setCsht_y_kien_netx(String csht_y_kien_netx) {
        this.csht_y_kien_netx = csht_y_kien_netx;
    }
    
    


    public String getDVI_TRACH_NHIEM_TU_NGUON() {
        return DVI_TRACH_NHIEM_TU_NGUON;
    }

    public void setDVI_TRACH_NHIEM_TU_NGUON(String DVI_TRACH_NHIEM_TU_NGUON) {
        this.DVI_TRACH_NHIEM_TU_NGUON = DVI_TRACH_NHIEM_TU_NGUON;
    }

    public String getTU_NGUON_DC() {
        return TU_NGUON_DC;
    }

    public void setTU_NGUON_DC(String TU_NGUON_DC) {
        this.TU_NGUON_DC = TU_NGUON_DC;
    }

    public String getTEN_LOAI_TU_NGUON() {
        return TEN_LOAI_TU_NGUON;
    }

    public void setTEN_LOAI_TU_NGUON(String TEN_LOAI_TU_NGUON) {
        this.TEN_LOAI_TU_NGUON = TEN_LOAI_TU_NGUON;
    }
    
//    trunglk_end

    public ProjectStationBO() {
    }

    public Integer getTramQhId() {
        return tramQhId;
    }

    public void setTramQhId(Integer tramQhId) {
        this.tramQhId = tramQhId;
    }

    public Integer getQhTinhId() {
        return qhTinhId;
    }

    public void setQhTinhId(Integer qhTinhId) {
        this.qhTinhId = qhTinhId;
    }

    public Integer getTramDaId() {
        return tramDaId;
    }

    public void setTramDaId(Integer tramDaId) {
        this.tramDaId = tramDaId;
    }

    public String getMaQh() {
        return maQh;
    }

    public void setMaQh(String maQh) {
        this.maQh = maQh;
    }

    public String getTenQh() {
        return tenQh;
    }

    public void setTenQh(String tenQh) {
        this.tenQh = tenQh;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Date getNamKhoiTao() {
        return namKhoiTao;
    }

    public void setNamKhoiTao(Date namKhoiTao) {
        this.namKhoiTao = namKhoiTao;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getLoaiCongNgheId() {
        return loaiCongNgheId;
    }

    public void setLoaiCongNgheId(Integer loaiCongNgheId) {
        this.loaiCongNgheId = loaiCongNgheId;
    }

    public Integer getBangTanId() {
        return bangTanId;
    }

    public void setBangTanId(Integer bangTanId) {
        this.bangTanId = bangTanId;
    }

    public Integer getLoaiPtCshtId() {
        return loaiPtCshtId;
    }

    public void setLoaiPtCshtId(Integer loaiPtCshtId) {
        this.loaiPtCshtId = loaiPtCshtId;
    }

    public Integer getTrangThaiCshtId() {
        return trangThaiCshtId;
    }

    public void setTrangThaiCshtId(Integer trangThaiCshtId) {
        this.trangThaiCshtId = trangThaiCshtId;
    }

    public Integer getDonViPheDuyet() {
        return donViPheDuyet;
    }

    public void setDonViPheDuyet(Integer donViPheDuyet) {
        this.donViPheDuyet = donViPheDuyet;
    }

    public String getSoHieuVanBan() {
        return soHieuVanBan;
    }

    public void setSoHieuVanBan(String soHieuVanBan) {
        this.soHieuVanBan = soHieuVanBan;
    }

    public Date getNgayPheDuyet() {
        return ngayPheDuyet;
    }

    public void setNgayPheDuyet(Date ngayPheDuyet) {
        this.ngayPheDuyet = ngayPheDuyet;
    }

    public Date getNgayDieuChinhGanNhat() {
        return ngayDieuChinhGanNhat;
    }

    public void setNgayDieuChinhGanNhat(Date ngayDieuChinhGanNhat) {
        this.ngayDieuChinhGanNhat = ngayDieuChinhGanNhat;
    }

    public Integer getDonViDieuChinhId() {
        return donViDieuChinhId;
    }

    public void setDonViDieuChinhId(Integer donViDieuChinhId) {
        this.donViDieuChinhId = donViDieuChinhId;
    }

    public Date getNgayPhatSong() {
        return ngayPhatSong;
    }

    public void setNgayPhatSong(Date ngayPhatSong) {
        this.ngayPhatSong = ngayPhatSong;
    }

    public Integer getDVI_TRACH_NHIEM_CCTB_ID() {
        return DVI_TRACH_NHIEM_CCTB_ID;
    }

    public void setDVI_TRACH_NHIEM_CCTB_ID(Integer DVI_TRACH_NHIEM_CCTB_ID) {
        this.DVI_TRACH_NHIEM_CCTB_ID = DVI_TRACH_NHIEM_CCTB_ID;
    }

    public Integer getNGUON_THIET_BI_ID() {
        return NGUON_THIET_BI_ID;
    }

    public void setNGUON_THIET_BI_ID(Integer NGUON_THIET_BI_ID) {
        this.NGUON_THIET_BI_ID = NGUON_THIET_BI_ID;
    }

    public Date getTHOI_DIEM_DAP_UNG_DU_KIEN() {
        return THOI_DIEM_DAP_UNG_DU_KIEN;
    }

    public void setTHOI_DIEM_DAP_UNG_DU_KIEN(Date THOI_DIEM_DAP_UNG_DU_KIEN) {
        this.THOI_DIEM_DAP_UNG_DU_KIEN = THOI_DIEM_DAP_UNG_DU_KIEN;
    }

    public Integer getCONG_NGHE_DAP_UNG() {
        return CONG_NGHE_DAP_UNG;
    }

    public void setCONG_NGHE_DAP_UNG(Integer CONG_NGHE_DAP_UNG) {
        this.CONG_NGHE_DAP_UNG = CONG_NGHE_DAP_UNG;
    }

    public String getCHUNG_LOAI_THIET_BI() {
        return CHUNG_LOAI_THIET_BI;
    }

    public void setCHUNG_LOAI_THIET_BI(String CHUNG_LOAI_THIET_BI) {
        this.CHUNG_LOAI_THIET_BI = CHUNG_LOAI_THIET_BI;
    }

    public Date getTHOI_DIEM_DAP_UNG_THUC_TE() {
        return THOI_DIEM_DAP_UNG_THUC_TE;
    }

    public void setTHOI_DIEM_DAP_UNG_THUC_TE(Date THOI_DIEM_DAP_UNG_THUC_TE) {
        this.THOI_DIEM_DAP_UNG_THUC_TE = THOI_DIEM_DAP_UNG_THUC_TE;
    }

    public String getKHO_KHAN_VUONG_MAC() {
        return KHO_KHAN_VUONG_MAC;
    }

    public void setKHO_KHAN_VUONG_MAC(String KHO_KHAN_VUONG_MAC) {
        this.KHO_KHAN_VUONG_MAC = KHO_KHAN_VUONG_MAC;
    }

    public Integer getDVI_TRACH_NHIEM_CSHT_ID() {
        return DVI_TRACH_NHIEM_CSHT_ID;
    }

    public void setDVI_TRACH_NHIEM_CSHT_ID(Integer DVI_TRACH_NHIEM_CSHT_ID) {
        this.DVI_TRACH_NHIEM_CSHT_ID = DVI_TRACH_NHIEM_CSHT_ID;
    }

    public Integer getTinhTpId() {
        return tinhTpId;
    }

    public void setTinhTpId(Integer tinhTpId) {
        this.tinhTpId = tinhTpId;
    }

    public Integer getQuanHuyenId() {
        return quanHuyenId;
    }

    public void setQuanHuyenId(Integer quanHuyenId) {
        this.quanHuyenId = quanHuyenId;
    }

    public Integer getPhuongXaId() {
        return phuongXaId;
    }

    public void setPhuongXaId(Integer phuongXaId) {
        this.phuongXaId = phuongXaId;
    }

    public String getDIA_CHI() {
        return DIA_CHI;
    }

    public void setDIA_CHI(String DIA_CHI) {
        this.DIA_CHI = DIA_CHI;
    }

    public String getTEN_TRAM() {
        return TEN_TRAM;
    }

    public void setTEN_TRAM(String TEN_TRAM) {
        this.TEN_TRAM = TEN_TRAM;
    }

    public Integer getCACH_XAY_CSHT_ID() {
        return CACH_XAY_CSHT_ID;
    }

    public void setCACH_XAY_CSHT_ID(Integer CACH_XAY_CSHT_ID) {
        this.CACH_XAY_CSHT_ID = CACH_XAY_CSHT_ID;
    }

    public Integer getLOAI_DAT_ID() {
        return LOAI_DAT_ID;
    }

    public void setLOAI_DAT_ID(Integer LOAI_DAT_ID) {
        this.LOAI_DAT_ID = LOAI_DAT_ID;
    }

    public Date getNGAY_CAP_DAT() {
        return NGAY_CAP_DAT;
    }

    public void setNGAY_CAP_DAT(Date NGAY_CAP_DAT) {
        this.NGAY_CAP_DAT = NGAY_CAP_DAT;
    }

    public Date getNGAY_XIN_PHEP_XD_NHA_TRAM() {
        return NGAY_XIN_PHEP_XD_NHA_TRAM;
    }

    public void setNGAY_XIN_PHEP_XD_NHA_TRAM(Date NGAY_XIN_PHEP_XD_NHA_TRAM) {
        this.NGAY_XIN_PHEP_XD_NHA_TRAM = NGAY_XIN_PHEP_XD_NHA_TRAM;
    }

    public Date getNGAY_HOAN_THANH_THU_TUC() {
        return NGAY_HOAN_THANH_THU_TUC;
    }

    public void setNGAY_HOAN_THANH_THU_TUC(Date NGAY_HOAN_THANH_THU_TUC) {
        this.NGAY_HOAN_THANH_THU_TUC = NGAY_HOAN_THANH_THU_TUC;
    }

    public Date getNGAY_KHOI_CONG_XD_NHA_TRAM() {
        return NGAY_KHOI_CONG_XD_NHA_TRAM;
    }

    public void setNGAY_KHOI_CONG_XD_NHA_TRAM(Date NGAY_KHOI_CONG_XD_NHA_TRAM) {
        this.NGAY_KHOI_CONG_XD_NHA_TRAM = NGAY_KHOI_CONG_XD_NHA_TRAM;
    }

    public Date getNGAY_HOAN_THANH_XD_NHA_TRAM() {
        return NGAY_HOAN_THANH_XD_NHA_TRAM;
    }

    public void setNGAY_HOAN_THANH_XD_NHA_TRAM(Date NGAY_HOAN_THANH_XD_NHA_TRAM) {
        this.NGAY_HOAN_THANH_XD_NHA_TRAM = NGAY_HOAN_THANH_XD_NHA_TRAM;
    }

    public Integer getLOAI_NHA_TRAM_ID() {
        return LOAI_NHA_TRAM_ID;
    }

    public void setLOAI_NHA_TRAM_ID(Integer LOAI_NHA_TRAM_ID) {
        this.LOAI_NHA_TRAM_ID = LOAI_NHA_TRAM_ID;
    }

    public Date getNGAY_XIN_PHEP_DO_CAO_COT() {
        return NGAY_XIN_PHEP_DO_CAO_COT;
    }

    public void setNGAY_XIN_PHEP_DO_CAO_COT(Date NGAY_XIN_PHEP_DO_CAO_COT) {
        this.NGAY_XIN_PHEP_DO_CAO_COT = NGAY_XIN_PHEP_DO_CAO_COT;
    }

    public Date getNGAY_CAP_PHEP_DO_CAO_COT() {
        return NGAY_CAP_PHEP_DO_CAO_COT;
    }

    public void setNGAY_CAP_PHEP_DO_CAO_COT(Date NGAY_CAP_PHEP_DO_CAO_COT) {
        this.NGAY_CAP_PHEP_DO_CAO_COT = NGAY_CAP_PHEP_DO_CAO_COT;
    }

    public Date getNGAY_HOAN_THANH_THU_TUC_XD_COT() {
        return NGAY_HOAN_THANH_THU_TUC_XD_COT;
    }

    public void setNGAY_HOAN_THANH_THU_TUC_XD_COT(Date NGAY_HOAN_THANH_THU_TUC_XD_COT) {
        this.NGAY_HOAN_THANH_THU_TUC_XD_COT = NGAY_HOAN_THANH_THU_TUC_XD_COT;
    }

    public Date getNGAY_KHOI_CONG_XD_COT() {
        return NGAY_KHOI_CONG_XD_COT;
    }

    public void setNGAY_KHOI_CONG_XD_COT(Date NGAY_KHOI_CONG_XD_COT) {
        this.NGAY_KHOI_CONG_XD_COT = NGAY_KHOI_CONG_XD_COT;
    }

    public Date getNGAY_HOAN_THANH_XD_COT() {
        return NGAY_HOAN_THANH_XD_COT;
    }

    public void setNGAY_HOAN_THANH_XD_COT(Date NGAY_HOAN_THANH_XD_COT) {
        this.NGAY_HOAN_THANH_XD_COT = NGAY_HOAN_THANH_XD_COT;
    }

    public Integer getLOAI_COT_ANTEN_ID() {
        return LOAI_COT_ANTEN_ID;
    }

    public void setLOAI_COT_ANTEN_ID(Integer LOAI_COT_ANTEN_ID) {
        this.LOAI_COT_ANTEN_ID = LOAI_COT_ANTEN_ID;
    }

    public Integer getDO_CAO_COT() {
        return DO_CAO_COT;
    }

    public void setDO_CAO_COT(Integer DO_CAO_COT) {
        this.DO_CAO_COT = DO_CAO_COT;
    }

    public Integer getDO_CAO_VI_TRI_XAY_COT_ANTTEN() {
        return DO_CAO_VI_TRI_XAY_COT_ANTTEN;
    }

    public void setDO_CAO_VI_TRI_XAY_COT_ANTTEN(Integer DO_CAO_VI_TRI_XAY_COT_ANTTEN) {
        this.DO_CAO_VI_TRI_XAY_COT_ANTTEN = DO_CAO_VI_TRI_XAY_COT_ANTTEN;
    }

    public Integer getLOAI_TRUYEN_DAN_ID() {
        return LOAI_TRUYEN_DAN_ID;
    }

    public void setLOAI_TRUYEN_DAN_ID(Integer LOAI_TRUYEN_DAN_ID) {
        this.LOAI_TRUYEN_DAN_ID = LOAI_TRUYEN_DAN_ID;
    }

    public Integer getGIAODIEN_TD_E1() {
        return GIAODIEN_TD_E1;
    }

    public void setGIAODIEN_TD_E1(Integer GIAODIEN_TD_E1) {
        this.GIAODIEN_TD_E1 = GIAODIEN_TD_E1;
    }

    public Integer getGIAODIEN_TD_FE_ID() {
        return GIAODIEN_TD_FE_ID;
    }

    public void setGIAODIEN_TD_FE_ID(Integer GIAODIEN_TD_FE_ID) {
        this.GIAODIEN_TD_FE_ID = GIAODIEN_TD_FE_ID;
    }

    public Integer getGIAODIEN_TD_GE_ID() {
        return GIAODIEN_TD_GE_ID;
    }

    public void setGIAODIEN_TD_GE_ID(Integer GIAODIEN_TD_GE_ID) {
        this.GIAODIEN_TD_GE_ID = GIAODIEN_TD_GE_ID;
    }

    public Integer getGIAODIEN_TD_STM1() {
        return GIAODIEN_TD_STM1;
    }

    public void setGIAODIEN_TD_STM1(Integer GIAODIEN_TD_STM1) {
        this.GIAODIEN_TD_STM1 = GIAODIEN_TD_STM1;
    }

    public Date getNGAY_KHOI_CONG_TD() {
        return NGAY_KHOI_CONG_TD;
    }

    public void setNGAY_KHOI_CONG_TD(Date NGAY_KHOI_CONG_TD) {
        this.NGAY_KHOI_CONG_TD = NGAY_KHOI_CONG_TD;
    }

    public Date getNGAY_HOAN_THANH_TD() {
        return NGAY_HOAN_THANH_TD;
    }

    public void setNGAY_HOAN_THANH_TD(Date NGAY_HOAN_THANH_TD) {
        this.NGAY_HOAN_THANH_TD = NGAY_HOAN_THANH_TD;
    }

    public Date getNGAY_DAP_UNG_DIEN_AC() {
        return NGAY_DAP_UNG_DIEN_AC;
    }

    public void setNGAY_DAP_UNG_DIEN_AC(Date NGAY_DAP_UNG_DIEN_AC) {
        this.NGAY_DAP_UNG_DIEN_AC = NGAY_DAP_UNG_DIEN_AC;
    }

    public Integer getHE_THONG_DIEN_NHA_TRAM_ID() {
        return HE_THONG_DIEN_NHA_TRAM_ID;
    }

    public void setHE_THONG_DIEN_NHA_TRAM_ID(Integer HE_THONG_DIEN_NHA_TRAM_ID) {
        this.HE_THONG_DIEN_NHA_TRAM_ID = HE_THONG_DIEN_NHA_TRAM_ID;
    }

    public Integer getHE_THONG_DIEU_HOA_ID() {
        return HE_THONG_DIEU_HOA_ID;
    }

    public void setHE_THONG_DIEU_HOA_ID(Integer HE_THONG_DIEU_HOA_ID) {
        this.HE_THONG_DIEU_HOA_ID = HE_THONG_DIEU_HOA_ID;
    }

    public Integer getHE_THONG_TIEP_DAT_ID() {
        return HE_THONG_TIEP_DAT_ID;
    }

    public void setHE_THONG_TIEP_DAT_ID(Integer HE_THONG_TIEP_DAT_ID) {
        this.HE_THONG_TIEP_DAT_ID = HE_THONG_TIEP_DAT_ID;
    }

    public Integer getHE_THONG_MAY_NO_ID() {
        return HE_THONG_MAY_NO_ID;
    }

    public void setHE_THONG_MAY_NO_ID(Integer HE_THONG_MAY_NO_ID) {
        this.HE_THONG_MAY_NO_ID = HE_THONG_MAY_NO_ID;
    }

    public Date getNGAY_HOAN_THANH_PHU_TRO() {
        return NGAY_HOAN_THANH_PHU_TRO;
    }

    public void setNGAY_HOAN_THANH_PHU_TRO(Date NGAY_HOAN_THANH_PHU_TRO) {
        this.NGAY_HOAN_THANH_PHU_TRO = NGAY_HOAN_THANH_PHU_TRO;
    }

    public String getDOI_TUONG_THONG_BAO() {
        return DOI_TUONG_THONG_BAO;
    }

    public void setDOI_TUONG_THONG_BAO(String DOI_TUONG_THONG_BAO) {
        this.DOI_TUONG_THONG_BAO = DOI_TUONG_THONG_BAO;
    }

    public String getSO_HIEU_THONG_BAO() {
        return SO_HIEU_THONG_BAO;
    }

    public void setSO_HIEU_THONG_BAO(String SO_HIEU_THONG_BAO) {
        this.SO_HIEU_THONG_BAO = SO_HIEU_THONG_BAO;
    }

    public Date getNGAY_THONG_BAO_HT_CSHT() {
        return NGAY_THONG_BAO_HT_CSHT;
    }

    public void setNGAY_THONG_BAO_HT_CSHT(Date NGAY_THONG_BAO_HT_CSHT) {
        this.NGAY_THONG_BAO_HT_CSHT = NGAY_THONG_BAO_HT_CSHT;
    }

    public String getKHO_KHAN_VUONG_MAC_CSHT() {
        return KHO_KHAN_VUONG_MAC_CSHT;
    }

    public void setKHO_KHAN_VUONG_MAC_CSHT(String KHO_KHAN_VUONG_MAC_CSHT) {
        this.KHO_KHAN_VUONG_MAC_CSHT = KHO_KHAN_VUONG_MAC_CSHT;
    }

    public Integer getDVI_TRACH_NHIEM_NGUON_DC_ID() {
        return DVI_TRACH_NHIEM_NGUON_DC_ID;
    }

    public void setDVI_TRACH_NHIEM_NGUON_DC_ID(Integer DVI_TRACH_NHIEM_NGUON_DC_ID) {
        this.DVI_TRACH_NHIEM_NGUON_DC_ID = DVI_TRACH_NHIEM_NGUON_DC_ID;
    }

    public Integer getNGUON_THIET_BI_TU_NGUON_ID() {
        return NGUON_THIET_BI_TU_NGUON_ID;
    }

    public void setNGUON_THIET_BI_TU_NGUON_ID(Integer NGUON_THIET_BI_TU_NGUON_ID) {
        this.NGUON_THIET_BI_TU_NGUON_ID = NGUON_THIET_BI_TU_NGUON_ID;
    }

    public Integer getLOAI_TU_NGUON_ID() {
        return LOAI_TU_NGUON_ID;
    }

    public void setLOAI_TU_NGUON_ID(Integer LOAI_TU_NGUON_ID) {
        this.LOAI_TU_NGUON_ID = LOAI_TU_NGUON_ID;
    }

    public Integer getDUNG_LUONG_TU_NGUON() {
        return DUNG_LUONG_TU_NGUON;
    }

    public void setDUNG_LUONG_TU_NGUON(Integer DUNG_LUONG_TU_NGUON) {
        this.DUNG_LUONG_TU_NGUON = DUNG_LUONG_TU_NGUON;
    }

    public Integer getSO_RACTIFIER() {
        return SO_RACTIFIER;
    }

    public void setSO_RACTIFIER(Integer SO_RACTIFIER) {
        this.SO_RACTIFIER = SO_RACTIFIER;
    }

    public Integer getLOAI_AC_QUY_ID() {
        return LOAI_AC_QUY_ID;
    }

    public void setLOAI_AC_QUY_ID(Integer LOAI_AC_QUY_ID) {
        this.LOAI_AC_QUY_ID = LOAI_AC_QUY_ID;
    }

    public Integer getDUNG_LUONG_ACCU() {
        return DUNG_LUONG_ACCU;
    }

    public void setDUNG_LUONG_ACCU(Integer DUNG_LUONG_ACCU) {
        this.DUNG_LUONG_ACCU = DUNG_LUONG_ACCU;
    }

    public Integer getSO_LUONG_ACCU() {
        return SO_LUONG_ACCU;
    }

    public void setSO_LUONG_ACCU(Integer SO_LUONG_ACCU) {
        this.SO_LUONG_ACCU = SO_LUONG_ACCU;
    }

    public Integer getDIEN_AP_ACCU() {
        return DIEN_AP_ACCU;
    }

    public void setDIEN_AP_ACCU(Integer DIEN_AP_ACCU) {
        this.DIEN_AP_ACCU = DIEN_AP_ACCU;
    }

    public Date getNGAY_DAP_UNG_NGUON_DC_DU_KIEN() {
        return NGAY_DAP_UNG_NGUON_DC_DU_KIEN;
    }

    public void setNGAY_DAP_UNG_NGUON_DC_DU_KIEN(Date NGAY_DAP_UNG_NGUON_DC_DU_KIEN) {
        this.NGAY_DAP_UNG_NGUON_DC_DU_KIEN = NGAY_DAP_UNG_NGUON_DC_DU_KIEN;
    }

    public Date getNGAY_DAP_UNG_NGUON_DC_TT() {
        return NGAY_DAP_UNG_NGUON_DC_TT;
    }

    public void setNGAY_DAP_UNG_NGUON_DC_TT(Date NGAY_DAP_UNG_NGUON_DC_TT) {
        this.NGAY_DAP_UNG_NGUON_DC_TT = NGAY_DAP_UNG_NGUON_DC_TT;
    }

    public String getLoaiCongNghe() {
        return loaiCongNghe;
    }

    public void setLoaiCongNghe(String loaiCongNghe) {
        this.loaiCongNghe = loaiCongNghe;
    }

    public String getBangTan() {
        return bangTan;
    }

    public void setBangTan(String bangTan) {
        this.bangTan = bangTan;
    }

    public String getPtCsht() {
        return ptCsht;
    }

    public void setPtCsht(String ptCsht) {
        this.ptCsht = ptCsht;
    }

    public String getTrangThaiCsht() {
        return trangThaiCsht;
    }

    public void setTrangThaiCsht(String trangThaiCsht) {
        this.trangThaiCsht = trangThaiCsht;
    }

    public String getDonviPheDuyet() {
        return donviPheDuyet;
    }

    public void setDonviPheDuyet(String donviPheDuyet) {
        this.donviPheDuyet = donviPheDuyet;
    }

    public String getDonViDieuChinh() {
        return donViDieuChinh;
    }

    public void setDonViDieuChinh(String donViDieuChinh) {
        this.donViDieuChinh = donViDieuChinh;
    }

    public String getDVI_TRACH_NHIEM_CCTB() {
        return DVI_TRACH_NHIEM_CCTB;
    }

    public void setDVI_TRACH_NHIEM_CCTB(String DVI_TRACH_NHIEM_CCTB) {
        this.DVI_TRACH_NHIEM_CCTB = DVI_TRACH_NHIEM_CCTB;
    }

    public String getNGUON_THIET_BI() {
        return NGUON_THIET_BI;
    }

    public void setNGUON_THIET_BI(String NGUON_THIET_BI) {
        this.NGUON_THIET_BI = NGUON_THIET_BI;
    }

    public String getTEN_CONG_NGHE_DAP_UNG() {
        return TEN_CONG_NGHE_DAP_UNG;
    }

    public void setTEN_CONG_NGHE_DAP_UNG(String TEN_CONG_NGHE_DAP_UNG) {
        this.TEN_CONG_NGHE_DAP_UNG = TEN_CONG_NGHE_DAP_UNG;
    }

    public String getTinhTp() {
        return tinhTp;
    }

    public void setTinhTp(String tinhTp) {
        this.tinhTp = tinhTp;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getCACH_XAY_CSHT() {
        return CACH_XAY_CSHT;
    }

    public void setCACH_XAY_CSHT(String CACH_XAY_CSHT) {
        this.CACH_XAY_CSHT = CACH_XAY_CSHT;
    }

    public String getLOAI_DAT() {
        return LOAI_DAT;
    }

    public void setLOAI_DAT(String LOAI_DAT) {
        this.LOAI_DAT = LOAI_DAT;
    }

    public String getLOAI_NHA_TRAM() {
        return LOAI_NHA_TRAM;
    }

    public void setLOAI_NHA_TRAM(String LOAI_NHA_TRAM) {
        this.LOAI_NHA_TRAM = LOAI_NHA_TRAM;
    }

    public Integer getDVI_TRACH_NHIEM_ANTEN() {
        return DVI_TRACH_NHIEM_ANTEN;
    }

    public void setDVI_TRACH_NHIEM_ANTEN(Integer DVI_TRACH_NHIEM_ANTEN) {
        this.DVI_TRACH_NHIEM_ANTEN = DVI_TRACH_NHIEM_ANTEN;
    }

    public Integer getLOAI_ANTEN_ID1() {
        return LOAI_ANTEN_ID1;
    }

    public void setLOAI_ANTEN_ID1(Integer LOAI_ANTEN_ID1) {
        this.LOAI_ANTEN_ID1 = LOAI_ANTEN_ID1;
    }

    public String getTEN_ANTENA1() {
        return TEN_ANTENA1;
    }

    public void setTEN_ANTENA1(String TEN_ANTENA1) {
        this.TEN_ANTENA1 = TEN_ANTENA1;
    }

    public String getHANG_SX_ANTENA1() {
        return HANG_SX_ANTENA1;
    }

    public void setHANG_SX_ANTENA1(String HANG_SX_ANTENA1) {
        this.HANG_SX_ANTENA1 = HANG_SX_ANTENA1;
    }

    public Integer getSO_LUONG_ANTENA1() {
        return SO_LUONG_ANTENA1;
    }

    public void setSO_LUONG_ANTENA1(Integer SO_LUONG_ANTENA1) {
        this.SO_LUONG_ANTENA1 = SO_LUONG_ANTENA1;
    }

    public Integer getBANG_TANG_ANTENA_ID1() {
        return BANG_TANG_ANTENA_ID1;
    }

    public void setBANG_TANG_ANTENA_ID1(Integer BANG_TANG_ANTENA_ID1) {
        this.BANG_TANG_ANTENA_ID1 = BANG_TANG_ANTENA_ID1;
    }

    public Integer getCAU_HINH_PORT_ID1() {
        return CAU_HINH_PORT_ID1;
    }

    public void setCAU_HINH_PORT_ID1(Integer CAU_HINH_PORT_ID1) {
        this.CAU_HINH_PORT_ID1 = CAU_HINH_PORT_ID1;
    }

    public String getCAU_HINH_GAIN1() {
        return CAU_HINH_GAIN1;
    }

    public void setCAU_HINH_GAIN1(String CAU_HINH_GAIN1) {
        this.CAU_HINH_GAIN1 = CAU_HINH_GAIN1;
    }

    public String getCAU_HINH_BEAM_WIDTH1() {
        return CAU_HINH_BEAM_WIDTH1;
    }

    public void setCAU_HINH_BEAM_WIDTH1(String CAU_HINH_BEAM_WIDTH1) {
        this.CAU_HINH_BEAM_WIDTH1 = CAU_HINH_BEAM_WIDTH1;
    }

    public Integer getTRONG_LUONG1() {
        return TRONG_LUONG1;
    }

    public void setTRONG_LUONG1(Integer TRONG_LUONG1) {
        this.TRONG_LUONG1 = TRONG_LUONG1;
    }

    public Integer getKICH_CO_CAO1() {
        return KICH_CO_CAO1;
    }

    public void setKICH_CO_CAO1(Integer KICH_CO_CAO1) {
        this.KICH_CO_CAO1 = KICH_CO_CAO1;
    }

    public Integer getKICH_CO_RONG1() {
        return KICH_CO_RONG1;
    }

    public void setKICH_CO_RONG1(Integer KICH_CO_RONG1) {
        this.KICH_CO_RONG1 = KICH_CO_RONG1;
    }

    public Integer getKICH_CO_SAU1() {
        return KICH_CO_SAU1;
    }

    public void setKICH_CO_SAU1(Integer KICH_CO_SAU1) {
        this.KICH_CO_SAU1 = KICH_CO_SAU1;
    }

    public Integer getDO_CAO_ANTENA_SO_VOI_MAT_DAT1() {
        return DO_CAO_ANTENA_SO_VOI_MAT_DAT1;
    }

    public void setDO_CAO_ANTENA_SO_VOI_MAT_DAT1(Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT1) {
        this.DO_CAO_ANTENA_SO_VOI_MAT_DAT1 = DO_CAO_ANTENA_SO_VOI_MAT_DAT1;
    }

    public Integer getLOAI_ANTEN_ID2() {
        return LOAI_ANTEN_ID2;
    }

    public void setLOAI_ANTEN_ID2(Integer LOAI_ANTEN_ID2) {
        this.LOAI_ANTEN_ID2 = LOAI_ANTEN_ID2;
    }

    public String getTEN_ANTENA2() {
        return TEN_ANTENA2;
    }

    public void setTEN_ANTENA2(String TEN_ANTENA2) {
        this.TEN_ANTENA2 = TEN_ANTENA2;
    }

    public String getHANG_SX_ANTENA2() {
        return HANG_SX_ANTENA2;
    }

    public void setHANG_SX_ANTENA2(String HANG_SX_ANTENA2) {
        this.HANG_SX_ANTENA2 = HANG_SX_ANTENA2;
    }

    public Integer getSO_LUONG_ANTENA2() {
        return SO_LUONG_ANTENA2;
    }

    public void setSO_LUONG_ANTENA2(Integer SO_LUONG_ANTENA2) {
        this.SO_LUONG_ANTENA2 = SO_LUONG_ANTENA2;
    }

    public Integer getBANG_TANG_ANTENA_ID2() {
        return BANG_TANG_ANTENA_ID2;
    }

    public void setBANG_TANG_ANTENA_ID2(Integer BANG_TANG_ANTENA_ID2) {
        this.BANG_TANG_ANTENA_ID2 = BANG_TANG_ANTENA_ID2;
    }

    public Integer getCAU_HINH_PORT_ID2() {
        return CAU_HINH_PORT_ID2;
    }

    public void setCAU_HINH_PORT_ID2(Integer CAU_HINH_PORT_ID2) {
        this.CAU_HINH_PORT_ID2 = CAU_HINH_PORT_ID2;
    }

    public String getCAU_HINH_GAIN2() {
        return CAU_HINH_GAIN2;
    }

    public void setCAU_HINH_GAIN2(String CAU_HINH_GAIN2) {
        this.CAU_HINH_GAIN2 = CAU_HINH_GAIN2;
    }

    public String getCAU_HINH_BEAM_WIDTH2() {
        return CAU_HINH_BEAM_WIDTH2;
    }

    public void setCAU_HINH_BEAM_WIDTH2(String CAU_HINH_BEAM_WIDTH2) {
        this.CAU_HINH_BEAM_WIDTH2 = CAU_HINH_BEAM_WIDTH2;
    }

    public Integer getTRONG_LUONG2() {
        return TRONG_LUONG2;
    }

    public void setTRONG_LUONG2(Integer TRONG_LUONG2) {
        this.TRONG_LUONG2 = TRONG_LUONG2;
    }

    public Integer getKICH_CO_CAO2() {
        return KICH_CO_CAO2;
    }

    public void setKICH_CO_CAO2(Integer KICH_CO_CAO2) {
        this.KICH_CO_CAO2 = KICH_CO_CAO2;
    }

    public Integer getKICH_CO_RONG2() {
        return KICH_CO_RONG2;
    }

    public void setKICH_CO_RONG2(Integer KICH_CO_RONG2) {
        this.KICH_CO_RONG2 = KICH_CO_RONG2;
    }

    public Integer getKICH_CO_SAU2() {
        return KICH_CO_SAU2;
    }

    public void setKICH_CO_SAU2(Integer KICH_CO_SAU2) {
        this.KICH_CO_SAU2 = KICH_CO_SAU2;
    }

    public Integer getDO_CAO_ANTENA_SO_VOI_MAT_DAT2() {
        return DO_CAO_ANTENA_SO_VOI_MAT_DAT2;
    }

    public void setDO_CAO_ANTENA_SO_VOI_MAT_DAT2(Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT2) {
        this.DO_CAO_ANTENA_SO_VOI_MAT_DAT2 = DO_CAO_ANTENA_SO_VOI_MAT_DAT2;
    }

    public Integer getLOAI_ANTEN_ID3() {
        return LOAI_ANTEN_ID3;
    }

    public void setLOAI_ANTEN_ID3(Integer LOAI_ANTEN_ID3) {
        this.LOAI_ANTEN_ID3 = LOAI_ANTEN_ID3;
    }

    public String getTEN_ANTENA3() {
        return TEN_ANTENA3;
    }

    public void setTEN_ANTENA3(String TEN_ANTENA3) {
        this.TEN_ANTENA3 = TEN_ANTENA3;
    }

    public String getHANG_SX_ANTENA3() {
        return HANG_SX_ANTENA3;
    }

    public void setHANG_SX_ANTENA3(String HANG_SX_ANTENA3) {
        this.HANG_SX_ANTENA3 = HANG_SX_ANTENA3;
    }

    public Integer getSO_LUONG_ANTENA3() {
        return SO_LUONG_ANTENA3;
    }

    public void setSO_LUONG_ANTENA3(Integer SO_LUONG_ANTENA3) {
        this.SO_LUONG_ANTENA3 = SO_LUONG_ANTENA3;
    }

    public Integer getBANG_TANG_ANTENA_ID3() {
        return BANG_TANG_ANTENA_ID3;
    }

    public void setBANG_TANG_ANTENA_ID3(Integer BANG_TANG_ANTENA_ID3) {
        this.BANG_TANG_ANTENA_ID3 = BANG_TANG_ANTENA_ID3;
    }

    public Integer getCAU_HINH_PORT_ID3() {
        return CAU_HINH_PORT_ID3;
    }

    public void setCAU_HINH_PORT_ID3(Integer CAU_HINH_PORT_ID3) {
        this.CAU_HINH_PORT_ID3 = CAU_HINH_PORT_ID3;
    }

    public String getCAU_HINH_GAIN3() {
        return CAU_HINH_GAIN3;
    }

    public void setCAU_HINH_GAIN3(String CAU_HINH_GAIN3) {
        this.CAU_HINH_GAIN3 = CAU_HINH_GAIN3;
    }

    public String getCAU_HINH_BEAM_WIDTH3() {
        return CAU_HINH_BEAM_WIDTH3;
    }

    public void setCAU_HINH_BEAM_WIDTH3(String CAU_HINH_BEAM_WIDTH3) {
        this.CAU_HINH_BEAM_WIDTH3 = CAU_HINH_BEAM_WIDTH3;
    }

    public Integer getTRONG_LUONG3() {
        return TRONG_LUONG3;
    }

    public void setTRONG_LUONG3(Integer TRONG_LUONG3) {
        this.TRONG_LUONG3 = TRONG_LUONG3;
    }

    public Integer getKICH_CO_CAO3() {
        return KICH_CO_CAO3;
    }

    public void setKICH_CO_CAO3(Integer KICH_CO_CAO3) {
        this.KICH_CO_CAO3 = KICH_CO_CAO3;
    }

    public Integer getKICH_CO_RONG3() {
        return KICH_CO_RONG3;
    }

    public void setKICH_CO_RONG3(Integer KICH_CO_RONG3) {
        this.KICH_CO_RONG3 = KICH_CO_RONG3;
    }

    public Integer getKICH_CO_SAU3() {
        return KICH_CO_SAU3;
    }

    public void setKICH_CO_SAU3(Integer KICH_CO_SAU3) {
        this.KICH_CO_SAU3 = KICH_CO_SAU3;
    }

    public Integer getDO_CAO_ANTENA_SO_VOI_MAT_DAT3() {
        return DO_CAO_ANTENA_SO_VOI_MAT_DAT3;
    }

    public void setDO_CAO_ANTENA_SO_VOI_MAT_DAT3(Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT3) {
        this.DO_CAO_ANTENA_SO_VOI_MAT_DAT3 = DO_CAO_ANTENA_SO_VOI_MAT_DAT3;
    }

    public Date getNGAY_DAP_UNG_ANTENA_DU_KIEN() {
        return NGAY_DAP_UNG_ANTENA_DU_KIEN;
    }

    public void setNGAY_DAP_UNG_ANTENA_DU_KIEN(Date NGAY_DAP_UNG_ANTENA_DU_KIEN) {
        this.NGAY_DAP_UNG_ANTENA_DU_KIEN = NGAY_DAP_UNG_ANTENA_DU_KIEN;
    }

    public Date getNGAY_DAP_UNG_ANTENA_THUC_TE() {
        return NGAY_DAP_UNG_ANTENA_THUC_TE;
    }

    public void setNGAY_DAP_UNG_ANTENA_THUC_TE(Date NGAY_DAP_UNG_ANTENA_THUC_TE) {
        this.NGAY_DAP_UNG_ANTENA_THUC_TE = NGAY_DAP_UNG_ANTENA_THUC_TE;
    }

    public String getTRANG_THAI_TRAM() {
        return TRANG_THAI_TRAM;
    }

    public void setTRANG_THAI_TRAM(String TRANG_THAI_TRAM) {
        this.TRANG_THAI_TRAM = TRANG_THAI_TRAM;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

   
    
    
    

}
