package com.alesarcode.rssreader.presentation.exception;

import android.content.Context;

import com.alesarcode.rssreader.R;

/**
 * Factory for creating translated error messages, depending on the exception;
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class ErrorMessageFactory {

    /**
     * Creates the string representing the error message.
     *
     * @param context   Context used to retrieve string resources.
     * @param exception Exception used as condition.
     * @return {@link String} the error message.
     */
    public static String getMessage(Context context, Exception exception) {
        return context.getString(R.string.default_error_message);
    }
}