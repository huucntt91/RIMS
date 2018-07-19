package com.vnpt.media.rims.exception;


public class ServiceException  extends VnptmediaException {
    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super();
    }

    public ServiceException(Throwable t) {
        super(t);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable t) {
        super(message, t);
    }
}
