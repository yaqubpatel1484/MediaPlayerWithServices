package com.bitcode.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MediaPlayerService extends Service {
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        mt("On Create");
        super.onCreate();
        mMediaPlayer= new MediaPlayer();
        mMediaPlayer.setVolume(100,100);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mt("On Start Command");

        if (mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
        }

        try {
            mMediaPlayer.setDataSource(intent.getStringExtra("path"));
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("tag","OnDestroy");
        mMediaPlayer.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void mt(String text){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }
}
