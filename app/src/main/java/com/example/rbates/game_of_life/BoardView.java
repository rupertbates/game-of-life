package com.example.rbates.game_of_life;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class BoardView extends View {
    private boolean[][] board;
    private float cellWidth;
    private Paint live;
    private Paint dead;
    private float radius;
    private Random random;

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBoard(boolean[][] board){
        this.board = board;
        cellWidth = getWidth() / board.length;
        radius = cellWidth / 2;
        live = new Paint();
        live.setColor(Color.RED);
        dead = new Paint();
        dead.setColor(Color.WHITE);
        random = new Random();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                float left = i * cellWidth;
                float top = j * cellWidth;
                boolean state = board[i][j];
                live.setColor(getRandomColour());
                canvas.drawCircle(left + radius, top + radius, radius, state ? live : dead);
            }
        }
    }

    private int getRandomColour(){
        return Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }


}
