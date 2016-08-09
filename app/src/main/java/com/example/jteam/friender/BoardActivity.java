//게시판 액티비티
package com.example.jteam.friender;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
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
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class BoardActivity extends AppCompatActivity {
    CityList CList = new CityList();
    TextView textview;
    BoardAdapter Adapter;
    Button startdateButton;
    Button lastdateButton;
    ArrayList<Bulletin> bulletin = new ArrayList<Bulletin>();

    private int USER_UNIQUE_ID = 0;
    private String city = null;
    private String name = null;
    private String writer = null;
    private String destination = null, sub_route1 = null, sub_route2 = null, text = null;
    private int date = 0, total_friends = 0, joined_friends = 0, character1, character2, character3;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_board);

        Intent intent = getIntent();


        if(intent.getIntExtra("USER_UNIQUE_ID",0)!=0) {
            USER_UNIQUE_ID = intent.getIntExtra("USER_UNIQUE_ID",0);
            name = intent.getStringExtra("NAME");
        }


        //액션바 타이틀 변경
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle((String)CList.getCity_list().get(intent.getFlags()));

       // View itemview = getLayoutInflater().inflate(R.layout.city_item,null);
        //actionbar.setCustomView(itemview);
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        actionbar.setDisplayHomeAsUpEnabled(true);

        //Get travel_info from database
        city = (String)CList.getCity_list().get(intent.getFlags());
        Log.i("city1",""+city);
        BulletinShow B_Show = new BulletinShow();
        B_Show.execute(city);

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

                //인텐트에 bulletin정보를 담아 전달
                intent.putExtra("bulletin",bulletin.get(position));
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
            Intent intent2 = new Intent(BoardActivity.this,BoardPost.class);
            if(USER_UNIQUE_ID!=0) {
                intent2.putExtra("USER_UNIQUE_ID",USER_UNIQUE_ID);
                intent2.putExtra("city",city);
                intent2.putExtra("writer",name);
                //intent2.putExtra("City",);
                Log.i("rightUSER_UNIQUE_ID",""+USER_UNIQUE_ID);
                Log.i("city3",""+city);
            }

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
        startdateButton = (Button) dialog.findViewById(R.id.custom_button_startdate);
        lastdateButton = (Button) dialog.findViewById(R.id.custom_button_lastdate);

        //날짜 선택 초기상태가 현재 날짜이도록 초기화
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DATE);

        //날짜선택버튼 클릭리스너 설정(데이트피커다이얼로그 뜨게)
        startdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog
                        (BoardActivity.this, startdatelistener, year, month, day);
                dialog.show();
            }
        });

        lastdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog
                        (BoardActivity.this, lastdatelistener, year, month, day);
                dialog.show();
            }
        });


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

    private DatePickerDialog.OnDateSetListener startdatelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear++;
            Toast.makeText(getApplicationContext(), year + "/" +
                    monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            //시간이 선택되면 버튼에 그 시간을 표시하게 변경
            startdateButton.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
        }
    };

    private DatePickerDialog.OnDateSetListener lastdatelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear++;
            Toast.makeText(getApplicationContext(), year + "/" +
                    monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            //시간이 선택되면 버튼에 그 시간을 표시하게 변경
            lastdateButton.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
        }
    };


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
            return bulletin.size();
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

            view.setBulletin(bulletin.get(position));
            Log.i("bulletin.get(position)",""+bulletin.get(position));

            return view;
        }
    }

    class BulletinShow extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String city = params[0];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://52.68.212.232/db_travel_bulletin.php");
                String urlParams = "city=" + city;

                Log.i("city2",""+city);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            String data = null;

            try {
                JSONObject json = new JSONObject(s);
                data = json.getString("travel_data");
                JSONArray results = json.getJSONArray("travel_data");

                for( int i = 0; i < results.length(); ++i) {
                    Bulletin temp = new Bulletin();
                    JSONObject dataJObject = results.getJSONObject(i);
                    destination = dataJObject.getString("destination");
                    writer = dataJObject.getString("writer");
                    sub_route1 = dataJObject.getString("sub_route1");
                    sub_route2 = dataJObject.getString("sub_route2");
                    date = dataJObject.getInt("date");
                    total_friends = dataJObject.getInt("total_friends");
                    joined_friends = dataJObject.getInt("joined_friends");
                    character1 = dataJObject.getInt("character1");
                    character2 = dataJObject.getInt("character2");
                    character3 = dataJObject.getInt("character3");
                    text = dataJObject.getString("text");

                    temp.setAllcomponents(destination, writer, sub_route1, sub_route2, date,
                            total_friends, joined_friends, character1, character2, character3,text);

                    bulletin.add(temp);
                    
                    Log.i("UNIQUE_UNIQUE_ID", "" + USER_UNIQUE_ID);
                    Log.i("writer", "" + name);
                    Log.i("destination", "" + destination);
                    Log.i("sub_route1", "" + sub_route1);
                    Log.i("sub_route2", "" + sub_route2);
                    Log.i("date", "" + date);
                    Log.i("total_friends", "" + total_friends);
                    Log.i("joined_friends", "" + joined_friends);
                    Log.i("character1", "" + character1);
                    Log.i("character2", "" + character2);
                    Log.i("character3", "" + character3);
                    Log.i("text",""+text);
                    Log.i("-----------", "----------\n");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
