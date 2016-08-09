package com.example.jteam.friender;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Sun on 2016-08-08.
 */
public class information extends AppCompatActivity {
    ImageButton choice1 = (ImageButton) findViewById(R.id.choice1);
    ImageButton choice2 = (ImageButton) findViewById(R.id.choice2);
    ImageButton choice3 = (ImageButton) findViewById(R.id.choice3);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_information);
    }


    public void onButtonClick(View view) {
        switch (view.getId()) {

           }
       }
}

