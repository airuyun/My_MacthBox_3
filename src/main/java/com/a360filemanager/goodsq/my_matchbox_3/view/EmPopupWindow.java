package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;

/**
 * Created by Administrator on 2016/8/15.
 */
public class EmPopupWindow extends PopupWindow {
    Context mContext;

    public EmPopupWindow(final Context context) {
        super(context);
        mContext = context;
        GridView gridView = new GridView(context);
        gridView.setAdapter(new EMAdapter());
        gridView.setNumColumns(7);
        setWidth(context.getResources().getDisplayMetrics().widthPixels);
        setHeight(300);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setOutsideTouchable(true);
        setContentView(gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (callback != null) {
                    //[  start    ] end
                    callback.onSuccess("【" + getName(i) + "】");
                }
            }
        });
    }

    CallbackUtils<String> callback;

    public void setCallback(CallbackUtils<String> callback) {
        this.callback = callback;
    }

    public class EMAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 107;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            String name = getName(i);
            int id = 0;
            try {
                id = R.drawable.class.getField(name).getInt(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if (view == null) {
                view = new ImageView(mContext);
            }
            ImageView iv = (ImageView) view;
            iv.setImageResource(id);
            return view;
        }
    }

    @NonNull
    private String getName(int i) {
        String name = "f_static_";
        if (i < 10) {
            name += "00" + i;
        } else if (i < 100) {
            name += "0" + i;
        } else
            name += i;
        return name;
    }

}
