package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.GoogleMapBO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.IGoogleMap;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;

public class GoogleMapDAO extends GenericDAO implements IGoogleMap {

    private static Logger logger = LogManager.getLogger(GoogleMapDAO.class);

    public List<GoogleMapBO> findAll(String nodeCode
                                    , String[] classType
                                    , Long tinhTpId
                                    , Long quanHuyenId
                                    , Long phuongXaId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();

            String querySql = "{? = call PKG_MAP.fc_find_node_cells(?,?,?,?,?)}";
       


            List<Object> vars = new Vector<Object>();

            vars.add(nodeCode == null ? "" : nodeCode);
            vars.add(classType == null ? "" : StringUtils.convertArrayToString(classType));
            vars.add(tinhTpId == null ? "" : tinhTpId);
            vars.add(quanHuyenId == null ? "" : quanHuyenId);
            vars.add(phuongXaId == null ? "" : phuongXaId);


            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GoogleMapBO mapBO = new GoogleMapBO();
                    mapBO.setNodeId(rs.getLong("node_id"));
                    mapBO.setMaMode(rs.getString("ma_node"));
                    mapBO.setLatitude(rs.getDouble("latitude"));
                    mapBO.setLongitude(rs.getDouble("longitude"));
                    mapBO.setType(rs.getLong("type"));
                    mapBO.setAzimuth(rs.getLong("azimuth"));
                    mapBO.setTinhTpId(rs.getLong("tinhtp_id"));
                    mapBO.setQuanHuyenId(rs.getLong("quanhuyen_id"));
                    mapBO.setPhuongXaId(rs.getLong("phuongxa_id"));
                    mapBO.setDonviId(rs.getLong("donvi_id"));
                    return mapBO;
                } 

            }, vars);

            return (List<GoogleMapBO>) list;
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
    
    public List<GoogleMapBO> findLinkNode(String nodeCode
                                    , String[] classType
                                    , Long tinhTpId
                                    , Long quanHuyenId
                                    , Long phuongXaId) throws DAOException {
        Connection conn = null;
        try {
            conn = this.getConnection();

            String querySql = "{? = call PKG_MAP.fc_find_link_node(?,?,?,?,?)}";
       


            List<Object> vars = new Vector<Object>();

            vars.add(nodeCode == null ? "" : nodeCode);
            vars.add(classType == null ? "" : StringUtils.convertArrayToString(classType));
            vars.add(tinhTpId == null ? "" : tinhTpId);
            vars.add(quanHuyenId == null ? "" : quanHuyenId);
            vars.add(phuongXaId == null ? "" : phuongXaId);


            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            List<?> list = sqlTemplate.queryFunction(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    GoogleMapBO mapBO = new GoogleMapBO();
                    mapBO.setLat1(rs.getDouble("lat1"));
                    mapBO.setLon1(rs.getDouble("lon1"));
                    mapBO.setLat2(rs.getDouble("lat2"));
                    mapBO.setLon2(rs.getDouble("lon2"));
                    return mapBO;
                } 

            }, vars);

            return (List<GoogleMapBO>) list;
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
