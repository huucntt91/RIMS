package com.vnpt.media.rims.exception;
 
public class ConnectionException extends VnptmediaException {

    private static final long serialVersionUID = 1L;

    protected ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable t) {
        super(message, t);
    }

    public ConnectionException(Throwable t) {
        super(t);
    }
}
