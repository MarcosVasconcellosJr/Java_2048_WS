package org.tensorflow.lite.examples.classification.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.controller.MovimentController.MovimentController;

public class ArrowMovesActivity extends AppCompatActivity {

    MovimentController MOVE = new MovimentController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrow_moves);
    }

    public void clickedUp(View view) {
        TextView texto = (TextView) findViewById(R.id.textView7);
        texto.setText("UP");
        MOVE.MovimentVerify("Up");
    }

    public void clickedDown(View view) {
        TextView texto = (TextView) findViewById(R.id.textView7);
        texto.setText("DOWN");
        MOVE.MovimentVerify("Down");
    }

    public void clickedLeft(View view) {
        TextView texto = (TextView) findViewById(R.id.textView7);
        texto.setText("LEFT");
        MOVE.MovimentVerify("Left");
    }

    public void clickedRight(View view) {
        TextView texto = (TextView) findViewById(R.id.textView7);
        texto.setText("RIGHT");
        MOVE.MovimentVerify("Right");
    }
}