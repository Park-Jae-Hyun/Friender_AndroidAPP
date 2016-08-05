package com.example.jteam.friender;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DB_Login extends Activity{

    private String id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id = intent.getStringExtra("main_id");
        password =intent.getStringExtra("main_password");
        LoginCheck login_check = new LoginCheck();
        login_check.execute(id, password);
    }

    class LoginCheck extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String user_password = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://52.68.212.232/db_login.php");
                String urlParams = "id=" + user_id + "&password=" + user_password;

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
            String ID = null, F_NAME = null, L_NAME = null, EMAIL = null, BIRTH = null, MOBILE_NUMBER = null;
            String err = null;
            String data = null;

            //Log.i("json", "First EMAIL : " + EMAIL);
            try {
                JSONObject json = new JSONObject(s);
                data = json.getString("user_data");
                JSONObject dataJObject = json.getJSONObject("user_data");
                ID = dataJObject.getString("id");
                F_NAME = dataJObject.getString("f_name");
                L_NAME = dataJObject.getString("l_name");
                EMAIL = dataJObject.getString("email");
                BIRTH = dataJObject.getString("birth");
                MOBILE_NUMBER = dataJObject.getString("mobile_number");

                Intent intent = new Intent(DB_Login.this, MainActivity.class );
                intent.putExtra("F_NAME", F_NAME);
                intent.putExtra("L_NAME", L_NAME);
                intent.putExtra("EMAIL", EMAIL);
                intent.putExtra("BIRTH", BIRTH);
                intent.putExtra("MOBILE_NUMBER", MOBILE_NUMBER);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
                Toast.makeText(getApplicationContext(), "" + err, Toast.LENGTH_LONG).show();
            }
        }
    }
}