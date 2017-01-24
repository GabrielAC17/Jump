package com.opet.jumper;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Jogo extends SurfaceView implements Runnable{

	public Jogo(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SurfaceHolder getHolder() {
		return holder;
	}

	private final SurfaceHolder holder = getHolder();
	private boolean isRunning = true;
	@Override
	public void run(){
		while(isRunning){
			
		}
	}
	public void cancela(){
		this.isRunning = false;
		
	}
	public void inicia(){
		this.isRunning = true;	
	}
}
	


