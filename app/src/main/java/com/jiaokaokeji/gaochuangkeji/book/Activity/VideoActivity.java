package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.dl7.player.media.IjkPlayerView;
import com.jiaokaokeji.gaochuangkeji.R;

public class VideoActivity extends AppCompatActivity {
    private  String VIDEO_URL;
    private  String title;
    private  String IMAGE_URL;
    private IjkPlayerView mPlayerView;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mPlayerView = (IjkPlayerView) findViewById(R.id.player_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        title=getIntent().getStringExtra("title");
        IMAGE_URL=getIntent().getStringExtra("image");
        VIDEO_URL=getIntent().getStringExtra("url");
        mToolbar.setTitle("Video Player");
        mToolbar.setSubtitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Glide.with(this).load(IMAGE_URL).fitCenter().into(mPlayerView.mPlayerThumb);
        mPlayerView.init()
                .setTitle("科目二考试记录")

                .setVideoSource(null, VIDEO_URL, null, null, null)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH);



    }
    @Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;}
        return super.onOptionsItemSelected(item);
    }
}
