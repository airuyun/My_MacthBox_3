package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.WelcomeDialog;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.MyViewPagerAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.callback.MySurfaceViewCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
/**
* 绝不修改版╱╲ <12> ╱╲
* */
public class MainActivity extends BaseActvity {

    int index = 0;

    @InjectView(R.id.main_sv)
    SurfaceView sv;
    @InjectView(R.id.main_vp)
    ViewPager vp;
    @InjectView(R.id.main_points)
    LinearLayout points;

    @Override
    public int getLayout() {
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        WelcomeDialog dialog = new WelcomeDialog(this);//显示欢迎页
        dialog.show();
        MySurfaceViewCallback callback = new MySurfaceViewCallback(this);
        sv.getHolder().addCallback(callback);//显示SurfaceView绑定的内容
        points.getChildAt(index).setSelected(true);
        vp.setAdapter(new MyViewPagerAdapter(this));
        vp.setOnPageChangeListener(pageChangeListener);
    }

    /*============================================================================================*/
    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {//选中当前点，取消上一个点的选中状态
            points.getChildAt(index).setSelected(false);
            points.getChildAt(position % 5).setSelected(true);
            index = position % 5;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /*============================================================================================*/
    @OnClick({R.id.main_tv_login, R.id.main_tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tv_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.main_tv_register:
                startActivity(new Intent(this,RegisterActiviry.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
