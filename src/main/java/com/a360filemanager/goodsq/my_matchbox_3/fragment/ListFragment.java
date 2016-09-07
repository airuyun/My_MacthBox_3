package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goodsq on 2016/9/1.
 */
public class ListFragment extends BaseFragment {

    List<String> mList = new ArrayList<>();


    @Override
    public View getLayout() {
        View view = View.inflate(getContext(), R.layout.fragment_list,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < 100; i++) {
            mList.add("条目"+i);
        }
        ListView lv = (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,mList));
    }
}
