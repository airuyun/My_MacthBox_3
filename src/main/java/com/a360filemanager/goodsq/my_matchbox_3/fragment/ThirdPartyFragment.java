package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a360filemanager.goodsq.my_matchbox_3.QQLogin;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.WeiboLogin;
import com.tencent.connect.common.Constants;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/8.
 */
public class ThirdPartyFragment extends Fragment {

    QQLogin qqLogin;
    WeiboLogin weiboLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.thirdpartyfragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.reset(this);
        qqLogin = new QQLogin(getContext(),getActivity());
        weiboLogin = new WeiboLogin(getContext(),getActivity());
    }


    @OnClick({R.id.iv_wechat, R.id.iv_weibo, R.id.iv_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_wechat:
                break;
            case R.id.iv_weibo:
                weiboLogin.login();
                break;
            case R.id.iv_qq:
                qqLogin.login();
                break;
        }
    }

    //需要拿到QQ授权登录返回的数据
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG","----------------089475");
        if (requestCode == Constants.REQUEST_LOGIN) {//Constants.REQUEST_LOGIN，是腾讯授权登录中返回的需求编码（标识）
            qqLogin.getmTencent().handleLoginData(data,qqLogin.getQqLoginListener());
        }
        if (weiboLogin.getmSsoHandler() != null){
            weiboLogin.getmSsoHandler().authorizeCallBack(requestCode, resultCode, data);
        }

    }
}
