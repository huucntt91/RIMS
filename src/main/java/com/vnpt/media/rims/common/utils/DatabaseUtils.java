package com.vnpt.media.rims.common.utils;

import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.transaction.ITransaction;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Collection;
import java.util.Date;
import org.apache.logging.log4j.LogManager;

public class DatabaseUtils {

    protected static org.apache.logging.log4j.Logger logger = LogManager.getLogger(DatabaseUtils.class);

    public static final int TYPE_UNKNOWN = Integer.MIN_VALUE;

    public static boolean isConnectOpen(Connection conn) {
        if (conn == null) {
            return false;
        }
        try {
            if (!conn.isClosed()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public static void close(PreparedStatement pStmt) {
        if (pStmt == null) {
            return;
        }
        try {
            pStmt.close();
        } catch (SQLException e) {
            logger.error("Prepared statement close error", e);
        } finally {
            pStmt = null;
        }
    }

    public static void close(Statement stmt) {
        if (stmt == null) {
            return;
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            logger.error("Statement close error", e);
        } finally {
            stmt = null;
        }
    }

    public static void close(CallableStatement cStmt) {
        if (cStmt == null) {
            return;
        }
        try {
            cStmt.close();
        } catch (SQLException e) {
            logger.error("Callable statement close error", e);
        } finally {
            cStmt = null;
        }
    }

    public static void close(ResultSet rs) {
        if (rs == null) {
            return;
        }
        try {
            rs.close();
        } catch (SQLException e) {
            logger.error("ResultSet close error", e);
        } finally {
            rs = null;
        }
    }

    public static void close(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            if ((conn != null) && (!conn.isClosed())) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("Connection ", e);
        } finally {
            conn = null;
        }
    }

    public static void close(ITransaction transaction) {
        if (transaction == null) {
            return;
        }
        try {
            if (transaction != null) {
                logger.trace("Close transaction ");
                transaction.closeConnection();
            }
        } catch (DAOException e) {
            logger.error("Connection of Transaction ", e);
        } finally {
            transaction = null;
        }
    }

    public static void closeAll(ResultSet rs, PreparedStatement pstm, Connection conn) {
        close(rs);
        close(pstm);
        close(conn);
    }


    public static void close(Object dbObj) {
        if (dbObj == null) {
            return;
        }
        if (dbObj instanceof PreparedStatement) {
            close((PreparedStatement) dbObj);
        } else if (dbObj instanceof Statement) {
            close((Statement) dbObj);
        } else if (dbObj instanceof ResultSet) {
            close((ResultSet) dbObj);
        } else if (dbObj instanceof CallableStatement) {
            close((CallableStatement) dbObj);
        } else if (dbObj instanceof Connection) {
            close((Connection) dbObj);
        } else {
            throw new IllegalArgumentException("Close attempted on unrecognized Database Object!");
        }
    }

    public static void close(Object... object) {
        if (object != null) {
            for (Object obj : object) {
                close(obj);
            }
        }
    }

    /**
     * Converts a java.sql.Timestamp to a java.util.Date. Null arguments
     * generate an IllegalArgumentException.
     *
     * @param ts
     * @return Date
     */
    public static Date convertTimestampToUtilDate(Timestamp ts) {
        if (ts == null) {
            throw new IllegalArgumentException("Null timestamp not allowed.");
        }
        return new Date(ts.getTime());
    }

    /**
     * Converts a java.util.Date to a java.sql.Timestamp. Null arguments
     * generate an IllegalArgumentException.
     *
     * @param date
     * @return Timestamp
     */
    public static Timestamp convertUtilDateToTimestamp(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Null timestamp not allowed.");
        }
        return new Timestamp(date.getTime());
    }

    /**
     * Provides a java.sql.Timestamp set to the current ssytem time.
     *
     * @return Timestamp Current
     */
    public static Timestamp getCurrentTimestamp() {
        return convertUtilDateToTimestamp(new Date());
    }

    /**
     * Rolls back a dataabse transaction logging a warning if a SQLException is
     * encountered. Use of this version of rollback eliminates the need for
     * verbose try/catch logic to handle the SQLException.
     */
    public static void rollback(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.rollback();
        } catch (SQLException e) {
            logger.error("Rollback close error", e);
        }
    }


    public static void rollback(ITransaction transaction) {
        if (transaction == null) {
            return;
        }
        try {
            transaction.rollback();
            logger.debug("Rollback transaction");
        } catch (DAOException e) {
            logger.error("Rollback close error", e);
        }
    }

    public static void rollback(Connection... connection) {
        if (connection != null) {
            for (Connection conn : connection) {
                rollback(conn);
            }
        }
    }


