package com.tibaes.juliana.jump;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.view.View;
import android.graphics.Paint;

import com.tibaes.juliana.jump.com.tibaes.juliana.jump.engine.LogicPipes;

/**
 * Created by juliana on 14/09/15.
 */
public class Game extends SurfaceView implements Runnable,View.OnTouchListener{
    Bitmap mBitmap;
    //declaração de variáveis
    private Bird bird;
    private LogicPipes logicPipes;
    private MediaPlayer mediaPulo;
    private MediaPlayer mediaCol;
    private MediaPlayer mediaPoints;
    private MediaPlayer mediaOver;
    private int lives = 3;
    private int score = 0;
    private static final Paint vermelho = Vermelho.getCorPassaro();
    private int i;



    // vamos verificar se o usuário está no jogo ou não
    private boolean isRunning = true;

    // canvas> desenho na tela, para ter acesso a ele precisamos do holder
    private final SurfaceHolder holder = getHolder();


    public Game(Context context){
        super (context);
        //cria o background de nuvens
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        inicializaElementos();
        setOnTouchListener(this);
        logicPipes.CriarPipes();
        //Atribui o aúdio através do mediaplayer (detalhe: isso é diferente do que a professora fez mas não tem problema)
        mediaPulo = MediaPlayer.create(context, R.raw.pulo);
        mediaCol = MediaPlayer.create(context, R.raw.colisao);
        mediaPoints = MediaPlayer.create(context,R.raw.pontos);
        mediaOver = MediaPlayer.create(context,R.raw.gameover);
    }

    @Override
    public void run() {

        while (isRunning) {
            // aqui vamos gerenciar os elementos do jogo que
            // rodam em threads

            // vamos só garantir que as paradas estão funcionando antes de mandar startar
            if (!holder.getSurface().isValid()) continue;

            Canvas canvas = holder.lockCanvas();

            //Aqui ele manda desenhar os canos e pássaro a cada frame
            // o movepipes é uma função de logicpipes que criei para fazer eles andarem
            canvas.drawBitmap(mBitmap, 0, 0, null);
            logicPipes.MovePipes();
            bird.desenhando(canvas, getContext());
            logicPipes.getPipes().get(0).desenhando(canvas, getContext());
            logicPipes.getPipes().get(1).desenhando(canvas, getContext());
            logicPipes.getPipes().get(2).desenhando(canvas, getContext());
            logicPipes.getPipes().get(3).desenhando(canvas, getContext());

            //esse cai é para fazer o pássaro cair um pouco a cada frame que se passa
            bird.cai();
            check();

            //aqui ele desenha a HUD do jogo, ou seja, vida e score.
            canvas.drawText("Lives: " + lives, 10, 75, vermelho);
            canvas.drawText("Score: "+score, 10, 1600-75, vermelho);


            // desenhando os elementos
            holder.unlockCanvasAndPost(canvas);
        }
    }

    // método para parar a aplicação
    public void cancela(){

        this.isRunning = false;
    }

    public void inicia(){

        this.isRunning = true;
    }

    private void inicializaElementos() {
        this.bird = new Bird();
        this.logicPipes = new LogicPipes();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // toda vez que o usuário clica na tela ele faz o pássaro pular e toca um somzinho
        bird.pula();
        mediaPulo.start();
        return false;
    }
    public void check(){
        //nesse loop ele verifica se o cano wrtyuki, o que significa que ele bateu e morreu, como tem 4 canos, ele verifica todos os 4 a cada frame
        for (i=0;i<4;i++){
            if ((logicPipes.getPipes().get(i).getLeft()<50 &&  logicPipes.getPipes().get(i).getLeft() > 0)){
                if (bird.getAltura() <600 || bird.getAltura() >850){
                    //som de gameover, uma pausa no thread (para congelar a tela quando morre e dar um tempo pro jogador respirar
                    mediaCol.start();
                    try{ Thread.sleep(500); }catch(InterruptedException e){ }
                    //importante notar que é necessário usar try catch nessa parte acima pois é passível de erro e isso pode travar o jogo.
                    lives--;
                    //reseta a altura original do pássaro
                    bird.setAltura(600);
                }
            }
            //se um dos canos passar a posição do pássaro significa que ele ganhou 2 pontos (1 por cano)
            if (logicPipes.getPipes().get(i).getLeft()==50){
                score++;
                mediaPoints.start();
            }
        }

        //Verifica se o pássaro caiu demais (abaixo do visível da tela, ou buraco se desejar)
        if (bird.getAltura()>1600){
            mediaCol.start();
            try{ Thread.sleep(500); }catch(InterruptedException e){ }
            lives--;
            bird.setAltura(600);
        }
        //verifica se acabaram as vidas e reinicia o jogo.
        if (lives <= 0){
            mediaOver.start();
            try{ Thread.sleep(3000); }catch(InterruptedException e){ }
            lives = 3;
            bird.setAltura(600);
            score = 0;

        }
    }

}
