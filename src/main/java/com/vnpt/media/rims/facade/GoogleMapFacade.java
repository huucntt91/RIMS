package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.ConfLayerBO;
import com.vnpt.media.rims.bean.DMBangTanBO;
import com.vnpt.media.rims.bean.FilterMapBO;
import com.vnpt.media.rims.bean.FilterReportBO;
import com.vnpt.media.rims.bean.GoogleMapBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.transaction.ITransaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IGoogleMap;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import com.vnpt.media.rims.bean.BuildingBO;

/**
 *
 * @author VNP
 */
public class GoogleMapFacade {

    private Logger logger = LogManager.getLogger(GoogleMapFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public GoogleMapFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public GoogleMapFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<GoogleMapBO> findAll(String nodeCode, String[] classType, Long tinhTpId, Long quanHuyenId, Long phuongXaId) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGoogleMap iGoogleMapDao = factory.getGoogleMapDAO();
            trans.connectionType(Constants.DB_CB);
            iGoogleMapDao.setTransaction(trans);
            return iGoogleMapDao.findAll(nodeCode, classType, tinhTpId, quanHuyenId, phuongXaId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<GoogleMapBO> findLinkNode(String nodeCode, String[] classType, Long tinhTpId, Long quanHuyenId, Long phuongXaId) throws ServiceException {
        Constants.loadConfiguration();
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            IGoogleMap iGoogleMapDao = factory.getGoogleMapDAO();
            trans.connectionType(Constants.DB_CB);
            iGoogleMapDao.setTransaction(trans);
            return iGoogleMapDao.findLinkNode(nodeCode, classType, tinhTpId, quanHuyenId, phuongXaId);
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    private static final String RIMS_DS = ResourceBundle.getBundle("config", Locale.getDefault()).getString("RIMS_DS");

    public static int getTotalNodes(String type, String where) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_FILTER_REPORT.fc_total_cell_2g(?,?) ; end;";
            if (type.equals("2")) {
                sql = "begin ?:=PKG_FILTER_REPORT.fc_total_bts(?,?) ; end;";
            } else if (type.equals("3")) {
                sql = "begin ?:=PKG_FILTER_REPORT.fc_total_nodeb(?,?) ; end;";
            } else if (type.equals("8")) {
                sql = "begin ?:=PKG_FILTER_REPORT.fc_total_enodeb(?,?) ; end;";
            }
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);

            cstmt.setString(2, where);
            cstmt.setString(3, type);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }
    private String getTableName(String type){
        String tableName="";
        if(type.equals("2"))
        {
            tableName="omc_bts_info";
        }
        else if(type.equals("3")){
            tableName="rims.omc_nodeb_info";
        }
        else if(type.equals("8")){
            tableName="omc_enodeb_info";
        }
        else if(type.equals("5")){
            tableName="omc_cell2g_info";
        }
        else if(type.equals("6")){
            //Cell3G
            tableName="OMC_CELL3G_INFO";
        }
        else if(type.equals("7")){
            //Cell4G
            tableName="OMC_CELL4G_INFO";
        }
        return tableName;
    }
     private String getObjectName(String type){
        String objectName="";
        if(type.equals("2"))
        {
            objectName=",omc_bts_info.TEN_TREN_HE_THONG ";
        }
        else if(type.equals("3")){
            objectName=",omc_nodeb_info.TEN_TREN_HE_THONG ";
        }
        else if(type.equals("8")){
            objectName=",omc_enodeb_info.TEN_TREN_HE_THONG ";
        }
        else if(type.equals("5")){
            objectName=",omc_cell2g_info.TEN_TREN_HE_THONG ";
        }
        else if(type.equals("6")){
            //Cell3G
            objectName=",OMC_CELL3G_INFO.TEN_TREN_HE_THONG ";
        }
        else if(type.equals("7")){
            //Cell4G
            objectName=",OMC_CELL4G_INFO.TEN_TREN_HE_THONG ";
        }
        return objectName;
    }
     public List<NodeBO> getNodes(String type, String where) {
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection conn = null;
        ArrayList<NodeBO> arrayList = new ArrayList<>();
        try {
            String extTableInfo=getTableName(type);
            String objectName=getObjectName(type);
            String sqlCommand="SELECT a.node_id, a.ma_node,a.ne_type_id, a.donvi_id,a.thiet_bi_id, a.building_id,building.dia_chi,building.latitude,building.longitude"
                    + objectName + " "
                    + "  FROM node a,building  where a.building_id=building.building_id ";
            if(extTableInfo!=null && type!="" && !extTableInfo.equals(""))
            {
                sqlCommand="SELECT a.node_id, a.ma_node,a.ne_type_id, a.donvi_id,a.thiet_bi_id, a.building_id,building.dia_chi,building.latitude,building.longitude " + objectName
                        + " FROM node a,building "
                        + ", " + extTableInfo + " "
                        + " where a.building_id=building.building_id"
                        + " and a.node_id=" + extTableInfo+ ".node_id ";
                        
            }
            if(where==null) where=" ";
            String querySql = sqlCommand + where  +"  ";
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_test.GoogleMapSearch(?,?) ; end;";
            if (type.equals("2")) {
                sql = "begin ?:=pkg_test.GoogleMapSearch(?,?) ; end;";
            } else if (type.equals("3")) {
                sql = "begin ?:=pkg_test.GoogleMapSearch(?,?) ; end;";
            } else if (type.equals("8")) {
                sql = "begin ?:=pkg_test.GoogleMapSearch(?,?) ; end;";
            }
            //
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2,querySql);
            cstmt.setInt(3, 2);
            cstmt.executeQuery();
            
            //return cstmt.getInt(1);
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                NodeBO item = new NodeBO();
                item.setId(rs.getLong("node_id"));
                item.setCode(rs.getString("ma_node"));
                item.setBuildingId(rs.getLong("building_id"));
                item.setAddress(rs.getString("dia_chi"));
                item.setLongitude(rs.getDouble("longitude"));
                item.setLatitude(rs.getDouble("latitude"));
                if(rs.getLong("ne_type_id")==2)
                {
                    item.setTenNeType("BTS");
                }
                else if(rs.getLong("ne_type_id")==3)
                {
                    item.setTenNeType("NODEB");
                }
               
                else if(rs.getLong("ne_type_id")==5)
                {
                    item.setTenNeType("2G");
                }
                else if(rs.getLong("ne_type_id")==6)
                {
                    item.setTenNeType("CELL3G");
                }
                else if(rs.getLong("ne_type_id")==7)
                {
                    item.setTenNeType("CELL4G");
                }
                 else if(rs.getLong("ne_type_id")==8)
                {
                    item.setTenNeType("ENODEB");
                }
                else if(rs.getLong("ne_type_id")==9)
                {
                    item.setTenNeType("NODE_QH");
                }
                else if(rs.getLong("ne_type_id")==10)
                {
                    item.setTenNeType("NODE_DA");
                }
                else if(rs.getLong("ne_type_id")==11)
                {
                    item.setTenNeType("BSC_RNC_MBSC");
                }
                else if(rs.getLong("ne_type_id")==12)
                {
                    item.setTenNeType("MSC");
                }
                else if(rs.getLong("ne_type_id")==13)
                {
                    item.setTenNeType("MSS");
                }
                else if(rs.getLong("ne_type_id")==14)
                {
                    item.setTenNeType("MGW");
                }
                else if(rs.getLong("ne_type_id")==15)
                {
                    item.setTenNeType("STP");
                }
                else if(rs.getLong("ne_type_id")==16)
                {
                    item.setTenNeType("TSS");
                }
                else if(rs.getLong("ne_type_id")==17)
                {
                    item.setTenNeType("HSS");
                }
                else if(rs.getLong("ne_type_id")==18)
                {
                    item.setTenNeType("HLR");
                }
                else if(rs.getLong("ne_type_id")==19)
                {
                    item.setTenNeType("IMS");
                }
                else if(rs.getLong("ne_type_id")==20)
                {
                    item.setTenNeType("NGN");
                }
                else if(rs.getLong("ne_type_id")==21)
                {
                    item.setTenNeType("ANTEN");
                }
                else if(rs.getLong("ne_type_id")==22)
                {
                    item.setTenNeType("SGSN");
                }
                 else if(rs.getLong("ne_type_id")==23)
                {
                    item.setTenNeType("GGSN");
                }
                  else if(rs.getLong("ne_type_id")==24)
                {
                    item.setTenNeType("MME");
                }
                 else if(rs.getLong("ne_type_id")==25)
                {
                    item.setTenNeType("PGW");
                }
                 else if(rs.getLong("ne_type_id")==26)
                {
                    item.setTenNeType("SMSC");
                }
                else if(rs.getLong("ne_type_id")==27)
                {
                    item.setTenNeType("SMPP");
                }
                else if(rs.getLong("ne_type_id")==28)
                {
                    item.setTenNeType("DSR");
                }
                else if(rs.getLong("ne_type_id")==29)
                {
                    item.setTenNeType("MCA");
                }
                else if(rs.getLong("ne_type_id")==30)
                {
                    item.setTenNeType("CRBT");
                }
                 else if(rs.getLong("ne_type_id")==31)
                {
                    item.setTenNeType("USSD");
                }
                  else if(rs.getLong("ne_type_id")==32)
                {
                    item.setTenNeType("DNS");
                }
                 else if(rs.getLong("ne_type_id")==33)
                {
                    item.setTenNeType("RC");
                }
                else if(rs.getLong("ne_type_id")==33)
                {
                    item.setTenNeType("RC");
                }
                 else if(rs.getLong("ne_type_id")==34)
                {
                    item.setTenNeType("SDP");
                }
                 else if(rs.getLong("ne_type_id")==35)
                {
                    item.setTenNeType("FDA");
                }
                else
                {
                item.setTenNeType("");
                }
                //Ten he thong
                try
                {
                     item.setTenHeThong(rs.getString("TEN_TREN_HE_THONG"));
                }
                catch(Exception exx)
                {
                     exx.printStackTrace();
                }
               
                arrayList.add(item);
            }
            return arrayList;
            
        } catch (Exception ex) {
            ex.printStackTrace();
           // return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
        return null;
    }
    public List<FilterMapBO> findFilterMap(String objectId) throws DAOException {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<FilterMapBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=PKG_MAP.fc_filter_map(?) ; end;";

            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, objectId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                FilterMapBO item = new FilterMapBO();
                item.setId(rs.getLong("id"));
                item.setType(rs.getString("TYPE"));
                item.setColumnName(rs.getString("COLUMN_NAME"));
                item.setColumnId(rs.getString("TABLE_NAME") + "." + item.getColumnName());
                item.setDataType(rs.getInt("DATA_TYPE"));
                item.setDescription(rs.getString("DESCRIPTION"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }
//trunglk_start

    public ArrayList<ConfLayerBO> fc_find_layer(String prs_layer_id, String userId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ConfLayerBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_conf_layer.fc_find_layer(?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_layer_id);
            cstmt.setString(3, userId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ConfLayerBO item = new ConfLayerBO();
                item.setLayerId(rs.getString("LAYER_ID") == null ? "" : rs.getString("LAYER_ID"));
                item.setLayerName(rs.getString("LAYER_NAME") == null ? "" : rs.getString("LAYER_NAME"));
                item.setStyleOpacity(rs.getString("STYLE_OPACITY") == null ? "" : rs.getString("STYLE_OPACITY"));
                item.setStyleColor(rs.getString("STYLE_COLOR") == null ? "" : rs.getString("STYLE_COLOR"));
                item.setStyleBorderColor(rs.getString("STYLE_BORDER_COLOR") == null ? "" : rs.getString("STYLE_BORDER_COLOR"));
                item.setStyleSize(rs.getString("STYLE_SIZE") == null ? "" : rs.getString("STYLE_SIZE"));
                item.setStyleWhere(rs.getString("SQL_WHERE") == null ? "" : rs.getString("SQL_WHERE"));
                item.setUserId(rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
                item.setObjectId(rs.getString("OBJECT_ID") == null ? "" : rs.getString("OBJECT_ID"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public ArrayList<ConfLayerBO> fc_find_event(String prs_layer_id, String userId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ConfLayerBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_conf_layer.fc_find_event(?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, prs_layer_id);
            cstmt.setString(3, userId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ConfLayerBO item = new ConfLayerBO();
                item.setLayerId(rs.getString("LAYER_ID") == null ? "" : rs.getString("LAYER_ID"));
                item.setLayerName(rs.getString("LAYER_NAME") == null ? "" : rs.getString("LAYER_NAME"));
                item.setStyleOpacity(rs.getString("STYLE_OPACITY") == null ? "" : rs.getString("STYLE_OPACITY"));
                item.setStyleColor(rs.getString("STYLE_COLOR") == null ? "" : rs.getString("STYLE_COLOR"));
                item.setStyleBorderColor(rs.getString("STYLE_BORDER_COLOR") == null ? "" : rs.getString("STYLE_BORDER_COLOR"));
                item.setStyleSize(rs.getString("STYLE_SIZE") == null ? "" : rs.getString("STYLE_SIZE"));
                item.setStyleWhere(rs.getString("SQL_WHERE") == null ? "" : rs.getString("SQL_WHERE"));
                item.setUserId(rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
                item.setEventId(rs.getString("EVENT_ID") == null ? "" : rs.getString("EVENT_ID"));
                item.setStartDate(rs.getString("START_DATE") == null ? "" : rs.getString("START_DATE"));
                item.setEndDate(rs.getString("END_DATE") == null ? "" : rs.getString("END_DATE"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static int fn_modify(String prn_action,
            String layerId,
            String layerName,
            String styleOpacity,
            String styleColor,
            String styleBorderColor,
            String styleSize,
            String styleWhere,
            String userId,
            String objectId,
            String eventId,
            String eventName,
            String startDate,
            String endDate) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_conf_layer.fn_modify(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            
            if(endDate == null)
            {
                Calendar c = Calendar.getInstance(); 
                c.setTime(date); 
                c.add(Calendar.DATE, 7);
                endDate = dateFormat.format(c.getTime());       
            }
            if(startDate == null)
            {
                Calendar c = Calendar.getInstance(); 
                c.setTime(date); 
                c.add(Calendar.DATE, -23);
                startDate = dateFormat.format(c.getTime());   
            }
            
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setString(2, prn_action);
            cstmt.setString(3, layerId);
            cstmt.setString(4, layerName);
            cstmt.setString(5, styleOpacity);
            cstmt.setString(6, styleColor);
            cstmt.setString(7, styleBorderColor);
            cstmt.setString(8, styleSize);
            cstmt.setString(9, styleWhere);
            cstmt.setString(10, userId);
            cstmt.setString(11, objectId);
            cstmt.setString(12, eventId);
            cstmt.setString(13, eventName);
            cstmt.setString(14, startDate);
            cstmt.setString(15, endDate);
            cstmt.executeQuery();
            return cstmt.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public ArrayList<ConfLayerBO> fc_list_event() {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ConfLayerBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_conf_layer.fc_list_event(); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ConfLayerBO item = new ConfLayerBO();
                item.setEventId(rs.getString("EVENT_DEFINE_ID") == null ? "" : rs.getString("EVENT_DEFINE_ID"));
                item.setEventName(rs.getString("EVENT_DEFINE_NAME") == null ? "" : rs.getString("EVENT_DEFINE_NAME"));
                item.setTechType(rs.getString("TECH_TYPE") == null ? "" : rs.getString("TECH_TYPE"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public ArrayList<ConfLayerBO> fc_data_layer(String eventId, String startDate, String endDate) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<ConfLayerBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_conf_layer.fc_list_data_event(?,?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
          
            if(startDate == null)
            {
                Calendar c = Calendar.getInstance(); 
                c.setTime(date); 
                c.add(Calendar.DATE, -23);
                startDate = dateFormat.format(c.getTime());   
                
            }
            if(endDate == null)
            {
                Calendar c = Calendar.getInstance(); 
                c.setTime(date); 
                c.add(Calendar.DATE, 7);
                endDate = dateFormat.format(c.getTime());   
                
            }
            
            cstmt.setString(2, eventId);
            cstmt.setString(3, startDate);
            cstmt.setString(4, endDate);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ConfLayerBO item = new ConfLayerBO();
                item.setNodeId(rs.getString("NODE_ID") == null ? "" : rs.getString("NODE_ID"));
                item.setMaNode(rs.getString("MA_NODE") == null ? "" : rs.getString("MA_NODE"));
                item.setLatitude(rs.getString("LATITUDE") == null ? "" : rs.getString("LATITUDE"));
                item.setLongitude(rs.getString("LONGITUDE") == null ? "" : rs.getString("LONGITUDE"));
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }
//    trunglk_end
    
    public static ArrayList<BuildingBO> getNodeChaByNodeId(String nodeId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<BuildingBO> arrayList = new ArrayList<>();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_node.getNodeChaById(?) ; end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, nodeId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                BuildingBO item = new BuildingBO();
                item.setId(rs.getLong("node_id"));
                item.setCode(rs.getString("ma_node") == null ? "" : rs.getString("ma_node"));
                item.setLat(rs.getString("latitude"));
                item.setLon(rs.getString("longitude"));
           
                arrayList.add(item);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return arrayList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }
    
    public static NodeBO getNodeById(String nodeId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        NodeBO item = new NodeBO();
        try {
            conn = EnvManager.getDbConnection(RIMS_DS);
            String sql = "begin ?:=pkg_node.getNodeId(?) ; end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, nodeId);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            if (rs.next()) {
                item.setId(rs.getLong("node_id"));
                item.setCode(rs.getString("ma_node") == null ? "" : rs.getString("ma_node"));
                item.setNeTypeId(rs.getLong("ne_type_id"));
                item.setTenTinhTp(rs.getString("ma_tinh_tp") == null ? "" : rs.getString("ma_tinh_tp"));
                item.setTinhTpId(rs.getString("tinhtp_id"));
            }
            return item;
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (Exception ex) {
                }
            }
        }
    }
    
}
