package com.vnpt.media.rims.connection;

import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcDriverConnection implements ConnectionProvider {

    private static Logger logger = LogManager.getLogger(JdbcDriverConnection.class);
    private JdbcConfig config = null;

    public JdbcDriverConnection() {
    }

    public JdbcDriverConnection(JdbcConfig config) throws SQLException {
        this.config = config;
        this.init();
    }

    private void init() throws SQLException {
        // validate config db
        if (config == null) {
            logger.error("Jdbc config is null");
            throw new SQLException("Jdbc config is null");
        }

        if (!StringUtils.hasText(config.getDriverName())) {
            logger.error("Driver name is null");
            throw new SQLException("Driver name is null");
        }

        if (!StringUtils.hasText(config.getUrl())) {
            logger.error("URL is null");
            throw new SQLException("URL is null");
        }

        if (!StringUtils.hasText(config.getUserName())) {
            logger.error("Username is null");
            throw new SQLException("Username is null");
        }

        if (!StringUtils.hasText(config.getPassword())) {
            logger.error("Password is null");
            throw new SQLException("Password is null");
        }
        logger.debug("Init configuration is success");
    }

    public Connection getConnection() throws ConnectionException {
        Connection conn = null;
        try {
            if (config == null) {
                logger.error("Jdbc config is null");
                return null;
            }

            Class.forName(config.getDriverName()).newInstance();

            conn = DriverManager.getConnection(config.getUrl(), config.getUserName().trim(), config.getPassword().trim());

            logger.debug("Connection is successfully");

            return conn;

        } catch (InstantiationException e) {
            logger.error("InstantiationException : ", e);
            return null;
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException : ", e);
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException : ", e);
            return null;
        } catch (SQLException e) {
            logger.error("SQLException : ", e);
            return null;
        }
    }

    public Connection getConnection(String nameConnection) throws ConnectionException {
        logger.debug("JDBC connection don't name connection : " + nameConnection);
        return this.getConnection();
    }

    public void shutdown() throws ConnectionException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void shutdown(String nameConnection) throws ConnectionException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
