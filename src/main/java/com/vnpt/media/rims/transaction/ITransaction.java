package com.vnpt.media.rims.transaction;

import com.vnpt.media.rims.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
 
public interface ITransaction {

    void beginTransaction() throws DAOException;

    void connectionType(String typeConn) throws DAOException;

    void rollback() throws DAOException;

    void endTransaction() throws DAOException;

    Connection getConnection() throws SQLException;

    void closeConnection() throws DAOException;

    void commit() throws DAOException;

}
