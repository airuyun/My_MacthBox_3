package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.ImageAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ConstantUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.FileUtils;
import com.a360filemanager.goodsq.my_matchbox_3.view.MyGridView;
import com.a360filemanager.goodsq.my_matchbox_3.view.ScaleImageView;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/11.
 */
public class SetPortraitActivity extends BaseActvity implements AdapterView.OnItemClickListener {

    ImageAdapter adapter;
    IntentFilter filter;
    UpdateImageAdapterReceiver receiver;

    @InjectView(R.id.set_figure_tv_allImg)
    TextView setFigureTvAllImg;//还没有实现
    @InjectView(R.id.set_figure_siv)
    ScaleImageView setFigureSiv;
    @InjectView(R.id.set_figure_mgv)
    MyGridView setFigureMgv;


    @Override
    public int getLayout() {
        return R.layout.activity_set_figure;
    }

    @Override
    public void init() {
        filter = new IntentFilter();
        filter.addAction("notify ImageAdapter data set changed");
        receiver = new UpdateImageAdapterReceiver();
        registerReceiver(receiver, filter);
        FileUtils.startGetImageThread(this);
        initGridView();
        initSIV();
    }

    @OnClick({R.id.set_figure_tv_cancle, R.id.set_figure_tv_allImg, R.id.set_figure_tv_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_figure_tv_cancle:
                finish();
                break;
            case R.id.set_figure_tv_allImg:
                break;
            case R.id.set_figure_tv_finish:
                String path = setFigureSiv.saveBitmap();
                Intent intent = new Intent();
                intent.putExtra("path", path);
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CODE && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            setFigureSiv.setImageBitmap(bitmap);
            UserInfoBean.getInstance().setUrl(data.getStringExtra("data"));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ScrollView sc = (ScrollView) findViewById(R.id.set_figure_sv_img);
        sc.fullScroll(ScrollView.FOCUS_UP);
    }

    private void initGridView() {
        adapter = new ImageAdapter(this);
        setFigureMgv.setAdapter(adapter);
        setFigureMgv.setOnItemClickListener(this);
    }

    class UpdateImageAdapterReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            adapter.notifyDataSetChanged();
            initSIV();
            Toast.makeText(SetPortraitActivity.this, "广播发送成功", Toast.LENGTH_SHORT).show();
        }
    }

    private void initSIV() {
        int width = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
        setFigureSiv.setLayoutParams(params);
        if (FileUtils.getAllImages().size() > 0)
            setFigureSiv.setImageBitmap(BitmapFactory.decodeFile(FileUtils.getAllImages().get(0).path));
        else
            setFigureSiv.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.icon_register_avatar_default));
    }


    public static final int CAMERA_CODE = 9991;
    //String filePath;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            //打开摄像头
            cameraPermission();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //filePath = ConstantUtils.IMAGE_PATH + "/" + System.currentTimeMillis() + ".jpg";
            startActivityForResult(intent, CAMERA_CODE);
        } else {
            adapter.setIndex(i);
            setFigureSiv.setImageBitmap(BitmapFactory.decodeFile(FileUtils.getAllImages().get(i - 1).path));
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 6.0版本之后打开摄像头需要动态申请权限
     */
    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;

    public void cameraPermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            int checkCameraPermission1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            int checkCameraPermission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkCameraPermission1 != PackageManager.PERMISSION_GRANTED || checkCameraPermission2 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_CALL_PHONE);
            }
        }
    }

}
