package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {
    private Paint paint;
    private Path path;

    {
        paint = new Paint();
        paint.setAntiAlias(true);

        //右下角圆所使用的path
        path=new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.addCircle(500,500,80, Path.Direction.CW);
        path.addCircle(500,500,100, Path.Direction.CW);
    }

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆

        //实心圆
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200f,200f,100f,paint);

        //空心圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(500f,200f,100f,paint);

        //蓝色实心圆
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xFF4A90E2);
        canvas.drawCircle(200f,500f,100f,paint);

        //线宽为 20 的空心圆（使用Path完成）
        paint.setColor(Color.BLACK);
        canvas.drawPath(path,paint);
    }
}
