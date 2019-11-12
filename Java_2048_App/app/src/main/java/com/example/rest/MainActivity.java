package com.example.rest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etMove = findViewById(R.id.etMain_direction);
        final TextView tvResponse = findViewById(R.id.etMain_resposta);

        Button btnMove = findViewById(R.id.btnMain_move);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try {
                Comandos retorno = new HTTPService(etMove.getText().toString()).execute().get();
                tvResponse.setText(retorno.toString());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            if (etMove.length() > 0 && etMove.getText().toString().equals("Down")){
                Log.i("Teste","Down");
            }
            if (etMove.length() > 0 && etMove.getText().toString().equals("Left")){
                Log.i("Teste","Left");
            }
            if (etMove.length() > 0 && etMove.getText().toString().equals("Right")){
                Log.i("Teste","Right");
            }
            }
        });
    }
}
