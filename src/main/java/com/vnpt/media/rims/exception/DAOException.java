package com.vnpt.media.rims.exception;
 
public class DAOException extends VnptmediaException {
    private static final long serialVersionUID = 1L;

    public DAOException() {
        super();
    }

    public DAOException(Throwable t) {
        super(t);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable t) {
        super(message, t);
    }
}
