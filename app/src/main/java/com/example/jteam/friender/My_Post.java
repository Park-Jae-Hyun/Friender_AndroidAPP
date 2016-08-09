package com.example.jteam.friender;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Sun on 2016-08-08.
 */
public class My_Post extends AppCompatActivity {

    BoardAdapter Adapter;
    ArrayList<Bulletin> post_bulletin = new ArrayList<Bulletin>();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_board);

        Adapter = new BoardAdapter();
        ListView list = (ListView) findViewById(R.id.listView2);
        list.setAdapter(Adapter);

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("FIND");
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    class BoardAdapter extends BaseAdapter {

        public BoardAdapter()
        {
        }

        @Override
        public int getCount() {
            return post_bulletin.size();
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        //각 게시판리스트에 보여질 뷰 세팅
        public View getView(int position, View convertView, ViewGroup parent) {

            BoardItemView view = new BoardItemView(getApplicationContext());

            view.setBulletin(post_bulletin.get(position));

            return view;
        }
    }
}
