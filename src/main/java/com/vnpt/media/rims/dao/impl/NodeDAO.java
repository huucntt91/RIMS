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
import com.vnpt.media.rims.formbean.ApproveAllForm;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import com.vnpt.media.rims.jdbc.DbSql;
import java.sql.CallableStatement;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class NodeDAO extends GenericDAO implements INode {

    private static Logger logger = LogManager.getLogger(NodeDAO.class);

    @Override
    public List<NodeBO> findAll(String startRow, String endRow, String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fc_find_all(?,?,?,?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(startRow);
            vars.add(endRow);
            vars.add(name);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            vars.add(neTypeId);
            vars.add(venderId);
            vars.add(statusList);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NodeBO item = new NodeBO();
                    item.setId(rs.getLong("node_id"));
                    item.setNeTypeId(rs.getLong("ne_type_id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setDonViId(rs.getLong("DONVI_ID"));
                    item.setCodeBuilding(rs.getString("ma_building"));
                    item.setTenNeType(rs.getString("ten_loai_ne"));
                    item.setTenThietBi(rs.getString("ten_thiet_bi"));
                    item.setDonViName(rs.getString("ten_don_vi"));
                    item.setAddress(rs.getString("DIA_CHI"));
                    item.setTrangThaiHDId(rs.getLong("TRANG_THAI_HD_ID"));
                    item.setTrangThaiQLId(rs.getLong("TRANG_THAI_QL_ID"));
                    item.setStatus(rs.getInt("status"));
                    item.setUserInsert(rs.getLong("USER_INSERT"));
                    return item;
                }
            }, vars);

            return (List<NodeBO>) list;
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
    public List<?> findDetail(String id, String neTypeId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fc_detail(?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(neTypeId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    String neTypeId = rs.getString("ne_type_id");
                    if (neTypeId.equals("2") || neTypeId.equals("3") || neTypeId.equals("8")) //site
                    {
                        BTSInfoBO item = new BTSInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setMaNodeCha(rs.getString("ma_node_cha"));
                        item.setNodeChaId(rs.getLong("node_cha_id"));

                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));

                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getString("Latitude"));
                        item.setLon(rs.getString("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        if (neTypeId.equals("2")) {
                            item.setName(rs.getString("TEN_BTS"));
                        } else if (neTypeId.equals("3")) {
                            item.setName(rs.getString("TEN_NODEB"));
                        } else if (neTypeId.equals("8")) {
                            item.setName(rs.getString("TEN_ENODEB"));
                        }
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setCauHinh(rs.getString("cau_hinh"));
                        item.setCauHinhPortId(rs.getLong("cau_hinh_port_id"));
                        if (!neTypeId.equals("8")) {
                            item.setCosite2G3GType(rs.getInt("COSITE_2G_3G_TYPE"));
                            item.setMaCosite(rs.getString("MA_COSITE_2G_3G"));
                        }
                        item.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                        item.setTenBSCRNC(rs.getString("TEN_BSC_RNC"));
                        item.setTenBSCRNCQL(rs.getString("TEN_BSC_RNC_QL"));
                        if (neTypeId.equals("8")) {
                            item.setmSCMSS(rs.getString("MSC_MSS"));
                            item.setsGSN(rs.getString("SGSN"));
                        }
                        if (neTypeId.equals("8") || neTypeId.equals("3")) {
                            item.setdCHSPDA42M(rs.getString("DC_HSDPA_42M"));
                        }
                        item.setFilterUser(rs.getString("FILTER_USER"));
                        item.setFrequencyBand(rs.getString("FREQUENCY_BAND"));
                        item.setBangTanId(rs.getLong("BANG_TAN_ID"));

                        item.setCodeTramDA(rs.getString("ma_tram_da"));
                        item.setTramDAId(rs.getLong("TRAM_DU_AN_ID"));
                        item.setStatus(rs.getInt("STATUS"));
                        if (neTypeId.equals("8")) {
                            item.setEnodebId(rs.getString("enodeb_id"));
                        }
//                        item.setAddress(rs.getString("dia_chi"));
//                        item.setTenQuan(rs.getString("ten_quan_huyen"));
//                        item.setTenPhuong(rs.getString("ten_phuong_xa"));
                        return item;

                    } else if (neTypeId.equals("5") || neTypeId.equals("6") || neTypeId.equals("7")) //cell
                    {
                        CellInfoBO item = new CellInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setMaNodeCha(rs.getString("ma_node_cha"));
                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));

                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getFloat("Latitude"));
                        item.setLon(rs.getFloat("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        item.setName(rs.getString("TEN_cell"));
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setAzimuth(rs.getLong("Azimuth"));
                        item.setMechanitalTilt(rs.getLong("MECHANICAL_TILT"));
                        item.setTotalTilt(rs.getLong("Total_Tilt"));
                        item.setAntennaType(rs.getLong("Antenna_Type"));
                        item.setAntennaHigh(rs.getLong("antenna_High"));
                        item.setAntennaGain(rs.getLong("antenna_Gain"));
                        item.setNoOfCarrier(rs.getLong("no_Of_Carrier"));
                        item.setBosterTma(rs.getString("boster_Tma"));

                        item.setCpichPower(rs.getString("cpich_Power"));

                        item.setTotalPower(rs.getString("total_Power"));
                        item.setSpecialCoverage(rs.getString("special_Coverage"));
                        item.setBlackListFlag(rs.getString("black_List_Flag"));
                        item.setLyDo(rs.getString("ly_Do"));
                        return item;
                    } else if (neTypeId.equals("11")) //BSC RNC
                    {
                        BSCRNCInfoBO item = new BSCRNCInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setMaNodeCha(rs.getString("ma_node_cha"));
                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));
                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getFloat("Latitude"));
                        item.setLon(rs.getFloat("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        item.setName(rs.getString("TEN_BSC_RNC"));
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setTypeBSCRNC(rs.getString("TYPE_BSC_RNC"));

                        return item;
                    }
                    return null;
                }
            }, vars);

            return (List<NodeBO>) list;
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

    public List<?> findAllDetail(String nodeId, String startRow, String endRow, String code,
            String khuvucId, String tinhTpId, String quanHuyenId, String phuongXaId,
            String neTypeId, String thietBiId, String status) throws DAOException {
        Connection conn = null;
        String querySql = "";
        try {
            conn = this.getConnection();
            if (neTypeId.equals("2")) //BTS
            {
                querySql = "{? = call PKG_NODE.fc_find_all_detail_bts(?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("3")) //nodeb
            {
                querySql = "{? = call PKG_NODE.fc_find_all_detail_nodeb(?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("8")) //enodeb
            {
                querySql = "{? = call PKG_NODE.fc_find_all_detail_enodeb(?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("5")) //cell
            {
                querySql = "{? = call PKG_NODE.fc_find_all_detail_cell2g(?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("6")) //cell
            {
                querySql = "{? = call PKG_NODE.fc_find_all_detail_cell3g(?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("7")) //cell
            {
                querySql = "{? = call PKG_NODE.fc_find_all_detail_cell4g(?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("11")) //bsc rnc
            {
                querySql = "{? = call PKG_NODE.fc_find_all_detail_bsc_rnc(?,?,?,?,?,?,?,?,?,?,?) }";
            }
//       

            List<Object> vars = new Vector<Object>();
            vars.add(nodeId);
            vars.add(startRow);
            vars.add(endRow);
            vars.add(code);
            vars.add(khuvucId);
            vars.add(tinhTpId);
            vars.add(quanHuyenId);
            vars.add(phuongXaId);
            vars.add(neTypeId);
            vars.add(thietBiId);
            vars.add(status);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    String neTypeId = rs.getString("ne_type_id");

                    if (neTypeId.equals("2") || neTypeId.equals("3") || neTypeId.equals("8")) //BTS
                    {
                        BTSInfoBO item = new BTSInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setMaNodeCha(rs.getString("ma_node_cha"));
                        item.setNodeChaId(rs.getLong("node_cha_id"));

                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));

                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getString("Latitude"));
                        item.setLon(rs.getString("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        if (neTypeId.equals("2")) {
                            item.setName(rs.getString("TEN_BTS"));
                        } else if (neTypeId.equals("3")) {
                            item.setName(rs.getString("TEN_NODEB"));
                        } else if (neTypeId.equals("8")) {
                            item.setName(rs.getString("TEN_ENODEB"));
                        }
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setCauHinh(rs.getString("cau_hinh"));
                        item.setCauHinhPortId(rs.getLong("cau_hinh_port_id"));
                        if (!neTypeId.equals("8")) {
                            item.setCosite2G3GType(rs.getInt("COSITE_2G_3G_TYPE"));
                            item.setMaCosite(rs.getString("MA_COSITE_2G_3G"));
                        }

                        item.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                        item.setTenBSCRNC(rs.getString("TEN_BSC_RNC"));
                        item.setTenBSCRNCQL(rs.getString("TEN_BSC_RNC_QL"));
                        if (neTypeId.equals("3") || neTypeId.equals("8")) //BTS
                        {
                            item.setdCHSPDA42M(rs.getString("DC_HSDPA_42M"));

                            if (neTypeId.equals("8")) {
                                item.setmSCMSS(rs.getString("MSC_MSS"));
                                item.setsGSN(rs.getString("SGSN"));
                                item.setEnodebId(rs.getString("enodeb_id"));

                            }
                        }
                        item.setFilterUser(rs.getString("FILTER_USER"));
                        item.setFrequencyBand(rs.getString("FREQUENCY_BAND"));
                        item.setBangTanId(rs.getLong("BANG_TAN_ID"));

                        item.setCodeTramDA(rs.getString("ma_tram_da"));
                        item.setTramDAId(rs.getLong("TRAM_DU_AN_ID"));
                        item.setStatus(rs.getInt("STATUS"));
                        item.setUserInsert(rs.getLong("USER_INSERT"));

                        item.setTenQuan(rs.getString("ten_quan_huyen"));
                        item.setTenPhuong(rs.getString("ten_phuong_xa"));
                        item.setTrangThaiMayNo(rs.getString("trang_thai_dat_may_no"));
                        return item;

                    } else if (neTypeId.equals("5") || neTypeId.equals("6") || neTypeId.equals("7")) {
                        NodeBO nodebo = new NodeBO();
                        nodebo.setId(rs.getLong("id"));

                        nodebo.setCode(rs.getString("ma_node"));
                        nodebo.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        nodebo.setSDTQLy(rs.getString("SDT_NG_QLY"));
                        nodebo.setDonViName(rs.getString("ten_don_vi"));
                        nodebo.setNodeChaId(rs.getLong("node_cha_id"));
                        nodebo.setTenThietBi(rs.getString("ten_thiet_bi"));
                        nodebo.setThietBiId(rs.getLong("thiet_bi_id"));

                        nodebo.setCodeBuilding(rs.getString("ma_building"));
                        nodebo.setNeTypeId(rs.getLong("ne_type_id"));
                        nodebo.setTenNeType(rs.getString("ten_loai_ne"));
                        nodebo.setLoaiTramId(rs.getLong("loai_tram_id"));

                        nodebo.setTenLoaiTram(rs.getString("ten_loai_tram"));
                        nodebo.setTenTrangThaiHD(rs.getString("ten_trangthai_hd"));

                        nodebo.setTenTrangThaiQL(rs.getString("ten_trangthai_ql"));

                        nodebo.setNote(rs.getString("note"));
                        nodebo.setMaNodeCha(rs.getString("ma_node_cha"));
                        nodebo.setCodeTramDA(rs.getString("ma_tram_da"));
                        nodebo.setUserInsert(rs.getLong("user_insert"));
                        nodebo.setBuildingId(rs.getLong("building_id"));
                        nodebo.setStatus(rs.getInt("status"));

                        if (neTypeId.equals("7")) //cell4g
                        {
                            OmcCell4gInfoBO omc = new OmcCell4gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getLong("lac"));
                            omc.setCi(rs.getLong("ci"));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            omc.setPci(rs.getString("pci"));
                            omc.setTac(rs.getString("tac"));
                            omc.setLcrid(rs.getString("lcrid"));

                            omc.setCellType(rs.getLong("CELL_TYPE"));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));

                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                            Long setAzimuth = rs.getLong("AZIMUTH");
                            omc.setAzimuth(rs.wasNull() ? null : setAzimuth);

//                            omc.setAzimuth(rs.getLong("AZIMUTH"));
//                            omc.setMechanicalTilt(rs.getLong("MECHANICAL_TILT"));
                            Long setMechanicalTilt = rs.getLong("MECHANICAL_TILT");
                            omc.setMechanicalTilt(rs.wasNull() ? null : setMechanicalTilt);

//                            omc.setElectricalTilt(rs.getLong("ELECTRICAL_TILT"));
                            Long setElectricalTilt = rs.getLong("ELECTRICAL_TILT");
                            omc.setElectricalTilt(rs.wasNull() ? null : setElectricalTilt);

                            omc.setTotalTilt(rs.getLong("TOTAL_TILT"));
                            omc.setAntennaType(rs.getLong("ANTENNA_TYPE"));
                            omc.setAntennaName(rs.getString("ten_loai_anten"));
                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
                            omc.setAntennaHigh(rs.getLong("ANTENNA_HIGH"));
                            omc.setNoOfCarrier(rs.getLong("no_of_carrier"));
                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));
                            omc.setLyDo(rs.getString("ly_do"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setEnodebId(rs.getString("enodeb_id"));
                            omc.setLat(rs.getString("Latitude"));
                            omc.setLon(rs.getString("Longitude"));
                            omc.setBangTanId(rs.getLong("bang_tan_id"));
                            return omc;
                        } else if (neTypeId.equals("5")) //cell
                        {
                            OmcCell2gInfoBO omc = new OmcCell2gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getLong("lac"));
                            omc.setCi(rs.getLong("ci"));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            Long bcch = rs.getLong("bcch");
                            omc.setBcch(rs.wasNull() ? null : bcch);

                            omc.setBsic(rs.getString("bsic"));
                            omc.setTch(rs.getString("tch"));
                            omc.setTrxConfig(rs.getString("trx_config"));
                            //info
                            omc.setCellType(rs.getLong("CELL_TYPE"));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));
                            omc.setVnpCode(rs.getString("VNP_CODE"));
                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
//                            omc.setAzimuth(rs.getLong("AZIMUTH"));
                            Long setAzimuth = rs.getLong("AZIMUTH");
                            omc.setAzimuth(rs.wasNull() ? null : setAzimuth);

//                            omc.setMechanicalTilt(rs.getLong("MECHANICAL_TILT"));
                            Long setMechanicalTilt = rs.getLong("MECHANICAL_TILT");
                            omc.setMechanicalTilt(rs.wasNull() ? null : setMechanicalTilt);

//                            omc.setElectricalTilt(rs.getLong("ELECTRICAL_TILT"));
                            Long setElectricalTilt = rs.getLong("ELECTRICAL_TILT");
                            omc.setElectricalTilt(rs.wasNull() ? null : setElectricalTilt);

//                            omc.setTotalTilt(rs.getLong("TOTAL_TILT"));
                            Long setTotalTilt = rs.getLong("TOTAL_TILT");
                            omc.setTotalTilt(rs.wasNull() ? null : setTotalTilt);

//                            omc.setAntennaType(rs.getLong("ANTENNA_TYPE"));
                            Long setAntennaType = rs.getLong("ANTENNA_TYPE");
                            omc.setAntennaType(rs.wasNull() ? null : setAntennaType);

                            omc.setAntennaName(rs.getString("ten_loai_anten"));

                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
//                            omc.setAntennaHigh(rs.getLong("ANTENNA_HIGH"));
                            Long setAntennaHigh = rs.getLong("ANTENNA_HIGH");
                            omc.setAntennaHigh(rs.wasNull() ? null : setAntennaHigh);

                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));
                            omc.setLat(rs.getString("Latitude"));
                            omc.setLon(rs.getString("Longitude"));
                            omc.setBangTanId(rs.getLong("bang_tan_id"));
                            return omc;

                        } else if (neTypeId.equals("6")) //cell
                        {
                            OmcCell3gInfoBO omc = new OmcCell3gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getLong("lac"));
                            omc.setCi(rs.getLong("ci"));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            omc.setDlPsc(rs.getString("dl_psc"));
                            omc.setCpichPower(rs.getString("cpich_power"));
                            omc.setTotalPower(rs.getString("total_power"));
                            omc.setMaxPower(rs.getString("max_power"));
                            omc.setCellType(rs.getLong("CELL_TYPE"));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));

                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                            omc.setAzimuth(rs.getLong("AZIMUTH"));
                            omc.setMechanicalTilt(rs.getLong("MECHANICAL_TILT"));
                            omc.setElectricalTilt(rs.getLong("ELECTRICAL_TILT"));
                            omc.setTotalTilt(rs.getLong("TOTAL_TILT"));
                            omc.setAntennaType(rs.getLong("ANTENNA_TYPE"));
                            omc.setAntennaName(rs.getString("ten_loai_anten"));
                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
                            omc.setAntennaHigh(rs.getLong("ANTENNA_HIGH"));
                            omc.setNoOfCarrier(rs.getLong("NO_OF_CARRIER"));
                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));
                            omc.setLyDo(rs.getString("LY_DO"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setLat(rs.getString("Latitude"));
                            omc.setLon(rs.getString("Longitude"));
                            omc.setBangTanId(rs.getLong("bang_tan_id"));
                            return omc;

                        }
                    } else if (neTypeId.equals("11")) //BSC RNC
                    {
                        BSCRNCInfoBO item = new BSCRNCInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setMaNodeCha(rs.getString("ma_node_cha"));
                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));
                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getFloat("Latitude"));
                        item.setLon(rs.getFloat("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        item.setName(rs.getString("TEN_BSC_RNC"));
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setTypeBSCRNC(rs.getString("TYPE_BSC_RNC"));

                        return item;
                    }
                    return null;
                }
            }, vars);
