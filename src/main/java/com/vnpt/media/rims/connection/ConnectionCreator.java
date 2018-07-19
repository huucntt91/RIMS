package com.vnpt.media.rims.connection;

public class ConnectionCreator extends AbstractConnection {

    private JdbcConfig jdbcConfig = null;

    public ConnectionCreator(JdbcConfig jdbcConfig) {
        super();
        this.jdbcConfig = jdbcConfig;
    }

    public JdbcConfig getJdbcConfig() {
        // TODO Auto-generated method stub
        return this.jdbcConfig;
    }
}
