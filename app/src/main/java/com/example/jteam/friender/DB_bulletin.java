package com.example.jteam.friender;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DB_bulletin extends AppCompatActivity {

    String url = "http://52.68.212.232:80/db_login.php";/////////////check
    TextView tv;

    public GettingPHP gPHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_login);

        gPHP = new GettingPHP();
        tv = (TextView)findViewById(R.id.email);/////////////////check

        gPHP.execute(url);
    }

    class GettingPHP extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();

                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                        while (true) {
                            String line = br.readLine();
                            if (line == null)
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected  void onPostExecute(String str) {
            try {
                //PHP에서 받아온 JSON 데이터를 JSON 오브젝트로 변환
                JSONObject jObject = new JSONObject(str);
                //results라는 key는 JSON배열로 되어있다.
                JSONArray results = jObject.getJSONArray("results");
                String info = "";
                info += "Status : " + jObject.get("status");
                info += "\n";
                info += "Number of results : " + jObject.get("num_result");
                info += "\n";
                info += "Results : \n";

                for (int i = 0; i < results.length(); ++i) {
                    JSONObject temp = results.getJSONObject(i);
                    info += "\tdoc_idx : " + temp.get("doc_idx");
                    info += "\tmember_idx : " + temp.get("member_idx");
                    info += "\tsubject : " + temp.get("subject");
                    info += "\tcontent : " + temp.get("content");
                    info += "\treg_date : " + temp.get("reg_date");
                    info += "\n\t--------------------------------------------\n";
                }
                tv.setText(info);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
