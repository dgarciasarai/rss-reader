package com.alesarcode.rssreader.presentation.mvp.views;

import com.alesarcode.rssreader.presentation.model.Model;

import java.util.Collection;

/**
 * Interface for views that display a list view.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface ListDataView extends LoadView {

    /**
     * Adds a list of {@link Model} to this view.
     *
     * @param models List of {@link Model}.
     */
    <T extends Model> void add(Collection<T> models);
}
