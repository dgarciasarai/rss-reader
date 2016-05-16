package com.alesarcode.rssreader.data;

/**
 * This interface should be implemented by the class which gets/sets
 * user preferences.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public interface UserPreferences {

    /**
     * Gets the feed url selected by the user.
     *
     * @return Url to be parsed.
     */
    String getFeedUrl();
}
