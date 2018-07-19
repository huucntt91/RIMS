package com.vnpt.media.rims.exception;

import com.vnpt.media.rims.common.utils.StringUtils;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;


public class VnptmediaException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String LINE_FEED = System.getProperty("line.separator");
    private String baseThrowableStackTrace = null;
    private transient Throwable baseThrowable = null;

    protected VnptmediaException() {
    }

    protected VnptmediaException(String message) {
        super(message);
    }

    protected VnptmediaException(String message, Throwable t) {
        super(message);

        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        } else {
            baseThrowableStackTrace = StringUtils.captureStackTrace(t);
            baseThrowable = t;
        }
    }

    protected VnptmediaException(Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        } else {
            baseThrowableStackTrace = StringUtils.captureStackTrace(t);
            baseThrowable = t;
        }
    }


    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);

        if (baseThrowableStackTrace != null) {
            s.print(LINE_FEED);
            s.print("Underlying Throwable stack trace follows:");
            s.print(LINE_FEED);
            s.print(baseThrowableStackTrace);
        }
    }


    public void printStackTrace() {
        this.printStackTrace(System.err);
    }


    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);

        if (baseThrowableStackTrace != null) {
            s.print(LINE_FEED);
            s.print("Underlying Throwable stack trace follows:");
            s.print(LINE_FEED);
            s.print(baseThrowableStackTrace);
        }
    }

    public Throwable getBaseThrowable() {
        return baseThrowable;
    }
}
