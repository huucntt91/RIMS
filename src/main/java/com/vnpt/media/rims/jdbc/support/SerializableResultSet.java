package com.vnpt.media.rims.jdbc.support;

import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.common.utils.numbers.DataArrayList;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * Implements a serializable version of a java.sql.ResultSet object. This is
 * usefull for returning data to callers on remote machines (e.g. EJB clients,
 * Web services clients, etc.)
 */
public class SerializableResultSet implements ResultSet, Serializable {

	private static final long serialVersionUID = 1L;

	public SerializableResultSet(ResultSet rs) throws SQLException {
		_metaData = new SerializableResultSetMetaData(rs.getMetaData());
		_data = new DataArrayList(_metaData);
		_data.gatherData(rs);
	}

	public SerializableResultSet(ResultSet rs,
			ColumnDefinition[] dataDefinitionOverride) throws SQLException {
		_metaData = new SerializableResultSetMetaData(dataDefinitionOverride);
		_data = new DataArrayList(_metaData);
		_data.gatherData(rs);
	}

	public SerializableResultSet(ColumnDefinition[] dataDefinitionOverride,
			String dataBuffer, String columnDelimiter, String rowDelimiter)
			throws SQLException {
		_metaData = new SerializableResultSetMetaData(dataDefinitionOverride);
		_data = new DataArrayList(_metaData);
		_data.gatherData(dataBuffer, columnDelimiter, rowDelimiter);
	}

	public boolean next() throws SQLException {
		boolean answer = false;
		if (_scrollForwards) {
			_currentRow++;
			if (_currentRow < _data.getNbrRows()) {
				answer = true;
			}
		} else {
			_currentRow--;
			if (_currentRow > -1) {
				answer = true;
			}
		}
		return answer;
	}

	public void close() throws SQLException {
	}

	public boolean wasNull() throws SQLException {
		return _wasNull;
	}

	public String getString(int columnIndex) throws SQLException {
		String value = _data.getString(columnIndex, _currentRow);

		if (value == Constants.NULL_STR)
			_wasNull = true;
		else
			_wasNull = false;

		return value;
	}

	public boolean getBoolean(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBoolean() not yet implemented.");
	}

	public byte getByte(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getByte() not yet implemented.");
	}

	public short getShort(int columnIndex) throws SQLException {
		short value = _data.getShort(columnIndex, _currentRow);

		if (value == Constants.NULL_SHORT)
			_wasNull = true;
		else
			_wasNull = false;

		return value;
	}

	public int getInt(int columnIndex) throws SQLException {
		int value = _data.getInt(columnIndex, _currentRow);

		if (value == Constants.NULL_INT)
			_wasNull = true;
		else
			_wasNull = false;

		return value;
	}

	public long getLong(int columnIndex) throws SQLException {
		int value = _data.getInt(columnIndex, _currentRow);

		if (value == Constants.NULL_INT)
			_wasNull = true;
		else
			_wasNull = false;

		return (long) value;
	}

	public float getFloat(int columnIndex) throws SQLException {
		float value = _data.getFloat(columnIndex, _currentRow);

		if (value == Constants.NULL_FLOAT)
			_wasNull = true;
		else
			_wasNull = false;

		return value;
	}

	public double getDouble(int columnIndex) throws SQLException {
		double value = _data.getDouble(columnIndex, _currentRow);

		if (value == Constants.NULL_DOUBLE)
			_wasNull = true;
		else
			_wasNull = false;

		return value;
	}

