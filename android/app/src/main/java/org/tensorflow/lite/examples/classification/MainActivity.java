package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import org.tensorflow.lite.examples.classification.presentation.AccelerometerActivity;
import org.tensorflow.lite.examples.classification.presentation.ArrowMovesActivity;
import org.tensorflow.lite.examples.classification.presentation.ClassifierActivity;
import org.tensorflow.lite.examples.classification.presentation.SpeechRecognitionActivity;


public class MainActivity extends AppCompatActivity {

    public String IP = "localhost";
    public boolean IP_BOX_VIEWED = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openArrowMovesActivity(View view){
        Intent setaIntent = new Intent(this, ArrowMovesActivity.class);
        startActivity(setaIntent);
    }

    public void openSpeechRecognitionActivity(View view){
        Intent setaIntent = new Intent(this, SpeechRecognitionActivity.class);
        startActivity(setaIntent);
    }

    public void openClassifierActivity(View view){
        Intent setaIntent = new Intent(this, ClassifierActivity.class);
        startActivity(setaIntent);
    }

    public void openAccelerometerActivity(View view){
        Intent setaIntent = new Intent(this, AccelerometerActivity.class);
        startActivity(setaIntent);
    }
}
