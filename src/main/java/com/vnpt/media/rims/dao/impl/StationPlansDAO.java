package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.jdbc.DbSql;

public class StationPlansDAO extends GenericDAO implements IPlanStation {

    private static Logger logger = LogManager.getLogger(StationPlansDAO.class);

//    trunglk_start
    public List<TramDuAnBO> findTramDAAll(String startRow, String endRow,
            String khuVucId,
            String tinhTpId,
            String idDuAn,
            String maTramDuAn, 
            String tenTramDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach,
            Long status) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fc_search_stationPlans(?,?,?,?,?,?,?,?,?,?,?)}";

            List<Object> vars = new Vector<Object>();

//            if (StringUtils.hasText(fullname)) {
//                vars.add("%" + fullname.trim().toLowerCase() + "%");
//            }
            vars.add(startRow);
            vars.add(endRow);
            vars.add(khuVucId == null ? "" : khuVucId);
            vars.add(tinhTpId == null ? "" : tinhTpId);
            vars.add(idDuAn == null ? "" : idDuAn);
            vars.add(maTramDuAn == null ? "" : maTramDuAn);
            vars.add(tenTramDuAn == null ? "" : tenTramDuAn);
            vars.add(msHopDong == null ? "" : msHopDong);
            vars.add(maTramBTS == null ? "" : maTramBTS);
            vars.add(maTramNodeB == null ? "" : maTramNodeB);
            vars.add(maTramQuyHoach == null ? "" : maTramQuyHoach);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TramDuAnBO tramDABO = new TramDuAnBO();
                    Long tramDuAnId = rs.getLong("tram_du_an_id");
                    tramDABO.setId(rs.wasNull() ? null : tramDuAnId);
                    Long duAnId = rs.getLong("du_an_id");
                    tramDABO.setDuAnId(rs.wasNull() ? null : duAnId);
                    tramDABO.setMaDuAn(rs.getString("ma_du_an"));
                    tramDABO.setTenDuAn(rs.getString("ten_du_an"));
                    Long tramQhId = rs.getLong("tram_qh_id");
                    tramDABO.setTramQHId(rs.wasNull() ? null : tramQhId);
                    tramDABO.setMaQuyHoach(rs.getString("ma_quy_hoach"));
                    tramDABO.setTenQuyHoach(rs.getString("ten_quy_hoach"));
                    tramDABO.setMaSoHopDong(rs.getString("ma_so_hop_dong"));
                    Long tinhTpId = rs.getLong("tinhtp_id");
                    tramDABO.setTinhTpId(rs.wasNull() ? null : tinhTpId);

                    tramDABO.setTenTinhTp(rs.getString("ten_tinh_tp"));
                    Long quanHuyenId = rs.getLong("quan_huyen_id");
                    tramDABO.setQuanHuyenId(rs.wasNull() ? null : quanHuyenId);
                    tramDABO.setTenQuanHuyen(rs.getString("ten_quan_huyen"));
                    tramDABO.setAddress(rs.getString("dia_chi"));
                    tramDABO.setMaTramDuAn(rs.getString("ma_tram_da"));
                    tramDABO.setTenTramDuAn(rs.getString("ten_tram"));
                    tramDABO.setMaTramBTS(rs.getString("ma_tram_bts"));
                    tramDABO.setMaTramNodeB(rs.getString("ma_tram_nodeB"));
                    tramDABO.setMaTramQuyHoach(rs.getString("ma_tram_quy_hoach"));
                    tramDABO.setHienTrangTram(rs.getString("hien_trang_tram"));
                    Long trangThaiCsht = rs.getLong("trang_thai_csht");
                    tramDABO.setTrangThaiCsht(rs.wasNull() ? null : trangThaiCsht);
                    tramDABO.setLongitude(rs.wasNull() ? null : rs.getString("longitude"));
                    tramDABO.setLatitude(rs.wasNull() ? null : rs.getString("latitude"));
