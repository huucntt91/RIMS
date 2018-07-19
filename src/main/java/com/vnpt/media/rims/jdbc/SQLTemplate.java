package com.vnpt.media.rims.jdbc;

import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.exception.JdbcException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 * Class providing SQL statement execution assistance. This is a JDBC code
 * reduction technique.
 * <p/>
 * <p/>
 * SQLAssistant can greatly reduce the amount of JDBC-related code you write and
 * maintain. Examples of how to instantiate and use a SQLAssistant follow.
 * <p/>
 * <p/>
 * Example instantiation of a SQLAssistant <blockquote>
 * <p/>
 * <
 * pre>
 * SQLAssistant sql = new SQLAssistant(_dbConnection);
 * </pre>
 * <p/>
 * </blockquote>
 * <p/>
 * <p/>
 * Example of a simple select: <blockquote>
 * <p/>
 * <
 * pre>
 * Object[][] results = null; results = sql.executeQuery(&quot;select * from
 * purchase_order&quot;);
 * </pre>
 * <p/>
 * </blockquote>
 * <p/>
 * <p/>
 * Example of a select with host variables: <blockquote>
 * <p/>
 * <
 * pre>
 * Object[][] results = null; results = sql.executeQuery( &quot;select * from
 * purchase_order where DATE_CREATED &lt; ?&quot;, new java.util.Date());
 * </pre>
 * <p/>
 * </blockquote>
 * <p/>
 * <p/>
 * Example of an update with host variables: <blockquote>
 * <p/>
 * <
 * pre>
 * int nbrRowsAffected = sql.executeUpdate( &quot;update purchase_order set
 * date_shipped = ? where order_nbr = ?&quot;, new java.util.Date(), new
 * Integer(809));
 * </pre>
 * <p/>
 * </blockquote>
 */
public class SQLTemplate {

    private Connection connection = null;
    private SimpleDateFormat defaultDateFormat = null;
    private DecimalFormat defaultDecimalFormat = null;
    private boolean spaceTrimOn = false;
    private boolean convertStringOn = false;

    private static class NullValue {
    }

    public static final NullValue NULL = new NullValue();

    public void setConnection(Connection conn) {
        connection = conn;
    }

    public Connection getConnection() {
        return connection;
    }

    public SimpleDateFormat getDefaultDateFormat() {
        return defaultDateFormat;
    }

    public DecimalFormat getDefaultDecimalFormat() {
        return defaultDecimalFormat;
    }

    public void setDefaultDateFormat(SimpleDateFormat format) {
        defaultDateFormat = format;
    }

    public void setDefaultDecimalFormat(DecimalFormat format) {
        defaultDecimalFormat = format;
    }

    public boolean isSpaceTrimOn() {
        return spaceTrimOn;
    }

    public void setSpaceTrimOn(boolean b) {
        spaceTrimOn = b;
    }

    public boolean isConvertStringOn() {
        return convertStringOn;
    }

    public void setConvertStringOn(boolean b) {
        convertStringOn = b;
    }

    public SQLTemplate() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SQLTemplate(Connection conn) {
        this.setConnection(conn);
    }

    /**
     * Executes a SQL statement making host variable substitutions where
     * appropriate.
     *
     * @param sqlText
     * @return NbrRows affected by SQL Statement
     * @throws java.sql.SQLException
     */
    public int executeUpdate(String sqlText) throws SQLException {
        return this.executeUpdate(sqlText, (Object) null);
    }

    /**
     * Executes a SQL statement making host variable substitutions where
     * appropriate.
     *
     * @param sqlText
     * @param
     * @return NbrRows affected by SQL Statement
     * @throws java.sql.SQLException
     */
    public int executeUpdate(String sqlText, Object... varObjects) throws SQLException {

        List<Object> list = new ArrayList<Object>();

        for (Object object : varObjects) {
            list.add(this.getVariableObj(object));
        }

        return this.executeUpdate(sqlText, list);
    }

    /**
     * Executes a SQL statement making host variable substitutions where
     * appropriate.
     *
     * @param sqlText
     * @return NbrRows affected by SQL Statement
     * @throws java.sql.SQLException
     */
    public int executeUpdate(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed.");
        }

        PreparedStatement pStmt = null;
        int pStmtReturn = -1;
        try {
            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            pStmtReturn = pStmt.executeUpdate();
        } finally {
            DatabaseUtils.close(pStmt);
        }

