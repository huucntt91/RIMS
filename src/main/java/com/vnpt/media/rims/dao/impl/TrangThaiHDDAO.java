package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.ThietBiBO;
import com.vnpt.media.rims.bean.TrangThaiHDBO;
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
import com.vnpt.media.rims.dao.ITrangThaiHD;

public class TrangThaiHDDAO extends GenericDAO implements ITrangThaiHD {

    private static Logger logger = LogManager.getLogger(TrangThaiHDDAO.class);

    @Override
    public List<TrangThaiHDBO> findAll(String id) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TRANG_THAI_HD.fc_find_all(?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TrangThaiHDBO temp = new TrangThaiHDBO();
                    temp.setId(rs.getLong("TRANG_THAI_HD_ID"));
                    temp.setName(rs.getString("TEN_TRANGTHAI_HD"));
                    return temp;
                }   
            }, vars);
//            
            return (List<TrangThaiHDBO>) list;
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
