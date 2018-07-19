package com.vnpt.media.rims.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *         <p>
 *         Useful for the typical case of one object per row in the database
 *         table. The number of entries in the results list will match the
 *         number of rows.
 * 
 *         RowMapper rowMapper = new UserRowMapper(); // reusable object
 * 
 *         List allUsers = (List) jdbcTemplate.query( "select * from user", new
 *         RowMapperResultSetExtractor(rowMapper, 10));
 * 
 *         User user = (User) jdbcTemplate.queryForObject(
 *         "select * from user where id=?", new Object[] {id}, new
 *         RowMapperResultSetExtractor(rowMapper, 1));</pre>
 */
public class RowMapperResultSetExtractor implements ResultSetExtractor {

	private final RowMapper rowMapper;

	private final int rowsExpected;

	/**
	 * Create a new RowMapperResultSetExtractor.
	 * 
	 * @param rowMapper
	 *            the RowMapper which creates an object for each row
	 */
	public RowMapperResultSetExtractor(RowMapper rowMapper) {
		this(rowMapper, 0);
	}

	/**
	 * Create a new RowMapperResultSetExtractor.
	 * 
	 * @param rowMapper
	 *            the RowMapper which creates an object for each row
	 * @param rowsExpected
	 *            the number of expected rows (just used for optimized
	 *            collection handling)
	 */
	public RowMapperResultSetExtractor(RowMapper rowMapper, int rowsExpected) {
		this.rowMapper = rowMapper;
		this.rowsExpected = rowsExpected;
	}

	public Object extractData(ResultSet rs) throws SQLException {
		List<Object> results = (this.rowsExpected > 0 ? new ArrayList<Object>(this.rowsExpected) : new ArrayList<Object>());
		int rowNum = 0;
		while (rs.next()) {
			results.add(this.rowMapper.mapRow(rs, rowNum++));
		}
		return results;
	}

}