        return pStmtReturn;
    }

    public int[] executeBatchUpdate(List<String> sqlText, int batchRowCommit, Logger logger, String levelLog) throws SQLException {

        if (StringUtils.isEmpty(sqlText)) {
            return null;
        }

        if (connection == null) {
            throw new IllegalArgumentException("Don't connect to database");
        }

        Statement pStmt = null;
        try {
            pStmt = connection.createStatement();

            int countRow = 0, countBatch = 0;

            for (String s : sqlText) {
                pStmt.addBatch(s);
                //writeLogger(logger, "Add Batch SQL  : " + s, levelLog);
                countRow = countRow + 1;
                if (countRow % batchRowCommit == 0) {
                    try {
                        int[] count = pStmt.executeBatch();
                        countBatch = countBatch + count.length;
//                        writeLogger(logger, "Execute batch : " + batchRowCommit, levelLog);
                        pStmt.clearBatch();
                    } catch (BatchUpdateException e) {
                        logger.error("BatchUpdateException : ", e);

                    }
                }
            }

            int[] count = pStmt.executeBatch();
            countBatch = countBatch + count.length;
            
            //writeLogger(logger, "Execute batch : " + countBatch, levelLog);
            pStmt.clearBatch();
            return count;
        } catch (BatchUpdateException e) {
            throw new SQLException(e);
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            DatabaseUtils.close(pStmt);
        }
    }

    private void writeLogger(Logger logger, String message, String levelLog) {
        if (logger != null) {
            if (!StringUtils.hasText(levelLog)) {
                logger.debug(message);
            } else if (levelLog.trim().equalsIgnoreCase("TRACE")) {
                logger.trace(message);
            } else if (levelLog.trim().equalsIgnoreCase("DEBUG")) {
                logger.debug(message);
            } else if (levelLog.trim().equalsIgnoreCase("INFO")) {
                logger.info(message);
            } else if (levelLog.trim().equalsIgnoreCase("ERROR")) {
                logger.error(message);
            } else if (levelLog.trim().equalsIgnoreCase("WARN")) {
                logger.warn(message);
            } else if (levelLog.trim().equalsIgnoreCase("FATAL")) {
                logger.fatal(message);
            }

        }
    }

    /**
     * Executes a SQL statement making host variable substitutions where
     * appropriate.
     *
     * @param sqlText
     * @return NbrRows affected by SQL Statement
     * @throws java.sql.SQLException
     */
    public boolean executeCall(String sqlText) throws SQLException {
        return this.executeCall(sqlText, (Object) null);
    }

    /**
     * Executes a SQL statement making host variable substitutions where
     * appropriate.
     *
     * @param sqlText
     * @param
     * @return NbrRows affected by SQL Statement
     * @throws java.sql.SQLException
     */
    public boolean executeCall(String sqlText, Object... varObjects) throws SQLException {

        List<Object> list = new ArrayList<Object>();

        for (Object object : varObjects) {
            list.add(this.getVariableObj(object));
        }

        return this.executeCall(sqlText, list);
    }

    /**
     * Executes a SQL statement making host variable substitutions where
     * appropriate.
     *
     * @param sqlText
     * @return NbrRows affected by SQL Statement
     * @throws java.sql.SQLException
     */
    public boolean executeCall(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed.");
        }

        CallableStatement callStmt = null;

        try {
            callStmt = this.getPrepareCall(sqlText, hostVariableList);
            return callStmt.execute();
        } finally {
            DatabaseUtils.close(callStmt);
        }
    }

    /**
     * Executes a query and returns results.
     * <p/>
     * <p/>
     * Object Types used are determined by the underlying JDBC driver.
     *
     * @param sqlText
     * @return QueryResults
     * @throws java.sql.SQLException
     */
    public Object[][] executeQuery(String sqlText) throws SQLException {
        return this.executeQuery(sqlText, (Object) null);
    }

    /**
     * Executes a query and returns results.
     * <p/>
     * <p/>
     * Object Types used are determined by the underlying JDBC driver.
     *
     * @param sqlText
     * @param
     * @return QueryResults
     * @throws java.sql.SQLException
     */
    public Object[][] executeQuery(String sqlText, Object... varObjects) throws SQLException {
        List<Object> list = new ArrayList<Object>();

        for (Object object : varObjects) {
            list.add(this.getVariableObj(object));
        }

        return this.executeQuery(sqlText, list);
    }

    /**
     * Executes a query and returns results.
     * <p/>
     * <p/>
     * Object Types used are determined by the underlying JDBC driver.
     *
     * @param sqlText
     * @param hostVariableList
     * @return QueryResults
     * @throws java.sql.SQLException
     */
    public Object[][] executeQuery(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed ");
        }

        Object[][] queryResults = null;
        List<Object[]> queryResultList = new ArrayList<Object[]>();
        Object[] rowResults = null;

        PreparedStatement pStmt = null;
        ResultSet results = null;
        ResultSetMetaData resultsMetaData = null;

        try {
            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            results = pStmt.executeQuery();
            resultsMetaData = results.getMetaData();
            while (results.next()) {
                rowResults = this.getNextRow(results, false);
                queryResultList.add(rowResults);
            }

            if (queryResultList.size() > 0) {
                queryResults = new Object[queryResultList.size()][resultsMetaData.getColumnCount()];
                queryResults = queryResultList.toArray(queryResults);
            }
        } finally {
            DatabaseUtils.close(results, pStmt);
        }

        return queryResults;
    }

    /**
     * Gets a row from a ResultSet as an array of Java objects.
     *
     * @param results
     * @return
     * @throws java.sql.SQLException
     */
    public Object[] getNextRow(ResultSet results) throws SQLException {
        return this.getNextRow(results, true);
    }

    private Object[] getNextRow(ResultSet results, boolean issueNext) throws SQLException {
        Object[] rowResults = null;
        Object tempObj = null;
        ResultSetMetaData resultsMetaData = results.getMetaData();

        boolean rowsAvailable = true;
        if (issueNext) {
            rowsAvailable = results.next();
        }

        if (rowsAvailable) {
            rowResults = new Object[resultsMetaData.getColumnCount()];
            for (int offset = 1; offset <= resultsMetaData.getColumnCount(); offset++) {
                tempObj = results.getObject(offset);
                if (results.wasNull()) {
                    rowResults[offset - 1] = null;
                } else if (tempObj instanceof java.util.Date
                        && defaultDateFormat != null) {
                    rowResults[offset - 1] = defaultDateFormat.format(tempObj);
                } else if (tempObj instanceof String && spaceTrimOn) {
                    rowResults[offset - 1] = ((String) tempObj).trim();
                } else if (!(tempObj instanceof String) && convertStringOn) {
                    rowResults[offset - 1] = tempObj.toString();
                } else {
                    rowResults[offset - 1] = tempObj;
                }
            }
        }

        return rowResults;
    }

    /**
     * Establishes and returns a ResultSet given a query and host variables.
     *
     * @param sqlText
     * @param
     * @return ResultSet
     * @throws java.sql.SQLException
     */
    public PreparedStatement getPreparedStatement(String sqlText, Object... varObjects) throws SQLException {
        List<Object> list = new ArrayList<Object>();

        for (Object object : varObjects) {
            list.add(this.getVariableObj(object));
        }

        return this.getPreparedStatement(sqlText, list);
    }

    /**
     * Establishes and returns a ResultSet given a query and host variables.
     *
     * @param sqlText
     * @return ResultSet
     * @throws java.sql.SQLException
     */
    public PreparedStatement getPreparedStatement(String sqlText) throws SQLException {
        List<Object> list = new ArrayList<Object>();

        return this.getPreparedStatement(sqlText, list);
    }

    /**
     * Establishes and returns a ResultSet given a query and host variables.
     *
     * @param sqlText
     * @param hostVariableList
     * @return PreparedStatement
     * @throws java.sql.SQLException
     */
    public PreparedStatement getPreparedStatement(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed");
        }

        if (connection == null) {
            throw new IllegalArgumentException("Don't connect to database");
        }

        PreparedStatement pStmt = null;
        int hostOffset = 1;

        pStmt = connection.prepareStatement(sqlText);

        for (int i = 0; hostVariableList != null && i < hostVariableList.size(); i++) {
            this.setHostVariable(pStmt, hostOffset, hostVariableList.get(i));
            hostOffset++;
        }

        return pStmt;
    }

    /**
     * Establishes and returns a ResultSet given a query and host variables.
     *
     * @param sqlText
     * @param hostVariableList
     * @return PreparedStatement
     * @throws java.sql.SQLException
     */
    public CallableStatement getPrepareCall(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed");
        }

        if (connection == null) {
            throw new IllegalArgumentException("Don't connect to database");
        }

        CallableStatement call = null;
        int hostOffset = 1;

        call = connection.prepareCall(sqlText);

        for (int i = 0; hostVariableList != null && i < hostVariableList.size(); i++) {
            this.setHostVariable(call, hostOffset, hostVariableList.get(i));
            hostOffset++;
        }

        return call;
    }

    private void setHostVariable(PreparedStatement pStmt, int hostOffset, Object hostVar) throws SQLException {
        if (hostVar == null) {
            
            pStmt.setNull(hostOffset, Types.JAVA_OBJECT);
        } else if (hostVar.equals(NULL)) {
            pStmt.setNull(hostOffset, Types.JAVA_OBJECT);
        } else {
            Object localHostVar = hostVar;
            if (localHostVar instanceof java.util.Date
                    && !(localHostVar instanceof Date || localHostVar instanceof Timestamp)) {
                localHostVar = new Timestamp(
                        ((java.util.Date) localHostVar).getTime());
            }
            pStmt.setObject(hostOffset, localHostVar);
        }
    }

    private void setHostVariable(PreparedStatement pStmt, int hostOffset, Object hostVar, int TYPE) throws SQLException {
        if (hostVar == null) {
//            
            pStmt.setNull(hostOffset, TYPE);
        } else if (hostVar.equals(NULL)) {
            pStmt.setNull(hostOffset, TYPE);
        } else {
            Object localHostVar = hostVar;
            if (localHostVar instanceof java.util.Date
                    && !(localHostVar instanceof Date || localHostVar instanceof Timestamp)) {
                localHostVar = new Timestamp(
                        ((java.util.Date) localHostVar).getTime());
            }
            pStmt.setObject(hostOffset, localHostVar);
        }
    }

    private Object getVariableObj(Object value) {
        if (value == null) {
            return NULL;
        } else {
            return value;
        }
    }

    public List<?> query(String sqlText, RowMapper rowMapper, List<Object> hostVariableList) throws JdbcException {
        return (List<?>) query(sqlText, new RowMapperResultSetExtractor(rowMapper), hostVariableList);
    }

    public List<?> query(String sqlText, RowMapper rowMapper) throws JdbcException {
        return (List<?>) query(sqlText, new RowMapperResultSetExtractor(rowMapper), null);
    }

    public Object queryForObject(String sql, RowMapper rowMapper, List<Object> hostVariableList) throws JdbcException {
        List<?> results = query(sql, rowMapper, hostVariableList);
        try {
            return DatabaseUtils.requiredSingleResult(results);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    public Object queryForObject(String sql, RowMapper rowMapper) throws JdbcException {
        return queryForObject(sql, rowMapper);
    }

    public Object query(final String sqlText, final ResultSetExtractor rse, List<Object> hostVariableList) throws JdbcException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed ");
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            rs = pStmt.executeQuery();
            return rse.extractData(rs);
        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
            throw new JdbcException(e);
        } finally {
            DatabaseUtils.close(rs, pStmt);
        }

    }

    public int[] executeBatchUpdate(String sqlText, int batchSize) throws SQLException {
        return executeBatchUpdate(sqlText, null, batchSize);
    }

    public int[] executeBatchUpdate(String sqlText, List<Object> hostVariableList, int batchSize) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed.");
        }

        if (connection == null) {
            throw new IllegalArgumentException("Don't connect to database");
        }

        PreparedStatement pStmt = null;
        int hostOffset = 1;
        try {
            pStmt = connection.prepareStatement(sqlText);
            for (int j = 0; j <= batchSize; j++) {
                for (int i = 0; hostVariableList != null
                        && i < hostVariableList.size(); i++) {
                    this.setHostVariable(pStmt, hostOffset,
                            hostVariableList.get(i));
                    hostOffset++;
                }
                pStmt.addBatch();
            }
            int[] count = pStmt.executeBatch();
            pStmt.clearBatch();
            return count;
        } catch (BatchUpdateException e) {
            throw new SQLException(e);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            DatabaseUtils.close(pStmt);
        }
    }

    public int queryForInt(final String sqlText, List<Object> hostVariableList, String alias) throws JdbcException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed ");
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            rs = pStmt.executeQuery();
            if (!rs.next()) {
                return 0;
            }

            return rs.getInt(alias);
        } catch (SQLException e) {
            throw new JdbcException(e);
        } finally {
            DatabaseUtils.close(rs, pStmt);
        }
    }

    public int queryForInt(final String sqlText, List<Object> hostVariableList) throws JdbcException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed ");
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            rs = pStmt.executeQuery();
            if (!rs.next()) {
                return 0;
            }

            return rs.getInt(1);
        } catch (SQLException e) {
            throw new JdbcException(e);
        } finally {
            DatabaseUtils.close(rs, pStmt);
        }
    }

    //TungPM
    public List<?> queryFunction(String sqlText, RowMapper rowMapper) throws JdbcException {
        return (List<?>) queryFunction(sqlText, new RowMapperResultSetExtractor(rowMapper), null);
    }

    public List<?> queryFunction(String sqlText, RowMapper rowMapper, List<Object> hostVariableList) throws JdbcException {
        return (List<?>) queryFunction(sqlText, new RowMapperResultSetExtractor(rowMapper), hostVariableList);
    }

    public Object queryFunctionForObject(String sql, RowMapper rowMapper, List<Object> hostVariableList) throws JdbcException {
        List<?> results = queryFunction(sql, rowMapper, hostVariableList);
        try {
            return DatabaseUtils.requiredSingleResult(results);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    public Object queryFunctionForObject(String sql, RowMapper rowMapper) throws JdbcException {
        return queryFunctionForObject(sql, rowMapper);
    }

    public Object queryFunction(final String sqlText, final ResultSetExtractor rse, List<Object> hostVariableList) throws JdbcException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed ");
        }

//        PreparedStatement pStmt = null;
        CallableStatement cstmt_ = null;
        ResultSet rs = null;
        try {
//            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            cstmt_ = this.getPrepareCallFunction(sqlText, hostVariableList);
            cstmt_.registerOutParameter(1, OracleTypes.CURSOR);
//            rs = cstmt_.executeQuery();
            cstmt_.execute();
            rs = (ResultSet) cstmt_.getObject(1);
//            cstmt_ = pStmt.prepareCall(sql);
            return rse.extractData(rs);
        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
            throw new JdbcException(e);
        } finally {
            DatabaseUtils.close(rs, cstmt_);
        }

    }

    public CallableStatement getPrepareCallFunction(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed");
        }

        if (connection == null) {
            throw new IllegalArgumentException("Don't connect to database");
        }

        CallableStatement call = null;
        int hostOffset = 2;

        call = connection.prepareCall(sqlText);

        for (int i = 0; hostVariableList != null && i < hostVariableList.size(); i++) {
            int iType = java.sql.Types.VARCHAR;
            if (hostVariableList.get(i) instanceof Integer || hostVariableList.get(i) instanceof Long) {
                iType = java.sql.Types.NUMERIC;
            } else if (hostVariableList.get(i) instanceof String) {
                iType = java.sql.Types.VARCHAR;
            }
            if (hostVariableList.get(i) == null || hostVariableList.get(i).equals(NULL)) {
                this.setHostVariable(call, hostOffset, hostVariableList.get(i), iType);
            } else {
                this.setHostVariable(call, hostOffset, hostVariableList.get(i));
            }
            hostOffset++;
        }

        return call;
    }

    public int executeUpdateFunction(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed.");
        }
        CallableStatement cstmt = connection.prepareCall(sqlText);
