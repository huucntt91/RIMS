/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.BcTongHopBO;
import com.vnpt.media.rims.bean.BtsReportBO;
import com.vnpt.media.rims.bean.Cell2GReportBO;
import com.vnpt.media.rims.bean.Detail2gBO;
import com.vnpt.media.rims.bean.Detail3gBO;
import com.vnpt.media.rims.bean.FilterReportBO;
import com.vnpt.media.rims.bean.NodeBReportBO;
import com.vnpt.media.rims.common.utils.Convert;
import com.vnpt.media.rims.formbean.BtsNodeB;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.dao.IReport;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.formbean.Cell2G;
import com.vnpt.media.rims.formbean.Cell3G;
import com.vnpt.media.rims.formbean.FilterForm;
import com.vnpt.media.rims.formbean.ReportConfigForm;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyano
 */
public class ReportDao extends GenericDAO implements IReport {

    @Override
    public List<BtsNodeB> findBtsNodeB(String vendor) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = this.getConnection();
            String sql = "begin ?:=pkg_report.findBtsNodeB(?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, vendor);
            cstmt.setString(3, vendor);
            cstmt.setLong(4, 0);
            cstmt.setLong(5, 100);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                BtsNodeB record = new BtsNodeB();
                record.setType1(rs.getString("type1"));
                record.setType2(rs.getString("type2"));
                record.setBscRnc(rs.getString("bsc_rnc"));
                record.setBtsNodeB(rs.getString("bts_nodeb"));
                record.setSiteType(rs.getString("site_type"));
                record.setVendor(rs.getString("vendor"));
                record.setPcCode(rs.getString("pcode"));
                record.setVnp(rs.getString("pname"));
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    @Override
    public List<Cell2G> findCell2G() throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = this.getConnection();
            String sql = "begin ?:=pkg_report.findCell(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setLong(2, 5);
            cstmt.setLong(3, 0);
            cstmt.setLong(4, 100);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cell2G record = new Cell2G();

                record.setmBsc(rs.getString("bsc_rnc"));
                record.setCellName(rs.getString("cellname"));
                record.setVendor(rs.getString("vendor"));
                record.setFreqBand(rs.getString("frequency_band"));
                record.setLac(rs.getLong("lac"));
                record.setCi(rs.getLong("ci"));
                record.setBtsName(rs.getString("bts_nodeb"));
                record.setType1("2G");
                record.setType2("2G");
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    @Override
    public List<Cell3G> findCell3G() throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Cell3G> ar = new ArrayList<>();
        try {
            conn = this.getConnection();
            String sql = "begin ?:=pkg_report.findCell(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setLong(2, 6);
            cstmt.setLong(3, 0);
            cstmt.setLong(4, 100);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cell3G record = new Cell3G();

                record.setVendor(rs.getString("vendor"));
                record.setType1("3G");
                record.setType2("3G");
                record.setmBsc(rs.getString("bsc_rnc"));
//                record.setNodeBname(rs.getString("nodeb_name"));
//                record.setCellType(rs.getString("cell_type"));
                record.setCellName(rs.getString("cellname"));
                record.setFreq(rs.getString("frequency_band"));
                record.setLac(rs.getLong("lac"));
                record.setCi(rs.getLong("ci"));
                record.setNodeBname(rs.getString("bts_nodeb"));
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    @Override
    public List<FilterReportBO> findFilterReport(int objectType) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_find_all(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(objectType);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    FilterReportBO item = new FilterReportBO();
                    item.setId(rs.getLong("id"));
                    item.setType(rs.getInt("TYPE"));
                    item.setColumnName(rs.getString("COLUMN_NAME"));
                    item.setColumnId(rs.getString("TABLE_NAME") + "." + item.getColumnName());
                    item.setDataType(rs.getInt("DATA_TYPE"));
                    item.setDescription(rs.getString("DESCRIPTION"));
                    return item;
                }
            }, vars);
            
            return (List<FilterReportBO>) list;
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
    public List<Cell2GReportBO> cell2GReport(int type, FilterForm filterForm, String startRow, String endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_cell_2g(?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            String sWhere = " ";
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {

                    if (!filterForm.getListFilterForm().get(i).getColumn().equalsIgnoreCase("-1")) {
                        sWhere += " and " + Convert.convertFilter(filterForm.getListFilterForm().get(i).getColumn(), filterForm.getListFilterForm().get(i).getOperator(), filterForm.getListFilterForm().get(i).getValue_());
                    }
                }
            }

            
            vars.add(sWhere);
            vars.add(type);
            vars.add(startRow);
            vars.add(endRow);
            
            
            

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Cell2GReportBO item = new Cell2GReportBO();
                    if (type == 5) {
                        
                        item.setNodeId(rs.getLong("nodeId"));
                        item.setLocationNumber(rs.getString("LOCATION_NUMBER"));
                        item.setTinh(rs.getString("ten_tinh_tp"));
                        item.setQuan(rs.getString("TEN_QUAN_HUYEN"));
                        item.setXa(rs.getString("TEN_PHUONG_XA"));
                        item.setDiachi(rs.getString("dia_chi"));
                        item.setNgayHoatDong(rs.getString("ngay_hoat_dong"));
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setTenCell(rs.getString("ten_cell"));
                        item.setCode(rs.getString("ma_node"));
                        item.setNgayDangKy(rs.getString("ngay_dang_ky"));
                        item.setNgayKiemDuyet(rs.getString("ngay_kiem_duyet"));
                        item.setNgayCapPhep(rs.getString("ngay_cap_phep"));
                        item.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));
                        item.setLac(rs.getString("lac"));
                        item.setCi(rs.getString("ci"));
                        item.setMaBTS(rs.getString("parentMaNode"));
                        item.setSoTrx(rs.getString("trx_config"));
                        item.setSDT_NG_QLY(rs.getString("SDT_NG_QLY"));
                        
