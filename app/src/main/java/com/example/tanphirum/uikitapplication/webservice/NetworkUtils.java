package com.example.tanphirum.uikitapplication.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    public static String getDataFromServer(String param){

        HttpURLConnection httpURLConnection;

        BufferedReader reader;

        String jsonResponse = null;

        try {
            URL requestUrl = new URL("http://www.mocky.io/v2/5b1ffaf0310000bf2d230aae");

            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();

            StringBuilder builder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            if (builder.length() == 0)
                return null;

            jsonResponse = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
