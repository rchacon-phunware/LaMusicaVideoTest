package com.lamusica.lamusicavideo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lamusica.lamusicavideo.R;
import com.lamusica.lamusicavideo.TabActivity;

/**
 * Created by @pablopantaleon on 2/19/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                TabActivity.launch(this);
                break;
            case R.id.button2:
                VideoPagerActivity.launch(this, VideoPagerActivity.VIDEO_TYPE_EXO);
                break;
            case R.id.button3:
                VideoPagerActivity.launch(this, VideoPagerActivity.VIDEO_TYPE_JW);
                break;
        }
    }
}
