package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
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

public class TVendorDAO extends GenericDAO implements ITVendor {

    private static Logger logger = LogManager.getLogger(TVendorDAO.class);

    @Override
    public List<TVendorBO> findAll(String id, String name, Integer startRow, Integer endRow) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TVendor.fn_find(?,?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
            vars.add(id);
            vars.add(name);
            vars.add(startRow);
            vars.add(endRow);
                        
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TVendorBO item = new TVendorBO();
                    item.setId(rs.getLong("TVENDOR_ID"));
                    item.setName(rs.getString("TVENDOR_NAME"));
                    item.setNote(rs.getString("NOTE"));
                    return item;
                }
            }, vars);

            return (List<TVendorBO>) list;
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
