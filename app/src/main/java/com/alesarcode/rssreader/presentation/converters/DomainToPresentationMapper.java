package com.alesarcode.rssreader.presentation.converters;

import com.alesarcode.rssreader.domain.FeedItem;
import com.alesarcode.rssreader.presentation.model.EntryModel;
import com.alesarcode.rssreader.presentation.model.Model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Mapper class used to transform {@link FeedItem} of the domain layer
 * to {@link EntryModel} of the presentation layer.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class DomainToPresentationMapper {

    /**
     * Transform a {@link FeedItem} into a {@link EntryModel}.
     *
     * @param item Model to be transformed.
     * @return {@link EntryModel}.
     */
    public static EntryModel convert(FeedItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        EntryModel entryModel = new EntryModel(item.getId());
        entryModel.setTitle(item.getTitle());
        entryModel.setImageUrl(item.getImageUrl());

        return entryModel;
    }

    /**
     * Transform a Collection of {@link FeedItem} into a Collection of
     * {@link EntryModel}.
     *
     * @param feedItems Models to be transformed.
     * @return Collection of {@link EntryModel}.
     */
    public static Collection<Model> convertToModelList(Collection<FeedItem> feedItems) {
        Collection<Model> modelCollection = new ArrayList<>();

        for (FeedItem item : feedItems) {
            modelCollection.add(convert(item));
        }

        return modelCollection;
    }
}
