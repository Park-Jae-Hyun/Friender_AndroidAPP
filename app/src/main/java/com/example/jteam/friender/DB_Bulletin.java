package com.example.jteam.friender;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by flag on 2016-08-05.
 */
public class DB_Bulletin extends AppCompatActivity{
    TextView Date;
    TextView Destination;
    TextView Route1;
    TextView Route2;
    TextView ID;
    TextView Present;
    TextView Finding;
    TextView Join;
    TextView Letter;
    ImageView[] Pictogram;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_db_bulletin);

        Date = (TextView) findViewById(R.id.bulletin_date);
        Destination= (TextView) findViewById(R.id.bulletin_destination);
        Route1= (TextView) findViewById(R.id.bulletin_route1);
        Route2= (TextView) findViewById(R.id.bulletin_route2);
        ID= (TextView) findViewById(R.id.bulletin_id);
        Present= (TextView) findViewById(R.id.bulletin_present);
        Finding= (TextView) findViewById(R.id.bulletin_finding);
        Join= (TextView) findViewById(R.id.bulletin_joinbutton);
        Letter= (TextView) findViewById(R.id.bulletin_letter);
        Pictogram[0] = (ImageView) findViewById(R.id.bulletin_pictogram1);
        Pictogram[1] = (ImageView) findViewById(R.id.bulletin_pictogram2);
        Pictogram[2] = (ImageView) findViewById(R.id.bulletin_pictogram3);


    }


}
