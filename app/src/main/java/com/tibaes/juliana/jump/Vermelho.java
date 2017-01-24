package com.tibaes.juliana.jump;

import android.graphics.Paint;

/**
 * Created by juliana on 14/09/15.
 */
public class Vermelho {
    //cria a cor vermelha.
    public static Paint getCorPassaro(){
        Paint vermelho = new Paint();
        vermelho.setTextSize(100);
        vermelho.setColor(0xFFFF0000);
        return vermelho;
    }
}