//cam ket thiet bi                   
                    Long vnptNetPheDuyet = rs.getLong("vnpt_net_phe_duyet");
                    tramDABO.setVnptNetPheDuyet(rs.wasNull() ? null : vnptNetPheDuyet);
                    tramDABO.setCauHinhThietBi(rs.getString("cau_hinh_thiet_bi"));
                    Long nguonThietBi = rs.getLong("nguon_thiet_bi");
                    tramDABO.setNguonThietBi(rs.wasNull() ? null : nguonThietBi);
                    Long loaiCongNghe = rs.getLong("loai_cong_nghe");
                    tramDABO.setLoaiCongNghe(rs.wasNull() ? null : loaiCongNghe);
                    tramDABO.setChungLoaiThietBi(rs.getString("chung_loai_thiet_bi"));
                    tramDABO.setChungLoaiAnten(rs.getString("chung_loai_anten"));
                    tramDABO.setNgayCungCapTb(rs.getDate("THOI_DIEM_CUNG_CAP_TB"));
                    tramDABO.setNgaySwapTb(rs.getDate("THOI_DIEM_SWAP_TB"));
//cam ket ha tang
                    tramDABO.setLongitudeKhaoSat(rs.getString("longitude_khao_sat")==null ? "" : rs.getString("longitude_khao_sat"));
                    tramDABO.setLatitudeKhaoSat(rs.getString("latitude_khao_sat")==null ? "" : rs.getString("latitude_khao_sat"));
                    Long nhaTram = rs.getLong("nha_tram");
                    tramDABO.setNhaTram(rs.wasNull() ? null : nhaTram);
                    Long cotAnten = rs.getLong("cot_anten");
                    tramDABO.setCotAnten(rs.wasNull() ? null : cotAnten);
                    Long cauCapNgoai = rs.getLong("cau_cap_ngoai");
                    tramDABO.setCauCapNgoai(rs.wasNull() ? null : cauCapNgoai);
                    Long tuNguon = rs.getLong("tu_nguon");
                    tramDABO.setTuNguon(rs.wasNull() ? null : tuNguon);
                    tramDABO.setDungLuongTuNguon(rs.getString("dung_luong_tu_nguon"));
                    tramDABO.setSoModuleTuNguon(rs.getString("so_module_tu_nguon"));
                    Long chungLoaiAccu = rs.getLong("chung_loai_accu");
                    tramDABO.setChungLoaiAccu(rs.wasNull() ? null : chungLoaiAccu);
                    tramDABO.setDungLuongAccu(rs.getString("dung_luong_accu"));
                    tramDABO.setSoLuongToAccu(rs.getString("so_luong_to_accu"));
                    Long truyenDan = rs.getLong("truyen_dan");
                    tramDABO.setTruyenDan(rs.wasNull() ? null : truyenDan);
                    Long dieuHoa = rs.getLong("dieu_hoa");
                    tramDABO.setDieuHoa(rs.wasNull() ? null : dieuHoa);
                    Long dienAc = rs.getLong("dien_ac");
                    tramDABO.setDienAc(rs.wasNull() ? null : dienAc);
                    Long dienAcNoiTram = rs.getLong("dien_ac_noi_tram");
                    tramDABO.setDienAcNoiTram(rs.wasNull() ? null : dienAcNoiTram);
                    Long duDkLapEnodeB = rs.getLong("du_dk_lap_enodeb");
                    tramDABO.setDuDkLapEnodeb(rs.wasNull() ? null : duDkLapEnodeB);
                    Long capMoiTuNguonDc = rs.getLong("cap_moi_tu_nguon_dc");
                    tramDABO.setCapMoiTuNguonDc(rs.wasNull() ? null : capMoiTuNguonDc);
                    Long capMoiAccu = rs.getLong("cap_moi_accu");
                    tramDABO.setCapMoiAccu(rs.wasNull() ? null : capMoiAccu);
                    tramDABO.setSwapAnten(rs.getString("swap_anten"));
                    tramDABO.setNgayHoanThanhKs(rs.getDate("ngay_hoan_thanh_ks"));
                    tramDABO.setNgayGuiSoLieu(rs.getDate("ngay_gui_so_lieu"));
                    tramDABO.setDauMoiNhanThietBi(rs.getString("dau_moi_nhan_thiet_bi"));
                    tramDABO.setDauMoiQLCSHT(rs.getString("DAU_MOI_QL_CSHT_TRAM"));
                    tramDABO.setDonViLapDat(rs.getString("don_vi_lap_dat"));
                    tramDABO.setNgayHTLapDatAnten(rs.getDate("NGAY_HOAN_THANH_LAP_DAT_ANTEN"));