//            
            return (List<NodeBO>) list;
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
    public int modify(String action, BuildingBO item) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_BUILDING.fn_modify(?,?,?,?,?,?,?,?,?,?)}";
            if (item == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(action);
            vars.add(item.getId());
            vars.add(item.getCode());
            vars.add(item.getAddress());
            vars.add(item.getTinhTpId());
            vars.add(item.getQuanHuyenId());
            vars.add(item.getPhuongXaId());
            vars.add(item.getDonViId());

            vars.add(item.getLat());
            vars.add(item.getLon());

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
    public int getTotalAll(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fc_total_all(?,?,?,?,?,?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(name);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            vars.add(neTypeId);
            vars.add(venderId);
            vars.add(statusList);

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

    public int getTotalDetail(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "";
            if (neTypeId.equals("2")) //BTS
            {
                querySql = "{? = call PKG_NODE.fc_total_detail_bts(?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("3")) //nodeb
            {
                querySql = "{? = call PKG_NODE.fc_total_detail_nodeb(?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("8")) //enodeb
            {
                querySql = "{? = call PKG_NODE.fc_total_detail_enodeb(?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("5")) //cell2g
            {
                querySql = "{? = call PKG_NODE.fc_total_detail_cell2g(?,?,?,?,?,?,?,?) }";

            } else if (neTypeId.equals("6")) //cell3g
            {
                querySql = "{? = call PKG_NODE.fc_total_detail_cell3g(?,?,?,?,?,?,?,?) }";

            } else if (neTypeId.equals("7")) //cell4g
            {

                querySql = "{? = call PKG_NODE.fc_total_detail_cell4g(?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("11")) //bsc rnc
            {
                querySql = "{? = call PKG_NODE.fc_total_detail_bsc_rnc(?,?,?,?,?,?,?,?) }";
            }

            List<Object> vars = new Vector<Object>();
            vars.add(name);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            vars.add(neTypeId);
            vars.add(venderId);
            vars.add(statusList);

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
    public int addNeLink(NeLinkForm bean) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.add_nodes_link(?,?,?)}";

            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(bean.getId1());
            vars.add(bean.getId2());
            vars.add(bean.getLoaiTruyenDanId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            //int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            int count = sqlTemplate.executeUpdateFuncRet(querySql, vars).intValue();

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
    public int removeNeLink(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.remove_nodes_link(?)}";

            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(id);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            //int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            int count = sqlTemplate.executeUpdateFuncRet(querySql, vars).intValue();

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
    public List<NeLinkForm> findNodeLink(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_node.fc_node_links(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NeLinkForm item = new NeLinkForm();
                    item.setId(rs.getLong("ne_link_id"));
                    item.setId2(rs.getLong("node_id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setNeTypeId(rs.getLong("ne_type_id"));
                    item.setTenLoaiTruyenDan(rs.getString("ten_loai_truyen_dan"));
                    return item;
                }
            }, vars);

            return (List<NeLinkForm>) list;
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
    public List<NeLinkForm> findNode1Link(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_map.fc_node1_links(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NeLinkForm item = new NeLinkForm();
                    item.setId(rs.getLong("ne_link_id"));
                    item.setId2(rs.getLong("node_id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setNeTypeId(rs.getLong("ne_type_id"));
                    item.setTenLoaiTruyenDan(rs.getString("ten_loai_truyen_dan"));
                    item.setLat(rs.getString("latitude"));
                    item.setLon(rs.getString("longitude"));

                    return item;
                }
            }, vars);

            return (List<NeLinkForm>) list;
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
    public List<NeLinkForm> findNode2Link(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_map.fc_node2_links(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NeLinkForm item = new NeLinkForm();
                    item.setId(rs.getLong("ne_link_id"));
                    item.setId2(rs.getLong("node_id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setNeTypeId(rs.getLong("ne_type_id"));
                    item.setTenLoaiTruyenDan(rs.getString("ten_loai_truyen_dan") == null ? "" : rs.getString("ten_loai_truyen_dan"));
                    item.setLat(rs.getString("latitude"));
                    item.setLon(rs.getString("longitude"));

                    return item;
                }
            }, vars);

            return (List<NeLinkForm>) list;
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
    public int addTram(BTSInfoBO item) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_add_tram(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            if (item == null) {
                return -1;
            }
            //logger.info("SQL : " + querySql);

            List<Object> vars = new Vector<Object>();
            vars.add(item.getCode());
            vars.add(item.getName());
            vars.add(item.getNeTypeId());
            vars.add(item.getDonViId());
            vars.add(item.getThietBiId());
            vars.add(item.getLoaiTramId());
            vars.add(item.getTenNgQLy());
            vars.add(item.getHoanCanhRaDoi());
            vars.add(item.getCauHinhPortId());
            vars.add(item.getNgayHoatDong());
            vars.add(item.getNodeChaId());
            vars.add(item.getTramDAId());
            vars.add(item.getBuildingId());
            vars.add(item.getEnodebId());
            vars.add(item.getTenTrenHeThong());
            vars.add(item.getUserInsert());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            //int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            int count = sqlTemplate.executeUpdateFuncRet(querySql, vars).intValue();

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
    public List<ClassAttributeBO> getAttributeFormName(String id) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_node.fc_getClassByNodeId(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

                    ClassAttributeBO item = new ClassAttributeBO();
                    item.setId(rs.getLong("attr_class_id"));
                    item.setAttrClassName(rs.getString("attr_class_name"));

                    return item;
                }
            }, vars);

            return (List<ClassAttributeBO>) list;
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
    public List<AttributeBO> getAttributeByClass(String id) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_node.fc_getAttributeByClass(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

                    AttributeBO item = new AttributeBO();
                    item.setId(rs.getLong("attr_class_id"));
                    item.setAttrName(rs.getString("attr_name"));

                    return item;
                }
            }, vars);

            return (List<AttributeBO>) list;
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

    public void updateNode(NodeBO nodeBO) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call pkg_node.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (nodeBO == null) {
                return;
            }

            List<Object> vars = new Vector<Object>();

            vars.add(nodeBO.getId());
            vars.add(nodeBO.getCode());
            vars.add(nodeBO.getDonViId());
            vars.add(nodeBO.getThietBiId() == null || nodeBO.getThietBiId() == -1 ? null : nodeBO.getThietBiId());

            if (nodeBO.getBuildingId() != null && nodeBO.getBuildingId() == 0) {
                vars.add(null);
            } else {
                vars.add(nodeBO.getBuildingId());
            }
            vars.add(nodeBO.getNeTypeId());
            vars.add(nodeBO.getLoaiTramId() == null || nodeBO.getLoaiTramId() == -1 ? null : nodeBO.getLoaiTramId());

            vars.add((nodeBO.getTrangThaiHDId() == null || nodeBO.getTrangThaiHDId() == -1) ? null : nodeBO.getTrangThaiHDId());
            vars.add((nodeBO.getTrangThaiQLId() == null || nodeBO.getTrangThaiQLId() == -1) ? null : nodeBO.getTrangThaiQLId());
            vars.add(nodeBO.getNodeChaId());
            vars.add(nodeBO.getTenNgQLy());
            vars.add(nodeBO.getSDTQLy());

            vars.add(nodeBO.getStatus());
            vars.add("");
            vars.add(nodeBO.getUserUpdate());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                logger.debug("Records resetPassword : " + count);
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

    public Long addNode(NodeBO nodeBO) throws DAOException {
        Connection conn = null;
        Long nodeId = -1l;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call pkg_node.fn_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (nodeBO == null) {
                return null;
            }

            List<Object> vars = new Vector<Object>();

//            vars.add(nodeBO.getId());
            vars.add(nodeBO.getCode());
            vars.add(nodeBO.getDonViId());
            vars.add(nodeBO.getThietBiId());
            vars.add(nodeBO.getBuildingId());
            vars.add(nodeBO.getNeTypeId());
            vars.add(nodeBO.getLoaiTramId());
            vars.add(nodeBO.getTrangThaiHDId());
            vars.add(nodeBO.getTrangThaiQLId());
            vars.add(nodeBO.getNodeChaId());
            vars.add(nodeBO.getTenNgQLy());
            vars.add(nodeBO.getSDTQLy());
            vars.add(nodeBO.getStatus());
            vars.add("");
            vars.add(null);
            vars.add(nodeBO.getUserInsert());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            nodeId = sqlTemplate.executeUpdateFuncRet(querySql, vars);
            if (nodeId > 0l) {
                logger.debug("Records  : " + nodeId);
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
        return nodeId;
    }

    @Override
    public void updateCellInfo(CellInfoBO cellInfoBO) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_CELL_INFO.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (cellInfoBO == null) {
                return;
            }

            List<Object> vars = new Vector<Object>();
//            
            vars.add(cellInfoBO.getId());
            vars.add(cellInfoBO.getCellType());
            vars.add(cellInfoBO.getNgayHoatDong());
            vars.add(cellInfoBO.getHoanCanhRaDoi());
            vars.add(cellInfoBO.getTenNeType());
            vars.add(cellInfoBO.getNgayDangKy());
            vars.add(cellInfoBO.getNgayKiemDuyet());
            vars.add(cellInfoBO.getNgayCapPhep());
            vars.add(cellInfoBO.getLat());
            vars.add(cellInfoBO.getLon());
            vars.add(cellInfoBO.getAzimuth());
            vars.add(cellInfoBO.getMechanitalTilt());
            vars.add(cellInfoBO.getTotalTilt());
            vars.add(cellInfoBO.getAntennaType());
            vars.add(cellInfoBO.getAntennaHigh());
            vars.add(cellInfoBO.getAntennaGain());
            vars.add(cellInfoBO.getNoOfCarrier());
            vars.add(cellInfoBO.getBosterTma());
            vars.add(cellInfoBO.getCpichPower());
            vars.add(cellInfoBO.getTotalPower());
            vars.add(cellInfoBO.getSpecialCoverage());
            vars.add(cellInfoBO.getBlackListFlag());
            vars.add(cellInfoBO.getLyDo());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                logger.debug("Records resetPassword : " + count);
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
    public void addCellInfo(CellInfoBO cellInfoBO) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_CELL_INFO.fn_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (cellInfoBO == null) {
                return;
            }

            List<Object> vars = new Vector<Object>();
//            
            vars.add(cellInfoBO.getId());
            vars.add(cellInfoBO.getCellType());
            vars.add(cellInfoBO.getNgayHoatDong());
            vars.add(cellInfoBO.getHoanCanhRaDoi());
            vars.add(cellInfoBO.getTenNeType());
            vars.add(cellInfoBO.getNgayDangKy());
            vars.add(cellInfoBO.getNgayKiemDuyet());
            vars.add(cellInfoBO.getNgayCapPhep());
            vars.add(cellInfoBO.getLat());
            vars.add(cellInfoBO.getLon());
            vars.add(cellInfoBO.getAzimuth());
            vars.add(cellInfoBO.getMechanitalTilt());
            vars.add(cellInfoBO.getTotalTilt());
            vars.add(cellInfoBO.getAntennaType());
            vars.add(cellInfoBO.getAntennaHigh());
            vars.add(cellInfoBO.getAntennaGain());
            vars.add(cellInfoBO.getNoOfCarrier());
            vars.add(cellInfoBO.getBosterTma());
            vars.add(cellInfoBO.getCpichPower());
            vars.add(cellInfoBO.getTotalPower());
            vars.add(cellInfoBO.getSpecialCoverage());
            vars.add(cellInfoBO.getBlackListFlag());
            vars.add(cellInfoBO.getLyDo());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                logger.debug("Records resetPassword : " + count);
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
    public void addOmcCellInfo(OmcCellInfoBO omcCellInfoBO) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();

//            String querySql = "update CMS_USER set password = ? where id = ?";
            String querySql = "{? = call PKG_OMC_CELL_INFO.fn_insert(?,?,?,?,?,?,?,?)}";

            if (omcCellInfoBO == null) {
                return;
            }

            List<Object> vars = new Vector<Object>();
//            
            vars.add(omcCellInfoBO.getOmcCellId());
            vars.add(omcCellInfoBO.getTenTrenHeThong());
            vars.add(omcCellInfoBO.getLac());
            vars.add(omcCellInfoBO.getCi());
            vars.add(omcCellInfoBO.getTenBscRnc());
            vars.add(omcCellInfoBO.getCode());
            vars.add(omcCellInfoBO.getDcHsdpa42M());
            vars.add(omcCellInfoBO.getBangTanId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                logger.debug("Records resetPassword : " + count);
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

    // list popup list tram du an
    @Override
    public List<TramDuAnBO> findAllTramDuAn(String startRow, String endRow, String name, String tinhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.find_all_tram_du_an(?,?,?,?) }";
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
                    item.setId(rs.getLong("tram_du_an_id"));
                    item.setCode(rs.getString("ma_tram_da"));
                    item.setName(rs.getString("ten_tram"));
                    item.setAddress(rs.getString("dia_chi"));
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
    public int getTotalAllTramDuAn(String name, String tinhId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fc_total_all_tram_du_an(?,?) }";
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
    public String getCodeVNPT(String tinhCode, String ne, String status) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.find_max_code_vnpt(?,?,?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(tinhCode);
            vars.add(ne);
            vars.add(status);
            DbSql sqlTemplate = new DbSql(conn);
            String vnptCode = sqlTemplate.runProcOutString(querySql, vars);
            return vnptCode;
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

    public List<CellNewForm> findCell(Long nodeId, String statusList, String tinhTpId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_find_all(?,?,?)}";

            List<Object> vars = new Vector<Object>();
//            vars.add(id);
//            }
//            if (StringUtils.hasText(name)) {
//            vars.add("%" + name.toLowerCase() + "%");
//            }

            vars.add(nodeId);
            vars.add(statusList);
            vars.add(tinhTpId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CellNewForm temp = new CellNewForm();
//                    ChildUserType.loai_tram_id,ChildUserType.node_Id, ChildUserType.ma_node, ParentUserType.node_Id, 
                    //ParentUserType.ma_node ma_cha,
//c.hoan_canh_ra_doi, c.ngay_hoat_dong, ChildUserType.ten_ng_qly,c.NO_OF_CARRIER,
//omc.ten_tren_he_thong, omc.lac, omc.ci, omc.frequency_band,
//thiet_bi.TEN_THIET_BI,loai_tram.TEN_LOAI_TRAM,
//tinh_tp.TEN_TINH_TP
                    CellInfoBO c = new CellInfoBO();
                    c.setUserInsert(rs.getLong("user_insert"));
                    c.setUserUpdate(rs.getLong("user_update"));
                    c.setInsertDate(rs.getDate("insert_date"));
                    c.setUpdateDate(rs.getDate("update_date"));
                    c.setNodeId(rs.getLong("node_Id"));
                    c.setCode(rs.getString("ma_node"));
                    c.setNote(rs.getString("note"));
                    c.setStatus(rs.getInt("status"));
                    Float latitude = rs.getFloat("latitude");
                    c.setLat(rs.wasNull() ? null : latitude);
                    Float longitude = rs.getFloat("longitude");
                    c.setLon(rs.wasNull() ? null : longitude);
                    Long azimuth = rs.getLong("AZIMUTH");
                    c.setAzimuth(rs.wasNull() ? null : azimuth);
                    Long mechanicalTilt = rs.getLong("MECHANICAL_TILT");
                    c.setMechanitalTilt(rs.wasNull() ? null : mechanicalTilt);
                    Long totalTilt = rs.getLong("TOTAL_TILT");
                    c.setTotalTilt(rs.wasNull() ? null : totalTilt);
                    Long antennaType = rs.getLong("ANTENNA_TYPE");
                    c.setAntennaType(rs.wasNull() ? null : antennaType);
                    Long antennaHigh = rs.getLong("ANTENNA_HIGH");
                    c.setAntennaHigh(rs.wasNull() ? null : antennaHigh);
                    Long antennaGain = rs.getLong("ANTENNA_GAIN");
                    c.setAntennaGain(rs.wasNull() ? null : antennaGain);
                    Long neTypeId = rs.getLong("NE_TYPE_ID");
                    Long nodeChaId = rs.getLong("NODE_CHA_ID");
                    c.setNodeChaId(rs.wasNull() ? null : nodeChaId);
                    c.setNeTypeId(rs.wasNull() ? null : neTypeId);
                    temp.setBtsCode(rs.getString("ma_cha"));

                    c.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    c.setNgayHoatDong(rs.getDate("ngay_hoat_dong"));
                    c.setTenNgQLy(rs.getString("ten_ng_qly"));
                    Long noOfCarrier = rs.getLong("NO_OF_CARRIER");
                    c.setNoOfCarrier(rs.wasNull() ? null : noOfCarrier);

                    Long trangthaiHDId = rs.getLong("trang_thai_hd_id");

                    c.setTrangThaiHDId(rs.wasNull() ? null : trangthaiHDId);
                    Long trangthaiqlid = rs.getLong("trang_thai_ql_id");
                    c.setTrangThaiQLId(rs.wasNull() ? null : trangthaiqlid);
                    c.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                    c.setBlackListFlag(rs.getString("BLACK_LIST_FLAG"));
                    c.setLyDo(rs.getString("LY_DO"));

                    OmcCellInfoBO omc = new OmcCellInfoBO();
                    omc.setOmcCellId(c.getNodeId());
                    omc.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));

                    Long lac = rs.getLong("lac");
                    omc.setLac(rs.wasNull() ? null : lac);
                    Long ci = rs.getLong("ci");
                    omc.setCi(rs.wasNull() ? null : ci);
//                    omc.setFrequencyBand(rs.getString("FREQUENCY_BAND"));
                    omc.setBangTanId(rs.getLong("BANG_TAN_ID"));
                    omc.setBangTanName(rs.getString("TEN_BANG_TAN"));
                    ThietBiBO thietBi = new ThietBiBO();
                    thietBi.setTenThietBi(rs.getString("TEN_THIET_BI"));
                    thietBi.setThietBiId(rs.getLong("THIET_BI_ID"));
                    LoaiTramBO loaiTram = new LoaiTramBO();
                    loaiTram.setTenLoaiTram(rs.getString("TEN_LOAI_TRAM"));
                    Long loaiTramId = c.getNeTypeId();
                    loaiTram.setLoaiTramId(rs.wasNull() ? null : loaiTramId);
                    temp.setTenTinhTP(rs.getString("TEN_TINH_TP"));
                    LoaiNeBO loaiNE = new LoaiNeBO();
                    loaiNE.setId(rs.getLong("ne_type_id"));
                    temp.setCellInfo(c);
                    temp.setOmcCellInfo(omc);
                    temp.setThietBi(thietBi);
                    temp.setLoaiTram(loaiTram);
                    temp.setLoaiNE(loaiNE);

                    return temp;
                }

            }, vars);

            return (List<CellNewForm>) list;
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

//    trunglk_start
    public int getTotal(String maDuAn,
            String tenDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();

            String querySql = "{? = call PKG_NODE.fc_get_total_stationPlans(?,?,?,?,?,?)}";

            List<Object> vars = new Vector<Object>();

//            if (StringUtils.hasText(fullname)) {
            vars.add(maDuAn == null ? "" : maDuAn);
            vars.add(tenDuAn == null ? "" : tenDuAn);
            vars.add(msHopDong == null ? "" : msHopDong);
            vars.add(maTramBTS == null ? "" : maTramBTS);
            vars.add(maTramNodeB == null ? "" : maTramNodeB);
            vars.add(maTramQuyHoach == null ? "" : maTramQuyHoach);
//            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            return sqlTemplate.queryFunctionForInt(querySql, vars);

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

    public List<TramDuAnBO> findTramDAAll(String maDuAn,
            String tenDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach, int startRow, int endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fc_search_stationPlans(?,?,?,?,?,?,?,?)}";

            List<Object> vars = new Vector<Object>();

//            if (StringUtils.hasText(fullname)) {
//                vars.add("%" + fullname.trim().toLowerCase() + "%");
//            }
            vars.add(maDuAn == null ? "" : maDuAn);
            vars.add(tenDuAn == null ? "" : tenDuAn);
            vars.add(msHopDong == null ? "" : msHopDong);
            vars.add(maTramBTS == null ? "" : maTramBTS);
            vars.add(maTramNodeB == null ? "" : maTramNodeB);
            vars.add(maTramQuyHoach == null ? "" : maTramQuyHoach);
            vars.add(startRow);
            vars.add(endRow);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TramDuAnBO tramDABO = new TramDuAnBO();
                    tramDABO.setId(rs.getLong("tram_du_an_id"));
                    tramDABO.setMaDuAn(rs.getString("ma_du_an"));
                    tramDABO.setTenDuAn(rs.getString("ten_du_an"));
                    tramDABO.setMaQuyHoach(rs.getString("ma_quy_hoach"));
                    tramDABO.setTenQuyHoach(rs.getString("ten_quy_hoach"));
                    tramDABO.setMaSoHopDong(rs.getString("ma_so_hop_dong"));
                    tramDABO.setAddress(rs.getString("dia_chi"));
                    tramDABO.setMaTramDuAn(rs.getString("ma_tram_da"));
                    tramDABO.setTenTramDuAn(rs.getString("ten_tram"));
                    tramDABO.setMaTramBTS(rs.getString("ma_tram_bts"));
                    tramDABO.setMaTramNodeB(rs.getString("ma_tram_nodeB"));
                    tramDABO.setMaTramQuyHoach(rs.getString("ma_tram_quy_hoach"));
                    tramDABO.setHienTrangTram(rs.getString("hien_trang_tram"));
                    tramDABO.setLongitude(rs.getString("longitude"));
                    tramDABO.setLatitude(rs.getString("latitude"));
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

    public void addTramDA(TramDuAnBO tramDABO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_add_tram_da(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

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
            vars.add(tramDABO.getAddress().trim());
            vars.add(tramDABO.getMaTramDuAn().trim());
            vars.add(tramDABO.getTenTramDuAn().trim());
            vars.add(tramDABO.getMaTramBTS().trim());
            vars.add(tramDABO.getMaTramNodeB().trim());
            vars.add(tramDABO.getMaTramNodeB().trim());
            vars.add(tramDABO.getHienTrangTram());
            vars.add(tramDABO.getLongitude());
            vars.add(tramDABO.getLatitude());

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
//    trunglk_end

    @Override
    public OmcCellInfoBO findOmcCellInfo(String id) throws ServiceException {
        Connection conn = null;
        try {

            if (!StringUtils.hasText(id)) {
                return null;
            }

            conn = this.getConnection();

            String querySql = "{? = call PKG_OMC_CELL_INFO.fn_find_all(?)}";

            List<Object> vars = new Vector<Object>();
            vars.add(id.trim().toLowerCase());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            OmcCellInfoBO omcCellInfoBO = (OmcCellInfoBO) sqlTemplate.queryFunctionForObject(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OmcCellInfoBO temp = new OmcCellInfoBO();
                    temp.setOmcCellId(rs.getLong("NODE_ID"));
                    temp.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                    temp.setLac(rs.getLong("Lac"));
                    temp.setCi(rs.getLong("ci"));
                    temp.setTenBscRnc(rs.getString("TEN_BSC_RNC"));
                    temp.setCode(rs.getString("code"));
                    temp.setDcHsdpa42M(rs.getString("DC_HSDPA_42M"));
                    temp.setBangTanId(rs.getLong("bang_tan_id"));
                    temp.setBangTanName(rs.getString("TEN_BANG_TAN"));
                    return temp;  //To change body of implemented methods use File | Settings | File Templates.
                }
            }, vars);

            return omcCellInfoBO;

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
    public void updateOmcCellInfo(OmcCellInfoBO omcCellInfoBO) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_OMC_CELL_INFO.fn_update(?,?,?,?,?,?,?,?)}";

            if (omcCellInfoBO == null) {
                return;
            }

            List<Object> vars = new Vector<Object>();
            vars.add(omcCellInfoBO.getOmcCellId());
            vars.add(omcCellInfoBO.getTenTrenHeThong());
            vars.add(omcCellInfoBO.getLac());
            vars.add(omcCellInfoBO.getCi());
            vars.add(omcCellInfoBO.getTenBscRnc());
            vars.add(omcCellInfoBO.getCode());
            vars.add(omcCellInfoBO.getDcHsdpa42M());
            vars.add(omcCellInfoBO.getBangTanId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                logger.debug("update omccellinfo : " + count);
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
    public List<NodeBO> findByNodeChaId(Long nodeChaId, String statusList) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_find_all_by_node_cha_id(?,?)}";

            List<Object> vars = new Vector<Object>();
            vars.add(nodeChaId);
            vars.add(statusList);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NodeBO temp = new NodeBO();
                    temp.setCode(rs.getString("MA_NODE"));
                    return temp;
                }

            }, vars);

            return (List<NodeBO>) list;
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
    public List<NodeBO> findIdByMaNode(String maNode) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_find_id_by_ma_node(?)}";

            List<Object> vars = new Vector<Object>();
            vars.add(maNode);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NodeBO temp = new NodeBO();
                    temp.setCode(rs.getString("MA_NODE"));
                    temp.setId(rs.getLong("node_id"));
                    return temp;
                }

            }, vars);

            return (List<NodeBO>) list;
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
    public void approveCell(ApproveForm approveForm, String maNode, Long userUpdate) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_approve(?,?,?,?,?)}";

            if (approveForm == null) {
                return;
            }

            List<Object> vars = new Vector<Object>();

            vars.add(approveForm.getNodeId());
            if (maNode != null && !maNode.equals("")) {
                vars.add(maNode);
            } else {
                vars.add(null);
            }

            vars.add(approveForm.getStatus());
            vars.add(approveForm.getComment());
            vars.add(userUpdate);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                logger.debug("update omccellinfo : " + count);
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
    public List<BTSInfoBO> findDuyetTram(Long nodeId, String statusList, String tinhTpId) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_find_all_tram(?,?,?)}";

            List<Object> vars = new Vector<Object>();
            vars.add(nodeId);
            vars.add(statusList);
            vars.add(tinhTpId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BTSInfoBO item = new BTSInfoBO();

                    item.setId(rs.getLong("node_Id"));
                    item.setCode(rs.getString("ma_node"));
                    item.setNote(rs.getString("note"));
                    item.setStatus(rs.getInt("status"));

                    item.setNeTypeId(rs.getLong("NE_TYPE_ID"));
                    item.setTenNeType(rs.getString("ten_loai_ne"));
                    item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                    item.setNgayHoatDong(rs.getDate("ngay_hoat_dong"));
                    item.setTenNgQLy(rs.getString("ten_ng_qly"));
                    item.setCauHinh(rs.getString("cau_hinh"));
                    item.setCodeBuilding(rs.getString("ma_building"));
                    item.setTenThietBi(rs.getString("ten_thiet_bi"));
                    item.setTenLoaiTram(rs.getString("ten_loai_tram"));
                    item.setTenTinhTp(rs.getString("ten_tinh_tp"));
                    item.setName(rs.getString("ten_tram"));
                    item.setTenTrenHeThong(rs.getString("ten_tren_he_thong"));
                    item.setMaNodeCha(rs.getString("ma_node_cha"));
                    item.setCodeTramDA(rs.getString("ma_tram_da"));
                    item.setDonViName(rs.getString("ten_don_vi"));
                    item.setCodeProvince(rs.getString("ma_tinh_tp"));
                    return item;
                }

            }, vars);

            return (List<BTSInfoBO>) list;
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
    public int findSpecialCell(Long buildingId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.find_special_cell(?)}";

            List<Object> vars = new Vector<Object>();
            vars.add(buildingId);
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
    public void updateTramInfo(BTSInfoBO item) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_BTS_INFO.fn_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            if (item == null) {
                return;
            }

            List<Object> vars = new Vector<Object>();
            vars.add(item.getNeTypeId());
            vars.add(item.getId());
            vars.add(item.getNgayHoatDong());
            vars.add(item.getHoanCanhRaDoi());
            vars.add(item.getName());
            vars.add(item.getNgayDangKy());
            vars.add(item.getNgayKiemDuyet());
            vars.add(item.getNgayCapPhep());
            vars.add(item.getLat());
            vars.add(item.getLon());
            vars.add(item.getCauHinhPortId());
            vars.add(item.getMaCosite());
            vars.add(item.getCosite2G3GType());
            // update omc
            vars.add(item.getTenTrenHeThong());
            vars.add(item.getTenBSCRNC());
            vars.add(item.getTenBSCRNCQL());
            vars.add(item.getmSCMSS());
            vars.add(item.getsGSN());
            vars.add(item.getdCHSPDA42M());
            vars.add(item.getFilterUser());
            vars.add(item.getBangTanId());
            vars.add(item.getUserUpdate());
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
            if (count > 0) {
                logger.debug("update omc info tram : " + count);
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
    public String importCell(String permission, String maNode, long userInsertId, ImportCellModel importcellModel) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_EXCEL_NODE.fn_import_cell(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(permission);
            vars.add(importcellModel.getNe_name());
            vars.add(maNode);
            vars.add(importcellModel.getMaNodeCha());
            vars.add(importcellModel.getTenChoQuanLy());
            vars.add(importcellModel.getHoanCanhRaDoi());
            vars.add(importcellModel.getNgayHoatDong());
            vars.add(importcellModel.getTenTrenHeThong());
            vars.add(importcellModel.getLac());
            vars.add(importcellModel.getCi());
            vars.add(importcellModel.getLoaiCongNghe());
            vars.add(importcellModel.getFrequenctyBand());
            vars.add(importcellModel.getTenThietBi());
            vars.add(importcellModel.getTenTram());
            vars.add(importcellModel.getNoOfCarrier());

            vars.add(2l);
            vars.add(1l);
            vars.add(Constants.NE_REG_ON);
            vars.add(userInsertId);
            for (int i = 0; i < vars.size(); i++) {

            }

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
//            int count = Integer.parseInt(sqlTemplate.runProc(querySql, vars));
            long out = sqlTemplate.executeUpdateFuncRet(querySql, vars);

            return String.valueOf(out);//String.valueOf(count);

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (SQLException e) {

            logger.error("SqlException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }

    @Override
    public String importDkBts(String codeVNPT, long userInsertId, ImportBtsModel importBtsModel, RedirectAttributes attr, MessageSource messageSource, Locale locale) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_EXCEL_NODE.fn_import_bts(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(importBtsModel.getMaDK());
            vars.add(importBtsModel.getNeTypeName());
            vars.add(importBtsModel.getMaBscRnc());
            vars.add(importBtsModel.getMaTramDuAn());
            vars.add(importBtsModel.getTenDonViQuanLy());
            vars.add(importBtsModel.getMaBuilding());
            vars.add(importBtsModel.getNgayHoatDong());
            vars.add(importBtsModel.getHoanCanhRaDoi());
            vars.add(importBtsModel.getTenNguoiQuanLy());
            vars.add(importBtsModel.getTenChoQuanLy());
            vars.add(importBtsModel.getTenTrenHeThong());
            vars.add(importBtsModel.getThietBi());
            vars.add(importBtsModel.getLoaiTram());
            vars.add(importBtsModel.getCauHinh());
            vars.add(importBtsModel.getEnodebId());
            vars.add(userInsertId);
            vars.add(importBtsModel.getDeviceType());
            vars.add(importBtsModel.getFrequencyBand());

            for (int i = 0; i < vars.size(); i++) {

            }
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String out = sqlTemplate.executeUpdateFunction2(querySql, vars);
            return out == null ? "" : out;//String.valueOf(count);

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
    public String excelCellUpdate(String permission, long userInsertId, ExcelCellUpdateBO excelCellUpdateBO) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();
            String querySql = "{? = call PKG_EXCEL_NODE.fn_update_cell(?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?)}";
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(permission);
            vars.add(excelCellUpdateBO.getMaCell());
            vars.add(excelCellUpdateBO.getKieuCell());
            vars.add(excelCellUpdateBO.getMaNodeCha());
            vars.add(excelCellUpdateBO.getTenChoQuanLy());
            vars.add(excelCellUpdateBO.getHoanCanhRaDoi());
            vars.add(excelCellUpdateBO.getNgayHoatDong());
            vars.add(excelCellUpdateBO.getTenTrenHeThong());
            vars.add(excelCellUpdateBO.getLac());
            vars.add(excelCellUpdateBO.getCi());
            vars.add(excelCellUpdateBO.getLoaiCongNghe());
            vars.add(excelCellUpdateBO.getFrequencyBand());
            vars.add(excelCellUpdateBO.getThietBi());
            vars.add(excelCellUpdateBO.getLoaiTram());
            vars.add(excelCellUpdateBO.getNoOfCarrier());
            vars.add(excelCellUpdateBO.getBlacklist());
            vars.add(excelCellUpdateBO.getNgayPheDuyet());
            vars.add(excelCellUpdateBO.getLydo());
            vars.add(excelCellUpdateBO.getLat());
            vars.add(excelCellUpdateBO.getLon());
            vars.add(excelCellUpdateBO.getAzimuth());
            vars.add(excelCellUpdateBO.getMechanicalTilt());
            vars.add(excelCellUpdateBO.getTotalTilt());
            vars.add(excelCellUpdateBO.getAntennaType());
            vars.add(excelCellUpdateBO.getAntennaHigh());
            vars.add(excelCellUpdateBO.getAntennaGain());
            vars.add(userInsertId);

            for (int i = 0; i < vars.size(); i++) {

            }
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            long out = sqlTemplate.executeUpdateFuncRet(querySql, vars);
            return String.valueOf(out);//String.valueOf(count);
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
//        return "ok";
    }

    @Override
    public String excelBtsUpdate(String permission, long userInsertId, ExcelBtsUpdateBO excelBtsUpdateBO) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();
            String querySql = "{? = call PKG_EXCEL_NODE.fn_update_bts(?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?)}";
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(permission);
            vars.add(excelBtsUpdateBO.getLoaiNE());
            vars.add(excelBtsUpdateBO.getMaBts());
            vars.add(excelBtsUpdateBO.getBscCode());
            vars.add(excelBtsUpdateBO.getMaTramDuAn());
            vars.add(excelBtsUpdateBO.getMaBuilding());
            vars.add(excelBtsUpdateBO.getTenNguoiQuanLy());
            vars.add(excelBtsUpdateBO.getSdtNguoiQuanLy());
            vars.add(excelBtsUpdateBO.getTenChoQuanLy());
            vars.add(excelBtsUpdateBO.getHoanCanhRaDoi());
            vars.add(excelBtsUpdateBO.getNgayHoatDong());
            vars.add(excelBtsUpdateBO.getTenTrenHeThong());
            vars.add(null);
            vars.add(excelBtsUpdateBO.getMscMss());
            vars.add(excelBtsUpdateBO.getSgsn());
            vars.add(excelBtsUpdateBO.getDcHsdpa42M());
            vars.add(excelBtsUpdateBO.getFilterUser());
            vars.add(excelBtsUpdateBO.getLoaiCongNghe());
            vars.add(excelBtsUpdateBO.getFrequencyBand());
            vars.add(excelBtsUpdateBO.getEnodebId());
            vars.add(excelBtsUpdateBO.getThietBi());
            vars.add(excelBtsUpdateBO.getLoaiTram());
            vars.add(excelBtsUpdateBO.getCauHinh());
            vars.add(userInsertId);
            vars.add(excelBtsUpdateBO.getDeviceType());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String out = sqlTemplate.executeUpdateFunction2(querySql, vars);
            return String.valueOf(out);//String.valueOf(count);
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
//        return "ok";
    }

    @Override
    public String excelDeleteNode(String permission, long userInsertId, ExcelDeleteNodeBO excelDeleteNodeBO) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();
            String querySql = "{? = call PKG_EXCEL_NODE.fn_delete_node(?,?,?,?)}";
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<>();
            vars.add(excelDeleteNodeBO.getLoaiNE());
            vars.add(excelDeleteNodeBO.getCode());
            vars.add(excelDeleteNodeBO.getLyDo());
            vars.add(userInsertId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            String out = sqlTemplate.executeUpdateFunction2(querySql, vars);
            return out;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public String excelDestroyNode(String permission, long userInsertId, ExcelDeleteNodeBO excelDeleteNodeBO) throws DAOException {
        Connection conn = null;
        try {

            conn = this.getConnection();
            String querySql = "{? = call PKG_EXCEL_NODE.fn_destroy_node(?,?,?,?)}";
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(excelDeleteNodeBO.getLoaiNE());
            vars.add(excelDeleteNodeBO.getCode());
            vars.add(excelDeleteNodeBO.getLyDo());
            vars.add(userInsertId);
            for (int i = 0; i < vars.size(); i++) {

            }
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            long out = sqlTemplate.executeUpdateFuncRet(querySql, vars);
            return String.valueOf(out);//String.valueOf(count);
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
    public int updateOmcBtsInfo(BTSInfoBO bo, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_OMC_BTS_INFO.fn_update(?,?,?,?,?,?,?)}";

            if (bo == null) {
                return 1;
            }

            List<Object> vars = new Vector<Object>();

            vars.add(bo.getId());
            vars.add(bo.getTenTrenHeThong());
            vars.add(bo.getTenBSCRNC());
            vars.add(bo.getTenBSCRNCQL());
            vars.add(bo.getFilterUser());
            vars.add(bo.getBangTanId());
            vars.add(userId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
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
    public int updateOmcNodeBInfo(Long nodeId, String tenTrenHeThong, String tenBSCRNC, String tenBSCRNCQly,
            String dcHsdpa42M, String fileterUser, String bangTanId, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_OMC_NODEB_INFO.fn_update(?,?,?,?,?,?,?,?)}";

            List<Object> vars = new Vector<Object>();

            vars.add(nodeId);
            vars.add(tenTrenHeThong);
            vars.add(tenBSCRNC);
            vars.add(tenBSCRNCQly);
            vars.add(dcHsdpa42M);
            vars.add(fileterUser);
            vars.add(bangTanId);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
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
    public int updateOmcEnodeBInfo(Long nodeId,
            String tenTrenHeThong, String tenBSCRNC, String tenBSCRNCQly,
            String MscMss, String sgsn, String dcHsdpa42M, String fileterUser, Long bangTanId, Long userId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_OMC_ENODEB_INFO.fn_update(?,?,?,?,?,?,?,?,?,?)}";

            List<Object> vars = new Vector<Object>();

            vars.add(nodeId);
            vars.add(tenTrenHeThong);
            vars.add(tenBSCRNC);
            vars.add(tenBSCRNCQly);
            vars.add(MscMss);
            vars.add(sgsn);
            vars.add(dcHsdpa42M);
            vars.add(fileterUser);
            vars.add(bangTanId);
            vars.add(userId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
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
    public int swapNodeB(Long rNodebId, Long vendor, long userId, String iBscRncName) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_swap_nodeb(?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(rNodebId);
            vars.add(vendor);
            vars.add(iBscRncName);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int swapENodeB(Long reNodebId, Long iVendor, long userId, String iBscRncName) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_swap_enodeb(?,?,?,?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(reNodebId);
            vars.add(iVendor);
            vars.add(iBscRncName);
            vars.add(userId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public int swapBts(Long btsId, long userId, String iBscRncName, Long vendor) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_swap_bts(?,?,?,?) }";

            List<Object> vars = new Vector<Object>();

            vars.add(btsId);

            vars.add(vendor);
            vars.add(iBscRncName);
            vars.add(userId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            int iRet = sqlTemplate.executeUpdateFunction(querySql, vars);
            return iRet;
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
    public List<?> findAllNodeDetailMap(String nodeId, String neTypeId) throws DAOException {
        Connection conn = null;
        String querySql = "";
        try {
            conn = this.getConnection();
            if (neTypeId.equals("2")) //BTS
            {
                querySql = "{? = call pkg_map.f_list_map_bts(?) }";
            } else if (neTypeId.equals("3")) //nodeb
            {
                querySql = "{? = call pkg_map.f_list_map_nodeb(?) }";
            } else if (neTypeId.equals("8")) //enodeb
            {
                querySql = "{? = call pkg_map.f_list_map_enodeb(?) }";
            } else if (neTypeId.equals("5")) //cell
            {
                querySql = "{? = call pkg_map.f_list_map_cell2g(?) }";
            } else if (neTypeId.equals("6")) //cell
            {
                querySql = "{? = call pkg_map.f_list_map_cell3g(?) }";
            } else if (neTypeId.equals("7")) //cell
            {
                querySql = "{? = call pkg_map.f_list_map_cell4g(?) }";
            } else if (neTypeId.equals("11")) //bsc rnc
            {
                querySql = "{? = call pkg_map.f_list_map_bsc_rnc(?) }";
            } else if (neTypeId.equals("4")) //building
            {
                querySql = "{? = call pkg_map.f_list_map_building(?) }";
            } else if (neTypeId.equals("9")) //QH
            {
                querySql = "{? = call pkg_map.f_list_map_tram_qh(?) }";
            } else if (neTypeId.equals("10")) //DA
            {
                querySql = "{? = call pkg_map.f_list_map_tram_du_an(?) }";
            } else if (neTypeId.equals("20")) //CS
            {
                querySql = "{? = call pkg_map.f_list_map_cs_core(?) }";
            } else if (neTypeId.equals("25")) //PS
            {
                querySql = "{? = call pkg_map.f_list_map_ps_core(?) }";
            }

            List<Object> vars = new Vector<Object>();
            vars.add(nodeId);
            //vars.add(neTypeId);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    String neTypeId = rs.getString("ne_type_id");

                    if (neTypeId.equals("2") || neTypeId.equals("3") || neTypeId.equals("8")) //BTS
                    {
                        BTSInfoBO item = new BTSInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setMaNodeCha(rs.getString("ma_node_cha"));
                        item.setNodeChaId(rs.getLong("node_cha_id"));

                        item.setDonViId(rs.getLong("DONVI_ID"));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getLong("building_id"));
                        item.setTenNeType(rs.getString("ten_loai_ne"));

                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getString("Latitude"));
                        item.setLon(rs.getString("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getLong("thiet_bi_id"));
                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));

                        if (neTypeId.equals("2")) {
                            item.setName(rs.getString("TEN_BTS"));
                        } else if (neTypeId.equals("3")) {
                            item.setName(rs.getString("TEN_NODEB"));
                        } else if (neTypeId.equals("8")) {
                            item.setName(rs.getString("TEN_ENODEB"));
                        }
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setCauHinh(rs.getString("cau_hinh"));
                        item.setCauHinhPortId(rs.getLong("cau_hinh_port_id"));

                        item.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                        item.setTenBSCRNC(rs.getString("TEN_BSC_RNC"));
                        item.setTenBSCRNCQL(rs.getString("TEN_BSC_RNC_QL"));

                        item.setFilterUser(rs.getString("FILTER_USER"));
                        item.setFrequencyBand(rs.getString("FREQUENCY_BAND"));
                        item.setBangTanId(rs.getLong("BANG_TAN_ID"));

                        item.setCodeTramDA(rs.getString("ma_tram_da"));
                        item.setTramDAId(rs.getLong("TRAM_DU_AN_ID"));
                        item.setStatus(rs.getInt("STATUS"));
                        item.setUserInsert(rs.getLong("USER_INSERT"));
                        return item;

                    } else if (neTypeId.equals("5") || neTypeId.equals("6") || neTypeId.equals("7")) {
                        NodeBO nodebo = new NodeBO();
                        nodebo.setId(rs.getLong("id"));

                        nodebo.setCode(rs.getString("ma_node"));
                        nodebo.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        nodebo.setSDTQLy(rs.getString("SDT_NG_QLY"));
                        nodebo.setDonViName(rs.getString("ten_don_vi"));
                        nodebo.setNodeChaId(rs.getLong("node_cha_id"));
                        nodebo.setTenThietBi(rs.getString("ten_thiet_bi"));
                        nodebo.setThietBiId(rs.getLong("thiet_bi_id"));

                        nodebo.setCodeBuilding(rs.getString("ma_building"));
                        nodebo.setNeTypeId(rs.getLong("ne_type_id"));
                        nodebo.setTenNeType(rs.getString("ten_loai_ne"));
                        nodebo.setLoaiTramId(rs.getLong("loai_tram_id"));

                        nodebo.setTenLoaiTram(rs.getString("ten_loai_tram"));
                        nodebo.setTenTrangThaiHD(rs.getString("ten_trangthai_hd"));

                        nodebo.setTenTrangThaiQL(rs.getString("ten_trangthai_ql"));

                        nodebo.setNote(rs.getString("note"));
                        nodebo.setMaNodeCha(rs.getString("ma_node_cha"));
                        nodebo.setCodeTramDA(rs.getString("ma_tram_da"));
                        nodebo.setUserInsert(rs.getLong("user_insert"));
                        nodebo.setStatus(rs.getInt("status"));
                        if (neTypeId.equals("7")) //cell4g
                        {
                            OmcCell4gInfoBO omc = new OmcCell4gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getLong("lac"));
                            omc.setCi(rs.getLong("ci"));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            omc.setPci(rs.getString("pci"));
                            omc.setTac(rs.getString("tac"));
                            omc.setLcrid(rs.getString("lcrid"));

                            omc.setCellType(rs.getLong("CELL_TYPE"));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));

                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                            Long setAzimuth = rs.getLong("AZIMUTH");
                            omc.setAzimuth(rs.wasNull() ? null : setAzimuth);

//                            omc.setAzimuth(rs.getLong("AZIMUTH"));
//                            omc.setMechanicalTilt(rs.getLong("MECHANICAL_TILT"));
                            Long setMechanicalTilt = rs.getLong("MECHANICAL_TILT");
                            omc.setMechanicalTilt(rs.wasNull() ? null : setMechanicalTilt);

//                            omc.setElectricalTilt(rs.getLong("ELECTRICAL_TILT"));
                            Long setElectricalTilt = rs.getLong("ELECTRICAL_TILT");
                            omc.setElectricalTilt(rs.wasNull() ? null : setElectricalTilt);

                            omc.setTotalTilt(rs.getLong("TOTAL_TILT"));
                            omc.setAntennaType(rs.getLong("ANTENNA_TYPE"));
                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
                            omc.setAntennaHigh(rs.getLong("ANTENNA_HIGH"));
                            omc.setNoOfCarrier(rs.getLong("no_of_carrier"));
                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));
                            omc.setLyDo(rs.getString("ly_do"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setTenNodeCha(rs.getString("ten_enodeb"));
                            omc.setLat(rs.getString("latitude"));
                            omc.setLon(rs.getString("longitude"));
                            return omc;
                        } else if (neTypeId.equals("5")) //cell
                        {
                            OmcCell2gInfoBO omc = new OmcCell2gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getLong("lac"));
                            omc.setCi(rs.getLong("ci"));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            Long bcch = rs.getLong("bcch");
                            omc.setBcch(rs.wasNull() ? null : bcch);

                            omc.setBsic(rs.getString("bsic"));
                            omc.setTch(rs.getString("tch"));
                            omc.setTrxConfig(rs.getString("trx_config"));
                            //info
                            omc.setCellType(rs.getLong("CELL_TYPE"));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));
                            omc.setVnpCode(rs.getString("VNP_CODE"));
                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
//                            omc.setAzimuth(rs.getLong("AZIMUTH"));
                            Long setAzimuth = rs.getLong("AZIMUTH");
                            omc.setAzimuth(rs.wasNull() ? null : setAzimuth);

//                            omc.setMechanicalTilt(rs.getLong("MECHANICAL_TILT"));
                            Long setMechanicalTilt = rs.getLong("MECHANICAL_TILT");
                            omc.setMechanicalTilt(rs.wasNull() ? null : setMechanicalTilt);

//                            omc.setElectricalTilt(rs.getLong("ELECTRICAL_TILT"));
                            Long setElectricalTilt = rs.getLong("ELECTRICAL_TILT");
                            omc.setElectricalTilt(rs.wasNull() ? null : setElectricalTilt);

//                            omc.setTotalTilt(rs.getLong("TOTAL_TILT"));
                            Long setTotalTilt = rs.getLong("TOTAL_TILT");
                            omc.setTotalTilt(rs.wasNull() ? null : setTotalTilt);

//                            omc.setAntennaType(rs.getLong("ANTENNA_TYPE"));
                            Long setAntennaType = rs.getLong("ANTENNA_TYPE");
                            omc.setAntennaType(rs.wasNull() ? null : setAntennaType);

                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
//                            omc.setAntennaHigh(rs.getLong("ANTENNA_HIGH"));
                            Long setAntennaHigh = rs.getLong("ANTENNA_HIGH");
                            omc.setAntennaHigh(rs.wasNull() ? null : setAntennaHigh);

                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));

                            omc.setTenNodeCha(rs.getString("ten_bts"));
                            omc.setLat(rs.getString("latitude"));
                            omc.setLon(rs.getString("longitude"));
                            return omc;

                        } else if (neTypeId.equals("6")) //cell
                        {
                            OmcCell3gInfoBO omc = new OmcCell3gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getLong("lac"));
                            omc.setCi(rs.getLong("ci"));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            omc.setDlPsc(rs.getString("dl_psc"));
                            omc.setCpichPower(rs.getString("cpich_power"));
                            omc.setTotalPower(rs.getString("total_power"));
                            omc.setMaxPower(rs.getString("max_power"));
                            omc.setCellType(rs.getLong("CELL_TYPE"));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));

                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                            omc.setAzimuth(rs.getLong("AZIMUTH"));
                            omc.setMechanicalTilt(rs.getLong("MECHANICAL_TILT"));
                            omc.setElectricalTilt(rs.getLong("ELECTRICAL_TILT"));
                            omc.setTotalTilt(rs.getLong("TOTAL_TILT"));
                            omc.setAntennaType(rs.getLong("ANTENNA_TYPE"));
                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
                            omc.setAntennaHigh(rs.getLong("ANTENNA_HIGH"));
                            omc.setNoOfCarrier(rs.getLong("NO_OF_CARRIER"));
                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));
                            omc.setLyDo(rs.getString("LY_DO"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setTenNodeCha(rs.getString("ten_nodeb"));
                            omc.setLat(rs.getString("latitude"));
                            omc.setLon(rs.getString("longitude"));
                            return omc;

                        }
                    } else if (neTypeId.equals("11")) //BSC RNC
                    {
                        BSCRNCInfoBO item = new BSCRNCInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setNeTypeId(rs.getLong("ne_type_id"));
                        item.setCode(rs.getString("ma_node"));
//                        item.setMaNodeCha(rs.getString("ma_node_cha"));
//                        item.setDonViId(rs.getLong("DONVI_ID"));
//                        item.setCodeBuilding(rs.getString("ma_building"));
//                        item.setBuildingId(rs.getLong("building_id"));
//                        item.setTenNeType(rs.getString("ten_loai_ne"));
//                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
//                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
//                        item.setLat(rs.getFloat("Latitude"));
//                        item.setLon(rs.getFloat("Longitude"));
//                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
//                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

//                        item.setThietBiId(rs.getLong("thiet_bi_id"));
//                        item.setLoaiTramId(rs.getLong("loai_tram_id"));
//                        item.setTrangThaiHDId(rs.getLong("trang_thai_hd_id"));
//                        item.setTrangThaiQLId(rs.getLong("trang_thai_ql_id"));
                        item.setName(rs.getString("TEN_BSC_RNC"));
//                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
//                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
//                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
//                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
//                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
//                        item.setTypeBSCRNC(rs.getString("TYPE_BSC_RNC"));

                        return item;
                    } else if (neTypeId.equals("4")) //Building
                    {
                        BuildingBO item = new BuildingBO();
                        item.setId(rs.getLong("building_id"));
                        item.setCode(rs.getString("ma_building"));
                        item.setTinhName(rs.getString("ten_tinh_tp"));
                        item.setQuanName(rs.getString("ten_quan_huyen"));
                        item.setPhuongName(rs.getString("ten_phuong_xa"));
                        item.setLat(rs.getString("latitude"));
                        item.setLon(rs.getString("longitude"));
                        item.setAddress(rs.getString("dia_chi"));
                        return item;
                    } else if (neTypeId.equals("9")) { // Quy Hoach
                        ProjectStationBO record = new ProjectStationBO();
                        record.setTramQhId(rs.getInt("TRAM_QH_ID"));
                        record.setMaQh(rs.getString("MA_QUY_HOACH"));
                        record.setTenQh(rs.getString("TEN_QUY_HOACH"));
                        record.setNamKhoiTao(rs.getDate("NAM_KHOI_TAO"));
                        record.setLongitude(rs.getFloat("LONGITUDE"));
                        record.setLatitude(rs.getFloat("LATITUDE"));
                        record.setLoaiCongNghe(rs.getString("ten_loai_cong_nghe"));
                        record.setBangTan(rs.getString("ten_bang_tan"));
                        record.setPtCsht(rs.getString("ten_loai_pt_csht"));
                        record.setTRANG_THAI_TRAM(rs.getString("TRANG_THAI_TRAM"));
                        record.setTinhTpId(rs.getInt("TINHTP_ID"));
                        return record;
                    } else if (neTypeId.equals("10")) {
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
//                        tramDABO.setHienTrangTram(rs.getString("hien_trang_tram"));
                        tramDABO.setLongitude(rs.getString("longitude"));
                        tramDABO.setLatitude(rs.getString("latitude"));
                        return tramDABO;
                    } else if (neTypeId.equals("20")) { // CS Core
                        HlrInfoBO item = new HlrInfoBO();
                        item.setNodeId(rs.getLong("node_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setAddress(rs.getString("dia_chi"));
                        return item;
                    } else if (neTypeId.equals("25")) { // CS Core
                        SgsnInfoBO item = new SgsnInfoBO();
                        item.setId(rs.getLong("node_id"));
                        item.setCode(rs.getString("ma_node"));
                        item.setAddress(rs.getString("dia_chi"));
                        return item;
                    }
                    return null;
                }
            }, vars);

            return (List<NodeBO>) list;
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

    public List<NodeBO> findSiteByBuilding(String buildingId, String neTypeId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.getIssetNodeBuiding(?,?)}";

            List<Object> vars = new Vector<Object>();
            vars.add(buildingId);
            vars.add(neTypeId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NodeBO temp = new NodeBO();
                    temp.setCode(rs.getString("MA_NODE"));
                    temp.setId(rs.getLong("NODE_ID"));
                    return temp;
                }
            }, vars);

            return (List<NodeBO>) list;
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
    public int approveNodeAll(ApproveAllForm approveForm, Long userUpdate) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NODE.fn_approve_all(?,?,?,?)}";

            List<Object> vars = new Vector<Object>();

            vars.add(approveForm.getIds());
            vars.add(approveForm.getStatus());
            vars.add(approveForm.getComment());
            vars.add(userUpdate);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
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
    public int approveAllBuilding(ApproveAllForm approveForm, Long userUpdate) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call pkg_building.fn_approve_all(?,?,?,?)}";

            List<Object> vars = new Vector<Object>();

            vars.add(approveForm.getIds());
            vars.add(approveForm.getStatus());
            vars.add(approveForm.getComment());
            vars.add(userUpdate);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdateFunction(querySql, vars);
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
    public int fn_check_name_system(Integer prn_node_id, String prn_name_system, Integer prn_ne_type_id) throws ServiceException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = this.getConnection();
            String sql = "begin ?:=pkg_node.fn_check_name_system(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, prn_node_id == null ? "" : prn_node_id.toString());
            cs.setString(3, prn_name_system);
            cs.setString(4, prn_ne_type_id == null ? "" : prn_ne_type_id.toString());
            cs.executeQuery();
            return (int) cs.getObject(1);
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return 0;
    }
    
    @Override
    public List<FilterMapBO> findFilterMap(String objectId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_MAP.fc_filter_map(?) }";
