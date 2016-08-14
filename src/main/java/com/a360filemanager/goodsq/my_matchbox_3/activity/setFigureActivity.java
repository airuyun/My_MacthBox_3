package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.HeadPicAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PopupBean;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ConstantUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.FileUtils;
import com.a360filemanager.goodsq.my_matchbox_3.view.MyGridView;
import com.a360filemanager.goodsq.my_matchbox_3.view.ScaleImageView;
import com.a360filemanager.goodsq.my_matchbox_3.view.ShowHeadPopuoWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/11.
 */
public class setFigureActivity extends BaseActvity {
    @InjectView(R.id.set_figure_tv_cancle)
    TextView setFigureTvCancle;
    @InjectView(R.id.set_figure_tv_allImg)
    TextView setFigureTvAllImg;
    @InjectView(R.id.set_figure_tv_finish)
    TextView setFigureTvFinish;
    @InjectView(R.id.set_figure_siv)
    ScaleImageView setFigureSiv;
    @InjectView(R.id.set_figure_mgv)
    MyGridView setFigureMgv;
    @InjectView(R.id.set_figure_sv_img)
    ScrollView setFigureSvImg;

    List<File> pics;

    List<File> gridFiles = new ArrayList<>();

    List<PopupBean> mList = new ArrayList<>();

    HeadPicAdapter adapter;


    @Override
    public void init() {
        if (MyApp.getInstance().getAllPic() != null) {
            pics = MyApp.getInstance().getAllPic();
        } else {
            pics = FileUtils.getAllPicture(Environment.getExternalStorageDirectory());
            MyApp.getInstance().setAllPic(pics);
        }
        initSIV();
        initData();
        initGridView();
    }

    private void initGridView() {
        gridFiles.addAll(pics);
        adapter = new HeadPicAdapter(this, gridFiles);
        setheadGv.setAdapter(adapter);
        setheadGv.setOnItemClickListener(this);
    }

    private void initData() {
        //文件夹名称
        HashSet<String> set = new HashSet();//Set 无序 不重复
        for (int i = 0; i < pics.size(); i++) {
            set.add(pics.get(i).getParent());
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String name = it.next();
            File file = new File(name);
            PopupBean bean = new PopupBean();
            bean.setName(file.getName());
            bean.setAbsPath(name);
            int index = 0;
            for (int i = 0; i < pics.size(); i++) {
                if (pics.get(i).getParent().equals(name)) {
                    if (index == 0)
                        bean.setFirstPic(pics.get(i).getAbsolutePath());
                    index++;
                }
            }
            bean.setCount(index);
            mList.add(bean);
        }
        PopupBean bean = new PopupBean();
        bean.setName("所有图片");
        bean.setFirstPic(pics.get(0).getAbsolutePath());
        bean.setChecked(true);
        bean.setAbsPath(Environment.getExternalStorageDirectory().getAbsolutePath());
        bean.setCount(pics.size());
        mList.add(0, bean);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ScrollView sc = (ScrollView) findViewById(R.id.sv);
        sc.fullScroll(ScrollView.FOCUS_UP);
    }

    private void initSIV() {
        int width = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
        setheadSiv.setLayoutParams(params);
        setheadSiv.setImageBitmap(BitmapFactory.decodeFile(pics.get(0).getAbsolutePath()));
    }


    @Override
    public int getLayout() {
        return R.layout.activity_sethead;
    }

    @OnClick({R.id.set_figure_tv_cancle, R.id.set_figure_tv_allImg, R.id.set_figure_tv_finish, R.id.set_figure_siv, R.id.set_figure_mgv, R.id.set_figure_sv_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_figure_tv_cancle:
                break;
            case R.id.set_figure_tv_allImg:
                showPopupWindow();
                break;
            case R.id.set_figure_tv_finish:
                finish();
                break;
            case R.id.set_figure_siv:
                break;
            case R.id.set_figure_mgv:
                break;
            case R.id.set_figure_sv_img:
                break;
        }
    }

    int dirIndex = 0;

    private void showPopupWindow() {
        tvTitleAllpic.setSelected(true);
        final ShowHeadPopuoWindow window = new ShowHeadPopuoWindow(this, mList);

        window.setCallback(new CallbackUtils<Integer>() {

            @Override
            public void onSuccess(Integer integer) {
                gridFiles.clear();
                tvTitleAllpic.setText(mList.get(integer).getName());
                //改变GridView的数据
                if (mList.get(integer).getAbsPath().equals(Environment.getExternalStorageDirectory().getAbsolutePath()))
                    gridFiles.addAll(pics);
                else {
                    for (int i = 0; i < pics.size(); i++) {
                        if (mList.get(integer).getAbsPath().equals(pics.get(i).getParent())) {
                            gridFiles.add(pics.get(i));
                        }
                    }
                }
                mList.get(dirIndex).setChecked(false);
                mList.get(integer).setChecked(true);
                dirIndex = integer;
                adapter.notifyDataSetChanged();
                window.dismiss();
            }

            @Override
            public void onFinish() {

            }
        });
        window.showAsDropDown(sethead_rl);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvTitleAllpic.setSelected(false);
            }
        });
    }

    public static final int CAMERA_CODE = 9991;

    /**
     * GridView点击事件
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    String filePath;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            //打开摄像头
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            filePath = ConstantUtils.IMAGE_PATH + "/" + System.currentTimeMillis() + ".jpg";
            startActivityForResult(intent, CAMERA_CODE);
        } else {
            adapter.setIndex(i);
            setheadSiv.setImageBitmap(BitmapFactory.decodeFile(gridFiles.get(i - 1).getAbsolutePath()));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CODE && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            setheadSiv.setImageBitmap(bitmap);
        }
    }
}
