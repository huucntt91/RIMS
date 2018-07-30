package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.bean.DonViBO;
import com.vnpt.media.rims.bean.FamilyTypeBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.LoaiNeBO;
import com.vnpt.media.rims.bean.LoaiTramBO;
import com.vnpt.media.rims.bean.NetworkTypeBO;
import com.vnpt.media.rims.bean.PhancapBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.ThietBiBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.bean.LoaiAnTenBO;
import com.vnpt.media.rims.dao.DAOFactory;
//import com.vnpt.media.rims.dao.ICps;
import com.vnpt.media.rims.dao.IDonVi;
import com.vnpt.media.rims.dao.IFamilyType;
import com.vnpt.media.rims.dao.INetworkType;
import com.vnpt.media.rims.dao.IPhancap;
import com.vnpt.media.rims.dao.ITinh;
import com.vnpt.media.rims.dao.IThietBi;
import com.vnpt.media.rims.dao.ILoaiTram;
import com.vnpt.media.rims.dao.ILoaiAnTen;
import com.vnpt.media.rims.dao.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author VNP
 */
public class CategoriesFacade {

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");
    private Logger logger = LogManager.getLogger(CategoriesFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";
    private final String DB_RIMS_BB = "db_rims_bb";

    public CategoriesFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public CategoriesFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    //End CP
    //DonVi
    public List<DonViBO> findAllDonVi(String id, String name, String tinhs) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IDonVi iDonVi = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            iDonVi.setTransaction(trans);
            return iDonVi.findAll(id, name, tinhs);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void addDonVi(DonViBO dvBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDonVi iDonVi = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            iDonVi.setTransaction(trans);
            trans.beginTransaction();
            iDonVi.addDonVi(dvBO);
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

    public void updateDonVi(DonViBO dvBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDonVi iDonVi = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            iDonVi.setTransaction(trans);
            trans.beginTransaction();
            iDonVi.updateDonVi(dvBO);
            trans.commit();
            trans.endTransaction();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public void deleteDonVi(String Id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDonVi users = factory.getDonViDAO();
            trans.connectionType(DB_ADMIN);
            users.setTransaction(trans);
            trans.beginTransaction();
            users.delete(Id);
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

    // Tinh danh muc
    public List<TinhBO> findAllTinh(String id) throws ServiceException {
        return TinhDSFacade.findAllTinh(id);
    }

    // Tinh + KhuVuc danh muc
    public List<KhuvucBO> findAllKhuVuc(String id) throws ServiceException {
        return TinhDSFacade.findAllKhuVuc(id);
    }

    // Tinh ok Nok
    public List<DanhMucBO> findDmOkNok() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IDanhMuc iDanhMuc = factory.getDanhMucDAO();
            trans.connectionType(DB_ADMIN);
            iDanhMuc.setTransaction(trans);
            return iDanhMuc.findDmOkNok();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<DanhMucBO> findDmNguonThietBi() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IDanhMuc iDanhMuc = factory.getDanhMucDAO();
            trans.connectionType(DB_ADMIN);
            iDanhMuc.setTransaction(trans);
            return iDanhMuc.findDmNguonThietBi();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<DanhMucBO> findDmLoaiCongNghe() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IDanhMuc iDanhMuc = factory.getDanhMucDAO();
            trans.connectionType(DB_ADMIN);
            iDanhMuc.setTransaction(trans);
            return iDanhMuc.findDmLoaiCongNghe();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<DanhMucBO> findDmHienTrangTram() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IDanhMuc iDanhMuc = factory.getDanhMucDAO();
            trans.connectionType(DB_ADMIN);
            iDanhMuc.setTransaction(trans);
            return iDanhMuc.findDmHienTrangTram();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<DanhMucBO> findDmDungLuongACCU() throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IDanhMuc iDanhMuc = factory.getDanhMucDAO();
            trans.connectionType(DB_ADMIN);
            iDanhMuc.setTransaction(trans);
            return iDanhMuc.findDmDungLuongACCU();
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // modify Tinh (add,edit,del) 
    public int modifyTinh(String action, TinhBO tinhBO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ITinh iTinh = factory.getTinhDAO();
            trans.connectionType(DB_ADMIN);
            iTinh.setTransaction(trans);
            trans.beginTransaction();
            result = iTinh.modifyTinh(action, tinhBO);
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

    public List<HuyenBO> findAllHuyen(String id, String tinhId) throws ServiceException {
        return TinhDSFacade.findAllHuyen(id, tinhId);
    }

    public List<HuyenBO> findAllHuyen(String listTinhId) throws ServiceException {
        return TinhDSFacade.findAllHuyen(listTinhId);
    }

    // modify Tinh (add,edit,del)
    public int modifyQuan(String action, HuyenBO huyenBO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ITinh iTinh = factory.getTinhDAO();
            trans.connectionType(DB_ADMIN);
            iTinh.setTransaction(trans);
            trans.beginTransaction();
            result = iTinh.modifyQuan(action, huyenBO);
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

    // modify Phuong  (add,edit,del)
    public int modifyPhuong(String action, PhuongXaBO huyenBO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ITinh iTinh = factory.getTinhDAO();
            trans.connectionType(DB_ADMIN);
            iTinh.setTransaction(trans);
            trans.beginTransaction();
            result = iTinh.modifyPhuong(action, huyenBO);
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

    public List<PhuongXaBO> findAllPhuongXa(String id, String huyenId) throws ServiceException {
        return TinhDSFacade.findAllPhuongXa(id, huyenId);

    }

    public List<PhuongXaBO> findAllPhuongXa(String listHuyenId) throws ServiceException {
        return TinhDSFacade.findAllPhuongXa("", listHuyenId);
    }

    public List<PhancapBO> findAllPhancap(String id, String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IPhancap iPhancap = factory.getPhancapDAO();
            trans.connectionType(DB_ADMIN);
            iPhancap.setTransaction(trans);
            return iPhancap.findAllPhancap(id, name);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NetworkTypeBO> findAllNetworkType(String id, String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            INetworkType iNetworkType = factory.getNetworkTypeDAO();
            trans.connectionType(DB_ADMIN);
            iNetworkType.setTransaction(trans);
            return iNetworkType.findAllNetworkType(id, name);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<FamilyTypeBO> findAllFamilyType(String id, String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();

            IFamilyType iFamilyType = factory.getFamilyTypeDAO();
            trans.connectionType(DB_ADMIN);
            iFamilyType.setTransaction(trans);
            return iFamilyType.findAllFamilyType(id, name);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Thiet Bi
    // modify Thiet Bi  (add,edit,del)
    public int modifyThietBi(String action, ThietBiBO thietBiBO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IThietBi iThietBi = factory.getThietBiDAO();
            trans.connectionType(DB_ADMIN);
            iThietBi.setTransaction(trans);
            trans.beginTransaction();
            result = iThietBi.modify(action, thietBiBO);
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

    public List<ThietBiBO> findAllThietBi(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IThietBi iThietBi = factory.getThietBiDAO();
            trans.connectionType(DB_ADMIN);
            iThietBi.setTransaction(trans);
            return iThietBi.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ThietBiBO> findThietBiByName(String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IThietBi iThietBi = factory.getThietBiDAO();
            trans.connectionType(DB_ADMIN);
            iThietBi.setTransaction(trans);
            return iThietBi.findThietBiByName(name);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Loai Tram
    // modify Loai Tram  (add,edit,del)
    public int modifyLoaiTram(String action, LoaiTramBO loaiTramBO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ILoaiTram iLoaiTram = factory.getLoaiTramDAO();
            trans.connectionType(DB_ADMIN);
            iLoaiTram.setTransaction(trans);
            trans.beginTransaction();
            result = iLoaiTram.modify(action, loaiTramBO);
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

    public List<LoaiTramBO> findAllLoaiTram(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiTram iLoaiTram = factory.getLoaiTramDAO();
            trans.connectionType(DB_ADMIN);
            iLoaiTram.setTransaction(trans);
            return iLoaiTram.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<LoaiTramBO> findAllByNeTypeId(String id, int neTypeId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiTram iLoaiTram = factory.getLoaiTramDAO();
            trans.connectionType(DB_ADMIN);
            iLoaiTram.setTransaction(trans);
            return iLoaiTram.findAllByNeTypeId(id, neTypeId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<LoaiTramBO> findAllLoaiTramByName(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiTram iLoaiTram = factory.getLoaiTramDAO();
            trans.connectionType(DB_ADMIN);
            iLoaiTram.setTransaction(trans);
            return iLoaiTram.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<LoaiNeBO> findAllLoaiNe(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiTram iLoaiTram = factory.getLoaiTramDAO();
            trans.connectionType(DB_ADMIN);
            iLoaiTram.setTransaction(trans);
            return iLoaiTram.findAllNe(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Loai AnTen
    // modify Loai An Ten  (add,edit,del)
    public int modifyAnTen(String action, LoaiAnTenBO loaiAnTenBO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ILoaiAnTen iLoaiAnTen = factory.getLoaiAnTenDAO();
            trans.connectionType(DB_ADMIN);
            iLoaiAnTen.setTransaction(trans);
            trans.beginTransaction();
            result = iLoaiAnTen.modify(action, loaiAnTenBO);
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

    public List<LoaiAnTenBO> findAllAnTen(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiAnTen iLoaiAnTen = factory.getLoaiAnTenDAO();
            trans.connectionType(DB_ADMIN);
            iLoaiAnTen.setTransaction(trans);
            return iLoaiAnTen.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Loai May No
    // modify Loai May No  (add,edit,del)
    public int modifyMayNo(String action, LoaiMayNoBO item) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ILoaiMayNo i = factory.getLoaiMayNoDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.modify(action, item);
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

    public List<LoaiMayNoBO> findAllMayNo(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiMayNo i = factory.getLoaiMayNoDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Loai Tu Nguon
    // modify Loai Tu Nguon  (add,edit,del)
    public int modifyTuNguon(String action, LoaiTuNguonBO item) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ILoaiTuNguon i = factory.getLoaiTuNguonDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.modify(action, item);
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

    public List<LoaiTuNguonBO> findAllTuNguon(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiTuNguon i = factory.getLoaiTuNguonDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Loai Truyen Dan
    // modify Loai Truyen Dan  (add,edit,del)
    public int modifyTruyenDan(String action, LoaiTruyenDanBO item) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ILoaiTruyenDan i = factory.getLoaiTruyenDanDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.modify(action, item);
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

    public List<LoaiTruyenDanBO> findAllTruyenDan(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiTruyenDan i = factory.getLoaiTruyenDanDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Loai Ac Quy
    // modify Loai Ac Quy  (add,edit,del)
    public int modifyAcQuy(String action, LoaiAcQuyBO item) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            ILoaiAcQuy i = factory.getLoaiAcQuyDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.modify(action, item);
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

    public List<LoaiAcQuyBO> findAllAcQuy(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ILoaiAcQuy i = factory.getLoaiAcQuyDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Buid Ding
    // modify Building (add,edit,del)
    public int modifyBuilding(String action, BuildingBO item) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IBuilding i = factory.getBuildingDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.modify(action, item);
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

    public List<BuildingBO> findAllBuildingBO(String startRow, String endRow, String id, String name, String khuvucId, String tinhId, String quanId, String phuongId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBuilding i = factory.getBuildingDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(startRow, endRow, id, name, khuvucId, tinhId, quanId, phuongId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int getTotalUser(String name, String khuvucId, String tinhId, String quanId, String phuongId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBuilding i = factory.getBuildingDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalAll(name, khuvucId, tinhId, quanId, phuongId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public int modifyPhuTro(String action, PhuTroBO item) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IPhuTro i = factory.getPhuTroDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.modify(action, item);
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

    public List<BuildingBO> findBuildingLink(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBuilding i = factory.getBuildingDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findBuildingLink(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<TrangThaiHDBO> findAllTrangThaiHD(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITrangThaiHD i = factory.getTrangThaiHDDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<TrangThaiQLBO> findAllTrangThaiQL(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITrangThaiQL i = factory.getTrangThaiQLDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<PhuTroBO> findPhuTroBO(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBuilding i = factory.getBuildingDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findListPhuTroByBuidingId(id);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NodeBO> findNodeBOByBuilding(String buildingId, String chaId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBuilding i = factory.getBuildingDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findNodeById(buildingId, chaId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // find tinh by khu vuc
    public List<TinhBO> findTinhByKv(String khuVucId) {
        return TinhDSFacade.findTinhByKv(khuVucId);
    }

//    list kpi pm
    public List<KpiPmBO> findKpiListByNeType(String neTypeId) {
        ITransaction trans = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<KpiPmBO> result = null;
        try {
            //get connecttion
            trans = factory.getTransaction();
            trans.connectionType(DB_ADMIN);
            conn = trans.getConnection();
            //
            String sql = "";
            if (neTypeId.equals("2")) {
                sql = "begin ?:=pkg_report_kpi.list_kpi_2g(); end;";
            } else if (neTypeId.equals("3")) {
                sql = "begin ?:=pkg_report_kpi.list_kpi_3g(); end;";
            } else if (neTypeId.equals("4")) {
                sql = "begin ?:=pkg_report_kpi.list_kpi_4g(); end;";
            }

            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                KpiPmBO item = new KpiPmBO();
                item.setKpiId(rs.getLong("kpi_mapping_id"));
                item.setNameKpi(rs.getString("name"));
                item.setCodeKpi(rs.getString("code"));
                result.add(item);
            }
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            logger.error(de.getMessage(), de);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseUtils.close(trans);
        }
        return result;
    }

    //check long lat co thuoc quan huyen do không
    public boolean checkLongLat(String longtidue, String latidue, String quanHuyenId) {
        ITransaction trans = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<TinhBO> result = null;
        try {
            //get connecttion
            trans = factory.getTransaction();
            trans.connectionType(DB_ADMIN);
            conn = trans.getConnection();
            //
            String sql = "begin ?:=pkg_building.check_long_lat(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, longtidue);
            cs.setString(3, latidue);
            cs.setString(4, quanHuyenId);
            cs.executeQuery();
            Integer numResult = (Integer) cs.getObject(1);
            if (numResult == 1) {
                return true;
            }

        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
            logger.error(de.getMessage(), de);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseUtils.close(trans);
        }
        return false;
    }

    public boolean checkLongLatExist(String longtidue, String latidue, String buildingId) {
        ITransaction trans = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<TinhBO> result = null;
        try {
            //get connecttion
            trans = factory.getTransaction();
            trans.connectionType(DB_ADMIN);
            conn = trans.getConnection();
            //
            String sql = "begin ?:=pkg_building.check_long_lat_exist(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, longtidue);
            cs.setString(3, latidue);
            cs.setString(4, buildingId);
            cs.executeQuery();
            Integer numResult = (Integer) cs.getObject(1);
            if (numResult == 1) {
                return true;
            }

        } catch (DAOException de) {
//            logger.error("DAOException : ", de);
            logger.error(de.getMessage(), de);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseUtils.close(trans);
        }
        return false;
    }

    public String checkBeforeDeleteBuilding(String buildingId) throws SQLException {
        ITransaction trans = null;
        CallableStatement cs = null;
        Connection conn = null;
        String numResult = null;
        try {
            //get connecttion
            trans = factory.getTransaction();
            trans.connectionType(DB_ADMIN);
            conn = trans.getConnection();
            //
            String sql = "begin ?:=pkg_building.check_before_delete(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            cs.setString(2, buildingId);
            cs.executeQuery();
            numResult = (String) cs.getObject(1);
        } catch (DAOException | SQLException de) {
            throw de;
        } finally {
            DatabaseUtils.close(trans);
        }
        return numResult;
    }
 
    public static List<PhuongXaBO> reportPhuongXa(String tinhTpId, 
                                                       String quanHuyenId) throws ServiceException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<PhuongXaBO> ar = null;
        try {
            String sql = "begin ?:=pkg_tinh.fc_export_exels_phuongxa(?,?); end;";
            ar = new ArrayList<>();
            conn = EnvManager.getDbConnection(RIMS_DS);
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, tinhTpId);
            cstmt.setString(3, quanHuyenId);

            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                PhuongXaBO record = new PhuongXaBO();
                  record.setTenTinh(rs.getString("ten_tinh_tp"));
                  record.setTenQuanHuyen(rs.getString("ten_quan_huyen"));
                  record.setTenPhuongXa(rs.getString("ten_phuong_xa"));
                  
                ar.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
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
        return ar;
    }
    

}
