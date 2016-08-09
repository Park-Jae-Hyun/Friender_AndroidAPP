package com.example.jteam.friender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    ImageView[] Pictogram = new ImageView[3];

    int pictogramres[] = {R.mipmap.p01people,R.mipmap.p02food,R.mipmap.p03beer,R.mipmap.p04coffee,
            R.mipmap.p05sports,R.mipmap.p06music,R.mipmap.p07movie,R.mipmap.p08photo,R.mipmap.p09reading,
            R.mipmap.p10concert,R.mipmap.p11festival,R.mipmap.p12travel,R.mipmap.p13rest,R.mipmap.p14tour,
            R.mipmap.p15beach,R.mipmap.p16mountain,R.mipmap.p17owncar,R.mipmap.p18bycicle,
            R.mipmap.p19publictransit,R.mipmap.p20cruise};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_bulletin);

        //intent로 보내온 bulletin객체 받아오기
        Intent intent = getIntent();
        Bulletin bulletin = (Bulletin) intent.getSerializableExtra("bulletin");

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

        Date.setText(bulletin.getDate());
        Destination.setText(bulletin.getDestination());
        Route1.setText(bulletin.getRoute1());
        Route2.setText(bulletin.getRoute2());
        ID.setText(bulletin.getUsername());
        Present.setText(""+bulletin.getJoinednum());
        if(bulletin.getTotalnum()==1)
            Finding.setText("Any");
        else
            Finding.setText(""+(bulletin.getTotalnum()+1));
        Letter.setText(bulletin.getLetter());

        Pictogram[0].setImageResource(pictogramres[bulletin.getCharacter(0)]);
        Pictogram[1].setImageResource(pictogramres[bulletin.getCharacter(1)]);
        Pictogram[2].setImageResource(pictogramres[bulletin.getCharacter(2)]);


    }



    public void join_Onclick(View view) {


    }

}
