package com.vnpt.media.rims.dao.impl;


import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.transaction.ITransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: haidd
 * Date: 6/20/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericDAO {

    protected static final Logger logger = LogManager.getLogger(GenericDAO.class);

    private Connection connection = null;


    protected GenericDAO() {
        super();
    }

    public Connection getConnection() throws DAOException {
        if (connection == null) {
            throw new DAOException("Connection is null please check connection again ");
        }
        return connection;
    }

    protected final void setConnection(Connection conn) {
        this.connection = conn;
    }


    public void setTransaction(ITransaction trans) throws DAOException {
        if (trans == null) {
            throw new DAOException("Transaction is null please check setting transaction");
        }
        try {
            setConnection(trans.getConnection());
            logger.trace("Set connection for JDBC Transaction ");
        } catch (SQLException e) {
            logger.error("SQL Exception: " + e.getMessage(), e);
            throw new DAOException(e.getMessage(), e);
        }
    }


}