//                    item.setTenBscRnc(rs.getString("TEN_BSC_RNC"));
//                    item.setCode(rs.getString("code"));
//                    item.setDcHsdpa42m(rs.getString("DC_HSDPA_42M"));
                        item.setFrequencyBand(rs.getString("Ten_bang_tan"));
                        item.setTrangThaiHd(rs.getString("TEN_TRANGTHAI_HD"));
//                        item.setTrangThaiQlId(rs.getString("TRANGTHAI_QL_ID"));
                        item.setTrangThaiQl(rs.getString("TEN_TRANGTHAI_QL"));
                        item.setLatitude(rs.getString("LATITUDE"));
                        item.setLongitude(rs.getString("LONGITUDE"));
                        item.setAzimuth(rs.getString("AZIMUTH"));
                        item.setMechanical(rs.getString("MECHANICAL_TILT"));
                        item.setTotalTilt(rs.getString("TOTAL_TILT"));
                        item.setAntennaHigh(rs.getString("ANTENNA_HIGH"));
                        item.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                        item.setAntennaType(rs.getString("ANTENNA_TYPE"));
                        item.setTenAnten(rs.getString("TEN_LOAI_ANTEN"));
                        item.setTenThietBi(rs.getString("TEN_THIET_BI"));
                        item.setTenLoaiTram(rs.getString("ten_loai_tram"));
//                    item.setNoOfCarrier(rs.getString("NO_OF_CARRIER"));
//                    item.setCpichPower(rs.getString("CPICH_POWER"));
//                    item.setTotalTilt(rs.getString("TOTAL_POWER"));
                        item.setBosterTma(rs.getString("BOSTER_TMA"));
                        item.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