//       

            List<Object> vars = new Vector<Object>();
            vars.add(objectId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    FilterMapBO item = new FilterMapBO();
                    item.setId(rs.getLong("id"));
                    item.setType(rs.getString("TYPE"));
                    item.setColumnName(rs.getString("COLUMN_NAME"));
//                    item.setColumnId(rs.getString("COLUMN_NAME"));
                    item.setColumnId(rs.getInt("DATA_TYPE")+rs.getString("ALIAS") + "." + item.getColumnName());
                    item.setDataType(rs.getInt("DATA_TYPE"));
                    item.setDescription(rs.getString("DESCRIPTION"));
                    return item;
                }
            }, vars);

            return (List<FilterMapBO>) list;
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

    public int getTotalDetailNode(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList, String strFilter) throws ServiceException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "";
            if (neTypeId.equals("2")) //BTS
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_total_detail_bts(?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("3")) //nodeb
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_total_detail_nodeb(?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("8")) //enodeb
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_total_detail_enodeb(?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("5")) //cell2g
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_total_detail_cell2g(?,?,?,?,?,?,?,?,?) }";

            } else if (neTypeId.equals("6")) //cell3g
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_total_detail_cell3g(?,?,?,?,?,?,?,?,?) }";

            } else if (neTypeId.equals("7")) //cell4g
            {

                querySql = "{? = call PKG_NODE_SEARCH.fc_total_detail_cell4g(?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("11")) //bsc rnc
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_total_detail_bsc_rnc(?,?,?,?,?,?,?,?,?) }";
            }

            List<Object> vars = new Vector<Object>();
            vars.add(name);
            vars.add(khuvucId);
            vars.add(tinhId);
            vars.add(quanId);
            vars.add(phuongId);
            vars.add(neTypeId);
            vars.add(venderId);
            vars.add(statusList);
            vars.add(strFilter);

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

    public List<?> findAllDetailNode(String nodeId, String startRow, String endRow, String code,
            String khuvucId, String tinhTpId, String quanHuyenId, String phuongXaId,
            String neTypeId, String thietBiId, String status, String strFilter) throws DAOException {
        Connection conn = null;
        String querySql = "";
        try {
            conn = this.getConnection();
            if (neTypeId.equals("2")) //BTS
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_find_all_detail_bts(?,?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("3")) //nodeb
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_find_all_detail_nodeb(?,?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("8")) //enodeb
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_find_all_detail_enodeb(?,?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("5")) //cell
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_find_all_detail_cell2g(?,?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("6")) //cell
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_find_all_detail_cell3g(?,?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("7")) //cell
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_find_all_detail_cell4g(?,?,?,?,?,?,?,?,?,?,?,?) }";
            } else if (neTypeId.equals("11")) //bsc rnc
            {
                querySql = "{? = call PKG_NODE_SEARCH.fc_find_all_detail_bsc_rnc(?,?,?,?,?,?,?,?,?,?,?,?) }";
            }
