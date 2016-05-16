package com.alesarcode.rssreader.data.parser;

import com.alesarcode.rssreader.domain.Feed;
import com.alesarcode.rssreader.domain.FeedItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler for the Feed.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class RSSParserHandler extends DefaultHandler {

    private final StringBuilder mProcessor;

    private Feed mFeed;
    private FeedItem mCurrentItem;

    public RSSParserHandler() {
        this.mProcessor = new StringBuilder();
    }

    public Feed getFeed() {
        return this.mFeed;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("channel")) {
            this.mFeed = new Feed();
        } else if (qName.equalsIgnoreCase("item")
                && this.mFeed != null) {
            this.mCurrentItem = new FeedItem();
            this.mFeed.addItem(this.mCurrentItem);
            this.mCurrentItem.setId(this.mFeed.getEntriesList().size());
        } else if (localName.equalsIgnoreCase("content")
                && qName.equalsIgnoreCase("media:content")
                && attributes.getValue("type").contains("image")) {
            this.mCurrentItem.setImageUrl(attributes.getValue("url"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (mFeed == null) {
            return;
        }

        if (qName.equalsIgnoreCase("item")) {
            this.mCurrentItem = null;
        } else {

            String string = this.mProcessor.toString().trim();

            if (this.mCurrentItem != null) {
                switch (qName.toLowerCase()) {
                    case "title":
                        this.mCurrentItem.setTitle(string);
                        break;
                    case "link":
                        this.mCurrentItem.setLink(string);
                        break;
                    case "media:description":
                        this.mCurrentItem.setDescription(string);
                        break;
                    case "pubdate":
                        this.mCurrentItem.setDate(string);
                        break;
                    default:
                        break;
                }
            } else if (qName.equalsIgnoreCase("title")){
                this.mFeed.setTitle(string);
            }

            this.mProcessor.setLength(0);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.mProcessor.append(ch, start, length);
    }
}
