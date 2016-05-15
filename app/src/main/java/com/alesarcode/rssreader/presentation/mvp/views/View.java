package com.alesarcode.rssreader.presentation.mvp.views;

import android.content.Context;

/**
 * Base interface for all views.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface View {

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param message The error message to be displayed.
     */
    void showError(String message);

    /**
     * Get actual {@link Context}.
     *
     * @return actual context.
     */
    Context context();
}
