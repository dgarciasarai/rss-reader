package com.alesarcode.rssreader.domain.exceptions;

/**
 * Base wrapper for exceptions.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class DefaultErrorBundle implements ErrorBundle {

    private static final String DEFAULT_MESSAGE = "Unknown error";
    private final Exception mException;

    public DefaultErrorBundle(Exception exception) {
        this.mException = exception;
    }

    @Override
    public Exception getException() {
        return mException;
    }

    @Override
    public String getErrorMessage() {
        return (mException != null) ? this.mException.getMessage() : DEFAULT_MESSAGE;
    }
}