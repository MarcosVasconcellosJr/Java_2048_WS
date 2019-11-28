package org.tensorflow.lite.examples.classification.presentation;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.tensorflow.lite.examples.classification.R;

public class AccelerometerActivity extends Activity implements SensorEventListener
{
    private TextView X, Y, Z, Movimento;//Variables to receive accelerometer values
    private android.hardware.Sensor Sensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        // Create our Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        Sensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        SM.registerListener(this, Sensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Assign TextView
        X = (TextView)findViewById(R.id.xText);
        Y = (TextView)findViewById(R.id.yText);
        Z = (TextView)findViewById(R.id.zText);
        Movimento = (TextView) findViewById(R.id.movtext);
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {
        // Not in use
    }

    @Override
    //Check which move to perform
    public void onSensorChanged(SensorEvent event) {
        X.setText("X: " + event.values[0]);
        Y.setText("Y: " + event.values[1]);
        Z.setText("Z: " + event.values[2]);


        float a = event.values[0];
        float b = event.values[1];
        float c = event.values[2];


        if(a < 0.3 && b < 9.6 && c > -0.2) {
            Movimento.setText("UP");
        }
        if(a < 0 && b < 7 && c < -1){
            Movimento.setText("DOWN");
        }
        if(a > 0.3 && b < 9.6 && c < -0.2){
            Movimento.setText("LEFT");
        }
        if(a < 0.3 && b > 0 && c < -0.2){
            Movimento.setText("RIGTH");
        }

    }

}


