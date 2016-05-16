package com.alesarcode.rssreader.data.parser;

import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;

import com.alesarcode.rssreader.domain.Feed;
import com.alesarcode.rssreader.domain.FeedItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
    private DateFormat formatterPub;
    private DateFormat formatterDc;

    public RSSParserHandler() {
        this.mProcessor = new StringBuilder();
        formatterPub = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
        formatterDc = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
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
                        this.mCurrentItem.setTitle(clean(string));
                        break;
                    case "link":
                        this.mCurrentItem.setLink(string);
                        break;
                    case "description":
                        //Prefer always other descriptions
                        if (this.mCurrentItem.getDescription() == null) {
                            this.mCurrentItem.setDescription(clean(string));
                        }
                        break;
                    case "media:description":
                        this.mCurrentItem.setDescription(clean(string));
                        break;
                    case "dc:date":
                        //Prefer always other dates
                        if (this.mCurrentItem.getDate() == null) {
                            try {
                                this.mCurrentItem.setDate(formatterDc.parse(string));
                            } catch (ParseException e) {
                                this.mCurrentItem.setDate(null);
                            }
                        }
                    case "pubdate":
                        try {
                            this.mCurrentItem.setDate(formatterPub.parse(string));
                        } catch (ParseException e) {
                            this.mCurrentItem.setDate(null);
                        }
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

    @NonNull
    private String clean(String string) {
        return Html.fromHtml(string).toString();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.mProcessor.append(ch, start, length);
    }
}
