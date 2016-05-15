package com.alesarcode.rssreader.presentation.mvp.presenters;

import android.content.Context;

import com.alesarcode.rssreader.domain.exceptions.DefaultErrorBundle;
import com.alesarcode.rssreader.domain.exceptions.ErrorBundle;
import com.alesarcode.rssreader.presentation.exception.ErrorMessageFactory;
import com.alesarcode.rssreader.presentation.mvp.views.View;

/**
 * Base class for presenters. Implements all methods from {@link Presenter} interface
 * to allow developers to only implement those which will be used.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class BasePresenter<T extends View> implements Presenter<T> {

    protected T mView;

    @Override
    public void initialize() {
        //no-op by default
    }

    @Override
    public void onViewResume() {
        //no-op by default
    }

    @Override
    public void onViewPause() {
        //no-op by default
    }

    @Override
    public void onViewStop() {
        //no-op by default
    }

    @Override
    public void onViewDestroy() {
        //no-op by default
    }

    @Override
    public void onError(String message) {
        //no-op by default
    }

    @Override
    public void setView(T view) {
        this.mView = view;
    }

    /**
     * Show the error ocurred in the context.
     *
     * @param errorBundle Exception wrapper.
     */
    public void showError(ErrorBundle errorBundle) {
        String message = ErrorMessageFactory.getMessage(this.mView.context(), errorBundle.getException());
        this.mView.showError(message);
    }

    /**
     * Gets the context from the attached view.
     *
     * @return View context.
     */
    protected Context context() {
        return this.mView.context();
    }
}
