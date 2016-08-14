package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.a360filemanager.goodsq.my_matchbox_3.utils.ConstantUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/8/6.
 */
public class ScaleImageView extends ImageView implements View.OnTouchListener {

    public static final int NONE = 1;
    public static final int DRAG = 2;
    public static final int SCALE = 3;

    private int mode = NONE;

    //拖拽点
    PointF dragPointF = new PointF();
    //中心点
    PointF midPointF = new PointF();

    float minScale = 1f;
    float maxScale = 15f;

    float dest = 1;

    Bitmap bmp;

    Paint paint;

    DisplayMetrics dm;

    //矩阵
    Matrix matrix = new Matrix();

    private void init() {
        initPaint();
        dm = getContext().getResources().getDisplayMetrics();
        //非常重要一步
        setScaleType(ScaleType.MATRIX);
        setImageMatrix(matrix);
        this.setOnTouchListener(this);
    }


    @Override
    public void setImageBitmap(Bitmap bm) {
        if (bm != null) {
            matrix = new Matrix();
            int width = dm.widthPixels;
            float widthAndHeight = bm.getWidth() >= bm.getHeight() ? bm.getHeight() : bm.getWidth();
            float ratio = width / widthAndHeight;
            Bitmap bmp = ThumbnailUtils.extractThumbnail(bm, (int) (bm.getWidth() * ratio), (int) (bm.getHeight() * ratio));
            super.setImageBitmap(bmp);
            this.bmp = bmp;
        }
    }

    int time = 0;
    boolean isShowWhite;

    public void startThread() {
        time = 100;
        new Thread() {
            @Override
            public void run() {
                while (time-- > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                time = 0;
                isShowWhite = false;
                postInvalidate();
            }
        }.start();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setAlpha(0x80);
    }

    public ScaleImageView(Context context) {
        super(context);
        init();
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //解决事件冲突


    // ViewGroup
    //Dispatch-->
    //分发事件
    //拦截事件
    //处理事件

    //View
    //分发事件
    //处理事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //请求父布局不要拦截事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.bmp == null)
            return false;
        isShowWhite = true;
        invalidate();
        switch (motionEvent.getAction() & motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mode = DRAG;
                dragPointF.set(motionEvent.getX(), motionEvent.getY());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                dest = calculate(motionEvent);
                if (dest > 10f) {
                    float x = motionEvent.getX(0) + motionEvent.getX(1);
                    float y = motionEvent.getY(0) + motionEvent.getY(1);
                    midPointF.set(x / 2, y / 2);
                    mode = SCALE;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.postTranslate(motionEvent.getX() - dragPointF.x, motionEvent.getY() - dragPointF.y);
                    dragPointF.set(motionEvent.getX(), motionEvent.getY());
                } else if (mode == SCALE) {
                    float sc = calculate(motionEvent) / dest;
                    matrix.postScale(sc, sc, midPointF.x, midPointF.y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (time > 0) {
                    //如果上一次还未结束
                    time = 100;
                } else {
                    //如果已经结束
                    startThread();
                }
                //复位
                resetDrag();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
        }
        //图片缩放的复位操作
        setImageMatrix(matrix);
        resetScale();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = dm.widthPixels;
        if (isShowWhite) {
            canvas.drawLine(0, width / 3, width, width / 3, paint);
            canvas.drawLine(0, width / 3 * 2, width, width / 3 * 2, paint);
            canvas.drawLine(width / 3, 0, width / 3, width, paint);
            canvas.drawLine(width / 3 * 2, 0, width / 3 * 2, width, paint);
        }
    }

    //缩放复位
    private void resetScale() {
        if (mode == SCALE) {
            float[] values = new float[9];
            matrix.getValues(values);
            if (values[0] < minScale) {
                matrix.setScale(minScale, minScale);
            } else if (values[0] > maxScale) {
                matrix.setScale(maxScale, maxScale, midPointF.x
                        , midPointF.y);
            }
        }
    }

    //复位
    private void resetDrag() {
        //将当前图片的信息丢入矩阵中
        RectF rect = new RectF(0, 0, bmp.getWidth(), bmp.getHeight());
        matrix.mapRect(rect);
        float x = 0, y = 0;
        int width = dm.widthPixels;
        if (rect.left > 0) {
            //
            x = -rect.left;
        } else if (rect.right < width) {
            x = width - rect.right;
        }

        if (rect.top > 0) {
            y = -rect.top;
        } else if (rect.bottom < width) {
            y = width - rect.bottom;
        }
        matrix.postTranslate(x, y);

    }


    public Bitmap getBitmap() {
        Bitmap bmp = Bitmap.createBitmap(dm.widthPixels, dm.widthPixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(((BitmapDrawable) getDrawable()).getBitmap(), matrix, paint);
        return bmp;
    }

    public String saveBitmap() {
        Bitmap bmp = getBitmap();
        String dir = ConstantUtils.IMAGE_PATH + System.currentTimeMillis() + ".png";
        try {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(dir));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dir;
    }


    private float calculate(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() >= 2) {
            float x = motionEvent.getX(0) - motionEvent.getX(1);
            float y = motionEvent.getY(0) - motionEvent.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        } else
            return 0;
    }
}