    public static void rollbackAutoCommit(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.rollback();
        } catch (SQLException e) {
            logger.error("Rollback close error", e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex1) {
                logger.error("SQL Exception RollBack : " + ex1);
            }
        }
    }


    /**
     * Retrieve a JDBC column value from a ResultSet, using the most appropriate
     * value type. The returned value should be a detached value object, not
     * having any ties to the active ResultSet: in particular, it should not be
     * a Blob or Clob object but rather a byte array respectively String
     * representation. <p> Uses the
     * <code>getObject(index)</code> method, but includes additional "hacks" to
     * get around Oracle 10g returning a non-standard object for its TIMESTAMP
     * datatype and a
     * <code>java.sql.Date</code> for DATE columns leaving out the time portion:
     * These columns will explicitly be extracted as standard
     * <code>java.sql.Timestamp</code> object.
     *
     * @param rs    is the ResultSet holding the data
     * @param index is the column index
     * @return the value object
     * @throws java.sql.SQLException if thrown by the JDBC API
     * @see java.sql.Blob
     * @see java.sql.Clob
     * @see java.sql.Timestamp
     */
    public static Object getResultSetValue(ResultSet rs, int index) throws SQLException {
        Object obj = rs.getObject(index);
        String className = null;
        if (obj != null) {
            className = obj.getClass().getName();
        }
        if (obj instanceof Blob) {
            obj = rs.getBytes(index);
        } else if (obj instanceof Clob) {
            obj = rs.getString(index);
        } else if (className != null
                && ("oracle.sql.TIMESTAMP".equals(className) || "oracle.sql.TIMESTAMPTZ".equals(className))) {
            obj = rs.getTimestamp(index);
        } else if (className != null && className.startsWith("oracle.sql.DATE")) {
            String metaDataClassName = rs.getMetaData().getColumnClassName(
                    index);
            if ("java.sql.Timestamp".equals(metaDataClassName)
                    || "oracle.sql.TIMESTAMP".equals(metaDataClassName)) {
                obj = rs.getTimestamp(index);
            } else {
                obj = rs.getDate(index);
            }
        } else if (obj != null && obj instanceof java.sql.Date) {
            if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
                obj = rs.getTimestamp(index);
            }
        }
        return obj;
    }

    /**
     * Retrieve a JDBC column value from a ResultSet, using the specified value
     * type.
     * <p/>
     * Uses the specifically typed ResultSet accessor methods, falling back to
     * {@link #getResultSetValue(java.sql.ResultSet, int)} for unknown types.
     * <p/>
     * Note that the returned value may not be assignable to the specified
     * required type, in case of an unknown type. Calling code needs to deal
     * with this case appropriately, e.g. throwing a corresponding exception.
     *
     * @param rs           is the ResultSet holding the data
     * @param index        is the column index
     * @param requiredType the required value type (may be <code>null</code>)
     * @return the value object
     * @throws java.sql.SQLException if thrown by the JDBC API
     */
    public static Object getResultSetValue(ResultSet rs, int index,
                                           Class<?> requiredType) throws SQLException {
        if (requiredType == null) {
            return getResultSetValue(rs, index);
        }

        Object value = null;
        boolean wasNullCheck = false;

        // Explicitly extract typed value, as far as possible.
        if (String.class.equals(requiredType)) {
            value = rs.getString(index);
        } else if (boolean.class.equals(requiredType)
                || Boolean.class.equals(requiredType)) {
            value = Boolean.valueOf(rs.getBoolean(index));
            wasNullCheck = true;
        } else if (byte.class.equals(requiredType)
                || Byte.class.equals(requiredType)) {
            value = new Byte(rs.getByte(index));
            wasNullCheck = true;
        } else if (short.class.equals(requiredType)
                || Short.class.equals(requiredType)) {
            value = new Short(rs.getShort(index));
            wasNullCheck = true;
        } else if (int.class.equals(requiredType)
                || Integer.class.equals(requiredType)) {
            value = new Integer(rs.getInt(index));
            wasNullCheck = true;
        } else if (long.class.equals(requiredType)
                || Long.class.equals(requiredType)) {
            value = new Long(rs.getLong(index));
            wasNullCheck = true;
        } else if (float.class.equals(requiredType)
                || Float.class.equals(requiredType)) {
            value = new Float(rs.getFloat(index));
            wasNullCheck = true;
        } else if (double.class.equals(requiredType)
                || Double.class.equals(requiredType)
                || Number.class.equals(requiredType)) {
            value = new Double(rs.getDouble(index));
            wasNullCheck = true;
        } else if (byte[].class.equals(requiredType)) {
            value = rs.getBytes(index);
        } else if (java.sql.Date.class.equals(requiredType)) {
            value = rs.getDate(index);
        } else if (Time.class.equals(requiredType)) {
            value = rs.getTime(index);
        } else if (Timestamp.class.equals(requiredType)
                || Date.class.equals(requiredType)) {
            value = rs.getTimestamp(index);
        } else if (BigDecimal.class.equals(requiredType)) {
            value = rs.getBigDecimal(index);
        } else if (Blob.class.equals(requiredType)) {
            value = rs.getBlob(index);
        } else if (Clob.class.equals(requiredType)) {
            value = rs.getClob(index);
        } else {
            // Some unknown type desired -> rely on getObject.
            value = getResultSetValue(rs, index);
        }

        // Perform was-null check if demanded (for results that the
        // JDBC driver returns as primitives).
        if (wasNullCheck && value != null && rs.wasNull()) {
            value = null;
        }
        return value;
    }

    /**
     * Extract a common name for the database in use even if various
     * drivers/platforms provide varying names.
     *
     * @param source the name as provided in database metedata
     * @return the common name to be used
     */
    public static String commonDatabaseName(String source) {
        String name = source;
        if (source != null && source.startsWith("DB2")) {
            name = "DB2";
        } else if ("Sybase SQL Server".equals(source)
                || "Adaptive Server Enterprise".equals(source)
                || "sql server".equals(source)) {
            name = "Sybase";
        }
        return name;
    }

    /**
     * Check whether the given SQL type is numeric.
     *
     * @param sqlType the SQL type to be checked
     * @return whether the type is numeric
     */
    public static boolean isNumeric(int sqlType) {
        return Types.BIT == sqlType || Types.BIGINT == sqlType
                || Types.DECIMAL == sqlType || Types.DOUBLE == sqlType
                || Types.FLOAT == sqlType || Types.INTEGER == sqlType
                || Types.NUMERIC == sqlType || Types.REAL == sqlType
                || Types.SMALLINT == sqlType || Types.TINYINT == sqlType;
    }


    /**
     * Determine the column name to use. The column name is determined based on
     * a lookup using ResultSetMetaData. <p> This method implementation takes
     * into account recent clarifications expressed in the JDBC 4.0
     * specification: <p> <i>columnLabel - the label for the column specified
     * with the SQL AS clause. If the SQL AS clause was not specified, then the
     * label is the name of the column</i>.
     *
     * @param resultSetMetaData the current meta data to use
     * @param columnIndex       the index of the column for the look up
     * @return the column name to use
     * @throws java.sql.SQLException in case of lookup failure
     */
    public static String lookupColumnName(ResultSetMetaData resultSetMetaData,
                                          int columnIndex) throws SQLException {
        String name = resultSetMetaData.getColumnLabel(columnIndex);
        if (name == null || name.length() < 1) {
            name = resultSetMetaData.getColumnName(columnIndex);
        }
        return name;
    }

    /**
     * Return whether the given JDBC driver supports JDBC 2.0 batch updates.
     * <p>Typically invoked right before execution of a given set of statements:
     * to decide whether the set of SQL statements should be executed through
     * the JDBC 2.0 batch mechanism or simply in a traditional one-by-one fashion.
     * <p>Logs a warning if the "supportsBatchUpdates" methods throws an exception
     * and simply returns {@code false} in that case.
     * @param con the Connection to check
     * @return whether JDBC 2.0 batch updates are supported
     * @see java.sql.DatabaseMetaData#supportsBatchUpdates()
     */
    public static boolean supportsBatchUpdates(Connection con) {
        try {
            DatabaseMetaData dbmd = con.getMetaData();
            if (dbmd != null) {
                if (dbmd.supportsBatchUpdates()) {
                    logger.debug("JDBC driver supports batch updates");
                    return true;
                }
                else {
                    logger.debug("JDBC driver does not support batch updates");
                }
            }
        }
        catch (SQLException ex) {
            logger.debug("JDBC driver 'supportsBatchUpdates' method threw exception", ex);
        }
        catch (AbstractMethodError err) {
            logger.debug("JDBC driver does not support JDBC 2.0 'supportsBatchUpdates' method", err);
        }
        return false;
    }


    /**
     * Convert a column name with underscores to the corresponding property name
     * using "camel case". A name like "customer_number" would match a
     * "customerNumber" property name.
     *
     * @param name the column name to be converted
     * @return the name using "camel case"
     */
    public static String convertUnderscoreNameToPropertyName(String name) {
        StringBuffer result = new StringBuffer();
        boolean nextIsUpper = false;
        if (name != null && name.length() > 0) {
            if (name.length() > 1 && name.substring(1, 2).equals("_")) {
                result.append(name.substring(0, 1).toUpperCase());
            } else {
                result.append(name.substring(0, 1).toLowerCase());
            }
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals("_")) {
                    nextIsUpper = true;
                } else {
                    if (nextIsUpper) {
                        result.append(s.toUpperCase());
                        nextIsUpper = false;
                    } else {
                        result.append(s.toLowerCase());
                    }
                }
            }
        }
        return result.toString();
    }

    public static Object requiredSingleResult(Collection<?> results) throws SQLException {
        int size = (results != null ? results.size() : 0);
        if (size == 0) {
            return null;//throw new SQLException("Don't have record");
        }
        if (results.size() > 1) {
            throw new SQLException("The system have many records : "
                    + String.valueOf(size));
        }
        return results.iterator().next();
    }


    public static long getSequence(String sequenceName, Connection connection) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.prepareStatement("select " + sequenceName.trim() + ".NEXTVAL SEQ from dual");
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("SEQ");
            } else {
                return 0;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            close(rs);
            close(stmt);
        }
    }
}
