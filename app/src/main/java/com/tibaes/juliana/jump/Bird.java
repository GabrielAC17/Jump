package com.tibaes.juliana.jump;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by juliana on 14/09/15.
 */
public class Bird {
    private static final int x = 100;
    private static final int raio = 50;
    private Game game;
    Bitmap birdbit;

    private int altura;

    public Bird(){
        this.altura = 600;
    }

    public void desenhando(Canvas canvas,Context context){
        birdbit = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        canvas.drawBitmap(birdbit,raio,altura, null);
    }

    public void cai(){
        this.altura +=30;
    }

    public void pula(){
        if (altura>raio){

            altura -= 150;
        }
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
