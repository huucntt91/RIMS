package com.vnpt.media.rims.jdbc.support;

import java.io.Serializable;
import java.sql.Types;

/**
 * Represents a column definition for the SerializableResultSet
 */
public class ColumnDefinition implements Serializable {

	private static final long serialVersionUID = 1L;

	public ColumnDefinition() {
	}

	public ColumnDefinition(int type, String name, String label,boolean nullable) {
		this.setDataType(type);
		this.setColumnName(name);
		this.setNullableInd(nullable);
		this.setColumnLabel(label);
	}

	public ColumnDefinition(int type, String name, String label,boolean nullable, int size) {
		this(type, name, label, nullable);
		this.setSize(size);
	}

	public ColumnDefinition(int type, String name, String label,boolean nullable, int precision, int scale) {
		this(type, name, label, nullable);
		this.setPrecision(precision);
		this.setScale(scale);
	}

	public void setDataType(int type) {
		dataType = type;
	}

	public int getDataType() {
		return dataType;
	}

	public void setColumnName(String name) {
		columnName = name;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnLabel(String name) {
		columnLabel = name;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setNullableInd(boolean ind) {
		isNullable = ind;
	}

	public boolean getNullableInd() {
		return isNullable;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setPrecision(int size) {
		precision = size;
		this.setSize(size);
	}

	public int getPrecision() {
		return precision;
	}

	public void setScale(int size) {
		scale = size;
	}

	public int getScale() {
		return scale;
	}

	public String getDataTypeName() {
		String name = "UNKNOWN";
		switch (dataType) {
		case Types.CHAR:
		case Types.VARCHAR: {
			name = "String";
			break;
		}
		}

		return name;
	}

	private static final int NA = -1;

	private int dataType = Types.OTHER;
	private String columnName = null;
	private String columnLabel = null;
	private boolean isNullable = false;
	private int size = NA;
	private int precision = NA;
	private int scale = NA;

}