package com.alesarcode.rssreader.presentation.exception;

import android.content.Context;
import android.util.Log;

import com.alesarcode.rssreader.R;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

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
        Log.d("ErrorMessageFactory", "Creating error for exception: " + exception.getMessage());
        if (exception instanceof ParserConfigurationException ||
                exception instanceof SAXException ||
                exception instanceof IOException) {
            return context.getString(R.string.wrong_feed);
        }
        return context.getString(R.string.default_error_message);
    }
}