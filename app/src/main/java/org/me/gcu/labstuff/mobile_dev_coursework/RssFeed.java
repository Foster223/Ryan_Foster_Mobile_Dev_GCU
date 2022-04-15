package org.me.gcu.labstuff.mobile_dev_coursework;

public class RssFeed {

    // Ryan Andrew Foster
    // (s2136071)

    //Object class for RssFeed
    private String title;
    private String description;
    private String link;
    private String geoPoints;
    private String pubDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGeoPoints() {
        return geoPoints;
    }

    public void setGeoPoints(String geoPoints) {
        this.geoPoints = geoPoints;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    @Override
    public String toString() {
        return "RssFeed [title=" + title + ", description=" + description + ", link="
                + link + ", geoPoints=" + geoPoints + ", pubDate=" + pubDate + "]";
    }
}
