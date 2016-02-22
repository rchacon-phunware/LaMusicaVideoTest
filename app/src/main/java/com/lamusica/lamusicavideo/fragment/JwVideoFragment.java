package com.lamusica.lamusicavideo.fragment;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lamusica.lamusicavideo.R;
import com.lamusica.lamusicavideo.model.Video;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.ads.Ad;
import com.longtailvideo.jwplayer.media.ads.AdBreak;
import com.longtailvideo.jwplayer.media.ads.AdSource;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.LinkedList;

/**
 * Created by @pablopantaleon on 2/22/16.
 */
public class JwVideoFragment extends Fragment implements VideoPlayerEvents.OnFullscreenListener {

    private static final String ARG_VIDEO = "arg.video";

    private Video mVideo;
    private JWPlayerView mPlayerView;

    public static JwVideoFragment newInstance(Video video) {
        final JwVideoFragment fragment = new JwVideoFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_VIDEO, video);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_jw, container, false);
        Log.d("PlayerType", "JwPlayer");

        mVideo = getArguments().getParcelable(ARG_VIDEO);
        mPlayerView = (JWPlayerView) root.findViewById(R.id.jwplayer);

        // Handle hiding/showing of ActionBar
        mPlayerView.addOnFullscreenListener(this);

        // Load a media source
        final Uri url = Uri.parse(mVideo.url);
        PlaylistItem pi = new PlaylistItem(url.toString());

        if(mVideo.showAd) {
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

        return root;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Set fullscreen when the device is rotated to landscape
        mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        // Let JW Player know that the app has returned from the background
        mPlayerView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        // Let JW Player know that the app is going to the background
        mPlayerView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Let JW Player know that the app is being destroyed
        mPlayerView.onDestroy();
        super.onDestroy();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mPlayerView.getFullscreen()) {
                mPlayerView.setFullscreen(false, true);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return false;
            } else {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        }

        return false;
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
}
