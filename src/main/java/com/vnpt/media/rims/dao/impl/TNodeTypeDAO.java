package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.dao.ITNodeType;
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

public class TNodeTypeDAO extends GenericDAO implements ITNodeType {

    private static Logger logger = LogManager.getLogger(TNodeTypeDAO.class);

    @Override
    public List<TnodeTypeBO> findAll(String id, String name) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TNODETYPE.fn_find(?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(id);
            vars.add(name);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TnodeTypeBO item = new TnodeTypeBO();
                    item.setId(rs.getLong("TNODE_TYPE_ID"));
                    item.setName(rs.getString("TNODE_NAME"));
                    item.setNote(rs.getString("NOTE"));                    
                    return item;
                }
            }, vars);

            return (List<TnodeTypeBO>) list;
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
