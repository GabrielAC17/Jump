package com.tibaes.juliana.jump;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;


/**
 * Created by gabri on 06/10/2015.
 */

public class Pipe {
    Bitmap canobitmap;

    private int left;
    private int top;

    //classe construtora (googleie o que é isso)
    public Pipe(int left, int top) {
        this.left = left;
        this.top = top;


    }

    //getters and setters
    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    //desenha os canos na tela, isso é chamado  e controlado pela classe logicpipes
    public void desenhando(Canvas canvas,Context context)
    {
        canobitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        canvas.drawBitmap(canobitmap,left,top, null);
    }



}

