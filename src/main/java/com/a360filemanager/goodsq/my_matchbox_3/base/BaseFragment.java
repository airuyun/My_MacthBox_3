package com.a360filemanager.goodsq.my_matchbox_3.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by goodsq on 2016/8/12.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = getLayout();
        ButterKnife.inject(this,layout);//忘记初始化引起的错误java.lang.RuntimeException: Unable to start activity
        return layout;
    }

    public abstract View getLayout();
}
