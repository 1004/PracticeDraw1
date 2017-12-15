package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {
    private Paint paint;

    {
        paint=new Paint();
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
    }

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        int midWidth = getWidth() / 2;
        int midHeight = getHeight() / 2;

        RectF rectF=new RectF(midWidth-250,midHeight-200,midWidth+250,midHeight+200);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF,20f,140f,false,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawArc(rectF,180f,60f,false,paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF,250f,100f,true,paint);
    }
}
