package com.vnpt.media.rims.exception;
 
public class EmailException extends VnptmediaException {
    private static final long serialVersionUID = 1L;

    public EmailException() {
        super();
    }

    public EmailException(Throwable t) {
        super(t);
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable t) {
        super(message, t);
    }
}
