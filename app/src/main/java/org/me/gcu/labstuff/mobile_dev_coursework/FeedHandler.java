package org.me.gcu.labstuff.mobile_dev_coursework;

//handler class for parsing the feed

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class FeedHandler extends DefaultHandler {

    // Ryan Andrew Foster
    // (s2136071)

    private boolean isItem, isTitle, isDescription, isLink, isGeoPoint, isPubDate; //know when loking at

    private RssFeed currentItem = new RssFeed();

    // Array list to hold the feed items
    private ArrayList<RssFeed> list = new ArrayList<>();


    // used to return an array of feed items
    public RssFeed[] getFeedItems() {
        RssFeed[] temp = new RssFeed[list.size()];
        list.toArray(temp);
        return temp;
    }


    //setting up a constructor
    public FeedHandler() {

    }

    @Override //start when we get to opening tag
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(localName.equals("item"))
        {
            isItem = true; // if inside item then set to true
        }
        else if(localName.equals("title"))
        {
            isTitle = true; //set isTitle to true
        }
        else if(localName.equals("description"))
        {
            isDescription = true;
        }
        else if(localName.equals("link"))
        {
            isLink = true;
        }
        else if(localName.equals("georss:point"))
        {
            isGeoPoint = true;
        }
        else if(localName.equals("pubDate"))
        {
            isPubDate = true;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if(isItem) // check if in item. Can nest inside item
        {
            if(isTitle)
            {
                currentItem.setTitle(new String(ch, start, length));
            }
            else if(isDescription)
            {
                currentItem.setDescription(new String(ch, start, length));
            }
            else if(isLink)
            {
                currentItem.setLink(new String(ch, start, length));
            }
            else if(isGeoPoint)
            {
                currentItem.setGeoPoints(new String(ch, start, length));
            }else if(isPubDate)
            {
                currentItem.setPubDate(new String(ch, start, length));
            }
        }
    }

    @Override //end = ending tag
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(localName.equals("item"))
        {
            isItem = false;
            list.add(currentItem);
            currentItem = new RssFeed();
        }
        else if(localName.equals("title"))
        {
            isTitle = false;
        }
        else if(localName.equals("description"))
        {
            isDescription = false;
        }
        else if(localName.equals("link"))
        {
            isLink = false;
        }
        else if(localName.equals("georss:point"))
        {
            isGeoPoint = false;
        }
        else if(localName.equals("pubDate"))
        {
            isPubDate = false;
        }
 //when done list is returned to array of fixed size
    }

}

//https://www.youtube.com/watch?v=O4sycDxE3V0