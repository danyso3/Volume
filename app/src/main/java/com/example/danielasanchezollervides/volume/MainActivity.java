package com.example.danielasanchezollervides.volume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.SeekBar;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mpLovers;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mplayer = MediaPlayer.create(this, R.raw.Katy);

        mpLovers = MediaPlayer.create(this, R.raw.Lovers);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

        }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
    });

        mplayer.start();

        final SeekBar scrubber = (SeekBar) findViewById(R.id.scrubber);
        scrubber.setMax(mpLovers.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mpLovers.getCurrentPosition());
            }
        },0,100);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser){
                mpLovers.seekTo(progress);
            }
            @Override
                    public void onStartTrackingTouch(SeekBar seekBar){
        }
            @Override
                    public void onStopTrackingTouch(SeekBar seekBar){

            }
        });
}
public void playAudio(View view){
    mpLovers.start();
}
public void pauseAudio(View view){
mpLovers.pause();
}
}
