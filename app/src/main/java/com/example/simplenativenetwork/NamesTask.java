package com.example.simplenativenetwork;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

//1.doinbackground, types of publish progress, type of postexecute
public class NamesTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "MainActivityTAG";

    private TextView mTextView;
    private MainActivity mMainActivity;

    public NamesTask(TextView textView) {
        mTextView = textView;
    }

    public NamesTask(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {

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
                stringBuilder.append(scanner.next());
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
           return "";
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: " + s);
        //mTextView.setText(s);
        mMainActivity.loadData(s);
    }
}
