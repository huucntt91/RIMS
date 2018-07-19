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
public class DuAnTinhFacade {

    private Logger logger = LogManager.getLogger(DuAnTinhFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public DuAnTinhFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public DuAnTinhFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }
 
    public List<DuAnTinhBO> findDATinh(String tinhTpId, String maDA) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDuAnTinh i = factory.getDuAnTinhDAO();
            trans.connectionType(Constants.DB_CB);
            i.setTransaction(trans);
            return i.findDATinh(tinhTpId, maDA);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
    public void modifyDuAnTinh(DuAnTinhBO duAnTinhBO) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IDuAnTinh i = factory.getDuAnTinhDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            i.modifyDuAnTinh(duAnTinhBO);
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
    
    public int deleteDuAnTinh(DuAnTinhBO duAnTinhBO) throws ServiceException {
        ITransaction trans = null;
        int result = -1;
        try {
            trans = factory.getTransaction();
            IDuAnTinh i = factory.getDuAnTinhDAO();
            trans.connectionType(DB_ADMIN);
            i.setTransaction(trans);
            trans.beginTransaction();
            result = i.deleteDuAnTinh(duAnTinhBO);
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
       
}
