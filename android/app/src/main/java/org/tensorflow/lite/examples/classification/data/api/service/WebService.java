package org.tensorflow.lite.examples.classification.data.api.service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebService extends AsyncTask<Void,Void,Void> {

    public String Json;

    public WebService(String json){
        this.Json=json;
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        try {

            URL url = new URL("http://6c19da56.ngrok.io/Java_2048_WS/webresources/insert");
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.i("WEB SERVICE","openConnection");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(Json.toString());
            writer.flush();
            int code = urlConnection.getResponseCode();
            if (code !=  200) {
                throw new IOException("Invalid Request: " + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

}