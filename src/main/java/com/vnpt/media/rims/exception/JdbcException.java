package com.vnpt.media.rims.exception;
 
public class JdbcException extends VnptmediaException {
	private static final long serialVersionUID = 1L;

	public JdbcException() {
		super();
	}

	public JdbcException(Throwable t) {
		super(t);
	}

	public JdbcException(String message) {
		super(message);
	}

	public JdbcException(String message, Throwable t) {
		super(message, t);
	}
}
