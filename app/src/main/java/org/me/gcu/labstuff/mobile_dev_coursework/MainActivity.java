package org.me.gcu.labstuff.mobile_dev_coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    // Ryan Andrew Foster
    // (s2136071)

    //setting up the list view
    private ListView listFeed; // used for filtering

    //setting up the search views
    private SearchView roadFilter;
    private SearchView dateFilter;

    //setting up the urls
    private String CIURL = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";
    private String RWURL = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    private String PWRURL = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";

    private Button btnGetCI;
    private Button btnGetRW;
    private Button btnGetPRW;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetCI = findViewById(R.id.btnCI);
        btnGetRW = findViewById(R.id.btnRW);
        btnGetPRW = findViewById(R.id.btnPRW);

        roadFilter = (SearchView)findViewById(R.id.road_Filter); // setting up the search view was not properly used
        dateFilter = (SearchView)findViewById(R.id.date_Filter); // setting up the search view was not properly used

        //Get reference to the ListView
        listFeed = (ListView) findViewById(R.id.listFeed);


        listFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override // this occurs upon clicking on the list item
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                //Builder design pattern in java
                //get object of builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Further Details")
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //not used
                            }
                        }).setNegativeButton("Open On Map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //not used
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });





        btnGetCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setting up async thread
                GetRssFeed getRssFeed = new GetRssFeed();
                getRssFeed.execute(CIURL); //execute the thread via feed URL
            }
        });

        btnGetRW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRssFeed getRssFeed = new GetRssFeed();
                getRssFeed.execute(RWURL);
            }
        });

        btnGetPRW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRssFeed getRssFeed = new GetRssFeed();
                getRssFeed.execute(PWRURL);
            }
        });
    }



    public class GetRssFeed extends AsyncTask<String, Void, RssFeed[]>
    {

        @Override
        protected RssFeed[] doInBackground(String... strings) {
            try {

                URL url= new URL(strings[0]); // converting string to url

                XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();

                FeedHandler handler = new FeedHandler(); // set up handler to work with the reader

                reader.setContentHandler(handler); // setting to handler

                reader.parse(new InputSource(url.openStream())); // inportant for other urls

                return handler.getFeedItems(); //will return an array of feed items
                //will inflate to list view
            }
            catch (Exception e)
            {
                //Not used properly
                RssFeed[] temp = {new RssFeed()};
                return temp;
            }
        }






        @Override
        protected void onPostExecute(RssFeed[] feedItems) { //takes in array of feed items
            super.onPostExecute(feedItems);

            listFeed = findViewById(R.id.listFeed);

            FeedAdapter adapter = new FeedAdapter(MainActivity.this, feedItems);

            listFeed.setAdapter(adapter); // will inflate list to adapter

            //listFeed.setTextFilterEnabled(true); Not used



            //Filters below were not used
            roadFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override// will be called when user types in some text + press enter
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override // this will occur upon any input change
                public boolean onQueryTextChange(String newText) {
                    //need to implement a filter here
                    //adapter is not available until post execute
                    return false;
                }
            });
            dateFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override// will be called when user types in some text + press enter
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override // this will occur upon any input change
                public boolean onQueryTextChange(String newText) {
                    //need to implement a filter here
                    //adapter is not available until post execute

                    return false;
                }
            });

        }
    }

}