package com.lamusica.lamusicavideo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.lamusica.lamusicavideo.R;
import com.lamusica.lamusicavideo.TestData;
import com.lamusica.lamusicavideo.adapter.VideoPagerAdapter;
import com.lamusica.lamusicavideo.fragment.JwVideoFragment;
import com.lamusica.lamusicavideo.model.Video;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @pablopantaleon on 2/19/16.
 */
public class VideoPagerActivity extends AppCompatActivity {

    @StringDef({ VIDEO_TYPE_EXO, VIDEO_TYPE_JW })
    @Retention(RetentionPolicy.SOURCE)
    public @interface VideoType {}

    public static final String VIDEO_TYPE_EXO = "video.type.exo";
    public static final String VIDEO_TYPE_JW = "video.type.jw";
    private static final String EXTRA_VIDEO_TYPE = "video.type";

    private ViewPager mVideoPager;

    public static void launch(Context context, @VideoType String videoType) {
        final Intent intent = new Intent(context, VideoPagerActivity.class);
        intent.putExtra(EXTRA_VIDEO_TYPE, videoType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exovideo_pager);

        // find views
        mVideoPager = (ViewPager) findViewById(R.id.videoPager);

        // noinspection ResourceType - setup view-pager
        mVideoPager.setAdapter(new VideoPagerAdapter(getSupportFragmentManager(), fetchVideos(),
                getIntent().getStringExtra(EXTRA_VIDEO_TYPE)));
        mVideoPager.setOffscreenPageLimit(3);
//        mVideoPager.setPageTransformer(false, new ParallaxPagerTransformer(R.id.parallax_pager_container));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:"
                + R.id.videoPager + ":" + mVideoPager.getCurrentItem());
        // based on the current position you can then cast the page to the correct
        // class and call the method:
        if (page instanceof JwVideoFragment) {
            return ((JwVideoFragment) page).onKeyDown(keyCode, event);
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * Get dummy data
     */
    private List<Video> fetchVideos() {
        final List<Video> videos = new ArrayList<>();
        final int length = TestData.URL_NAMES.length;

        for (int position = 0; position < length; position++) {
            final Video video = new Video();
            video.name = TestData.URL_NAMES[position];
            video.url = TestData.URL_DATA[position];
            videos.add(video);
        }

        return videos;
    }
}
