package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.PopupAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PopupBean;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/6.
 */
public class ShowHeadPopuoWindow extends PopupWindow {
    ListView lv;

    public ShowHeadPopuoWindow(Context context, List<PopupBean> list) {
        super(context);
        setFocusable(true);//获取焦点？
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.colorArmyGreen)));//设置背景颜色
        setWidth(context.getResources().getDisplayMetrics().widthPixels);
        setHeight(context.getResources().getDisplayMetrics().heightPixels / 2);
        setOutsideTouchable(true);
        lv = new ListView(context);
        lv.setAdapter(new PopupAdapter(context, list));
        setContentView(lv);
    }


    public void setCallback(final CallbackUtils<Integer> callback) {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callback.onSuccess(i);
            }
        });
    }

}
