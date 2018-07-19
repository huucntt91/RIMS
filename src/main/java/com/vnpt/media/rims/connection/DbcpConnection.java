package com.vnpt.media.rims.connection;

import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.ConnectionException;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbcpConnection implements ConnectionProvider {

    private static Logger logger = LogManager.getLogger(DbcpConnection.class);
    private JdbcConfig config = null;

    public DbcpConnection() {
    }

    public DbcpConnection(JdbcConfig config) throws SQLException {
        this.config = config;
        this.initConfig();
    }

    private void initConfig() throws SQLException {
        if (config == null) {
            throw new SQLException("DBPool could not be created ");
        }
        if (!StringUtils.hasText(config.getDriverName())) {
            throw new SQLException("DBPool could not be created: DB Driver Name cannot be null");
        }

        if (!StringUtils.hasText(config.getUrl())) {
            throw new SQLException("DBPool Url is null");
        }

        if (!StringUtils.hasText(config.getUserName())) {
            throw new SQLException("DBPool Username is null");
        }

        if (!StringUtils.hasText(config.getPassword())) {
            throw new SQLException("DBPool Passowrd is null");
        }

        // create an instance of the JDBC driver
        try {
            Class.forName(config.getDriverName()).newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            logger.error("InstantiationException :", e);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            logger.error("IllegalAccessException :", e);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            logger.error("ClassNotFoundException :", e);
        }

        // initialize a keyed object pool to store connections
        GenericObjectPool objectPool = new GenericObjectPool(null);

        objectPool.setMaxActive(config.getMaxActive());
        objectPool.setMaxIdle(config.getMaxIdle());
        objectPool.setMinIdle(config.getMinIdle());
        objectPool.setMaxWait(config.getIdleMaxAge());

        if (StringUtils.hasText(config.getTimeBetweenEvictionRunsMillis())) {
            objectPool.setTimeBetweenEvictionRunsMillis(Long.parseLong(config.getTimeBetweenEvictionRunsMillis()));
            int maxIdle = objectPool.getMaxIdle();
            int numTestsPerEvictionRun = (int) Math.ceil(maxIdle / 4.0D);
            objectPool.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        }

        if (StringUtils.hasText(config.getMinEvictableIdleTimeMillis())) {
            objectPool.setMinEvictableIdleTimeMillis(Long.parseLong(config.getMinEvictableIdleTimeMillis()));
        }

        String validationQuery = null;

        if (StringUtils.hasText(config.getConnectionTestStatement())) {
            objectPool.setTestOnBorrow(true);
            objectPool.setTestWhileIdle(true);
            objectPool.setTestOnReturn(true);
            validationQuery = config.getConnectionTestStatement().trim();
            logger.debug("Setting validation Query ");
        }

        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(config.getUrl(), config.getUserName(), config.getPassword());

        // initialize a factory to obtain pooled connections and prepared statements
        new PoolableConnectionFactory(connectionFactory, objectPool, null, validationQuery, false, true);

        PoolingDriver driver = new PoolingDriver();
        driver.registerPool(config.getPoolName(), objectPool);

        logger.debug("Init configuration and driver are success");
    }

    public Connection getConnection() throws ConnectionException {
        // nothing
        return null;
    }

    public Connection getConnection(String poolName) throws ConnectionException {
        try {
            JdbcConfig jdbcConfig = null;
            if (Constants.MAP_JDBC_CONFIG.containsKey(poolName.trim())) {
                jdbcConfig = (JdbcConfig) Constants.MAP_JDBC_CONFIG.get(poolName.trim());
            }

            if (jdbcConfig.getPoolName().trim().equalsIgnoreCase(poolName)) {// call connection ko phai tu DB tham chieu
                return DriverManager.getConnection("jdbc:apache:commons:dbcp:" + poolName);
            } else {// call connection tu DB tham chieu
                return DriverManager.getConnection("jdbc:apache:commons:dbcp:" + jdbcConfig.getPoolName().trim());
            }

        } catch (SQLException e) {
            logger.error("SQL Exception get connection:", e);
            return null;
        }
    }

    public void shutdown() throws ConnectionException {
        // nothing
    }

    public void shutdown(String poolName) throws ConnectionException {
        PoolingDriver driver = null;
        try {
            driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            if (driver != null) {
                driver.closePool(poolName);
                logger.error("The system shutdown pool is sussccessfully ");
            } else {
                logger.error("The system shutdown pool is null ");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("The system don't shutdown pool : ", e);
        }
    }
}
