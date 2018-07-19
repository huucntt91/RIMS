package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.*;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.StringUtils;
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
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.ApproveForm;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import com.vnpt.media.rims.jdbc.DbSql;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class TnodeDAO extends GenericDAO implements ITnode {

    private static Logger logger = LogManager.getLogger(TnodeDAO.class);

    @Override
    public List<TNodeBO> findAll(String tnodeid, String code,String name) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "{? = call PKG_TNODE.fn_find(?,?,?) }";
//       


            List<Object> vars = new Vector<Object>();
//            vars.add(startRow);
            vars.add(tnodeid);
            vars.add(code);
            vars.add(name);
//            vars.add(name);
//            vars.add(khuvucId);
//            vars.add(tinhId);
//            vars.add(quanId);
//            vars.add(phuongId);
//            vars.add(neTypeId);
//            vars.add(venderId);
//            vars.add(statusList);
            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TNodeBO item = new TNodeBO();
                    item.setId(rs.getString("TNODE_ID"));
                    item.setCode(rs.getString("TNODE_CODE"));
                    item.setName(rs.getString("TNODE_NAME"));
                    item.setTypeId(rs.getString("TNODE_TYPE_ID"));
                    item.setTinhTpId(rs.getString("TINHTP_ID"));
                    item.setMaTinh(rs.getString("MA_TINH_TP"));
                    return item;
                }
            }, vars);

            return (List<TNodeBO>) list;
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
