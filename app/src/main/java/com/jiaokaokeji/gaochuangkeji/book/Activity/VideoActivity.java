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
    private static final String VIDEO_URL = "http://k.youku.com/player/getFlvPath/sid/0486696154553120c7fb3_00/st/flv/fileid/03000201005546382C1E69010DD7BB852DE34F-3B4C-81FA-6447-E7E2278D50CC?K=e2a7a75a27de0ba4261f81d7&sign=3671adc87d987a00c225808af20a1949&ctype=12&ev=1&token=0519&oip=1968735650&ep=ciacHE%2BEUM8F4Sbaiz8bZ3%2FlIiUMXP4J9h%2BFg9JjALshTey5mjbTtJzFSPZCF%2FltBiV0YuP0qaSSaURiYfMxrRsQ10%2FeSPrm%2BvTk5dhUsZICYRFCd82kx1SeRjH4&ymovie=1";
    private static final String VIDEO_HD_URL = "http://k.youku.com/player/getFlvPath/sid/0486696154553120c7fb3_00/st/mp4/fileid/030008010055466AF61E69010DD7BB852DE34F-3B4C-81FA-6447-E7E2278D50CC?K=08ffa3e1373922d2282c1c2e&sign=3671adc87d987a00c225808af20a1949&ctype=12&ev=1&token=0519&oip=1968735650&ep=ciacHE%2BEUM8F4Sbaiz8bZ3%2FlIiUMXP4J9h%2BFidJjALshTey5n0%2BnwZzFSPZCF%2FltBiV0YuP0qaSSaURiYfMxrRsQ10%2FeSPrm%2BvTk5dhUsZICYRFCd82kx1SeRjH4&ymovie=1";
    private static final String IMAGE_URL = "http://vimg2.ws.126.net/image/snapshot/2016/11/I/M/VC62HMUIM.jpg";
    private IjkPlayerView mPlayerView;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mPlayerView = (IjkPlayerView) findViewById(R.id.player_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Video Player");
        mToolbar.setSubtitle("123");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Glide.with(this).load(IMAGE_URL).fitCenter().into(mPlayerView.mPlayerThumb);
        mPlayerView.init()
                .setTitle("科目二考试记录")

                .setVideoSource(null, VIDEO_URL, VIDEO_HD_URL, null, null)
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
