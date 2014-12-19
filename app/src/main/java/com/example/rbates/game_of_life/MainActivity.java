package com.example.rbates.game_of_life;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.Game;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MainActivity extends ActionBarActivity {

    public static final int NUM_COLUMNS = 15;
    public static final int REFRESH_TIME = 150;
    private Game game;
    private Subscription subscription;
    private BoardView boardView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.game = new Game(NUM_COLUMNS);
        textView = (TextView) findViewById(R.id.text);
        textView.setText("Generation 0");
        boardView = (BoardView) findViewById(R.id.board_view);
        boardView.setBoard(game.createBoard());
        boardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscription.unsubscribe();
                boardView.setBoard(game.createBoard());
                subscription = runGame();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscription = runGame();
    }

    private Subscription runGame() {
        return Observable.interval(REFRESH_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        textView.setText("Generation " + aLong);
                        boardView.setBoard(game.calculateNextGeneration());
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        subscription.unsubscribe();
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
