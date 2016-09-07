package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;

/**
 * Created by Administrator on 2016/8/11.
 */
public class DisplayTextView extends LinearLayout {

    /**
     * @param context
     * @param attrs   设置自定义属性
     *                显示文本，隐藏文本，最大行数，暂时隐藏的时间
     *                文本颜色设定 text颜色和show颜色
     */
    public DisplayTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DisplayTextView);
        showText = a.getString(R.styleable.DisplayTextView_showMoreText);
        if (showText == null || showText.equals(""))
            showText = "显示更多";
        hideText = a.getString(R.styleable.DisplayTextView_hideMoreText);
        if (hideText == null || hideText.equals(""))
            hideText = "更多";
        customLines = a.getInt(R.styleable.DisplayTextView_maxLines, 10);
        currTime = a.getInt(R.styleable.DisplayTextView_time, 300);
        int textColor = a.getColor(R.styleable.DisplayTextView_textColor, Color.BLACK);
        text.setTextColor(textColor);
        int showColor = a.getColor(R.styleable.DisplayTextView_showColor, Color.GRAY);
        show.setTextColor(showColor);
        String msg = a.getString(R.styleable.DisplayTextView_text);
        if (msg != null && !msg.equals(""))
            setText(msg);
        show.setText(showText);
        a.recycle();
    }

    /**
     * 展示多少行
     */
    int customLines = 10;
    TextView text;
    TextView show;

    //文本最大行数
    int countLines;

    //show文本展示
    String showText = "显示更多";
    String hideText = "更多";

    //设置动画时间
    int currTime = 300;

    public int getCurrTime() {
        return currTime;
    }

    public void setCurrTime(int currTime) {
        this.currTime = currTime;
    }

    public String getHideText() {
        return hideText;
    }

    public void setHideText(String hideText) {
        this.hideText = hideText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
        show.setText(showText);
    }

    public void setPadding(int left, int top, int right, int bottom) {
        text.setPadding(left, top, right, left);
    }

    public void setPadding(int padding) {
        text.setPadding(padding, padding, padding, padding);
    }


    public int getCustomLines() {
        return customLines;
    }

    public void setCustomLines(int customLines) {
        this.customLines = customLines;
    }

    public DisplayTextView(Context context) {
        super(context);
        init();
    }


    private void init() {
        //添加View，设置基本属性
        text = new TextView(getContext());
        show = new TextView(getContext());
        text.setPadding(5, 5, 5, 5);
        show.setPadding(5, 5, 5, 5);
        setOrientation(VERTICAL);
        this.addView(text);
        this.addView(show);
        show.setText(showText);
    }

    public void setText(String msg) {
        text.setText(msg);
        text.post(new Runnable() {
            @Override
            public void run() {
                //获取当前行数
                countLines = text.getLineCount();
                if (countLines == 1) {
                    text.setGravity(Gravity.CENTER);
                } else {
                    text.setGravity(Gravity.LEFT);
                }
                //如果文本超过了设置的行数，那么显示“显示更多”
                if (countLines > customLines) {
                    text.setLines(customLines);
                    show.setVisibility(View.VISIBLE);
                    //设置显示的行数

                    show.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String string = show.getText().toString();
                            if (string.equals(showText)) {
                                show.setText(hideText);
                                //展开 从最大限制行数到自身行数
                                ObjectAnimator.ofInt(text, "lines", customLines, countLines).setDuration(currTime).start();
                            } else {
                                show.setText(showText);
                                //从自身行数到最大限制行数
                                ObjectAnimator.ofInt(text, "lines", countLines, customLines).setDuration(currTime).start();
                            }
                        }
                    });
                } else
                    show.setVisibility(View.GONE);
            }
        });
    }
}
