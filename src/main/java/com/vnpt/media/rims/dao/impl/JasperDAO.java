package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.ExportExcelL2Switch;
import com.vnpt.media.rims.bean.ExportExcelLKNAccessTheoTb;
import com.vnpt.media.rims.bean.ExportExcelLKNAccessTheoTinh;
import com.vnpt.media.rims.dao.IJasper;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JasperDAO extends GenericDAO implements IJasper {

    private static Logger logger = LogManager.getLogger(JasperDAO.class);

    @Override
    public List<ExportExcelL2Switch> exportExcelL2Switch(Long tnodeTypeId) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_JASPER.fn_l2switch(?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(tnodeTypeId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            HashMap<String, HashMap<String, Long>> map = new HashMap<String, HashMap<String, Long>>();
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ExportExcelL2Switch item = new ExportExcelL2Switch();
                    String tenTinh = "";
                    String tenDongTB = "";
                    Long c = 0l;
                    tenDongTB = rs.getString("TEN_DONG_TBI");
                    tenTinh = rs.getString("TEN_TINH_TP");
                    c = rs.getLong("c");
                    HashMap<String, Long> map1 = new HashMap<String, Long>();
                    if (map.get(tenTinh) == null) {
                        map1.put(tenDongTB, c);
                        map.put(tenTinh, map1);
                    } else {
                        map1 = map.get(tenTinh);
                        map1.put(tenDongTB, c);
                        map.put(tenTinh, map1);
                    }                    
                    return null;
                }
            }, vars);
            return map2Object(map);
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

    public List<ExportExcelL2Switch> map2Object(HashMap<String, HashMap<String, Long>> map) {
        List<ExportExcelL2Switch> ret = new ArrayList<ExportExcelL2Switch>();
        ExportExcelL2Switch temp = new ExportExcelL2Switch();
        for (Map.Entry<String, HashMap<String, Long>> entry : map.entrySet()) {
            String tenTinh = entry.getKey();
            HashMap<String, Long> map1 = entry.getValue();
            temp=new ExportExcelL2Switch();
            temp.setTenTinh(tenTinh);
            temp.setData(map1);
            ret.add(temp);
        }
        return ret;
    }

    @Override
    public List<ExportExcelLKNAccessTheoTinh> exportExcelKetNoiAccessTheoTinh(Long tinhTPId) {
       Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_JASPER.fn_rpt_ketnoi_access_theo_tinh(?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(tinhTPId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            HashMap<String, HashMap<String, Long>> map = new HashMap<String, HashMap<String, Long>>();
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ExportExcelLKNAccessTheoTinh item = new ExportExcelLKNAccessTheoTinh();
                                item.setTnodeCode(rs.getString("tnode_code"));
                                item.setRingAgg(rs.getString("ringagg"));
                                item.setRingNpe(rs.getString("ringnpe"));
                                item.setRingUpe(rs.getString("ringupe"));
                                item.setTotal1(rs.getInt("total_1"));
                                item.setTotal2(rs.getInt("total_2"));
                                item.setTotal3(rs.getInt("total_3"));
                                item.setTotal4(rs.getInt("total_4"));
                                
                    return item;
                }
            }, vars);
            return (List<ExportExcelLKNAccessTheoTinh>)list;
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
    public List<ExportExcelLKNAccessTheoTb> exportExcelKetNoiAccessTheoTb() {
       Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_JASPER.fn_rpt_ketnoi_access_theo_tb() }";

            List<Object> vars = new Vector<Object>();
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            HashMap<String, HashMap<String, Long>> map = new HashMap<String, HashMap<String, Long>>();
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ExportExcelLKNAccessTheoTb item = new ExportExcelLKNAccessTheoTb();
                                item.setMaTinhTP(rs.getString("MA_TINH_TP"));
                                item.setTenTinhTP(rs.getString("TEN_TINH_TP"));
                                
                                item.setTotal1(rs.getInt("total_1"));
                                item.setTotal2(rs.getInt("total_2"));
                                item.setTotal3(rs.getInt("total_3"));
                                item.setTotal4(rs.getInt("total_4"));
                                
                    return item;
                }
            }, vars);
            return (List<ExportExcelLKNAccessTheoTb>)list;
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
    public List<ExportExcelLKNAccessTheoTinh> vn2ExportExcelKetNoiAccessTheoTinh(Long tinhTPId) {
       Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_JASPER.fn_rpt_vn2_kn_access_theo_tinh(?) }";

            List<Object> vars = new Vector<Object>();
            vars.add(tinhTPId);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            HashMap<String, HashMap<String, Long>> map = new HashMap<String, HashMap<String, Long>>();
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ExportExcelLKNAccessTheoTinh item = new ExportExcelLKNAccessTheoTinh();
                                item.setTnodeCode(rs.getString("tnode_code"));
                                item.setRingAgg(rs.getString("ringagg"));
                                item.setRingNpe(rs.getString("ringnpe"));
                                item.setRingUpe(rs.getString("ringupe"));
                                item.setTotal1(rs.getInt("total_1"));
                                item.setTotal2(rs.getInt("total_2"));
                                item.setTotal3(rs.getInt("total_3"));
                                item.setTotal4(rs.getInt("total_4"));
                                
                    return item;
                }
            }, vars);
            return (List<ExportExcelLKNAccessTheoTinh>)list;
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
    public List<ExportExcelLKNAccessTheoTb> vn2ExportExcelKetNoiAccessTheoTb() {
       Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_JASPER.fn_rpt_vn2_kn_access_theo_tb() }";

            List<Object> vars = new Vector<Object>();
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            HashMap<String, HashMap<String, Long>> map = new HashMap<String, HashMap<String, Long>>();
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ExportExcelLKNAccessTheoTb item = new ExportExcelLKNAccessTheoTb();
                                item.setMaTinhTP(rs.getString("MA_TINH_TP"));
                                item.setTenTinhTP(rs.getString("TEN_TINH_TP"));
                                
                                item.setTotal1(rs.getInt("total_1"));
                                item.setTotal2(rs.getInt("total_2"));
                                item.setTotal3(rs.getInt("total_3"));
                                item.setTotal4(rs.getInt("total_4"));
                                
                    return item;
                }
            }, vars);
            return (List<ExportExcelLKNAccessTheoTb>)list;
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
