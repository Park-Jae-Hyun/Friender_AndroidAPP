package com.example.jteam.friender;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class DB_Posting extends Activity {
    Button datebutton;
    Spinner numofmembers;
    private String id_EMAIL=null;
    /*EditText destinatoin;
    EditText route1;
    EditText route2;
    EditText Letter;
    CheckBox[] checkBox;
    Button write;
    Button cancel;*/

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

        datebutton = (Button) findViewById(R.id.posting_datebutton);
        numofmembers = (Spinner) findViewById(R.id.posting_num);
       /* destinatoin = (EditText) findViewById(R.id.posting_destinationedit);
        route1 = (EditText)findViewById(R.id.posting_route1);
        route2 = (EditText)findViewById(R.id.posting_route2);
        Letter = (EditText)findViewById(R.id.posting_letter);
        for(int i = 0 ; i< 20; i++)
            checkBox[i] = (CheckBox)findViewById(checkres[i]);
        write = (Button) findViewById(R.id.posting_write);
        cancel = (Button) findViewById(R.id.posting_cancel);*/

        //날짜 선택 초기상태가 현재 날짜이도록 초기화
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DATE);


        Intent intent = getIntent();
        if(intent.getStringExtra("EMAIL")!=null) {
            id_EMAIL=intent.getStringExtra("EMAIL");
        }


        //구하는 사람수를 선택하기 위해 string.xml에 리스트(2,3,4...미리적어둠)를 불러옴
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.number, android.R.layout.simple_spinner_item);
        numofmembers.setAdapter(adapter);

        //date버튼이 눌리면 datepickerdialog 생성
        datebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog
                        (DB_Posting.this, listener, year, month, day);
                dialog.show();
            }
        });
    }

    //datepickerdialog listener 정의
    private DatePickerDialog.OnDateSetListener listener = new
            DatePickerDialog.OnDateSetListener() {
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

}
