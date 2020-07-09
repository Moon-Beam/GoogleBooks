package com.moonbeam.googlebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);


        try {
            URL bookUrl = APIBuilder.buildURL("cooking");
            new BookQueryTask().execute(bookUrl);
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    public class BookQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String output = null;

            try {
                output = APIBuilder.getJson(searchURL);
            }catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return output;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView = findViewById(R.id.apiText);
            textView.setText(result);
        }
    }
}