//trang thai trien khai netx
                    tramDABO.setNgayPheDuyetKhaoSat(rs.getDate("ngay_phe_duyet_khao_sat"));
                    tramDABO.setNgayTiepNhanTruyenDan(rs.getDate("ngay_tiep_nhan_truyen_dan"));
//trang thai trien khai
                    tramDABO.setKeHoachXuatThietBi(rs.getDate("ke_hoach_xuat_thiet_bi"));
                    tramDABO.setNgayXuatThietBiThucTe(rs.getDate("ngay_xuat_thiet_bi_thuc_te"));
                    tramDABO.setNgayTiepNhanTb(rs.getDate("ngay_tiep_nhan_tb"));
                    tramDABO.setKeHoachTbDenSite(rs.getDate("ke_hoach_tb_den_site"));
                    tramDABO.setKeHoachLapDat(rs.getDate("ke_hoach_lap_dat"));
                    tramDABO.setNgayBatDauLapDat(rs.getDate("ngay_bat_dau_lap_dat"));
                    tramDABO.setNgayHTLapDatTb(rs.getDate("NGAY_HT_LAP_TB"));
                    tramDABO.setKeHoachHoaMang(rs.getDate("ke_hoach_hoa_mang"));
                    tramDABO.setNgayHoaMangThucTe(rs.getDate("ngay_hoa_mang_thuc_te"));
                    tramDABO.setKeHoachPhatSongCt(rs.getDate("ke_hoach_phat_song_ct"));
                    tramDABO.setNgayPhatSongCt(rs.getDate("ngay_phat_song_ct"));
                    tramDABO.setKeHoachNghiemThu(rs.getDate("ke_hoach_nghiem_thu"));
                    tramDABO.setNgayNghiemThu(rs.getDate("ngay_nghiem_thu"));
                    tramDABO.setDauMoiVnptNet(rs.getString("dau_moi_vnpt_net"));
                    tramDABO.setDonViVanChuyen(rs.getString("don_vi_van_chuyen"));
                    tramDABO.setGhiChu(rs.getString("ghi_chu"));
                    Long trangThaiTram = rs.getLong("trang_thai_tram");
                    tramDABO.setTrangThaiTram(rs.wasNull() ? null : trangThaiTram);
                    tramDABO.setTenTrangThaiTram(rs.getString("ten_trang_thai_tram"));
                    tramDABO.setNgayXuatAntenThucTe(rs.getDate("NGAY_XUAT_ANTEN_THUC_TE"));
