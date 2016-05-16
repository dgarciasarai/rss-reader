package com.alesarcode.rssreader.data.parser;

import com.alesarcode.rssreader.data.UserPreferences;
import com.alesarcode.rssreader.domain.Feed;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.prefs.Preferences;

import javax.inject.Inject;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Parser for XML Feed RSS.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class FeedParser {

    private final UserPreferences mUserPreferences;

    @Inject
    public FeedParser(UserPreferences userPreferences) {
        this.mUserPreferences = userPreferences;
    }

    public Feed getFeed() throws ParserConfigurationException, SAXException, IOException {
        final String url = mUserPreferences.getFeedUrl();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RSSParserHandler handler = new RSSParserHandler();
        saxParser.parse(url, handler);

        return handler.getFeed();
    }
}
