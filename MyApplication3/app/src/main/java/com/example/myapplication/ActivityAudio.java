package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;

import android.os.Bundle;

import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class ActivityAudio extends AppCompatActivity {
    private final int ID_TEXTO_PARA_VOZ = 100;
    Button btn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        btn = (Button) findViewById(R.id.button);
        textView= (TextView) findViewById(R.id.textView8);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVoz = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                iVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                iVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                iVoz.putExtra(RecognizerIntent.EXTRA_PROMPT, "Diga o seu movimento....");
                try {
                    startActivityForResult(iVoz, ID_TEXTO_PARA_VOZ);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "JOGA ESSA MERDA FORA", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int id, int resultCodeID, Intent dados ){
        super.onActivityResult(id, resultCodeID, dados);
        switch (id){
            case ID_TEXTO_PARA_VOZ:
                if(resultCodeID == RESULT_OK && null != dados){
                    ArrayList<String> result = dados.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String ditado =  result.get(0);
                    Toast.makeText(getApplicationContext(), ditado, Toast.LENGTH_LONG).show();
                    textView.setText(ditado);
                }break;

        }
    }
}


