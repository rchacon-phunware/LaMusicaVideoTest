package com.lamusica.lamusicavideo.jw;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.lamusica.lamusicavideo.R;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.ads.Ad;
import com.longtailvideo.jwplayer.media.ads.AdBreak;
import com.longtailvideo.jwplayer.media.ads.AdSource;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.LinkedList;

public class JWActivity extends Activity implements VideoPlayerEvents.OnFullscreenListener {
    /**
     * Reference to the JW Player View
     */
    JWPlayerView mPlayerView;

    /**
     * An instance of our event handling class
     */
//    private JWEventHandler mEventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jw);
        mPlayerView = (JWPlayerView)findViewById(R.id.jwplayer);

//        TextView outputTextView = (TextView)findViewById(R.id.output);

        // Handle hiding/showing of ActionBar
        mPlayerView.addOnFullscreenListener(this);

        // Instantiate the JW Player event handler class
//        mEventHandler = new JWEventHandler(mPlayerView, outputTextView);

        // Load a media source
        Uri url = getIntent().getData();
        PlaylistItem pi = new PlaylistItem(url.toString());

        if(getIntent().getBooleanExtra("AD",false)) {
            // Construct a new Ad
            Ad ad = new Ad(AdSource.VAST, "http://www.adotube.com/php/services/player/OMLService.php?avpid=oRYYzvQ&platform_version=vast20&ad_type=linear&groupbypass=1&HTTP_REFERER=http://www.longtailvideo.com&video_identifier=longtailvideo.com,test");

            // Construct a new AdBreak containing the Ad
            // This AdBreak will play a midroll at 10%
            AdBreak adBreak = new AdBreak("100%", ad);
            adBreak.setOffset("pre");

            // Create a new AdSchedule containing the AdBreak we just created
            LinkedList<AdBreak> schedule = new LinkedList<>();
            schedule.add(adBreak);

            pi.setAdSchedule(schedule);
        }

        mPlayerView.load(pi);
        mPlayerView.play();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Set fullscreen when the device is rotated to landscape
        mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        // Let JW Player know that the app has returned from the background
        mPlayerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        // Let JW Player know that the app is going to the background
        mPlayerView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // Let JW Player know that the app is being destroyed
        mPlayerView.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Exit fullscreen when the user pressed the Back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mPlayerView.getFullscreen()) {
                mPlayerView.setFullscreen(false, true);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return false;
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Handles JW Player going to and returning from fullscreen by hiding the ActionBar
     *
     * @param fullscreen true if the player is fullscreen
     */
    @Override
    public void onFullscreen(boolean fullscreen) {
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            if (fullscreen) {
//                actionBar.hide();
//            } else {
//                actionBar.show();
//            }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}