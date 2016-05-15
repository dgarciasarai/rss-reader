package com.alesarcode.rssreader.domain.exceptions;

/**
 * Interface for all {@link java.lang.Exception} wrappers.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface ErrorBundle {

    /**
     * Get the associated exception to this bundle.
     *
     * @return associated exception.
     */
    Exception getException();

    /**
     * Gets the error message associated to this exception-bundle.
     *
     * @return the error message.
     */
    String getErrorMessage();
}
