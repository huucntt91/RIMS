package com.vnpt.media.rims.facade;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EnvManager {

    public static Connection getDbConnection(String poolName)
            throws Exception {
        Connection conn_ = null;
        try {
            Context ctx_ = new InitialContext();
            DataSource dataSource_ = (DataSource) ctx_.lookup(poolName);
            conn_ = dataSource_.getConnection();
        } catch (NamingException | SQLException ex) {
            throw ex;
        }
        return conn_;
    }
}
