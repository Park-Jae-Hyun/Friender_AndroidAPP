package com.example.jteam.friender;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class BoardPost extends Activity {
    private Button datebutton;
    private Spinner numofmembers;
    private Spinner myfriend;

    private String USER_UNIQUE_ID=null;
    private EditText editDestination;
    private EditText editRoute1;
    private EditText editRoute2;
    private EditText editLetter;
    private CheckBox[] checkBox = new CheckBox[20];
    private Button buttonWrite;
    private Button buttonCancel;

    //check box resource 배열
    int[] checkres = {R.id.posting_check1, R.id.posting_check2, R.id.posting_check3,
            R.id.posting_check4,R.id.posting_check5,R.id.posting_check6,R.id.posting_check7,
            R.id.posting_check8,R.id.posting_check9,R.id.posting_check10,R.id.posting_check11,
            R.id.posting_check12,R.id.posting_check13,R.id.posting_check14,R.id.posting_check15,
            R.id.posting_check16,R.id.posting_check17,R.id.posting_check18,R.id.posting_check19,
            R.id.posting_check20};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_posting);

        // GET ID FROM BOARDACTIVITY TO GET TRABLE_INFO FROM DATABASE, USER_UNIQUE_ID is FOREIGNER
        Intent intent = getIntent();
        if(intent.getStringExtra("USER_UNIQUE_ID")!=null) {
            USER_UNIQUE_ID=intent.getStringExtra("USER_UNIQUE_ID");
            Log.i("USER_UNIQUE_ID",USER_UNIQUE_ID);
        }

        datebutton = (Button) findViewById(R.id.posting_datebutton);
        numofmembers = (Spinner) findViewById(R.id.posting_total_num);
        myfriend = (Spinner) findViewById(R.id.posting_myfriends_num);

        editDestination = (EditText) findViewById(R.id.posting_destinationedit);
        editRoute1 = (EditText)findViewById(R.id.posting_route1);
        editRoute2 = (EditText)findViewById(R.id.posting_route2);
        editLetter = (EditText)findViewById(R.id.posting_letter);
        for(int i = 0; i < 20; i++) {
            checkBox[i] = (CheckBox)findViewById(checkres[i]);
        }

        buttonWrite = (Button) findViewById(R.id.posting_write);
        buttonCancel = (Button) findViewById(R.id.posting_cancel);

        String destination = editDestination.getText().toString();
        String route1 = editRoute1.getText().toString();
        String route2 = editRoute2.getText().toString();
        String letter = editLetter.getText().toString();
        Integer[] checkbox = new Integer[20];
        for(int i = 0; i < 20; i++) {
            //checkbox[i] = checkBox[i].getInE;
        }

        //날짜 선택 초기상태가 현재 날짜이도록 초기화
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DATE);

        //구하는 사람수를 선택하기 위해 string.xml에 리스트(2,3,4...미리적어둠)를 불러옴
        ArrayAdapter totalnumadapter = ArrayAdapter.createFromResource(this,
                R.array.totalnumbers, android.R.layout.simple_spinner_item);
        numofmembers.setAdapter(totalnumadapter);
        ArrayAdapter joinednumadapter = ArrayAdapter.createFromResource(this,
                R.array.joinednumbers, android.R.layout.simple_spinner_item);
        myfriend.setAdapter(joinednumadapter);

        //전체 사람수 선택 스피너 리스너
        numofmembers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //현재사람수 선택 스피너 리스너
        myfriend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //date버튼이 눌리면 datepickerdialog 생성
        datebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog
                        (BoardPost.this, listener, year, month, day);
                dialog.show();
            }
        });
    }

    //datepickerdialog listener 정의
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    monthOfYear++;
                    Toast.makeText(getApplicationContext(), year + "/" +
                            monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
                    Button date = (Button) findViewById(R.id.posting_datebutton);
                    date.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
                }
    };

    class PostOnBoard extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            int user_u_id = Integer.parseInt(params[0]);
            String destination = params[1];
            String route1 = params[2];
            String route2 = params[3];
            String letter = params[4];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://52.68.212.232/db_post.php");
                String urlParams = "user_u_id="+user_u_id+"&destination="
                                 ;

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

            finish();
        }
    }

}
