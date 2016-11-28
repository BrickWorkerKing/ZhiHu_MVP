//package com.nn.zhihumvp.customview;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.view.View;
//
//import com.nn.zhihumvp.R;
//import com.nn.zhihumvp.util.DensityUtil;
//
///**
// * 实线
// *
// * @author LZRUI
// *         <p/>
// *         line_color 颜色
// *         line_height 高度（或者宽度）
// *         orientation 方向
// */
//public class LineView extends View {
//
//    public static int ORIENTATION_HORIZONTAL = 0;
//    private Paint mPaint;
//    private int orientation;
//    private int marginLeft = 0;
//    private int marginRight = 0;
//    private int marginTop = 0;
//    private int marginButton = 0;
//    private Context mContext;
//
//    public LineView(Context context) {
//        this(context, null);
//    }
//
//    public LineView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.mContext = context;
//        int color = 0xffe5e5e5;
//        float lineHeight = 1.0f;
//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LineView, defStyleAttr, 0);
//
//        if (a != null) {
//            orientation = a.getInt(R.styleable.LineView_line_orientation, ORIENTATION_HORIZONTAL);
//            color = a.getColor(R.styleable.LineView_line_color, 0xffe5e5e5);
//            lineHeight = a.getDimension(R.styleable.LineView_line_height, 1.0f);
//            a.recycle();
//        }
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setColor(color);
//        mPaint.setStrokeWidth(lineHeight);
//        mPaint.setStyle(Paint.Style.STROKE);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
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
//        this.marginLeft = DensityUtil.dip2px(mContext, (float) marginLeft);
//    }
//
//    /**
//     * 当为横线时，设置右边距
//     *
//     * @param marginRight
//     */
//    public void setMarginRight(int marginRight) {
//        this.marginRight = DensityUtil.dip2px(mContext, (float) marginRight);
//    }
//
//    /**
//     * 当为竖线时，设置上边距
//     *
//     * @param marginTop
//     */
//    public void setMarginTop(int marginTop) {
//        this.marginTop = DensityUtil.dip2px(mContext, (float) marginTop);
//    }
//
//    /**
//     * 当为竖线时，设置下边距
//     *
//     * @param marginButton
//     */
//    public void setMarginButton(int marginButton) {
//        this.marginButton = DensityUtil.dip2px(mContext, (float) marginButton);
//    }
//}
