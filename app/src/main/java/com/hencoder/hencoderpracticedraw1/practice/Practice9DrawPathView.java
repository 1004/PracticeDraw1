package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    private Paint paint;

    {
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
    }

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        int midWidth = getWidth() / 2;
        int midHeight = getHeight() / 2;

        Path path=new Path();
//        path.moveTo(midWidth,midHeight);
//        path.addArc(midWidth,midHeight-75,midWidth+150,midHeight+75,-1800,240);
//        path.rLineTo(-150,150);
//        path.rLineTo(-150,-150);
//        path.addArc(midWidth-150,midHeight-75,midWidth,midHeight+75,-255,255);

        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
        path.close();
        canvas.drawPath(path,paint);
    }
}
