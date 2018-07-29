package com.example.tecno.mp3player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    static MediaPlayer mp;
    ArrayList<File> myvideos;
    ImageButton love;
    int postion;
    Thread th;
    Uri uri;
    TextView videos_name;
    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        videos_name=(TextView)findViewById(R.id.tv);
        videoView=(VideoView)findViewById(R.id.videoView);


        Intent i=getIntent();
        Bundle b=i.getExtras();
        myvideos=(ArrayList) b.getParcelableArrayList("videoslist");
        videos_name.setText(b.getString("username"));
        postion=b.getInt("pos",0);


        if (mp!=null)
        {
            mp.stop();
            mp.release();
        }
        uri=Uri.parse(myvideos.get(postion).toString());
        mp=MediaPlayer.create(getApplicationContext(),uri);
        mp.stop();


        videoView.setVideoURI(uri);
        videoView.setMediaController(new MediaController(this));
        videoView.start();
        videos_name.setText(myvideos.get(postion).getName().toString().replace(".mp4",""));

    }
}