//                    item.setBlackListFlag(rs.getString("BLACK_LIST_FLAG"));
//                    item.setLyDo(rs.getString("LY_DO"));
                        item.setTenBscRnc(rs.getString("ten_bscrnc"));
                    }
                    if (type == 6) {
                        item.setNodeId(rs.getLong("nodeId"));
                        item.setLocationNumber(rs.getString("LOCATION_NUMBER"));
                        item.setCode(rs.getString("ma_node"));
                        item.setSDT_NG_QLY(rs.getString("SDT_NG_QLY"));
                        item.setTinh(rs.getString("ten_tinh_tp"));
                        item.setQuan(rs.getString("TEN_QUAN_HUYEN"));
                        item.setXa(rs.getString("TEN_PHUONG_XA"));
                        item.setDiachi(rs.getString("dia_chi"));
                        item.setNgayHoatDong(rs.getString("ngay_hoat_dong"));
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setTenCell(rs.getString("ten_cell"));
                        item.setNgayDangKy(rs.getString("ngay_dang_ky"));
                        item.setNgayKiemDuyet(rs.getString("ngay_kiem_duyet"));
                        item.setNgayCapPhep(rs.getString("ngay_cap_phep"));
                        item.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));
                        item.setLac(rs.getString("lac"));
                        item.setCi(rs.getString("ci"));
                        item.setMaBTS(rs.getString("parentMaNode"));
                        item.setTenBscRnc(rs.getString("ten_bscrnc"));
//                    item.setCode(rs.getString("code"));
                        item.setDcHsdpa42m(rs.getString("DC_HSDPA_42M"));
                        item.setFrequencyBand(rs.getString("Ten_bang_tan"));
//                        item.setTrangThaiHdId(rs.getString("TRANGTHAI_HD_ID"));
                        item.setTrangThaiHd(rs.getString("TEN_TRANGTHAI_HD"));
//                        item.setTrangThaiQlId(rs.getString("TRANGTHAI_QL_ID"));
                        item.setTrangThaiQl(rs.getString("TEN_TRANGTHAI_QL"));
                        item.setLatitude(rs.getString("LATITUDE"));
                        item.setLongitude(rs.getString("LONGITUDE"));
                        item.setAzimuth(rs.getString("AZIMUTH"));
                        item.setMechanical(rs.getString("MECHANICAL_TILT"));
                        item.setTotalTilt(rs.getString("TOTAL_TILT"));
                        item.setAntennaHigh(rs.getString("ANTENNA_HIGH"));
                        item.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                        item.setAntennaType(rs.getString("ANTENNA_TYPE"));
                        item.setTenAnten(rs.getString("TEN_LOAI_ANTEN"));
                        item.setTenThietBi(rs.getString("TEN_THIET_BI"));
                        item.setTenLoaiTram(rs.getString("ten_loai_tram"));
                        item.setNoOfCarrier(rs.getString("NO_OF_CARRIER"));
//                    item.setCpichPower(rs.getString("CPICH_POWER"));
//                    item.setTotalTilt(rs.getString("TOTAL_POWER"));
                        item.setBosterTma(rs.getString("BOSTER_TMA"));
                        item.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
