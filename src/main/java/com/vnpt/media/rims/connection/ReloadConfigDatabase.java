package com.vnpt.media.rims.connection;

//import com.mbbank.monitorLog.BO.DatabaseNameBO;
//import com.vnptmedia.cb.common.Constants;
//import com.vnptmedia.cb.common.encryption.SimpleCrypto;
//import com.vnptmedia.cb.common.utils.StringUtils;
//import com.mbbank.monitorLog.facade.SqlCommandFacade;

import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReloadConfigDatabase {
    private static Logger logger = LogManager.getLogger(ReloadConfigDatabase.class);

//    public static void loadConfigDatabase(){
//        SqlCommandFacade facade = new SqlCommandFacade();
//        List<DatabaseNameBO> databaseNameBO = facade.findAll(null, null, 0, 0);
//        if (StringUtils.isEmpty(databaseNameBO)) {
//            logger.fatal("Khong ton tai cac database name nao can ket noi !!!");
//        } else {
//            logger.fatal("The system have " + Constants.MAP_JDBC_CONFIG.size() + " databases");
//            for (DatabaseNameBO nameBO : databaseNameBO) {
//                JdbcConfig config = new JdbcConfig();
//                config.setTypeConnec(2);
//                config.setMaxActive(StringUtils.hasText(nameBO.getJdbcMaxActive()) ? Integer.parseInt(nameBO.getJdbcMaxActive()) : 3);
//                config.setMaxIdle(StringUtils.hasText(nameBO.getJdbcMaxActive()) ? Integer.parseInt(nameBO.getJdbcMaxActive()) : 3);
//                config.setMinIdle(StringUtils.hasText(nameBO.getJdbcMinActive()) ? Integer.parseInt(nameBO.getJdbcMinActive()) : 1);
//                config.setIdleMaxAge(60);
//                config.setDriverName(StringUtils.hasText(nameBO.getJdbcDriverName()) ? nameBO.getJdbcDriverName() : Constants.DEFAULT_DRIVE_NAME);
//                if (config.getDriverName().trim().equalsIgnoreCase(Constants.DEFAULT_DRIVE_NAME)) {
//                    config.setConnectionTestStatement("SELECT 1 FROM DUAL");
//                }
//
//                config.setTimeBetweenEvictionRunsMillis("2400000");
//                config.setMinEvictableIdleTimeMillis("18000000");
//                config.setPoolName(nameBO.getDatabaseNameCode());
//                config.setUserName(nameBO.getJdbcUser());
//                try {
//                    config.setPassword(SimpleCrypto.decrypt(nameBO.getJdbcPassword()));
//                } catch (Exception e) {
//                    logger.error("Decrypt password is error : " , e);
//                }
//                if (StringUtils.hasText(nameBO.getJdbcUrl())) {
//                    config.setUrl(nameBO.getJdbcUrl());
//                } else {
//                    String url = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = " + nameBO.getJdbcIp() + ")(PORT = " + nameBO.getJdbcPort() + ")))(CONNECT_DATA =(SERVICE_NAME = " + nameBO.getJdbcServiceName() + ")))";
//                    config.setUrl(url);
//                }
//                logger.debug("Add database name : " + nameBO.getDatabaseName() + " to the system ");
//                Constants.MAP_JDBC_CONFIG.put(nameBO.getDatabaseNameCode(), config);
//            }
//
//            logger.fatal("After load from database now system have " + Constants.MAP_JDBC_CONFIG.size() + " databases");
//
//            try {
//                ConnectionManager.getInstance().initConfig();
//            } catch (SQLException e) {
//                logger.error("SQLException when register database to the system : ", e);
//            }
//        }
//    }
}
