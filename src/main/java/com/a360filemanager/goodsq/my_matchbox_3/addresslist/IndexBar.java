package com.a360filemanager.goodsq.my_matchbox_3.addresslist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public class IndexBar extends LinearLayout {

    List<String> letter;


    public IndexBar(Context context) {
        super(context);
        init();
    }

    public IndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //有可能，不是一个String
    public void setLetter(List<? extends IGetString> list) {
        //去掉重复的
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            //A - Z changchun
            String str = list.get(i).getString().substring(0, 1).toUpperCase();
            //LogUtils.e("--------首字母" + str);
            if (str.matches("[A-Z]"))
                set.add(list.get(i).getString().substring(0, 1).toUpperCase());
            else
                set.add("#");
        }
        letter = new ArrayList<>(set);
        Collections.sort(letter, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
        //LogUtils.e(letter.toString());
        //A-Z
        drawLayout();
    }

    public void init() {
        this.setOrientation(VERTICAL);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(1, Color.BLACK);
        drawable.setCornerRadius(30);
        drawable.setBounds(5, 5, 5, 5);
        this.setPadding(10, 10, 10, 10);
        this.setBackground(drawable);
        LayoutParams params = new LayoutParams(-2, -1);
        params.setMargins(5, 5, 5, 5);
        this.setLayoutParams(params);
    }

    private void drawLayout() {
        if (letter == null)
            return;
        this.removeAllViews();
        for (int i = 0; i < letter.size(); i++) {
            TextView tv = new TextView(getContext());
            LayoutParams params = new LayoutParams(-2, -1);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            tv.setText(letter.get(i));
            tv.setTextColor(Color.GRAY);
            tv.setLayoutParams(params);
            tv.setGravity(Gravity.CENTER);
            this.addView(tv);
            final int finalI = i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onIndexBarClickListener != null)
                        onIndexBarClickListener.onIndexBarClick(finalI, letter.get(finalI));
                }
            });
        }
    }

    public void setOnIndexBarClickListener(OnIndexBarClickListener onIndexBarClickListener) {
        this.onIndexBarClickListener = onIndexBarClickListener;
    }

    OnIndexBarClickListener onIndexBarClickListener;

    public interface OnIndexBarClickListener {
        public void onIndexBarClick(int position, String letter);
    }
}
