package com.bitcode.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button mBtnPlay,mBtnStop;
    private EditText mEdtPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }


    private void init(){
        mBtnPlay= findViewById(R.id.btnPlay);
        mBtnStop = findViewById(R.id.btnStop);
        mEdtPath = findViewById(R.id.edtpath);

        mBtnPlay.setOnClickListener(new BtnPlayClickListener());
        mBtnStop.setOnClickListener(new BtnStopClickListener());



    }

    private class BtnPlayClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // Explain to the user why we need to read the contacts
                    }

                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);

                    // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                    // app-defined int constant that should be quite unique

                    return;
                }
            }


            Intent intent = new Intent(MainActivity.this , MediaPlayerService.class);
            intent.putExtra("path",mEdtPath.getText().toString());
            startService(intent);

        }
    }

    private class BtnStopClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,MediaPlayerService.class);
            stopService(intent);
        }
    }
}
