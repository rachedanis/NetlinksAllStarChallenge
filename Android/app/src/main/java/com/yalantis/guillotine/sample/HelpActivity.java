package com.yalantis.guillotine.sample;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.yalantis.guillotine.sample.activity.MainActivity;
import com.yalantis.guillotine.sample.widget.CustomVideoView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        final Context _context = this;
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.help);

        VideoView VideoView = (VideoView) findViewById(R.id.videoView);
        VideoView.setVideoURI(uri);
        VideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent i = new Intent(_context, MainActivity.class);
                startActivity(i);
            }
        });

//        cVideoView.setMediaController(new MediaController(this));

        VideoView.start();

    }
}
