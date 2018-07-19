package com.vnpt.media.rims.facade;

import java.util.HashMap;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.bean.DonViBO;
import com.vnpt.media.rims.bean.FamilyTypeBO;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.bean.GroupMenuBO;
import com.vnpt.media.rims.bean.HierarchicalFunction;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.LoaiNeBO;
import com.vnpt.media.rims.bean.LoaiTramBO;
import com.vnpt.media.rims.bean.MenuBO;
import com.vnpt.media.rims.bean.NetworkTypeBO;
import com.vnpt.media.rims.bean.PhancapBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.ThietBiBO;
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.bean.LoaiAnTenBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.dao.DAOFactory;
//import com.vnpt.media.rims.dao.ICps;
import com.vnpt.media.rims.dao.IDonVi;
import com.vnpt.media.rims.dao.IFamilyType;
import com.vnpt.media.rims.dao.IGroups;
import com.vnpt.media.rims.dao.IMenuAction;
import com.vnpt.media.rims.dao.INetworkType;
import com.vnpt.media.rims.dao.IPhancap;
import com.vnpt.media.rims.dao.ITinh;
import com.vnpt.media.rims.dao.IThietBi;
import com.vnpt.media.rims.dao.ILoaiTram;
import com.vnpt.media.rims.dao.ILoaiAnTen;
import com.vnpt.media.rims.dao.*;

/**
 *
 * @author VNP
 */
public class CSHTFacade {

    private Logger logger = LogManager.getLogger(CSHTFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public CSHTFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public CSHTFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
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

    public List<BuildingBO> findAllBuildingBO(String startRow, String endRow, String id, String name, String khuvucId,String tinhId, String quanId, String phuongId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBuilding i = factory.getBuildingDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(startRow, endRow, id, name,khuvucId, tinhId, quanId, phuongId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    // Phu Tro
    public int getTotalPhuTro(String id,String khuvucId, String tinhId,String quanId, String phuongId, String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPhuTro i = factory.getPhuTroDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.getTotalAll(id, khuvucId,tinhId,quanId,phuongId, code);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<PhuTroBO> findAllPhuTroBO(String startRow, String endRow, String id, String khuvucId,String tinhId,String quanId, String phuongId, String code) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPhuTro i = factory.getPhuTroDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findAll(startRow, endRow, id, khuvucId,tinhId,quanId,phuongId, code);
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

    public PhuTroBO findSupportBuilding(String id) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IPhuTro i = factory.getPhuTroDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
      
            List<PhuTroBO> list = i.findSupportBuilding(id);
            if(list != null && list.size()> 0 )
                return list.get(0);
            return null;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public List<NodeBO> findNodeBOByBuilding(String buildingId) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IBuilding i = factory.getBuildingDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            return i.findCellByBuildingId(buildingId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
}
