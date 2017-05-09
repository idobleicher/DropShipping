package com.shipping.ido.dropshoppingproductsearcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class search extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);


        // Hide the action bar on the top
        //ActionBar actionBar = getSupportActionBar();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3680180972942165~8232293539");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        handleCreateRoomButton();
    }

    private void handleCreateRoomButton()
    {
        Button createRoomBtn = (Button) findViewById(R.id.SearchButton);


        createRoomBtn.setOnClickListener(new View.OnClickListener()
        {
            Spinner shoppingOfferSpinner = (Spinner)findViewById(R.id.spinner4);

            String shoppingOffer = shoppingOfferSpinner.getSelectedItem().toString();


            Spinner postTimeSpinner = (Spinner)findViewById(R.id.spinner3);

            String postTime = postTimeSpinner.getSelectedItem().toString();


            Spinner lookForSpeyficWebSiteSPinner = (Spinner)findViewById(R.id.spinner2);

            String specsificWebsite = lookForSpeyficWebSiteSPinner.getSelectedItem().toString();

            Spinner postTypeSpinner = (Spinner)findViewById(R.id.spinner);

            String postType = postTypeSpinner.getSelectedItem().toString();

            EditText postKeyWordsEditText = (EditText)findViewById(R.id.editText3);

            String postKeyWords = postKeyWordsEditText.getText().toString();

            String url = GetTextStr(shoppingOffer,postTime,specsificWebsite,postType,postKeyWords);



            public void onClick(View v)
            {
                Intent searchingIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(url));
                startActivity(searchingIntent);
            }
        });
    }

    private String GetTextStr(String shoppingOffer,String postTime,String website,String type,String postkeywords)
    {
        String base="https://www.facebook.com/search/str/";

        String searchstr = "";
        if (website != "") {
            searchstr = website + '+' + postkeywords;
        } else if (shoppingOffer != ""){
            searchstr = shoppingOffer + '+' + postkeywords;
        } else {
            searchstr = postkeywords;
        }

        base += searchstr;

        if (type == "recent") {
            base += "/stories-keyword/" + postTime + "/date/stories/intersect";
        }
        if (type == "all") {
            base += "/stories-keyword/" + postTime + "/date/stories/intersect";
        }
        if (type == "live") {
            base += "/stories-keyword/intersect/stories-live";
        }
        if (type == "pages") {
            base += "/stories-keyword/stories-publishers";
        }

        if (type == "shared") {
            base += "/stories-keyword/" + postTime + "/date/stories/share/stories/intersect";
        }
        if (type == "photos") {
            base += "/stories-keyword/" + postTime + "/date/stories/photo/stories/intersect";
        }
        if (type == "videos") {
            base += "/stories-keyword/" + postTime + "/date/stories/video/stories/intersect";
        }

        return base;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
