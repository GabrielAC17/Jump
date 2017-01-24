package com.opet.jumper;

import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;



public class MainActivity extends ActionBarActivity {
 private Jogo jogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
jogo = new Jogo(this);
    FrameLayout container =
    		(FrameLayout)findViewById(R.id.container);
    container.addView(jogo);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onPause(){
    	super.onPause();
    	jogo.cancela();
    }
    protected void onResume(){
    	super.onResume();
    	jogo.inicia();
    	new Thread(jogo).start();
    }
}
