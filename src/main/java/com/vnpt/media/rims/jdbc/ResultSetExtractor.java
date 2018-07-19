package com.vnpt.media.rims.jdbc;

import com.vnpt.media.rims.exception.JdbcException;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface ResultSetExtractor {
	
	Object extractData(ResultSet rs) throws SQLException, JdbcException;

}
