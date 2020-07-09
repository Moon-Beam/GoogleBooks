package com.moonbeam.googlebooks;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

//only has static methods and constants, won't be instantiated -hence a private constructor
public class APIBuilder {
    private APIBuilder(){}

    public static final String BASE_API_URL =
            "https://www.googleapis.com/books/v1/volumes";

    public static URL buildURL(String title) {
        String fullUrl = BASE_API_URL + "/?q=" + title;
        URL url = null;
        try {
            url = new URL(fullUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    //setup API Connection
    public static String getJson(URL url) throws Exception {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {

            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            boolean hasData = scanner.hasNext();

            if (hasData) {
                return scanner.next();
            }
            return null;
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        }finally {
            connection.disconnect();
        }
    }
}
