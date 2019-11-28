package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.*;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_Setas extends AppCompatActivity {
   // private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__setas);
    }

    public void clicouSetaCima(View view) {
        TextView texto = (TextView) findViewById(R.id.textView7);
        texto.setText("UP");
    }

    public void clicouSetaBaixo(View view) {
        TextView texto = (TextView) findViewById(R.id.textView7);
        texto.setText("DOWN");
    }

    public void clicouSetaEsquerda(View view) {
        TextView texto = (TextView) findViewById(R.id.textView7);
        texto.setText("LEFT");
    }

    public void clicouSetaDireita(View view) {
        TextView texto = (TextView) findViewById(R.id.textView7);
        texto.setText("RIGHT");
        }
}