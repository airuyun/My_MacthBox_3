package com.a360filemanager.goodsq.my_matchbox_3.callback;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

import com.a360filemanager.goodsq.my_matchbox_3.R;


/**
 * Created by goodsq on 2016/8/8.
 *两个知识点：SurfaceView MediaPlayer
 * SurfaceView 的使用还不是很熟悉，加强
 */
public class MySurfaceViewCallback implements SurfaceHolder.Callback {//回调接口

    Context mContext;
    public MySurfaceViewCallback(Context mContext){
        this.mContext = mContext;
    }

    MediaPlayer mp;//播放视频
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mp = MediaPlayer.create(mContext,R.raw.to_login_art);
        mp.setDisplay(surfaceHolder);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
