package com.vnpt.media.rims.dao.impl;

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
import com.vnpt.media.rims.bean.TinhBO;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.PhancapBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.dao.IPhancap;

public class PhancapDAO extends GenericDAO implements IPhancap {

    private static Logger logger = LogManager.getLogger(PhancapDAO.class);

    @Override
    public List<PhancapBO> findAllPhancap(String id, String name) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_PHANCAP.fc_find_all_phancap() }";
//       


            List<Object> vars = new Vector<Object>();
     
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PhancapBO dvBO = new PhancapBO();
                    dvBO.setId(rs.getLong("phan_cap_id"));
                    dvBO.setName(rs.getString("ten"));
                    dvBO.setDescription(rs.getString("description"));                    
                    return dvBO;
                }

            }, vars);

            return (List<PhancapBO>) list;
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