//        PreparedStatement pStmt = null;
        int pStmtReturn = -1;
        try {
            int hostOffset = 2;
            cstmt.registerOutParameter(1, java.sql.Types.NUMERIC);
            for (int i = 0; i < hostVariableList.size(); i++) {
                int iType = java.sql.Types.VARCHAR;
                if (hostVariableList.get(i) instanceof Integer || hostVariableList.get(i) instanceof Long) {
                    iType = java.sql.Types.NUMERIC;
                } else if (hostVariableList.get(i) instanceof String) {
                    iType = java.sql.Types.VARCHAR;
                }
                if (hostVariableList.get(i) == null || hostVariableList.get(i).equals(NULL)) {
                    this.setHostVariable(cstmt, hostOffset, hostVariableList.get(i), iType);
                } else {
                    this.setHostVariable(cstmt, hostOffset, hostVariableList.get(i));
                }
                hostOffset++;
            }
//            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            pStmtReturn = cstmt.executeUpdate();
        } finally {
            DatabaseUtils.close(cstmt);
        }
        return pStmtReturn;
    }

    public String executeUpdateFunction2(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed.");
        }
        CallableStatement cstmt = connection.prepareCall(sqlText);
//        PreparedStatement pStmt = null;
        int pStmtReturn = -1;
        String lReturn = "";
        try {
            int hostOffset = 2;
            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            for (int i = 0; i < hostVariableList.size(); i++) {
                int iType = java.sql.Types.VARCHAR;
                if (hostVariableList.get(i) instanceof Integer || hostVariableList.get(i) instanceof Long) {
                    iType = java.sql.Types.NUMERIC;
                } else if (hostVariableList.get(i) instanceof String) {
                    iType = java.sql.Types.VARCHAR;
                }
                if (hostVariableList.get(i) == null || hostVariableList.get(i).equals(NULL)) {
                    this.setHostVariable(cstmt, hostOffset, hostVariableList.get(i), iType);
                } else {
                    this.setHostVariable(cstmt, hostOffset, hostVariableList.get(i));
                }
                hostOffset++;
            }
//            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            pStmtReturn = cstmt.executeUpdate();
            lReturn = cstmt.getString(1);
        } finally {
            DatabaseUtils.close(cstmt);
        }
        return lReturn;
    }

    public Long executeUpdateFuncRet(String sqlText, List<Object> hostVariableList) throws SQLException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed.");
        }
        CallableStatement cstmt = connection.prepareCall(sqlText);
