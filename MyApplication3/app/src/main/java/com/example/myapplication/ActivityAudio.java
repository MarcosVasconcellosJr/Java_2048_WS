package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.UUID;

public class ActivityAudio extends AppCompatActivity {
    Button btnRecord, btnStopRecord, btnPlay, btnStop;
    String PathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    final int REQUEST_PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        if(!checkPermissionFromDevide()){
            requestPermission();
        }

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnRecord = (Button) findViewById(R.id.btnStartRecord);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStopRecord = (Button) findViewById(R.id.btnStopRecord);


        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissionFromDevide()) {
                PathSave = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + UUID.randomUUID().toString() + "_audio_recorder.3gp";
                setupMediaRecord();
                try {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                btnPlay.setEnabled(false);
                btnStop.setEnabled(false);
                btnStopRecord.setEnabled(true);
                Toast.makeText(ActivityAudio.this, "Recording...", Toast.LENGTH_SHORT).show();
                } else {
                    requestPermission();
                }
                }
        });
        btnStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaRecorder.stop();
                btnStopRecord.setEnabled(false);
                btnPlay.setEnabled(true);
                btnStop.setEnabled(false);
                btnRecord.setEnabled(true);

            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStop.setEnabled(true);
                btnStopRecord.setEnabled(false);
                btnRecord.setEnabled(false);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(PathSave);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                Toast.makeText(ActivityAudio.this, "Playing...", Toast.LENGTH_SHORT).show();

            }

        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStopRecord.setEnabled(false);
                btnStop.setEnabled(false);
                btnRecord.setEnabled(true);
                btnPlay.setEnabled(true);
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    setupMediaRecord();
                }
            }
        });

    }

    private void setupMediaRecord() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(PathSave);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    private boolean checkPermissionFromDevide() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recordAudioResult = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED && recordAudioResult == PackageManager.PERMISSION_GRANTED;
    }

}
