package com.prashant.android.youtubeplayer;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Prashant on 19-05-2015.
 */
public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    public static final String GOOGLE_API_KEY="AIzaSyDXIf1pujX7AfePEooXfbv_sVxPj2XrW5A";
    public static final String VIDEO_ID="QGJuMBdaqIw";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube);
        YouTubePlayerView yplayer = (YouTubePlayerView) findViewById(R.id.youtube_player);
        yplayer.initialize(GOOGLE_API_KEY, this);


    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        //youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        //youTubePlayer.setPlaybackEventListener(playbackEventListener);
        if(!b){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,"Cannot initialize player",Toast.LENGTH_LONG).show();
    }

}
