//package com.nn.zhihumvp.customview;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.DashPathEffect;
//import android.graphics.Paint;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.view.View;
//
//import com.nn.zhihumvp.R;
//
//
///**
// * 虚线，可以水平或者垂直的
// * <p>
// * dashWidth 表示'-'这样一个横线的宽度
// * dashGap 表示之间隔开的距离
// * dashThickness 厚度
// *
// * @author LZRUI
// */
//public class DashedLine extends View {
//
//    public static int ORIENTATION_HORIZONTAL = 0;
//    private Paint mPaint;
//    private int orientation;
//    private int marginLeft = 0;
//    private int marginRight = 0;
//    private int marginTop = 0;
//    private int marginButton = 0;
//
//    public DashedLine(Context context) {
//        this(context, null);
//    }
//
//    public DashedLine(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//        int dashGap = 5, dashLength = 5, dashThickness = 3;
//        int color = 0xffe5e5e5;
//        float lineHeight = 1.0f;
//
//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DividerView, 0, 0);
//
//        if (a != null) {
//            dashGap = a.getDimensionPixelSize(R.styleable.DividerView_dashGap, 5);
//            dashLength = a.getDimensionPixelSize(R.styleable.DividerView_dashLength, 5);
//            dashThickness = a.getDimensionPixelSize(R.styleable.DividerView_dashThickness, 3);
//            color = a.getColor(R.styleable.DividerView_lineColor, 0xffe5e5e5);
//            orientation = a.getInt(R.styleable.DividerView_orientation, ORIENTATION_HORIZONTAL);
//            lineHeight = a.getDimension(R.styleable.DividerView_lineHeight, 1.0f);
//            a.recycle();
//        }
//
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setColor(color);
//        mPaint.setStrokeWidth(lineHeight);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(dashThickness);
//        mPaint.setPathEffect(new DashPathEffect(new float[]{dashLength, dashGap,}, 0));
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//
//        if (Build.VERSION.SDK_INT >= 11) {
//            // 硬件加速
//            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
//        if (orientation == ORIENTATION_HORIZONTAL) {
//            float center = getHeight() * 0.5f;
//            canvas.drawLine(marginLeft, center, getWidth() - marginRight, center, mPaint);
//        } else {
//            float center = getWidth() * 0.5f;
//            canvas.drawLine(center, marginTop, center, getHeight() - marginButton, mPaint);
//        }
//    }
//
//    /**
//     * 当为横线时，设置左边距
//     *
//     * @param marginLeft
//     */
//    public void setMarginLeft(int marginLeft) {
//        this.marginLeft = marginLeft;
//    }
//
//    /**
//     * 当为横线时，设置右边距
//     *
//     * @param marginRight
//     */
//    public void setMarginRight(int marginRight) {
//        this.marginRight = marginRight;
//    }
//
//    /**
//     * 当为竖线时，设置上边距
//     *
//     * @param marginTop
//     */
//    public void setMarginTop(int marginTop) {
//        this.marginTop = marginTop;
//    }
//
//    /**
//     * 当为竖线时，设置下边距
//     *
//     * @param marginButton
//     */
//    public void setMarginButton(int marginButton) {
//        this.marginButton = marginButton;
//    }
//
//}
