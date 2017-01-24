package com.tibaes.juliana.jump.com.tibaes.juliana.jump.engine;

import com.tibaes.juliana.jump.MainActivity;
import com.tibaes.juliana.jump.Pipe;

import java.util.ArrayList;

/**
 * Created by gabri on 26/10/2015.
 */
public class LogicPipes {
    //cria uma lista de objetos do tipo cano (tipo variáveis int, float, mas com a classe cano).
    ArrayList<Pipe> pipes = new ArrayList<>();


    //getters and setters
    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public void setPipes(ArrayList<Pipe> pipes) {
        this.pipes = pipes;
    }

    //classe que cria os canos na tela, chamada na classe game.
    public void CriarPipes(){
        pipes.add(0,new Pipe(300,0));
        pipes.add(1,new Pipe(900,0));
        pipes.add(2,new Pipe(300,1000));
        pipes.add(3,new Pipe(900,1000));
    }

    //aqui ele move os canos 10 pixels a cada frame
    public void MovePipes(){
        pipes.get(0).setLeft(pipes.get(0).getLeft()-10);
        pipes.get(1).setLeft(pipes.get(1).getLeft()-10);
        pipes.get(2).setLeft(pipes.get(2).getLeft()-10);
        pipes.get(3).setLeft(pipes.get(3).getLeft()-10);

        //se os canos saírem da tela eles voltam ao começo lá, dando a impressão de vários canos e não só 4.
        if (pipes.get(0).getLeft() <-200){
            pipes.get(0).setLeft(1100);
        }
        if (pipes.get(1).getLeft() <-200){
            pipes.get(1).setLeft(1100);
        }
        if (pipes.get(2).getLeft() <-200){
            pipes.get(2).setLeft(1100);
        }
        if (pipes.get(3).getLeft() <-200){
            pipes.get(3).setLeft(1100);
        }
    }
}
