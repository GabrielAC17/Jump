package com.tibaes.juliana.jump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
        Vamos adicionar o nosso modelo na tela (surface)
    */
        game = new Game(this);
        FrameLayout container =
                (FrameLayout) findViewById(R.id.container);
        container.addView(game);
    }

    // pede pra parar a aplicação
    @Override
    protected void onPause(){
       super.onPause();
        game.cancela();
    }

    // vamos começar a aplicação
    @Override
    protected void onResume(){
        super.onResume();
        game.inicia();
        new Thread(game).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