//                    Long trangthaiAction = Long.parseLong(status);
                    tramDABO.setTrangThaiTramAction(status);
                    return tramDABO;
                }

            }, vars);

            return (List<TramDuAnBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }
    
        @Override
    public int getTotalAll(String khuVucId,
                            String tinhTpId,
                            String idDuAn,
                            String maTramDuAn,
                            String tenTramDuAn,
                            String msHopDong,
                            String maTramBTS,
                            String maTramNodeB,
                            String maTramQuyHoach,
                            Long status) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fc_total_all_tram_ke_hoach(?,?,?,?,?,?,?,?,?)}";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(khuVucId == null ? "" : khuVucId);
            vars.add(tinhTpId == null ? "" : tinhTpId);
            vars.add(idDuAn == null ? "" : idDuAn);
            vars.add(maTramDuAn == null ? "" : maTramDuAn);
            vars.add(tenTramDuAn == null ? "" : tenTramDuAn);
            vars.add(msHopDong == null ? "" : msHopDong);
            vars.add(maTramBTS == null ? "" : maTramBTS);
            vars.add(maTramNodeB == null ? "" : maTramNodeB);
            vars.add(maTramQuyHoach == null ? "" : maTramQuyHoach);
            DbSql sqlTemplate = new DbSql(conn);
            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            return count;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    public void addTramDA(TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_add_tram_da(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (tramDABO == null) {
                return;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
//            vars.add(tramDABO.getId());
            vars.add(tramDABO.getDuAnId());
            vars.add(tramDABO.getTramQHId());
            vars.add(tramDABO.getMaSoHopDong().trim());
            vars.add(tramDABO.getTinhTpId());
            vars.add(tramDABO.getQuanHuyenId());
            vars.add(tramDABO.getAddress().trim());
            vars.add(tramDABO.getMaTramDuAn().trim());
            vars.add(tramDABO.getTenTramDuAn().trim());
            vars.add(tramDABO.getMaTramBTS().trim());
            vars.add(tramDABO.getMaTramNodeB().trim());
            vars.add(tramDABO.getMaTramQuyHoach().trim());
            vars.add(tramDABO.getHienTrangTram());
            vars.add(tramDABO.getLongitude());
            vars.add(tramDABO.getLatitude());
            vars.add(tramDABO.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<TramDuAnBO> findAllDuAn(String startRow, String endRow, String name, String tinhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.find_all_du_an(?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(name);
            vars.add(tinhId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TramDuAnBO item = new TramDuAnBO();
                    item.setId(rs.getLong("du_an_id"));
                    item.setCode(rs.getString("ma_du_an"));
                    item.setName(rs.getString("ten_du_an"));
                    return item;
                }
            }, vars);

            return (List<TramDuAnBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int getTotalAllDuAn(String name, String tinhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fc_total_all_du_an(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(name);
            vars.add(tinhId);
            DbSql sqlTemplate = new DbSql(conn);
            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            return count;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int getTotalAll(String name, String tinhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fc_total_all(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(name);
            vars.add(tinhId);
            DbSql sqlTemplate = new DbSql(conn);
            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            return count;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<TramDuAnBO> findAllTramKH(String startRow, String endRow, String name, String tinhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fc_find_all(?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(name);
            vars.add(tinhId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TramDuAnBO item = new TramDuAnBO();
                    item.setId(rs.getLong("tram_qh_id"));
                    item.setCode(rs.getString("ma_quy_hoach"));
                    item.setName(rs.getString("ten_quy_hoach"));
                    return item;
                }
            }, vars);

            return (List<TramDuAnBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int updateTramDA(String action, TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_update_tram_da(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (tramDABO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(tramDABO.getId());
            vars.add(tramDABO.getTramQHId());
            vars.add(tramDABO.getDuAnId());
            vars.add(tramDABO.getMaSoHopDong().trim());
            vars.add(tramDABO.getAddress().trim());
            vars.add(tramDABO.getMaTramDuAn().trim());
            vars.add(tramDABO.getTenTramDuAn().trim());
            vars.add(tramDABO.getMaTramBTS().trim());
            vars.add(tramDABO.getMaTramNodeB().trim());
            vars.add(tramDABO.getMaTramQuyHoach().trim());
            vars.add(tramDABO.getHienTrangTram());
            vars.add(tramDABO.getLongitude());
            vars.add(tramDABO.getLatitude());
            vars.add(tramDABO.getVnptNetPheDuyet());
            vars.add(tramDABO.getCauHinhThietBi().trim());
            vars.add(tramDABO.getNguonThietBi());
            vars.add(tramDABO.getLoaiCongNghe());
            vars.add(tramDABO.getChungLoaiThietBi().trim());
            vars.add(tramDABO.getChungLoaiAnten().trim());
            vars.add(tramDABO.getLongitudeKhaoSat());
            vars.add(tramDABO.getLatitudeKhaoSat());
            vars.add(tramDABO.getNhaTram());
            vars.add(tramDABO.getCotAnten());
            vars.add(tramDABO.getCauCapNgoai());
            vars.add(tramDABO.getTuNguon());
            vars.add(tramDABO.getDungLuongTuNguon().trim());
            vars.add(tramDABO.getSoModuleTuNguon().trim());
            vars.add(tramDABO.getChungLoaiAccu());
            vars.add(tramDABO.getDungLuongAccu());
            vars.add(tramDABO.getSoLuongToAccu());
            vars.add(tramDABO.getTruyenDan());
            vars.add(tramDABO.getDieuHoa());
            vars.add(tramDABO.getDienAc());
            vars.add(tramDABO.getDienAcNoiTram());
            vars.add(tramDABO.getDuDkLapEnodeb());
            vars.add(tramDABO.getCapMoiTuNguonDc());
            vars.add(tramDABO.getCapMoiAccu());
            vars.add(tramDABO.getSwapAnten().trim());
            vars.add(tramDABO.getNgayHoanThanhKs());
            vars.add(tramDABO.getNgayGuiSoLieu());
            vars.add(tramDABO.getDauMoiNhanThietBi().trim());
            vars.add(tramDABO.getDonViLapDat().trim());
            vars.add(tramDABO.getNgayPheDuyetKhaoSat());
            vars.add(tramDABO.getNgayTiepNhanTruyenDan());
            vars.add(tramDABO.getKeHoachXuatThietBi());
            vars.add(tramDABO.getNgayXuatThietBiThucTe());
            vars.add(tramDABO.getNgayTiepNhanTb());
            vars.add(tramDABO.getKeHoachTbDenSite());
            vars.add(tramDABO.getKeHoachLapDat());
            vars.add(tramDABO.getNgayBatDauLapDat());
            vars.add(tramDABO.getKeHoachHoaMang());
            vars.add(tramDABO.getNgayHoaMangThucTe());
            vars.add(tramDABO.getKeHoachPhatSongCt());
            vars.add(tramDABO.getNgayPhatSongCt());
            vars.add(tramDABO.getKeHoachNghiemThu());
            vars.add(tramDABO.getNgayNghiemThu());
            vars.add(tramDABO.getDauMoiVnptNet().trim());
            vars.add(tramDABO.getDonViVanChuyen().trim());
            vars.add(tramDABO.getGhiChu().trim());
            vars.add(tramDABO.getTinhTpId());
            vars.add(tramDABO.getQuanHuyenId());
            vars.add(tramDABO.getDauMoiQLCSHT().trim());
            vars.add(tramDABO.getNgayHTLapDatTb());
            vars.add(tramDABO.getTrangThaiTram());
            vars.add(tramDABO.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);

//            DbSql sqlTemplate = new DbSql(conn);
//
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
            return count;

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }
    
    @Override
    public int deleteTramDA(String tramDaId, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_delete_tram_da(?,?)}";

            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(tramDaId);
            vars.add(userId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);

//            DbSql sqlTemplate = new DbSql(conn);
//
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
            return count;

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int approve(TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_approve(?,?,?,?)}";

            if (tramDABO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(tramDABO.getId());
            vars.add(tramDABO.getTrangThaiTram());
            vars.add(tramDABO.getGhiChu().trim());
            vars.add(tramDABO.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
            return count;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int updateThongTinChungTramDA(TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_update_ttc_tram_da(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (tramDABO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(tramDABO.getId());
            vars.add(tramDABO.getTramQHId());
            vars.add(tramDABO.getDuAnId());
            vars.add(tramDABO.getMaSoHopDong().trim());
            vars.add(tramDABO.getAddress().trim());
            vars.add(tramDABO.getMaTramDuAn().trim());
            vars.add(tramDABO.getTenTramDuAn().trim());
            vars.add(tramDABO.getMaTramBTS().trim());
            vars.add(tramDABO.getMaTramNodeB().trim());
            vars.add(tramDABO.getMaTramQuyHoach().trim());
            vars.add(tramDABO.getHienTrangTram());
            vars.add(tramDABO.getTrangThaiCsht());
            vars.add(tramDABO.getLongitude());
            vars.add(tramDABO.getLatitude());
            vars.add(tramDABO.getTinhTpId());
            vars.add(tramDABO.getQuanHuyenId());
            vars.add(tramDABO.getTrangThaiTram());
            vars.add(tramDABO.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);

//            DbSql sqlTemplate = new DbSql(conn);
//
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
            return count;

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int updateCCTBTramDA(TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_update_cctb_tram_da(?,?,?,?,?,?,?,?,?,?,?)}";

            if (tramDABO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(tramDABO.getId());
            vars.add(tramDABO.getVnptNetPheDuyet());
            vars.add(tramDABO.getCauHinhThietBi().trim());
            vars.add(tramDABO.getNguonThietBi());
            vars.add(tramDABO.getLoaiCongNghe());
            vars.add(tramDABO.getChungLoaiThietBi().trim());
            vars.add(tramDABO.getChungLoaiAnten().trim());
            vars.add(tramDABO.getTrangThaiTram());
            vars.add(tramDABO.getUserId());
            vars.add(tramDABO.getNgayCungCapTb());
            vars.add(tramDABO.getNgaySwapTb());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);

//            DbSql sqlTemplate = new DbSql(conn);
//
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
            return count;

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int updateCCHTTramDA(TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_update_ccht_tram_da(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (tramDABO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(tramDABO.getId());
            vars.add(tramDABO.getLongitudeKhaoSat());
            vars.add(tramDABO.getLatitudeKhaoSat());
            vars.add(tramDABO.getNhaTram());
            vars.add(tramDABO.getCotAnten());
            vars.add(tramDABO.getCauCapNgoai());
            vars.add(tramDABO.getTuNguon());
            vars.add(tramDABO.getDungLuongTuNguon().trim());
            vars.add(tramDABO.getSoModuleTuNguon().trim());
            vars.add(tramDABO.getChungLoaiAccu());
            vars.add(tramDABO.getDungLuongAccu());
            vars.add(tramDABO.getSoLuongToAccu());
            vars.add(tramDABO.getTruyenDan());
            vars.add(tramDABO.getDieuHoa());
            vars.add(tramDABO.getDienAc());
            vars.add(tramDABO.getDienAcNoiTram());
            vars.add(tramDABO.getDuDkLapEnodeb());
            vars.add(tramDABO.getNgayDuDkLapDatThietBi());
//            dudklapthietbi
            vars.add(tramDABO.getCapMoiTuNguonDc());
            vars.add(tramDABO.getCapMoiAccu());
            vars.add(tramDABO.getSwapAnten().trim());
            vars.add(tramDABO.getNgayHoanThanhKs());
            vars.add(tramDABO.getNgayGuiSoLieu());
            vars.add(tramDABO.getDauMoiNhanThietBi().trim());
            vars.add(tramDABO.getDauMoiQLCSHT().trim());
            vars.add(tramDABO.getDonViLapDat().trim());
            vars.add(tramDABO.getTrangThaiTram());
            vars.add(tramDABO.getKeHoachLapDat());
            vars.add(tramDABO.getNgayBatDauLapDat());
            vars.add(tramDABO.getNgayHTLapDatTb());
            vars.add(tramDABO.getNgayHTLapDatAnten());
//            cstmt.setString(31, tramDABO.getNgayBatDauLapDat());
//            cstmt.setString(32, tramDABO.getNgayHTLapDatTb());
//            cstmt.setString(33, tramDABO.getNgayHTLapDatAnten()); 
            vars.add(tramDABO.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);

//            DbSql sqlTemplate = new DbSql(conn);
//
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
            return count;

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int updateDeployNetXTramDA(TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_update_deploy_netx_tram_da(?,?,?,?,?)}";

            if (tramDABO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(tramDABO.getId());
            vars.add(tramDABO.getNgayPheDuyetKhaoSat());
            vars.add(tramDABO.getNgayTiepNhanTruyenDan());
            vars.add(tramDABO.getTrangThaiTram());
            vars.add(tramDABO.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);

//            DbSql sqlTemplate = new DbSql(conn);
//
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
            return count;

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public int updateDeployQLHTTramDA(TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_tram_ke_hoach.fn_update_deploy_qlht_tram_da(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (tramDABO == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(tramDABO.getId());
            vars.add(tramDABO.getKeHoachXuatThietBi());
            vars.add(tramDABO.getNgayXuatThietBiThucTe());
            vars.add(tramDABO.getNgayXuatAntenThucTe());
            vars.add(tramDABO.getNgayTiepNhanTb());
            vars.add(tramDABO.getKeHoachTbDenSite());
//            vars.add(tramDABO.getKeHoachLapDat());
//            vars.add(tramDABO.getNgayBatDauLapDat());
            vars.add(tramDABO.getKeHoachHoaMang());
            vars.add(tramDABO.getNgayHoaMangThucTe());
            vars.add(tramDABO.getKeHoachPhatSongCt());
            vars.add(tramDABO.getNgayPhatSongCt());
            vars.add(tramDABO.getKeHoachNghiemThu());
            vars.add(tramDABO.getNgayNghiemThu());
            vars.add(tramDABO.getDauMoiVnptNet().trim());
            vars.add(tramDABO.getDonViVanChuyen().trim());
//            vars.add(tramDABO.getNgayHTLapDatTb());
            vars.add(tramDABO.getGhiChu().trim());
            vars.add(tramDABO.getTrangThaiTram());
            vars.add(tramDABO.getUserId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);

//            DbSql sqlTemplate = new DbSql(conn);
//
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            
            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }
            return count;

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }
//    trunglk_end

    // tu update import excel
//    public String addExcelTramDA(TramDuAnTinhExcel tramDABO, String userId) throws DAOException {
//        Connection conn = null;
//        String output = "";
//        try {
//            conn = this.getConnection();
//            String querySql = "{? = call pkg_excel.fn_check_tram_ke_hoach(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
//
//            if (tramDABO == null) {
//                return "";
//            }
//            logger.info("SQL : " + querySql);
//
//            List<Object> vars = new Vector<Object>();
////            vars.add(tramDABO.getId());
//            vars.add(tramDABO.getCode().trim());
//            vars.add(tramDABO.getMaQuyHoach().trim());
//            vars.add(tramDABO.getMaSoHopDong().trim());
//
//            vars.add(tramDABO.getTenTinhTp().trim());
//            vars.add(tramDABO.getTenQuanHuyen().trim());
//            vars.add(tramDABO.getAddress().trim());
//            vars.add(tramDABO.getMaTramDuAn().trim());
//            vars.add(tramDABO.getTenTramDuAn().trim());
//            vars.add(tramDABO.getMaTramBTS().trim());
//            vars.add(tramDABO.getMaTramNodeB().trim());
//            vars.add(tramDABO.getMaTramQuyHoach());
//            vars.add(tramDABO.getHienTrangTram());
//            vars.add(tramDABO.getLongitude());
//            vars.add(tramDABO.getLatitude());
//            vars.add(userId);
//            DbSql sqlTemplate = new DbSql(conn);
//
//            output = sqlTemplate.runProc(querySql, vars);
//
//            
////            if (count > 0) {
////                logger.debug("Records update : " + count);
////            } else {
////                throw new DAOException();
////            }
//
//        } catch (ConnectionException e) {
//            logger.error("ConnectionException :", e);
//            throw new DAOException(e);
//        } catch (JdbcException e) {
//            logger.error("JdbcException :", e);
//            throw new DAOException(e);
//        } catch (Exception e) {
//            logger.error("Exception :", e);
//            throw new DAOException(e);
//        }
//        return output;
//    }
}
