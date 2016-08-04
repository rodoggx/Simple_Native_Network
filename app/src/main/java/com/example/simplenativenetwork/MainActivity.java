package com.example.simplenativenetwork;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String doMagic(View view) {
        //1.
        String url = "http://www.mocky.io/v2/57a01bec0f0000c10d0f650f";

        HttpURLConnection urlConnection = null;
        try {
            URL urlFormatted = new URL(url);
            urlConnection = (HttpURLConnection) urlFormatted.openConnection();

            InputStream inputStream =
                    new BufferedInputStream(urlConnection.getInputStream());

            Scanner scanner = new Scanner(inputStream);
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                Log.d(TAG, "doMagic: " + scanner.next());
                stringBuilder.append(scanner.next());
            }
            return stringBuilder.toString();
            //return stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return "";
    }
}
