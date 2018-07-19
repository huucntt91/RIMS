package com.vnpt.media.rims.connection;

import java.io.Serializable;

public final class JdbcConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    private String driverName = null;
    private String url = null;
    private String userName = null;
    private String password = null;
    private String poolName = null;
    private long connectionTimeout = 0;
    private int maxActive = 0;
    private int minActive = 0;
    private int idleMaxAge = 0;
    private int idleConnectionTestPeriod = 0;
    private int partitionCount = 1;
    private String initSQL = null;
    private int acquireRetryDelay = 0;
    private int acquireRetryAttempts = 5;
    private int queryExecuteTimeLimit = 0;
    private boolean encrypt = false;
    private String nameFile = null;
    private String connectionTestStatement = null;
    private int typeConnec = 0;
    private int maxIdle = 0;
    private int minIdle = 0;
    private String databaseRef = null;
    private String maxStatements = null;
    private String timeBetweenEvictionRunsMillis = null;
    private String minEvictableIdleTimeMillis  = null;

    public JdbcConfig() {
    }

    public JdbcConfig(String driverName, String connectString,
                      String dbUserID, String password) {
        this.setDriverName(driverName);
        this.setUrl(connectString);
        this.setUserName(dbUserID);
        this.setPassword(password);
    }

    public String getMaxStatements() {
        return maxStatements;
    }

    public void setMaxStatements(String maxStatements) {
        this.maxStatements = maxStatements;
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public String getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getDatabaseRef() {
        return databaseRef;
    }

    public void setDatabaseRef(String databaseRef) {
        this.databaseRef = databaseRef;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getTypeConnec() {
        return typeConnec;
    }

    public void setTypeConnec(int typeConnec) {
        this.typeConnec = typeConnec;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Null driverName not allowed.");
        }
        driverName = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url == null || url.trim().length() == 0) {
            throw new IllegalArgumentException("Null connectString not allowed.");
        }
        this.url = url;
    }

    public int getAcquireRetryAttempts() {
        return acquireRetryAttempts;
    }

    public void setAcquireRetryAttempts(int acquireRetryAttempts) {
        this.acquireRetryAttempts = acquireRetryAttempts;
    }

    public int getAcquireRetryDelay() {
        return acquireRetryDelay;
    }

    public void setAcquireRetryDelay(int acquireRetryDelay) {
        this.acquireRetryDelay = acquireRetryDelay;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getIdleConnectionTestPeriod() {
        return idleConnectionTestPeriod;
    }

    public void setIdleConnectionTestPeriod(int idleConnectionTestPeriod) {
        this.idleConnectionTestPeriod = idleConnectionTestPeriod;
    }

    public int getIdleMaxAge() {
        return idleMaxAge;
    }

    public void setIdleMaxAge(int idleMaxAge) {
        this.idleMaxAge = idleMaxAge;
    }

    public String getInitSQL() {
        return initSQL;
    }

    public void setInitSQL(String initSQL) {
        this.initSQL = initSQL;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinActive() {
        return minActive;
    }

    public void setMinActive(int minActive) {
        this.minActive = minActive;
    }

    public int getPartitionCount() {
        return partitionCount;
    }

    public void setPartitionCount(int partitionCount) {
        this.partitionCount = partitionCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public int getQueryExecuteTimeLimit() {
        return queryExecuteTimeLimit;
    }

    public void setQueryExecuteTimeLimit(int queryExecuteTimeLimit) {
        this.queryExecuteTimeLimit = queryExecuteTimeLimit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getConnectionTestStatement() {
        return connectionTestStatement;
    }

    public void setConnectionTestStatement(String connectionTestStatement) {
        this.connectionTestStatement = connectionTestStatement;
    }
}
