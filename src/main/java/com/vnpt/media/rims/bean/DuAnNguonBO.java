package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class DuAnNguonBO {

      private Long du_an_nguon_id; 
      private Long area; 
      private Long tinh_tp_id; 
      private String ten_tinh_tp; 
      private Long tram_quy_hoach_id; 
      private String ten_quy_hoach; 
      private String ma_quy_hoach;
      private Long building_id; 
      private String ma_building; 
      private String building_name; 
      private String dia_chi; 
      private String longitude; 
      private String latitude; 
      private Long status; 
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_hoat_dong_csht;
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_hoan_thanh_csht; 
      private Long tu_nguon; 
      private String chungloai_accu;
      private Long so_to_accu; 
      private String muc_dich_tb_nguon; 
      private String so_van_ban; 
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_van_ban;
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_can_lap_tb_nguon; 
      private String phone_number; 
      private String ten_tu_nguon;
      private Long so_tu_nguon; 
      private Long so_module_nan; 
      private String cong_suat_module_nan;
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_du_kien_ban_giao_tu_nguon; 
      private String thuoc_du_an_tu_nguon;
      private Long so_po_tu_nguon; 
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_thuc_te_giao_tu_nguon;
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_nghiem_thu; 
      private String ten_accu; 
      private String chung_loai_accu_ptm;
      private Long so_to_accu_ptm; 
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_du_kien_ban_giao_accu;
      private String thuoc_du_an_accu; 
      private Long so_po_accu; 
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_thuc_te_giao_accu;
      @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date ngay_nghiem_thu_accu; 
      private String ghi_chu_net; 
      private String ghi_chu_vtt;                 
      private Long userId;                   

    public Long getDu_an_nguon_id() {
        return du_an_nguon_id;
    }

    public void setDu_an_nguon_id(Long du_an_nguon_id) {
        this.du_an_nguon_id = du_an_nguon_id;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getTinh_tp_id() {
        return tinh_tp_id;
    }

    public void setTinh_tp_id(Long tinh_tp_id) {
        this.tinh_tp_id = tinh_tp_id;
    }

    public String getTen_tinh_tp() {
        return ten_tinh_tp;
    }

    public void setTen_tinh_tp(String ten_tinh_tp) {
        this.ten_tinh_tp = ten_tinh_tp;
    }

    public Long getTram_quy_hoach_id() {
        return tram_quy_hoach_id;
    }

    public void setTram_quy_hoach_id(Long tram_quy_hoach_id) {
        this.tram_quy_hoach_id = tram_quy_hoach_id;
    }

    public String getTen_quy_hoach() {
        return ten_quy_hoach;
    }

    public void setTen_quy_hoach(String ten_quy_hoach) {
        this.ten_quy_hoach = ten_quy_hoach;
    }

    public String getMa_quy_hoach() {
        return ma_quy_hoach;
    }

    public void setMa_quy_hoach(String ma_quy_hoach) {
        this.ma_quy_hoach = ma_quy_hoach;
    }

    public Long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Long building_id) {
        this.building_id = building_id;
    }

    public String getMa_building() {
        return ma_building;
    }

    public void setMa_building(String ma_building) {
        this.ma_building = ma_building;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getNgay_hoat_dong_csht() {
        return ngay_hoat_dong_csht;
    }

    public void setNgay_hoat_dong_csht(Date ngay_hoat_dong_csht) {
        this.ngay_hoat_dong_csht = ngay_hoat_dong_csht;
    }

    public Date getNgay_hoan_thanh_csht() {
        return ngay_hoan_thanh_csht;
    }

    public void setNgay_hoan_thanh_csht(Date ngay_hoan_thanh_csht) {
        this.ngay_hoan_thanh_csht = ngay_hoan_thanh_csht;
    }


    public Long getTu_nguon() {
        return tu_nguon;
    }

    public void setTu_nguon(Long tu_nguon) {
        this.tu_nguon = tu_nguon;
    }

    public String getChungloai_accu() {
        return chungloai_accu;
    }

    public void setChungloai_accu(String chungloai_accu) {
        this.chungloai_accu = chungloai_accu;
    }

    public Long getSo_to_accu() {
        return so_to_accu;
    }

    public void setSo_to_accu(Long so_to_accu) {
        this.so_to_accu = so_to_accu;
    }

    public String getSo_van_ban() {
        return so_van_ban;
    }

    public void setSo_van_ban(String so_van_ban) {
        this.so_van_ban = so_van_ban;
    }

    public Date getNgay_van_ban() {
        return ngay_van_ban;
    }

    public void setNgay_van_ban(Date ngay_van_ban) {
        this.ngay_van_ban = ngay_van_ban;
    }

    public Date getNgay_can_lap_tb_nguon() {
        return ngay_can_lap_tb_nguon;
    }

    public void setNgay_can_lap_tb_nguon(Date ngay_can_lap_tb_nguon) {
        this.ngay_can_lap_tb_nguon = ngay_can_lap_tb_nguon;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getTen_tu_nguon() {
        return ten_tu_nguon;
    }

    public void setTen_tu_nguon(String ten_tu_nguon) {
        this.ten_tu_nguon = ten_tu_nguon;
    }

    public Long getSo_tu_nguon() {
        return so_tu_nguon;
    }

    public void setSo_tu_nguon(Long so_tu_nguon) {
        this.so_tu_nguon = so_tu_nguon;
    }

    public Long getSo_module_nan() {
        return so_module_nan;
    }

    public void setSo_module_nan(Long so_module_nan) {
        this.so_module_nan = so_module_nan;
    }

    public String getCong_suat_module_nan() {
        return cong_suat_module_nan;
    }

    public void setCong_suat_module_nan(String cong_suat_module_nan) {
        this.cong_suat_module_nan = cong_suat_module_nan;
    }

    public Date getNgay_du_kien_ban_giao_tu_nguon() {
        return ngay_du_kien_ban_giao_tu_nguon;
    }

    public void setNgay_du_kien_ban_giao_tu_nguon(Date ngay_du_kien_ban_giao_tu_nguon) {
        this.ngay_du_kien_ban_giao_tu_nguon = ngay_du_kien_ban_giao_tu_nguon;
    }

    public String getThuoc_du_an_tu_nguon() {
        return thuoc_du_an_tu_nguon;
    }

    public void setThuoc_du_an_tu_nguon(String thuoc_du_an_tu_nguon) {
        this.thuoc_du_an_tu_nguon = thuoc_du_an_tu_nguon;
    }

    public Long getSo_po_tu_nguon() {
        return so_po_tu_nguon;
    }

    public void setSo_po_tu_nguon(Long so_po_tu_nguon) {
        this.so_po_tu_nguon = so_po_tu_nguon;
    }

    public Date getNgay_thuc_te_giao_tu_nguon() {
        return ngay_thuc_te_giao_tu_nguon;
    }

    public void setNgay_thuc_te_giao_tu_nguon(Date ngay_thuc_te_giao_tu_nguon) {
        this.ngay_thuc_te_giao_tu_nguon = ngay_thuc_te_giao_tu_nguon;
    }

    public Date getNgay_nghiem_thu() {
        return ngay_nghiem_thu;
    }

    public void setNgay_nghiem_thu(Date ngay_nghiem_thu) {
        this.ngay_nghiem_thu = ngay_nghiem_thu;
    }

    public String getTen_accu() {
        return ten_accu;
    }

    public void setTen_accu(String ten_accu) {
        this.ten_accu = ten_accu;
    }

    public String getChung_loai_accu_ptm() {
        return chung_loai_accu_ptm;
    }

    public void setChung_loai_accu_ptm(String chung_loai_accu_ptm) {
        this.chung_loai_accu_ptm = chung_loai_accu_ptm;
    }

    public Long getSo_to_accu_ptm() {
        return so_to_accu_ptm;
    }

    public void setSo_to_accu_ptm(Long so_to_accu_ptm) {
        this.so_to_accu_ptm = so_to_accu_ptm;
    }

    public Date getNgay_du_kien_ban_giao_accu() {
        return ngay_du_kien_ban_giao_accu;
    }

    public void setNgay_du_kien_ban_giao_accu(Date ngay_du_kien_ban_giao_accu) {
        this.ngay_du_kien_ban_giao_accu = ngay_du_kien_ban_giao_accu;
    }

    public String getThuoc_du_an_accu() {
        return thuoc_du_an_accu;
    }

    public void setThuoc_du_an_accu(String thuoc_du_an_accu) {
        this.thuoc_du_an_accu = thuoc_du_an_accu;
    }

    public Long getSo_po_accu() {
        return so_po_accu;
    }

    public void setSo_po_accu(Long so_po_accu) {
        this.so_po_accu = so_po_accu;
    }

    public Date getNgay_thuc_te_giao_accu() {
        return ngay_thuc_te_giao_accu;
    }

    public void setNgay_thuc_te_giao_accu(Date ngay_thuc_te_giao_accu) {
        this.ngay_thuc_te_giao_accu = ngay_thuc_te_giao_accu;
    }

    public Date getNgay_nghiem_thu_accu() {
        return ngay_nghiem_thu_accu;
    }

    public void setNgay_nghiem_thu_accu(Date ngay_nghiem_thu_accu) {
        this.ngay_nghiem_thu_accu = ngay_nghiem_thu_accu;
    }

    public String getGhi_chu_net() {
        return ghi_chu_net;
    }

    public void setGhi_chu_net(String ghi_chu_net) {
        this.ghi_chu_net = ghi_chu_net;
    }

    public String getGhi_chu_vtt() {
        return ghi_chu_vtt;
    }

    public void setGhi_chu_vtt(String ghi_chu_vtt) {
        this.ghi_chu_vtt = ghi_chu_vtt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMuc_dich_tb_nguon() {
        return muc_dich_tb_nguon;
    }

    public void setMuc_dich_tb_nguon(String muc_dich_tb_nguon) {
        this.muc_dich_tb_nguon = muc_dich_tb_nguon;
    }
                         

}
