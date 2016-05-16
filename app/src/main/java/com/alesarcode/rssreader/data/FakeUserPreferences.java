package com.alesarcode.rssreader.data;

import javax.inject.Inject;

/**
 * Fake preferences which return always the same feed URL.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class FakeUserPreferences implements UserPreferences {

    @Inject
    public FakeUserPreferences() {
    }

    @Override
    public String getFeedUrl() {
        return "http://estaticos.elmundo.es/elmundo/rss/portada.xml";
    }
}