//       

            List<Object> vars = new Vector<Object>();
            vars.add(nodeId);
            vars.add(startRow);
            vars.add(endRow);
            vars.add(code);
            vars.add(khuvucId);
            vars.add(tinhTpId);
            vars.add(quanHuyenId);
            vars.add(phuongXaId);
            vars.add(neTypeId);
            vars.add(thietBiId);
            vars.add(status);
            vars.add(strFilter);

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    String neTypeId = rs.getString("ne_type_id");

                    if (neTypeId.equals("2") || neTypeId.equals("3") || neTypeId.equals("8")) //BTS
                    {
                        BTSInfoBO item = new BTSInfoBO();
                        item.setId(rs.getObject("node_id", Long.class));
                        item.setNeTypeId(rs.getObject("ne_type_id", Long.class));
                        item.setCode(rs.getString("ma_node"));
                        item.setMaNodeCha(rs.getString("ma_node_cha"));
                        item.setNodeChaId(rs.getObject("node_cha_id", Long.class));

                        item.setDonViId(rs.getObject("DONVI_ID", Long.class));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getObject("building_id", Long.class));
                        item.setTenNeType(rs.getString("ten_loai_ne"));

                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getString("Latitude"));
                        item.setLon(rs.getString("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getObject("thiet_bi_id", Long.class));
                        item.setLoaiTramId(rs.getObject("loai_tram_id", Long.class));
                        item.setTrangThaiHDId(rs.getObject("trang_thai_hd_id", Long.class));
                        item.setTrangThaiQLId(rs.getObject("trang_thai_ql_id", Long.class));

                        if (neTypeId.equals("2")) {
                            item.setName(rs.getString("TEN_BTS"));
                        } else if (neTypeId.equals("3")) {
                            item.setName(rs.getString("TEN_NODEB"));
                        } else if (neTypeId.equals("8")) {
                            item.setName(rs.getString("TEN_ENODEB"));
                        }
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setCauHinh(rs.getString("cau_hinh"));
                        item.setCauHinhPortId(rs.getObject("cau_hinh_port_id", Long.class));
                        if (!neTypeId.equals("8")) {
                            item.setCosite2G3GType(rs.getInt("COSITE_2G_3G_TYPE"));
                            item.setMaCosite(rs.getString("MA_COSITE_2G_3G"));
                        }

                        item.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                        item.setTenBSCRNC(rs.getString("TEN_BSC_RNC"));
                        item.setTenBSCRNCQL(rs.getString("TEN_BSC_RNC_QL"));
                        if (neTypeId.equals("3") || neTypeId.equals("8")) //BTS
                        {
                            item.setdCHSPDA42M(rs.getString("DC_HSDPA_42M"));

                            if (neTypeId.equals("8")) {
                                item.setmSCMSS(rs.getString("MSC_MSS"));
                                item.setsGSN(rs.getString("SGSN"));
                                item.setEnodebId(rs.getString("enodeb_id"));

                            }
                        }
                        item.setFilterUser(rs.getString("FILTER_USER"));
                        item.setFrequencyBand(rs.getString("FREQUENCY_BAND"));
                        item.setBangTanId(rs.getObject("BANG_TAN_ID", Long.class));

                        item.setCodeTramDA(rs.getString("ma_tram_da"));
                        item.setTramDAId(rs.getObject("TRAM_DU_AN_ID", Long.class));
                        item.setStatus(rs.getInt("STATUS"));
                        item.setUserInsert(rs.getObject("USER_INSERT", Long.class));

                        item.setTenQuan(rs.getString("ten_quan_huyen"));
                        item.setTenPhuong(rs.getString("ten_phuong_xa"));
                        item.setTrangThaiMayNo(rs.getString("trang_thai_dat_may_no"));
                        item.setTenLoaiTram(rs.getString("ten_loai_tram"));
                        item.setNote(rs.getString("note"));
                        return item;

                    } else if (neTypeId.equals("5") || neTypeId.equals("6") || neTypeId.equals("7")) {
                        NodeBO nodebo = new NodeBO();
                        nodebo.setId(rs.getObject("id", Long.class));

                        nodebo.setCode(rs.getString("ma_node"));
                        nodebo.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        nodebo.setSDTQLy(rs.getString("SDT_NG_QLY"));
                        nodebo.setDonViName(rs.getString("ten_don_vi"));
                        nodebo.setNodeChaId(rs.getObject("node_cha_id", Long.class));
                        nodebo.setTenThietBi(rs.getString("ten_thiet_bi"));
                        nodebo.setThietBiId(rs.getObject("thiet_bi_id", Long.class));

                        nodebo.setCodeBuilding(rs.getString("ma_building"));
                        nodebo.setNeTypeId(rs.getObject("ne_type_id", Long.class));
                        nodebo.setTenNeType(rs.getString("ten_loai_ne"));
                        nodebo.setLoaiTramId(rs.getObject("loai_tram_id", Long.class));

                        nodebo.setTenLoaiTram(rs.getString("ten_loai_tram"));
                        nodebo.setTenTrangThaiHD(rs.getString("ten_trangthai_hd"));

                        nodebo.setTenTrangThaiQL(rs.getString("ten_trangthai_ql"));

                        nodebo.setNote(rs.getString("note"));
                        nodebo.setMaNodeCha(rs.getString("ma_node_cha"));
                        nodebo.setCodeTramDA(rs.getString("ma_tram_da"));
                        nodebo.setUserInsert(rs.getObject("user_insert", Long.class));
                        nodebo.setBuildingId(rs.getObject("building_id", Long.class));
                        nodebo.setStatus(rs.getInt("status"));

                        if (neTypeId.equals("7")) //cell4g
                        {
                            OmcCell4gInfoBO omc = new OmcCell4gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getObject("lac", Long.class));
                            omc.setCi(rs.getObject("ci", Long.class));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            omc.setPci(rs.getString("pci"));
                            omc.setTac(rs.getString("tac"));
                            omc.setLcrid(rs.getString("lcrid"));

                            omc.setCellType(rs.getObject("CELL_TYPE", Long.class));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));

                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));

                            omc.setAzimuth(rs.getObject("AZIMUTH", Long.class));
                            omc.setMechanicalTilt(rs.getObject("MECHANICAL_TILT", Long.class));

                            omc.setElectricalTilt(rs.getObject("ELECTRICAL_TILT", Long.class));

                            omc.setTotalTilt(rs.getObject("TOTAL_TILT", Long.class));
                            omc.setAntennaType(rs.getObject("ANTENNA_TYPE", Long.class));
                            omc.setAntennaName(rs.getString("ten_loai_anten"));
                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
                            omc.setAntennaHigh(rs.getObject("ANTENNA_HIGH", Long.class));
                            omc.setNoOfCarrier(rs.getObject("no_of_carrier", Long.class));
                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));
                            omc.setLyDo(rs.getString("ly_do"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setEnodebId(rs.getString("enodeb_id"));
                            omc.setLat(rs.getString("Latitude"));
                            omc.setLon(rs.getString("Longitude"));
                            omc.setBangTanId(rs.getObject("bang_tan_id", Long.class));
                            omc.setBandwidth(rs.getString("bandwidth"));
                            omc.setUarfcn(rs.getString("uarfcn"));
                            return omc;
                        } else if (neTypeId.equals("5")) //cell
                        {
                            OmcCell2gInfoBO omc = new OmcCell2gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getObject("lac", Long.class));
                            omc.setCi(rs.getObject("ci", Long.class));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            omc.setBcch(rs.getObject("bcch", Long.class));

                            omc.setBsic(rs.getString("bsic"));
                            omc.setTch(rs.getString("tch"));
                            omc.setTrxConfig(rs.getString("trx_config"));
                            //info
                            omc.setCellType(rs.getObject("CELL_TYPE", Long.class));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));
                            omc.setVnpCode(rs.getString("VNP_CODE"));
                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                            omc.setAzimuth(rs.getObject("AZIMUTH", Long.class));

                            omc.setMechanicalTilt(rs.getObject("MECHANICAL_TILT", Long.class));

                            omc.setElectricalTilt(rs.getObject("ELECTRICAL_TILT", Long.class));
                            omc.setTotalTilt(rs.getObject("TOTAL_TILT", Long.class));
                            omc.setAntennaType(rs.getObject("ANTENNA_TYPE", Long.class));

                            omc.setAntennaName(rs.getString("ten_loai_anten"));

                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
                            omc.setAntennaHigh(rs.getObject("ANTENNA_HIGH", Long.class));

                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));
                            omc.setLat(rs.getString("Latitude"));
                            omc.setLon(rs.getString("Longitude"));
                            omc.setBangTanId(rs.getObject("bang_tan_id", Long.class));
                            omc.setBscRncCode(rs.getString("bsc_rnc_code"));
                            return omc;

                        } else if (neTypeId.equals("6")) //cell
                        {
                            OmcCell3gInfoBO omc = new OmcCell3gInfoBO(nodebo);
                            omc.setTenTrenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                            omc.setLac(rs.getObject("lac", Long.class));
                            omc.setCi(rs.getObject("ci", Long.class));
                            omc.setTenBangTan(rs.getString("FREQUENCY_BAND"));
                            omc.setDlPsc(rs.getString("dl_psc"));
                            omc.setCpichPower(rs.getString("cpich_power"));
                            omc.setTotalPower(rs.getString("total_power"));
                            omc.setMaxPower(rs.getString("max_power"));
                            omc.setCellType(rs.getObject("CELL_TYPE", Long.class));
                            omc.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                            omc.setHoanCanhRaDoi(rs.getString("HOAN_CANH_RA_DOI"));
                            omc.setTenCell(rs.getString("TEN_CELL"));

                            omc.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                            omc.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                            omc.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                            omc.setAzimuth(rs.getObject("AZIMUTH", Long.class));
                            omc.setMechanicalTilt(rs.getObject("MECHANICAL_TILT", Long.class));
                            omc.setElectricalTilt(rs.getObject("ELECTRICAL_TILT", Long.class));
                            omc.setTotalTilt(rs.getObject("TOTAL_TILT", Long.class));
                            omc.setAntennaType(rs.getObject("ANTENNA_TYPE", Long.class));
                            omc.setAntennaName(rs.getString("ten_loai_anten"));
                            omc.setAntennaModel(rs.getString("ANTENNA_MODEL"));
                            omc.setAntennaPattern(rs.getString("ANTENNA_PATTERN"));
                            omc.setAntennaHigh(rs.getObject("ANTENNA_HIGH", Long.class));
                            omc.setNoOfCarrier(rs.getObject("NO_OF_CARRIER", Long.class));
                            omc.setBosterTma(rs.getString("BOSTER_TMA"));
                            omc.setSpecialCoverage(rs.getString("SPECIAL_COVERAGE"));
                            omc.setListCellGroupId(rs.getString("list_group_id"));
                            omc.setLyDo(rs.getString("LY_DO"));
                            omc.setAntennaGain(rs.getString("ANTENNA_GAIN"));
                            omc.setLat(rs.getString("Latitude"));
                            omc.setLon(rs.getString("Longitude"));
                            omc.setBangTanId(rs.getObject("bang_tan_id", Long.class));
                            omc.setBscRncCode(rs.getString("bsc_rnc_code"));
                            return omc;

                        }
                    } else if (neTypeId.equals("11")) //BSC RNC
                    {
                        BSCRNCInfoBO item = new BSCRNCInfoBO();
                        item.setId(rs.getObject("node_id", Long.class));
                        item.setNeTypeId(rs.getObject("ne_type_id", Long.class));
                        item.setCode(rs.getString("ma_node"));
                        item.setMaNodeCha(rs.getString("ma_node_cha"));
                        item.setDonViId(rs.getObject("DONVI_ID", Long.class));
                        item.setCodeBuilding(rs.getString("ma_building"));
                        item.setBuildingId(rs.getObject("building_id", Long.class));
                        item.setTenNeType(rs.getString("ten_loai_ne"));
                        item.setTenThietBi(rs.getString("ten_thiet_bi"));
                        item.setDonViName(rs.getString("ten_don_vi"));
                        item.setAddress(rs.getString("DIA_CHI"));
                        item.setLat(rs.getFloat("Latitude"));
                        item.setLon(rs.getFloat("Longitude"));
                        item.setTenNgQLy(rs.getString("TEN_NG_QLY"));
                        item.setSDTQLy(rs.getString("SDT_NG_QLY"));

                        item.setThietBiId(rs.getObject("thiet_bi_id", Long.class));
                        item.setLoaiTramId(rs.getObject("loai_tram_id", Long.class));
                        item.setTrangThaiHDId(rs.getObject("trang_thai_hd_id", Long.class));
                        item.setTrangThaiQLId(rs.getObject("trang_thai_ql_id", Long.class));

                        item.setName(rs.getString("TEN_BSC_RNC"));
                        item.setHoanCanhRaDoi(rs.getString("hoan_canh_ra_doi"));
                        item.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
                        item.setNgayKiemDuyet(rs.getDate("NGAY_KIEM_DUYET"));
                        item.setNgayCapPhep(rs.getDate("NGAY_CAP_PHEP"));
                        item.setNgayHoatDong(rs.getDate("NGAY_HOAT_DONG"));
                        item.setTypeBSCRNC(rs.getString("TYPE_BSC_RNC"));

                        return item;
                    }
                    return null;
                }
            }, vars);
//            
            return (List<NodeBO>) list;
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
    
    
    

}
