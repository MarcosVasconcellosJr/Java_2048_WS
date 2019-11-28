package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import static android.Manifest.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void abreViewSetas(View view){
        Intent setaIntent = new Intent(this, Activity_Setas.class);
        startActivity(setaIntent);
    }
    public void abreViewAudio(View view){
        Intent setaIntent = new Intent(this, ActivityAudio.class);
        startActivity(setaIntent);
    }

    public void abreViewporImages(View view){
        ImageView imagem;
        Intent setaIntent = new Intent(this, activityMovImages.class);
        startActivity(setaIntent);
    }
    public void abreViewAcelerometro(View view){
        Intent setaIntent = new Intent(this, ActivityAcelerometro.class);
        startActivity(setaIntent);
    }



}