//                    item.setBlackListFlag(rs.getString("BLACK_LIST_FLAG"));
                        item.setLyDo(rs.getString("LY_DO"));
                    }
                    return item;
                }
            }, vars);
            
            return (List<Cell2GReportBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public int getTotalCell2GReport(int type, FilterForm filterForm) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_total_cell_2g(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            String sWhere = "  ";
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {

                    if (!filterForm.getListFilterForm().get(i).getColumn().equalsIgnoreCase("-1")) {
                        sWhere += " and " + Convert.convertFilter(filterForm.getListFilterForm().get(i).getColumn(), filterForm.getListFilterForm().get(i).getOperator(), filterForm.getListFilterForm().get(i).getValue_());
                    }
                }
            }

            
            vars.add(sWhere);
            vars.add(type);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int total = sqlTemplate.queryFunctionForInt(querySql, vars);
            return total;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<BtsReportBO> btsReport(int type, FilterForm filterForm, String startRow, String endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_bts(?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            String sWhere = " ";
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {

                    if (!filterForm.getListFilterForm().get(i).getColumn().equalsIgnoreCase("-1")) {
                        sWhere += " and " + Convert.convertFilter(filterForm.getListFilterForm().get(i).getColumn(), filterForm.getListFilterForm().get(i).getOperator(), filterForm.getListFilterForm().get(i).getValue_());
                    }
                }
            }

            
            vars.add(sWhere);
            vars.add(type);
            vars.add(startRow);
            vars.add(endRow);
            
            
            

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BtsReportBO item = new BtsReportBO();
                    item.setId(rs.getString("node_id"));
                    item.setTinh(rs.getString("ten_tinh_tp"));
                    item.setQuan(rs.getString("TEN_QUAN_HUYEN"));
                    item.setXa(rs.getString("TEN_PHUONG_XA"));
                    item.setDiachi(rs.getString("dia_chi"));
                    item.setNgayHoatDong(rs.getString("ngay_hoat_dong"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setTenBts(rs.getString("ten_bts"));
                    item.setNgayDangKy(rs.getString("ngay_dang_ky"));
                    item.setNgayKiemDuyet(rs.getString("ngay_kiem_duyet"));
                    item.setNgayCapPhep(rs.getString("ngay_cap_phep"));
                    item.setTrangThaiHdId(rs.getString("ten_trangthai_hd"));
                    item.setTrangThaiQlId(rs.getString("trang_thai_ql_id"));
                    item.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));
                    item.setTenBscRnc(rs.getString("ten_bsc_rnc"));
                    item.setTenBscRncQl(rs.getString("ten_bsc_rnc_ql"));

                    item.setMaNode(rs.getString("MA_NODE"));
//                    item.setDcHsdpa42M(rs.getString("DC_HSDPA_42M"));
                    item.setFilterUser(rs.getString("FILTER_USER"));
                    item.setFrequencyBand(rs.getString("Ten_bang_tan"));
                    item.setLatitude(rs.getString("LATITUDE"));
                    item.setLongitude(rs.getString("LONGITUDE"));
                    item.setCosite2G3GType(rs.getString("COSITE_2G_3G_TYPE"));
                    item.setMaCosite2G3GType(rs.getString("MA_COSITE_2G_3G"));
                    item.setTenThietBi(rs.getString("TEN_THIET_BI"));
                    item.setTenLoaiTram(rs.getString("TEN_LOAI_TRAM"));
                    item.setCauhinh(rs.getString("CAU_HINH"));
                    item.setCauhinhSoTRX("");
                    item.setNgayBaoDuong(rs.getString("NGAY_BAO_DUONG"));
                    item.setDonViThucHien(rs.getString("DON_VI_THUC_HIEN"));
                    item.setMaKiemDinh(rs.getString("MA_KIEM_DINH"));
                    item.setNgayHieuLuc(rs.getString("NGAY_HIEU_LUC"));
                    item.setNgayHetHieuLuc(rs.getString("NGAY_HET_HIEU_LUC"));
                    item.setTenNguoiQL(rs.getString("TEN_NG_QLY"));
                    item.setSoDTNgQL(rs.getString("SDT_NG_QLY"));
                    item.setChungCSHT(rs.getString("CHUNG_CSHT"));
                    item.setLoaiTramCSHT(rs.getString("LOAI_TRAM_CSHT"));
                    item.setDocaoAnTen(rs.getString("DO_CAO_ANTEN"));
                    item.setDoCaoNhaDatAnten(rs.getString("DO_CAO_NHA_DAT_ANTEN"));
                    item.setLoaiAnTenId(rs.getString("LOAI_ANTEN_ID"));
                    item.setNgayHDTuNguon(rs.getString("NGAY_HD_TU_NGUON"));
                    item.setLoaiTuNguonId(rs.getString("LOAI_TU_NGUON_ID"));
                    item.setSoModuleTuNguon(rs.getString("SO_MODULE_TU_NGUON"));
                    item.setNgayHDMayNo(rs.getString("NGAY_HD_MAY_NO"));
                    item.setLoaiMayNoId(rs.getString("LOAI_MAY_NO_ID"));
                    item.setCongSuatMayNo(rs.getString("CONG_SUAT_MAY_NO"));
                    item.setMayNoCoDinhDiDong(rs.getString("trang_thai_dat_may_no"));
                    item.setNgayHDAccu(rs.getString("NGAY_HD_ACCU"));
                    item.setLoaiAcQuyId(rs.getString("LOAI_AC_QUY_ID"));
                    item.setDungLuongAcc(rs.getString("dung_luong_accu"));
                    item.setThoiGianHdSauMatDien(rs.getString("THOI_GIAN_HD_SAU_MAT_DIEN"));
                    item.setNgayBaoDuongAccu(rs.getString("NGAY_BAO_DUONG_ACCU"));
                    item.setLoaiTruyenDanId(rs.getString("LOAI_TRUYEN_DAN_ID"));
                    item.setDuongLuongTruyenDan(rs.getString("DUNG_LUONG_TRUYEN_DAN"));
                    item.setDienTroTiepDia(rs.getString("DIEN_TRO_TIEP_DIA"));
                    item.setLoaiCotAnten(rs.getString("loai_cot_anten"));
                    item.setMaBuilding(rs.getString("ma_building"));
                    item.setLocationNumber(rs.getString("location_number"));
                    item.setSoLuongDieuHoa(rs.getString("sl_dieu_hoa"));
                    item.setSoLuongAccuBinh(rs.getString("sl_accu_binh"));
                    item.setDienApBinh(rs.getString("dien_ap_accu"));
                    item.setDongTieuThuTuNguon(rs.getString("tieu_thu_tu_nguon"));
                    item.setDongCungCapTuNguon(rs.getString("dong_cung_cap_tu_nguon"));
                    item.setGiaoDienTruyenDan(rs.getString("giao_dien_truyen_dan"));
                    item.setTongCongSuatDieuHoa(rs.getString("tong_cs_dieuhoa"));
                    item.setLoaiHinhMayNo(rs.getString("LOAI_HINH_MAY_NO"));
                    item.setMscMss(rs.getString("cs_core"));
                    item.setSgsn(rs.getString("ps_core"));
                    return item;
                }
            }, vars);
            
            return (List<BtsReportBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public int getTotalBtsReport(int type, FilterForm filterForm) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_total_bts(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            String sWhere = "  ";
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {

                    if (!filterForm.getListFilterForm().get(i).getColumn().equalsIgnoreCase("-1")) {
                        sWhere += " and " + Convert.convertFilter(filterForm.getListFilterForm().get(i).getColumn(), filterForm.getListFilterForm().get(i).getOperator(), filterForm.getListFilterForm().get(i).getValue_());
                    }
                }
            }

            
            vars.add(sWhere);
            vars.add(type);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int total = sqlTemplate.queryFunctionForInt(querySql, vars);
            return total;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<NodeBReportBO> nodeBReport(int type, FilterForm filterForm, String startRow, String endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_nodeb(?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            String sWhere = " ";
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {

                    if (!filterForm.getListFilterForm().get(i).getColumn().equalsIgnoreCase("-1")) {
                        sWhere += " and " + Convert.convertFilter(filterForm.getListFilterForm().get(i).getColumn(), filterForm.getListFilterForm().get(i).getOperator(), filterForm.getListFilterForm().get(i).getValue_());
                    }
                }
            }

            
            vars.add(sWhere);
            vars.add(type);
            vars.add(startRow);
            vars.add(endRow);
            
            
            

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NodeBReportBO item = new NodeBReportBO();
                    item.setId(rs.getString("node_id"));
                    item.setTinh(rs.getString("ten_tinh_tp"));
                    item.setQuan(rs.getString("TEN_QUAN_HUYEN"));
                    item.setXa(rs.getString("TEN_PHUONG_XA"));
                    item.setDiachi(rs.getString("dia_chi"));
                    item.setNgayHoatDong(rs.getString("ngay_hoat_dong"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setTenBts(rs.getString("ten_nodeb"));
                    item.setNgayDangKy(rs.getString("ngay_dang_ky"));
                    item.setNgayKiemDuyet(rs.getString("ngay_kiem_duyet"));
                    item.setNgayCapPhep(rs.getString("ngay_cap_phep"));
                    item.setTrangThaiHdId(rs.getString("ten_trangthai_hd"));
                    item.setTrangThaiQlId(rs.getString("trang_thai_ql_id"));
                    item.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));
                    item.setTenBscRnc(rs.getString("ten_bsc_rnc"));
                    item.setTenBscRncQl(rs.getString("ten_bsc_rnc_ql"));

                    item.setMaNode(rs.getString("MA_NODE"));
                    item.setDcHsdpa42M(rs.getString("DC_HSDPA_42M"));
                    item.setFilterUser(rs.getString("FILTER_USER"));
                    item.setFrequencyBand(rs.getString("Ten_bang_tan"));
                    item.setLatitude(rs.getString("LATITUDE"));
                    item.setLongitude(rs.getString("LONGITUDE"));
                    item.setCosite2G3GType(rs.getString("COSITE_2G_3G_TYPE"));
                    item.setMaCosite2G3GType(rs.getString("MA_COSITE_2G_3G"));
                    item.setTenThietBi(rs.getString("TEN_THIET_BI"));
                    item.setTenLoaiTram(rs.getString("TEN_LOAI_TRAM"));
                    item.setCauhinh(rs.getString("CAU_HINH"));
                    item.setCauhinhSoTRX("");
                    item.setNgayBaoDuong(rs.getString("NGAY_BAO_DUONG"));
                    item.setDonViThucHien(rs.getString("DON_VI_THUC_HIEN"));
                    item.setMaKiemDinh(rs.getString("MA_KIEM_DINH"));
                    item.setNgayHieuLuc(rs.getString("NGAY_HIEU_LUC"));
                    item.setNgayHetHieuLuc(rs.getString("NGAY_HET_HIEU_LUC"));
                    item.setTenNguoiQL(rs.getString("TEN_NG_QLY"));
                    item.setSoDTNgQL(rs.getString("SDT_NG_QLY"));
                    item.setChungCSHT(rs.getString("CHUNG_CSHT"));
                    item.setLoaiTramCSHT(rs.getString("LOAI_TRAM_CSHT"));
                    item.setDocaoAnTen(rs.getString("DO_CAO_ANTEN"));
                    item.setDoCaoNhaDatAnten(rs.getString("DO_CAO_NHA_DAT_ANTEN"));
                    item.setLoaiAnTenId(rs.getString("LOAI_ANTEN_ID"));
                    item.setNgayHDTuNguon(rs.getString("NGAY_HD_TU_NGUON"));
                    item.setLoaiTuNguonId(rs.getString("LOAI_TU_NGUON_ID"));
                    item.setSoModuleTuNguon(rs.getString("SO_MODULE_TU_NGUON"));
                    item.setNgayHDMayNo(rs.getString("NGAY_HD_MAY_NO"));
                    item.setLoaiMayNoId(rs.getString("LOAI_MAY_NO_ID"));
                    item.setCongSuatMayNo(rs.getString("CONG_SUAT_MAY_NO"));
                    item.setMayNoCoDinhDiDong(rs.getString("trang_thai_dat_may_no"));
                    item.setNgayHDAccu(rs.getString("NGAY_HD_ACCU"));
                    item.setLoaiAcQuyId(rs.getString("LOAI_AC_QUY_ID"));
                    item.setDungLuongAcc(rs.getString("dung_luong_accu"));
                    item.setThoiGianHdSauMatDien(rs.getString("THOI_GIAN_HD_SAU_MAT_DIEN"));
                    item.setNgayBaoDuongAccu(rs.getString("NGAY_BAO_DUONG_ACCU"));
                    item.setLoaiTruyenDanId(rs.getString("LOAI_TRUYEN_DAN_ID"));
                    item.setDuongLuongTruyenDan(rs.getString("DUNG_LUONG_TRUYEN_DAN"));
                    item.setDienTroTiepDia(rs.getString("DIEN_TRO_TIEP_DIA"));
                    item.setLoaiCotAnten(rs.getString("loai_cot_anten"));
                    item.setMaBuilding(rs.getString("ma_building"));
                    item.setLocationNumber(rs.getString("location_number"));
                    item.setSoLuongDieuHoa(rs.getString("sl_dieu_hoa"));
                    item.setSoLuongAccuBinh(rs.getString("sl_accu_binh"));
                    item.setDienApBinh(rs.getString("dien_ap_accu"));
                    item.setDongTieuThuTuNguon(rs.getString("tieu_thu_tu_nguon"));
                    item.setDongCungCapTuNguon(rs.getString("dong_cung_cap_tu_nguon"));
                    item.setGiaoDienTruyenDan(rs.getString("giao_dien_truyen_dan"));
                    item.setTongCongSuatDieuHoa(rs.getString("tong_cs_dieuhoa"));
                    item.setLoaiHinhMayNo(rs.getString("LOAI_HINH_MAY_NO"));
                    item.setMscMss(rs.getString("cs_core"));
                    item.setSgsn(rs.getString("ps_core"));
                    return item;
                }
            }, vars);
            
            return (List<NodeBReportBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public int getTotalNodeBReport(int type, FilterForm filterForm) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_total_nodeb(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            String sWhere = "  ";
            if (filterForm != null && filterForm.getListFilterForm() != null) {
                for (int i = 0; i < filterForm.getListFilterForm().size(); i++) {

                    if (!filterForm.getListFilterForm().get(i).getColumn().equalsIgnoreCase("-1")) {
                        sWhere += " and " + Convert.convertFilter(filterForm.getListFilterForm().get(i).getColumn(), filterForm.getListFilterForm().get(i).getOperator(), filterForm.getListFilterForm().get(i).getValue_());
                    }
                }
            }

            
            vars.add(sWhere);
            vars.add(type);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int total = sqlTemplate.queryFunctionForInt(querySql, vars);
            return total;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public int findDataType(String type, String column) throws DAOException {
        Connection conn = null;
        try {
            if (column == null || column.equalsIgnoreCase("-1")) {
                return -1;
            }
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_find_data_type(?,?) }";
//       


            List<Object> vars = new Vector<Object>();

            vars.add(type);
            vars.add(column.split("\\.")[1]);
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int dataType = sqlTemplate.queryFunctionForInt(querySql, vars);
            
            return dataType;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<BcTongHopBO> findConfigReport() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_findBcTongHop() }";
//       


            List<Object> vars = new Vector<Object>();

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BcTongHopBO item = new BcTongHopBO();
                    item.setId(rs.getLong("id"));
                    item.setMaTinh(rs.getString("MA_TINH"));
                    item.setTenTinh(rs.getString("TEN_TINH"));
                    item.setSoBts(rs.getLong("SO_BTS"));
                    item.setSoBts222(rs.getLong("SO_BTS_2_2_2"));
                    item.setSoBts333(rs.getLong("SO_BTS_3_3_3"));
                    item.setSoBts444(rs.getLong("SO_BTS_4_4_4"));
                    item.setSoBtsKhac(rs.getLong("SO_BTS_KHAC"));
                    item.setSoNodeB(rs.getLong("SO_NODEB"));
                    item.setSoNodeB111U900(rs.getLong("SO_NODEB_1_1_1_U900"));
                    item.setSoNodeB111U2100(rs.getLong("SO_NODEB_1_1_1_U2100"));
                    item.setSoNodeB222(rs.getLong("SO_NODEB_2_2_2_U2100"));
                    item.setSoNodeB333(rs.getLong("SO_NODEB_3_3_3_U2100"));
                    item.setSoNodeB111111(rs.getLong("SO_NODEB_1_1_1_1_1_1"));
                    item.setSoNodeB111222(rs.getLong("SO_NODEB_1_1_1_2_2_2"));
                    item.setSoNodeB111333(rs.getLong("SO_NODEB_1_1_1_3_3_3"));
                    item.setSoNodeBKhac(rs.getLong("SO_NODEB_KHAC"));
                    return item;
                }
            }, vars);
            return (List<BcTongHopBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Detail2gBO> findDetail2G() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_findDetail2G() }";
//       


            List<Object> vars = new Vector<Object>();

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Detail2gBO item = new Detail2gBO();
                    item.setTinh(rs.getString("tinh"));
                    item.setTen(rs.getString("ten"));
                    item.setCauhinh(rs.getString("cauhinh"));
                    return item;
                }
            }, vars);
            return (List<Detail2gBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Detail3gBO> findDetail3G() throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FILTER_REPORT.fc_findDetail3G() }";
//       


            List<Object> vars = new Vector<Object>();

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Detail3gBO item = new Detail3gBO();
                    item.setTinh(rs.getString("tinh"));
                    item.setTen(rs.getString("ten"));
                    item.setCauhinh(rs.getString("cauhinh"));
                    return item;
                }
            }, vars);
            return (List<Detail3gBO>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }
}