//        PreparedStatement pStmt = null;
        int pStmtReturn = -1;
        Long lReturn = -1l;
        try {
            int hostOffset = 2;
            cstmt.registerOutParameter(1, java.sql.Types.NUMERIC);
            for (int i = 0; i < hostVariableList.size(); i++) {
                int iType = java.sql.Types.VARCHAR;
                if (hostVariableList.get(i) instanceof Integer || hostVariableList.get(i) instanceof Long) {
                    iType = java.sql.Types.NUMERIC;
                } else if (hostVariableList.get(i) instanceof String) {
                    iType = java.sql.Types.VARCHAR;
                }
                if (hostVariableList.get(i) == null || hostVariableList.get(i).equals(NULL)) {
                    this.setHostVariable(cstmt, hostOffset, hostVariableList.get(i), iType);
                } else {
                    this.setHostVariable(cstmt, hostOffset, hostVariableList.get(i));
                }
                hostOffset++;
            }
//            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            pStmtReturn = cstmt.executeUpdate();
            lReturn = cstmt.getLong(1);
        } finally {
            DatabaseUtils.close(cstmt);
        }
//        return pStmtReturn;
        return lReturn;
    }

    public int queryFunctionForInt(final String sqlText, List<Object> hostVariableList) throws JdbcException {
        if (sqlText == null || sqlText.trim().length() == 0) {
            throw new IllegalArgumentException("Null sqlText not allowed ");
        }

//        PreparedStatement pStmt = null;
        CallableStatement cstmt_ = null;
        ResultSet rs = null;
        try {
//            pStmt = this.getPreparedStatement(sqlText, hostVariableList);
            int hostOffset = 2;

            cstmt_ = this.getPrepareCallFunction(sqlText, hostVariableList);
            cstmt_.registerOutParameter(1, OracleTypes.NUMERIC);
//            rs = cstmt_.executeQuery();
            for (int i = 0; i < hostVariableList.size(); i++) {
                int iType = java.sql.Types.VARCHAR;
                if (hostVariableList.get(i) instanceof Integer || hostVariableList.get(i) instanceof Long) {
                    iType = java.sql.Types.NUMERIC;
                } else if (hostVariableList.get(i) instanceof String) {
                    iType = java.sql.Types.VARCHAR;
                }
                if (hostVariableList.get(i) == null || hostVariableList.get(i).equals(NULL)) {
                    this.setHostVariable(cstmt_, hostOffset, hostVariableList.get(i), iType);
                } else {
                    this.setHostVariable(cstmt_, hostOffset, hostVariableList.get(i));
                }

//                this.setHostVariable(cstmt_, hostOffset, hostVariableList.get(i));
                hostOffset++;
            }
            cstmt_.executeQuery();
//            rs = (ResultSet) cstmtcstmt__.getObject(1);
//            cstmt_ = pStmt.prepareCall(sql);
            int out2 = cstmt_.getInt(1);
            return out2;
        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
            throw new JdbcException(e);
        } finally {
            DatabaseUtils.close(rs, cstmt_);
        }

    }
}
