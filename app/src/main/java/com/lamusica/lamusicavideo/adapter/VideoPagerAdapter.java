package com.lamusica.lamusicavideo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lamusica.lamusicavideo.activity.VideoPagerActivity;
import com.lamusica.lamusicavideo.fragment.ExoVideoFragment;
import com.lamusica.lamusicavideo.model.Video;

import java.util.List;

/**
 * Created by @pablopantaleon on 2/19/16.
 */
public class VideoPagerAdapter extends FragmentStatePagerAdapter {

    // TODO delete this value later
    private @VideoPagerActivity.VideoType String mVideoType;
    private List<Video> mVideos;

    public VideoPagerAdapter(FragmentManager fm, List<Video> videos, @VideoPagerActivity.VideoType String type) {
        super(fm);
        this.mVideos = videos;
        this.mVideoType = type;
    }

    @Override
    public Fragment getItem(int position) {
        // TODO remove if condition
        if (mVideoType.equals(VideoPagerActivity.VIDEO_TYPE_EXO)) {
            return ExoVideoFragment.newInstance(mVideos.get(position));
        } else {
            return ExoVideoFragment.newInstance(mVideos.get(position));
        }
    }

    @Override
    public int getCount() {
        return mVideos.size();
    }
}
