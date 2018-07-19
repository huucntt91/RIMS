package com.vnpt.media.rims.connection;

import com.vnpt.media.rims.common.Constants;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractConnection {

    private static Logger logger = LogManager.getLogger(AbstractConnection.class);

    public ConnectionProvider getConnectionFactory(int whichFactory) throws SQLException {
        JdbcConfig jdbcConfig = getJdbcConfig();
        switch (whichFactory) {
            case Constants.JDBC_POOL:
                logger.debug("Connection type is JDBC Connect");
                return new JdbcDriverConnection(jdbcConfig);
            case Constants.DBCP_POOL:
                logger.debug("Connection type is DBCP Connect");
                return new DbcpConnection(jdbcConfig);
            default:
                logger.debug("Default : Connection type is JDBC Connect");
                return new JdbcDriverConnection(jdbcConfig);
        }
    }

    public abstract JdbcConfig getJdbcConfig();
}
