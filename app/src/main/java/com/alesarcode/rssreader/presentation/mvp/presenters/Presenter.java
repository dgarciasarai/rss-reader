package com.alesarcode.rssreader.presentation.mvp.presenters;

import com.alesarcode.rssreader.presentation.mvp.views.View;

/**
 * Base presenter interface. All presenters must implement this contract.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface Presenter<T extends View> {

    /**
     * Method for initialization the presenter.
     */
    void initialize();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void onViewResume();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void onViewPause();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onStop() method.
     */
    void onViewStop();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void onViewDestroy();

    /**
     * Method that should signal the appropriate view to show the appropriate error with the provided message.
     */
    void onError(String message);

    /**
     * Sets the view linked with this presenter.
     * @param view View for the presenter.
     */
    void setView(T view);
}
