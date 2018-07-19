package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.FamilyTypeBO;
import com.vnpt.media.rims.bean.NetworkTypeBO;
import com.vnpt.media.rims.dao.INetworkType;
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

public class NetworkTypeDAO extends GenericDAO implements INetworkType {

    private static Logger logger = LogManager.getLogger(NetworkTypeDAO.class);

    @Override
    public List<NetworkTypeBO> findAllNetworkType(String id, String name) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_NETWORK_TYPE.fc_find_all_network_type() }";
//       


            List<Object> vars = new Vector<Object>();
     
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    NetworkTypeBO dvBO = new NetworkTypeBO();
                    dvBO.setId(rs.getLong("network_type_id"));
                    dvBO.setName(rs.getString("network_type_name"));
                    dvBO.setDescription(rs.getString("network_type_desc"));
                     
                    return dvBO;
                }

            }, vars);

            return (List<NetworkTypeBO>) list;
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
