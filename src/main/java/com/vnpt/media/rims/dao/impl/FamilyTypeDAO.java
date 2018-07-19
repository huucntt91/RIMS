package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.FamilyTypeBO;
import com.vnpt.media.rims.dao.IFamilyType;
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

public class FamilyTypeDAO extends GenericDAO implements IFamilyType {

    private static Logger logger = LogManager.getLogger(FamilyTypeDAO.class);

    @Override
    public List<FamilyTypeBO> findAllFamilyType(String id, String name) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_FAMILY_TYPE.fc_find_all_family_type() }";
//       


            List<Object> vars = new Vector<Object>();
     
            
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    FamilyTypeBO dvBO = new FamilyTypeBO();
                    dvBO.setId(rs.getLong("family_type_id"));
                    dvBO.setName(rs.getString("family_name"));
                    dvBO.setDescription(rs.getString("family_desc"));
                    
                    return dvBO;
                }

            }, vars);

            return (List<FamilyTypeBO>) list;
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
