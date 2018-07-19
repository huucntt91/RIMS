package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.Message;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.DAOFactory;
//import com.vnpt.media.rims.dao.ICps;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.RegTramDuAnExcel;
import com.vnpt.media.rims.formbean.RegTramQuyHoachExcel;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VNP
 */
public class StationPlansFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    private Logger logger = LogManager.getLogger(StationPlansFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public StationPlansFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public StationPlansFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

//      trunglk_start
    public List<TramDuAnBO> findAllTramDA(String startRow,
            String endRow,
            String khuVucId,
            String tinhTpId,
            String idDuAn,
            String maTramDuAn,
            String tenTramDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach,
            Long status) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(Constants.DB_CB);
            i.setTransaction(trans);
            return i.findTramDAAll(startRow, endRow, khuVucId, tinhTpId, idDuAn, maTramDuAn, tenTramDuAn, msHopDong, maTramBTS, maTramNodeB, maTramQuyHoach, status);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void addtramDA(TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            i.addTramDA(tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int updateTramDA(String action, TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateTramDA(action, tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int deleteTramDA(String tramDaId, Long userId) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.deleteTramDA(tramDaId, userId);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int updateCCTBTramDA(TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateCCTBTramDA(tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int updateThongTinChungTramDA(TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateThongTinChungTramDA(tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int updateCCHTTramDA(TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateCCHTTramDA(tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int updateDeployNetXTramDA(TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateDeployNetXTramDA(tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int updateDeployQLHTTramDA(TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.updateDeployQLHTTramDA(tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int approve(TramDuAnBO tramDABO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.approve(tramDABO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            DatabaseUtils.rollback(trans);
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
            return result;
        }
    }

    public int getTotalAllDuAn(String name, String tinhId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalAllDuAn(name, tinhId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<TramDuAnBO> findAllDuAn(String startRow, String endRow, String name, String tinhId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllDuAn(startRow, endRow, name, tinhId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalTramKH(String name, String tinhId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalAll(name, tinhId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<TramDuAnBO> findAllTramKH(String startRow, String endRow, String name, String tinhId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAllTramKH(startRow, endRow, name, tinhId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//      trunglk_end

    public static String addtramDAExcel(RegTramDuAnExcel tramDABO, String userId) {
        tramDABO = (RegTramDuAnExcel) StringUtils.trimObject(tramDABO);
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel.fn_check_tram_ke_hoach(?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cstmt.setString(2, tramDABO.getMaTramDuAn().trim());
            cstmt.setString(3, tramDABO.getName().trim());
            cstmt.setString(4, tramDABO.getMaSoHopDong().trim());
            cstmt.setString(5, tramDABO.getMaCSHT().trim());
            cstmt.setString(6, tramDABO.getMaQuyHoach().trim());
            cstmt.setString(7, tramDABO.getHienTrangTram().trim());
            cstmt.setString(8, userId);

            cstmt.executeQuery();
            return cstmt.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "-1";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static String updatetramDAExcel(TramDuAnTinhExcel tramDABO, String userId, String tinhTpIds) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(userId, "U", "10");
            String DATE_FORMAT = "dd/MM/yyyy";
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel.fn_edit_tram_ke_hoach_tinh(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);

            cstmt.setString(2, tramDABO.getMaTramDuAn().trim());
            cstmt.setString(3, userId);
            cstmt.setString(4, classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "");

            // csht
            cstmt.setString(5, tramDABO.getLonKS() != null ? tramDABO.getLonKS().replace(",", ".").trim() : "");
            
            cstmt.setString(6, tramDABO.getLatKS() != null ? tramDABO.getLatKS().replace(",", ".").trim() : "");
            
            cstmt.setString(7, tramDABO.getNhaTram());
            cstmt.setString(8, tramDABO.getCotAnten());
            cstmt.setString(9, tramDABO.getCauCapNgoai());
            cstmt.setString(10, tramDABO.getTuNguon());
            cstmt.setString(11, tramDABO.getDungLuongTuNguon());
            cstmt.setString(12, tramDABO.getSoModuleTuNguon());
            cstmt.setString(13, tramDABO.getChungLoaiAccu());
            cstmt.setString(14, tramDABO.getDungLuongAccu());
            cstmt.setString(15, tramDABO.getSoLuongToAccu());
            cstmt.setString(16, tramDABO.getTruyenDan());
            cstmt.setString(17, tramDABO.getDieuHoa());
            cstmt.setString(18, tramDABO.getDienAc());
            cstmt.setString(19, tramDABO.getDienAcNoiTram());
            cstmt.setString(20, tramDABO.getDuDkLapEnodeb());
            cstmt.setString(21, tramDABO.getNgayDuDkLapThietBi());        
            cstmt.setString(22, tramDABO.getCapMoiTuNguonDc());
            cstmt.setString(23, tramDABO.getCapMoiAccu());
            cstmt.setString(24, tramDABO.getSwapAnten());
            cstmt.setString(25, tramDABO.getNgayHoanThanhKs());
            cstmt.setString(26, tramDABO.getNgayGuiSoLieu());
            cstmt.setString(27, tramDABO.getDauMoiNhanThietBi());
            cstmt.setString(28, tramDABO.getDauMoiQLCSHT());
            cstmt.setString(29, tramDABO.getDonViLapDat());
            cstmt.setString(30, tramDABO.getNgayKeHoachLapDat());
            cstmt.setString(31, tramDABO.getNgayBatDauLapDat());
            cstmt.setString(32, tramDABO.getNgayHTLapDatTb());
            cstmt.setString(33, tramDABO.getNgayHTLapDatAnten());            
            cstmt.setString(34, tramDABO.getGhiChu());
            cstmt.setString(35, tinhTpIds);
            
            cstmt.executeQuery();
            return cstmt.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "-1";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }
    
    public static String updatetramDAQLDAExcel(TramDuAnQLDAExcel tramDABO, String userId) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(userId, "U", "10");
            String DATE_FORMAT = "dd/MM/yyyy";
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel.fn_edit_tram_ke_hoach_qlda(?,?,?,?,?,?,?,?,?,?,"
                                                                     + "?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);

            cstmt.setString(2, tramDABO.getMaTramDuAn().trim());
            cstmt.setString(3, userId);
            cstmt.setString(4, classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "");
            cstmt.setString(5, tramDABO.getKeHoachXuatThietBi());
            cstmt.setString(6, tramDABO.getNgayXuatThietBiThucTe());
            cstmt.setString(7, tramDABO.getNgayXuatAntenThucTe());
            cstmt.setString(8, tramDABO.getNgayTiepNhanTb());
            cstmt.setString(9, tramDABO.getKeHoachTbDenSite());
            cstmt.setString(10, tramDABO.getKeHoachHoaMang());
            cstmt.setString(11, tramDABO.getNgayHoaMangThucTe());
            cstmt.setString(12, tramDABO.getKeHoachPhatSongCt());
            cstmt.setString(13, tramDABO.getNgayPhatSongCt());
            cstmt.setString(14, tramDABO.getKeHoachNghiemThu());
            cstmt.setString(15, tramDABO.getNgayNghiemThu());
            cstmt.setString(16, tramDABO.getDauMoiVnptNet());
            cstmt.setString(17, tramDABO.getDonViVanChuyen());
            cstmt.setString(18, tramDABO.getGhiChu());
            
            
            cstmt.executeQuery();
            return cstmt.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "-1";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }
    
    public static String updatetramDAPTMExcel(TramDuAnPTMExcel tramDABO, String userId) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(userId, "U", "10");
            String DATE_FORMAT = "dd/MM/yyyy";
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel.fn_edit_tram_ke_hoach_ptm(?,?,?,?,?,?,?,?,?,?,"
                                                                     + "?,?,?,?,?,?,?,?,"
                                                                     + "?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);

            cstmt.setString(2, tramDABO.getMaTramDuAn().trim());
            cstmt.setString(3, userId);
            cstmt.setString(4, classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "");
            cstmt.setString(5, tramDABO.getTenDuAn());
            cstmt.setString(6, tramDABO.getMaSoHopDong());
            cstmt.setString(7, tramDABO.getTenTinhTp());
            cstmt.setString(8, tramDABO.getTenQuanHuyen());
            cstmt.setString(9, tramDABO.getAddress());
            cstmt.setString(10, tramDABO.getTenTramDuAn());
            cstmt.setString(11, tramDABO.getMaTramBTS());
            cstmt.setString(12, tramDABO.getMaTramNodeB());
            cstmt.setString(13, tramDABO.getMaTramQuyHoach());
            cstmt.setString(14, tramDABO.getLongitude());
            cstmt.setString(15, tramDABO.getLatitude());
            cstmt.setString(16, tramDABO.getHienTrangTram());
            cstmt.setString(17, tramDABO.getTrangthaiCSHT());
            cstmt.setString(18, tramDABO.getVnptNetPheDuyet());
            cstmt.setString(19, tramDABO.getCauHinhThietBi());
            cstmt.setString(20, tramDABO.getNguonThietBi());
            cstmt.setString(21, tramDABO.getLoaiCongNghe());
            cstmt.setString(22, tramDABO.getChungLoaiThietBi());
            cstmt.setString(23, tramDABO.getChungLoaiAnten());
            cstmt.setString(24, tramDABO.getThoiDiemCungCapThietBi());
            cstmt.setString(25, tramDABO.getThoiDiemSwapThietBi());
            cstmt.setString(26, tramDABO.getGhiChu());
            
            
            cstmt.executeQuery();
            return cstmt.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "-1";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }
    
    public static String updatetramDANetXExcel(TramDuAnNetXExcel tramDABO, String userId) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(userId, "U", "10");
            String DATE_FORMAT = "dd/MM/yyyy";
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_excel.fn_edit_tram_ke_hoach_netx(?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);

            cstmt.setString(2, tramDABO.getMaTramDuAn().trim());
            cstmt.setString(3, userId);
            cstmt.setString(4, classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "");
            cstmt.setString(5, tramDABO.getNgayPheDuyetKhaoSat());
            cstmt.setString(6, tramDABO.getNgayTiepNhanTruyenDan());
            cstmt.setString(7, tramDABO.getGhiChu());
            
            
            cstmt.executeQuery();
            return cstmt.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "-1";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

//QH EXCEL
    public String addtramQHExcel(RegTramQuyHoachExcel tramQHDKBO, String userId) {
        tramQHDKBO = (RegTramQuyHoachExcel) StringUtils.trimObject(tramQHDKBO);
        CallableStatement cstmt = null;
        Connection conn = null;
        try {

            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_EXCEL_QUY_HOACH.fn_check_tram_quy_hoach(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cstmt.setString(2, tramQHDKBO.getTenTramQH());
            cstmt.setString(3, tramQHDKBO.getMaTinh());
            cstmt.setString(4, tramQHDKBO.getNamKhoiTao());
            cstmt.setString(5, tramQHDKBO.getLatitude());
            cstmt.setString(6, tramQHDKBO.getLongiude());
            cstmt.setString(7, tramQHDKBO.getLoaiCongNghe());
            cstmt.setString(8, tramQHDKBO.getBangTan2G());
            cstmt.setString(9, tramQHDKBO.getBangTan3G());
            cstmt.setString(10, tramQHDKBO.getBangTan4G());
            cstmt.setString(11, tramQHDKBO.getCtPTCSHT());
            cstmt.setString(12, tramQHDKBO.getTrangThaiCSHT());
            cstmt.setString(13, tramQHDKBO.getDvpheduyetTTC());
            cstmt.setString(14, tramQHDKBO.getSohieuVB());
            cstmt.setString(15, tramQHDKBO.getNgaypheduyetTTC());
            cstmt.setInt(16, Integer.valueOf(userId));

            cstmt.executeQuery();
            return cstmt.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "-1";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static String updatetramQHExcel(TramQuyHoachUpdateExcel tramQHBO, String userId) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<String> classAtrrEdit = adminFacade.findClassAttrByUserId(userId, "U", "11");
            String DATE_FORMAT = "dd/MM/yyyy";
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_EXCEL_QUY_HOACH.fn_edit_tram_quy_hoach(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?); end;";

            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cstmt.setString(2, classAtrrEdit != null && classAtrrEdit.size() > 0 ? String.join(",", classAtrrEdit) : "");
            
            
            
            cstmt.setString(3, tramQHBO.getMaQHTinh().trim());
            cstmt.setString(4, tramQHBO.getMaTramQH().trim());
            cstmt.setString(5, tramQHBO.getTenTramQH().trim());

            cstmt.setString(6, tramQHBO.getMaBuilding().trim());
            cstmt.setString(7, tramQHBO.getNamKhoiTao().trim());
            cstmt.setString(8, tramQHBO.getLoaiCongNghe().trim());
            cstmt.setString(9, tramQHBO.getBangtan().trim());
            cstmt.setString(10, tramQHBO.getCtPTCSHT().trim());
            cstmt.setString(11, tramQHBO.getTtCSHT().trim());
            cstmt.setString(12, tramQHBO.getDvpheduyetTTC().trim());
            cstmt.setString(13, tramQHBO.getSohieuVB());
            cstmt.setString(14, tramQHBO.getNgaypheduyetTTC());
            cstmt.setString(15, tramQHBO.getNgaydieuchinhTTC());
            cstmt.setString(16, tramQHBO.getDonviDieuChinh());
            cstmt.setString(17, tramQHBO.getNgayPhatSongTTC());
            cstmt.setString(18, tramQHBO.getTtCkDonViChiuTrachNhiem());
            //cam ket thiet bi
            
            cstmt.setString(19, tramQHBO.getTtCkNguonThietBi());
            cstmt.setString(20, tramQHBO.getTtCkThoiDiemDapUngDuKien());
            cstmt.setString(21, tramQHBO.getTtCkCongNgheDapUng());
            cstmt.setString(22, tramQHBO.getTtCkChungLoaiThietBi());
            cstmt.setString(23, tramQHBO.getTtCkThoiGianDapUngThucTe());
            cstmt.setString(24, tramQHBO.getTtCkKhoKhanVuongMac());
            // csht
            cstmt.setString(25, tramQHBO.getCshtDonViChiuTrachNhiem());
            cstmt.setString(26, tramQHBO.getCshtTenTram());
            cstmt.setString(27, tramQHBO.getCshtCachThucXD());
            cstmt.setString(28, tramQHBO.getCshtLoaiDat());
            cstmt.setString(29, tramQHBO.getCshtNgayCapThueDat());
            cstmt.setString(30, tramQHBO.getCshtNgayXinPhepXDNhaTram());
            cstmt.setString(31, tramQHBO.getCshtNgayHoanThanhThuTucXay());
            cstmt.setString(32, tramQHBO.getCshtNgayKhoiCongXD());
            cstmt.setString(33, tramQHBO.getCshtNgayHoanThanhXay());
            cstmt.setString(34, tramQHBO.getCshtNgayXinPhepDoCaoCot());
            cstmt.setString(35, tramQHBO.getCshtNgayCapPhepDoCaoCot());
            cstmt.setString(36, tramQHBO.getCshtNgayHoanThanhThuTucXDCot());
            cstmt.setString(37, tramQHBO.getCshtNgayKhoiCongDungCot());

            cstmt.setString(38, tramQHBO.getNgayHoanThanhCot());
            cstmt.setString(39, tramQHBO.getLoaiCot());
            cstmt.setString(40, tramQHBO.getDoCaoCot());
            cstmt.setString(41, tramQHBO.getDoCaoChanCot());
            cstmt.setString(42, tramQHBO.getPhuongThucTruyenDan());
            cstmt.setString(43, tramQHBO.getE1());
            cstmt.setString(44, tramQHBO.getFe());
            cstmt.setString(45, tramQHBO.getGe());
            cstmt.setString(46, tramQHBO.getStm());

            cstmt.setString(47, tramQHBO.getNgayKhoiCongTruyenDan());
            cstmt.setString(48, tramQHBO.getNgayHoanThanhTruyenDan());

            cstmt.setString(49, tramQHBO.getNgayDienApAC());
            cstmt.setString(50, tramQHBO.getHeThongDienTrongNhaTram());
            cstmt.setString(51, tramQHBO.getHeThongDieuHoa());
            cstmt.setString(52, tramQHBO.getHeThongTiepDat());
            cstmt.setString(53, tramQHBO.getMayNo());
            cstmt.setString(54, tramQHBO.getNgayHoanThanhPHuTro());
            cstmt.setString(55, tramQHBO.getDoiTuongThongBao());
            cstmt.setString(56, tramQHBO.getSoHieuThongBao());
            cstmt.setString(57, tramQHBO.getNgayThongBaoHoanThanhCSHT());
            cstmt.setString(58, tramQHBO.getKhoKhanVuongMac());
            cstmt.setString(59, tramQHBO.getNguonDonViChiuTrachNhiem());
            cstmt.setString(60, tramQHBO.getTuNguon());
            cstmt.setString(61, tramQHBO.getLoaiTuNguon());
            cstmt.setString(62, tramQHBO.getDungLuongTuNguon());
            cstmt.setString(63, tramQHBO.getSoLuongRacktifier());
            cstmt.setString(64, tramQHBO.getDungLuongAcquy());
            cstmt.setString(65, tramQHBO.getSoLuongToAcquy());
            cstmt.setString(66, tramQHBO.getDienApAcquy());
            cstmt.setString(67, tramQHBO.getNgayDapUngDcDuKien());
            cstmt.setString(68, tramQHBO.getNgayDapUngDcThucTe());
            cstmt.setString(69, tramQHBO.getAntenDonViChiuTrachNhiem());
            cstmt.setString(70, tramQHBO.getAntenNgayDapUngDuKien());
            cstmt.setString(71, tramQHBO.getAntenNgayDapUngThucTe());

            cstmt.setString(72, tramQHBO.getLoaiAnTen1());
            cstmt.setString(73, tramQHBO.getTenAnTen1());
            cstmt.setString(74, tramQHBO.getHangSX1());
            cstmt.setString(75, tramQHBO.getSoLuongAnten1());
            cstmt.setString(76, tramQHBO.getBangTan1());
            cstmt.setString(77, tramQHBO.getCauHinhPort1());
            cstmt.setString(78, tramQHBO.getCauHinhGain1());
            cstmt.setString(79, tramQHBO.getCauHinhBeamWidth1());
            cstmt.setString(80, tramQHBO.getTrongLuong1());
            cstmt.setString(81, tramQHBO.getKichCoCao1());
            cstmt.setString(82, tramQHBO.getKichCoRong1());
            cstmt.setString(83, tramQHBO.getKichCoSau1());
            cstmt.setString(84, tramQHBO.getDoCaoLapDatAnten1());
            cstmt.setString(85, tramQHBO.getLoaiAnTen2());
            cstmt.setString(86, tramQHBO.getTenAnTen2());
            cstmt.setString(87, tramQHBO.getHangSX2());
            cstmt.setString(88, tramQHBO.getSoLuongAnten2());
            cstmt.setString(89, tramQHBO.getBangTan2());
            cstmt.setString(90, tramQHBO.getCauHinhPort2());
            cstmt.setString(91, tramQHBO.getCauHinhGain2());
            cstmt.setString(92, tramQHBO.getCauHinhBeamWidth2());
            cstmt.setString(93, tramQHBO.getTrongLuong2());
            cstmt.setString(94, tramQHBO.getKichCoCao2());
            cstmt.setString(95, tramQHBO.getKichCoRong2());
            cstmt.setString(96, tramQHBO.getKichCoSau2());
            cstmt.setString(97, tramQHBO.getDoCaoLapDatAnten2());
            cstmt.setString(98, tramQHBO.getLoaiAnTen3());
            cstmt.setString(99, tramQHBO.getTenAnTen3());
            cstmt.setString(100, tramQHBO.getHangSX3());
            cstmt.setString(101, tramQHBO.getSoLuongAnten3());
            cstmt.setString(102, tramQHBO.getBangTan3());
            cstmt.setString(103, tramQHBO.getCauHinhPort3());
            cstmt.setString(104, tramQHBO.getCauHinhGain3());
            cstmt.setString(105, tramQHBO.getCauHinhBeamWidth3());
            cstmt.setString(106, tramQHBO.getTrongLuong3());
            cstmt.setString(107, tramQHBO.getKichCoCao3());
            cstmt.setString(108, tramQHBO.getKichCoRong3());
            cstmt.setString(109, tramQHBO.getKichCoSau3());
            cstmt.setString(110, tramQHBO.getDoCaoLapDatAnten3());
            cstmt.setInt(111, Integer.valueOf(userId));

            cstmt.executeQuery();
            return cstmt.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "-1";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
//        return "";
    }
//END QH EXCEL        
//    trunglk_start

    public int getTotalRow(String khuVucId,
                            String tinhTpId,
                            String idDuAn,
                            String maTramDuAn,
                            String tenTramDuAn,
                            String msHopDong,
                            String maTramBTS,
                            String maTramNodeB,
                            String maTramQuyHoach,
                            Long status) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPlanStation i = factory.getStationPlansDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalAll(khuVucId,tinhTpId, idDuAn, maTramDuAn, tenTramDuAn, msHopDong, maTramBTS, maTramNodeB, maTramQuyHoach, status);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
//    trunglk_end
    
    public static List<TramDuAnBO> eportExelsTramDuAn(String khuVucId,
                                                String tinhTpId,
                                                String maTramDuAn, 
                                                String tenTramDuAn) throws DAOException {
        Connection conn = null;
        List<?> list = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String querySql = "{? = call pkg_tram_ke_hoach.fc_export_tram_ke_hoach(?,?,?,?)}";

            List<Object> vars = new Vector<Object>();

//            if (StringUtils.hasText(fullname)) {
//                vars.add("%" + fullname.trim().toLowerCase() + "%");
//            }

            vars.add(khuVucId == null ? "" : khuVucId);
            vars.add(tinhTpId == null ? "" : tinhTpId);
            vars.add(maTramDuAn == null ? "" : maTramDuAn);
            vars.add(tenTramDuAn == null ? "" : tenTramDuAn);


            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            list = sqlTemplate.queryFunction(querySql, new RowMapper() {
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
                    tramDABO.setHienTrangTramName(rs.getString("hien_trang_tram_name"));
                    Long trangThaiCsht = rs.getLong("trang_thai_csht");
                    tramDABO.setTrangThaiCsht(rs.wasNull() ? null : trangThaiCsht);
                    tramDABO.setLongitude(rs.wasNull() ? null : rs.getString("longitude"));
                    tramDABO.setLatitude(rs.wasNull() ? null : rs.getString("latitude"));
//cam ket thiet bi                   
                    Long vnptNetPheDuyet = rs.getLong("vnpt_net_phe_duyet");
                    tramDABO.setVnptNetPheDuyet(rs.wasNull() ? null : vnptNetPheDuyet);
                    tramDABO.setCauHinhThietBi(rs.getString("cau_hinh_thiet_bi"));
                    tramDABO.setCauHinhThietBiName(rs.getString("cau_hinh_thiet_bi_name"));
                    Long nguonThietBi = rs.getLong("nguon_thiet_bi");
                    tramDABO.setNguonThietBi(rs.wasNull() ? null : nguonThietBi);
                    tramDABO.setNguongThietBiName(rs.getString("TEN_NGUON_THIET_BI"));
                    Long loaiCongNghe = rs.getLong("loai_cong_nghe");
                    tramDABO.setLoaiCongNghe(rs.wasNull() ? null : loaiCongNghe);
                    tramDABO.setLoaiCongNgheName(rs.getString("TEN_LOAI_CONG_NGHE"));
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
                    tramDABO.setChungLoaiAccuName(rs.getString("TEN_LOAI_AC_QUY"));
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

                    return tramDABO;
                }

            }, vars);

            
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
        }
        return (List<TramDuAnBO>) list;
    }
}
