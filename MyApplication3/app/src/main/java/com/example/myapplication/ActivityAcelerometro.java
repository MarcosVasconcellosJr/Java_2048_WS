package com.example.myapplication;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class ActivityAcelerometro extends Activity implements SensorEventListener
    {
        private TextView X, Y, Z, Movimento;//Variables to receive accelerometer values
        private Sensor Sensor;
        private SensorManager SM;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);

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
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
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
        if(a < 0.3 && b < 9.6 && c > -0.2){
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

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    }


