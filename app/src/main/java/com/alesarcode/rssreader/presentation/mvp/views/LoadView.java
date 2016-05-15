package com.alesarcode.rssreader.presentation.mvp.views;

/**
 * This interface represents a basic view which loads data. All views of this kind should implement
 * these common methods.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface LoadView extends View {

    /**
     * This is a general method used for showing some kind of progress during a background task.
     * For example, this method should show a progress bar and/or disable buttons before some
     * background work starts.
     */
    void showLoading();

    /**
     * This is a general method used for hiding progress information after a background task
     * finishes.
     */
    void hideLoading();
}
