package com.vnpt.media.rims.connection;

import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.ConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionManager {

    private static final Logger logger = LogManager.getLogger(ConnectionManager.class);
    private static volatile  ConnectionManager instance = null;
    private Map providers = new Hashtable();

    private ConnectionManager() throws SQLException {
        initConfig();
    }

    public synchronized void initConfig() throws SQLException{
        Map map = Constants.MAP_JDBC_CONFIG;
        Map<String,String> databaseRef = new Hashtable<String, String>();
        if (!StringUtils.isEmpty(map)) {
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if(providers.containsKey(key)){
//                    logger.debug(key + " exits in the system ");
                }else{
                    JdbcConfig jdbcConfig = (JdbcConfig) map.get(key);
                    if(StringUtils.hasText(jdbcConfig.getDatabaseRef())){// neu co DB tham chieu
                        databaseRef.put(jdbcConfig.getPoolName().trim(), jdbcConfig.getDatabaseRef().trim());
                    }else{// khai bao moi DB ko co tham chieu den DB nao
                        // dang ky connection voi DBCP
                        this.addConnectionProvider(key, registerDriverConnection(jdbcConfig.getTypeConnec(), jdbcConfig));
//                        logger.debug("Register Driver Connection is successfully : " + key);
                    }
                }
            }

            if(!StringUtils.isEmpty(databaseRef)){// ton tai khai bao tham chieu DB
                for (Map.Entry<String, String> entry : databaseRef.entrySet()) {
                    ConnectionProvider provider = (ConnectionProvider) providers.get(entry.getValue());
                    if(providers.containsKey(entry.getKey())){
//                        logger.debug(entry.getKey() + " exits in the system ");
                    }else{
                        // them vao tham so quan ly connection provider
                        this.addConnectionProvider(entry.getKey(),provider);
//                        logger.debug("Register Driver Connection of database ref is successfully : " + entry.getKey());
                    }
                }
            }
        }
    }

    private ConnectionProvider registerDriverConnection(int typeConnection, JdbcConfig jdbcConfig) throws SQLException {
        AbstractConnection connection = new ConnectionCreator(jdbcConfig);
        ConnectionProvider provider = connection.getConnectionFactory(typeConnection);
        return provider;
    }

    private void addConnectionProvider(String dataSourceName, ConnectionProvider provider) {
        this.providers.put(dataSourceName, provider);
    }

    /**
     * Get the class instance.
     *
     * @return an instance of this class
     * @throws java.sql.SQLException
     */
    public static synchronized ConnectionManager getInstance() throws SQLException {
        // since the instance variable is initialized at class loading time,
        // it's not necessary to synchronize this method
        if (instance == null) {
            synchronized (ConnectionManager.class){
                if(instance == null){
                    instance = new ConnectionManager();
                }
            }
        }

        return instance;
    }

    public ConnectionProvider getProvider(String dsName) {
        return (ConnectionProvider) providers.get(dsName);
    }

    /**
     * Get a database connection from the DataSource with the given name.
     *
     * @return a database connection
     * @throws java.sql.SQLException if an error occurs, or there is no DataSource with the given name.
     */
    public Connection getConnection(String dsName) throws ConnectionException {

        if(!StringUtils.hasText(dsName)){
            logger.error("Parameter dsName is null ");
            throw new ConnectionException("Parameter dsName is null ");
        }

        synchronized (dsName){
            if(providers == null || this.getProvider(dsName) == null){
                JdbcConfig jdbcConfig = (JdbcConfig) Constants.MAP_JDBC_CONFIG.get(dsName);
                try {
                    this.addConnectionProvider(jdbcConfig.getPoolName(), registerDriverConnection(jdbcConfig.getTypeConnec(), jdbcConfig));
                }catch (SQLException e){
                    logger.error("SQLException when try create database pool : ",e);
                }
            }

            ConnectionProvider provider = this.getProvider(dsName);

            if (provider == null) {
                logger.error("Can't connection to database : " + dsName);
                throw new ConnectionException("There is no DataSource named '" + dsName + "'");
            }

            if (StringUtils.hasText(dsName)) {
                return provider.getConnection(dsName);
            } else {
                return provider.getConnection();
            }
        }
    }

    /**
     * Shuts down database connections from the DataSource with the given name,
     * if applicable for the underlying provider.
     *
     * @throws java.sql.SQLException if an error occurs, or there is no DataSource with the given name.
     */
    public void shutdown(String dsName) throws ConnectionException {
        ConnectionProvider provider = this.getProvider(dsName);
        if (provider == null) {
            throw new ConnectionException("There is no DataSource named '" + dsName + "'");
        }

        if (StringUtils.hasText(dsName)) {
            provider.shutdown(dsName);
        } else {
            provider.shutdown();
        }
    }

    public void shutdown() throws ConnectionException {
        if (providers != null && providers.size() > 0) {
            Iterator iterator = providers.keySet().iterator();
            while (iterator.hasNext()) {
                String dsName = (String) iterator.next();
                this.shutdown(dsName);
//                logger.debug("Shutdown database name : " + dsName);
            }
        } else {
            logger.error("All DB don't shutdown ");
        }
    }
}
