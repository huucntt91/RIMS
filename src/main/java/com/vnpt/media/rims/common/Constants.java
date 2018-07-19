package com.vnpt.media.rims.common;

import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.connection.JdbcConfig;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Constants {

    public static final String FOLDER_SAVE_IMG = "/resources/img/topo/";
    public static final int BATCH_ROW_COMMIT = 30;
    public static final int NUMBER_FOR_PAGING = 10;
    private static final Logger logger = LogManager.getLogger(Constants.class);
    public static final String USER_KEY = "user";
    public static final String MENU_KEY = "menu";
    public static final String FUNCTION_KEY = "function";

    public static final String PROVINCE_KEY = "province_manager";

    public static final String DEFAULT_PASSWORD = "123456";
    // dinh nghia cac file cau hinh
    public static final String PATH = "../conf/";
    public static final String PACKAGE_CONFIG = "vn/vnpt/media/common/";
    public static final String CONFIGURATION_FILENAME = "config.properties";
    public static final String LOG4J_FILENAME = "log4j.properties";
    public static final String CONFIG_FILE_PATH = PATH + File.separator + CONFIGURATION_FILENAME;
    public static final String LINE_FEED = System.getProperty("line.separator");
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static Map CONFIGURATION = new Hashtable();

    // dinh nghia connection
    public static final int JDBC_POOL = 1;
    public static final int DBCP_POOL = 2;
    public static final String DB_CB = "rims_web";
    public static final String DB_AUDIT = "db_audit";

    public static String DEFAULT_DRIVE_NAME = null;
    public static Map MAP_JDBC_CONFIG = new Hashtable();

    public static final int JAVA_STRING = 1;
    public static final int JAVA_DATE = 2;
    public static final int JAVA_INT = 3;
    public static final int JAVA_SHORT = 4;
    public static final int JAVA_FLOAT = 5;
    public static final int JAVA_DOUBLE = 6;
    public static final String NULL_STR = "";
    public static final Date NULL_DATE = new Date(0);
    public static final int NULL_INT = Integer.MIN_VALUE;
    public static final short NULL_SHORT = Short.MIN_VALUE;
    public static final float NULL_FLOAT = Float.MIN_VALUE;
    public static final double NULL_DOUBLE = Double.MIN_VALUE;

    public static String KEY_ENCRYPTION = null;

    public static int SOCKET_TIMEOUT = 20000;

//    public static int NE_UPDATE_CELL_STATUS=10;
//    public static int NE_ADD_CELL_STATUS=10;//y/c on air
//    public static int NE_APPROVE_CELL_STATUS=20;// duyet on air
//    public static int NE_UN_APPROVE_CELL_STATUS=30;// khong duyet on air
//    public static int NE_UN_APPROVE_CELL_OFF_STATUS=40;// duyet off air
//    public static int NE_APPROVE_CELL_OFF_STATUS=20;// khong duyet on air
    public static int NE_REG_ON = 112;
    public static int NE_APPROVE_ON = 211;
    public static int NE_UNAPPROVE_ON = 121;
    public static int NE_REG_OFF = 212;
    public static int NE_APPROVE_OFF = 111;
    public static int NE_UNAPPROVE_OFF = 221;
    public static String API_RIMS = "";
    static {
        loadConfiguration();
    }

    public static void loadConfiguration() {
//        Properties props = new Properties();
        Resource resource = new ClassPathResource(CONFIGURATION_FILENAME);
        if (resource == null) {
            resource = new ClassPathResource(PACKAGE_CONFIG + CONFIGURATION_FILENAME);
        }
        Properties props = null;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
//        InputStream in = null;
        try {
//            in = Constants.class.getClassLoader().getResourceAsStream(CONFIGURATION_FILENAME);
////            PropertyConfigurator.configure("config/" + LOG4J_FILENAME);
//
//            if (in == null) {
//                in = Constants.class.getClassLoader().getResourceAsStream("/" + CONFIGURATION_FILENAME);
//            }
//            if (in == null) {
//                in = Constants.class.getClassLoader().getResourceAsStream(PACKAGE_CONFIG + CONFIGURATION_FILENAME);
//            }
//
//            if (in == null) {
//                File propFile = new File(CONFIG_FILE_PATH);
//                if (propFile.exists()) {
//                    try {
//                        in = new BufferedInputStream(new FileInputStream(CONFIG_FILE_PATH));
//                    } catch (IOException e) {
//                        logger.error("ERROR: Could not load config file at: " + CONFIGURATION_FILENAME, e);
//                        System.exit(-1);
//                    }
//                }
//            }
//
//            if (in == null) {
//                logger.error("ERROR: Could not load config file at: " + CONFIGURATION_FILENAME);
//                System.exit(-1);
//            }
//
//            try {
//                props.load(in);
//
//            } catch (IOException e) {
//                logger.error("ERROR: Could not load config file at: " + CONFIGURATION_FILENAME, e);
//                System.exit(-1);
//            }

            CONFIGURATION.clear();

            CONFIGURATION.put("CONFIGURATION_FILENAME", CONFIGURATION_FILENAME);

            Enumeration resKeys = props.keys();
            while (resKeys.hasMoreElements()) {
                String resKey = (String) resKeys.nextElement();
                CONFIGURATION.put(resKey, props.getProperty(resKey));
            }

            DEFAULT_DRIVE_NAME = props.getProperty("driver_name");
            API_RIMS = props.getProperty("API_RIMS");
            int i = 1;
            while (true) {

                String poolName = props.getProperty("database_pool_name_" + i);

                if (!StringUtils.hasText(poolName)) {
                    break;
                }

                String driverName = props.getProperty("database_driver_name_" + i);

                if (!StringUtils.hasText(driverName)) {
                    driverName = DEFAULT_DRIVE_NAME;// default driver for oracle
                }

                String databaseRef = props.getProperty("database_ref_" + i);

                if (StringUtils.hasText(databaseRef)) {
                    // database nay cung duong dan voi ref tren he thong
                    if (MAP_JDBC_CONFIG.containsKey(databaseRef.trim())) {
                        JdbcConfig config = (JdbcConfig) MAP_JDBC_CONFIG.get(databaseRef.trim());
                        MAP_JDBC_CONFIG.put(poolName, config);
                    } else {// ko ton tai database tham chieu
                        logger.error("Khong ton tai database ref : " + databaseRef);
                    }
                } else {// ko thiet lap db tham chieu
                    String url = props.getProperty("database_url_" + i);
                    String userName = props.getProperty("database_user_name_" + i);
                    String password = props.getProperty("database_password_" + i);

                    JdbcConfig config = new JdbcConfig(driverName, url, userName, password);
                    config.setTypeConnec(Integer.parseInt(props.getProperty("database_type_connect_" + i)));
                    config.setMaxActive(Integer.parseInt(props.getProperty("database_max_active_" + i)));
                    config.setMaxIdle(Integer.parseInt(props.getProperty("database_max_idle_" + i)));
                    config.setMinIdle(Integer.parseInt(props.getProperty("database_min_idle_" + i)));
                    config.setIdleMaxAge(Integer.parseInt(props.getProperty("database_max_wait_" + i)));
                    config.setConnectionTestStatement(props.getProperty("database_validationQuery_" + i));
                    config.setTimeBetweenEvictionRunsMillis(props.getProperty("database_timeBetweenEvictionRunsMillis_" + i));
                    config.setMinEvictableIdleTimeMillis(props.getProperty("database_minEvictableIdleTimeMillis_" + i));
                    config.setPoolName(poolName);

                    MAP_JDBC_CONFIG.put(poolName, config);
                }

                i++;
            }

        } catch (Exception e) {
            logger.error("Exception : ", e);
        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    logger.error("Don't close the file : " + e.getMessage());
//                }
//            }
        }
    }
}
