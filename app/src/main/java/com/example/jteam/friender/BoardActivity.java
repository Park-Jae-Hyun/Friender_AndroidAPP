//게시판 액티비티
package com.example.jteam.friender;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BoardActivity extends AppCompatActivity {
    CityList CList = new CityList();
    TextView textview;
    BoardAdapter Adapter;
    String id_EMAIL = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_board);

        Intent intent = getIntent();

        if(intent.getStringExtra("EMAIL")!=null) {
            id_EMAIL=intent.getStringExtra("EMAIL");
        }
        //액션바 타이틀 변경
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle((String)CList.getCity_list().get(intent.getFlags()));

       // View itemview = getLayoutInflater().inflate(R.layout.city_item,null);

        //actionbar.setCustomView(itemview);

        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        actionbar.setDisplayHomeAsUpEnabled(true);

        Adapter = new BoardAdapter();
        ListView list = (ListView) findViewById(R.id.listView2);
        list.setAdapter(Adapter);

        // listview options
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setDivider(new ColorDrawable(Color.BLACK));
        list.setDividerHeight(10);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),DB_Bulletin.class);
                if(id_EMAIL!=null) {
                    intent.putExtra("EMAIL",""+id_EMAIL);
                }

                //인텐트에 position정보를 담아 전달
                //intent.setFlags(position);
                startActivity(intent);
            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            finish();
            return true;
        }

        if(id == R.id.Find)
        {
            findcustom(null);
        }
        if(id == R.id.Write)
        {
            Intent intent2 = new Intent(BoardActivity.this,DB_Posting.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

    private void findcustom(final CalendarContract.Reminders reminder)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.find_custom);

        Button findButton = (Button) dialog.findViewById(R.id.custom_button_find);
        Button cancelButton = (Button) dialog.findViewById(R.id.custom_button_cancel);

        findButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
            }

        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }

        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }


    class BoardAdapter extends BaseAdapter {

        public BoardAdapter()
        {
        }

        @Override
        public int getCount() {
            return 10;
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

            return view;
        }
    }

}
