package com.example.jteam.friender;

import android.app.Activity;
import android.app.DatePickerDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_posting);
        //날짜 선택 초기상태가 현재 날짜이도록 초기화
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DATE);
        datebutton = (Button) findViewById(R.id.datebutton);
        numofmembers = (Spinner) findViewById(R.id.numofmembers);


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
                    Button date = (Button) findViewById(R.id.datebutton);
                    date.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
                }
            };

}
