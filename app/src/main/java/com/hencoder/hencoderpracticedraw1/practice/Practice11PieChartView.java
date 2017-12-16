package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {
    private Paint paint;
    private Paint linePaint;
    private Paint textPaint;
    private List<DataBean> dataList;

    private static final int RADIUS = 300;
    private static final int INTERVAL_ANGLE = 2;
    private static final int DEVIATION_LENGTH = 30;
    private static final int SLANT_LINE_LENGTH = 30;
    private static final int STRAIGHT_LINE_LENGTH = 50;
    private static final int TEXT_INTERVAL = 15;
    private static final int TEXT_SIZE = 28;

    {
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        linePaint =new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setAntiAlias(true);
        linePaint.setColor(0xFFFFFFFF);
        linePaint.setStrokeWidth(3);

        textPaint =new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setColor(0xFFFFFFFF);
        textPaint.setTextSize(TEXT_SIZE);

        dataList=new ArrayList<>();
        dataList.add(new DataBean(Color.YELLOW,100,"Marshmallow",true));
        dataList.add(new DataBean(Color.LTGRAY,200,"Froyo"));
        dataList.add(new DataBean(Color.GREEN,50,"Gingerbread"));
        dataList.add(new DataBean(Color.BLUE,10,"Ice Cream Sandwich"));
        dataList.add(new DataBean(Color.CYAN,30,"Jelly Bean"));
        dataList.add(new DataBean(Color.DKGRAY,200,"KitKat"));
        dataList.add(new DataBean(Color.RED,150,"Lollipop"));
    }

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        int midWidth = getWidth() / 2;
        int midHeight = getHeight() / 2;

        BigDecimal startArc=BigDecimal.valueOf(0);
        int totalArc = 360-(dataList.size()*INTERVAL_ANGLE);

        RectF rectF=new RectF(midWidth-RADIUS,midHeight-RADIUS,midWidth+RADIUS,midHeight+RADIUS);
        for (DataBean dataBean : dataList) {
            //这部分扇形绘制的弧度
            BigDecimal sweepAngle = BigDecimal.valueOf(totalArc).multiply(dataBean.getPercentage());
            //这部分扇形绘制的弧度中心，用于扇形的偏移与指示线的绘制
            int midSweepAngle = startArc.add(sweepAngle.divide(BigDecimal.valueOf(2))).intValue();

            //中心点坐标，为这部分图形的中心点
            int centerX;
            int centerY;
            if (dataBean.isDeviation){
                centerX=(int)(midWidth + DEVIATION_LENGTH*Math.cos(midSweepAngle*(Math.PI/180)));
                centerY=(int)(midHeight + DEVIATION_LENGTH*Math.sin(midSweepAngle*(Math.PI/180)));
            }else{
                centerX=midWidth;
                centerY=midHeight;
            }

            //扇形部分绘制
            paint.setColor(dataBean.getColor());
            //判断是绘制普通
            if (!dataBean.isDeviation) {
                canvas.drawArc(rectF, startArc.floatValue(), sweepAngle.floatValue(), true, paint);
            }else{
                //绘制偏移的扇形
                RectF deviationRectF=new RectF(centerX-RADIUS,centerY-RADIUS,
                        centerX+RADIUS,centerY+RADIUS);
                canvas.drawArc(deviationRectF, startArc.floatValue(), sweepAngle.floatValue(), true, paint);
            }
            startArc = startArc.add(sweepAngle).add(BigDecimal.valueOf(INTERVAL_ANGLE));

            //指示线斜线部分绘制
            int lineStartX = (int)(centerX + RADIUS*Math.cos(midSweepAngle*(Math.PI/180)));
            int lineStartY = (int)(centerY + RADIUS*Math.sin(midSweepAngle*(Math.PI/180)));
            int lineEndX = (int)(centerX + (RADIUS+ SLANT_LINE_LENGTH)*Math.cos(midSweepAngle*(Math.PI/180)));
            int lineEndY = (int)(centerY +(RADIUS+ SLANT_LINE_LENGTH)*Math.sin(midSweepAngle*(Math.PI/180)));
            canvas.drawLine(lineStartX,lineStartY,lineEndX,lineEndY, linePaint);

            //指示线直线部分绘制
            if (midSweepAngle>90&&midSweepAngle<270) {
                canvas.drawLine(lineEndX, lineEndY, lineEndX-STRAIGHT_LINE_LENGTH,lineEndY, linePaint);
            }else{
                canvas.drawLine(lineEndX, lineEndY, lineEndX+STRAIGHT_LINE_LENGTH,lineEndY, linePaint);
            }

            //文字部分绘制
            String text = dataBean.getTitle();
            if (midSweepAngle>90&&midSweepAngle<270){
                canvas.drawText(text, lineEndX- textPaint.measureText(text)-TEXT_INTERVAL-STRAIGHT_LINE_LENGTH,
                        lineEndY- (textPaint.ascent()+ textPaint.descent()) /2, textPaint);
            }else{
                canvas.drawText(text, lineEndX+TEXT_INTERVAL+STRAIGHT_LINE_LENGTH,
                        lineEndY- (textPaint.ascent()+ textPaint.descent()) /2, textPaint);
            }
        }
    }

    private static class DataBean{
        private static int total;

        @ColorInt private int color;
        private int dataNum;
        private String title;
        private boolean isDeviation = false;

        public DataBean(int color, int dataNum, String title) {
            this(color,dataNum,title,false);
        }

        public DataBean(int color, int dataNum, String title, boolean isDeviation) {
            this.color = color;
            this.dataNum = dataNum;
            this.title = title;
            this.isDeviation = isDeviation;
            total+=dataNum;
        }

        /**
         * 使用Bigdecimal来避免float不精确的问题
         */
        public BigDecimal getPercentage(){
            return BigDecimal.valueOf(dataNum).divide(BigDecimal.valueOf(total),4, RoundingMode.DOWN);
        }

        public int getColor() {
            return color;
        }

        public int getDataNum() {
            return dataNum;
        }

        public String getTitle() {
            return title;
        }
    }
}
