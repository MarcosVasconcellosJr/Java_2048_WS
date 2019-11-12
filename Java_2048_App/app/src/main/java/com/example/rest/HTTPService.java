package com.example.rest;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void, Void, Comandos> {

    private final String comando;

    HTTPService(String comando) {
        this.comando = comando;
    }

    @Override
    protected Comandos doInBackground(Void... voids) {
        StringBuilder response = new StringBuilder();

        try{
            URL url = new URL("http://orio.serveo.net/Java_2048_WS/webresources/Jogo2048/comando/" + this.comando + "");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()){
                response.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(response.toString(), Comandos.class);
    }
}
