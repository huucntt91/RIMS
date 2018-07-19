package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ExportExcelL2Switch;
import com.vnpt.media.rims.bean.ExportExcelLKNAccessTheoTb;
import com.vnpt.media.rims.bean.ExportExcelLKNAccessTheoTinh;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IJasper;
import com.vnpt.media.rims.dao.ITEq;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JasperFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    public JasperFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public JasperFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public JasperPrint export(String jasperFileName, Map<String, Object> params) {
        String pathJasper = "/com/jasper/template/";
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITEq i = factory.getTEqDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            Connection con = trans.getConnection();
            InputStream resourceAsStream = getClass().getResourceAsStream(pathJasper + jasperFileName);
            JasperReport jr = JasperCompileManager.compileReport(resourceAsStream);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, con);
            trans.commit();
            trans.endTransaction();
            return jp;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } catch (Exception de) {
//            logger.error(de.getMessage(), de);
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<ExportExcelL2Switch> exportExcelL2Switch(Long tnodeTypeId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IJasper i = factory.getJasperDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<ExportExcelL2Switch> iRet = new ArrayList<>();
            iRet = i.exportExcelL2Switch(tnodeTypeId);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public Map<String, List<ExportExcelLKNAccessTheoTinh>> exportExcelKetNoiAccessTheoTinh(Long tinhTPId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IJasper i = factory.getJasperDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            Map<String, List<ExportExcelLKNAccessTheoTinh>> iRet = new HashMap<String, List<ExportExcelLKNAccessTheoTinh>>();
            List<ExportExcelLKNAccessTheoTinh> temp = i.exportExcelKetNoiAccessTheoTinh(tinhTPId);

            if (temp != null && temp.size() > 0) {
                for (int j = 0; j < temp.size(); j++) {
                    if (iRet.get((temp.get(j).getRingAgg()==null?"":temp.get(j).getRingAgg()) 
                                + (temp.get(j).getRingNpe()==null?"":temp.get(j).getRingNpe())
                                + (temp.get(j).getRingUpe()==null?"":temp.get(j).getRingUpe())) == null) {
                        List<ExportExcelLKNAccessTheoTinh> temp1 = new ArrayList<ExportExcelLKNAccessTheoTinh>();
                        temp1.add(temp.get(j));
                        iRet.put((temp.get(j).getRingAgg()==null?"":temp.get(j).getRingAgg()) 
                                + (temp.get(j).getRingNpe()==null?"":temp.get(j).getRingNpe())
                                + (temp.get(j).getRingUpe()==null?"":temp.get(j).getRingUpe()),
                                temp1);
                    } else {
                        List<ExportExcelLKNAccessTheoTinh> temp1 = iRet.get((temp.get(j).getRingAgg()==null?"":temp.get(j).getRingAgg()) 
                                + (temp.get(j).getRingNpe()==null?"":temp.get(j).getRingNpe())
                                + (temp.get(j).getRingUpe()==null?"":temp.get(j).getRingUpe()));
                        temp1.add(temp.get(j));
                        iRet.put((temp.get(j).getRingAgg()==null?"":temp.get(j).getRingAgg()) 
                                + (temp.get(j).getRingNpe()==null?"":temp.get(j).getRingNpe())
                                + (temp.get(j).getRingUpe()==null?"":temp.get(j).getRingUpe()),
                                temp1);
                    }
                }
            }
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
     public List<ExportExcelLKNAccessTheoTb> exportExcelKetNoiAccessTheoTb() {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IJasper i = factory.getJasperDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            
            List<ExportExcelLKNAccessTheoTb> temp = i.exportExcelKetNoiAccessTheoTb();

           
            trans.commit();
            trans.endTransaction();
            return temp;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
     
     
     public Map<String, List<ExportExcelLKNAccessTheoTinh>> vn2ExportExcelKetNoiAccessTheoTinh(Long tinhTPId) {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IJasper i = factory.getJasperDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            Map<String, List<ExportExcelLKNAccessTheoTinh>> iRet = new HashMap<String, List<ExportExcelLKNAccessTheoTinh>>();
            List<ExportExcelLKNAccessTheoTinh> temp = i.vn2ExportExcelKetNoiAccessTheoTinh(tinhTPId);

            if (temp != null && temp.size() > 0) {
                for (int j = 0; j < temp.size(); j++) {
                    if (iRet.get((temp.get(j).getRingAgg()==null?"":temp.get(j).getRingAgg()) 
                                + (temp.get(j).getRingNpe()==null?"":temp.get(j).getRingNpe())
                                + (temp.get(j).getRingUpe()==null?"":temp.get(j).getRingUpe())) == null) {
                        List<ExportExcelLKNAccessTheoTinh> temp1 = new ArrayList<ExportExcelLKNAccessTheoTinh>();
                        temp1.add(temp.get(j));
                        iRet.put((temp.get(j).getRingAgg()==null?"":temp.get(j).getRingAgg()) 
                                + (temp.get(j).getRingNpe()==null?"":temp.get(j).getRingNpe())
                                + (temp.get(j).getRingUpe()==null?"":temp.get(j).getRingUpe()),
                                temp1);
                    } else {
                        List<ExportExcelLKNAccessTheoTinh> temp1 = iRet.get((temp.get(j).getRingAgg()==null?"":temp.get(j).getRingAgg()) 
                                + (temp.get(j).getRingNpe()==null?"":temp.get(j).getRingNpe())
                                + (temp.get(j).getRingUpe()==null?"":temp.get(j).getRingUpe()));
                        temp1.add(temp.get(j));
                        iRet.put((temp.get(j).getRingAgg()==null?"":temp.get(j).getRingAgg()) 
                                + (temp.get(j).getRingNpe()==null?"":temp.get(j).getRingNpe())
                                + (temp.get(j).getRingUpe()==null?"":temp.get(j).getRingUpe()),
                                temp1);
                    }
                }
            }
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
    
     public List<ExportExcelLKNAccessTheoTb> vn2ExportExcelKetNoiAccessTheoTb() {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IJasper i = factory.getJasperDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            
            List<ExportExcelLKNAccessTheoTb> temp = i.vn2ExportExcelKetNoiAccessTheoTb();

           
            trans.commit();
            trans.endTransaction();
            return temp;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }
}
