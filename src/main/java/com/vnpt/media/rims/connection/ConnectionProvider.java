package com.vnpt.media.rims.connection;

import com.vnpt.media.rims.exception.ConnectionException;

import java.sql.Connection;


public interface ConnectionProvider {

    Connection getConnection() throws ConnectionException;

    Connection getConnection(String nameConnection) throws ConnectionException;

    void shutdown() throws ConnectionException;

    void shutdown(String nameConnection) throws ConnectionException;
}
