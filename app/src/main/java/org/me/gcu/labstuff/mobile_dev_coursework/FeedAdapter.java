package org.me.gcu.labstuff.mobile_dev_coursework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class FeedAdapter extends BaseAdapter {

    // Ryan Andrew Foster
    // (s2136071)

    private LayoutInflater feedInflater;

    private RssFeed[] feedItems;


   // private Filter customFilter; // was to be used for custom filter


    public FeedAdapter(Context c, RssFeed[] feedItems) {
        this.feedItems = feedItems;
        this.feedInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //required methods
    @Override//how many things show in list
    public int getCount() {
        return feedItems.length;
    }

    @Override
    public Object getItem(int i) {
        return feedItems[1];
    }

    @Override
    public long getItemId(int i) {
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View list = feedInflater.inflate(R.layout.custom_list_view, null);

        // setting up the textViews to display the feed in
        TextView txtTitle = list.findViewById(R.id.titleTextView);
        TextView txtDescription = list.findViewById(R.id.descTextView);
        TextView txtLink = list.findViewById(R.id.linkTextView);
        TextView txtPubDate = list.findViewById(R.id.pubDateTextView);
        TextView txtGeoPoint = list.findViewById(R.id.geoPointTextView);

        // Display the feed in the list view
        txtTitle.setText(feedItems[i].getTitle());
        txtDescription.setText(feedItems[i].getDescription());
        txtLink.setText(feedItems[i].getLink());
        txtPubDate.setText(feedItems[i].getPubDate());
        txtGeoPoint.setText(feedItems[i].getGeoPoints());

        // return the list view
        return list;

    }


}

