package com.vnpt.media.rims.transaction.impl;

import com.vnpt.media.rims.connection.ConnectionManager;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.transaction.ITransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
 
public class JDBCTransaction implements ITransaction {

    private Logger logger = LogManager.getLogger(JDBCTransaction.class);

    private Connection connection = null;
    private String typeConn = null;

    public JDBCTransaction() {
        super();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = ConnectionManager.getInstance().getConnection(typeConn);
        }
        return connection;
    }

    public void beginTransaction() throws DAOException {
        Connection conn = null;
        try {

            conn = getConnection();
            conn.setAutoCommit(false);

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public void connectionType(String typeConn) throws DAOException {
        this.setTypeConn(typeConn);
    }

    public void commit() throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.commit();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public void rollback() throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.rollback();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public void endTransaction() throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public void closeConnection() throws DAOException {
        Connection conn = null;
        try {

            conn = getConnection();
            if ((conn != null) && (!conn.isClosed())) {
                conn.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public String getTypeConn() {
        return typeConn;
    }

    public void setTypeConn(String typeConn) {
        this.typeConn = typeConn;
    }
}