	public BigDecimal getBigDecimal(int columnIndex, int scale)
			throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBigDecimal() not yet implemented.");
	}

	public byte[] getBytes(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBytes() not yet implemented.");
	}

	public Date getDate(int columnIndex) throws SQLException {
		java.util.Date value = _data.getDate(columnIndex, _currentRow);

		if (value == Constants.NULL_DATE)
			_wasNull = true;
		else
			_wasNull = false;

		return new Date(value.getTime());
	}

	public Time getTime(int columnIndex) throws SQLException {
		java.util.Date value = _data.getDate(columnIndex, _currentRow);

		if (value == Constants.NULL_DATE)
			_wasNull = true;
		else
			_wasNull = false;

		return new Time(value.getTime());
	}

	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		java.util.Date value = _data.getDate(columnIndex, _currentRow);

		if (value == Constants.NULL_DATE)
			_wasNull = true;
		else
			_wasNull = false;

		return new Timestamp(value.getTime());
	}

	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getAsciiStream() not yet implemented.");
	}

	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getUnicodeStream() not yet implemented.");
	}

	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBinaryStream() not yet implemented.");
	}

	public String getString(String columnName) throws SQLException {
		return this.getString(_metaData.getColumnIndexByName(columnName));
	}

	public boolean getBoolean(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBoolean() not yet implemented.");
	}

	public byte getByte(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getByte() not yet implemented.");
	}

	public short getShort(String columnName) throws SQLException {
		return this.getShort(_metaData.getColumnIndexByName(columnName));
	}

	public int getInt(String columnName) throws SQLException {
		return this.getInt(_metaData.getColumnIndexByName(columnName));
	}

	public long getLong(String columnName) throws SQLException {
		return this.getLong(_metaData.getColumnIndexByName(columnName));
	}

	public float getFloat(String columnName) throws SQLException {
		return this.getFloat(_metaData.getColumnIndexByName(columnName));
	}

	public double getDouble(String columnName) throws SQLException {
		return this.getDouble(_metaData.getColumnIndexByName(columnName));
	}

	public BigDecimal getBigDecimal(String columnName, int scale)
			throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBigDecimal() not yet implemented.");
	}

	public byte[] getBytes(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBytes() not yet implemented.");
	}

	public Date getDate(String columnName) throws SQLException {
		return this.getDate(_metaData.getColumnIndexByName(columnName));
	}

	public Time getTime(String columnName) throws SQLException {
		return this.getTime(_metaData.getColumnIndexByName(columnName));
	}

	public Timestamp getTimestamp(String columnName) throws SQLException {
		return this.getTimestamp(_metaData.getColumnIndexByName(columnName));
	}

	public InputStream getAsciiStream(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getAsciiStream() not yet implemented.");
	}

	public InputStream getUnicodeStream(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getUnicodeStream() not yet implemented.");
	}

	public InputStream getBinaryStream(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBinaryStream() not yet implemented.");
	}

	public SQLWarning getWarnings() throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getWarnings() not yet implemented.");
	}

	public void clearWarnings() throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method clearWarnings() not yet implemented.");
	}

	public String getCursorName() throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getCursorName() not yet implemented.");
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return _metaData;
	}

	public Object getObject(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getObject() not yet implemented.");
	}

	public Object getObject(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getObject() not yet implemented.");
	}

	public int findColumn(String columnName) throws SQLException {
		return _metaData.getColumnIndexByName(columnName);
	}

	public Reader getCharacterStream(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getCharacterStream() not yet implemented.");
	}

	public Reader getCharacterStream(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getCharacterStream() not yet implemented.");
	}

	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBigDecimal() not yet implemented.");
	}

	public BigDecimal getBigDecimal(String columnName) throws SQLException {
		/** @todo: Implement this java.sql.ResultSet method */
		throw new UnsupportedOperationException(
				"Method getBigDecimal() not yet implemented.");
	}

	public boolean isBeforeFirst() throws SQLException {
		boolean answer = false;
		if (_currentRow < 0)
			answer = true;
		return answer;
	}

	public boolean isAfterLast() throws SQLException {
		boolean answer = false;
		if (_currentRow >= _data.getNbrRows())
			answer = true;
		return answer;
	}

	public boolean isFirst() throws SQLException {
		boolean answer = false;
		if (_currentRow == 0)
			answer = true;
		return answer;
	}

	public boolean isLast() throws SQLException {
		boolean answer = false;
		if (_currentRow == _data.getNbrRows() - 1)
			answer = true;
		return answer;
	}

	public void beforeFirst() throws SQLException {
		_currentRow = -1;
	}

	public void afterLast() throws SQLException {
		_currentRow = _data.getNbrRows();
	}

	public boolean first() throws SQLException {
		return (_currentRow == 0);
	}

	public boolean last() throws SQLException {
		return (_currentRow == _data.getNbrRows() - 1);
	}

	public int getRow() throws SQLException {
		return _currentRow + 1;
	}

	public boolean absolute(int row) throws SQLException {
		boolean answer = true;
		if (row > 0) {
			if (row > _data.getNbrRows())
				answer = false;
			_currentRow = row - 1;
		} else {
			int newRow = _data.getNbrRows() + row;
			if (newRow < 0)
				answer = false;
			_currentRow = newRow - 1;
		}

		return answer;
	}

	public boolean relative(int rows) throws SQLException {
		return this.absolute(_currentRow + 1 + rows);
	}

	public boolean previous() throws SQLException {
		boolean answer = true;
		_currentRow--;
		if (_currentRow < 0)
			answer = false;
		return answer;
	}

	public void setFetchDirection(int direction) throws SQLException {
		if (direction == ResultSet.FETCH_FORWARD)
			_scrollForwards = true;
		else
			_scrollForwards = false;
	}

	public int getFetchDirection() throws SQLException {
		int direction = ResultSet.FETCH_REVERSE;
		if (_scrollForwards)
			direction = ResultSet.FETCH_FORWARD;
		return direction;
	}

	public void setFetchSize(int rows) throws SQLException {
		return;
	}

	public int getFetchSize() throws SQLException {
		return 1;
	}

	public int getType() throws SQLException {
		return ResultSet.TYPE_SCROLL_INSENSITIVE;
	}

	public int getConcurrency() throws SQLException {
		return ResultSet.CONCUR_READ_ONLY;
	}

	public boolean rowUpdated() throws SQLException {
		return false;
	}

	public boolean rowInserted() throws SQLException {
		return false;
	}

	public boolean rowDeleted() throws SQLException {
		return false;
	}

	public int getNbrRows() {
		return _data.getNbrRows();
	}

	private SerializableResultSetMetaData _metaData = null;

	private int _currentRow = -1;
	private boolean _wasNull = false;
	private boolean _scrollForwards = true;

	private DataArrayList _data = null;

	@Override
	public int getHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob getNClob(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNString(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNString(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowId getRowId(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowId getRowId(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x,
			long length) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream,
			long length) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader,
			long length) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(int columnIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(String columnLabel, Reader reader)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(int columnIndex, Reader reader, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(String columnLabel, Reader reader, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader,
			long length) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(String columnLabel, NClob nClob)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(int columnIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(String columnLabel, Reader reader)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(int columnIndex, Reader reader, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(String columnLabel, Reader reader, long length)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNString(int columnIndex, String nString)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNString(String columnLabel, String nString)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRowId(int columnIndex, RowId x) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRowId(String columnLabel, RowId x) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelRowUpdates() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRow() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Array getArray(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array getArray(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob getBlob(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob getBlob(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob getClob(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob getClob(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(int arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(int arg0, Map<String, Class<?>> arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(String arg0, Map<String, Class<?>> arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ref getRef(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ref getRef(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement getStatement() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(int arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(String arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(int arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(String arg0, Calendar arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getURL(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getURL(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertRow() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveToCurrentRow() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveToInsertRow() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshRow() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateArray(int arg0, Array arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateArray(String arg0, Array arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBigDecimal(String arg0, BigDecimal arg1)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(int arg0, Blob arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(String arg0, Blob arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBoolean(int arg0, boolean arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBoolean(String arg0, boolean arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateByte(int arg0, byte arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateByte(String arg0, byte arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBytes(int arg0, byte[] arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBytes(String arg0, byte[] arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(int arg0, Clob arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(String arg0, Clob arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDate(int arg0, Date arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDate(String arg0, Date arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDouble(int arg0, double arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDouble(String arg0, double arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFloat(int arg0, float arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFloat(String arg0, float arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInt(int arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInt(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLong(int arg0, long arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLong(String arg0, long arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNull(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNull(String arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObject(int arg0, Object arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObject(String arg0, Object arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObject(int arg0, Object arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObject(String arg0, Object arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRef(int arg0, Ref arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRef(String arg0, Ref arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRow() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateShort(int arg0, short arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateShort(String arg0, short arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateString(int arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateString(String arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTime(int arg0, Time arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTime(String arg0, Time arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTimestamp(int arg0, Timestamp arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTimestamp(String arg0, Timestamp arg1)
			throws SQLException {
		// TODO Auto-generated method stub

	}

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}