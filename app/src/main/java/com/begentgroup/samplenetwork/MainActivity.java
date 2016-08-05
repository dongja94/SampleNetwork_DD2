package com.begentgroup.samplenetwork;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = (TextView)findViewById(R.id.text_message);

        Button btn = (Button)findViewById(R.id.btn_google);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyGoogleTask().execute("http://www.google.com");
            }
        });
    }

    class MyGoogleTask extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... strings) {
            String urlText = strings[0];
            try {
                URL url = new URL(urlText);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setConnectTimeout(30000);
                conn.setReadTimeout(10000);
                int code = conn.getResponseCode();
                if (code >= 200 && code < 300) {
                    InputStream is = conn.getInputStream();
                    StringBuilder sb = new StringBuilder();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while((line=br.readLine()) != null) {
                        sb.append(line).append("\r\n");
                    }
                    return sb.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                messageView.setText(s);
            } else {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
